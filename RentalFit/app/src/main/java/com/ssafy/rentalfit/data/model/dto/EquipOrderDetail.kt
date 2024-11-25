package com.ssafy.rentalfit.data.model.dto

data class EquipOrderDetail(
    val detailId: Int,
    val equipOrderId: Int,
    val equipId: Int,
    val quantity: Int,
    val equip: Equip? = null
) {
    constructor(equipId: Int, quantity: Int):this(0, 0, equipId, quantity)
}