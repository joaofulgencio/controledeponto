package config

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*

fun Application.module() {
    install(ContentNegotiation) {
        gson {
        }
    }
}