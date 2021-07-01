package com.vangthao.mvvm_architecture_example.data.api

import com.rx2androidnetworking.Rx2AndroidNetworking
import com.vangthao.mvvm_architecture_example.data.model.User
import io.reactivex.Single

class ApiServiceImpl : ApiService {

    override fun getUsers(): Single<List<User>> {
        return Rx2AndroidNetworking.get("https://60dd93cd878c890017fa2a0b.mockapi.io/api/users/users_list")
            .build()
            .getObjectListSingle(User::class.java)
    }
}