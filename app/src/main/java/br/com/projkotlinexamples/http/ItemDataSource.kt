package br.com.projkotlinexamples.http

import br.com.projkotlinexamples.ui.model.Materia

interface ItemDataSource {

    fun listAllObjects(success : (List<Materia>) -> Unit, failure: () -> Unit)

    suspend fun listAllObjectsCoroutines() : List<Materia>
}