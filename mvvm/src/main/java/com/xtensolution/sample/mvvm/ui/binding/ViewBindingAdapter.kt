package com.xtensolution.sample.mvvm.ui.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


/**
 * Created by Vaghela Mithun R. on 10/11/20.
 * mithun@smartadvancedtech.com
 */

class ViewBindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("imgUrl")
        fun bindImage(view: ImageView, url: String?) {
            Glide.with(view.context)
                .load(url)
                .circleCrop()
                .into(view)
        }
    }
}