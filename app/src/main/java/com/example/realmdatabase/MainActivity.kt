package com.example.realmdatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.*
import com.example.realmdatabase.databinding.ActivityMainBinding
import io.realm.Realm
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), LifecycleObserver {

    private lateinit var binding: ActivityMainBinding

    private val defaultLifecycleObserver = object : DefaultLifecycleObserver {

    }

    private val viewModel : MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(defaultLifecycleObserver)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ContactsAdapter()

        viewModel.allContacts.observe(this) {
            adapter.setData(it)
        }

        binding.rvContacts.adapter = adapter

        binding.fabAddContact.setOnClickListener {
            startActivity(Intent(this, AddContactActivity::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(defaultLifecycleObserver)
    }
}