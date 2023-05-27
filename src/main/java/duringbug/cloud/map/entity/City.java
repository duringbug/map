/*
 * @Description: 
 * @Author: 唐健峰
 * @Date: 2023-05-26 21:01:03
 * @LastEditors: ${author}
 * @LastEditTime: 2023-05-26 21:04:52
 */
package duringbug.cloud.map.entity;

public class City {
    public String name;
    public double x;
    public double y;
    public City(String name,double x,double y){
        this.name=name;
        this.x=x;
        this.y=y;
    }
}
