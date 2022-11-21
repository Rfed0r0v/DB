package com.example.realmdatabase

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.example.realmdatabase.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), LifecycleObserver {
    private lateinit var binding: ActivityMainBinding

    private val defaultLifecycleObserver = object : DefaultLifecycleObserver {
        override fun onCreate(owner: LifecycleOwner) {
            super.onCreate(owner)
            Log.d("Main", "DefaultLifecycleObserver - onCreate")
        }

        override fun onStart(owner: LifecycleOwner) {
            super.onStart(owner)
            Log.d("Main", "DefaultLifecycleObserver - onStart")
        }

        override fun onResume(owner: LifecycleOwner) {
            super.onResume(owner)
            Log.d("Main", "DefaultLifecycleObserver - onResume")
        }
    }

    private val viewModel by viewModel<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(defaultLifecycleObserver)

        // новое использование адаптера
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ContactsAdapter { contactToEditIndex ->
            editContact(contactToEditIndex)
        }

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

    private fun editContact(contactToEditIndex: Int) {
        val id = viewModel.getContactId(contactToEditIndex)
        val intent = Intent(this, EditContactActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }


}