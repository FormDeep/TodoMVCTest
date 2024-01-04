package com.toporead.todomvctest;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.room.Entity;

import androidx.recyclerview.widget.RecyclerView;
import androidx.room.TypeConverters;

import java.util.ArrayList;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.MyViewHolder> {
    public interface OnUpdateTodoClickListener{
        void onDeleteTodoClicked(TodoItem todo);
        void onToggleCompleteTodoClicked(TodoItem todo);
    }

    @TypeConverters(DateConverter.class)
    private ArrayList<TodoItem> mDataSet;
    private int REQUEST_CODE =20;
    private OnUpdateTodoClickListener onUpdateTodoClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        View mTodoItem ;
        Button removeTodo;
        TextView todoText;
        CheckBox completedTodo;
        private MyViewHolder(View v){
            super(v);
            mTodoItem = v;
            removeTodo =v.findViewById(R.id.removeTodo);
        }
        void bind(final TodoItem todo){
            removeTodo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onUpdateTodoClickListener!=null){
                        onUpdateTodoClickListener.onDeleteTodoClicked(todo);
                    }
                }
            });

        }
    }
}
