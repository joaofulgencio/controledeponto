import config.firebase.firebaseInitializer
import controller.loginRoutes
    import io.ktor.server.engine.*
    import io.ktor.server.netty.*
    import config.gsonModuleConfig
import database.repository.FireStoreLoginRepository
import io.ktor.server.application.*
    import io.ktor.server.routing.routing
import usecase.LoginUseCase


fun main() {
        val fireStore = firebaseInitializer()
        val loginRepository = FireStoreLoginRepository(fireStore)
        val loginUseCase = LoginUseCase(loginRepository)
        val server = embeddedServer(Netty, port = 8080) {
            moduleAndRouting(loginUseCase)
        }
        server.start(wait = true)
    }

    private fun Application.moduleAndRouting(loginUseCase: LoginUseCase) {
        gsonModuleConfig()

        routing {
            loginRoutes(loginUseCase)
        }
    }