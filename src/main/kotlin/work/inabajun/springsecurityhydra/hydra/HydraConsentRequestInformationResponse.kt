package work.inabajun.springsecurityhydra.hydra

import java.util.*

class HydraConsentRequestInformationResponse (var challenge:String) {

    // for jackson
    constructor():this("INVALID")

    var acr: String? = null
    var client: Client? = null
    var context: Context? = null
    var loginChallenge: String? = null
    var loginSessionId: String? = null
    var oidcContext: OidcContext? = null
    var requestUrl: String? = null
    var requestedAccessTokenAudience: List<String>? = null
    var requestedScope: List<String>? = null
    var skip = false
    var subject: String? = null
    override fun toString(): String {
        return "HydraConsentRequestInformationResponse(challenge='$challenge', acr=$acr, client=$client, context=$context, loginChallenge=$loginChallenge, loginSessionId=$loginSessionId, oidcContext=$oidcContext, requestUrl=$requestUrl, requestedAccessTokenAudience=$requestedAccessTokenAudience, requestedScope=$requestedScope, skip=$skip, subject=$subject)"
    }
}

class Jwks

class Metadata

class Client {
    var allowedCorsOrigins: List<String>? = null
    var audience: List<String>? = null
    var backchannelLogoutSessionRequired = false
    var backchannelLogoutUri: String? = null
    var clientId: String? = null
    var clientName: String? = null
    var clientSecret: String? = null
    var clientSecretExpiresAt = 0
    var clientUri: String? = null
    var contacts: List<String>? = null
    var createdAt: Date? = null
    var frontchannelLogoutSessionRequired = false
    var frontchannelLogoutUri: String? = null
    var grantTypes: List<String>? = null
    var jwks: Jwks? = null
    var jwksUri: String? = null
    var logoUri: String? = null
    var metadata: Metadata? = null
    var owner: String? = null
    var policyUri: String? = null
    var postLogoutRedirectUris: List<String>? = null
    var redirectUris: List<String>? = null
    var requestObjectSigningAlg: String? = null
    var requestUris: List<String>? = null
    var responseTypes: List<String>? = null
    var scope: String? = null
    var sectorIdentifierUri: String? = null
    var subjectType: String? = null
    var tokenEndpointAuthMethod: String? = null
    var tokenEndpointAuthSigningAlg: String? = null
    var tosUri: String? = null
    var updatedAt: Date? = null
    var userinfoSignedResponseAlg: String? = null

    override fun toString(): String {
        return "Client(allowedCorsOrigins=$allowedCorsOrigins, audience=$audience, backchannelLogoutSessionRequired=$backchannelLogoutSessionRequired, backchannelLogoutUri=$backchannelLogoutUri, clientId=$clientId, clientName=$clientName, clientSecret=$clientSecret, clientSecretExpiresAt=$clientSecretExpiresAt, clientUri=$clientUri, contacts=$contacts, createdAt=$createdAt, frontchannelLogoutSessionRequired=$frontchannelLogoutSessionRequired, frontchannelLogoutUri=$frontchannelLogoutUri, grantTypes=$grantTypes, jwks=$jwks, jwksUri=$jwksUri, logoUri=$logoUri, metadata=$metadata, owner=$owner, policyUri=$policyUri, postLogoutRedirectUris=$postLogoutRedirectUris, redirectUris=$redirectUris, requestObjectSigningAlg=$requestObjectSigningAlg, requestUris=$requestUris, responseTypes=$responseTypes, scope=$scope, sectorIdentifierUri=$sectorIdentifierUri, subjectType=$subjectType, tokenEndpointAuthMethod=$tokenEndpointAuthMethod, tokenEndpointAuthSigningAlg=$tokenEndpointAuthSigningAlg, tosUri=$tosUri, updatedAt=$updatedAt, userinfoSignedResponseAlg=$userinfoSignedResponseAlg)"
    }
}

class Context

class IdTokenHintClaims

class OidcContext {
    var acrValues: List<String>? = null
    var display: String? = null
    var idTokenHintClaims: IdTokenHintClaims? = null
    var loginHint: String? = null
    var uiLocales: List<String>? = null
}
