

## 介绍





- Geotools主要提供各种GIS算法，实现各种数据格式的读写和显示。
- 在显示方面要差一些，只是用Swing实现了地图的简单查看和操作。
-  用户可以根据Geotools提供的算法自己实现地图的可视化。OpenJump和udig就是基于Geotools的。
- 目前的大部分开源软件，如udig，geoserver等，对空间数据的处理都是由geotools来做支撑。
- web服务，命令行工具和桌面程序都可以由geotools来实现。
- 是构建在OGC标准之上的，是OGC思想的一种实现。而OGC是国际标准，所以geotools将来必定会成为开源空间数据处理的主要工具，

-  Geotools用到的两个较重要的开源GIS工具包是JTS和GeoAPI。前者主要是实现各种GIS拓扑算法，也是基于GeoAPI的。但是由 于两个工具包的GeoAPI分别采用不同的Java代码实现，所以在使用时需要相互转化。Geotools又根据两者定义了部分自己的GeoAPI，所以 代码显得臃肿，有时容易混淆。由于GeoAPI进展缓慢，Geotools自己对其进行了扩充。
- Geotools现在还只是基于2D图形的，缺乏对 3D空间数据算法和显示的支持。

 ## Geotools支持的数据格式

1. `arcsde`, `arcgrid`, `geotiff`, `grassraster`, `gtopo30`, `image`(`JPEG`, `TIFF`, `GIF`, `PNG`), `imageio-ext-gdal`, `imagemoasaic`, `imagepyramid`, `JP2K`,`matlab`；
2. 支持的数据库“jdbc-ng”：`db2`, `h2`, `mysql`, `oracle`, `postgis`, `spatialite`, `sqlserver`；
3. 支持的矢量格式和数据访问：`app-schema`, `arcsde`, `csv`, `dxf`, `edigeo`, `excel`, `geojson`,`org`, `property`, `shapefile`, `wfs`；
4. `XML`绑定。基于xml的Java数据结构和绑定提供了如下格式`xsd-core` (xml simple types), `fes`,`filter`, `gml2`, `gml3`, `kml`, `ows`, `sld`, `wcs`, `wfs`, `wms`, `wps`, `vpf`。对于额外的`geometry`、`sld`和`filter`的编码和解析可以通过`dom`和`sax`程序。



 **支持大部分的OGC标准**

1. OGC中的sld/SE和渲染引擎；
2. OGC一般要素模型包括简单要素支持；
3. OGC中栅格信息的网格影像表达；
4. OGC中WFS，WMS和额外的WPS；
5. ISO 19107 geometry规范；



## Geotools依赖的开源项目

1. JTS：JTS是加拿大的 Vivid Solutions 做的一套开放源码的 Java API。它提供了一套空间数据操作的核心算法,为在兼容OGC标准的空间对象模型中进行基础的几何操作提供2D空间谓词API。
2. GeoAPI：GeoAPI为OpenGIS规范提供一组Java接口。





## 本文参考

- [Geotools应用简要指南](http://www.gisempire.com/a/open/2015/1109/1374.html)