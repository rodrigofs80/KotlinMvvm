package br.com.projkotlinexamples.di

import br.com.projkotlinexamples.http.ItemDataSource
import br.com.projkotlinexamples.http.ItemRepository
import br.com.projkotlinexamples.http.remote.FreeApi
import br.com.projkotlinexamples.http.remote.FreeApiDataSource
import br.com.projkotlinexamples.vm.MainViewModel
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import org.koin.android.viewmodel.ext.koin.viewModel

val Module = module {
    single{ Retrofit.Builder().baseUrl("https://raw.githubusercontent.com/").addConverterFactory(GsonConverterFactory.create()).client(OkHttpClient.Builder().addNetworkInterceptor(StethoInterceptor()).build()).build()}
    single{ (get() as Retrofit).create(FreeApi::class.java) }
    single("api"){FreeApiDataSource(get())  as ItemDataSource }
    single("repository") { ItemRepository(get("api")) as ItemDataSource }
    viewModel { MainViewModel(get("repository"), androidApplication()) }
}

val myModule = listOf(Module)