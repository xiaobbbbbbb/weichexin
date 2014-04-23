<!DOCTYPE html> 

<html> 

<head> 

	<title>优惠活动</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="pragma" content="no-cache" />
	<link rel="stylesheet" href="https://d10ajoocuyu32n.cloudfront.net/mobile/1.3.1/jquery.mobile-1.3.1.min.css">
	  
	<!-- jQuery and jQuery Mobile -->
	<script src="https://d10ajoocuyu32n.cloudfront.net/jquery-1.9.1.min.js"></script>
	<script src="https://d10ajoocuyu32n.cloudfront.net/mobile/1.3.1/jquery.mobile-1.3.1.min.js"></script> 
	
	 <style>
 
			div{
				text-align:center;
			 }
			
			img{
			 	max-width:300px;
				width:expression(document.body.clientWidth > 300?"300px":"auto" );
				border:0
			 }
	</style>
	<script>
		function yuyue(e){
			window.location.href="/activity/yuyue?id="+e+"&openid=${openid}&orgCode=${orgCode}";
		}
	</script>

</head> 

<body> 

	<div > 
		<div style="margin-top: 15px;" > 
			<div>
				
				
				<b>活动详情</b>
				<div style="clear:both;" ></div>
			</div>
			<div style="height: 1px;font-size: 1px;overflow: hidden;width: 100%;background-color: #575757;margin-top: 8px;margin-bottom: 5px;" ></div>
			
			 
			<b style="color:#ff6600" >${activity.title}</b><br>
			<span style="color:#cccccc;font-size: 12px;text-decoration: none;">有效期：${activity.btime?string("yyyy年MM月dd日")}~${activity.etime?string("yyyy年MM月dd日")}</span>
			<#if activity.image!="">
				<img src="${ctx}${activity.image}" style="width:90%;margin:5%" />
			<#else>	
				<img src='/static/wcx/huodong.png' style="width:90%;margin:5%;" />
			</#if>
			<br>
			<div style="width:90%;margin:5% auto;text-align:left;"  >
			活动内容：<br />
			&nbsp;&nbsp;${activity.content}
			</div>


			<a   href="tel://${activity.phoneNo}" style="float:left;display:block;width:48%;padding-top:10px;padding-bottom:10px;background-color:#ff6600;color:#ffffff;TEXT-DECORATION:none;text-shadow:none;" >电话咨询</a>
			<#if activityAppoint??>
				<a  style="float:right;display:block;width:48%;padding-top:10px;padding-bottom:10px;background-color:#ff6600;color:#ffffff;text-shadow:none;" >已预约</a>
			<#else>
				<#if activity.needAppoint==true>
					<a onclick="yuyue(${activity.id})" style="float:right;display:block;width:48%;padding-top:10px;padding-bottom:10px;background-color:#ff6600;color:#ffffff;text-shadow:none;" >活动预约</a>
				<#else>
					<a  style="float:right;display:block;width:48%;padding-top:10px;padding-bottom:10px;background-color:#ff6600;color:#ffffff;text-shadow:none;" >无需预约</a>
				</#if>
			</#if>
		</div>
		</div>
	</div>
</body> 

</html> 