package com.candra.projectcapstone.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.candra.projectcapstone.helper.State
import com.candra.projectcapstone.model.ListMusicModel
import com.candra.projectcapstone.repo.ListMusicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListMusicViewModel @Inject constructor(
    private val repository: ListMusicRepository
): ViewModel() {

    private var _isLoading = MutableLiveData<Boolean>()
    val loading get() = _isLoading

    private var _listMusicData = MutableLiveData<List<ListMusicModel>>()
    val listMusicData get() = _listMusicData

    fun getAllMusicList(category: String) = viewModelScope.launch {
         repository.getAllMusicList(category).collect {
             when(it){
                 is State.Loading -> { _isLoading.value = true }
                 is State.Success -> {
                     _isLoading.value = false
                     it.data.let { listMusic -> _listMusicData.value = listMusic }
                 }
                 is State.Failed -> {
                     _isLoading.value = false
                 }
             }
         }
    }
}