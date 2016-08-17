package com.wen.rfsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class resDetail extends AppCompatActivity {

    int pos;
    SFsysDAO dao;
    reserve r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_detail);
        Log.d("resDetail","1");
        Intent it = getIntent();
        pos = it.getIntExtra("pos", 0);
        //res _id
        Log.d("resDetail","2");
        dao =new SFsysDAOImp(resDetail.this);
         r=dao.checkres(pos);


    }

    public void click_update(View v)
    {

    }

    public void click_del(View v)
    {
        dao.resdel(r);
        finish();
    }

}
