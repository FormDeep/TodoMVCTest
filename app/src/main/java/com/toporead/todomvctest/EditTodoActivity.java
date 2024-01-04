package com.toporead.todomvctest;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.IllegalFormatCodePointException;
import java.util.Locale;

public class EditTodoActivity extends AppCompatActivity {
    EditText editTodoItem;
    Button saveChange;
    int position;
    boolean textChanged =false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_todo);
        Intent intent = getIntent();
        editTodoItem =findViewById(R.id.editTodoText);
        saveChange =findViewById(R.id.saveChanges);
        final String todo_text =intent.getStringExtra("todo_text");
        position =intent.getIntExtra("position",0);
        long updateDateRaw =intent.getLongExtra("update_date",0);
        long creatDateRaw =intent.getLongExtra("creat_date",0);
        TextView updateDateView =findViewById(R.id.updateDate);
        TextView createDateView =findViewById(R.id.createDate);
        updateDateView.setText(buildDateString(R.string.last_updated,updateDateRaw));
        createDateView.setText(buildDateString(R.string.create_date,creatDateRaw));
        editTodoItem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (!textChanged && s!=todo_text){
                    textChanged =true;
                    changeButtonText();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private String buildDateString(int resource ,long dateRaw){
        if (dateRaw == 0){
            return "";
        }
        Date date =new Date(dateRaw);
        Format formatter = new SimpleDateFormat("MMM dd,yyyy - h:mma", Locale.US);
        return getResources().getString(resource,formatter.format(date));
    }
    public void changeButtonText(){
        saveChange.setText(R.string.save);
    }
    public void onSubmit(View view){
        view.clearFocus();
        String todo_text =editTodoItem.getText().toString();
        if (todo_text.length()>0){
            Intent data = new Intent();
            data.putExtra("todo_text",todo_text);
            data.putExtra("position",position);
            setResult(RESULT_OK,data);
            finish();
        }
    }
}
