package com.apaqah.rssfeedmvp

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.prof.rssparser.Parser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ResultFeedPresenter(context: Context) {

    private var context: Context = context

    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    var impl: ResultFeedImpl? = null

    fun setImplementation(view: ResultFeedImpl?) {
        impl = view
    }

    private fun onNoInternetConnection(): Boolean {
        if (!NetworkHelper.isConnected(context)) {
            Toast.makeText(context, "Sorry no connection", Toast.LENGTH_SHORT).show()
            return true
        }
        return false
    }

    fun onSearchFeed(textInput: String) {
        impl?.onStartLoad()
        if (!onNoInternetConnection()) {
            coroutineScope.launch(Dispatchers.Main) {
                try {
                    val parser = Parser()
                    val articleList = parser.getChannel(textInput)
                    Log.i("ListChannel", articleList.title!!)
                    // Show the channel data
                    impl?.onSuccessLoad(articleList)
                    impl?.onFinishLoad()
                } catch (e: Exception) {
                    impl?.onFinishLoad()
                    e.printStackTrace()
                    e.message?.let { impl?.onErrorLoad(it) }
                    // Handle the exception
                }
            }
        } else {
            impl?.onFinishLoad()
        }
    }
}