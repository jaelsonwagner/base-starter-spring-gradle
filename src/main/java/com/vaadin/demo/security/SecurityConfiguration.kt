package com.vaadin.demo.security

import com.vaadin.demo.oauth.ui.LoginView
import com.vaadin.flow.spring.security.VaadinWebSecurity
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity


/**
 * Based on the following samples:
 * https://vaadin.com/forum/t/help-with-implementing-login-and-logout/166979
 * https://vaadin.com/docs/latest/flow/security/enabling-security#security-configuration-class
 */
@EnableWebSecurity
@Configuration
open class SecurityConfiguration : VaadinWebSecurity() {

    override fun configure(http: HttpSecurity) {
        // Authorize http requests for pages like the ones annotated with @AnonymousAllowed
        // See the implementation for more details.
        super.configure(http)

        // Authorize oauth login
        http.oauth2Login { oauth2LoginCustomizer ->
            oauth2LoginCustomizer.loginPage("/login")
            oauth2LoginCustomizer.defaultSuccessUrl("/", true)
        }

        // This is important to register your login view to the
        // navigation access control mechanism:
        setLoginView(http, LoginView::class.java, "/")
    }
}