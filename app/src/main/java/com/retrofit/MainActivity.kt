package com.retrofit

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.retrofit.dominio.RetrofitService
import com.retrofit.dominio.Usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.List as List1


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buscaDados()
    }

    private fun buscaDados() {
        val serviceRetrofit = RetrofitService()
        serviceRetrofit.api?.obterUsuarios()?.enqueue(object : Callback<List1<Usuario>> {
            override fun onResponse(
                    call: Call<List1<Usuario>?>?,
                    response: Response<List1<Usuario>?>?
            ) {
                val lista = response?.body();
                val linearLayout = findViewById<LinearLayout>(R.id.rootLayout)
                linearLayout.gravity = Gravity.CENTER_VERTICAL;
                if (lista != null) {
                    for (user in lista) {
                        val linearLayout2 = LinearLayout(this@MainActivity)
                        linearLayout2.setPadding(11,11,11,11)
                        linearLayout2.setOrientation(LinearLayout.VERTICAL)
                        val textView1 = TextView(this@MainActivity)
                        val textView2 = TextView(this@MainActivity)
                        textView1.textSize = 20f
                        textView1.text = "Nome: " + user.name.toString()
                        textView1.setGravity(Gravity.CENTER);
                        textView2.textSize = 20f
                        textView2.text = "Email:" + user.email.toString()
                        textView2.setGravity(Gravity.CENTER);
                        linearLayout2.addView(textView1)
                        linearLayout2.addView(textView2)
                        linearLayout.addView(linearLayout2)
                    }
                }
            }

            override fun onFailure(call: Call<List1<Usuario>?>?, t: Throwable?) {
                Log.e("Erro", "************** erro **********\n" + t?.message.toString())
            }
        })
    }
}