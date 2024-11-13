package com.vaadin.demo.oauth.ui

import com.vaadin.demo.oauth.data.UserSession
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.html.H1
import com.vaadin.flow.component.html.Image
import com.vaadin.flow.component.html.Paragraph
import com.vaadin.flow.component.orderedlayout.FlexComponent
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route
import jakarta.annotation.security.PermitAll

/**
 * Application main class that is hidden to user before authentication.
 *
 * The class is annotated with `@PermitAll` to allow only authenticated
 * users to view the class.
 */
@Route("")
@PermitAll
class MainView(userSession: UserSession) : VerticalLayout() {
    init {
        val user = userSession.user
        add(Paragraph("Logged: ${userSession.isLoggedIn}"))
        add(H1("Hello ${user.firstName}!"))
        add(Paragraph("Your email is ${user.email}"))

        val image = Image(user.picture, "User Image")
        image.addClassName("rounded-image")
        add(image)

        val logoutButton = Button("Logout") { _ -> userSession.logout() }
        add(logoutButton)

        alignItems = FlexComponent.Alignment.CENTER
    }
}
