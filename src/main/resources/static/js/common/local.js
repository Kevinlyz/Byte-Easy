function isLogin()
{
    $.ajax({
        url:'/isLogin',// 跳转到 action
        type:'post',
        cache:false,
        dataType:'json',
        contentType:"application/json",  //发送信息至服务器时内容编码类型。
        success:function(data) {
            if(data.code == '123456'){
                window.location.href="/login"; // 尚未登录，跳转到登录页
            }else{
                return true;
            }
        },
        error : function() {
            layer.msg("请检查网络连接！");
        }
    });
}