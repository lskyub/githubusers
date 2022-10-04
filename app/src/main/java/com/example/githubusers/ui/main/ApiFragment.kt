package com.example.githubusers.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.domain.model.User
import com.example.githubusers.R
import com.example.githubusers.adapter.OnItemClickListener
import com.example.githubusers.databinding.FragmentApiBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Api 통신으로 리스트를 그리는 fragment
 */
@AndroidEntryPoint
class ApiFragment : BindingFragment<FragmentApiBinding, ApiViewModel>(R.layout.fragment_api) {

    override val viewModel: ApiViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setAdapter(listClickListener)
        sharedViewModel.onClick = viewModel.search
    }

    private val listClickListener = object : OnItemClickListener {
        override fun onClick(v: View?, item: User.Item, position: Int) {
            when (v?.id) {
                R.id.btn_item,
                R.id.btn_favorites -> {
                    if (item.favorites) {
                        sharedViewModel.addUser(item)
                    } else {
                        sharedViewModel.removeUser(item)
                    }
                }
            }
        }
    }
}