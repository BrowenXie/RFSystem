package com.wen.rfsystem;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class cuslistActive extends AppCompatActivity {
    ListView cuslv;
    ArrayList<String> cusdisp = new ArrayList<>();
    ArrayAdapter<String> cusadapter;
    List<customer> cuslist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuslist_active);


        // listview
        SFsysDAO dao = new SFsysDAOImp(cuslistActive.this);
        cuslist = dao.getAllcuserve();
        cuslv = (ListView) findViewById(R.id.listView3);


       /*
         for (customer s : cuslist)
        {
            cusdisp.add(s.name);
        }
        cusadapter = new ArrayAdapter<String>( cuslistActive.this,
                android.R.layout.simple_list_item_1,
                cusdisp);
        cuslv.setAdapter(cusadapter);
        */


        //----------
        ArrayList<Map<String, String>> mylist2 = new ArrayList();
        mylist2.clear();
        for (customer s : cuslist)
        {
            HashMap<String, String> m1 = new HashMap();
            m1.put("name", s.name);
            m1.put("tel", s.tel);
            mylist2.add(m1);
        }

        SimpleAdapter adapter = new SimpleAdapter(cuslistActive.this,
                mylist2,
                android.R.layout.simple_list_item_2,
                new String[] {"name", "tel"},
                new int[] {android.R.id.text1, android.R.id.text2});
        cuslv.setAdapter(adapter);

        //----------
        cuslv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(cuslistActive.this, cusDetail.class);
                it.putExtra("pos", cuslist.get(position)._id);

                Log.d("cuslist post:", String.valueOf(position));

                startActivity(it);
            }

        });




    }
}
