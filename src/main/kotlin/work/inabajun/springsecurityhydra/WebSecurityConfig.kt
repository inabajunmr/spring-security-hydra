package work.inabajun.springsecurityhydra

import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

import java.lang.Exception

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.web.client.RestTemplate
import work.inabajun.springsecurityhydra.hydra.HydraAuthenticationSuccessHandler

@EnableWebSecurity
class WebSecurityConfig(private val restTemplate : RestTemplate) : WebSecurityConfigurerAdapter() {

    @Throws(Exception::class)  // @formatter:off
    override fun configure(http: HttpSecurity) {

        http.authorizeRequests { requests ->
            requests
                    .anyRequest().authenticated()
        }.formLogin()
                .successHandler(HydraAuthenticationSuccessHandler(restTemplate))
                .loginPage("/login")
                .permitAll()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication()
                .withUser("user1").password("{noop}password").roles("USER")
                .and()
                .withUser("user2").password("{noop}password").roles("USER")
    }
}