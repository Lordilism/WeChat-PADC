package com.example.wechat_padc.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.wechat_padc.R
import com.example.wechat_padc.databinding.ActivityMainScreenBinding
import com.example.wechat_padc.fragments.*

class MainScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainScreenBinding

    companion object{
        fun newIntent(context:Context): Intent {
            return Intent(context,MainScreenActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.bottomNav.setOnItemSelectedListener { item ->

            when (item.itemId) {
                R.id.action_moment -> {
                    supportFragmentManager.commit {
                        replace<MomentsFragment>(R.id.fl_container)
                    }
                }
                R.id.action_chat -> {
                    supportFragmentManager.commit {
                        replace<ChatFragment>(R.id.fl_container)
                    }
                }
                R.id.action_contact -> {
                    supportFragmentManager.commit {
                        replace<ContactsFragment>(R.id.fl_container)
                    }
                }
                R.id.action_me -> {
                    supportFragmentManager.commit {
                        replace<MeFragment>(R.id.fl_container)
                    }
                }
                R.id.action_setting -> {
                    supportFragmentManager.commit {
                        replace<SettingsFragment>(R.id.fl_container)
                    }
                }
                else -> {}
            }
            true

        }
    }
}