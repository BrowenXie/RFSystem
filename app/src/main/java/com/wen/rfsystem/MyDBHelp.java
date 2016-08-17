package com.wen.rfsystem;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Date;


public class MyDBHelp extends SQLiteOpenHelper {

    final static String DB_Name = "SFSdb.sqlite";
    final static int VERSION = 1;

    //CREATE customer SQL
    final static String CREATE_TABLE_SQLcus = " CREATE  TABLE customer ("+
                                            "_id INTEGER PRIMARY KEY  AUTOINCREMENT ,"+
                                            "name VARCHAR,"+
                                            "sex INTEGER,"+
                                            "awkward INTEGER,"+
                                            "awkreason VARCHAR,"+
                                            "VIP INTEGER,"+
                                            "birthday VARCHAR,"+
                                            "address VARCHAR,"+
                                            "tel VARCHAR,"+
                                            "PS VARCHAR)";

    //CREATE reserve SQL
    final static String CREATE_TABLE_SQLres = "CREATE  TABLE reserve ("+
                                            "_id INTEGER PRIMARY KEY  AUTOINCREMENT,"+  //UNIQUE?
                                            "customer INTEGER,"+    //用customer ID
                                            "adult INTEGER,"+
                                            "child INTEGER, "+
                                            "checkout INTEGER,"+      //BOOL?
                                            "checkin INTEGER, "+
                                            "reservetime  VARCHAR,"+
                                            "PS VARCHAR,"+
                                            "service VARCHAR)";


    //建構方法
   public MyDBHelp(Context context) {
        super(context,DB_Name, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DB", "cusDB is onCreate");
        db.execSQL(CREATE_TABLE_SQLcus);
        Log.d("DB", "resDB is onCreate");
        db.execSQL(CREATE_TABLE_SQLres);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
