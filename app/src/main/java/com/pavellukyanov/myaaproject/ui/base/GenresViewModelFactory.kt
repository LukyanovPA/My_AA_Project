package com.pavellukyanov.myaaproject.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pavellukyanov.myaaproject.data.api.MoviesRemoteRepo
import com.pavellukyanov.myaaproject.data.repository.MainRepositoryImpl
import com.pavellukyanov.myaaproject.ui.viewmodels.GenresViewModel
import com.pavellukyanov.myaaproject.ui.viewmodels.MovieListViewModel
import java.lang.IllegalArgumentException

class GenresViewModelFactory(
    private val moviesRemoteRepo: MoviesRemoteRepo
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(GenresViewModel::class.java)) {
            return GenresViewModel(MainRepositoryImpl(moviesRemoteRepo)) as T
        }
        throw IllegalArgumentException("Неизвестное имя класса")
    }
}