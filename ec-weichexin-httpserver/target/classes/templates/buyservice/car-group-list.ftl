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
		var openid= $("#openid").val();
		var orgId =$("#orgId").val();
		window.location.href="/buy/serial_list?groupId="+e+"&openId="+openid+"&orgId="+orgId;
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
			<ul >
			 <hr/>
			  <#list list as dto>
			  <li style="border-bottom: solid 1px rgba(197, 196, 177, 0.88);list-style-type: none">
			  	<table  style="margin-top: 5px;margin-bottom: 5px;">
			  		<tr>
					<td rowspan="3">
						<input type="hidden" id = "openid" value = "${openId}">
						<input type="hidden" id = "orgId" value = "${orgId?c}">
						    <img  style="max-width:90px;width:110px;height:68px;" src='/static/wcx/huodong.png'/>
					</td>
					<td style=" text-align: left; ">
						<a style="text-decoration: none;color:#FF7F50;"  onclick= "detail(${dto.id?c})">${dto.name}</a>
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