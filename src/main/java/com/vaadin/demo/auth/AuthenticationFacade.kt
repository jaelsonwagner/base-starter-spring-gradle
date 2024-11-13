package com.vaadin.demo.auth

import org.springframework.security.core.Authentication

interface AuthenticationFacade {
    val authentication: Authentication
}