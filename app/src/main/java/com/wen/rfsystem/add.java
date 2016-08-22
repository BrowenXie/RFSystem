package com.wen.rfsystem;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static java.lang.Integer.parseInt;

public class add extends AppCompatActivity {
    TextView DateeditText,TIMEeditText;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);

        Calendar mCal = Calendar.getInstance();
        String dateformat = "yyyy/MM/dd";
        SimpleDateFormat df = new SimpleDateFormat(dateformat);
        String today = df.format(mCal.getTime());
        Log.d("TODAY",today);
        DateeditText = (TextView) findViewById(R.id.DateeditText);
        DateeditText.setText(today);


        String dateformatTIME = "HH:mm";
        SimpleDateFormat dft = new SimpleDateFormat(dateformatTIME);
        String nowtime = dft.format(mCal.getTime());
        final TextView TIMEeditText = (TextView) findViewById(R.id.TIMEeditText);
        TIMEeditText.setText(nowtime);


        GregorianCalendar calendar = new GregorianCalendar();
        // 實作TimePickerDialog的onTimeSet方法，設定時間後將所設定的時間show在textTime上
        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            //將時間轉為12小時製顯示出來
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                TIMEeditText.setText(hourOfDay  + ":" + minute );
                /*
                 TIMEeditText.setText((hourOfDay > 12 ? hourOfDay - 12 : hourOfDay)
                        + ":" + minute + " " + (hourOfDay > 12 ? "PM" : "AM"));
                 */
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(calendar.MINUTE),
                false);


    }


    public void click_add(View v)
    {
         EditText nameed = (EditText) findViewById(R.id.nameEDText);
         EditText adultEDText = (EditText) findViewById(R.id.adultEDText);
         EditText childEDText = (EditText) findViewById(R.id.childEDText);
         EditText tel = (EditText) findViewById(R.id.telEDText);
         EditText PS = (EditText) findViewById(R.id.PSEDText);

         DateeditText = (TextView) findViewById(R.id.DateeditText);
         TIMEeditText = (TextView) findViewById(R.id.TIMEeditText);


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
                                "19850301",   //生日
                                "",   //地址
                                tel.getText().toString(),//電話
                                PS.getText().toString()   //備註
        );
        Log.d("customer",a.toString());
        long cusid=dao.cusadd(a);
        Log.d("cusid", String.valueOf(cusid));



        String date=DateeditText.getText().toString();
        date=date.replace("/","");
        Log.d("date",date);
        String time=TIMEeditText.getText().toString();
        time=time.replace(":","");
        Log.d("TIME",time);

        String ads=null,chs=null;
        int adu,chi;
        ads=adultEDText.getText().toString();
        chs=childEDText.getText().toString();
        Log.d("adu=",ads);
        Log.d("adu=",chs);
        if ( isEmpty(ads))
        {
            adu=0;
        }else {
            adu=parseInt(ads);
        }

        if ( isEmpty(chs))
        {
            chi=0;
        }else {
            chi=parseInt( chs);
        }



        reserve b = new reserve(
                                cusid,  //顧客編號
                                adu,     //幾大
                                chi,    //幾小  parseInt( childEDText.getText().toString()) ,
                                false,// 已離開
                                false,// 已進入
                                date+time, //訂位時間
                                PS.getText().toString(), //備註
                                "LO"  //訂位輸入人員
        );

        Log.d("reserve",b.toString());
        long resid =dao.resadd(b);
        Log.d("resid", String.valueOf(resid));

        finish();


    }

 public void setDate(View v){

        GregorianCalendar calendar = new GregorianCalendar();
        DateeditText = (TextView) findViewById(R.id.DateeditText);

        Calendar mCal = Calendar.getInstance();
        String dateformat = "yyyy/MM/dd";
        SimpleDateFormat df = new SimpleDateFormat(dateformat);

        // 實作DatePickerDialog的onDateSet方法，設定日期後將所設定的日期show在textDate上
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                DateeditText.setText(year + "/" + (monthOfYear+1) + "/" + dayOfMonth);
            }
        },calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    // setTime Button onClick 顯示時間設定視窗
    public void setTime(View v) {
        timePickerDialog.show();
    }

    public void cancel(View v){

        finish();

    }


    public static boolean isEmpty(String str) {

        if (str == null)
            return true;
        else if (str.toString().trim().length() == 0)
            return true;

        return false;
    }

}

