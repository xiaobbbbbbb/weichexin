<!DOCTYPE html> 

<html> 

<head> 

	<title>车型展示</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="pragma" content="no-cache" />
	<link rel="stylesheet" href="https://d10ajoocuyu32n.cloudfront.net/mobile/1.3.1/jquery.mobile-1.3.1.min.css">
	  
	<!-- jQuery and jQuery Mobile -->
	<script src="https://d10ajoocuyu32n.cloudfront.net/jquery-1.9.1.min.js"></script>
	<script src="https://d10ajoocuyu32n.cloudfront.net/mobile/1.3.1/jquery.mobile-1.3.1.min.js"></script> 
	  
	<script type="text/javascript">
	function yuyue(e){
		window.location.href="/drive/test_drive?modelId="+e+"&openId=${openId}&orgId=${orgId}";
	}
	</script>
	<style>
			
			body{padding:0px;top:0px;left:0px;margin:0px;text-shadow:none;}
			.clear{clear:both;}
			.bg{background-image:url("../image/bg.png");}

			.t_left{float:left;margin-top:10px;}
			.t_left img{margin-left:1%;}

			.t_right{float:left;margin-top:10px;}


			.orange{color:#ff6600;}
			.red{color:red;}
			.c{background-color:#dadada;width:94%;margin-left:2%;padding-left:2%;margin-top:10px;min-height:40px;line-height:40px;}
			.c img{margin-top:7px;float:left;margin-right: 10px;}
			.c_t{margin-left:2%;height:35px;line-height:35px;width:24%;background-color:#575757;color:#ffffff;text-align:center;margin-top:10px;}
			.img{width:30%;margin-left:2%;margin-top:10px;}

		</style>
</head> 

<body> 

	<div> 
		<div > 
		
		<!-- 外围 -->
		<div class="bg" >
			<!-- 头部 -->
				<table cellspacing="0" cellpadding="0" style="margin-left:2%;margin-top: 5px;" >
					<tr>
						<td>
							<#if imageUrl??>
								<img style="max-width:100px;width:100px;height:88px;" src="${imageUrl}" />
							<#else>
								<img style="max-width:100px;width:100px;height:88px;" src='/static/wcx/no_image3.png' />
							</#if>
						</td>
						<td style=padding-left:2%;" valign="middle" >
							<span class="orange">${carserial.name} ${model.year}款 ${model.name}</span><br/>
							官方价：${showmodel.refPrice}万<br/>
							本店价：<span class="red" >${showmodel.ourPrice}万</span>
						</td>
					</tr>
				</table>
			<!-- 头部尾巴 -->

			<div style="text-align: left; margin-top: 10px; margin-left: 2%;">
				<a style="padding-top: 8px; padding-bottom: 8px; padding-left: 20px; padding-right: 20px; background-color: #ff6600; color: #ffffff;text-shadow:none;">车型信息</a>
			</div>
			<div style="clear: both;"></div>
			<div style="height: 2px;font-size: 1px;overflow: hidden;width: 100%;background-color: #ff6600;margin-top: 7px;" ></div>
			<div style="clear: both;"></div>
			<!-- 标签内容 -->
			
			<#if youhui??>
				<#list youhui as dto> 
					<div class="c" >
						<#if li??>
							<img src="/static/wcx/car_li.png" />
						</#if>
						<img src="/static/wcx/car_cu.png" />
						<span  class="red" style="text-shadow:none;"  >${dto.comment}</span>
					</div>
				</#list>
			</#if>
			<!-- 标签内容尾巴-->
			
			<!-- 标签内容 -->
			<#if showmodel.colors??>
			<div class="c"  >
					<span style="" >颜色：<span>
					${showmodel.colors}
			</div >
			</#if>
			<!-- 标签内容尾巴-->

			<!-- 具体内容 -->
			<div>
				<div class="c_t" style="text-shadow:none;" >图集</div>
				<div style="height: 1px;font-size: 1px;overflow: hidden;width: 100%;background-color: #565656;margin-top: 0px;" ></div>
				<div>
					<#if images??>
						<#list images as dto>
						<img class="img" style="height:80px;" src="${ctx}${dto.url}" />	
						</#list>
					<#else>
						<img class="img"  src='/static/wcx/no_image2.png' />	
					</#if>
				</div>
			</div>
			<!-- 具体内容尾巴-->
		<br />
		<a href="tel://${phoneNo}"  style="float:left;display:block;width:49%;margin-right:1%;padding-top:10px;padding-bottom:10px;background-color: #ff6600;color: #ffffff;text-align: center;text-decoration: none;text-shadow:none;" >电话咨询</a>
		<a  onclick="yuyue('${model.id?c}')" style="float:left;display:block;width:49%;margin-left:1%;padding-top:10px;padding-bottom:10px;background-color: #ff6600;color: #ffffff;text-align: center;text-shadow:none;" >试驾预约</a>
		</div>
		<!-- 外围尾巴-->
		
		</div>
	</div>
</body> 

</html> 