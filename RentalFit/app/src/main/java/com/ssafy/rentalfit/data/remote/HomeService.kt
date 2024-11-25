package com.ssafy.rentalfit.data.remote

import com.ssafy.rentalfit.data.model.dto.Equip
import com.ssafy.rentalfit.data.model.dto.Place
import retrofit2.http.GET

interface HomeService {

    @GET("rest/equip")
    suspend fun selectEquip() : List<Equip>

    @GET("rest/place")
    suspend fun selectPlace() : List<Place>

}