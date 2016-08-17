package com.wen.rfsystem;

import java.util.Date;

/**
 * Created by wen on 2016/8/6.
 *會員資料CLASS
 *
 */
public class customer {
 //建構
    public customer(

                    int sex,
                    int awkward,
                    String awkreason,
                    int VIP,
                    String name,
                    String birthday,
                    String address,
                    String tel,
                    String PS )
    {

        this.sex=sex;
        this.awkward=awkward;
        this.awkreason=awkreason;
        this.VIP=VIP;
        this.name=name;
        this.birthday=birthday;
        this.address=address;
        this. tel=tel;
        this.PS=PS;
    }

    public long _id;           //會員編號.SQLite的Key值
    public int sex;           //性別
    public int awkward;       //黑名單
    public String awkreason;  //黑名單理由
    public int VIP;           //VIP等級
    public String name;       //姓名 生日 地址 電話 備註
    public String birthday;
    public String address;
    public String tel;
    public String PS;
}
