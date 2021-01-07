package com.xtensolution.sample.mvvm.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xtensolution.sample.mvvm.R
import com.xtensolution.core.BaseViewHolder
import com.xtensolution.core.DataResultStatus
import com.xtensolution.core.data.model.User
import com.xtensolution.sample.mvvm.ui.adapter.UserListAdapter
import com.xtensolution.sample.mvvm.ui.viewmodel.UserViewModel
import com.xtensolution.sample.mvvm.ui.viewmodel.UserViewModel.UserViewModelFactory

class UserListFragment : Fragment(), BaseViewHolder.ViewHolderClickListener {

    companion object {
        fun newInstance() = UserListFragment()
    }

    private val viewModel: UserViewModel by lazy {
        ViewModelProvider(this, UserViewModelFactory(requireActivity().application)).get(
            UserViewModel::class.java
        )
    }
    private lateinit var userListAdapter: UserListAdapter
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchUsers()
        initListView(view)
    }

    private fun initListView(view: View) {
        progressBar = view.findViewById(R.id.pbLoader)

        val recyclerView: RecyclerView = view.findViewById(R.id.rvUserItems)
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager

        userListAdapter = UserListAdapter(context, ArrayList<User>())
        userListAdapter.holderClickListener = this
        userListAdapter.setHasMorePage(false)
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.adapter = userListAdapter
        bindUserData()
    }

    override fun onViewHolderViewClicked(view: View?, position: Int) {
        if (view?.id == R.id.userItemContainer) {
        }
    }

    private fun bindUserData() {
        handleError(View.GONE)
        viewModel.userData.observe(
            viewLifecycleOwner,
            object : Observer<DataResultStatus<List<User>>> {
                override fun onChanged(t: DataResultStatus<List<User>>?) {
                    loading(View.GONE)
                    handleError(View.GONE)
                    when (t) {
                        is DataResultStatus.Loading -> loading(View.VISIBLE)
                        is DataResultStatus.Success -> userListAdapter.addList(t.data)
                        is DataResultStatus.Error -> handleError(View.VISIBLE)
                        else -> showNoData(t!!.message)
                    }
                }
            })
    }

    private fun showNoData(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun handleError(visibility: Int) {
        loading(visibility)
    }

    fun loading(visibility: Int) {
        progressBar.visibility = visibility
    }
}