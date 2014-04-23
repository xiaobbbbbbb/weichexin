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
		var openid= $("#openid").val();
		var orgId =$("#orgId").val();
		window.location.href="/drive/serial_detail?serialId="+e+"&openId="+openid+"&orgId="+orgId;
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
			
			<span style="text-decoration: none;color:#FF7F50;text-align: left;font-size: 18px;font-weight: bold;" >${groupName}</span>
			<div style="text-align: left; margin-top: 10px; margin-left: 2%;">
				<a style="padding-top: 8px; padding-bottom: 8px; padding-left: 20px; padding-right: 20px; background-color: #ff6600; color: #ffffff;text-shadow:none;">全部车系</a>
			</div>
			<div style="clear: both;"></div>
			<div style="height: 2px;font-size: 1px;overflow: hidden;width: 100%;background-color: #ff6600;margin-top: 7px;" ></div>
			<div style="clear: both;"></div>
			 <ul >
			  <#list carSerialImagesVO as dto>
			  <li style="border-bottom: solid 1px rgba(197, 196, 177, 0.88);margin-left:-40px;list-style-type: none">
			  <table cellspacing="0" cellpadding="0" style="margin-left:2%;margin-top: 5px;margin-bottom: 5px;" >
			  		<tr  onclick= "detail(${dto.id?c})">
						<td >
							<input type="hidden" id = "openid" value = "${openId}">
							<input type="hidden" id = "orgId" value = "${orgId?c}">
							<#if (dto.imageUrl)??>
								<img  style="max-width:90px;width:110px;height:68px;" src='${ctx}${dto.imageUrl}'/>
							<#else>
								<img  style="max-width:90px;width:110px;height:68px;" src='/static/wcx/no_image1.png'/>
							</#if>
						</td>
						<td style=" text-align: left; padding-left: 5px;" valign="middle" >
							<a style="text-decoration: none;color:#FF7F50;" >${dto.name}</a>
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