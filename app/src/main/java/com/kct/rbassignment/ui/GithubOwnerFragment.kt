package com.kct.rbassignment.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kct.rbassignment.R
import com.kct.rbassignment.repository.api.Owner
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_github_detail.*

/**
 * Created by krishan on 03/10/17.
 */
class GithubOwnerFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_github_detail, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val owner = (activity as GithubOwnerProvider).getOwner()
        repoFullName.text = owner.htmlUrl
        repoOwner.text = owner.login
        Picasso.with(context).load(owner.avatarUrl).into(imageView)
    }

    companion object {
        fun newInstance(): GithubOwnerFragment {
            return GithubOwnerFragment()
        }
    }

    interface GithubOwnerProvider {
        fun getOwner(): Owner
    }

}