package com.pavellukyanov.myaaproject.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pavellukyanov.myaaproject.data.repository.MainRepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TestViewModel(
    private var mainRepositoryInterface: MainRepositoryInterface
): ViewModel() {

    fun testStart(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            mainRepositoryInterface.testBaza(id)
        }
    }
}