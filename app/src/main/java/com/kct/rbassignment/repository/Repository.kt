package com.kct.rbassignment.repository

import com.kct.rbassignment.repository.api.Repo
import io.reactivex.Single

/**
 * Created by krishan on 24/09/17.
 */
interface Repository {
    fun getData(): Single<List<Repo>>
}