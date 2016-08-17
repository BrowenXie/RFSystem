package com.wen.rfsystem;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static java.lang.Integer.parseInt;

public class add extends AppCompatActivity {
    EditText DateeditText;
    private DatePickerDialog datePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);

        Calendar mCal = Calendar.getInstance();
        String dateformat = "yyyy/MM/dd";
        SimpleDateFormat df = new SimpleDateFormat(dateformat);
        String today = df.format(mCal.getTime());

        EditText DateeditText = (EditText) findViewById(R.id.DateeditText);
        DateeditText.setText(today);

    }


    public void click_add(View v)
    {
         EditText nameed = (EditText) findViewById(R.id.nameEDText);
         EditText adultEDText = (EditText) findViewById(R.id.adultEDText);
         EditText childEDText = (EditText) findViewById(R.id.childEDText);
         EditText tel = (EditText) findViewById(R.id.telEDText);
         EditText PS = (EditText) findViewById(R.id.PSEDText);

       /* SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date dt = null;
        try {
            dt = sdf.parse("2016/08/17 12:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
            Log.d("ERR","日期轉換錯誤~");
        }
*/


        SFsysDAO dao = new SFsysDAOImp(add.this);
        customer a=new customer(1,   //性別
                                0,   //黑名單
                                "",  //黑名單理由
                                0,   //VIP
                                nameed.getText().toString(), //姓名
                                "1985/03/01",   //生日
                                "",   //地址
                                tel.getText().toString(),//電話
                                PS.getText().toString()   //備註
        );
        Log.d("customer",a.toString());
        long cusid=dao.cusadd(a);
        Log.d("cusid", String.valueOf(cusid));


        reserve b = new reserve(
                                cusid,  //顧客編號
                                parseInt( adultEDText.getText().toString()),     //幾大
                                parseInt( childEDText.getText().toString()),//幾小  parseInt( childEDText.getText().toString()) ,
                                false,// 已離開
                                false,// 已進入
                                "2016/08/17 12:30", //訂位時間
                                PS.getText().toString(), //備註
                                "LO"  //訂位輸入人員
        );

        Log.d("reserve",b.toString());
        long resid =dao.resadd(b);
        Log.d("resid", String.valueOf(resid));

    }
/*
 public void setDate(View v){

        GregorianCalendar calendar = new GregorianCalendar();
        DateeditText = (EditText) findViewById(R.id.DateeditText);

        Calendar mCal = Calendar.getInstance();
        String dateformat = "yyyy/MM/dd";
        SimpleDateFormat df = new SimpleDateFormat(dateformat);

        // 實作DatePickerDialog的onDateSet方法，設定日期後將所設定的日期show在textDate上
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            //將設定的日期顯示出來


            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                DateeditText.setText(year + "/" + (monthOfYear+1) + "/" + dayOfMonth);
            }
        },calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH));

    }
 */


    public void cancel(View v){

        finish();

    }


}

