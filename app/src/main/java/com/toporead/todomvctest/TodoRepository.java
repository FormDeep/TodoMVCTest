package com.toporead.todomvctest;


import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import java.util.List;


public class TodoRepository {
    ///1111111111111
    private TodoDao mTodoDao;
    private LiveData<List<TodoItem>>mAllTodos;
    private LiveData<List<TodoItem>>mActiveTodos;
    private LiveData<List<TodoItem>>mCompleteTodos;
    TodoRepository(Application application){
        TodoRoomDatabase db =TodoRoomDatabase.getDatebase(application);
        mTodoDao = db.todoDao();
        mAllTodos =mTodoDao.getAllTodos();
        mActiveTodos =mTodoDao.getActiveTodos();
        mCompleteTodos =mTodoDao.getCompleteTodos();
    }
    public LiveData<List<TodoItem>>getAllTodos(){
        return this.mAllTodos;
    }
    public LiveData<List<TodoItem>>getActiveTodos(){
        return this.mActiveTodos;
    }
    public LiveData<List<TodoItem>>getCompleteTodos(){
        return this.mCompleteTodos;
    }

    public void insert(TodoItem todo){
        new insertAsyncTask(mTodoDao).execute(todo);
    }
    public void update(TodoItem todo) {
        new updateAsyncTask(mTodoDao).execute(todo);
    }
    public void delete(TodoItem todo) {
        new deleteAsyncTask(mTodoDao).execute(todo);
    }

    private static class insertAsyncTask extends AsyncTask<TodoItem, Void,Void>{
      private TodoDao mAsyncTaskDao;
      insertAsyncTask(TodoDao dao){
          mAsyncTaskDao =dao;
      }
      protected Void doInBackground(final TodoItem...params){
          mAsyncTaskDao.insert(params[0]);
          return null;
      }
    }
    private static class updateAsyncTask extends AsyncTask<TodoItem, Void, Void> {

        private TodoDao mAsyncTaskDao;
        updateAsyncTask(TodoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final TodoItem... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<TodoItem, Void, Void> {

        private TodoDao mAsyncTaskDao;
        deleteAsyncTask(TodoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final TodoItem... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}
