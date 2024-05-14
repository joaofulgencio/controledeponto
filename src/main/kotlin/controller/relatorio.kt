package controller

import controller.request.LoginRequest
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import usecase.LoginUseCase
import usecase.RelatorioUseCase

fun Route.relatorioRoutes(relatorioUseCase: RelatorioUseCase) {

    get("/relatorio/{email}") {
        val email = call.parameters["email"] ?: return@get
        val response = relatorioUseCase.execute(email)
            call.respond(HttpStatusCode.OK, response)
    }
}