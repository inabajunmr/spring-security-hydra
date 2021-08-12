package work.inabajun.springsecurityhydra

import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ApiController {

    @GetMapping("/api")
    fun sub(authentication: BearerTokenAuthentication): String {
        return authentication.tokenAttributes["sub"].toString()
    }
}