package com.toporead.todomvctest;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {TodoItem.class},version = 1,exportSchema = false)

public abstract class TodoRoomDatabase extends RoomDatabase {
    public abstract TodoDao todoDao();
    private static TodoRoomDatabase INSTANCE;
    static TodoRoomDatabase getDatebase(final Context context){
        if (INSTANCE == null){
            synchronized (RoomDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),TodoRoomDatabase.class,"todo_database").build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomDatebaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            // If you want to keep the data through app restarts,
            // comment out the following line.
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void,Void,Void>{
        private TodoDao mDao;
        PopulateDbAsync(TodoRoomDatabase db){
            mDao =db.todoDao();
        }

        @Override
        protected Void doInBackground(final Void... voids) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            mDao.deleteAll();
            TodoItem todo =new TodoItem("hello");
            mDao.insert(todo);
            todo =new TodoItem("world");
            mDao.insert(todo);
            return null;
        }
    }
}
