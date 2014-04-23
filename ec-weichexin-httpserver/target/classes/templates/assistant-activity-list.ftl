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
	  
	<script type="text/javascript">
	function detail(e){
		window.location.href="/activity/brief?id="+e+"&orgCode=${orgCode}&openid=${openid}";
	}
	function gohistory(){
		window.location.href="/activity/history?orgCode=${orgCode}&openid=${openid}";
	}
	</script>
	<style>
		div{
			text-align:center;
		}
		img{
			max-width:80px;
			width:expression(document.body.clientWidth > 80?"80px":"auto" );
			border:0
		}
	</style>

</head> 

<body> 

	<div > 
			<div style="margin-top: 15px;" > 
	
			<a style="padding-top:8px;padding-bottom:8px;padding-left:20px;padding-right:20px;background-color:#ff6600;color:#ffffff;text-shadow:none;" >全部活动</a>
			<a onclick="gohistory();"  style="padding-top:8px;padding-bottom:8px;padding-left:20px;padding-right:20px;background-color:#dddddd;color:#ffffff;margin-left:-5px;text-shadow:none;" >我的预约</a>
			<div style="height: 1px;font-size: 1px;overflow: hidden;width: 100%;background-color: #575757;margin-top: 8px;" ></div>
			
			<ul style="-webkit-padding-start: 5px;">
			  <#list dtos as dto>
			  <li style="border-bottom: solid 1px rgba(197, 196, 177, 0.88);list-style-type: none">
			  	<table onclick= "detail(${dto.id})" style="margin-top: 5px;margin-bottom: 5px;">
			  		<tr>
					<td rowspan="3">
						<#if dto.image!=""> 				    
						    <img  style="max-width:90px;width:110px;height:68px;" src='${ctx}${dto.image}'/>
						<#else>
						    <img  style="max-width:90px;width:110px;height:68px;" src='/static/wcx/huodong.png'/>
						</#if> 
					
					</td>
					<td style=" text-align: left; ">
						<a style="text-decoration: none;color:#FF7F50;"  onclick= "detail(${dto.id})">${dto.title}</a>
					</td>
					
					
				</tr>
			 	<tr>
					<td style=" text-align: left; ">
			 			<span style="color:#cccccc;font-size: 12px;">${dto.btime?string("yyyy年MM月dd日")}~${dto.etime?string("yyyy年MM月dd日")}<span/>
			 		</td>
					
				</tr>
			  	<tr>
					
					<td style="color: #666; text-align: left;font-size: 12px; ">
			 			<#if dto.content?length gt 15> 
						    ${dto.content?substring(0, 15)}...
						<#else>
						  ${dto.content}
						</#if> 
			 		</td>
					
				</tr>
			  	</table>
			  </li>
			  
				
			</#list>
	</ul>
	
			<div>
			</div>
		</div>
	</div>
</body> 

</html> 