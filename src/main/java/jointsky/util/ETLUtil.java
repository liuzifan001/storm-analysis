package jointsky.util;

import jointsky.vo.GasTenMinData;

/**
 * Created by LiuZifan on 2018/1/30.
 */
public class ETLUtil {
    //对数据进行ETL,判断数据的字段是否合法
    public static boolean PsCodeFilter(String PsCode) {
        return false;
    }

    public static boolean outputCodeFilter(String outputCode) {
        return false;
    }

    public static boolean pollutantCodeFilter(String pollutantCode) {
        return false;
    }

    public static boolean TimeFilter(String monitorTime) {
        return false;
    }

    public static boolean isDouble(String value) {
        return false;
    }
    public static boolean isInt(String value) {
        return false;
    }


    public static boolean gasTenMinDataFilter(GasTenMinData data){
        return false;
    }

}
