package com.example.lifecyclelivedataviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lifecyclelivedataviewmodel.model.UserData
import com.example.lifecyclelivedataviewmodel.networking.ApiClient
import com.example.lifecyclelivedataviewmodel.networking.ApiService
import com.example.lifecyclelivedataviewmodel.utils.UserResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel : ViewModel() {

    private var liveData = MutableLiveData<UserResource>(UserResource.Loading)

    // bu viewModelni elon qilinganda 1 marta chaqiriladi qayta chaqirilmaydi
    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        ApiClient.getRetrofit().create(ApiService::class.java)
            .getUsers()
            .enqueue(object : Callback<List<UserData>> {
                override fun onResponse(
                    call: Call<List<UserData>>,
                    response: Response<List<UserData>>
                ) {
                    if (response.isSuccessful) {
                        liveData.postValue(UserResource.Success(response.body()))
                    } else {
                        when (response.code()) {
                            in 400..499 -> liveData.postValue(UserResource.Error("Client error"))
                            in 500..599 -> liveData.postValue(UserResource.Error("Server error"))
                        }
                    }
                }

                override fun onFailure(call: Call<List<UserData>>, t: Throwable) {
                    liveData.postValue(UserResource.Error(t.message ?: ""))

                }

            })
    }

    fun getUserList(): LiveData<UserResource> {
        return liveData
    }

}