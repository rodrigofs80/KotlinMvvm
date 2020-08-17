package br.com.projkotlinexamples.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import br.com.projkotlinexamples.R
import br.com.projkotlinexamples.http.ItemDataSource
import br.com.projkotlinexamples.ui.model.Materia
import kotlinx.coroutines.*
import java.lang.Exception

class MainViewModel(private val repository: ItemDataSource, private val application: Application) : ViewModel(), LifecycleObserver {

    var lista = MutableLiveData<List<Materia>>()
    val showLoading = MutableLiveData(false)
    val msg = MutableLiveData<String>()

    fun toLoad() {
        showLoading.value = true
        repository.listAllObjects({ list ->
            lista.postValue(list)
            if(list.isEmpty()){
                msg.value = application.getString(R.string.empty)
                showLoading.value = false
            } else {
                showLoading.value = false
            }
        },{
            msg.value = application.getString(R.string.failed)
            showLoading.value = false
        })
    }

    fun toLoadCoroutines() {
        viewModelScope.launch {
            showLoading.value = true
            try {
                lista.postValue(repository.listAllObjectsCoroutines())
            } catch (e: Exception){
                msg.value = application.getString(R.string.failed)
            }
            showLoading.value = false
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        //toLoad()
        toLoadCoroutines()
    }
}