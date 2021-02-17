package com.pavellukyanov.myaaproject.ui.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.pavellukyanov.myaaproject.data.models.*
import com.pavellukyanov.myaaproject.data.repository.RepositoryInterface
import com.pavellukyanov.myaaproject.utils.MovieCategory
import kotlinx.coroutines.*

class MovieListViewModel(
    private val repositoryInterface: RepositoryInterface
) : ViewModel() {
    private val handler = CoroutineExceptionHandler(handler = { _, error ->
        Log.d(LOG_TAG, "${error.message}")
    })
    private var _movieList: MutableLiveData<List<Movie>> = MutableLiveData()
    private val movieList: LiveData<List<Movie>> get() = _movieList

    private fun selectMovieList(methodName: MovieCategory) {
        viewModelScope.launch(Dispatchers.IO + handler) {
            _movieList.postValue(repositoryInterface.getMoviesWithGenres(methodName))
        }
    }

    fun getMovie(methodName: MovieCategory): LiveData<List<Movie>> {
        selectMovieList(methodName)
        return movieList
    }

    companion object {
        private const val LOG_TAG = "MovieListViewModel"
    }
}