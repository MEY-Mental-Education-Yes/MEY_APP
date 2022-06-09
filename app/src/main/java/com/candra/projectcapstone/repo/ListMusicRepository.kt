package com.candra.projectcapstone.repo

import com.candra.projectcapstone.helper.State
import com.candra.projectcapstone.model.ListMusicModel
import com.candra.projectcapstone.model.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ListMusicRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): ListMusicRepositoryImpl {
    override suspend fun getAllMusicList(category: String): Flow<State<List<ListMusicModel>>> {
       return remoteDataSource.getAllListMusic(category)
    }

}