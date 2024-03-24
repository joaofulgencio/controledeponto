package config

import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

fun Application.module() {
    install(ContentNegotiation) {
        gson {
        }
    }
}