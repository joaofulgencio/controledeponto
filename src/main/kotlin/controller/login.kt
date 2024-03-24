package controller

import controller.request.LoginRequest
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

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