package com.example.lifecyclelivedataviewmodel.utils

import com.example.lifecyclelivedataviewmodel.model.UserData

sealed class UserResource {
    object Loading : UserResource()
    data class Success(val list: List<UserData>?) : UserResource()
    data class Error(val message: String) : UserResource()
}
