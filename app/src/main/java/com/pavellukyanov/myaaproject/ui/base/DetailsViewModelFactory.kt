package com.pavellukyanov.myaaproject.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pavellukyanov.myaaproject.data.api.MoviesRemoteRepo
import com.pavellukyanov.myaaproject.data.repository.MainRepositoryImpl
import com.pavellukyanov.myaaproject.ui.viewmodels.MovieDetailsViewModel
import com.pavellukyanov.myaaproject.ui.viewmodels.MovieListViewModel
import java.lang.IllegalArgumentException

class DetailsViewModelFactory(
    private val moviesRemoteRepo: MoviesRemoteRepo
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MovieDetailsViewModel::class.java)) {
            return MovieDetailsViewModel(MainRepositoryImpl(moviesRemoteRepo)) as T
        }
        throw IllegalArgumentException("Неизвестное имя класса")
    }
}