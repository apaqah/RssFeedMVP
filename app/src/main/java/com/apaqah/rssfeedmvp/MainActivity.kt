package com.apaqah.rssfeedmvp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.prof.rssparser.Channel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class MainActivity : AppCompatActivity() {

    private val url = "https://www.antaranews.com/rss/top-news"
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private var textInput: String? = ""
    var articleList: Channel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnSubmit.setOnClickListener {
            textInput = edtInput.text.toString()
            if (!textInput.isNullOrEmpty()) {
                val intent = Intent(this, ResultFeedActivity::class.java)
                intent.putExtra("url", textInput)
                startActivity(intent)
            }
            Log.d("Text", textInput!!)
        }
    }

    /*private fun getFeed(url: String) {

        coroutineScope.launch(Dispatchers.Main) {
            try {
                val parser = Parser()
                val articleList = parser.getChannel(url)
                Log.i("ListChannel", articleList.title!!)
                // Show the channel data
            } catch (e: Exception) {
                e.printStackTrace()
                // Handle the exception
            }
        }

    }*/
}
