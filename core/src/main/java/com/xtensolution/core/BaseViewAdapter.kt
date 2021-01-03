package com.xtensolution.core

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView


/**
 * Created by Vaghela Mithun R. on 01/11/20.
 * vaghela.mithun@gmail.com
 */
abstract class BaseViewAdapter<T>(ctx: Context?, list: MutableList<T>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val TAG = BaseViewAdapter::class.java.simpleName
    protected var inflater: LayoutInflater
    protected var context: Context? = ctx
    protected var objectsList: MutableList<T>? = list
    lateinit var holderClickListener: BaseViewHolder.ViewHolderClickListener
    private var paginationListener: PaginationListener? = null
    private var isPageLoading = false
    private var hasMorePage = true

    init {
        context = ctx
        inflater = LayoutInflater.from(ctx)
    }

    open fun setHasMorePage(hasPage: Boolean) {
        hasMorePage = hasPage
    }

    open fun hasMorePage(): Boolean {
        return hasMorePage
    }

    open fun setPageLoading(pageLoading: Boolean) {
        isPageLoading = pageLoading
    }

    open fun isPageLoading(): Boolean {
        return isPageLoading
    }

    open fun setPaginationListener(paginationListener: PaginationListener?) {
        this.paginationListener = paginationListener
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun getItemCount(): Int {
        return objectsList!!.size
    }

    open fun getCount(): Int {
        return objectsList!!.size
    }

    open fun getItem(position: Int): T {
        return objectsList!![position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    open fun updateList(newData: MutableList<T>?) {
        objectsList = newData
        notifyDataSetChanged()
    }

    open fun add(item: T) {
        objectsList!!.add(item)
        notifyDataSetChanged()
    }

    open fun addAt(position: Int, item: T) {
        objectsList!!.add(position, item)
        notifyDataSetChanged()
    }

    open fun addList(items: List<T>?) {
        objectsList!!.addAll(items!!)
        notifyDataSetChanged()
    }

    open fun addAtLast(items: List<T>?) {
        if (isPageLoading()) objectsList!!.addAll(
            objectsList!!.size - 1,
            items!!
        ) else objectsList!!.addAll(
            items!!
        )
        notifyDataSetChanged()
    }


    open fun getList(): List<T>? {
        return objectsList
    }

    open fun remove(item: T) {
        objectsList!!.remove(item)
        notifyDataSetChanged()
    }

    open fun remove(position: Int) {
        objectsList!!.removeAt(position)
        notifyItemRemoved(position)
    }

    interface PaginationListener {
        fun loadNextPage(offset: Int)
    }

    protected open fun loadNextPage(position: Int) {
        Log.d(TAG, "loadNextPage: ")
        if (paginationListener != null && position == objectsList!!.size - 1) {
            if (!isPageLoading() && hasMorePage()) {
                paginationListener!!.loadNextPage(objectsList!!.size - 1)
            }
        }
    }

    open fun clear() {
        objectsList!!.clear()
        notifyDataSetChanged()
    }
}