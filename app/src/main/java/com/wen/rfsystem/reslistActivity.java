package com.wen.rfsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class reslistActivity extends AppCompatActivity {
    ListView reslv;
    ArrayList<String> resdisp = new ArrayList<>();
    ArrayAdapter<String> resadapter;
    List<reserve> reslist;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reslist);


        // listview
        SFsysDAO dao = new SFsysDAOImp(reslistActivity.this);

        reslist = dao.getAllreserve();
        for (reserve s : reslist)
        {
            resdisp.add(dao.checkcus(s.customer).tel);
        }

        resadapter = new ArrayAdapter<String>( reslistActivity.this,
                android.R.layout.simple_list_item_1,
                resdisp);

        reslv = (ListView) findViewById(R.id.listView2);
        reslv.setAdapter(resadapter);


                reslv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(reslistActivity.this, resDetail.class);
                it.putExtra("pos", reslist.get(position)._id);
                startActivity(it);
            }

        });






    }
}
