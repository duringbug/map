/*
 * @Description: 
 * @Author: 唐健峰
 * @Date: 2023-05-25 14:32:27
 * @LastEditors: ${author}
 * @LastEditTime: 2023-05-26 21:40:00
 */
package duringbug.cloud.map.service;

public class Road implements Comparable<Road>{
    @Override
    public String toString() {
        return "Road [from=" + from + ", to=" + to + ", dist=" + dist + "]";
    }
    String from;
    String to;
    double dist;
    public Road(String from,String to,double dist){
        this.dist=dist;
        this.from=from;
        this.to=to;
    }
    @Override
    public int compareTo(Road r) {
        return Double.compare(this.dist, r.getDist());
    }
    public String getFrom() {
        return from;
    }
    public String getTo() {
        return to;
    }
    public double getDist() {
        return dist;
    }
}
