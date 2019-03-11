//dialog相关
$(function () {
	//dialog阴影高度
	$(window).on('load resize',function(){
		var H = $(document).height();
		$(".dialog_shadow").css('height',H+"px");         
	}); 
	//dialog隐藏	
	$('.el-dialog__close').on('click',function(){
		$(".dialog_shadow").hide();
		$(this).parents('.el-dialog__wrapper').hide();
	})

})
//dialog显示
function dialog__open(id){
	$(".dialog_shadow").show();
	$('#el-dialog__wrapper_'+id).show();
}

//滚动到顶部
$(function () {
	$(window).on('load resize',function(){
		var H = $(".header").height();
		$(document).scroll(function(){
			var st= $(window).scrollTop();
			if(H < st){
			  $(".back").css("display","block");
			}else{
				$(".back").css("display","none");
			}
		});
	});        
})
//tab切换
$(function(){
	$('#tab-first').click(function(){
	  $(this).addClass('is-active');
	  $(this).siblings().removeClass('is-active');
	  $('#pane-first').css("display","block");
	  $('#pane-first').siblings().css("display","none");
	  $('.el-tabs__active-bar').css('transform','translateY(0px)');
	});
	$('#tab-second').click(function(){
	  $(this).addClass('is-active');
	  $(this).siblings().removeClass('is-active');
	  $('#pane-second').css("display","block");
	  $('#pane-second').siblings().css("display","none");
	  $('.el-tabs__active-bar').css('transform','translateY(70px)');
	});
	$('#tab-third').click(function(){
	  $(this).addClass('is-active');
	  $(this).siblings().removeClass('is-active');
	  $('#pane-third').css("display","block");
	  $('#pane-third').siblings().css("display","none");
	  $('.el-tabs__active-bar').css('transform','translateY(140px)');
	});
	$('#tab-fourth').click(function(){
	  $(this).addClass('is-active');
	  $(this).siblings().removeClass('is-active');
	  $('#pane-fourth').css("display","block");
	  $('#pane-fourth').siblings().css("display","none");
	  $('.el-tabs__active-bar').css('transform','translateY(210px)');
	});
})
//radio效果
$(function(){
	$('.el-radio').click(function(){	
		var _this_name = $(this).find('.el-radio__original').attr('name');
		$(".el-radio__original[name='"+_this_name+"']").parents('.el-radio').removeClass('is-checked');
		$(".el-radio__original[name='"+_this_name+"']").parents('.el-radio__input').removeClass('is-checked');
		$(".el-radio__original[name='"+_this_name+"']").attr('checked',false);
		$(this).addClass('is-checked');
		$(this).find('.el-radio__input').addClass('is-checked');
		$(this).find('.el-radio__original').attr('checked',true);			  	 
	});          
})