package cn.actional.blog.utils;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @auther actional
 * @create 2020-05-19
 */
public class DateFormatUtil {


    public static String format(Object date,String strFromat) {
        SimpleDateFormat format = new SimpleDateFormat(strFromat);
        return format.format(date);
    }

    /**
     *  格式化年月日
     */
    public static String formatYMD(Date date,String strFromat) {
        return format(date,strFromat);
    }

    public static Date parse(String strDate,String strFormat) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(strFormat);
        return format.parse(strDate);
    }


    public static String strConversionStr(String date,String oldStr,String newStr) throws ParseException {
        Date parse = parse(date, oldStr);
        return format(parse, newStr);
    }
}
