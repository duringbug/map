/*
 * @Description: 
 * @Author: 唐健峰
 * @Date: 2023-05-25 14:30:10
 * @LastEditors: ${author}
 * @LastEditTime: 2023-05-27 12:03:38
 */
package duringbug.cloud.map.utils;

public class MinPathUtil {
    private final static double EARTH_RADIUS = 6378.137;//地球半径
    private static double rad(double d)
    {
        return d * Math.PI / 180.0;
    }

    public static double GetDistance(double lng1,double lat1,double lng2,double lat2)//纬线,经线
    {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);

        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
            Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }
}
