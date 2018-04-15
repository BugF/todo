package com.todo.util.wx;

import com.todo.controller.param.WX_Message;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

public class XmlUtil {
    public static Object fromXmlToBean(Element rootElt, Class pojo) throws Exception
    {
        // 首先得到pojo所定义的字段
        Field[] fields = pojo.getDeclaredFields();
        // 根据传入的Class动态生成pojo对象
        Object obj = pojo.newInstance();
        for (Field field : fields)
        {
            // 设置字段可访问（必须，否则报错）
            field.setAccessible(true);
            // 得到字段的属性名
            String name = field.getName();
            // 这一段的作用是如果字段在Element中不存在会抛出异常，如果出异常，则跳过。
            try
            {
                rootElt.elementTextTrim(name);
            }
            catch (Exception ex)
            {
                continue;
            }
            if (rootElt.elementTextTrim(name) != null && !"".equals(rootElt.elementTextTrim(name)))
            {
                // 根据字段的类型将值转化为相应的类型，并设置到生成的对象中。
                if (field.getType().equals(Long.class) || field.getType().equals(long.class))
                {
                    field.set(obj, Long.parseLong(rootElt.elementTextTrim(name)));
                }
                else if (field.getType().equals(String.class))
                {
                    field.set(obj, rootElt.elementTextTrim(name));
                }
                else if (field.getType().equals(Double.class) || field.getType().equals(double.class))
                {
                    field.set(obj, Double.parseDouble(rootElt.elementTextTrim(name)));
                }
                else if (field.getType().equals(Integer.class) || field.getType().equals(int.class))
                {
                    field.set(obj, Integer.parseInt(rootElt.elementTextTrim(name)));
                }
                else if (field.getType().equals(java.util.Date.class))
                {
                    field.set(obj, Date.parse(rootElt.elementTextTrim(name)));
                }
                else
                {
                    continue;
                }
            }
        }
        return obj;
    }
    public static Document getDocument(Object b) {
        Document document = DocumentHelper.createDocument();
        try {
// 创建根节点元素
            Element root = document.addElement(b.getClass().getSimpleName());
            Field[] field = b.getClass().getDeclaredFields(); // 获取实体类b的所有属性，返回Field数组
            for (int j = 0; j < field.length; j++) { // 遍历所有有属性
                String name = field[j].getName(); // 获取属属性的名字
                if (!name.equals("serialVersionUID")) {//去除串行化序列属性
                    name = name.substring(0, 1).toUpperCase()
                            + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
                    Method m = b.getClass().getMethod("get" + name);
// System.out.println("属性get方法返回值类型:" + m.getReturnType());
                    Object propertievalue =  m.invoke(b);// 获取属性值
                    Element propertie = root.addElement(name);
                    propertie.setText(propertievalue+"");
                }
            }


        } catch (Exception e) {
// TODO Auto-generated catch block
            e.printStackTrace();


        }
        return document;
    }
    public static void main(String[] args) {
        WX_Message toMessage=new WX_Message();
        toMessage.setCreateTime(new Date().getTime());
        toMessage.setMsgType("text");
        toMessage.setFromUserName("asd");
        toMessage.setToUserName("asd");

        toMessage.setContent("还未添加此服务。。。");

        String data=XmlUtil.getDocument(toMessage).asXML();
        System.out.println(data.indexOf("\n"));

        System.out.println( XmlUtil.getDocument(toMessage).asXML());
    }
}
