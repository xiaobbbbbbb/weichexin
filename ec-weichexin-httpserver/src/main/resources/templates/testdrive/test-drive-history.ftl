<!DOCTYPE html> 

<html> 

	<head> 

		<title>试驾预约</title> 
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta http-equiv="pragma" content="no-cache" />
  
		<!-- jQuery and jQuery Mobile -->
		<link rel="stylesheet" href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css">
		<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
		<script src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>
		<style>
 
			div{
				text-align:center;
			 }
		</style>
		<script>
			function cancel(id){
				var url="/buy/cancel";
					$.ajax({
		                cache: true,
		                type: "POST",
		                url:url,
		                data:{id:id},
		                async: false,
		                success: function(data) {
			                if(data=="true"){
			                	alert("取消成功");
			                 	window.location.reload();
			                }else{
			                	alert("取消失败！");
			                }
			         	}
	          	 	 });
			}
			
			function doAdd(){
				window.location.href='/drive/test_drive?openId=${openId}&orgId=${orgId}&modelId=${modelId}';
			}
		</script>

	</head> 

	<body> 

	
	<div > 
			<div style="margin-top: 15px;" > 
	
			<a onclick="doAdd();" style="padding-top:8px;padding-bottom:8px;padding-left:20px;padding-right:20px;background-color:#757575;color:#ffffff;text-shadow:none;" >试驾预约</a>
			<a style="padding-top:8px;padding-bottom:8px;padding-left:20px;padding-right:20px;background-color:#ff6600;color:#ffffff;margin-left:-5px;text-shadow:none;" >历史预约</a>
			<div style="height: 1px;font-size: 1px;overflow: hidden;width: 100%;background-color: #575757;margin-top: 8px;" ></div>
			<ul style="-webkit-padding-start: 5px;">
			   <#if (list??) && (list?size > 0) >
				  <#list list as dto>
					  <li style="border-bottom: solid 1px rgba(197, 196, 177, 0.88);list-style-type: none">
					  	<table  style="margin-top: 5px;margin-bottom: 5px;width: 100%;">
					  		<tr>
							<td align="left"  >
								${dto_index+1}&nbsp;&nbsp;${dto.serialName} ${dto.year} 款<br/>
								<span style="color:#cccccc;font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;预约时间:${dto.appointTime?string('yyyy-MM-dd')}</span>
							</td>
							<td style=" text-align: right;padding-right: 10px;">
								<#if dto.status=="0"||dto.status=="1">
									<#if dto.status=="1">
										确认去
									</#if>
									<a style="text-decoration: none;color:#FF7F50;" onclick= "cancel(${dto.id?c})">取消预约</a>
								</#if>
								<#if dto.status=="3">确认不去</#if>
								<#if dto.status=="2">已取消</#if>
								<#if dto.status=="4">已完成</#if>
							</td>
						</tr>
					  	</table>
					  </li>
					</#list>
				<#else>
					 暂无预约历史信息 
				</#if>
			</ul>
	
			<div>
			</div>
		</div>
	</div>
	</body> 

</html> 