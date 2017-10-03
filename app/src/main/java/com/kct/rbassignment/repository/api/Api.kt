package com.kct.rbassignment.repository.api

import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by krishan on 24/09/17.
 */
interface Api {
    @GET("/orgs/octokit/repos")
    fun getGitRepos(): Single<List<Repo>>
}