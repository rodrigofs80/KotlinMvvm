package br.com.projkotlinexamples.http

import br.com.projkotlinexamples.ui.model.Materia

class ItemRepository(private val freeApiDataSource: ItemDataSource):  ItemDataSource {

    override fun listAllObjects(success: (List<Materia>) -> Unit, failure: () -> Unit) {
        freeApiDataSource.listAllObjects(success, failure)
    }

    override suspend fun listAllObjectsCoroutines(): List<Materia> {
        return freeApiDataSource.listAllObjectsCoroutines()
    }
}