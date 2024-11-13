package com.vaadin.demo.oauth.data

import com.vaadin.demo.auth.AuthenticationFacade
import com.vaadin.flow.component.UI
import com.vaadin.flow.server.VaadinServletRequest
import com.vaadin.flow.spring.annotation.VaadinSessionScope
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler
import org.springframework.stereotype.Component
import java.io.Serializable

@Component
@VaadinSessionScope
class UserSession(private val authenticationFacade: AuthenticationFacade) : Serializable {
    val user: User
        get() {
            val authentication = authenticationFacade.authentication
            val principal = authentication.principal as OAuth2AuthenticatedPrincipal

            return with(principal) {
                User(
                    firstName = getAttribute("given_name"),
                    lastName = getAttribute("family_name"),
                    email = getAttribute("email"),
                    picture = getAttribute("picture")
                )
            }
        }

    // TODO: A better solution needs to be implemented.
    val isLoggedIn: Boolean
        get() {
            return authenticationFacade.authentication.principal != "anonymousUser"
        }

    fun logout() {
        UI.getCurrent().page.setLocation(LOGOUT_SUCCESS_URL)
        SecurityContextLogoutHandler().logout(
            VaadinServletRequest.getCurrent().httpServletRequest,
            null,
            null
        )
    }

    companion object {
        private const val LOGOUT_SUCCESS_URL = "/login"
    }
}
