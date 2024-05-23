package di

import config.firebase.firebaseInitializer
import database.repository.*
import usecase.*

class DIContainer {
    private val fireStore = firebaseInitializer()

    val loginRepository: LoginRepository by lazy {
        FireStoreLoginRepository(fireStore)
    }

    val cadastrarRepository: CadastrarRepository by lazy {
        FireStoreCadastrarRepository(fireStore)
    }

    val baterPontoRepository: BaterPontoRepository by lazy {
        FireStoreBaterPontoRepository(fireStore)
    }

    val relatorioRepository: RelatorioRepository by lazy {
        FireStoreRelatorioRepository(fireStore)
    }

    val funcionariosRepository: FuncionariosRepository by lazy {
        FireStoreFuncionariosRepository(fireStore)
    }

    val loginUseCase: LoginUseCase by lazy {
        LoginUseCase(loginRepository)
    }

    val cadastrarUseCase: CadastrarUseCase by lazy {
        CadastrarUseCase(cadastrarRepository)
    }

    val baterPontoUseCase: BaterPontoUseCase by lazy {
        BaterPontoUseCase(baterPontoRepository)
    }

    val relatorioUseCase: RelatorioUseCase by lazy {
        RelatorioUseCase(relatorioRepository)
    }

    val buscarFuncionariosUseCase: BuscarFuncionariosUseCase by lazy {
        BuscarFuncionariosUseCase(funcionariosRepository)
    }

}