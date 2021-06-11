package com.wale.nasaApiImageApps.ui

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.wale.nasaApiImageApps.R
import com.wale.nasaApiImageApps.data.module.NasaImages
import com.wale.nasaApiImageApps.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.images.observe(this, Observer {
            when(it.status){
                Resource.Status.SUCCESS -> {
                    progress_bar.visibility = View.GONE
                    inside_lay.visibility = View.VISIBLE
                    if (!it.data.isNullOrEmpty()) setupUI(it.data)
                }
                Resource.Status.ERROR ->{
                    progress_bar.visibility = View.GONE
                    inside_lay.visibility = View.VISIBLE
                    if (isInternetOn()){
                        showError()
                    }
                }
                Resource.Status.LOADING ->{
                    progress_bar.visibility = View.VISIBLE
                    inside_lay.visibility = View.GONE
                }
            }
        })
    }

    private fun setupUI(data: List<NasaImages>) {
        if (data.size > 0)
        {
            heading.text = data[0].title
            explanation.setContent(data[0].explanation)
           // explanation.text =
            val url = data[0].url
            Glide.with(this).load(url).into(imageView)
        }

    }

    private fun showError() {
        val msg = "We are not connected to the internet, showing you the last image we have"
        Snackbar.make(holdParent, msg, Snackbar.LENGTH_INDEFINITE).show()
    }

    fun isInternetOn(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
}