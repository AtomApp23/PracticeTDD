package com.github.johnnysc.practicetdd

import java.lang.IllegalStateException

interface Repository {

    interface DataCallback {
        fun provideError(message: String)
        fun provideSuccess(data: String)
    }

    class Base(private val api: Api) {
        fun fetch(s: String, dataCallback: DataCallback) {
        }
    }
}

interface Api {

    fun fetch(body: RequestBody, callback: Callback)

    interface RequestBody {
        fun isEmpty(): Boolean
    }

    interface Callback {
        fun provideError(e: Result.Error)
        fun provideSuccess(data: Result.Success)
    }

    interface Result {
        open class Error(private val e: IllegalStateException)
        open class Success(private val data: String)
    }
}

