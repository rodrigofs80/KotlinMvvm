package br.com.projkotlinexamples.http.remote

import br.com.projkotlinexamples.http.ItemDataSource
import br.com.projkotlinexamples.ui.model.Item
import br.com.projkotlinexamples.ui.model.Materia
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await

class FreeApiDataSource(private val freeApi: FreeApi): ItemDataSource {

    override fun listAllObjects(success: (List<Materia>) -> Unit, failure: () -> Unit) {
        val call = freeApi.listObjects()
        call.enqueue(object : Callback<List<Item>>{
            override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) {
                if (response.isSuccessful) {
                    val itens = mutableListOf<List<Materia>>()
                    response.body()?.forEach {
                        itens.add(it.conteudos)
                    }
                    success(itens[0])
                } else {
                    failure()
                }
            }
            override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                failure()
            }
        })
    }

    override suspend fun listAllObjectsCoroutines(): List<Materia> {
        val call = freeApi.listObjects().await()
        return call.get(0).conteudos
    }
}