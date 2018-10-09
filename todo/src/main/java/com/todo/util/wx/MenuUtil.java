package com.todo.util.wx;

public class MenuUtil {
    private String createMenuURL
            ="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    public void buildMenu() throws Exception {
        String tocken
                ="14_ORDL_r406CQ_uUD5aYd-rR50g9lU9lOLTCfzz2_NeCil7yWB-SE2nvm8vlGtxfMCIcyZrADEKIE8Noz11EMswQ6CVGbj0XrPbUHRmbFwyQD0o_z8R9eLIfMA438_SVN9tDwk_93b9K3RLvWTZDCeADAVZY";
        String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx9fb5d2c14742fa1f&redirect_uri=http%3a%2f%2f139.199.36.234%2ftodo%2fapi%2fuser%2fbindWX&response_type=code&scope=snsapi_userinfo&state=bind#wechat_redirec";
        String menu="{" +
                "     \"button\":[" +
                "     {    " +
                "          \"type\":\"click\"," +
                "          \"name\":\"今日歌曲\"," +
                "          \"key\":\"V1001_TODAY_MUSIC\"" +
                "      }," +
                "      {" +
                "           \"name\":\"菜单\"," +
                "           \"sub_button\":[" +
                "           {    " +
                "               \"type\":\"view\"," +
                "               \"name\":\"绑定\"," +
                "               \"url\":\""+url+"\"" +
                "            }," +
                "            {" +
                "               \"type\":\"click\"," +
                "               \"name\":\"赞一下我们\"," +
                "               \"key\":\"V1001_GOOD\"" +
                "            }]" +
                "       }]" +
                " }";
        System.out.println(
                HttpUtil.doPost(
                createMenuURL.replace("ACCESS_TOKEN",tocken),menu));
    }

    public static void main(String[] args) throws Exception {
        new MenuUtil().buildMenu();
    }
}
