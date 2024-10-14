package com.example.swiss.ui.di

import com.example.swiss.ui.vm.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelKoinModule = module {
    viewModel { ViewModel(get()) }
}