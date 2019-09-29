package com.tutorial.shp;

import org.geotools.data.FeatureWriter;
import org.geotools.data.Transaction;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShpUtil {

    public static void main(String[] args){

        testWritePoint();

    }

    public static void testWritePoint(){
        List<Point> pointList = new ArrayList<>();
        List<String> nameList = new ArrayList<>();

        for(int i = 0; i< 10; i++){
            Coordinate coordinate = new Coordinate(120+ i*0.1, 30+ 0.1 *i);

            pointList.add(new GeometryFactory().createPoint(coordinate));

            nameList.add("name is " + i);
        }

        writePoint("data/point.shp", pointList, nameList);

    }


    /**
     * 点写入shp文件
     * @param filePath shp文件位置
     * @param pointList
     * @param nameList 点列表对应的点名称列表
     */
    public static void writePoint(String filePath, List<Point> pointList, List<String> nameList){


        try {
            // 创建shape文件对象
            File file = new File(filePath);
            Map<String, Serializable> params = new HashMap<>();
            params.put(ShapefileDataStoreFactory.URLP.key, file.toURI().toURL());

            ShapefileDataStore ds = (ShapefileDataStore) new ShapefileDataStoreFactory().createDataStore(params);

            //定义图形信息和属性信息
            SimpleFeatureTypeBuilder tb = new SimpleFeatureTypeBuilder();
            tb.setCRS(DefaultGeographicCRS.WGS84);
            tb.setName("shapefile");
            tb.add("the_geom", Point.class);
            tb.add("POIID", Long.class);
            tb.add("NAMEC", String.class);
            ds.createSchema(tb.buildFeatureType());

            ds.setCharset(Charset.forName("GBK"));

            //设置writer
            FeatureWriter<SimpleFeatureType, SimpleFeature> writer = ds.getFeatureWriter(ds.getTypeNames()[0],
                    Transaction.AUTO_COMMIT);

            // 写入数据
            for(int i = 0; i< pointList.size(); i++){
                SimpleFeature feature = writer.next();
                feature.setAttribute("the_geom", pointList.get(i));
                // 如果想生成线，the_geom就是线

                feature.setAttribute("POIID", Long.class);
                feature.setAttribute("NAMEC", nameList.get(i));
            }


            writer.write();
            writer.close();
            ds.dispose();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



}
