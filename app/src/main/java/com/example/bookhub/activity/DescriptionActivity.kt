package com.example.bookhub.activity

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.bookhub.R
import com.example.bookhub.model.Book
import com.example.bookhub.util.ConnectionManager
import com.squareup.picasso.Picasso
import org.json.JSONException
import org.json.JSONObject

lateinit var txtBookName:TextView
lateinit var txtBookAuthor:TextView
lateinit var txtBookPrice:TextView
lateinit var txtBookRating:TextView
lateinit var imgBookImage:ImageView
lateinit var txtBookDesc:TextView
lateinit var btnAddToFav:Button
lateinit var progressBar:ProgressBar
lateinit var progressLayout: RelativeLayout
var bookId:String ? ="100"
lateinit var toolbar2: Toolbar


class DescriptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)

        txtBookName = findViewById(R.id.txtBookName)
        txtBookAuthor = findViewById(R.id.txtBookAuthor)
        txtBookPrice = findViewById(R.id.txtBookPrice)
        txtBookRating = findViewById(R.id.txtBookRating)
        imgBookImage = findViewById(R.id.imgBookImage)
        txtBookDesc=findViewById(R.id.txtBookDesc)
        btnAddToFav=findViewById(R.id.btnAddtoFav)
        progressBar=findViewById(R.id.progressBar)
        progressBar.visibility=View.VISIBLE
        progressLayout=findViewById(R.id.progressLayout)
        progressLayout.visibility=View.VISIBLE
        toolbar=findViewById(R.id.toolbar2)
        setSupportActionBar(toolbar)
        supportActionBar?.title="Book Details"



        if(intent!= null){
            bookId=intent.getStringExtra("book_id")
        }else{
            finish()
            Toast.makeText(this@DescriptionActivity,"Some Unexpected Error",Toast.LENGTH_SHORT).show()
        }
        if (bookId == "100"){
            finish()
            Toast.makeText(this@DescriptionActivity,"Some Unexpected Error",Toast.LENGTH_SHORT).show()
        }
        val queue = Volley.newRequestQueue(this@DescriptionActivity)
        val url ="http://13.235.250.119/v1/book/get_book/"
        val jsonParams = JSONObject()
        jsonParams.put("book_id", bookId)
        if (ConnectionManager().checkConnectivity(this@DescriptionActivity as Context)){
        val jsonRequest = object :
            JsonObjectRequest(Request.Method.POST, url,jsonParams, Response.Listener {
                try {

                    val success = it.getBoolean("success")
                    progressLayout.visibility = View.GONE
                    if (success) {
                        val bookJsonObject = it.getJSONObject("book_data")

                        Picasso.get().load(bookJsonObject.getString("image")).error(R.drawable.default_book_cover).into(
                            imgBookImage)
                        txtBookName.text = bookJsonObject.getString("name")
                        txtBookAuthor.text = bookJsonObject.getString("author")
                        txtBookPrice.text = bookJsonObject.getString("price")
                        txtBookRating.text = bookJsonObject.getString("rating")
                        txtBookDesc.text = bookJsonObject.getString("description")
                    }else {
                        Toast.makeText(
                            this@DescriptionActivity as Context,
                            "Some Error occurred!!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }catch (e: JSONException){
                    Toast.makeText(this@DescriptionActivity as Context,"Some unexpected error",Toast.LENGTH_SHORT).show()
                }

            },Response.ErrorListener {
                Toast.makeText(this@DescriptionActivity  as Context,"Volley error Occured",Toast.LENGTH_SHORT).show()
            }){
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-type"] = "application/json"
                headers["token"] = "4dabd3b987382b"
                return headers
        }
        }
        queue.add(jsonRequest)
        }else{val dialog = AlertDialog.Builder(this@DescriptionActivity as Context)
        dialog.setTitle("Error")
        dialog.setMessage("Internet Connection not Found")
        dialog.setPositiveButton("Open Settings") { text, listener ->
            val settingIntent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
            startActivity(settingIntent)
            this@DescriptionActivity?.finish()

        }
        dialog.setNegativeButton("Exit"){text,listener->
            ActivityCompat.finishAffinity(this@DescriptionActivity as Activity)
        }
        dialog.create()
        dialog.show()
            }



    }

    }
