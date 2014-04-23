<!DOCTYPE html> 

<html> 

	<head> 

		<title>活动预约</title> 
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
				var url="/activity/cancel";
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
			
			function golist(){
				window.location.href='/activity/list?openid=${openid}&org_code=${orgCode}';
			}
			function detail(e){
				window.location.href="/activity/detail?id="+e+"&orgCode=${orgCode}&openid=${openid}";
			}
		</script>

	</head> 

	<body> 

	
	<div > 
			<div style="margin-top: 15px;" > 
	
			<a onclick="golist();" style="padding-top:8px;padding-bottom:8px;padding-left:20px;padding-right:20px;background-color:#dddddd;color:#ffffff;text-shadow:none;" >全部活动</a>
			<a style="padding-top:8px;padding-bottom:8px;padding-left:20px;padding-right:20px;background-color:#ff6600;color:#ffffff;margin-left:-5px;text-shadow:none;" >我的预约</a>
			<div style="height: 1px;font-size: 1px;overflow: hidden;width: 100%;background-color: #575757;margin-top: 8px;" ></div>
			
			<ul style="-webkit-padding-start: 5px;">
			  <#if (list??) && (list?size > 0) >
				  <#list list as dto>
					  <li style="border-bottom: solid 1px rgba(197, 196, 177, 0.88);list-style-type: none">
					  	<div style="margin:10px;border:1px solid #cccccc;">
						
						<div style="width:100%;margin:10px;" >
							<div style="float:left;text-align:left;" >
								<span style="color:#ff6600;" ><b>${dto.title}</b></span><br />
								
							</div>
							<a onclick="detail('${dto.activityId}')" style="float:right;padding-top:5px;padding-bottom:5px;padding-left:15px;padding-right:15px;background-color:#2572af;color:#ffffff;margin-right:20px;text-shadow:none;" >查看详情</a>
							<div style="clear:both;" ></div>
						<div style="float:left;text-align:left;" >
							<span style="color:#cccccc;font-size: 12px;text-decoration: none;">有效期：${dto.btime?string("yyyy年MM月dd日")}~${dto.etime?string("yyyy年MM月dd日")}</span>
						</div>
						</div>
		
						<div style="border-bottom:1px solid #cccccc;width:100%;" >
							<#if dto.image!="">
								<img src="${ctx}${dto.image}" style="width:90%;margin:10px;" />
							<#else>
								<img  src='/static/wcx/huodong.png' style="width:90%;margin:10px;" />
							</#if>
							
						</div>
						<span style="float:left;display:block;width:49%;padding-top:15px;padding-bottom:15px;" > 
							<#if dto.status==0>已预约</#if>
							<#if dto.status==1>已确认</#if>
							<#if dto.status==2||dto.status==3>已取消</#if>
						</span>
						<#if dto.status==0||dto.status==1>
							<a  onclick="cancel(${dto.id})" style="float:right;display:block;width:50%;padding-top:15px;padding-bottom:15px;" >取消</a>
						</#if>
						
						<div style="clear:both;" ></div>
					</div>
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