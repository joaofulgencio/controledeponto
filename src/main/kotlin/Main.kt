import controller.loginRoutes
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import config.gsonModuleConfig
import controller.baterPontoRoutes
import controller.cadastroRoutes
import controller.relatorioRoutes
import di.DIContainer
import io.ktor.server.application.*
import io.ktor.server.routing.routing


fun main() {

        val di = DIContainer()
        val server = embeddedServer(Netty, port = 8080) {
            moduleAndRouting(di)
        }
        server.start(wait = true)
    }

    private fun Application.moduleAndRouting(di: DIContainer) {
        gsonModuleConfig()

        routing {
            loginRoutes(di.loginUseCase)
            cadastroRoutes(di.cadastrarUseCase)
            cadastroRoutes(di.cadastrarUseCase)
            baterPontoRoutes(di.baterPontoUseCase)
            relatorioRoutes(di.relatorioUseCase)
        }
    }

