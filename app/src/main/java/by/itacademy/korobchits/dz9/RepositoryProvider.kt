package by.itacademy.korobchits.dz9

import by.itacademy.korobchits.dz12.StudentRepository
import by.itacademy.korobchits.dz12.StudentRepositoryRemote

fun provideCarRepository(): CarRepository {

    return CarRepositoryRemote(
        NetProvider.provideApi(
            NetProvider.provideRetrofit(
                "http://kiparo.ru/",
                NetProvider.provideOkHttp(),
                NetProvider.provideGson()
            )
        )
    )
}

fun provideStudentRepository(): StudentRepository {
    return StudentRepositoryRemote(
        NetProvider.provideStudentApi(
            NetProvider.provideRetrofit(
                "https://api.backendless.com/3CE79BD2-2E4D-B5B6-FFAE-F3893EAB3600/34BCE78F-8F98-3421-FF73-3B4EEB276F00/",
                NetProvider.provideOkHttp(),
                NetProvider.provideGsonExpose()
            )
        )
    )
}