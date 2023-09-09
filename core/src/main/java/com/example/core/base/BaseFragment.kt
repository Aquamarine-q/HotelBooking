package com.example.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.core.di.App
import com.example.core.di.provider.ApplicationProvider
import com.example.core.di.viewmodel.ViewModelFactory
import javax.inject.Inject

abstract class BaseFragment<VB : ViewBinding>(
    private val inflate: (
        LayoutInflater, ViewGroup?, Boolean
    ) -> VB
) : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var _binding: VB? = null
    val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        inject((requireActivity().application as App).getApplicationProvider())
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    abstract fun inject(applicationProvider: ApplicationProvider)

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}