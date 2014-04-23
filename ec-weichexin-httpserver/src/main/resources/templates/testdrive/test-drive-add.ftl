<!DOCTYPE html> 

<html> 

	<head> 

		<title>试驾预约</title> 
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta http-equiv="pragma" content="no-cache" />
  		<link rel="stylesheet" href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css">
		<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
		<script src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>
  	 	<link href="/static/mobiscroll/css/mobiscroll.custom-2.6.2.min.css" rel="stylesheet" type="text/css" />
		<script src="/static/mobiscroll/js/mobiscroll.custom-2.6.2.min.js" type="text/javascript"></script>
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
            	if(!validate()){
            		return true;
            	}
					$.ajax({
		                cache: true,
		                type: "POST",
		                url:'/buy/add',
		                data:$('#settingForm').serialize(),// 你的formid
		                async: false,
		                success: function(data) {
			                if(data!="true"){
			                 	alert(data);
			                }else{
			                	alert("预约成功！");
			                	window.location.href='/drive/history?openId=${openId}&orgId=${orgId}&modelId=${(model.id)?c}';
			                }
			         	}
	          	 	 });
            
            
            }
            
            
           var validate = function(){
           		if($("#appointTime").val()==""){
           			alert("时间不能为空！");
           			return false;
           		}else if($("#telNo").val()==""){
           			alert("手机不能为空！");
           			return false;
           		}else if($("#telNo").val().length!=11){
		            alert('请输入有效的手机号码！');
		            return false;
		        }else if($("#name").val()==""){
           			alert("姓名不能为空！");
           			return false; 
           		}
           		return true;
           }
           
  
  $(function() {
   		//$("#bydate,#njdate,#xbdate,#licenseDate,#changedate").mobiscroll().date(); 	
   				//初始化日期控件
    var opt = {
        preset: 'date', //日期
        //theme: 'jqm', //皮肤样式
        display: 'modal', //显示方式 
       // mode: 'clickpick', //日期选择模式
        dateFormat: 'yy-mm-dd', // 日期格式
        setText: '确定', //确认按钮名称
        cancelText: '取消',//取消按钮名籍我
        dateOrder: 'yymmdd', //面板中日期排列格式
        dayText: '日', monthText: '月', yearText: '年', //面板中年月日文字
        endYear:2020 //结束年份
    };
    
    $("#appointTime").mobiscroll(opt);
   		
 	});
 	
   
		</script>

	</head> 

	<body> 

	
		<div > 
			<div style="margin-top: 15px;" > 
				 
				<a  style="padding-top:8px;padding-bottom:8px;padding-left:20px;padding-right:20px;background-color:#ff6600;color:#ffffff;text-shadow:none;" >试驾预约</a>
				<a onclick="javascript:window.location.href='/drive/history?openId=${openId}&orgId=${orgId}&modelId=${(model.id)?c}'" style="padding-top:8px;padding-bottom:8px;padding-left:20px;padding-right:20px;background-color:#757575;color:#ffffff;margin-left:-5px;text-shadow:none;" >历史预约</a>
				<div style="height: 1px;font-size: 1px;overflow: hidden;width: 100%;background-color: #575757;margin-top: 8px;" ></div>
				 
				<form  id="settingForm">
					<div class="ui-grid-a" style="margin-top: 5px;">
			    		<div class="ui-block-a" style="width:30%;padding-top: 15px;"><lable for="email">车型：</div>
			    		<div class="ui-block-b" style="width:60%;">
							<input type="hidden" id="modelId" name ="modelId" value ="${(model.id)?c}"/>
							<input readonly="readonly" type="text"   value="${model.name}"  />
						</div>
						<input type="hidden" name="orgId" id="orgId" value="${orgId?c}"/>
						<input type="hidden" name="openId" id="openId" value="${openId}"/>
						
			    		<div class="ui-block-a" style="width:30%;margin-top: 15px;"><lable for="appointTime">时间：</div>
			    		<div class="ui-block-b" style="width:60%;"><input type="text" name="appointTime" id="appointTime" /></div>
			    		<div class="ui-block-a" style="width:30%;margin-top: 15px;"><lable for="telNo">手机：</div>
			    		<div class="ui-block-b" style="width:60%;"><input type="text" name="telNo" id="telNo" /></div>
			    		<div class="ui-block-a" style="width:30%;margin-top: 15px;"><lable for="carNo">姓名：</div>
			    		<div class="ui-block-b" style="width:60%;"><input type="text" name="name" id="name"  /></div>
			    		<div class="ui-block-a" style="width:30%;margin-top: 15px;"><lable for="comment">说明：</div>
			    		<div class="ui-block-b" style="width:60%;"><textarea  name="comment" id="comment"  ></textarea></div>
					</div>
					
					<img class="_button" src="/static/wcx/button-queren.png" onclick="submitForm()"/>
					
					
					

				</form>
		  
			</div>
		</div>
	</body> 

</html> 