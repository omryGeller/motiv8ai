package com.safelevel.motiv8aiha.modules

import com.safelevel.motiv8aiha.repo.MainRepository
import com.safelevel.motiv8aiha.viewmodel.MainViewModel
import org.koin.dsl.module

val mainModule = module{
    factory { MainViewModel(get()) }
    single { MainRepository() }
}