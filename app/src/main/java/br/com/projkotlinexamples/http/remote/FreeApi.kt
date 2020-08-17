package br.com.projkotlinexamples.http.remote

import br.com.projkotlinexamples.ui.model.Item
import retrofit2.Call
import retrofit2.http.GET

interface FreeApi {

    @GET("/Infoglobo/desafio-apps/master/capa.json")
    fun listObjects(): Call<List<Item>>
}