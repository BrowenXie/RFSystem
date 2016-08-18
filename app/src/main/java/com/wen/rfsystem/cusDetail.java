package com.wen.rfsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;

public class cusDetail extends AppCompatActivity {

    long pos;
    SFsysDAO dao;
    customer c;
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


        EditText cusnamed=(EditText)findViewById(R.id.cusnameEDText);
        cusnamed.setText(c.name);

        EditText cusreled=(EditText)findViewById(R.id.custelEDText);
        cusreled.setText(c.tel);

        EditText cussexed=(EditText)findViewById(R.id.cusexEDText);
        String sextext;
        if(c.sex==1)
        {sextext="先生";}
        else
        {sextext="小姐";}
        cussexed.setText(sextext);

      Log.d("VIP", String.valueOf(c.VIP));


        EditText cusVIPeditText=(EditText)findViewById(R.id.cusVIPeditText);
        cusVIPeditText.setText(String.valueOf(c.VIP));

        EditText cusaddreditText=(EditText)findViewById(R.id.cusaddreditText);
        cusaddreditText.setText(c.address);

        EditText cusBireditText=(EditText)findViewById(R.id.cusBireditText);
        cusBireditText.setText(c.birthday);

        EditText cusPSEDText=(EditText)findViewById(R.id.cusPSEDText);
        cusPSEDText.setText(c.PS);


        CheckBox cb=(CheckBox) findViewById(R.id.checkBox3);
        boolean a=(c.awkward == 1)? true : false;
        cb.setChecked(a);

        EditText cusawSeditText=(EditText)findViewById(R.id.cusawSeditText);
        cusawSeditText.setText(c.awkreason);





    }
}
