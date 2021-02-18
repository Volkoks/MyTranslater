package com.example.mytranslater.model.networkstatus

import io.reactivex.Observable

interface INetworkStatus {
    fun isOnline():Observable<Boolean>
}