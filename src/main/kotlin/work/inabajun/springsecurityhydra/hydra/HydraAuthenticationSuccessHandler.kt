package work.inabajun.springsecurityhydra.hydra

import org.springframework.http.RequestEntity
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler
import org.springframework.security.web.savedrequest.HttpSessionRequestCache
import org.springframework.security.web.savedrequest.RequestCache
import org.springframework.web.client.RestTemplate
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.web.util.UriComponentsBuilder

/**
 * AuthenticationSuccessHandler for redirect to Hydra
 */
class HydraAuthenticationSuccessHandler(private val restTemplate: RestTemplate) : SavedRequestAwareAuthenticationSuccessHandler() {

    private val requestCache: RequestCache = HttpSessionRequestCache()

    override fun onAuthenticationSuccess(request: HttpServletRequest, response: HttpServletResponse, authentication: Authentication) {

        val savedRequest = this.requestCache.getRequest(request, response)
        logger.info("savedRequest:${savedRequest}")

        if (savedRequest != null) {
            if (savedRequest.parameterMap["login_challenge"] != null) {
                val builder = UriComponentsBuilder.fromHttpUrl("http://localhost:9001/oauth2/auth/requests/login/accept")
                        .queryParam("login_challenge", savedRequest.parameterMap["login_challenge"]!![0])
                val req = RequestEntity.put(builder.toUriString())
                        .body(HydraAcceptLoginRequest(authentication.name))
                logger.info(req.toString())
                var res = restTemplate.exchange(req, HydraAcceptLoginResponse::class.java)
                logger.info("Hydra response: ${res.body.toString()}")
                this.requestCache.removeRequest(request, response)

                // redirect to Hydra
                redirectStrategy.sendRedirect(request, response, res.body?.redirectTo)
                return
            }
        }

        super.onAuthenticationSuccess(request, response, authentication)
    }
}
