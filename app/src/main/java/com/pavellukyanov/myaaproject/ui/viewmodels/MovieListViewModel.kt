package com.pavellukyanov.myaaproject.ui.viewmodels

import android.content.Context
import androidx.lifecycle.*
import com.pavellukyanov.myaaproject.data.repository.DataBaseRepoInterface
import com.pavellukyanov.myaaproject.data.repository.NetworkRepoInterface
import com.pavellukyanov.myaaproject.utils.MovieCategory
import com.pavellukyanov.myaaproject.utils.Resource
import kotlinx.coroutines.*
import java.lang.Exception

class MovieListViewModel(
    private val networkRepoInterface: NetworkRepoInterface
) : ViewModel() {

    fun getMovieList(methodName: MovieCategory) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = networkRepoInterface.getMoviesWithGenres(methodName)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: ERROR_MESSAGE))
        }
    }

    companion object {
        private const val ERROR_MESSAGE = "Error Occurred!"
    }
}