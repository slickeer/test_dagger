package com.example.swipemenu

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.example.swipemenu.databinding.ChildFragmentBinding
import com.example.swipemenu.vm.SharedViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ChildFrag : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: SharedViewModel
    lateinit var viewModel2: SharedViewModel

    private var _binding: ChildFragmentBinding? = null
    private val binding get() = _binding!!

    init {
        logInfo("MAIN")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = ChildFragmentBinding.inflate(inflater, container, false)

        Log.d("CHILD FRAG", "onCreateView")

        logInfo("MAIN")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("CHILD FRAG", "onViewCreated")

        AndroidSupportInjection.inject(this)

        viewModel = ViewModelProvider(getParent(), viewModelFactory)
            .get(SharedViewModel::class.java)

        viewModel2 = ViewModelProvider(this, viewModelFactory)
            .get("secondVM", SharedViewModel::class.java)

        viewModel.test = "SHared ViewModel #1"
        viewModel2.test = "SHared ViewModel #2"

        binding.textTest.text = viewModel2.test

        binding.showSecondFrag.setOnClickListener {
            childFragmentManager.beginTransaction().add(R.id.frag_test, ChildSecondFrag()).commit()
        }

        binding.showThirdFrag.setOnClickListener {
            childFragmentManager.beginTransaction().add(R.id.frag_test, ChildThirdFrag()).commit()
        }


    }

}