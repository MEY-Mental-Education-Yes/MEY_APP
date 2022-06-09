package com.candra.projectcapstone.repo

import com.candra.projectcapstone.helper.State
import com.candra.projectcapstone.model.ListMusicModel
import kotlinx.coroutines.flow.Flow

interface ListMusicRepositoryImpl {
    suspend fun getAllMusicList(category: String): Flow<State<List<ListMusicModel>>>
}