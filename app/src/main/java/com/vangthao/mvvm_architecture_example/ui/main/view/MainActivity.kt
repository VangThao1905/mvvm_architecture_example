package com.vangthao.mvvm_architecture_example.ui.main.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.vangthao.mvvm_architecture_example.R
import com.vangthao.mvvm_architecture_example.data.api.ApiHelper
import com.vangthao.mvvm_architecture_example.data.api.ApiServiceImpl
import com.vangthao.mvvm_architecture_example.data.model.User
import com.vangthao.mvvm_architecture_example.ui.base.ViewModelFactory
import com.vangthao.mvvm_architecture_example.ui.main.adapter.MainAdapter
import com.vangthao.mvvm_architecture_example.ui.main.viewmodel.MainViewModel
import com.vangthao.mvvm_architecture_example.utils.Status
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupUI()
        setupViewModel()
        setupObserver()
        //addDefaultUser()
    }

    private fun setupObserver() {
        Log.d(TAG, "setupObserver: ")
        mainViewModel.getUsers().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    Log.d(TAG, "setupObserver: success")
                    progressBar.visibility = View.GONE
                    it.data?.let { users ->
                        renderList(users)
                    }
                    my_recyclerview.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    Log.d(TAG, "setupObserver: loading...")
                    progressBar.visibility = View.VISIBLE
                    my_recyclerview.visibility = View.GONE

                }
                Status.ERROR -> {
                    progressBar.visibility = View.GONE
                    Log.d(TAG, "setupObserver error message:${it.message}")
                }
            }
        })
    }

    private fun renderList(users: List<User>) {
        Log.d(TAG, "renderList: ")
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {
        Log.d(TAG, "setupViewModel: ")
        mainViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(ApiServiceImpl()))
        ).get(MainViewModel::class.java)
    }

    private fun setupUI() {
        Log.d(TAG, "setupUI: ")
        progressBar.visibility = View.VISIBLE
        my_recyclerview.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())

        my_recyclerview.addItemDecoration(
            DividerItemDecoration(
                my_recyclerview.context,
                (my_recyclerview.layoutManager as LinearLayoutManager).orientation
            )
        )

        my_recyclerview.adapter = adapter
    }
}