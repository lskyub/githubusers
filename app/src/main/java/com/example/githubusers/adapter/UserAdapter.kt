package com.example.githubusers.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.User
import com.example.githubusers.R
import com.example.githubusers.BR

interface OnItemClickListener {
    fun onClick(v: View?, position: Int)
}

class UserAdapter :
    PagingDataAdapter<User.RS, UserAdapter.ViewHolder>(
        object : DiffUtil.ItemCallback<User.RS>() {
            override fun areItemsTheSame(oldItem: User.RS, newItem: User.RS): Boolean {
                return oldItem.id == newItem.id && oldItem.node_id == newItem.node_id
            }

            override fun areContentsTheSame(oldItem: User.RS, newItem: User.RS): Boolean {
                return oldItem.id == newItem.id && oldItem.toString() == newItem.toString()
            }
        }) {

    lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(R.layout.item_user, parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.also {
            holder.binding.setVariable(BR.item, it)
            holder.binding.setVariable(BR.listener, object : View.OnClickListener {
                override fun onClick(v: View?) {
                    if (::listener.isInitialized) {
                        getItem(holder.bindingAdapterPosition)?.id?.also {
                            listener.onClick(v, it)
                        }
                    }
                }
            })
        }
    }

    inner class ViewHolder(
        @LayoutRes layoutResId: Int,
        parent: ViewGroup
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
    ) {
        val binding: ViewDataBinding = DataBindingUtil.bind(itemView)!!
    }
}