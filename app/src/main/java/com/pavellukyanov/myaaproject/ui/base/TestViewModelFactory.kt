package com.pavellukyanov.myaaproject.ui.base

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pavellukyanov.myaaproject.data.api.MoviesRemoteRepo
import com.pavellukyanov.myaaproject.data.database.MovieDataBase
import com.pavellukyanov.myaaproject.data.repository.DataBaseRepoImpl
import com.pavellukyanov.myaaproject.data.repository.MainRepositoryImpl
import com.pavellukyanov.myaaproject.data.repository.NetworkRepoImpl
import com.pavellukyanov.myaaproject.ui.viewmodels.MovieListViewModel
import com.pavellukyanov.myaaproject.ui.viewmodels.TestViewModel
import java.lang.IllegalArgumentException

class TestViewModelFactory(
    private val moviesRemoteRepo: MoviesRemoteRepo,
    private val dataBaseRepoImpl: DataBaseRepoImpl
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TestViewModel::class.java)) {
            return TestViewModel(MainRepositoryImpl(NetworkRepoImpl(moviesRemoteRepo), dataBaseRepoImpl)) as T
        }
        throw IllegalArgumentException("Неизвестное имя класса")
    }
}