//单文件上传url
var singlFileUrl ="admin/upload/singleFile";
/**
 * @Title: uploadSinglFile
 * @Description: 单文件上传
 * @Author: lxt 
 * @param: e file对象
 * @param: inputId 返回url复制input的ID，可不传
 * @Date: 2019-02-15 15:09
 * @throws: 
 */
function uploadSinglFile(e,inputId) {
    var vlas = $(e).val();
    if (vlas == "") {
        layer.msg("文件上传失败，上传对象不存在！",{icon:1,time:1000});
        return;
    }
    $.ajaxFileUpload({
        url : singlFileUrl,
        secureuri : false,
        fileElementId : $(e).attr('id'),
        dataType : 'json',
        success : function(data) // 服务器成功响应处理函数
        {
            console.log("上传结果data=>："+data);
            if (inputId != null && data != null){
                $("#"+inputId).val(data);
            }
            return data;
        },
        error : function() {
            layer.msg('文件上传异常！',{icon:1,time:1000});
        }
    })
    return false;
}
//-----------------------------------------------------------------------
var downloadSingleFileUrl = "/file/downloadSingleFile";
var downloadMultipartFileWithZipUrl = "/file/downloadMultipartFileWithZip";
/**
 * @Title: download
 * @Description: 文件下载
 * @Author: lxt 
 * @Date: 2019-02-19 13:46 
 * @throws: 
 */
function download(url, urlArr,fileName){ // 获得url和data
    if( url && urlArr ){
        var inputs = '';
        if (downloadSingleFileUrl == url){
            inputs+='<input type="hidden" name="url" value="'+ urlArr[0]+'" />'
            if (fileName){
                inputs+='<input type="hidden" name="fileName" value="'+fileName+'" />'
            }
        }else{
            for (var i = 0; i < urlArr.length; i++) {
                inputs+='<input type="hidden" name="urlList['+ i +']" value="'+ urlArr[i]+'" />'
            }
            if (fileName){
                inputs+='<input type="hidden" name="zipName" value="'+fileName+'" />'
            }
        }
        // console.log(inputs);
        // request发送请求
        jQuery('<form action="'+ url +'" method="'+ ('post') +'">'+inputs+'</form>').appendTo('body').submit().remove();
    };
};
/**
 * @Title: download
 * @Description: 单文件下载
 * @Author: lxt
 * @Date: 2019-02-19 13:46
 * @throws:
 */
function downloadSingleFile(url, fileName){
    if (url){
        var urlArr = new  Array();
        urlArr.push(url);
        download(downloadSingleFileUrl,urlArr,fileName);
    }
};
/**
 * @Title: download
 * @Description: 多文件下载
 * @Author: lxt
 * @Date: 2019-02-19 13:46
 * @throws:
 */
function downloadMultipartFileWithZip(urlArr, fileName){
    download(downloadMultipartFileWithZipUrl,urlArr,fileName);
};