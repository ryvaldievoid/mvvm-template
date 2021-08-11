package com.company_name.app_name.data.source.local.movie

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.company_name.app_name.data.model.Movie
import io.reactivex.Single

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie WHERE id = :id")
    fun getMovieById(id: Int): Movie

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMovies(movies: List<Movie>)

    @Query("DELETE FROM movie")
    fun deleteAllHeroes()

    @Query("SELECT * FROM movie")
    fun getMovieList(): Single<List<Movie>>

    @Query("SELECT * FROM movie")
    fun getAllMovies(): List<Movie>

}