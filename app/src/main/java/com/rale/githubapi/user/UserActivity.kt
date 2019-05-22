package com.rale.githubapi.user

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.Toast
import com.rale.githubapi.R
import com.rale.githubapi.base.BaseApp
import com.rale.githubapi.base.ToolbarManager
import com.rale.githubapi.repositories.REPO_OWNER_ARG
import com.rale.githubapi.repositories.RepositoriesActivity
import com.squareup.picasso.Picasso
import org.jetbrains.anko.startActivity
import kotlinx.android.synthetic.main.activity_user.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import timber.log.Timber

const val USER_NAME_ARG = "user_name_arg"

class UserActivity : AppCompatActivity(), ToolbarManager {

    override val toolbar: Toolbar by lazy { findViewById<Toolbar>(R.id.toolbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        val userName = intent.getStringExtra(USER_NAME_ARG)
        enableHomeAsUp { onBackPressed() }

        userName?.let {
            toolbarTitle = userName
            val viewModel = ViewModelProviders
                    .of(this, UserViewModel.Factory(this@UserActivity.application as BaseApp, it))
                    .get(UserViewModel::class.java)
            subscribeUi(viewModel)
        }
        ownedRepoBtn.setOnClickListener {
                startActivity<RepositoriesActivity>(REPO_OWNER_ARG to userName)
        }
    }

    private fun subscribeUi(viewModel: UserViewModel) {
        viewModel.userModel.observe(this, Observer<UserModel> {
            it?.let {
                userNameTv.text = it.name
                userFollowersTv.text = it.followers
                userFollowingTv.text = it.following
                userEmailTv.text = it.email
                Timber.d("Avatar url ${it.avatar}")
                if (it.avatar.isNotEmpty()) {
                    Picasso.get().load(it.avatar).resize(200, 200).into(avatarIv)
                }
            }

        })
        viewModel.error.observe(this, Observer<String> {
            it?.let {
                longToast(it)
            }
        })
    }
}
