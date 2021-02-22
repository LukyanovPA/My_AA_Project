package com.pavellukyanov.myaaproject.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pavellukyanov.myaaproject.data.api.MoviesRemoteRepo
import com.pavellukyanov.myaaproject.data.repository.DataBaseRepoImpl
import com.pavellukyanov.myaaproject.data.repository.DataBaseRepoInterface
import com.pavellukyanov.myaaproject.data.repository.NetworkRepoImpl
import com.pavellukyanov.myaaproject.ui.viewmodels.MovieListViewModel
import java.lang.IllegalArgumentException

class MovieListViewModelFactory(
    private val moviesRemoteRepo: MoviesRemoteRepo
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MovieListViewModel::class.java)) {
            return MovieListViewModel(NetworkRepoImpl(moviesRemoteRepo)) as T
        }
        throw IllegalArgumentException("Неизвестное имя класса")
    }
}