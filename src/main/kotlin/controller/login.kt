package controller

import controller.request.LoginRequest
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.loginRoutes() {
    get("/login") {
        val loginBody = call.receive<LoginRequest>()
        call.respondText(loginBody.toString())
    }

    post("/login") {
        val loginBody = call.receive<LoginRequest>()
        call.respondText(loginBody.toString())
    }
}