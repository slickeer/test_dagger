package com.example.swipemenu

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import com.example.swipemenu.databinding.ChildFragmentBinding
import com.example.swipemenu.databinding.ChildSecondFragmentBinding
import com.example.swipemenu.vm.SharedViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ChildSecondFrag: Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: SharedViewModel

    private var _binding: ChildSecondFragmentBinding? = null
    private val binding get() = _binding!!

    init {
        logInfo("SECOND")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ChildSecondFragmentBinding.inflate(inflater, container, false)

        Log.d("CHILD SECOND FRAG", "onCreateView")
        logInfo("SECOND")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("CHILD SECOND FRAG", "onViewCreated")

        AndroidSupportInjection.inject(this)

        viewModel = ViewModelProvider(getParent(), viewModelFactory)
            .get("secondVM", SharedViewModel::class.java)

        binding.textTest.text = viewModel.test

    }

}