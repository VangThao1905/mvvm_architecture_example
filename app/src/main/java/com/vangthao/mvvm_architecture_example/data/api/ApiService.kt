package com.vangthao.mvvm_architecture_example.data.api

import com.vangthao.mvvm_architecture_example.data.model.User
import io.reactivex.Single

interface ApiService {
    fun getUsers(): Single<List<User>>
}