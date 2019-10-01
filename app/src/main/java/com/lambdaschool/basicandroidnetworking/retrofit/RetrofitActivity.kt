package com.lambdaschool.basicandroidnetworking.retrofit

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.lambdaschool.basicandroidnetworking.R
import com.lambdaschool.basicandroidnetworking.model.AdviceMsg
import kotlinx.android.synthetic.main.activity_retrofit.*
import leakcanary.LeakCanary
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitActivity : AppCompatActivity(), Callback<AdviceMsg> {

    companion object {
        private const val TAG = "RETROFIT"
        var adviceTextRetrofitObj: TextView? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        adviceTextRetrofitObj = adviceTextRetrofit

        fetchNetworkAPIRetrofitButton.setOnClickListener {
            AlertDialog.Builder(this@RetrofitActivity)
                .setTitle("Delete entry")
                .setMessage("Test")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes,
                    DialogInterface.OnClickListener { dialog, which ->
                        dialog.dismiss()
                    })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()


            AdviceRetriever().getRandomAdvice().enqueue(this)
        }

        fetchNetworkAPIOkHttpButton.setOnClickListener {

            startActivity(LeakCanary.newLeakDisplayActivityIntent())

            AdviceRetriever().getRandomAdviceWithOkHttp().enqueue(this)
        }
    }

    // Callback for Retrofit Call
    override fun onResponse(call: Call<AdviceMsg>, response: Response<AdviceMsg>) {
        Log.d("RETROFIT", "onResponse")
        if (response.isSuccessful) {
            val adviceMsg = response.body()
            Log.d(TAG, adviceMsg?.getAdvice())
            adviceTextRetrofitObj?.text = adviceMsg?.getAdvice()
        } else {
            val response = "response not successful; ${response.errorBody().toString()}"
            Log.d(TAG, response)
            Toast.makeText(this@RetrofitActivity, response, Toast.LENGTH_SHORT).show()
        }
    }

    // Callback for Retrofit Call
    override fun onFailure(call: Call<AdviceMsg>, t: Throwable) {
        t.printStackTrace()
        val response = "faliure; ${t.printStackTrace()}"
        Log.d(TAG, response)
        Toast.makeText(this@RetrofitActivity, response, Toast.LENGTH_SHORT).show()
    }
}
