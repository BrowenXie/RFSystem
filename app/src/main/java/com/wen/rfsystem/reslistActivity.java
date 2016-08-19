package com.wen.rfsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        reslv = (ListView) findViewById(R.id.listView2);

        /*
             for (reserve s : reslist)
        {
            resdisp.add(dao.checkcus(s.customer).tel);
        }

        resadapter = new ArrayAdapter<String>( reslistActivity.this,
                android.R.layout.simple_list_item_1,
                resdisp);
        reslv.setAdapter(resadapter);

         */


        //----------
        ArrayList<Map<String, String>> mylist2 = new ArrayList();

        mylist2.clear();
        for (reserve s : reslist)
        {
            HashMap<String, String> m1 = new HashMap();
            m1.put("name", dao.checkcus(s._id).name);
            m1.put("tel", dao.checkcus(s._id).tel);
            mylist2.add(m1);
        }

        SimpleAdapter adapter = new SimpleAdapter(reslistActivity.this,
                mylist2,
                android.R.layout.simple_list_item_2,
                new String[] {"name", "tel"},
                new int[] {android.R.id.text1, android.R.id.text2});
        reslv.setAdapter(adapter);

        //-------

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
