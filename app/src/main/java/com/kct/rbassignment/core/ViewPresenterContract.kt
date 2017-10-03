package com.kct.rbassignment.core

import io.reactivex.disposables.CompositeDisposable

/**
 * Created by krishan on 26/09/17.
 */
interface Screen

abstract class Presenter<T : Screen> {

    val compositeDisposable = CompositeDisposable()
    internal var view: T? = null

    fun bind(view: T) {
        this.view = view
    }

    fun unBind() {
        view = null
        compositeDisposable.clear()
    }

}