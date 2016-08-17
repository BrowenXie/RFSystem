package com.wen.rfsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

    }


    public void tojason(View v){
        SFsysDAO dao= new SFsysDAOImp(setting.this);
        dao.jasonsave(setting.this);
    }


    public void fromjason(View v){
        SFsysDAO dao= new SFsysDAOImp(setting.this);
        dao.jasonload(setting.this);
    }


}
