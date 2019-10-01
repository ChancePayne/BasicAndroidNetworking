package com.lambdaschool.basicandroidnetworking

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lambdaschool.basicandroidnetworking.http.HttpActivity
import com.lambdaschool.basicandroidnetworking.retrofit.RetrofitActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle(R.string.title_main)

        httpUrlConnectionButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, HttpActivity::class.java))
        }

        retrofitButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, RetrofitActivity::class.java))
        }
    }
}
