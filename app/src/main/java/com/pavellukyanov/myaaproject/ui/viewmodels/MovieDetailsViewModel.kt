package com.pavellukyanov.myaaproject.ui.viewmodels

import androidx.lifecycle.*
import com.pavellukyanov.myaaproject.data.repository.NetworkRepoInterface
import com.pavellukyanov.myaaproject.utils.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class MovieDetailsViewModel(
    private val networkRepoInterface: NetworkRepoInterface
) : ViewModel() {

    fun getMovieCredits(movieId: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = networkRepoInterface.getCreditsWithPosterUrl(movieId)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: ERROR_MESSAGE))
        }
    }

    companion object {
        private const val ERROR_MESSAGE = "Error Occurred!"
    }
}