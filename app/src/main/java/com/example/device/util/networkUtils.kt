package com.example.device.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import kotlin.reflect.typeOf

class networkUtils (private val context: Context){
    fun isNEtWorkConnected(): Boolean{
      var result= false
      val contectivityManager =
          context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val networkCapabilities = contectivityManager.activeNetwork?: return false
            val activeNetWork =
                contectivityManager.getNetworkCapabilities(networkCapabilities)?: return false
            result = when{
                activeNetWork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetWork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                activeNetWork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }else{
            contectivityManager.run {
                this.activeNetworkInfo?.run {
                    result = when (type){
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else-> true
                    }
                }
            }
        }
        return result

    }
}