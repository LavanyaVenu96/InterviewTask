package com.example.task.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public int no;

    @ColumnInfo(name = "user_id")
    public Integer userId;

    @ColumnInfo(name = "user_name")
    public String userName;

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "gender")
    public String gender;

    @ColumnInfo(name = "status")
    public String status;

    @ColumnInfo(name = "comments")
    public String comments;



    public User(int no,int userId, String userName,String email,String gender,String status) {
        this.no = no;
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.gender = gender;
        this.status = status;
        this.comments = comments;
    }
}
