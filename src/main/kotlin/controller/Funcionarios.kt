package controller

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import usecase.BuscarFuncionariosUseCase

fun Route.funcionariosRoutes(buscarFuncionariosUseCase: BuscarFuncionariosUseCase) {

    get("/funcionarios") {
        val response = buscarFuncionariosUseCase.execute()
            call.respond(HttpStatusCode.OK, response)
    }
}