package com.todo.util.wx;

import com.google.gson.Gson;
import com.todo.util.ConfigUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class WXTocken {
    private static String tocken=null;
    private static long createTime=0;
    private static final Logger logger = LogManager.getLogger(WXTocken.class.getName());

    //获取tocken
    public static String getTocken(){

        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMDDHHmmss");
        long now=Long.parseLong(sdf.format(new Date()));

        if(null==tocken||0==createTime||Long.compare(400,(long)(now-createTime))<0){
            String retruen=HttpUtil.doGet(
                    ConfigUtil.getContextProperty("GET_TOCKEN_URL")
                            .toString()
                            .replace("APPID",
                                    ConfigUtil.getContextProperty("APPID")
                                            .toString())
                            .replace("APPSECRET",ConfigUtil.getContextProperty("APPSECRET")
                                    .toString())
            );
            System.out.println("return:"+retruen);
            Map<String,String> map=new Gson().fromJson(
                    retruen,Map.class);
            tocken=map.get("access_token");
            createTime=now;
            //createTime=new Timestamp(new Date().getTime());
        }
        System.out.println(tocken);
        logger.info("tocken***="+tocken);
        return tocken;
    }
}
