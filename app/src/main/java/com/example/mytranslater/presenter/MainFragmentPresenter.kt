package com.example.mytranslater.presenter


import com.example.mytranslater.model.state.AppState
import com.example.mytranslater.contract.ContractMainFragment
import com.example.mytranslater.model.datasource.retrofit.datasource.LocalDataSource
import com.example.mytranslater.model.datasource.retrofit.datasource.RemoteDataSource
import com.example.mytranslater.model.repository.RepoWordImpl
import geekbrains.ru.translator.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class MainFragmentPresenter<T : AppState, V : ContractMainFragment.View>(
    private val interactor: MainFaragmentInteractor = MainFaragmentInteractor(
        RepoWordImpl(RemoteDataSource(), LocalDataSource())
    ),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    protected val schedulerProvider: SchedulerProvider = SchedulerProvider()
) :
    ContractMainFragment.Presenter<T, V> {

    private var currentView: V? = null
    override fun attachView(view: V) {
        if (view != currentView) {
            currentView = view
        }
    }

    override fun detachView(view: V) {
        compositeDisposable.clear()
        if (view == currentView) {
            currentView == null
        }
    }

    override fun getData(word: String, isOnline: Boolean) {
        currentView?.renderData(AppState.Loading(1))

        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeWith(getObserver())
        )
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {
            override fun onNext(t: AppState) {
                currentView?.renderData(t)
            }

            override fun onError(e: Throwable) {
                currentView?.renderData(AppState.Error(e))
            }

            override fun onComplete() {}

        }
    }
}