package com.vaadin.demo.oauth.ui

import com.vaadin.flow.component.html.Paragraph
import com.vaadin.flow.component.orderedlayout.FlexComponent
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route
import com.vaadin.flow.server.auth.AnonymousAllowed

@Route("/generic-error")
@PageTitle("Error")
@AnonymousAllowed
class ErrorView : VerticalLayout() {

    init {
        alignItems = FlexComponent.Alignment.CENTER
        add(Paragraph("Sample error page!"))
    }
}