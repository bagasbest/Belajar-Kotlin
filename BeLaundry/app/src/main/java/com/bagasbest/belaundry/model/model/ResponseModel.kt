package com.bagasbest.belaundry.model.model

data class ResponseModel(
    var code: Int? = 0,
    var message: String? = null,
    var data: List<LaundryModel>
)