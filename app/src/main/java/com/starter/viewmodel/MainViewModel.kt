package com.starter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.starter.model.ResponseState
import com.starter.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(var repository: AppRepository) : ViewModel() {
    private val _response = MutableLiveData<ResponseState<String>>()
    val response : LiveData<ResponseState<String>> = _response
    fun getList(){
        viewModelScope.launch {
            _response.postValue(ResponseState.Loading(true))
            val response = repository.getList()
            if (res)
            _response.postValue(ResponseState.Loading(true))
            _response.postValue(ResponseState.Loading(true))


        }

    }
}