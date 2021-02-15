package com.pavellukyanov.myaaproject.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pavellukyanov.myaaproject.data.models.Genres
import com.pavellukyanov.myaaproject.data.models.Images
import com.pavellukyanov.myaaproject.data.repository.RepositoryInterface
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val repositoryInterface: RepositoryInterface
) : ViewModel() {
    private val handler = CoroutineExceptionHandler(handler = { _, error ->
        Log.d(LOG_TAG, "${error.message}")
    })

    private val _imageConfig: MutableLiveData<Images> = MutableLiveData()

    fun loadConfig() {
        viewModelScope.launch(Dispatchers.IO + handler) {
            _imageConfig.postValue(repositoryInterface.getConfiguration())
        }
    }

    fun getConfig(): LiveData<Images> = _imageConfig

    companion object {
        private const val LOG_TAG = "MainActivityViewModel"
    }
}