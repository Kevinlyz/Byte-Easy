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