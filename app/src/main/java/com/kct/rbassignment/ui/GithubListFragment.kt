package com.kct.rbassignment.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kct.rbassignment.R
import com.kct.rbassignment.repository.api.Repo
import kotlinx.android.synthetic.main.fragment_github_list.*
import kotlinx.android.synthetic.main.item_repo.view.*

/**
 * Created by krishan on 02/10/17.
 */
class GithubListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_github_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showList()
    }

    fun showList() {
        val list = (activity as GitHubListAdapter.RepoListProvider).getRepoList()
        githubList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        githubList.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        githubList.adapter = GitHubListAdapter(list)
    }

    companion object {
        fun newInstance(): GithubListFragment {
            return GithubListFragment()
        }
    }

}

class GitHubListAdapter(val list: List<Repo>) : RecyclerView.Adapter<GitHubListAdapter.GithubListHolder>() {

    override fun onBindViewHolder(holder: GithubListHolder, position: Int) {
        holder.bindData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
        return GithubListHolder(view)
    }

    class GithubListHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindData(repo: Repo) {
            itemView.repoName.text = repo.name // todo move to constructor initialization
            itemView.repoOwner.text = repo.owner.login
        }
    }

    interface RepoListProvider {
        fun getRepoList(): List<Repo>
    }

}

