package com.kct.rbassignment.ui

import com.kct.rbassignment.core.Presenter
import com.kct.rbassignment.core.Screen
import com.kct.rbassignment.repository.api.Owner
import com.kct.rbassignment.repository.api.RemoteRepository
import com.kct.rbassignment.repository.api.Repo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by krishan on 02/10/17.
 */
interface MainActivityScreen : Screen {
    fun showLoading()
    fun hideLoading()
    fun showData()
    fun showError()
}

class MainActivityPresenter @Inject constructor() : Presenter<MainActivityScreen>() {

    @Inject
    lateinit var remote: RemoteRepository

    internal lateinit var uiModel: UiModel

    fun getGithubData() {
        view?.showLoading()
        compositeDisposable.add(remote.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Repo>>() {
                    override fun onError(e: Throwable) {
                        view?.hideLoading()
                        view?.showError()
                    }

                    override fun onSuccess(t: List<Repo>) {
                        uiModel = UiModel(t[0].owner, t)
                        view?.showData()
                        view?.hideLoading()
                    }
                }))
    }

    data class UiModel(val owner: Owner, val list: List<Repo>)
}