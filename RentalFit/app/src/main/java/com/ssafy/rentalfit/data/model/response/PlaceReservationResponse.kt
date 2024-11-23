package com.ssafy.rentalfit.data.model.response

import com.ssafy.rentalfit.data.model.dto.Place

data class PlaceReservationResponse(
    val resId: Int,
    val userId: String,
    val placeId: Int,
    val resStartTime: String,
    val resEndTime: String,
    val resCost: Int,
    val place: Place,
)