package com.example.realmdatabase.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner


fun Fragment.modifySoftInputMode(softInputMode: Int): SoftInputModeModifier =
    SoftInputModeModifier(this, softInputMode)

val Fragment.viewLifeCycle get() = viewLifecycleOwner.lifecycle

class SoftInputModeModifier(private val fragment: Fragment, private val softInputMode: Int) {

    private val previousSoftInputMode: Int =
        fragment.requireActivity().window.attributes.softInputMode
    private val lifecycleObserver = ModifySoftInputModeLifeCycleObserver()

    init {
        changeSoftInputModeToNew()
        fragment.viewLifeCycle.addObserver(lifecycleObserver)
    }

    fun changeSoftInputModeToNew() {
        setSoftInputMode(softInputMode)
    }

    fun changeSoftInputModeBack() {
        setSoftInputMode(previousSoftInputMode)
    }

    private fun removeObserver() {
        fragment.viewLifeCycle.removeObserver(lifecycleObserver)
    }

    private fun setSoftInputMode(softInputMode: Int) {
        fragment.requireActivity().window.setSoftInputMode(softInputMode)
    }

    private inner class ModifySoftInputModeLifeCycleObserver : DefaultLifecycleObserver {

        override fun onResume(owner: LifecycleOwner) {
            super.onResume(owner)
            changeSoftInputModeToNew()
        }

        override fun onPause(owner: LifecycleOwner) {
            super.onResume(owner)
            changeSoftInputModeBack()
        }

        override fun onDestroy(owner: LifecycleOwner) {
            super.onResume(owner)
            removeObserver()
        }
    }
}