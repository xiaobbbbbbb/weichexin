<!DOCTYPE html> 

<html> 

	<head> 

		<title>设置</title> 
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
			
		</style>
		<script type="text/javascript">
			$(function() {
		 	});
		 	
		 	function getTimes(){
		 		var date=$("#dateSelect").val();
		 		
		 		if(date!=""){
		 			$.ajax({
	                cache: true,
	                type: "GET",
	                dateType:"json",
	                url:'/myfs/times',
	                data:{'org_code':'${orgCode}','uid':'${uid}','date':date},// 你的formid
	                async: false,
	                success: function(data) {
	                	
	               				var dataObj=eval("("+data+")");
		                		if(dataObj!=null){
		                			$('#timeSelect').empty();
		                			var options="<option value='' selected='selected'>请选择时间点</option>";
		                			for(var i=0;i<dataObj.length;i++){
		                	 			options+="<option value='"+dataObj[i].timepoint+"'>"+dataObj[i].timepoint+"</option>"; 
		                			}
		                			$('#timeSelect').append(options);
		                			$('#item').html("");
		                			$('.ui-btn-text').children().html('请选择时间点');
		               			}
		         			}
          	 		 });
		 		}
		 	}
		 	
		 	function showDiv(){
		 		var timepoint = $("#timeSelect").val();
		 	 	var date=$("#dateSelect").val();
		 		if(timepoint!=""&&date!=null){
		 			$.ajax({
	                cache: true,
	                type: "GET",
	                dateType:"json",
	                url:'/myfs/worker',
	                data:{'org_code':'${orgCode}','uid':'${uid}','timepoint':timepoint,'date':date},// 你的formid
	                async: false,
	                success: function(data) {
	               				var dataObj=eval("("+data+")");
		                		if(dataObj!=null){
		                	 		$('#item').html("剩余工位"+dataObj.worker+"<p>"+dataObj.item+"</p>"); 
		                		}
		         			}
          	 	 	});
		 		}
		 	}
		 	
		 	
		 	 function submitForm(){
		 	 	if(validatemobile()){
					$.ajax({
		                cache: true,
		                type: "POST",
		                url:'/myfs/addYuyue',
		                data:$('#yuyueForm').serialize(),// 你的formid
		                async: false,
		                success: function(data) {
			                if(data!="true"){
			                 	alert(data);
			                }else{
			                	var dateSelect=$("#dateSelect").val();
			                	window.location.href="/myfs/yuyue?openid=${openid}&org_code=${orgCode}&reserveDate="+dateSelect;
			                }
			         	}
	          	 	 });
	          	 }
            
            }
            
            function validatemobile(){
            	var mobile = $("#contact_tel").val();
            	var dateSelect=$("#dateSelect").val();
            	var timeSelect =$("#timeSelect").val();
            	if(dateSelect.length==0){
            		alert('请选择日期！');
		           return false;
            	}
            	if(timeSelect.length==0){
            		alert('请选择时间点！');
		           return false;
            	}
		        if(mobile.length==0)
		        {
		           alert('请输入手机号码！');
		           return false;
		        }    
		        if(mobile.length!=11)
		        {
		            alert('请输入有效的手机号码！');
		            return false;
		        }
	        
		        var myreg = /^(((1[0-9]{2})|159|153)+\d{8})$/;
		        if(!myreg.test(mobile))
		        {
		            alert('请输入有效的手机号码！');
		            return false;
	       	 	}
	       	 	return true;
    		}
		</script>


	</head> 

	<body> 
		<div data-role="page" id="home" > 
			<div data-role="content"> 
	
		<div >
			<#if '${note}'!=''><div style= "background:#EEDD82;height:50px;line-height:50px;">${note}</div></#if>
				<form  method="post" id="yuyueForm">
				<div >
					<table  style="margin:auto">
						<tr>
							<td width="100px"><lable for="dateSelect">预约日期</td>
							<td height="40px"><select id="dateSelect"  name="dateSelect" onchange = "getTimes()">
			    				<option value="">请选择预约日期</option>
			    				<#list daymap as day>
									<option value=${day.key}>${day.value}</option>
								</#list>
			    			</select></td>
			    			
						</tr>
						<tr>
							<td height="40px"><lable for="timeSelect">预约时间</td>
							<td height="40px">
							<select name="timeSelect" id="timeSelect"  onchange="showDiv()" >
								<option value="">请选择时间点</option>
							</select>
							<input type="hidden" name="org_code" id= "org_code" value="${orgCode}">
							<input type="hidden" name="uid" id= "uid" value="${uid}"></td>
						</tr>
						<tr>
							<td ></td>
							<td ><div id='item'></td>
						</tr>
						<tr>
							<td height="40px"><lable for="contact_tel">手机号<span style="color:red">*</span></td>
							<td height="40px"><input type="text"  name="contact_tel" id="contact_tel" placeholder="请填写您的手机号" /></td>
						</tr>
						<tr>
							<td height="40px"><lable for="car_no">车牌号</td>
							<td height="40px"><input type="text" name="car_no" id="car_no" placeholder="请填写您的车牌号" value="<#if carNo??>${carNo}</#if>"/></td>
						</tr>
					</table>
					
					<img class="_button" id ="submit_img" src="/static/wcx/button-queren.png" onclick="submitForm()"/>
					</div>
				</form>
			</div>
	</body> 

</html> 