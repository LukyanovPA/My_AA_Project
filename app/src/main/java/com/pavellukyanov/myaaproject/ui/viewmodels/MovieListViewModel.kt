package com.pavellukyanov.myaaproject.ui.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.pavellukyanov.myaaproject.data.models.PopularMovieResponse
import com.pavellukyanov.myaaproject.data.repository.MainRepositoryImpl
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieListViewModel(private val mainRepositoryImpl: MainRepositoryImpl): ViewModel() {
    private val handler = CoroutineExceptionHandler(handler = { _, error ->
        Log.d(LOG_TAG, "Something went wrong")
    })
    private var _popularMovies: MutableLiveData<PopularMovieResponse> = MutableLiveData()
    val popularMovies: MutableLiveData<PopularMovieResponse> get() = _popularMovies

    init {
        viewModelScope.launch(Dispatchers.IO + handler) {
            popularMovies.postValue(mainRepositoryImpl.getPopularMovies())
        }
    }

    fun getMovies() = popularMovies

    companion object {
       private const val LOG_TAG = "MovieListViewModel"
   }
}