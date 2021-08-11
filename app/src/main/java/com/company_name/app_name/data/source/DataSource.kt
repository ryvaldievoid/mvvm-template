package com.company_name.app_name.data.source

import com.company_name.app_name.base.BaseDataSource
import com.company_name.app_name.base.BaseDataSource.GitsResponseCallback
import com.company_name.app_name.data.model.Movie

/**
 * Created by irfanirawansukirman on 26/01/18.
 */

interface DataSource: BaseDataSource {

    fun getMovies(callback: GetMoviesCallback)

    fun saveMovie(movie: List<Movie>)

    interface GetMoviesCallback : GitsResponseCallback<List<Movie>>

    interface GetMoviesByIdCallback : GitsResponseCallback<Movie>

}