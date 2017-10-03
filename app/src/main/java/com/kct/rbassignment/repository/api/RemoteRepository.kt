package com.kct.rbassignment.repository.api

import com.kct.rbassignment.repository.Repository
import io.reactivex.Single

class RemoteRepository(val api: Api) : Repository {

    override fun getData(): Single<List<Repo>> {
        return api.getGitRepos()
    }

}