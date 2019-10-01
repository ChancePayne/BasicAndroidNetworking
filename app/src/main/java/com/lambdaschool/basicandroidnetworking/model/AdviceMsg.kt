package com.lambdaschool.basicandroidnetworking.model

import com.google.gson.annotations.SerializedName

data class Slip(
    val advice: String?,
    @SerializedName("slip_id") val slipId: String?
) {
    override fun toString(): String {
        return "Slip(advice=$advice, slipId=$slipId)"
    }
}

data class AdviceMsg(
    val slip: Slip?
) {

    fun getAdvice(): String {
        return "${slip?.advice}"
    }

    override fun toString(): String {
        return "AdviceMsg(slip=$slip)"
    }
}
