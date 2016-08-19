package com.wen.rfsystem;
/**
* 訂位系統專題  博文
 *
* */
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity{

    ArrayList<String> disp = new ArrayList<>();
    ListView lv;
    ArrayAdapter<String> adapter;
    TextView textDate;
    private DatePickerDialog datePickerDialog;
    List<reserve> mylist=null;
    String today;String today2;

    public MainActivity() {
        super();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GregorianCalendar calendar = new GregorianCalendar();
         textDate = (TextView) findViewById(R.id.datetext);

        //今天日期
        Calendar mCal = Calendar.getInstance();
        String dateformat = "yyyy/MM/dd";
        SimpleDateFormat df = new SimpleDateFormat(dateformat);
        today = df.format(mCal.getTime());
        textDate.setText(today);

        String dateformatNOF = "yyyyMMdd";
        SimpleDateFormat df2 = new SimpleDateFormat(dateformatNOF);
        today2 = df2.format(mCal.getTime());


        // 實作DatePickerDialog的onDateSet方法，設定日期後將所設定的日期show在textDate上
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            //將設定的日期顯示出來
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
        textDate.setText(year + "/" + (monthOfYear+1) + "/" + dayOfMonth);

                String mm; int m=monthOfYear+1;
                if(m<10) { mm="0"+m; }
                else {mm= String.valueOf(m); }

                String dd; int d=monthOfYear+1;
                if(d<10)  {dd="0"+d;}
                else {dd= String.valueOf(d); }

                today2= ""+year+ mm + dayOfMonth;
                onResume();

                Log.d("today2",today2);
            }
        },calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
         calendar.get(Calendar.DAY_OF_MONTH));


    }



    @Override
    protected void onResume() {

        //-----Received功能測試-------
        Calendar cal = Calendar.getInstance();

        cal.add(Calendar.SECOND, 3);
        Intent intent = new Intent(this, MyReceiver.class);
        intent.putExtra("msg", "my_alarm_action");
        PendingIntent pi = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pi);

        //------Received功能結束-----


        Log.d("INTO","onResume");
        Log.d("toady2",today2);
        super.onResume();


        lv = (ListView) findViewById(R.id.listView);
        SFsysDAO dao = new SFsysDAOImp(MainActivity.this);
        mylist = dao.getadayreserve(today2);

        // ArrayAdapter listview
        /*
                // mylist = dao.getAllreserve();
        disp.clear();
        for (reserve s : mylist)
        {
            disp.add(dao.checkcus(s._id).tel);
        }
        adapter = new ArrayAdapter<String>( MainActivity.this,
                android.R.layout.simple_list_item_1,
                disp);

        lv.setAdapter(adapter);
         */

        //---------SimpleAdapter --------
        ArrayList<Map<String, String>> mylist2 = new ArrayList();

        mylist2.clear();
        for (reserve s : mylist)
        {
            HashMap<String, String> m1 = new HashMap();
            m1.put("name", dao.checkcus(s._id).name);
            m1.put("tel", dao.checkcus(s._id).tel);
            mylist2.add(m1);
        }

        SimpleAdapter adapter = new SimpleAdapter(MainActivity.this,
                mylist2,
                android.R.layout.simple_list_item_2,
                new String[] {"name", "tel"},
                new int[] {android.R.id.text1, android.R.id.text2});
        lv.setAdapter(adapter);

        //----------------
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(MainActivity.this, resDetail.class);
                Log.d("position=", String.valueOf(position));
                it.putExtra("pos", mylist.get(position)._id);
                startActivity(it);
            }

        });

        adapter.notifyDataSetChanged();
        /*
              //mylist = dao.getAllreserve();
        disp.clear();
        for (reserve s :mylist)
        {
            disp.add(dao.checkcus(s._id).tel);
        }
        adapter.notifyDataSetChanged();

*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add)
        {
            Intent it = new Intent(MainActivity.this, add.class);
            startActivity(it);
        }
        return super.onOptionsItemSelected(item);
    }


    public void setDate(View v) {
        datePickerDialog.show();
    }


    public void goRes(View v){
        Intent it = new Intent(MainActivity.this, reslistActivity.class);
        startActivity(it);
    }

    public void goCus(View v){
        Intent it = new Intent(MainActivity.this, cuslistActive.class);
        startActivity(it);
    }

    public void goSet(View v){
        Intent it = new Intent(MainActivity.this, setting.class);
        startActivity(it);
    }
    public void goAdd(View v){
        Intent it = new Intent(MainActivity.this, add.class);
        startActivity(it);
    }


}




