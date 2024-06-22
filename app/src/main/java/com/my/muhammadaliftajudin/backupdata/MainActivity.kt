package com.my.muhammadaliftajudin.backupdata

import android.app.backup.BackupManager
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.my.muhammadaliftajudin.backupdata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var backupManager: BackupManager? = null
    private var prefs: SharedPreferences? = null
    private var edit: SharedPreferences.Editor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the shared preference [filename, db name]
        prefs = getSharedPreferences("messages", Context.MODE_PRIVATE)
        edit = prefs?.edit()

        binding.buttonSave.setOnClickListener {
            edit?.putString("mesej", binding.savedDataEditText.text.toString())
            edit?.commit()
        }

        binding.buttonRetrieve.setOnClickListener {
            var savedString = prefs?.getString("mesej", "")
            binding.retrieveDataEditText.setText(savedString)
        }
    }
}