package com.toporead.todomvctest;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;
import java.util.UUID;

@Entity(tableName = "todo_table")
@TypeConverters(DateConverter.class)

public class TodoItem {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private String mId;

    @ColumnInfo
    private String text;

    @ColumnInfo(name = "complete")

    private Boolean isConplete;
    @ColumnInfo
    private Date lastUpdated;

    @ColumnInfo
    private Date creatDate;

    @Ignore
    public TodoItem(String text){
        this.mId = UUID.randomUUID().toString();
        this.text =text;
        this.isConplete =false;
        this.creatDate = new Date();
        this.lastUpdated =creatDate;
    }

    public TodoItem(String id, String text, Boolean complete, Date lastUpdated, Date createDate){
        this.mId =id;
        this.text =text;
        this.isConplete =complete;
        this.lastUpdated =lastUpdated;
        this.creatDate =createDate;
    }

    @NonNull
    public String getMId(){
        return this.mId;
    }
    public void setmId(@NonNull String id){

    }


}
