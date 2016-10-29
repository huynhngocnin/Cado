package nin.app.cado.Util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import nin.app.cado.constant.CommonConstant;

/**
 * Created by NinHuynh on 10/13/16.
 */

public class DateTimeUtil {
    private static final String FORMAT_YYYYMMDD = "yyyy-MM-dd";

    public static String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat(FORMAT_YYYYMMDD);
        return dateFormat.format(new Date()); //2014-08-06
    }

    public static String getYesterdayDate() {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYYMMDD);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, -1);
        return sdf.format(c.getTime());
    }

    public static String getTomorrowDate() {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYYMMDD);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 1);
        return sdf.format(c.getTime());
    }

    public static List<String> getList7DayResult() {
        List<String> lstDate = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYYMMDD);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        for (int i = 1; i <= 7; i++) {
            c.add(Calendar.DATE, -1);
            lstDate.add(sdf.format(c.getTime()));
        }
        return lstDate;
    }

    public static List<String> getList7DayFixtures() {
        List<String> lstDate = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYYMMDD);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        for (int i = 1; i <= 7; i++) {
            c.add(Calendar.DATE, 1);
            lstDate.add(sdf.format(c.getTime()));
        }
        return lstDate;
    }

    public static String convertDateFromReceiveToRequest(String receiveDate) {
        SimpleDateFormat dmyFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dmyFormat.parse(receiveDate);
            return ymdFormat.format(date);
        } catch (ParseException parseEx) {
            return CommonConstant.BLANK;
        }
    }


}
