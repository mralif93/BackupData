package com.my.muhammadaliftajudin.backupdata

import android.app.backup.BackupAgentHelper
import android.app.backup.SharedPreferencesBackupHelper

class BackupData : BackupAgentHelper() {
    companion object {
        val PREFS_TEST = "messages"
        val MY_PREFS_BACKUP_KEY = "messagebckp"
    }

    override fun onCreate() {
        super.onCreate()
        val helper = SharedPreferencesBackupHelper(this, PREFS_TEST)
        addHelper(MY_PREFS_BACKUP_KEY, helper)
    }
}