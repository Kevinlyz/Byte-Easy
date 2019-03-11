/*--------------分页-------------------------*/
var pageNo = $('#pageNo');
var pageSize = $('#pageSize');

var Form = $('form');

//第几页
var total = Number($.trim($('#total').val()));
function goPage(){
	var t =  Number($.trim($('#goPage').val()));
	if(isNaN(t)){
		layer.msg("请输入数字");
		return;
	}else{
		if(t>0&&t<=total){
	
			toPage(t);
		}else{
			layer.msg("请输入有效数字");
		}
				
	}
};
function toPage(page){
	pageNo.val(page);
	Form.submit();
}
function toPageSize(size){
	pageSize.val(size);
	Form.submit();
}
//查询到第一页
function select_submit(){
	//$('#pageNo').val("1");
	toPage(1);
}
