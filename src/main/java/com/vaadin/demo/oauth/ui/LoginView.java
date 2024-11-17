package com.vaadin.demo.oauth.ui;


import com.vaadin.demo.oauth.data.UserSession;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

/**
 * Adds a link that the user has to click to login.
 *
 * This view is marked with {@code @AnonymousAllowed} to allow all users access
 * to the login page.
 */
@Route("/login")
@PageTitle("Login")
@AnonymousAllowed
public class LoginView extends VerticalLayout {

    /**
     * URL that Spring uses to connect to Google services
     */
    private static final String OAUTH_URL = "/oauth2/authorization/google";

    public LoginView(@Autowired Environment env, @Autowired UserSession userSession) {
        setAlignItems(Alignment.CENTER);

        String clientKey = env.getProperty("spring.security.oauth2.client.registration.google.client-id");

        // To give a better demo experience, check that oauth keys are present
        if (clientKey == null || clientKey.isBlank() || clientKey.length() < 32) {
            add(new Paragraph("Could not find OAuth client key in application.properties. "
                    + "Please double-check the key and refer to the README.md file for instructions."));
        } else {
            add(new Paragraph("LoggedIn: " + userSession.isLoggedIn()));
            add(new H1("Login to access this app"));
            add(new Paragraph("This is demo app for Spring Security + Google, thus there is only one option to log in:"));

            // Create an image element
            Button loginButton = createGoogleLoginButton();
            add(loginButton);
        }
    }

    @NotNull
    private Button createGoogleLoginButton() {
        String googleSignInImg = "public/btn_google_signin.png";

        Image googleLogo = new Image(googleSignInImg, "Google Sign in");

        // The button text will be provided by the image
        Button loginButton = new Button("", googleLogo);
        // Add a click listener to redirect to the Google OAuth URL
        loginButton.addClickListener(event -> {
            // Redirect to the Google OAuth login page
            getUI().ifPresent(ui -> ui.getPage().setLocation(OAUTH_URL));
        });

        loginButton.addClassName("background-color-transparent");  // Add custom class for styling
        return loginButton;
    }
}
