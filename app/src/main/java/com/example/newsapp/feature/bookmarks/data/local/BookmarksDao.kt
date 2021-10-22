package com.example.newsapp.feature.bookmarks.data.local

import androidx.room.*

@Dao
interface BookmarksDao {

    @Query("SELECT * FROM ${BookmarkEntity.TABLE_NAME}")
    suspend fun getAll(): List<BookmarkEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(bookmarks: List<BookmarkEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bookmark: BookmarkEntity)

    @Update
    suspend fun update(bookmark: BookmarkEntity)

    @Delete
    suspend fun delete(bookmark: BookmarkEntity)
}