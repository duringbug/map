/*
 * @Description: 
 * @Author: 唐健峰
 * @Date: 2023-05-25 14:33:59
 * @LastEditors: ${author}
 * @LastEditTime: 2023-05-27 13:17:01
 */
package duringbug.cloud.map.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class CityMap {
    double cnt=0;
    int cityNum;
    ArrayList<Road> roadlist;
    HashMap<String,String> fatherList;
    ArrayList<Road> notAllowRoad;
    ArrayList<Road> pathMap; 
    public CityMap(int cityNum){
        ArrayList<Road> roadlist=new ArrayList<Road>();
        this.roadlist=roadlist;
        this.cityNum=cityNum;
        this.fatherList=new HashMap<String,String>();
        this.notAllowRoad=new ArrayList<Road>();
        this.pathMap=new ArrayList<Road>();
    }
    public HashMap<String,String> getFatherList(){
        return this.fatherList;
    }
    public void addRoad(Road roud){
        this.roadlist.add(roud);
        fatherList.put(roud.from, roud.from);
        fatherList.put(roud.to,roud.to);
        Collections.sort(roadlist);
    }
    public void addRoad(ArrayList<Road> roadList2){
        this.roadlist.addAll(roadList2);
        for(Road roud : roadList2)
        {
            fatherList.put(roud.from, roud.from);
            fatherList.put(roud.to,roud.to);
        }
        Collections.sort(roadlist);
    }
    public double getMinLength(){
        double length=0;
        for(Road road : roadlist)
        {
            String x=findFather(road.from);
            String y=findFather(road.to);
            if(notAllow(road.from, road.to)){
                continue;
            }
            // System.out.println("x为: "+x+";y为: "+y);
            // System.out.println("");
            if (x != y)
            {
                fatherList.put(y, x);
                // fatherList.put(road.from, road.to);
                // System.out.println(this.fatherList);
                // System.out.print(road.from+"到"+road.to);
                Road newRoad=new Road(road.from, road.to,searchRoad(road.from, road.to));
                pathMap.add(newRoad);
                length += road.dist;
                cnt++;
                if (cnt == cityNum - 1)
                    break;
            }
            // System.out.println("");
        }
        return length;
    }
    private String findFather(String to) {
        if (fatherList.get(to).equals(to)) {
            return to;
        } else {
            String father = findFather(fatherList.get(to));
            return father;
        }
    }
    public int getCityNum() {
        return cityNum;
    }
    public ArrayList<Road> getRoadlist() {
        return roadlist;
    }
    private double searchRoad(String from,String to){
        for (Road road : roadlist) {
            if ((road.getFrom().equals(from) && road.getTo().equals(to))||(road.getFrom().equals(to) && road.getTo().equals(from))) {
                return road.getDist();
            }
        }
        return -1;
    }
    public ArrayList<Road> getPathMap() {
        return pathMap;
    }
    public void addSpecailLine(String from,String to){
        String x=findFather(from);
        this.fatherList.put(from, x);
        this.fatherList.put(to,x);
        Road newRoad=new Road(from, to,searchRoad(from, to));
        pathMap.add(newRoad);
        cnt=cnt+searchRoad(from, to);
    }
    public void notIncludeLine(String from,String to){
        this.notAllowRoad.add(new Road(from, to, -1));
    }
    public Boolean notAllow(String from,String to){
        for(int i=0;i<notAllowRoad.size();i++){
            if((notAllowRoad.get(i).from.equals(from)&&notAllowRoad.get(i).to.equals(to))||(notAllowRoad.get(i).from.equals(to)&&notAllowRoad.get(i).to.equals(from))){
                return true;
            }
        }
        return false;
    }
}
