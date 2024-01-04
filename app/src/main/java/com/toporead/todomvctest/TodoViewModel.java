package com.toporead.todomvctest;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class TodoViewModel extends AndroidViewModel {
    private final MutableLiveData<TodosState> currentFilter;
    private TodoRepository mRepository;
    private LiveData<List<TodoItem>> mTodoData;
    public TodoViewModel(@NonNull Application application) {
        super(application);
    }
}
