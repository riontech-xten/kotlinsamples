package com.xtensolution.sample.mvvm.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView


/**
 * Created by Vaghela Mithun R. on 10/11/20.
 * vaghela.mithun@gmail.com
 */
abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private lateinit var viewHolderClickListener: ViewHolderClickListener

    interface ViewHolderClickListener {
        fun onViewHolderViewClicked(view: View?, position: Int)
    }

    fun setViewClickListener(holderClickObserver: ViewHolderClickListener) {
        this.viewHolderClickListener = holderClickObserver
    }

    override fun onClick(view: View?) {
        viewHolderClickListener.onViewHolderViewClicked(view, adapterPosition)
    }
}