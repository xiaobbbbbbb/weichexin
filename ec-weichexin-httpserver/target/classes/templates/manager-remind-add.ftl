<!DOCTYPE html> 

<html> 

  <head> 

  <title>用车提醒</title> 
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="pragma" content="no-cache" />
    

  <link rel="stylesheet" href="https://d10ajoocuyu32n.cloudfront.net/mobile/1.3.1/jquery.mobile-1.3.1.min.css">
  
  <!-- jQuery and jQuery Mobile -->
  <script src="https://d10ajoocuyu32n.cloudfront.net/jquery-1.9.1.min.js"></script>
  <script src="https://d10ajoocuyu32n.cloudfront.net/mobile/1.3.1/jquery.mobile-1.3.1.min.js"></script> 
  
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
    
    $("#bydate,#njdate,#xbdate,#licenseDate,#changedate").mobiscroll(opt);
   		
 	});
 	
 	function submitForm(){
            
					$.ajax({
		                cache: true,
		                type: "get",
		                url:'/carManager/addRemind',
		                data:$('#remindForm').serialize(),// 你的formid
		                async: false,
		                success: function(data) {
			                if(data!="true"){
			                 	alert(data);
			                	
			                }else{
			                	window.location.href="/carManager/remindList?uid=${uid}&org_code=${orgCode}";
			                }
			         	}
	          	 	 });
            
            }
  </script>

</head> 

<body> 


<div data-role="page" id="home"> 
  <div data-role="content"> 
	<form name="remindForm" id="remindForm">
		<label for="bydate">保养日期：</label>
         <input type="text"    id="bydate" name="bydate" />
         <label for="bymiles">保养里程：</label>
         <input type="text"    id="bymiles" name="bymiles" />
         <label for="njdate">年检日期：</label>
         <input type="text"    id="njdate" name="njdate" />
         <label for="xbdate">续保日期：</label>
         <input type="text" data-role="datebox"   id="xbdate" name="xbdate" />
         <label for="licenseDate">驾照年审：</label>
         <input type="text" data-role="datebox"   id="licenseDate" name="licenseDate" />
         <label for="changedate">驾照换证：</label>
         <input type="text" data-role="datebox"   id="changedate" name="changedate" />
         <input type="hidden" id="uid" name="uid" value="${uid}">
         <input type="hidden" id="org_code" name="org_code" value="${orgCode}"/>
         <img class="_button" src="/static/wcx/button-queren.png" onclick="submitForm()"/>
	</form>
	
	</div>
    </div>
  </div>

</ul>

</div>
</div>
</body> 

</html> 