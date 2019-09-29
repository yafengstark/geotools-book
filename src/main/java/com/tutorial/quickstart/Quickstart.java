package com.tutorial.quickstart;

/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2019, Open Source Geospatial Foundation (OSGeo)
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 *
 */


import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.map.FeatureLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.styling.SLD;
import org.geotools.styling.Style;
import org.geotools.swing.JMapFrame;
import org.geotools.swing.data.JFileDataStoreChooser;

import java.io.File;
import java.io.IOException;

/**
 * Prompts the user for a shapefile and displays the contents on the screen in a map frame.
 *
 * <p>This is the GeoTools Quickstart application used in documentationa and tutorials. *
 */
public class Quickstart {

    /**
     * GeoTools Quickstart demo application. Prompts the user for a shapefile and displays its
     * contents on the screen in a map frame
     */
    public static void main(String[] args)  {
        // display a data store file chooser dialog for shapefiles
        File file = JFileDataStoreChooser.showOpenFile("shp", null);
        if (file == null) {
            return;
        }

//        "data/CHN_adm_shp/CHN_adm0.shp"
//        String path = "data/ne_50m_admin_0_countries/ne_50m_admin_0_countries.shp";

//        File file = new File(path);

        // FileDataStoreFinder
        // 可以使我们轻松处理文件。另一种处理方法是使用连接参数映射。这种技术使我们对使用shapefile的方式有了更多的控制，
        // 还使我们可以连接到数据库和Web功能服务器。
        FileDataStore store = null;
        try {
            store = FileDataStoreFinder.getDataStore(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SimpleFeatureSource featureSource = null;
        try {
            featureSource = store.getFeatureSource();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create a map content and add our shapefile to it
        MapContent map = new MapContent();
        map.setTitle("Quickstart");



        Style style = SLD.createSimpleStyle(featureSource.getSchema());


        Layer layer = new FeatureLayer(featureSource, style);
        map.addLayer(layer);

        // Now display the map
        JMapFrame.showMap(map);
    }


}