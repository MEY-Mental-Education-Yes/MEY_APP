package com.candra.projectcapstone.model

import android.util.Log
import com.candra.projectcapstone.api.ApiInterface
import com.candra.projectcapstone.helper.Constant.TAG
import com.candra.projectcapstone.helper.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiInterface
) {
  suspend fun getAllListMusic(category: String) = flow<State<List<ListMusicModel>>> {
      emit(State.loading())
      val responseMusicList = apiService.getAllMusicList(category)
      responseMusicList.let {
          if (it.isSuccessful && it.body() != null){
              emit(State.success(it.body()?.data?.toGenerateListMusic()?: listOf()))
          }else{
              emit(State.failed(it.message()?: ""))
          }
      }
  }.catch {
      Log.d(TAG, "getAllListMusic: failed = ${it.message}")
      emit(State.failed(it.message.toString()))
  }.flowOn(Dispatchers.IO)
}