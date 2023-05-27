/*
 * @Description: 
 * @Author: 唐健峰
 * @Date: 2023-05-26 17:20:22
 * @LastEditors: ${author}
 * @LastEditTime: 2023-05-27 13:20:53
 */
/*
 * @Description: 
 * @Author: 唐健峰
 * @Date: 2023-05-26 17:20:22
 * @LastEditors: ${author}
 * @LastEditTime: 2023-05-27 11:13:13
 */
package duringbug.cloud.map;

import duringbug.cloud.map.entity.City;
import duringbug.cloud.map.service.CityMap;
import duringbug.cloud.map.service.Road;
import duringbug.cloud.map.utils.MinPathUtil;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.boot.test.context.SpringBootTest;





@SpringBootTest
class MapApplicationTests {
    public static void main(String[] args) {
        int N=34;
        CityMap cityMap=new CityMap(N);
        City [] cityArray=new City[35];
        cityArray[1]=new City("沈阳市",123.429092,41.796768);
        cityArray[2]= new City("长春市",125.324501,43.886841);
        cityArray[3]= new City("哈尔滨市",126.642464,45.756966);
        cityArray[4]= new City("北京市",116.405289,39.904987);
        cityArray[5]= new City("天津市",117.190186,39.125595);
        cityArray[6]= new City("呼和浩特市",111.751990,40.841490);
        cityArray[7]= new City("银川市",106.232480,38.486440);
        cityArray[8]= new City("太原市",112.549248,37.857014);
        cityArray[9]= new City("石家庄市",114.502464,38.045475);
        cityArray[10]= new City("济南市",117.000923,36.675808);
        cityArray[11]= new City("郑州市",113.665413,34.757977);
        cityArray[12]= new City("西安市",108.948021,34.263161);
        cityArray[13]= new City("武汉市",114.298569,30.584354);
        cityArray[14]= new City("南京市",118.76741,32.041546);
        cityArray[15]= new City("合肥市",117.283043,31.861191);
        cityArray[16]= new City("上海市",121.472641,31.23170);
        cityArray[17]=new City("长沙市",112.982277,28.19409);
        cityArray[18]= new City("南昌市",115.892151,28.676493);
        cityArray[19]= new City("杭州市",120.15358,30.287458);
        cityArray[20]= new City("福州市",119.306236,26.075302);
        cityArray[21]= new City("广州市",113.28064,23.125177);
        cityArray[22]= new City("台北市",121.5200760,25.0307240);
        cityArray[23]= new City("海口市",110.199890,20.044220);
        cityArray[24]= new City("南宁市",108.320007,22.82402);
        cityArray[25]= new City("重庆市",106.504959,29.533155);
        cityArray[26]= new City("昆明市",102.71225,25.040609);
        cityArray[27]= new City("贵阳市",106.713478,26.578342);
        cityArray[28]= new City("成都市",104.065735,30.659462);
        cityArray[29]= new City("兰州市",103.834170,36.061380);
        cityArray[30]= new City("西宁市",101.777820,36.617290);
        cityArray[31]= new City("拉萨市",91.11450,29.644150);
        cityArray[32]= new City("乌鲁木齐市",87.616880,43.826630);
        cityArray[33]= new City("香港",114.165460,22.275340);
        cityArray[34]= new City("澳门",113.549130,22.198750);

        ArrayList<Road> roadlist2=new ArrayList<Road>();
        
        for(int i=1;i<N;i++)
        {
            for(int j=i+1;j<=N;j++)
            {
                Road road=new Road(cityArray[i].name, cityArray[j].name,MinPathUtil.GetDistance(cityArray[j].x, cityArray[j].y,cityArray[i].x, cityArray[i].y));
                roadlist2.add(road);
            }
        }
        cityMap.addRoad(roadlist2);
        StopWatch stopWatch=StopWatch.createStarted();
        
        // cityMap.addSpecailLine("西宁市","郑州市");
        // cityMap.addSpecailLine("杭州市","长沙市");
        // cityMap.addSpecailLine("郑州市","武汉市");
        // cityMap.addSpecailLine("武汉市","西宁市");
        // cityMap.notIncludeLine("香港", "澳门");
        // cityMap.notIncludeLine("澳门", "广州市");
        System.out.println(cityMap.getMinLength()+"公里");

        stopWatch.stop();
        System.out.println(stopWatch.getTime(TimeUnit.MICROSECONDS)*0.001+"ms");
        ArrayList<Road> rArrayList=cityMap.getPathMap();
        ArrayList<Road> list= cityMap.getRoadlist();
        for(Road road:list)
        {
            if(road.getFrom().equals("沈阳市")&&road.getTo().equals("北京市"))
            {
                System.out.println("沈阳市到北京市距离为"+road.getDist()+"公里");
            }
            else if(road.getFrom().equals("沈阳市")&&road.getTo().equals("上海市"))
            {
                System.out.println("沈阳市到上海市距离为"+road.getDist()+"公里");
            }
        }
        int add=0;
        int cout=0;
        for(Road road:rArrayList)
        {
            // System.out.println(road.getFrom()+" "+road.getTo()+" "+road.getDist());
            for(int i=1;i<=34;i++)
            {
                if(cityArray[i].name.equals(road.getFrom()))
                {
                    for(int j=1;j<=34;j++)
                    {
                        if(cityArray[j].name.equals(road.getTo()))
                        {
                            cout=cout+1;
                            System.out.println(
                            "var polyline"+cout+" = new BMapGL.Polyline([\n"+
                            "new BMapGL.Point("+cityArray[i].x+","+ cityArray[i].y+"),\n"+
                            "new BMapGL.Point("+cityArray[j].x+","+ cityArray[j].y+"),\n"+
                            "], { strokeColor: \"blue\", strokeWeight: 10, strokeOpacity: 0.5 });\n"+
                            "map.addOverlay(polyline"+cout+");"
                            );
                        }
                    }
                }
            }
            add+=+road.getDist();
        }
        System.out.println(add+"公里");
    }
}
