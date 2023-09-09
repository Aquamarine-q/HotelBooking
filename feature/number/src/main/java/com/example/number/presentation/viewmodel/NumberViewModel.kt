package com.example.number.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.number.data.repository.NumberRepository
import com.example.number.data.model.Rooms
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

class NumberViewModel
@Inject constructor(private val repository: NumberRepository) : ViewModel() {

    private val _viewState = MutableLiveData(Rooms(emptyList()))
    val viewState: LiveData<Rooms> = _viewState

    fun onScreenOpened() {
        viewModelScope.launch {
            try {
                _viewState.postValue(repository.getRooms())
            } catch (exception: IOException) {

            }
        }
    }
}