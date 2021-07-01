package com.vangthao.mvvm_architecture_example.data.repository

import com.vangthao.mvvm_architecture_example.data.api.ApiHelper
import com.vangthao.mvvm_architecture_example.data.model.User
import io.reactivex.Single

class MainRepository(private val apiHelper: ApiHelper) {
    fun getUsers(): Single<List<User>> {
        return apiHelper.getUsers()
    }
}