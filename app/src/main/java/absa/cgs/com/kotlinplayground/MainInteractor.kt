package absa.cgs.com.kotlinplayground

import absa.cgs.com.api.DefaultResponse
import absa.cgs.com.api.RetrofitClient
import android.util.Log
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainInteractor {

    interface onFirstEventTriggerListener {
        fun onSuccessInteractListener(message: String)
        fun onFailureInteractListener(error: String)
    }


    fun firstTriggerEvent(username: JSONObject, listener: onFirstEventTriggerListener) {
        RetrofitClient.instance.createUser(username)
                .enqueue(object : Callback<DefaultResponse> {
                    override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                        listener.onFailureInteractListener(t.message.toString())
                    }

                    override fun onResponse(call: Call<DefaultResponse>, response: Response<DefaultResponse>) {
                        listener.onSuccessInteractListener(response.body()?.success.toString())
                        if (response.isSuccessful) {
                            Log.v("Message:----", response.message())
                            Log.v("Response Code:----", response.code().toString())
                            Log.v("Response Header:----", response.headers().toString())
                            Log.v("Response Raw:----", response.raw().request().toString())
                            Log.v("Body:----", response.body().toString())
                        }
                    }

                })
    }

}