package com.wen.rfsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class cusDetail extends AppCompatActivity {

    long pos;
    SFsysDAO dao;
    customer c;
    EditText cusVIPeditText,cusaddreditText,cusBireditText,cusPSEDText,cusawSeditText,cusnamed,custelEDText,cussexed;
    CheckBox cb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_detail);

        Intent it = getIntent();
        pos = it.getLongExtra("pos", 0);
        //res _id
        Log.d("cusDetail","OK");
        Log.d("cusD_POS=", String.valueOf(pos));

 dao =new SFsysDAOImp(cusDetail.this);
        c=dao.checkcus(pos);


        cusnamed=(EditText)findViewById(R.id.cusnameEDText);
        cusnamed.setText(c.name);

        custelEDText=(EditText)findViewById(R.id.custelEDText);
        custelEDText.setText(c.tel);

        cussexed=(EditText)findViewById(R.id.cusexEDText);
        String sextext;
        if(c.sex==1)
        {sextext="先生";}
        else
        {sextext="小姐";}
        cussexed.setText(sextext);
        cusVIPeditText=(EditText)findViewById(R.id.cusVIPeditText);
        cusVIPeditText.setText(String.valueOf(c.VIP));
        cusaddreditText=(EditText)findViewById(R.id.cusaddreditText);
        cusaddreditText.setText(c.address);
        cusBireditText=(EditText)findViewById(R.id.cusBireditText);
        cusBireditText.setText(c.birthday);
        cusPSEDText=(EditText)findViewById(R.id.cusPSEDText);
        cusPSEDText.setText(c.PS);
        cb=(CheckBox) findViewById(R.id.checkBox3);
        boolean a=(c.awkward == 1)? true : false;
        cb.setChecked(a);
        cusawSeditText=(EditText)findViewById(R.id.cusawSeditText);
        cusawSeditText.setText(c.awkreason);

    }


    public void edit(View v){

        int sex = (String.valueOf(cussexed.getText()) =="先生")? 1 : 0;
        int awk = ( cb.isChecked() ==true)? 1 : 0;
        customer nc=new customer(sex,   //性別
                awk,   //黑名單
                String.valueOf(cusawSeditText.getText()),  //黑名單理由
                Integer.valueOf(cusVIPeditText.getText().toString()) ,   //VIP
                String.valueOf(cusnamed.getText()), //姓名
                String.valueOf(cusBireditText.getText()),   //生日
                String.valueOf(cusaddreditText.getText()),   //地址
                String.valueOf(custelEDText.getText()),//電話
                String.valueOf(cusPSEDText.getText())  //備註
        );
        nc._id=pos;
        Log.d("POS_ID", String.valueOf(pos));
        dao.cusupdata(nc);
    }

    public void del(View v){

        dao.cusdel(c);
        finish();
    }


}
