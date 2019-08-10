package com.raul.androidapps.cvapp.utils


import java.util.concurrent.TimeUnit

class RateLimiter(timeout: Int, timeUnit: TimeUnit) {
    private val timeout: Long = timeUnit.toMillis(timeout.toLong())

    @Synchronized
    fun shouldFetch(lastFetched: Long): Boolean {
        val now = now()
        if (lastFetched == 0L) {
            return true
        }
        if (now - lastFetched > timeout) {
            return true
        }
        return false
    }

    private fun now(): Long {
        return System.currentTimeMillis()
    }

}
