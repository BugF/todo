<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <title>扫码登录</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/render/semantic.min.css">
    <script src="<%= request.getContextPath()%>/js/library/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/render/semantic.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/library/sockjs.min.js"></script>
</head>
<body style="background: lavender;height: 90%;">
<div style="width: 100%;position: fixed;bottom: 30px;">
    <%String tocken= (String) request.getAttribute("tocken");%>
    <%String openid= (String) request.getAttribute("openid");%>
    <div class="ui two column centered grid" style="padding-top: 30px;height: 100%">
        <div class="column">
            <div class="ui center card">
                <div class="image" style="width: 100%;text-align: center;">
                    <i class="ui massive user icon"></i>
                </div>
                <div class="content">
                    <%--<%=openid%>--%>
                    <%--<%=tocken%>--%>
                    <%--<%=request.getAttribute("openid")%>--%>
                    <%--<%=request.getAttribute("tocken")%>--%>
                    <div class="description">点击下面按钮，允许登录 </div>
                </div>
                <div class="extra content">
                    <div class="ui fluid button" id="canLogin"> 允许登录</div>
                </div>
            </div>


        </div>
    </div>
</div>



<script type="text/javascript">
    jQuery(document).ready(function(){
        var websocket = null;
        websocket = new SockJS("http://"+document.location.host+"/todo/api/websocket");
        websocket.onopen = onOpen;
        websocket.onmessage = onMessage;
        websocket.onerror = onError;
        websocket.onclose = onClose;

        function onOpen(openEvt) {
            console.info(JSON.stringify(openEvt));
        }

        function onMessage(evt) {
        }
        function onError(openEvt) {
            console.info(JSON.stringify(openEvt));
        }
        function onClose(openEvt) {
            console.info(JSON.stringify(openEvt));
        }

        jQuery('#canLogin').click(
            function () {
                alert('asdasd');
                var as={
                    tocken:'<%=tocken%>',
                    openid:'<%=openid%>',
                    type:'canLogin'
                }
                jQuery.ajax({
                    //提交数据的类型 POST GET
                    type:"POST",
                    //提交的网址
                    url:"http://"+document.location.host+"/todo/api/todo/login",
                    //提交的数据

                    //返回数据的格式
                    dataType: "json",//"xml", "html", "script", "json", "jsonp", "text".
                    contentType : 'application/json',
                    data : JSON.stringify(as),
                    //在请求之前调用的函数
                    //  beforeSend:function(){$("#msg").html("logining");},
                    //成功返回之后调用的函数
                    success:function(data){
                        alert(JSON.stringify(data));
                    }   ,
                    //调用执行后调用的函数
                    complete: function(XMLHttpRequest, textStatus){
                    },
                    //调用出错执行的函数
                    error: function(){
                    }
                });

            }
        )


    });

</script>

</body>
</html>