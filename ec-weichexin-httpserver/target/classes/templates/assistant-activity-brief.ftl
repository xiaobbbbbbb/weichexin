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
			
	</style>
	<script>
		function detail(){
			window.location.href="/activity/detail?id=${activity.id}&orgCode=${orgCode}&openid=${openid}";
		}
		
		function yuyue(e){
			window.location.href="/activity/yuyue?id="+e+"&orgCode=${orgCode}&openid=${openid}";
		}
		function gohistory(){
			window.location.href="/activity/history?orgCode=${orgCode}&openid=${openid}";
		}
	</script>

</head> 

<body> 

	<div > 
			<div style="margin-top: 15px;" > 
			<a style="padding-top:8px;padding-bottom:8px;padding-left:20px;padding-right:20px;background-color:#ff6600;color:#ffffff;text-shadow:none;" >精彩活动</a>
			<a onclick="gohistory()" style="padding-top:8px;padding-bottom:8px;padding-left:20px;padding-right:20px;background-color:#757575;color:#ffffff;margin-left:-5px;text-shadow:none;" >我的预约</a>
			<div style="height: 1px;font-size: 1px;overflow: hidden;width: 100%;background-color: #575757;margin-top: 8px;" ></div>
			
			 
			<div style="margin:10px;border:1px solid #cccccc;">
				
				<div style="width:100%;margin:10px;" >
					<div style="float:left;text-align:left;" >
						<span style="color:#ff6600;" ><b>${activity.title}</b></span><br />
						
					</div>
					<a onclick="detail()" style="float:right;padding-top:5px;padding-bottom:5px;padding-left:15px;padding-right:15px;background-color:#2572af;color:#ffffff;margin-right:20px;text-shadow:none;" >查看详情</a>
					<div style="clear:both;" ></div>
				<div style="float:left;text-align:left;" >
					<span style="color:#cccccc;font-size: 12px;text-decoration: none;">有效期：${activity.btime?string("yyyy年MM月dd日")}~${activity.etime?string("yyyy年MM月dd日")}</span>
				</div>
				</div>

				<div style="border-bottom:1px solid #cccccc;width:100%;" >
					<#if activity.image!="">
						<img src="${ctx}${activity.image}" style="width:90%;margin:10px;" />
					<#else>
						<img  src='/static/wcx/huodong.png' style="width:90%;margin:10px;" />
					</#if>
				</div>
				
				<a  href="tel://${activity.phoneNo}" style="float:left;display:block;width:49%;padding-top:15px;padding-bottom:15px;border-right:1px solid #cccccc;TEXT-DECORATION:none;" >电话咨询</a>
				<#if activityAppoint??>
					<a style="float:left;display:block;width:50%;padding-top:15px;padding-bottom:15px;" >已预约</a>
				<#else>
					<#if activity.needAppoint==true>
						<a  onclick="yuyue(${activity.id})" style="float:left;display:block;width:50%;padding-top:15px;padding-bottom:15px;" >活动预约</a>
					<#else>
						<a style="float:left;display:block;width:50%;padding-top:15px;padding-bottom:15px;" >无需预约</a>
					</#if>
				</#if>
				<div style="clear:both;" ></div>
			</div>
		</div>
	</div>
</body> 

</html> 