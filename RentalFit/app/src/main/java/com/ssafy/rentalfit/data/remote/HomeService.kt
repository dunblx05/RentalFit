package com.ssafy.rentalfit.data.remote

import com.ssafy.rentalfit.data.model.dto.Equip
import com.ssafy.rentalfit.data.model.dto.Place
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeService {

    // 장비 관련
    // 장비 전체 조회
    @GET("rest/equip")
    suspend fun selectEquip() : List<Equip>
    
    // 장비 타입별 조회
    @GET("rest/equip/type/{type}")
    suspend fun selectEquipByType(@Path("type") equipType: String): List<Equip>

    // 장비 id에 다른 단건 조회
    @GET("rest/equip/eid/{equipId}")
    suspend fun selectEquipById(@Path("equipId") equipId: Int): Equip

    @GET("rest/place")
    suspend fun selectPlace() : List<Place>
    
}