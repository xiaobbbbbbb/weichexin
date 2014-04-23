<!DOCTYPE html> 

<html> 

<head> 

	<title>车型列表</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="pragma" content="no-cache" />
	<link rel="stylesheet" href="https://d10ajoocuyu32n.cloudfront.net/mobile/1.3.1/jquery.mobile-1.3.1.min.css">
	  
	<!-- jQuery and jQuery Mobile -->
	<script src="https://d10ajoocuyu32n.cloudfront.net/jquery-1.9.1.min.js"></script>
	<script src="https://d10ajoocuyu32n.cloudfront.net/mobile/1.3.1/jquery.mobile-1.3.1.min.js"></script> 
	  
	<script type="text/javascript">
	function detail(e){
		window.location.href="/drive/test_drive?modelId="+e+"&openId=${openId}&orgId=${orgId}";
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

	<div> 
		<div> 
		
		<table cellspacing="0" cellpadding="0" style="margin-left:2%;margin-top: 10px;margin-bottom: 5px;">
	  		<tr>
				<td>
					<#if imageUrl??>
						<img style="max-width:90px;width:110px;height:68px;" src='${imageUrl}'/>
					<#else>
						<img  style="max-width:90px;width:110px;height:68px;" src='/static/wcx/no_image1.png'/>
						
					</#if>
				
				</td>
				<td style=" text-align: left; padding-left:10px;" valign="middle" >
					<a style="text-decoration: none;color:#FF7F50;">${serial.name}</a>
					<br />
				</td>
			</tr>
			 
		</table>
		
		<div style="text-align: left; margin-top: 10px; margin-left: 2%;">
			<a style="padding-top: 8px; padding-bottom: 8px; padding-left: 20px; padding-right: 20px; background-color: #ff6600; color: #ffffff;text-shadow:none;">全部排量</a>
		</div>
		<div style="clear: both;"></div>
		<div style="height: 2px;font-size: 1px;overflow: hidden;width: 100%;background-color: #ff6600;margin-top: 7px;" ></div>
		<div style="clear: both;"></div>
		
		<ul style="-webkit-padding-start: 5px;">
			  <#if list??>
			  <#list list as dto>
			  <li style="border-bottom: solid 1px rgba(197, 196, 177, 0.88);list-style-type: none">
			  	<table style="margin-top: 5px;margin-bottom: 5px;width:100%;">
			  		<tr >
					<td align="left"  >
						<input type="hidden" id = "openid" value = "${openId}">
						<input type="hidden" id = "orgId" value = "${orgId?c}">
						<b onclick= "detail(${dto.id?c})" >${dto.year}款  ${dto.name}<br/>
						官方价：${dto.refPrice}万<br/>
						<span style="color:#FF7F50;">本店价：${dto.ourPrice}万</span><br/>
						</b>
					
					</td>
					<td style=" text-align: right;padding-right: 5px;" valign="middle" >
						<a onclick="detail(${dto.id?c})" style="float:right;display:block;font-size:14px;font-weight:none;text-decoration: none;color:#ffffff;background-color:#ff6600;text-shadow:none;text-align: center;width:60px;height:28px;line-height:28px;"  >试驾</a>
						 <div style="clear: both;" ></div>
						<a href="tel://${phoneNo}" style="float:right;display:block;font-size:14px;font-weight:none;text-decoration: none;color:#ffffff;background-color:#ff6600;text-shadow:none;text-align: center;width:60px;height:28px;line-height:28px;margin-top: 5px;"  >咨询</a>
					</td>
					
				</tr>
			 
			  	</table>
			  </li>
			  
				
			</#list>
			</#if>
	</ul>
	
		</div>
	</div>
</body> 

</html> 