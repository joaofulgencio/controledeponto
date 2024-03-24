package controller

import controller.request.LoginRequest
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import usecase.LoginUseCase

fun Route.loginRoutes(loginUseCase: LoginUseCase) {

    post("/login") {
        val loginBody = call.receive<LoginRequest>()
        val response = loginUseCase.execute(loginBody.matricula, loginBody.senha)
        if(response.success) {
            call.respond(HttpStatusCode.OK, response)
        } else {
            call.respond(HttpStatusCode.Unauthorized, response)
        }
    }
}