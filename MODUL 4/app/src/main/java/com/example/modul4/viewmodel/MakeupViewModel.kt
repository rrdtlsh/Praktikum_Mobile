package com.example.modul4.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.modul4.model.Makeup
import com.example.modul4.repository.MakeupRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

private const val TAG = "MakeupViewModel"

class MakeupViewModel(
    private val repository: MakeupRepository
) : ViewModel() {

    private val _makeupList = MutableStateFlow<List<Makeup>>(emptyList())
    val makeupList: StateFlow<List<Makeup>> = _makeupList

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow: SharedFlow<UiEvent> = _eventFlow.asSharedFlow()

    init {
        loadMakeup()
    }

    private fun loadMakeup() {
        val list = repository.getMakeupList()
        list.forEach { makeup ->
            Log.d(TAG, "Data masuk: ${makeup.name} (ID: ${makeup.id})")
        }
        _makeupList.value = list
    }

    fun onDetailClicked(id: String) {
        Log.d(TAG, "Navigasi ke detail: ID = $id")
        viewModelScope.launch {
            _eventFlow.emit(UiEvent.NavigateToDetail(id))
        }
    }

    fun onVisitClicked(url: String) {
        Log.d(TAG, "Tombol Kunjungi ditekan, URL = $url")
        viewModelScope.launch {
            _eventFlow.emit(UiEvent.OpenWebUrl(url))
        }
    }

    sealed class UiEvent {
        data class NavigateToDetail(val makeupId: String) : UiEvent()
        data class OpenWebUrl(val url: String) : UiEvent()
    }
}