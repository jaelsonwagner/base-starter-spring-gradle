package com.vaadin.demo.security

import com.vaadin.demo.oauth.ui.LoginView
import com.vaadin.flow.spring.security.VaadinWebSecurity
import com.vaadin.flow.spring.security.WebIconsRequestMatcher
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.security.web.util.matcher.RequestMatcher
import org.springframework.security.web.util.matcher.RequestMatchers


/**
 * Based on the following samples:
 * https://vaadin.com/forum/t/help-with-implementing-login-and-logout/166979
 * https://vaadin.com/docs/latest/flow/security/enabling-security#security-configuration-class
 */
@EnableWebSecurity
@Configuration
open class SecurityConfiguration : VaadinWebSecurity() {

    override fun configure(http: HttpSecurity) {
        http.authorizeHttpRequests { urlRegistry ->

                // Vaadin internal requests must always be allowed to allow public
                // Flow pages and/or login page implemented using Flow.
                urlRegistry.requestMatchers(AntPathRequestMatcher("/public/**")).permitAll()
            }
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