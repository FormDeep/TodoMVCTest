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

    private Boolean isComplete;

    @ColumnInfo
    private Date lastUpdated;
    @ColumnInfo
    private Date createDate;

    @Ignore
    public TodoItem(String text){
        this.mId = UUID.randomUUID().toString();
        this.text =text;
        this.isComplete =false;
        this.createDate = new Date();
        this.lastUpdated =createDate;
    }

    public TodoItem (String mId, String text, Boolean isComplete, Date lastUpdated, Date createDate){
        this.mId =mId;
        this.text =text;
        this.isComplete =isComplete;
        this.lastUpdated =lastUpdated;
        this.createDate =createDate;
    }

    @NonNull
    public String getMId(){
        return this.mId;
    }
    public void setMId(@NonNull String mId){
        this.mId =mId;
    }

    public Boolean getIsComplete() {
        return this.isComplete;
    }

    public void setIsComplete(Boolean complete) {
        this.isComplete = complete;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getLastUpdated() {
        return this.lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreatDate(Date createDate) {
        this.createDate = createDate;
    }
}
