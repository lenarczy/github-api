package com.rale.githubapi.repositories

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rale.githubapi.R
import kotlinx.android.synthetic.main.repo_item.view.*

class RepositoriesAdapter : RecyclerView.Adapter<RepositoriesAdapter.ViewHolder>() {

    val repositories = mutableListOf<RepositoryModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repo_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = repositories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(repositories[position])
    }

    fun setRepositories(repos : List<RepositoryModel>) {
        if (repositories.isEmpty()) {
            repositories.clear()
            repositories.addAll(repos)
            notifyItemRangeInserted(0, repos.size)
        } else {
            val oldRepos = repositories.toList()
            val result = DiffUtil.calculateDiff(object: DiffUtil.Callback() {
                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                        oldRepos[oldItemPosition].name == repos[newItemPosition].name

                override fun getOldListSize() = oldRepos.size

                override fun getNewListSize() = repos.size

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                        oldRepos[oldItemPosition] == repos[newItemPosition]
            })
            repositories.clear()
            repositories.addAll(repos)
            result.dispatchUpdatesTo(this)
        }
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        fun bind(repository: RepositoryModel) {
            with(repository) {
                itemView.repoNameTv.text = name
                itemView.repoDescriptionTv.text = description
                itemView.repoLanguageTv.text = language
            }
        }
    }
}