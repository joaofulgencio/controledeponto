package di

import config.firebase.firebaseInitializer
import database.repository.*
import usecase.BaterPontoUseCase
import usecase.CadastrarUseCase
import usecase.LoginUseCase
import usecase.RelatorioUseCase

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

}