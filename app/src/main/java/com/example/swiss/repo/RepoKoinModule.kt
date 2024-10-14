package com.example.swiss.repo

import org.koin.dsl.module


val repoKoinModule = module {
    factory { UserRepo(get()) }
}