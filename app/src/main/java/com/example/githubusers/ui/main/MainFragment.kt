package com.example.githubusers.ui.main

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.example.githubusers.R
import com.example.githubusers.adapter.OnItemClickListener
import com.example.githubusers.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BindingFragment<FragmentMainBinding, MainViewModel>(R.layout.fragment_main) {

    override val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setAdapter(listClickListener)
    }

    private val listClickListener = object : OnItemClickListener {
        override fun onClick(v: View?, position: Int) {

        }
    }
}