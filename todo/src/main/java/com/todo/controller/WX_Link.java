package com.todo.controller;

import com.google.gson.Gson;
import com.todo.controller.param.WX_Message;
import com.todo.util.wx.SHA1;
import com.todo.util.wx.WXTocken;
import com.todo.util.wx.XmlUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

@Controller
public class WX_Link {
    private String TOKEN = "flxdyckfzjdwxgzh";
    @RequestMapping(value="wx/link" ,method = RequestMethod.GET)
    public @ResponseBody
    String get(HttpServletRequest request, HttpServletResponse response) throws IOException {
// 微信加密签名
        String signature = request.getParameter("signature");
        // 随机字符串
        String echostr = request.getParameter("echostr");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        System.out.println(signature);
        System.out.println(echostr);
        System.out.println(timestamp);
        System.out.println(nonce);

        String[] str = { TOKEN, timestamp, nonce };
        Arrays.sort(str); // 字典序排序
        String bigStr = str[0] + str[1] + str[2];
        // SHA1加密
        String digest = new SHA1().getDigestOfString(bigStr.getBytes()).toLowerCase();

        // 确认请求来至微信
        if (digest.equals(signature)) {
            //response.getWriter().print(echostr);
            return echostr;
        }else return null;


    }
    @RequestMapping(value="wx/link" ,method = RequestMethod.POST)
    public @ResponseBody
    String post(HttpServletRequest request, HttpServletResponse response ) throws IOException, DocumentException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        WXTocken.getTocken();
       // Document document= DocumentHelper.parseText(xmlStr.toString());
        SAXReader reader = new SAXReader();
        Document document;
        WX_Message message=null;
        try {
            document = reader.read(request.getInputStream());
            Element root = document.getRootElement();
            message = (WX_Message) XmlUtil.fromXmlToBean(root, WX_Message.class);
            System.out.println(message.getContent());
            System.out.println(new Gson().toJson(message));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("数据解析错误");
        }

        if(message.getMsgType().equals("text")){
            WX_Message toMessage=new WX_Message();
            toMessage.setCreateTime(new Date().getTime());
            toMessage.setMsgType("text");
            toMessage.setFromUserName(message.getToUserName());
            toMessage.setToUserName(message.getFromUserName());
            if(message.getContent().indexOf("验证码")>-1){
                toMessage.setContent("还未添加此服务。。。");
            }else if(message.getContent().indexOf("英文")>-1)
                toMessage.setContent("what?");
                else{
                toMessage.setContent("你说啥？");
            }
            String toMe=XmlUtil.getDocument(toMessage).asXML();
            int a=toMe.indexOf("\n");
            return new String(toMe.substring(a+1).replaceAll("WX_Message","xml").getBytes(),"utf-8");
        }
        return null;
    }
}
