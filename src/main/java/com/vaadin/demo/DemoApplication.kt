package com.vaadin.demo

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class DemoApplication

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args) {
        setBannerMode(Banner.Mode.CONSOLE) // OFF to disable banner printing in console at app bootstrap.
    }
}
