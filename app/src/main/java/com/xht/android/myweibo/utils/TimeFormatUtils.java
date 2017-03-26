package com.xht.android.myweibo.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Administrator on 2017/2/13.
 */
public class TimeFormatUtils {
    /**
     * 把一个时间毫秒值转换为   yyyy-MM-dd hh:mm:ss  的具体时间
     * @param now
     * @return
     */
    public static String getTimeUtils(long now) {
		/*DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		//long now = System.currentTimeMillis();
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(now);
		return formatter.format(calendar.getTime());*/
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sd.format(new Date(now));
    }
    /**
     * 通过时间秒毫秒数判断两个时间的间隔天数
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDaysByMillisecond(long date1,long date2)
    {
        // SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // Date date = format.parse(dateStr); // dateStr="2008-1-1 1:21:28"   dateStr;---》Data对象
        // date.getTime();  //获得毫秒值      Data对象---》毫秒值
        int days = (int) ((date2 - date1) / (1000*3600*24));
        return days;
    }

    /**
     * 把 时间  yyyy-MM-dd hh:mm:ss  过滤掉为 年月日 的形式  -------yyyy/MM/dd
     * @param time"Sun Mar 12 19:36:11 +0800 2017"
     * @return
     */
    public static String getTimes(String time){
        DateFormat formatter=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date=null;
        try {
            date=formatter.parse(time);

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        GregorianCalendar calendar=new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR)+"/"+(calendar.get(Calendar.MONTH)+1)+"/"+calendar.get(Calendar.DAY_OF_MONTH);
    }


    /**
     *   把这种形式  "EEE MMM dd HH:mm:ss Z yyy"----转换为需要的方式
     * @param time
     * @return
     */
    public static String parseYYMMDD(String time){
        SimpleDateFormat sdf=new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyy", Locale.ENGLISH);
        Date data=null;
        Calendar calendar=Calendar.getInstance();
        try {
            data=sdf.parse(time);
            calendar.setTime(data);
            calendar.setTimeZone(TimeZone.getDefault());
            calendar.getTimeInMillis();
            return converToSimpleStrData(calendar.getTimeInMillis());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String converToSimpleStrData(long timeInMillis) {
        long current=System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat;
        long offset=(current-timeInMillis)/100;
        Date dt=new Date(timeInMillis);
        String returnData="";
        if (offset<5*60){
            returnData="刚刚";
        }else if(offset>=5*60&& offset<=60*60){
            returnData=offset/60+"分钟前";

        }else if(offset>=60*60&& offset<=60*60*24){
            returnData=offset/60/60+"小时前";
        }else if(offset>=60*60*24&& offset<=60*60*24*2){
            returnData="昨天";
        }else if(offset>=60*60*2&&offset<=60*60*24*30){
            simpleDateFormat=new SimpleDateFormat("MM-dd HH:mm");
            returnData=simpleDateFormat.format(dt);

        }else if(offset>=60*60*24*30&&offset<=60*60*24*30*365){
            simpleDateFormat=new SimpleDateFormat("MM-dd HH:mm");
            returnData=simpleDateFormat.format(dt);

        }else{
            simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            returnData=simpleDateFormat.format(dt);
        }


        return returnData;
    }
}
