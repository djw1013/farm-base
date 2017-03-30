package com.wj.farm.core.util.convert;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

/**
 * 
 * map工具类
 * 
 * @author 16060834
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class MapUtils {
    /**
     * 
     * Map转换为bean
     *
     * @param map
     * @param class1
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static <T> T map2Bean(Map<String, Object> map, Class<T> class1) {
        T bean = null;
        try {
            bean = class1.newInstance();
            BeanUtils.populate(bean, map);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }
    
    /**
     * 
     * maplist转换为beanlist
     *
     * @param list
     * @param class1
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static <T> List<T> mapList2BeanList(List<Map<String, Object>>list, Class<T> class1) {
        T bean = null;
        List<T> beanList = new ArrayList<T>();
        try {
            for(Map<String, Object> map:list){
                bean = class1.newInstance();
                BeanUtils.populate(bean, map);
                beanList.add(bean);
            }
            
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return beanList;
    }
    
}