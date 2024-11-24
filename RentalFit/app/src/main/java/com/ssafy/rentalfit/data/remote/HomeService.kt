package com.ssafy.rentalfit.data.remote

import com.ssafy.rentalfit.data.model.dto.Equip
import retrofit2.http.GET

interface HomeService {

    @GET("rest/equip")
    suspend fun selectEquip() : List<Equip>

}