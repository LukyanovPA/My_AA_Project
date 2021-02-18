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
    private val handler = CoroutineExceptionHandler(handler = { _, error ->
        Log.d(LOG_TAG, "${error.message}")
    })
    private val _movieDetailsMutLiveData: MutableLiveData<MovieDetails> = MutableLiveData()
    private val _creditsMutLiveData: MutableLiveData<Credits> = MutableLiveData()
    private val movieDetailsLiveData: LiveData<MovieDetails> get() = _movieDetailsMutLiveData
    private val creditsLiveData: LiveData<Credits> get() = _creditsMutLiveData

    fun selectMovieDetails(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO + handler) {
            _movieDetailsMutLiveData.postValue(repositoryInterface.getMovieDetailsWithGenres(movieId))
        }
    }

    fun getMovieDetailsTest(movieId: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repositoryInterface.getMovieDetailsWithGenres(movieId)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getMovieDetails(): LiveData<MovieDetails> = movieDetailsLiveData

    fun selectCredits(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO + handler) {
            _creditsMutLiveData.postValue(repositoryInterface.getCreditsWithPosterUrl(movieId))
        }
    }

    fun getCredits(): LiveData<Credits> = creditsLiveData

    companion object {
        private const val LOG_TAG = "MovieDetailsViewModel"
    }
}