package work.inabajun.springsecurityhydra.hydra

class HydraAcceptLoginResponse(var redirectTo : String) {

    // for jackson
    constructor():this("INVALID")

    override fun toString(): String {
        return "HydraAcceptLoginResponse(redirect_to='$redirectTo')"
    }
}