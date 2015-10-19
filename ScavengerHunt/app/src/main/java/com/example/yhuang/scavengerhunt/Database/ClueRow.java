package com.example.yhuang.scavengerhunt.Database;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by siyer on 10/19/2015.
 */
public class ClueRow {

    public static class Row{
        public Integer id;
        public Double lat;
        public Double lon;
        public String s3video;
        public Row(Integer id, Double lat, Double lon, String s3video)
        {
            this.id = id; System.out.println(id);
            this.lat = lat; System.out.println(lat);
            this.lon = lon; System.out.println(lon);
            this.s3video = s3video; System.out.println(s3video);
        }
    }

    public static Map<Integer, Row> Map(ArrayList<Integer> id, ArrayList<Double> lati, ArrayList<Double> longi, ArrayList<String> s3vid){
        Map<Integer, Row> map = new TreeMap<Integer, Row>();
        for (int i=0; i<id.size(); i++){
            Row r = new Row(id.get(i),lati.get(i),longi.get(i),s3vid.get(i));
            map.put(r.id,r);
        }
        return map;
    }
}
