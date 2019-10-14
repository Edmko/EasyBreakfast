package com.example.easybreakfast.model

data class ResponseModel(
    val count: Int,
    val from: Int,
    val hits: List<Hit>,
    val more: Boolean,
    val params: Params,
    val q: String,
    val to: Int
)