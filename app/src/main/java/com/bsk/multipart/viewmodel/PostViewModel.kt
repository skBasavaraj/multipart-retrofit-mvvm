package com.bsk.multipart.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bsk.multipart.model.RegisterRequestBody
import com.bsk.multipart.network.ApiInterface
import com.bsk.multipart.model.ApiResponse
import com.bsk.multipart.network.RetrofitInstance
import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class PostViewModel:ViewModel() {
    var mutableLiveData:MutableLiveData<ApiResponse>
    init {
        mutableLiveData = MutableLiveData()
    }
    fun getViewModel():MutableLiveData<ApiResponse>{
        return  mutableLiveData
    }
    /**
     *   request api
     */
    fun makeApiCall(fileRealPath:String,name:String, mail:String, password:String){
        var registerRequestBody: RegisterRequestBody = RegisterRequestBody(name, mail, password)
        val gson = Gson()
        var jsonToString = gson.toJson(registerRequestBody)
        val file: File = File(fileRealPath)
        var requestFile: RequestBody =
            RequestBody.create(MediaType.parse("multipart/form-data"), file)
        var body = MultipartBody.Part.createFormData("file", file.name, requestFile)
        var jsonBody: RequestBody =
            RequestBody.create(MediaType.parse("multipart/form-data"), jsonToString)
        var apiInterface = RetrofitInstance.getInstance().create(ApiInterface::class.java)
        var call = apiInterface.register(body, jsonBody)
        call.enqueue(object : Callback<ApiResponse?> {
            override fun onResponse(call: Call<ApiResponse?>, response: Response<ApiResponse?>) {
                if (response.isSuccessful) {
                    mutableLiveData.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<ApiResponse?>, t: Throwable) {
                mutableLiveData.postValue(null)
                Log.d(";;","${t.message}")
            }
        })
    }
}