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
            
            function submitForm(){
            
				if(validate()){
					$.ajax({
		                cache: true,
		                type: "POST",
		                url:'/myfs/updateCar',
		                data:$('#settingForm').serialize(),// 你的formid
		                async: false,
		                success: function(data) {
			                if(data!="true"){
			                 	alert(data);
			                }else{
			                	window.location.href="/myfs/setInfo?uid=${uid}&org_code=${orgCode}";
			                }
			         	}
	          	 	 });
	          	  }
            
            
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

	
		<div data-role="page" id="home" > 
			<div data-role="content"> 
				<form action="/myfs/add" method="post" id="settingForm">
					<div class="ui-grid-a">
			    		<div class="ui-block-a" style="width:30%;padding-top: 15px;"><lable for="email">邮箱</div>
			    		<div class="ui-block-b" style="width:60%;"><input type="text" name="email" id="email" placeholder="请输入您的常用邮箱" value="${email}"/></div>
						<input type="hidden" name="org_code" id="org_code" value="${orgCode}"/>
						<input type="hidden"  name="fid" id="fid" value="${fid}"/>
						
			    		<div class="ui-block-a" style="width:30%;margin-top: 15px;"><lable for="carNo">车牌号</div>
			    		<div class="ui-block-b" style="width:60%;"><input type="text" name="carNo" id="carNo" placeholder="请输入您的车牌号"  value="${carNo}"/></div>
					</div>
					
					
					<img class="_button" src="/static/wcx/button-queren.png" onclick="submitForm()"/>

				</form>
		  
			</div>
		</div>
	</body> 

</html> 