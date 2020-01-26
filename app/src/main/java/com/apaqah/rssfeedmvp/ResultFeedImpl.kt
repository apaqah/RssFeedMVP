package com.apaqah.rssfeedmvp

import com.prof.rssparser.Channel

interface ResultFeedImpl {
    fun onStartLoad()
    fun onSuccessLoad(channel: Channel)
    fun onErrorLoad(message: String)
    fun onFinishLoad()
}