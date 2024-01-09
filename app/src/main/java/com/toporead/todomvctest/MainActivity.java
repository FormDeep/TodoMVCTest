package com.toporead.todomvctest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TodosAdapter.OnUpdateTodoClickListener{
    ArrayList<TodoItem> todoItems;
    ArrayAdapter<TodoItem> aTodoAdapter;
    private TodosAdapter mAdapter;
    private TextView todoInput;
    private int REQUEST_CODE =20;
    private Button allButton;
    private Button activeButton;
    private Button completeButton;
    private TodoViewModel mTodoViewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        todoInput = findViewById(R.id.editText);
        allButton = findViewById(R.id.all_button);
        activeButton = findViewById(R.id.active_button);
        completeButton = findViewById(R.id.complete_button);
        todoInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                v.clearFocus();
                addTodo(v);
                return false;
            }
        });
        RecyclerView mRecyclerView =findViewById(R.id.todosView);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter =new TodosAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        aTodoAdapter = new ArrayAdapter<>(this,android.R.layout.list_content,todoItems);
        this.initRecyclerView();
        mTodoViewModel = new ViewModelProvider(this).get(TodoViewModel.class);
        mTodoViewModel.getTodos().observe(this, new Observer<List<TodoItem>>() {
            @Override
            public void onChanged(@NonNull final List<TodoItem> todos) {
                todoItems =new ArrayList<>();
                todoItems.addAll(todos);
                mAdapter.setTodos(todoItems);
            }
        });
    }

    private void initRecyclerView(){
        RecyclerView mRecyclerView = findViewById(R.id.todosView);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        todoItems = new ArrayList<>();
        mAdapter =new TodosAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        aTodoAdapter =new ArrayAdapter<>(this, android.R.layout.list_content,todoItems);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && data != null) {
            int position = data.getExtras().getInt("position", -1);
            if (position >= 0) {
                String todo_text = data.getExtras().getString("todo_text");
                TodoItem todo = todoItems.get(position);
                todo.setText(todo_text);
                mTodoViewModel.update(todo);
            }
        }
    }

    @Override
    public void onDeleteTodoClicked(TodoItem todo) {
        mTodoViewModel.delete(todo);
    }

    @Override
    public void onToggleCompleteTodoClicked(TodoItem todo) {
        mTodoViewModel.update(todo);
    }

    public void addTodo(View view){
        Editable todoTextField = (Editable) todoInput.getText();
        String todoText = todoTextField.toString();
        if(todoText.length() > 0){
            TodoItem todo = new TodoItem(todoText);
            mTodoViewModel.insert(todo);
            todoTextField.clear();
        }
    }

    public void showAll(View v){
        v.setBackgroundResource(R.drawable.active_button_background);
        int color = ContextCompat.getColor(this, android.R.color.transparent);
        completeButton.setBackgroundColor(color);
        activeButton.setBackgroundColor(color);
        mTodoViewModel.setCurrentFilter(TodosState.ALL);
    }

    public void showComplete(View v){
        v.setBackgroundResource(R.drawable.active_button_background);
        int color = ContextCompat.getColor(this, android.R.color.transparent);
        activeButton.setBackgroundColor(color);
        allButton.setBackgroundColor(color);
        mTodoViewModel.setCurrentFilter(TodosState.COMPLETED);
    }

    public void showActive(View v){
        v.setBackgroundResource(R.drawable.active_button_background);
        int color = ContextCompat.getColor(this, android.R.color.transparent);
        completeButton.setBackgroundColor(color);
        allButton.setBackgroundColor(color);
        mTodoViewModel.setCurrentFilter(TodosState.ACTIVE);

    }
}