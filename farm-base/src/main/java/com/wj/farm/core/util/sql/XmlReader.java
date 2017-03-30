package com.wj.farm.core.util.sql;

import java.util.HashMap;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XmlReader {
 private static XmlReader xml = new XmlReader();
 
 private static final String XML_PATH_EXPRESSION = "template/sqltemplate";

 /**
  * 
  *读取xml文件。获取document树
  *
  * @param xmlPath
  * @return
  * @see [相关类/方法](可选)
  * @since [产品/模块版本](可选)
  */
 private static Document getXml(String xmlPath)
    {
        Document document = null;
        SAXReader reader = new SAXReader();
        
        try
        {
            document = reader.read(xml.getClass().getClassLoader().getResourceAsStream(xmlPath));
        }
        catch (DocumentException e)
        {
            System.out.println("获取xml " + xmlPath + " 文件失败！");
            e.printStackTrace();
        }
        return document;
    }
    
    /**
     * 
     * 获取xml中的sql语句
     *
     * @param xmlPath
     * @param xpathExpression
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @SuppressWarnings("unchecked")
    public static HashMap<String, String> getSql(String xmlPath )
    {
        HashMap<String, String> map = new HashMap<String, String>();
        List<Object> list = getXml(xmlPath).selectNodes(XML_PATH_EXPRESSION);
        if (list != null && !list.isEmpty())
        {
            
            for (Object object : list)
            {
                Element element = (Element)object;
                String sql = element.getTextTrim();
                String id = element.attributeValue("id").trim();
                map.put(id, sql);
            }
        }
        return map;
    }
}
