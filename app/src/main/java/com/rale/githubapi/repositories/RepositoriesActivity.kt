package com.rale.githubapi.repositories

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.rale.githubapi.R
import com.rale.githubapi.base.BaseApp
import com.rale.githubapi.base.ToolbarManager
import kotlinx.android.synthetic.main.activity_repositories.*
import org.jetbrains.anko.longToast
import kotlin.properties.Delegates

const val REPO_OWNER_ARG = "repo_owner_arg"

class RepositoriesActivity : AppCompatActivity(), ToolbarManager {

    override val toolbar: Toolbar by lazy { findViewById<Toolbar>(R.id.toolbar) }

    private var adapter by Delegates.notNull<RepositoriesAdapter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repositories)
        enableHomeAsUp { onBackPressed() }
        adapter = RepositoriesAdapter()
        reposRv.adapter = adapter
        val repoOwner = intent.getStringExtra(REPO_OWNER_ARG)
        collapsingToolbarLayout.title = "User name: $repoOwner"
        val viewModel = ViewModelProviders.of(this, RepositoryViewModel.Factory(application as BaseApp, repoOwner)).get(RepositoryViewModel::class.java)
        subscribeUi(viewModel)
    }

    private fun subscribeUi(viewModel: RepositoryViewModel) {
        viewModel.getRepositories.observe(this, Observer {
            it?.let {
                adapter.setRepositories(it)
            }
        })
        viewModel.error.observe(this, Observer {
            it?.let {
                longToast(it)
            }
        })
    }
}
