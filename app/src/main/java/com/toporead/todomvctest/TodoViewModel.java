package com.toporead.todomvctest;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.*;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import java.util.Date;
import java.util.List;
import java.util.function.Function;



public class TodoViewModel extends AndroidViewModel {
    private final MutableLiveData<TodosState> currentFilter;
    private TodoRepository mRepository;
    private  LiveData<List<TodoItem>> mTodoData;

    public TodoViewModel (Application application) {
        super(application);
        this.currentFilter =new MutableLiveData<>();
        mTodoData = Transformations.switchMap(currentFilter, input -> {
            if (input.equals(TodosState.ACTIVE)){
                return mRepository.getActiveTodos();
            } else if (input.equals(TodosState.COMPLETED)) {
                return mRepository.getCompleteTodos();
            } else {
                return mRepository.getAllTodos();
            }
        });
    }
    LiveData<List<TodoItem>> getTodos() { return mTodoData; }

    public void setCurrentFilter(TodosState nextFilter){
        if(nextFilter != null && nextFilter != currentFilter.getValue()){
            this.currentFilter.setValue(nextFilter);
        }
    }
    public void insert(TodoItem todo) { mRepository.insert(todo); }
    public void update(TodoItem todo) {
        todo.setLastUpdated(new Date());
        mRepository.update(todo);
    }
    public void delete(TodoItem todo) { mRepository.delete(todo); }
}