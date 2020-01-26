package com.apaqah.rssfeedmvp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.prof.rssparser.Channel
import kotlinx.android.synthetic.main.activity_result_feed.*

class ResultFeedActivity : AppCompatActivity(), ResultFeedImpl {

    private lateinit var adapter: ArticleAdapter

    var url: String = ""
    override fun onStartLoad() {
        progressBar.visibility = View.VISIBLE
    }

    override fun onSuccessLoad(channel: Channel) {
        swipe_layout.isRefreshing = false
        adapter = ArticleAdapter(channel.articles)
        recycler_view.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun onErrorLoad(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onFinishLoad() {
        progressBar.visibility = View.GONE
    }

    var presenter: ResultFeedPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_feed)
        presenter = ResultFeedPresenter(this)
        presenter?.setImplementation(this)

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.itemAnimator = DefaultItemAnimator()
        recycler_view.setHasFixedSize(true)
        handleIntent()

        swipe_layout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark)
        swipe_layout.canChildScrollUp()
        swipe_layout.setOnRefreshListener {
            adapter.articles.clear()
            adapter.notifyDataSetChanged()
            swipe_layout.isRefreshing = true
            handleCall(url)
        }
    }

    private fun handleIntent() {
        url = intent.getStringExtra("url")
        handleCall(url)
    }

    private fun handleCall(url: String?) {
        if (!url?.isEmpty()!!) {
            presenter?.onSearchFeed(url)
        }
    }
}
