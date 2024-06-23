package com.my.muhammadaliftajudin.backupdata

import android.app.backup.BackupManager
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.my.muhammadaliftajudin.backupdata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var prefs: SharedPreferences? = null
    private var edit: SharedPreferences.Editor? = null

    private var backupManager: BackupManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the shared preference [filename, db name]
        prefs = getSharedPreferences(BackupData.PREFS_TEST, Context.MODE_PRIVATE)
        edit = prefs?.edit()

        // Initialize the BackupManager
        backupManager = BackupManager(this)
        // binding.buttonRetrieve.isEnabled = false

        binding.buttonSave.setOnClickListener {
            edit?.putString("mesej", binding.savedDataEditText.text.toString())
            edit?.commit()

            // Update the backup in Google Drive
            Log.d("Test", "Calling backup....")
            backupManager?.dataChanged()

            if (binding.savedDataEditText.text.isNotEmpty()) {
                binding.buttonRetrieve.isEnabled = true
            }
        }

        binding.buttonRetrieve.setOnClickListener {
            val savedString = prefs?.getString("mesej", "")
            binding.retrieveDataEditText.setText(savedString)
            binding.buttonRetrieve.isEnabled = true
        }
    }
}