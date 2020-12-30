package com.xtensolution.sample.mvvm.ui.viewholder

import com.xtensolution.sample.mvvm.core.BaseViewHolder
import com.xtensolution.sample.mvvm.data.model.User
import com.xtensolution.sample.mvvm.databinding.RowItemUserBinding


/**
 * Created by Vaghela Mithun R. on 10/11/20.
 * vaghela.mithun@gmail.com
 */
class UserHolder(val binding: RowItemUserBinding) : BaseViewHolder(binding.root) {
    fun bindData(user: User) {
        binding.user = user
        binding.userItemContainer.setOnClickListener(this)
    }
}