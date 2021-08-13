package work.inabajun.springsecurityhydra

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

import java.lang.Exception

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.oauth2.server.resource.introspection.NimbusOpaqueTokenIntrospector
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector
import org.springframework.web.client.RestTemplate
import work.inabajun.springsecurityhydra.hydra.HydraAuthenticationSuccessHandler

@EnableWebSecurity
@Configuration
class WebSecurityConfig(private val restTemplate : RestTemplate) : WebSecurityConfigurerAdapter() {

    @Value("\${security.oauth2.resourceserver.opaque-token.introspection-uri}")
    var introspectionUri: String? = null

    @Value("\${security.oauth2.resourceserver.opaque-token.client-id}")
    var clientId: String? = null

    @Value("\${security.oauth2.resourceserver.opaque-token.client-secret}")
    var clientSecret: String? = null

    @Throws(Exception::class)  // @formatter:off
    override fun configure(http: HttpSecurity) {

        http.authorizeRequests { requests ->
            requests
                    .antMatchers(HttpMethod.GET, "/")
                    .authenticated()
                    .antMatchers("/consent")
                    .authenticated()
                    .antMatchers(HttpMethod.GET, "/login")
                    .authenticated()
        }.formLogin()
                .successHandler(HydraAuthenticationSuccessHandler(restTemplate))
                .loginPage("/login")
                .permitAll()

        http.authorizeRequests { requests ->
            requests
                    .antMatchers("/api/**").hasAnyAuthority("SCOPE_api")
        }.oauth2ResourceServer { oauth2 -> oauth2.opaqueToken() }
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication()
                .withUser("user1").password("{noop}password").roles("USER")
                .and()
                .withUser("user2").password("{noop}password").roles("USER")
    }

    @Bean
    fun introspector(): OpaqueTokenIntrospector {
        return NimbusOpaqueTokenIntrospector(introspectionUri, clientId, clientSecret)
    }
}
