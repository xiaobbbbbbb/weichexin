<!DOCTYPE html>

<html>

<head>

<title>车系列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="pragma" content="no-cache" />
<link rel="stylesheet" href="https://d10ajoocuyu32n.cloudfront.net/mobile/1.3.1/jquery.mobile-1.3.1.min.css">

<!-- jQuery and jQuery Mobile -->
<script src="https://d10ajoocuyu32n.cloudfront.net/jquery-1.9.1.min.js"></script>
<script src="https://d10ajoocuyu32n.cloudfront.net/mobile/1.3.1/jquery.mobile-1.3.1.min.js"></script>

<script type="text/javascript">
	function detail(e){
		window.location.href="/buy/serial_detail?serialId="+e+"&openId=${openId}&orgId=${orgId?c}";
	}
	
	function actDetail(e){
		window.location.href="/activity/brief?id="+e+"&orgCode=${orgCode}&openid=${openId}";
	}
	
	function getWidth(){
		return document.body.clientWidth;
	 }

	function getHeight(){
		return document.body.clientHeight*0.4;
	 }
	var index =0;
	$(function(){
		var length = ${activityList?size};
		 $("#img_div").css("height",getHeight());

		  $("#float").css("top",getHeight()-30);
		  $("#float").css("left",(getWidth()-length*14)/2);
		  $(".activity_img img").each(function(){
			  $(this).css("height",getHeight());
		  });
		 
		 var length = ${activityList?size};
		 if(length>1){
		  setInterval(c,5000);
		 }
		
	});
	      	
	function c(){
		index++;
		var length = ${activityList?size};
		if(index==length)
			index=0;
		changeImage(index);
	}
	
	function changeImage(e){
		var length = ${activityList?size};
		for(var i = 0;i<length;i++){
			if(e==i){
				$("#activity_"+(i+1)).show();
				$("#act_"+(i+1)).attr("src","/static/wcx/2.png");
			}else{
				$("#activity_"+(i+1)).hide();
				$("#act_"+(i+1)).attr("src","/static/wcx/1.png");
			}
		}
	
	}
	</script>
<style>
div {
	text-align: center;
}
/*	img{
			max-width:80px;
			width:expression(document.body.clientWidth > 80?"80px":"auto" );
			border:0
		}*/
</style>

</head>

<body>

	<div data-role="page" id="home">

		<div id="img_div"
			style="width: 100%; height: 100px; text-align: left; overflow: hidden; ">

			<div></div>
			
			<#list activityList as dto >
				<#if dto_index==0>
					<div class="activity_img" id="activity_${dto_index+1}"
						style="display:">
						<img onclick= "actDetail(${dto.id?c})" style="width: 100%;height:100%;" src='${ctx}${dto.image}' />
						<div style="width: 100%;text-align:left;height:40px;line-height:40px;text-shadow:none;color:#ffffff;position: absolute;margin-top: -45px;background-image: url('/static/wcx/act_bg.png');">
							&nbsp;&nbsp;${dto.title}
						</div>
					</div>
				<#else>
					<div class="activity_img" id="activity_${dto_index+1}"
						style="display: none">
						<img onclick= "actDetail(${dto.id?c})" style="width: 100%;height:100%;" src='${ctx}${dto.image}' />
						<div style="width: 100%;text-align:left;height:40px;line-height:40px;text-shadow:none;color:#ffffff;position: absolute;margin-top: -45px;background-image: url('/static/wcx/act_bg.png');">
							&nbsp;&nbsp;${dto.title}
						</div>
					</div>
				</#if> 
			</#list>
			<#if activityList?? && activityList?size == 0>
				<div class="activity_img">
					<img style="width: 100%;height:100%;" src='/static/wcx/seriallist-noactivity.png' />
				</div>
			</#if>
			<div style="clear: both;"></div>
		</div>

		<div>
			<div style="text-align: left; margin-top: 20px; margin-left: 2%;">
				<a style="padding-top: 8px; padding-bottom: 8px; padding-left: 20px; padding-right: 20px; background-color: #ff6600; color: #ffffff;text-shadow:none;">${groupName}</a>
			</div>
			<div style="clear: both;"></div>
			<div style="height: 2px;font-size: 1px;overflow: hidden;width: 100%;background-color: #ff6600;margin-top: 7px;" ></div>
			<div style="clear: both;"></div>
			<div>
				<#list list as dto> 
				<input type="hidden" id="openid" value="${openId}">
				<input type="hidden" id="orgId" value="${orgId?c}">
				<div onclick="detail(${dto.id?c})"
					style="float: left; width: 31%; margin-left: 2%; text-align: center; margin-top: 20px; margin-bottom: 10px;">
					<div style="position: absolute;">
						<#if dto.hasYouhui>
						<img src="/static/wcx/car_cu_s.png">
						</#if>
						<#if dto.hasGift>
						<img src="/static/wcx/car_li_s.png">
						</#if>
					</div>
					<#if dto.imageUrl??>
						<img  style="width: 100%;height:80px;border: 1px solid #cccccc;" src='${ctx}${dto.imageUrl}' />
					<#else>
						<img style="width: 100%;height:80px;" src='/static/wcx/no_image1.png' />
					</#if>
					<br /> <a
						style="text-decoration: none; color: #2572af;">${dto.name}</a>
				</div>
				</#list>
				
				 
				
				<div style="clear: both;"></div>

			</div>
		</div>


		<div id="float" style="position: absolute; text-align: center;">
			<#list activityList as dto >
				<#if dto_index==0> 
					<img  name="act_img" id="act_${dto_index+1}"  src="/static/wcx/2.png">
				<#else> 
					<img  name="act_img" id="act_${dto_index+1}"  src="/static/wcx/1.png">
				</#if>
			</#list>

		</div>

		 
	</div>
	</div>
</body>

</html>
