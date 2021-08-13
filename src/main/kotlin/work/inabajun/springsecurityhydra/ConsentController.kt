package work.inabajun.springsecurityhydra

import org.springframework.http.RequestEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import work.inabajun.springsecurityhydra.hydra.*
import javax.servlet.http.HttpSession

@Controller
class ConsentController(private val restTemplate: RestTemplate, private val session: HttpSession) {

    private val logger = org.apache.commons.logging.LogFactory.getLog(this.javaClass)

    @GetMapping("consent")
    fun consentForm(@RequestParam("consent_challenge") challenge: String, model: Model): String {
        // https://www.ory.sh/hydra/docs/reference/api#operation/getConsentRequest
        val builder = UriComponentsBuilder.fromHttpUrl("http://localhost:9001/oauth2/auth/requests/consent")
                .queryParam("consent_challenge", challenge)
        var res = restTemplate.getForEntity(builder.toUriString(), HydraConsentRequestInformationResponse::class.java)
        logger.info("Hydra response: ${res.body}")

        model.addAttribute("requestedScope", res.body?.requestedScope)
        model.addAttribute("clientId", res.body?.client?.clientId)

        session.setAttribute("consent_challenge", challenge)
        session.setAttribute("requested_scope", res.body?.requestedScope)
        return "consent"
    }

    @PostMapping("consent")
    fun consent(): String {

        var challenge = session.getAttribute("consent_challenge")
        session.removeAttribute("consent_challenge")
        var requestedScope = session.getAttribute("requested_scope")
        session.removeAttribute("requested_scope")

        val builder = UriComponentsBuilder.fromHttpUrl("http://localhost:9001/oauth2/auth/requests/consent/accept")
                .queryParam("consent_challenge", challenge)
        val reqBody = HydraAcceptConsentRequest()
        reqBody.grantScope = requestedScope as List<String>?

        val req = RequestEntity.put(builder.toUriString())
                .body(reqBody)
        var res = restTemplate.exchange(req, HydraAcceptConsentResponse::class.java)
        return "redirect:${res.body?.redirectTo}"
    }
}
