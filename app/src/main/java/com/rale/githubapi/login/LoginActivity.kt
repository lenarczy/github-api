package com.rale.githubapi.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.rale.githubapi.R
import com.rale.githubapi.base.ToolbarManager
import com.rale.githubapi.user.USER_NAME_ARG
import com.rale.githubapi.user.UserActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity

class LoginActivity : AppCompatActivity(), ToolbarManager {

    override val toolbar: Toolbar by lazy { findViewById<Toolbar>(R.id.toolbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginBtn.setOnClickListener { getUser() }
        toolbarTitle = getString(R.string.app_name)
    }

    private fun getUser() {
        startActivity<UserActivity>(USER_NAME_ARG to userNameEt.text.toString())
    }
}
