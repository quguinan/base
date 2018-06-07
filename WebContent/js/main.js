// JavaScript Document
$(function(){
    //滚动图导航点
	var $ul = $("#i_d_p");			
	var kuand = $ul.width();
	var kddec = kuand/2;
	$("#i_d_p").css({"margin-left":"-" + kddec + "px"})
	/*头部导航*/
	$(".banner .xla .dianj").click(function(){
		$(this).parent("li").children("div.zilan").slideDown();
		$(this).parent("li").siblings().children("div.zilan").slideUp();
	});
	
	$(".banner .close").click(function(){
		$(this).css({"display":"none"});
		$(".banner .open").removeClass("hide");
		$("#img1").rotate({animateTo:0});			
		$("#img2").rotate({animateTo:360})
		$(".banner .xla").slideDown();
	});
	$(".banner .open").click(function(){
		$(this).addClass("hide");
		$(".banner .close").css({"display":"block"});
		$("#img2").rotate({animateTo:0});			
		$("#img1").rotate({animateTo:360})
	    $(".banner .xla").slideUp();
		$(".banner .xla .zilan").slideUp();
		$("#ig1").rotate({animateTo:0});
		$("#ig2").rotate({animateTo:0});
		$("#ig3").rotate({animateTo:0});
		$("#ig4").rotate({animateTo:0});
		$("#ig5").rotate({animateTo:0});
	});
	
	$(".banner .xla .dj1").click(function(){
		$("#ig1").rotate({animateTo:90});			
		$("#ig2").rotate({animateTo:0});
		$("#ig3").rotate({animateTo:0});
		$("#ig4").rotate({animateTo:0});
		$("#ig5").rotate({animateTo:0});
	});
	$(".banner .xla .dj2").click(function(){
		$("#ig2").rotate({animateTo:90});			
		$("#ig1").rotate({animateTo:0});
		$("#ig3").rotate({animateTo:0});
		$("#ig4").rotate({animateTo:0});
		$("#ig5").rotate({animateTo:0});
	});
	$(".banner .xla .dj3").click(function(){
		$("#ig3").rotate({animateTo:90});			
		$("#ig1").rotate({animateTo:0});
		$("#ig2").rotate({animateTo:0});
		$("#ig4").rotate({animateTo:0});
		$("#ig5").rotate({animateTo:0});
	});
	$(".banner .xla .dj4").click(function(){
		$("#ig4").rotate({animateTo:90});			
		$("#ig1").rotate({animateTo:0});
		$("#ig2").rotate({animateTo:0});
		$("#ig3").rotate({animateTo:0});
		$("#ig5").rotate({animateTo:0});
	});
	$(".banner .xla .dj5").click(function(){
		$("#ig5").rotate({animateTo:90});			
		$("#ig1").rotate({animateTo:0});
		$("#ig2").rotate({animateTo:0});
		$("#ig3").rotate({animateTo:0});
		$("#ig4").rotate({animateTo:0});
	});
	/*滚动图*/
	var $header = $(".header");
	var ww = $header.width();
	var swiper1 = $(".swiper1");
	var slide = $(".swiper1 .swiper-slide");
	var navh = ((340/740)*ww);
	swiper1.height(navh);
	slide.height(navh);
	/*图片墙*/
	var $pic = $(".case_list_body_pic img");
	var idw = ((334/740)*ww);
	var iw = $pic.width(idw);
	var ih = ((439/334)*iw);
	$pic.height(ih);
	/*返回顶部*/
	$(document).on('click', '#dianj a', function(e){
		e.preventDefault();
		$("html, body").animate({ scrollTop: $('#bktop').offset().top + "px" }, { duration:800, easing: "swing" }); 
								
	});
	/*留言成功*/
	//var $success = $(".success");
	//var bottom = $(".bottom");
	//var $window = $(window);
	//var wh = $window.height();
	//var hh = (ww*(84/740));	
	//var bh = bottom.height();
	//var mh = (wh-hh-bh-35);
	//$success.height(mh);
})




