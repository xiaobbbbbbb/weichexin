<!DOCTYPE html> 

<html> 
	<head> 
	
		<title>车辆列表</title> 
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
			 ._button{
			 	max-width:300px;
				width:expression(document.body.clientWidth > 300?"300px":"auto" );
				border:0
			 }
			 ._img{
			 	max-width:40px;
				width:expression(document.body.clientWidth > 40?"40px":"auto" );
				border:0
			 }
		</style>
		<script type="text/javascript">
			$(function() {
			});
			
			function update(e){
				window.location.href="/driverHelper/upPage?id="+e;
			}
			function del(e){
			 if (confirm("是否删除？")){
					$.ajax({
		                cache: true,
		                type: "POST",
		                url:'/driverHelper/del',
		                data:{'id':e},
		                async: false,
		                success: function(data) {
			                if(data!="true"){
			                 	alert(data);
			                }else{
			                	location.reload();
			                }
			         	}
	          	 	 });
	          	 }
			}
			
			function details(s){
				window.location.href="/driverHelper/detail?carNo="+s;
			}
		</script>
	
	</head> 

	<body> 
		<div data-role="page" id="home"> 
			<div data-role="content">
				<#list carList as car>	
					<div  class="ui-grid-a ">
      					<div class="ui-block-a" style="text-align:center;padding-top: 11px;" width="70%">
                 			<#if car.count==0><span  style="color:white;background-color:#00BFFF;">无违章</span>&nbsp;&nbsp;&nbsp;${car.carNo}<#else><span onclick="details('${car.carNo}')"><span style="color:white;background-color: #FF7F50;" >未处理${car.count}</span>&nbsp;&nbsp;&nbsp;${car.carNo}</span></#if>
      				 	</div>
       					<div class="ui-block-b" style="text-align:right" width="30%">
      						<img class="_img" src="/static/wcx/update.png" onclick="update(${car.id})"/>&nbsp;&nbsp;&nbsp;<img onclick="del(${car.id})" class="_img" src="/static/wcx/delete.png">
      	
       					</div>
    				</div>
					<hr>
				</#list>
				<#if '${size}'=='0' >您尚未定制违章信息,请返回添加定制车辆</#if>
			</div>
		</div>
	</body> 

</html> 