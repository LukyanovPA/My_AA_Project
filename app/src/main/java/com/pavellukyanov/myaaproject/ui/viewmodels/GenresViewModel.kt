package com.pavellukyanov.myaaproject.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pavellukyanov.myaaproject.data.models.Genres
import com.pavellukyanov.myaaproject.data.repository.RepositoryInterface
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GenresViewModel(
    private val repositoryInterface: RepositoryInterface
) : ViewModel() {
    private val handler = CoroutineExceptionHandler(handler = { _, error ->
        Log.d(LOG_TAG, "${error.message}")
    })
    private var _listGenres: MutableLiveData<MutableMap<Int, String>> = MutableLiveData()
    private var genresMap: MutableMap<Int, String> = mutableMapOf()
    private val listGenres: LiveData<MutableMap<Int, String>> get() = _listGenres

    private fun selectGenres() {
        viewModelScope.launch(Dispatchers.IO + handler) {
            val genresTemp: List<Genres> = repositoryInterface.getAllGenres()
            genresTemp.forEach {
                genresMap.put(it.id, it.name)
            }
            _listGenres.postValue(genresMap)

            Log.d("GenResp", "Genres - ${genresTemp.size}")
            Log.d("GenResp", "GenresMap - ${genresMap.size}")
            Log.d("GenResp", "GenresLiveData - ${_listGenres.value?.size}")
        }
    }

    fun getGenres(): LiveData<MutableMap<Int, String>> {
        selectGenres()
        return listGenres
    }

    companion object {
        private const val LOG_TAG = "GenresViewModel"
    }
}