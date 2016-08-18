package com.wen.rfsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class resDetail extends AppCompatActivity {

    long pos;
    SFsysDAO dao;
    reserve r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_detail);
        Log.d("resDetail","into");
        Intent it = getIntent();
        pos = it.getLongExtra("pos", 0);
        //res _id
        Log.d("resDetail","2");
        dao =new SFsysDAOImp(resDetail.this);
        r=dao.checkres(pos);
        customer c=dao.checkcus(r.customer);


        EditText named = (EditText) findViewById(R.id.nameEDText);//請輸入姓名
        named.setText(c.name);

        EditText tel = (EditText) findViewById(R.id.telEDText);//請輸入連絡電話
        tel.setText(c.tel);

        EditText adult = (EditText) findViewById(R.id.adultEDText);//大人人數
        adult.setText(r.adult + "");

        EditText child = (EditText) findViewById(R.id.childEDText);//小孩人數
        child.setText(r.child + "");

        EditText Dateedit = (EditText) findViewById(R.id.DateeditText);//請輸入預約日期
        Dateedit.setText(r.reservetime.substring(0, 4) + "/" + r.reservetime.substring(5, 6) + "/" + r.reservetime.substring(7, 8));


        EditText psed = (EditText) findViewById(R.id.PSEDText);//請輸入備註
        psed.setText(r.PS + "");

        EditText service = (EditText) findViewById(R.id.editText);//
        service.setText(r.service+ "");








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
