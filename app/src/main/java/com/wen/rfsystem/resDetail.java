package com.wen.rfsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import static java.lang.Integer.parseInt;

public class resDetail extends AppCompatActivity {

    long pos;
    SFsysDAO dao;
    reserve r;
    customer c;
    EditText named,tel,adult,child,Dateedit,psed,service,Dateedit1;
    CheckBox checkBox,checkBox2;

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
        c=dao.checkcus(r.customer);


        named = (EditText) findViewById(R.id.nameEDText);//請輸入姓名
        named.setText(c.name);
        tel = (EditText) findViewById(R.id.telEDText);//請輸入連絡電話
        tel.setText(c.tel);
        adult = (EditText) findViewById(R.id.adultEDText);//大人人數
        adult.setText(r.adult + "");
        child = (EditText) findViewById(R.id.childEDText);//小孩人數
        child.setText(r.child + "");

        Log.d("time",r.reservetime.toString());
        Dateedit = (EditText) findViewById(R.id.DateeditText);//請輸入預約日期
        Dateedit.setText(r.reservetime.substring(0, 4) + "/" + r.reservetime.substring(4, 6) + "/" + r.reservetime.substring(6, 8));

        Dateedit1 = (EditText) findViewById(R.id.TIMEeditText);
        Dateedit1.setText(r. reservetime.substring(8,10) +":"+ r. reservetime.substring(10,12));

        psed = (EditText) findViewById(R.id.PSEDText);//請輸入備註
        psed.setText(r.PS + "");
        service = (EditText) findViewById(R.id.editText);//
        service.setText(r.service+ "");
        checkBox=(CheckBox)findViewById(R.id.checkBox);
        checkBox.setChecked(r.checkin);

        checkBox2=(CheckBox)findViewById(R.id.checkBox2);
        checkBox2.setChecked(r.checkout);

    }

    public void click_update(View v)
    {
        SFsysDAO dao = new SFsysDAOImp(resDetail.this);
        c.name=named.getText().toString();
        c.tel=tel.getText().toString();

        dao.cusupdata(c);

       String Datetime=Dateedit.getText().toString()+Dateedit1.getText().toString();
        Datetime=Datetime.replace("/","");
        Datetime=Datetime.replace(":","");

        reserve b = new reserve(
                c._id,  //顧客編號
                parseInt(adult.getText().toString()),     //幾大
                parseInt(child.getText().toString()),//幾小  parseInt( childEDText.getText().toString()) ,
                checkBox2.isChecked(),// 已離開
                checkBox.isChecked(),// 已進入
                Datetime, //訂位時間
                psed.getText().toString(), //備註
                service.getText().toString() //訂位輸入人員
        );

        b._id=pos;

        dao.resupdata(b);
        finish();




    }

    public void click_del(View v)
    {
        dao.resdel(r);
        finish();
    }

}
