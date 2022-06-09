package com.candra.projectcapstone.api

import com.candra.projectcapstone.api.data.ResponseAllMusicList
import com.candra.projectcapstone.helper.Constant.CATEGORY
import com.candra.projectcapstone.helper.Constant.ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface{
    @GET(ENDPOINT)
    suspend fun getAllMusicList(
        @Path(CATEGORY) category: String
    ): Response<ResponseAllMusicList>
}