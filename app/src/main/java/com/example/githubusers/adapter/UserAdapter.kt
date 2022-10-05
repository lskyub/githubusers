package com.example.githubusers.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
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
    fun onClick(v: View?, item: User.Item, position: Int)
}

class UserAdapter :
    PagingDataAdapter<User.Item, UserAdapter.ViewHolder>(
        /**
         * DiffUtil 리스트 아이템을 비교하여 다른 아이템만 업데이트 하기위해 사용
         */
        object : DiffUtil.ItemCallback<User.Item>() {
            override fun areItemsTheSame(oldItem: User.Item, newItem: User.Item): Boolean {
                return oldItem.id == newItem.id && oldItem.login == newItem.login
            }

            override fun areContentsTheSame(oldItem: User.Item, newItem: User.Item): Boolean {
                return oldItem.id == newItem.id && oldItem.toString() == newItem.toString()
            }
        }) {

    lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(R.layout.item_user, parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.also { item ->
            holder.binding.setVariable(
                /**
                 * 0번 위치는 항상 헤더를 포함하고
                 * 이전 데이터랑 현재의 데이터의 시작이 다를 경우 헤더를 표현
                 */
                BR.isHeader, if (position == 0) {
                    true
                } else {
                    getItem(position - 1)?.login?.substring(0, 1) != item.login.substring(0, 1)
                }
            )
            holder.binding.setVariable(BR.item, item)
            holder.favorites.isChecked = item.favorites
            holder.binding.setVariable(BR.listener, object : View.OnClickListener {
                override fun onClick(v: View?) {
                    holder.favorites.isChecked = !item.favorites
                    item.favorites = !item.favorites
                    if (::listener.isInitialized) {
                        getItem(holder.bindingAdapterPosition)?.id?.also {
                            listener.onClick(v, item, it)
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
        val favorites = binding.root.findViewById<CheckBox>(R.id.btn_favorites)
    }
}