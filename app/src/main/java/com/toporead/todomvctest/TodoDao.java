package com.toporead.todomvctest;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TodoItem todo);

    @Query("DELETE FROM todo_table")
    void deleteAll();

    @Query("SELECT * from todo_table")
    LiveData<List<TodoItem>> getAllTodos();

    @Query("SELECT * from todo_table where complete = 1")
    LiveData<List<TodoItem>> getCompleteTodos();

    @Query("SELECT * from todo_table where complete = 0")
    LiveData<List<TodoItem>> getActiveTodos();

    @Delete
    void delete(TodoItem... todo);

    @Update
    void update(TodoItem... todo);
}
