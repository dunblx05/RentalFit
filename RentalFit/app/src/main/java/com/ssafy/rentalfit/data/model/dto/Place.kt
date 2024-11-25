package com.ssafy.rentalfit.data.model.dto

data class Place(
    val placeId: Int,
    val placeName: String,
    val placePeople: Int,
    val placeLocation: String,
    val placeType: String,
    val placeCost: Int,
    val placeImg: String,
    val placeDetail: String
)