package di

import config.firebase.firebaseInitializer
import database.repository.*
import database.repository.flow.FireStoreFlowFuncionariosRepository
import database.repository.flow.FuncionariosFlowRepository
import usecase.*
import usecase.flow.BuscarFuncionariosFlowUseCase

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

    val editarPontoRepository: EditarPontoRepository by lazy {
        FireStoreEditarPontoRepository(fireStore)
    }

    val relatorioRepository: RelatorioRepository by lazy {
        FireStoreRelatorioRepository(fireStore)
    }

    val funcionariosRepository: FuncionariosRepository by lazy {
        FireStoreFuncionariosRepository(fireStore)
    }

    val funcionariosFlowRepository: FuncionariosFlowRepository by lazy {
        FireStoreFlowFuncionariosRepository(fireStore)
    }

    val loginReactiveRepository: LoginReactiveRepository by lazy {
        FireStoreLoginReactiveRepository(fireStore)
    }

    val loginUseCase: LoginUseCase by lazy {
        LoginUseCase(loginRepository)
    }

    val loginReactiveUseCase: LoginReactiveUseCase by lazy {
        LoginReactiveUseCase(loginReactiveRepository)
    }

    val cadastrarUseCase: CadastrarUseCase by lazy {
        CadastrarUseCase(cadastrarRepository)
    }

    val baterPontoUseCase: BaterPontoUseCase by lazy {
        BaterPontoUseCase(baterPontoRepository)
    }

    val editarPontoUseCase: EditarPontoUseCase by lazy {
        EditarPontoUseCase(editarPontoRepository)
    }

    val relatorioUseCase: RelatorioUseCase by lazy {
        RelatorioUseCase(relatorioRepository)
    }

    val buscarFuncionariosUseCase: BuscarFuncionariosUseCase by lazy {
        BuscarFuncionariosUseCase(funcionariosRepository)
    }

    val buscarFuncionariosFlowUseCase: BuscarFuncionariosFlowUseCase by lazy {
        BuscarFuncionariosFlowUseCase(funcionariosFlowRepository)
    }

}