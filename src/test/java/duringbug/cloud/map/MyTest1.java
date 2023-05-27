package duringbug.cloud.map;

import duringbug.cloud.map.service.CityMap;
import duringbug.cloud.map.service.Road;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;



public class MyTest1 {
    @Test
    public void test1(){
        ArrayList<Road> roadlist2=new ArrayList<Road>();
        roadlist2.add(new Road("A", "B", 5));
        roadlist2.add(new Road("A", "G", 2));
        roadlist2.add(new Road("A", "C", 7));
        roadlist2.add(new Road("B", "D", 9));
        roadlist2.add(new Road("D", "F", 4));
        roadlist2.add(new Road("B", "G", 3));
        roadlist2.add(new Road("G", "F", 6));
        roadlist2.add(new Road("E", "C", 8));
        roadlist2.add(new Road("E", "G", 4));
        roadlist2.add(new Road("E", "F", 5));
        CityMap cityMap=new CityMap(7);
        cityMap.addRoad(roadlist2);
        cityMap.getMinLength();
        System.out.println(cityMap.getPathMap());
    }
}
