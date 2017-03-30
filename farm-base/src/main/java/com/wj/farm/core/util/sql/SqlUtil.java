package com.wj.farm.core.util.sql;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * 
 * 初始化加载sql配置文件
 *
 * @author 16060834
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Lazy(false)
@Component
public class SqlUtil implements InitializingBean {
    Logger logger = LoggerFactory.getLogger(SqlUtil.class);
    
    private static Map<String, Map<String, String>> keySqlMap = new HashMap<String, Map<String,String>>();
    
    private static Map<String, String> proMap = new HashMap<String, String>();
    public void afterPropertiesSet() throws Exception {
        long startTime=System.currentTimeMillis();//记录开始时间
        logger.info("------------加载sql配置文件开始-----------");
        proMap = PropertyReader.readPropertyFile();//读取sql文件的总配置
        
        if(!CollectionUtils.isEmpty(proMap)){
            Iterator<Entry<String, String>> iterator = proMap.entrySet().iterator();
            String sqlXmlPath;//sql xml文件的路径
            while (iterator.hasNext()) {
                Entry<String, String> entry = iterator.next();
                sqlXmlPath = entry.getValue();
                
                Map<String, String> sqlMap = XmlReader.getSql(sqlXmlPath);//获取sql，放在map
                
                keySqlMap.put(entry.getValue(), sqlMap);//properties配置文件key为主键，value存对应的sqlmap
            }
        }
        long endTime=System.currentTimeMillis();//记录结束时间
        logger.info("------------加载sql配置文件成功,耗时："+(endTime-startTime)+"ms-----------");
    }
    
    /**
     * 
     * 对外提供获取sql文件方法
     *
     * @param fileName
     * @param sqlId
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String getSql(String fileName,String sqlId){
        String sql = null;
        if(!CollectionUtils.isEmpty(keySqlMap)){
            Map<String, String> map = new HashMap<String, String>();
            for(Map.Entry<String, Map<String, String>> entry:keySqlMap.entrySet()){
                if(proMap.get(fileName).equals(entry.getKey())){//文件名相同，取sql
                     map = entry.getValue();//取出该sql文件下的所有sql的map
                     sql = map.get(sqlId);
                     break;
                }
            }
        }
        
        return sql;
    }
}
