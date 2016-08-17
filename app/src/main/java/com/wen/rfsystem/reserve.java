package com.wen.rfsystem;

import java.util.Date;
public class reserve {

//建構
    public reserve(
            long customer,
            int adult,
            int child,
            boolean checkout,
            boolean checkin,
            String reservetime,
            String PS,
            String service
    ){

        this.customer=customer;
        this.adult=adult;
        this.child=child;
        this.checkout=checkout;
        this.checkin=checkin;
        this.reservetime=reservetime;
        this.PS=PS;
        this.service=service;
    }

    public long _id;                //訂位編號
    public long customer;           //顧客資料   int customerID
    public int adult;               //幾大幾小
    public int child;
    public boolean checkout;        //入場.出場
    public boolean checkin;
    public String reservetime;        //訂位日期訂位時間
    public String PS;               //備註
    public String service;          //訂位人員


}