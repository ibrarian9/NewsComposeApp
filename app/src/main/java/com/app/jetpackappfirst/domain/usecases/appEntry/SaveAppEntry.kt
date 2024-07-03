package com.app.jetpackappfirst.domain.usecases.appEntry

import com.app.jetpackappfirst.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }
}