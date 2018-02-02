package jointsky.main;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by LiuZifan on 2018/1/29.
 */
public class Test {
    public static void main(String[] args) {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
        System.out.println(sdf.format(now));
       // System.out.println(sdf.format(now).substring(0,15) + "0:00");
    }
}
