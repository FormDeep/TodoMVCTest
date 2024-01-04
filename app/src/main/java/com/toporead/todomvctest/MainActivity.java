package com.toporead.todomvctest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TodoAdapter.OnUpdateTodoClickListener{
    ArrayList<TodoItem> todoItems;
    ArrayAdapter<TodoItem> aTodoAdapter;
    private TodoAdapter mAdapter;
    private TextView todoInput;
    private int REQUEST_CODE =20;
    private Button allButton;
    private Button activeButton;
    private Button completeButton;
    private TodoViewModel mTodoViewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDeleteTodoClicked(TodoItem todo) {

    }

    @Override
    public void onToggleCompleteTodoClicked(TodoItem todo) {

    }
}