/**
 * 类型为7的layer弹窗
 *
 * @param msg
 *            弹窗
 * @param icon
 *            图标
 * @param e
 *            回调函數
 * @param time
 *            秒数,销毁时间(可为空，默认1秒)
 * @returns
 */
layer.designMsg = function(msg,icon,e,time) {
    if (time == null) {
        time = 1000;
    }else{
        time = time * 1000;
    }

    if (icon == 1) {
        icon = 1;
//		对勾
    }
    if (icon == 2) {
        icon = 2;
//		叉号
    }
    if (icon == 3) {
        icon = 3;
//		问号
    }
    if (icon == 4) {
        icon = 4;
//		锁
    }
    if (icon == 5) {
        icon = 5;
//		哭脸
    }
    if (icon == 6) {
        icon = 6;
//		笑脸
    }
    if (icon == 7) {
        icon = 7;
//		叹号
    }
    //默认使用叉号
    if(icon == null){
        icon = 2;
    }
    layer.msg(msg, {
        icon : icon,
        time : time,
        // shade: 0.4,
        // skin:'white_bg'
    },e);
}

/**
 * 根据id删除
 * @param url
 */
function deleteById(url){
    layer.confirm('确认要删除吗？',function(index){
        $.ajax({
            type: 'GET',
            url: url,
            dataType: 'json',
            success: function(data){
                if(data.code == "0"){
                    layer.designMsg('删除成功!',1,function(){
                        window.location.reload();
                    });
                }else{
                    layer.designMsg('删除失败!');
                }
            },
            error:function() {
                layer.designMsg('删除异常!',5);
            },
        });
    });
}

/**
 * 批量删除
 * @param url
 * @returns {boolean}
 */
function deleteAll(url){
    var _list = new Array();
    $('input[name="id"]:checked').each(
        function (i) {
            _list[i] = $(this).val();
        });
    if(_list.length == 0){
        layer.msg("请选择删除的数据");
        return false;
    }
    layer.confirm('确定要删除选中的全部数据吗？', {
        btn : [ '确定', '取消' ]
        // 按钮
    }, function() {
        $.ajax({
            url:url,// 跳转到 action
            data:JSON.stringify(_list),
            type:'post',
            cache:false,
            dataType:'json',
            contentType:"application/json",  //发送信息至服务器时内容编码类型。
            success:function(data) {
                if(data.code == "0"){
                    layer.designMsg('删除成功!',1,function(){
                        window.location.reload();
                    });
                }else{
                    layer.designMsg('删除失败!');
                }
            },
            error:function() {
                layer.designMsg('删除异常!',5);
            },
        });
    }, function() {

    });
}

/**
 * 检验输入的值是否为整数
 * @param inputNode
 */
function checkint(inputNode){
    if(/^\d?$/.test($(inputNode).val())){

    }
}