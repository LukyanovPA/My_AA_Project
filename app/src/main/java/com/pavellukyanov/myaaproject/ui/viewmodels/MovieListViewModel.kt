package com.pavellukyanov.myaaproject.ui.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.pavellukyanov.myaaproject.data.models.*
import com.pavellukyanov.myaaproject.data.repository.RepositoryInterface
import kotlinx.coroutines.*

class MovieListViewModel(
    private val repositoryInterface: RepositoryInterface
) : ViewModel() {
    private val handler = CoroutineExceptionHandler(handler = { _, error ->
        Log.d(LOG_TAG, "${error.message}")
    })
    private var _movieList: MutableLiveData<List<Movie>> = MutableLiveData()
    private val movieList: LiveData<List<Movie>> get() = _movieList
    private var _runtimeForIds: MutableLiveData<MutableMap<Int, Int>> = MutableLiveData()
    private var runtimeMap: MutableMap<Int, Int> = mutableMapOf()

    private fun selectMovieList() {
        viewModelScope.launch(Dispatchers.IO + handler) {
//            _movieList.postValue(repositoryInterface.getPopularMovies())
            _movieList.postValue(repositoryInterface.getNowPlaying())
        }
    }

    private fun selectRuntime(list: List<Movie>) {
        viewModelScope.launch(Dispatchers.IO + handler) {
            val tempListMovieWithRuntime: MutableList<Movie> = mutableListOf()
            for (movie in list) {
                movie.runtime = repositoryInterface.getMovieId(movie.id)
                Log.d("MoTi", "selectRuntime - ${movie.runtime}")
                tempListMovieWithRuntime.add(movie)
            }
            Log.d("MoTi", "selectRuntime - ${tempListMovieWithRuntime.size}")
            _movieList.postValue(tempListMovieWithRuntime)
        }


//        _movieList.observeForever { listMovie ->
//            listMovie?.let {
//                viewModelScope.launch(Dispatchers.IO + handler) {
//                    listMovie.forEach {
//                        val runtime: Int = repositoryInterface.getMovieId(it.id)
//                        runtimeMap.put(it.id, runtime)
//                    }
//                    _movieList.value?.get(0)?.runtime = 1
//                }
//            }
//        }
    }

    fun getMovie(): LiveData<List<Movie>> {
        selectMovieList()
        return movieList
    }

    companion object {
        private const val LOG_TAG = "MovieListViewModel"
    }
}