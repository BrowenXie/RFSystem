package com.wen.rfsystem;

import android.content.Context;

import java.sql.Date;
import java.util.List;

/**
 * Created by wen on 2016/8/7.
 *
 * ＤＡＯ　ＣＬＡＳＳ
 */
public interface SFsysDAO {

    public long cusadd(customer person);  //顧客資料SQLite新增 刪除 修改
    public void cusdel(customer person);
    public void cusupdata(customer person);
    public customer  checkcus(long id);  //傳回指定編號的顧客資料



    public long resadd(reserve reserve);  //訂位資料SQLite新增 刪除 修改
    public void resdel(reserve reserve);
    public void resupdata(reserve reserve);
    public reserve  checkres(long id);  //傳回指定編號的顧客資料

    public void jasonsave(Context context);              //讀寫JSON (用匯出/儲存/備分)
    public void jasonload(Context context);




    public List getAllreserve();   //傳回所有訂位資料
    public List getadayreserve(String date);

    public List getAllcuserve();   //傳回所有顧客資料


}
