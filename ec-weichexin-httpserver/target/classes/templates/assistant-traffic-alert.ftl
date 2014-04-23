<!DOCTYPE html> 

<html> 
	<head> 
	
		<title>修改车辆</title> 
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta http-equiv="pragma" content="no-cache" />
		    
		
		<link rel="stylesheet" href="https://d10ajoocuyu32n.cloudfront.net/mobile/1.3.1/jquery.mobile-1.3.1.min.css">
		  
		<!-- jQuery and jQuery Mobile -->
		<script src="https://d10ajoocuyu32n.cloudfront.net/jquery-1.9.1.min.js"></script>
		<script src="https://d10ajoocuyu32n.cloudfront.net/mobile/1.3.1/jquery.mobile-1.3.1.min.js"></script> 
		  
		<link href="/mobiscroll/css/mobiscroll.custom-2.6.2.min.css" rel="stylesheet" type="text/css" />
		<script src="/mobiscroll/js/mobiscroll.custom-2.6.2.min.js" type="text/javascript"></script>
		  
		  
		<script type="text/javascript">
			function shorter(){
				var short=$("#select_city").val();
				short=short.split("_")[0];
				$("#carNo").val(short);
				$("#carInfo").show();
			}
			  
			$(function() {
				var  s= '${car.carFrameNo}';
				if(s==""){
					$("#carInfo").hide();
				}
			});
			
		</script>
		<style>
 
			div{
				text-align:center;
			 }
			 ._button{
			 	max-width:300px;
				width:expression(document.body.clientWidth > 300?"300px":"auto" );
				border:0
			 }
		</style>
		<script type="text/javascript">
			
            function submitForm(){
            
				$.ajax({
	                cache: true,
	                type: "GET",
	                url:'/driverHelper/update',
	                data:$('#trafficUpdateForm').serialize(),// 你的formid
	                async: false,
	                success: function(data) {
		                if(data!="true"){
		                 	alert(data);
		                }else{
		                	window.location.href="/driverHelper/carList?uid=${car.wcxUserId}&org_code=${car.orgCode}";
		                }
		         	}
          	 	 });
            
            }
            
            
           var validate = function(){
           		if($("#email").val()==""){
           			alert("邮箱不能为空！");
           			return false;
           		}if($("#carNo").val()==""){
           			alert("车牌不能为空！");
           			return false;
           		}if (!$("#email").val().match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/)) {
           			alert("邮箱格式不正确");
           			return false; 
           		}
           		return true;
           }
		</script>
	
	</head> 

	<body> 
		<div data-role="page" id="home"> 
			<div data-role="content">
				<div>请填写以下违章查询所需信息,当有违章时我们将在第一时间通知您！</div>
				<form  method="get" id="trafficUpdateForm">
					<div data-role="fieldcontain" >
						<select name="select_city" id="select_city" onchange="shorter()">
							<#list volist as vo>
								<optgroup label="${vo.pname}">
									<#list vo.cityList as cityList>
										<option value="${cityList.sname}_${cityList.name}" <#if '${cityList.sname}_${cityList.name}'=='${car.city}'> selected</#if>>${cityList.name}</option>
									</#list>
								</optgroup>
							</#list>
						</select>
					</div >
					<div data-role="fieldcontain" >
						<input type="text" name="carNo" id="carNo" value="${car.carNo}"/>
					</div>
					
					<div  data-role="fieldcontain" id="carInfo">
						<input type="text" name="carFrame" id="carFrame" placeholder="请填车架后4位" value="${car.carFrameNo}"/>
						<input type="hidden" name="uid" id="uid" value="${car.wcxUserId}"/>
						<input type="hidden" name="org_code" id="org_code" value="${car.orgCode}"/>
						<input type="hidden" name="carId" id="carId" value="${car.id}"/>
					</div>
					<img class="_button" src="/static/wcx/button-dingzhi.png" onclick="submitForm()"/>
				</form>
			</div>
		</div>
	</body> 

</html> 