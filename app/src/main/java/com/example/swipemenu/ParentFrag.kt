package com.example.swipemenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.swipemenu.databinding.ParentFragmentBinding
import com.example.swipemenu.vm.SharedViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ParentFrag : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
   lateinit var viewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = ParentFragmentBinding.inflate(layoutInflater, container, false)

        return view.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       super.onViewCreated(view, savedInstanceState)
        AndroidSupportInjection.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(SharedViewModel::class.java)

        viewModel.test = "PARENT FRAG parent"

        childFragmentManager.beginTransaction().add(R.id.fragment_test, ChildFrag()).commit()
    }

}