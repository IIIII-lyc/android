package com.fzu.campusnews;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelper extends SQLiteOpenHelper {
    public MyHelper(Context context)
    {
        super(context,"com.fzu.campusNews.db",null,2);
    }

    //数据库第一次被创建时调用
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create TABLE information(_id INTEGER PRIMARY KEY AUTOINCREMENT,account VARCHAR(20),password VARCHAR(20))");
    }

    //增加版本号时调用
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
