package com.toporead.todomvctest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.room.TypeConverters;

import java.util.ArrayList;

public class TodosAdapter extends RecyclerView.Adapter<TodosAdapter.MyViewHolder> {
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
            completedTodo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (onUpdateTodoClickListener!=null){
                        todo.setIsComplete(isChecked);
                        onUpdateTodoClickListener.onToggleCompleteTodoClicked(todo);
                    }
                }
            });
            todoText.setText(todo.getText());
        }
    }
    public TodosAdapter(OnUpdateTodoClickListener listener){
        this.onUpdateTodoClickListener =listener;
    }
    @Override
    public TodosAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item_view,parent,false);
        MyViewHolder viewHolder =new MyViewHolder(v);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(final TodosAdapter.MyViewHolder holder,final int position){
        TodoItem todo =mDataSet.get(position);
        holder.bind(todo);
        holder.completedTodo.setChecked(todo.getIsComplete());
        holder.todoText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                int position =holder.getAdapterPosition();
                TodoItem todo =mDataSet.get(position);
                Intent i = new Intent(context, EditTodoActivity.class);
                i.putExtra("todo_text",todo.getText());
                i.putExtra("position",position);
                i.putExtra("create_data",todo.getCreateDate().getTime());
                i.putExtra("update_data",todo.getLastUpdated().getTime());
                ((Activity)context).startActivityForResult(i,REQUEST_CODE);
            }
        });
    }
    public void setTodos(ArrayList<TodoItem> todos){
        mDataSet =todos;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount(){
        if (mDataSet == null){
            return 0;
        }
        return mDataSet.size();
    }
}
