package com.company_name.app_name.data.source

import com.company_name.app_name.data.model.Movie

/**
 * Created by irfanirawansukirman on 26/01/18.
 */

open class Repository(private val remoteDataSource: DataSource,
                      private val localDataSource: DataSource) : DataSource {

    override fun onClearDisposables() {
        remoteDataSource.onClearDisposables()
        localDataSource.onClearDisposables()
    }

    override fun saveMovie(movie: List<Movie>) {
        localDataSource.saveMovie(movie)
    }

    override fun getMovies(callback: DataSource.GetMoviesCallback) {
        remoteDataSource.getMovies(object : DataSource.GetMoviesCallback {
            override fun onShowProgressDialog() {

            }

            override fun onHideProgressDialog() {

            }

            override fun onSuccess(data: List<Movie>) {
                saveMovie(data)
                loadMovies(callback)
            }

            override fun onFinish() {
                callback.onFinish()
            }

            override fun onFailed(statusCode: Int, errorMessage: String?) {
                callback.onFailed(statusCode, errorMessage)
            }
        })
    }

    private fun loadMovies(callback: DataSource.GetMoviesCallback) {
        localDataSource.getMovies(object : DataSource.GetMoviesCallback {
            override fun onShowProgressDialog() {

            }

            override fun onHideProgressDialog() {

            }

            override fun onSuccess(data: List<Movie>) {
                callback.onSuccess(data)
            }

            override fun onFinish() {
                callback.onFinish()
            }

            override fun onFailed(statusCode: Int, errorMessage: String?) {
                callback.onFailed(statusCode, errorMessage)
            }
        })
    }

}