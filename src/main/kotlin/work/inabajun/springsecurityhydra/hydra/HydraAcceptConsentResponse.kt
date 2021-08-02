package work.inabajun.springsecurityhydra.hydra

class HydraAcceptConsentResponse(var redirectTo : String) {

    // for jackson
    constructor():this("INVALID")

    override fun toString(): String {
        return "HydraAcceptConsentResponse(redirect_to='$redirectTo')"
    }
}