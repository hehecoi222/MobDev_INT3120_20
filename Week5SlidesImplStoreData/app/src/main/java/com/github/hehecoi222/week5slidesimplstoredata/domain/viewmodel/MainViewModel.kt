package com.github.hehecoi222.week5slidesimplstoredata.domain.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.github.hehecoi222.week5slidesimplstoredata.MainApplication
import com.github.hehecoi222.week5slidesimplstoredata.domain.entities.SimpleEntity
import com.github.hehecoi222.week5slidesimplstoredata.domain.repository.FileRepository
import com.github.hehecoi222.week5slidesimplstoredata.domain.repository.KeyValueStoreRepository
import com.github.hehecoi222.week5slidesimplstoredata.domain.repository.RoomAccessRepository
import com.github.hehecoi222.week5slidesimplstoredata.domain.state.MainState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val keyValueStoreRepository: KeyValueStoreRepository,
    private val fileRepository: FileRepository,
    private val roomRepository: RoomAccessRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        MainState(
            null, null, null
        )
    )
    val uiState = _uiState.asStateFlow()

    var userInputKey by mutableStateOf("")
    var userInputValue by mutableStateOf("")

    suspend fun onSubmitDS() {
        keyValueStoreRepository.put(userInputKey, userInputValue)
    }

    suspend fun onLoadTabDS() {
        _uiState.value = _uiState.value.copy(allKeyDS = keyValueStoreRepository.getAll())
    }

    suspend fun onRemoveDS(key: String) {
        if (key == "null" || key == "") return
        keyValueStoreRepository.delete(key)
    }

    suspend fun onSubmitFile() {
        fileRepository.saveData(userInputKey, userInputValue)
    }

    suspend fun onLoadTabFile() {
        _uiState.value = _uiState.value.copy(allKeyFile = fileRepository.loadAll())
    }

    suspend fun onRemoveFile(key: String) {
        if (key == "null" || key == "") return
        fileRepository.remove(key)
    }

    fun onSubmitRoom(simpleEntity: SimpleEntity){
        viewModelScope.launch {
            roomRepository.insert(simpleEntity)
        }
    }

    fun onLoadTabRoom(){
        viewModelScope.launch {
            roomRepository.allKeys.observeForever {
                _uiState.value = _uiState.value.copy(allKeyRoom = it)
            }
        }
    }

    fun onRemoveRoom(simpleEntity: SimpleEntity){
        viewModelScope.launch {
            roomRepository.delete(simpleEntity)
        }
    }

    init {
        viewModelScope.launch {
            _uiState.value = MainState(
                keyValueStoreRepository.getAll(),
                fileRepository.loadAll(),
                null
            )
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MainApplication)
                val keyValueStoreRepository = application.container.keyValueStoreRepository
                val fileRepository = application.container.fileRepository
                val roomRepository = application.container.roomRepository
                MainViewModel(keyValueStoreRepository, fileRepository, roomRepository)
            }
        }
    }
}
