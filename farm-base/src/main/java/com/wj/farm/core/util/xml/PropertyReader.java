package com.wj.farm.core.util.xml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
/**
 * 
 * classpath下properties配置文件的读取
 *
 * @author 16060834
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class PropertyReader {
    private static final String SQL_CONFIG_PATH = "sqlxml/sqlconfig.properties";
    
    private static PropertyReader propertyReader = new PropertyReader();
    
    public static Map<String, String> readPropertyFile(){
        Map<String, String> map = new HashMap<String, String>();
        Properties pro = new Properties();
        InputStream in;
        try {
            in = propertyReader.getClass().getClassLoader().getResourceAsStream(SQL_CONFIG_PATH);
            pro.load(in);
            in.close();
            Iterator<Entry<Object, Object>> iterator = pro.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<Object, Object> entry = iterator.next();
                map.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
            }
        } catch (Exception e) {
            System.out.println("读取文件出错："+e);
        }
        return map;
    }

}
