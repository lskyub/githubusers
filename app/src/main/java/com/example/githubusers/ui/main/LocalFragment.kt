package com.example.githubusers.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.domain.model.User
import com.example.githubusers.R
import com.example.githubusers.adapter.OnItemClickListener
import com.example.githubusers.databinding.FragmentLocalBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocalFragment :
    BindingFragment<FragmentLocalBinding, LocalViewModel>(R.layout.fragment_local) {

    override val viewModel: LocalViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.setAdapter(listClickListener)
    }

    private val listClickListener = object : OnItemClickListener {
        override fun onClick(v: View?, item: User.Item, isCheck: Boolean, position: Int) {
            when (v?.id) {
                R.id.btn_item,
                R.id.btn_favorites -> {
                    if (isCheck) {
                        sharedViewModel.addUser(item)
                    } else {
                        sharedViewModel.removeUser(item)
                    }
                }
            }
        }
    }
}