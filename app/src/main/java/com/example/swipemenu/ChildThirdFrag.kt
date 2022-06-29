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
import com.example.swipemenu.databinding.ChildThirdFragmentBinding
import com.example.swipemenu.vm.SharedViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ChildThirdFrag: Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: SharedViewModel

    private var _binding: ChildThirdFragmentBinding? = null
    private val binding get() = _binding!!

    init {
         logInfo("THIRD")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ChildThirdFragmentBinding.inflate(inflater, container, false)

        Log.d("CHILD THIRD FRAG", "onCreateView")
        logInfo("THIRD")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("CHILD THIRD FRAG", "onViewCreated")

        AndroidSupportInjection.inject(this)

        viewModel = ViewModelProvider(getParent(), viewModelFactory)
            .get("secondVM", SharedViewModel::class.java)

        binding.textTest.text = viewModel.test

    }

    /**
     * use this method in lifecycle methods like. onCreateView, onViewCreated
     * cause fragment can be created without attaching to activity or parent fragment
     * but those method will run when we attach it, then we can find where it's attached by using this method.
     *
     *
     * if fragment is attached to parentFragment it will have parentFragment and activity set ([requireParentFragment] [requireActivity])
     * but if it's attached to activity it will have parentFragment as null ([requireParentFragment]) and activity ([requireActivity]) set.
     */
    private fun getParent(): ViewModelStore {
        if (parentFragment != null)
            return requireParentFragment().viewModelStore

        //Default, cause fragment needs to be attached to activity
        return requireActivity().viewModelStore
    }

}