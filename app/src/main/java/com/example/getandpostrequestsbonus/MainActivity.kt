package com.example.getandpostrequestsbonus

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var ed: EditText
    lateinit var bt: Button
    lateinit var tv:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ed=findViewById(R.id.ed)
        tv=findViewById(R.id.tv)
        bt= findViewById(R.id.button)

        bt.setOnClickListener {

            addUser()

        }
        retrifit()
    }

private fun retrifit(){


    val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)//call api to fetch data

    apiInterface!!.getUser()?.enqueue(object : Callback<Custompeople?> {

        override fun onResponse(
            call: Call<Custompeople?>,
            response: Response<Custompeople?>
        ) {
                for(index in response.body()!!){

                    tv.text =" ${tv.text} \n ${index.name}"
                }

        }

        override fun onFailure(call: Call<Custompeople?>, t: Throwable) {
         call.cancel()

        }


    } )














}
    private fun addUser() {

        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        val progressDialog = ProgressDialog(this@MainActivity)
        progressDialog.setMessage("Please wait")
        progressDialog.show()
        if(apiInterface!=null){

            apiInterface.addUser(Custompeople.CustompeopleItem(ed.text.toString(), 0)).enqueue(object : Callback<Custompeople.CustompeopleItem?> {


                override fun onResponse(
                    call: Call<Custompeople.CustompeopleItem?>,
                    response: Response<Custompeople.CustompeopleItem?>
                ) {

                   tv.text = response.body()!!.name

                }

                override fun onFailure(call: Call<Custompeople.CustompeopleItem?>, t: Throwable) {


                }
            })




        }

    }


}