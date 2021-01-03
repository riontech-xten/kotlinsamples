package com.xtensolution.sample.mvvm.ui.adapter

import com.xtensolution.sample.mvvm.ui.viewholder.UserHolder
import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xtensolution.core.BaseViewAdapter
import com.xtensolution.core.data.model.User
import com.xtensolution.core.databinding.RowItemUserBinding


/**
 * Created by Vaghela Mithun R. on 10/11/20.
 * vaghela.mithun@gmail.com
 */
class UserListAdapter(context: Context?, list: MutableList<User>) :
    BaseViewAdapter<User>(context, list) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: RowItemUserBinding = RowItemUserBinding.inflate(inflater, parent, false)
        return UserHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is UserHolder) {
            holder.setViewClickListener(holderClickListener)
            holder.bindData(getItem(position))
            loadNextPage(position)
        }
    }
}