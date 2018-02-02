package jointsky.util;

import jointsky.vo.GasTenMinData;

import java.text.SimpleDateFormat;

/**
 * Created by LiuZifan on 2018/1/30.
 */
public class ETLUtil {
    //对数据进行ETL,判断数据的字段是否合法
    public static boolean PsCodeFilter(String PsCode) {
        return true;
    }

    public static boolean outputCodeFilter(String outputCode) {
        return true;
    }

    public static boolean pollutantCodeFilter(String pollutantCode) {
        return true;
    }

    public static boolean TimeFilter(String monitorTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setLenient(false);
        try {
            sdf.parse(monitorTime);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    public static boolean isDouble(String value) {
        return true;
    }
    public static boolean isInt(String value) {
        return true;
    }

}
