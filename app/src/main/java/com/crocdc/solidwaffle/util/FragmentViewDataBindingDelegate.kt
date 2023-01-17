package com.crocdc.solidwaffle.util

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

// https://billbk.in/blog/fragment-view-binding-initialisation-using-delegates/

inline fun <reified T : ViewDataBinding> Fragment.viewDataBinding() =
    FragmentViewDataBindingDelegate(T::class.java, this)

class FragmentViewDataBindingDelegate<T : ViewDataBinding>(
    bindingClass: Class<T>,
    private val fragment: Fragment
) : ReadOnlyProperty<Fragment, T> {

    private var binding: T? = null
    private val bindMethod = bindingClass.getMethod("bind", View::class.java)

    init {
        fragment.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onCreate(owner: LifecycleOwner) {
                fragment.viewLifecycleOwnerLiveData.observe(fragment) { viewLifecycleOwner ->
                    viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {

                        override fun onCreate(owner: LifecycleOwner) {}

                        override fun onStart(owner: LifecycleOwner) {}

                        override fun onResume(owner: LifecycleOwner) {}

                        override fun onPause(owner: LifecycleOwner) {}

                        override fun onStop(owner: LifecycleOwner) {}

                        override fun onDestroy(owner: LifecycleOwner) {
                            /**
                             * Clear the binding when Fragment lifecycle called the onDestroy
                             */
                            binding = null
                        }
                    })
                }
            }

            override fun onStart(owner: LifecycleOwner) {}

            override fun onResume(owner: LifecycleOwner) {}

            override fun onPause(owner: LifecycleOwner) {}

            override fun onStop(owner: LifecycleOwner) {}

            override fun onDestroy(owner: LifecycleOwner) {}
        })
    }

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        binding?.let { return it }
        val lifecycle = fragment.viewLifecycleOwner.lifecycle

        if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            error("Cannot access view bindings. View lifecycle is ${lifecycle.currentState}!")
        }

        val invoke = bindMethod.invoke(null, thisRef.requireView()) as T
        return invoke.also { this.binding = it }
    }
}
