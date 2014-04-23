<!DOCTYPE html> 

<html> 

	<head> 

		<title>活动预约</title> 
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta http-equiv="pragma" content="no-cache" />
  		<link rel="stylesheet" href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css">
		<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
		<script src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>
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
            
	            function submitForm(){
	           	 	if(validate()){
						$.ajax({
			                cache: true,
			                type: "POST",
			                url:'/activity/add',
			                data:$('#settingForm').serialize(),// 你的formid
			                async: false,
			                success: function(data) {
				                if(data!="true"){
				                 	alert(data);
				                	
				                }else{
				                	alert("预约成功！");
				                	window.location.href='/activity/history?openid=${openid}&orgCode=${orgCode}&activityId=${(activity.id)?c}';
				                }
				         	}
		          	 	 });
            		}
            
           	 }
            
          function validate(){
           		if($("#name").val()==""){
           			alert( "姓名不能为空！");
           			return false;
           		}if($("#telNo").val()==""){
           			alert("电话不能为空！");
           			return false;
           		}
           		return true;
           }
   
		</script>

	</head> 

	<body> 

	
			<div > 
			<div style="margin-top: 15px;" >
				<div>
				
				<b>活动预约</b>
				<div style="clear:both;" ></div>
				</div>
				<div style="height: 1px;font-size: 1px;overflow: hidden;width: 100%;background-color: #575757;margin-top: 8px;" ></div>
			
				<br>
				<b style="color:#ff6600" >${activity.title}</b><br>
				<form  method="post" id="settingForm">
					<div class="ui-grid-a" style="margin-top: 10px;">
						
						<input type="hidden" id="activityId" name ="activityId" value ="${(activity.id)?c}"/>
						<input type="hidden" name="orgCode" id="orgCode" value="${orgCode}"/>
						<input type="hidden" name="openid" id="openid" value="${openid}"/>
						<div class="ui-block-a" style="width:25%;margin-top: 5px;text-align: right;"><lable for="telNo">有效期：</div>
						<div class="ui-block-b" style="width:70%;margin-top: 5px;text-align: left;">
							 ${activity.btime?string('yyyy年MM月dd日')}~${activity.etime?string('yyyy年MM月dd日')}
						</div>
			    		<div class="ui-block-a" style="width:25%;margin-top: 30px;text-align: right;"><lable for="telNo">手机：</div>
			    		<div class="ui-block-b" style="width:60%;margin-top: 15px;"><input type="text" name="telNo" id="telNo" /></div>
			    		<div class="ui-block-a" style="width:25%;margin-top: 15px;text-align: right;"><lable for="name">姓名：</div>
			    		<div class="ui-block-b" style="width:60%;"><input type="text" name="name" id="name"  /></div>
			    		<div class="ui-block-a" style="width:25%;margin-top: 15px;text-align: right;"><lable for="comment">备注：</div>
			    		<div class="ui-block-b" style="width:60%;"><textarea  name="comment" id="comment"  ></textarea></div>
					</div>
					
					<img class="_button" src="/static/wcx/button-queren.png" onclick="submitForm()"/>
					
					
					

				</form>
		  
			</div>
		</div>
	</body> 

</html> 