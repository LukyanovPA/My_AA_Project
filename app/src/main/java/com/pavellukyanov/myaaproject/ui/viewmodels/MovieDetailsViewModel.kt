package com.pavellukyanov.myaaproject.ui.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.pavellukyanov.myaaproject.data.models.Credits
import com.pavellukyanov.myaaproject.data.models.MovieDetails
import com.pavellukyanov.myaaproject.data.repository.RepositoryInterface
import com.pavellukyanov.myaaproject.utils.Resource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MovieDetailsViewModel(
    private val repositoryInterface: RepositoryInterface
) : ViewModel() {

    fun getMovieCredits(movieId: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repositoryInterface.getCreditsWithPosterUrl(movieId)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: ERROR_MESSAGE))
        }
    }

    companion object {
        private const val ERROR_MESSAGE = "Error Occurred!"
    }
}