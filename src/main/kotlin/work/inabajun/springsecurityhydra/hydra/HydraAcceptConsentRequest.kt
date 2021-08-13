package work.inabajun.springsecurityhydra.hydra

import java.util.*

class HydraAcceptConsentRequest() {
    var grantAccessTokenAudience: List<String>? = null
    var grantScope: List<String>? = null
    var handledAt: Date? = null
    var remember = false
    var rememberFor = 0
    var session: Session? = null
}

class AccessToken

class IdToken

class Session {
    var access_token: AccessToken? = null
    var id_token: IdToken? = null
}
