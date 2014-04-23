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
  
  <link href="/mobiscroll/css/mobiscroll.custom-2.6.2.min.css" rel="stylesheet" type="text/css" />
	<script src="/mobiscroll/js/mobiscroll.custom-2.6.2.min.js" type="text/javascript"></script>
  
  <style>
 	._img{
		max-width:60px;
		width:expression(document.body.clientWidth > 60?"60px":"auto" );
		border:0
	}

 
  </style>
  <script type="text/javascript">
  $(function() {
 	});
 	
 	function doupdate(){
 		window.location.href="/carManager/alert?uid=${uid}&org_code=${orgCode}";
 	}
  </script>


</head> 

<body> 


<div data-role="page" id="home" data-theme="d"> 
  <div data-role="content"> 
  <div  class="ui-grid-a ">
      <div class="ui-block-a" style="text-align:center">
                 	<h3>用车提醒</h3>
       </div>
       <div class="ui-block-b" style="text-align:right">
      <img onclick="doupdate()" class="_img" src="/static/wcx/update.png"  />
      	
       </div>
    </div>
  
  <hr>
    
    <div>
		<table style="text-align:center;margin:auto;border-spacing:10px;">
			<tr>
				<td style="text-align:center" colspan=2><lable style="color:#35A5E6">距下次保养还剩</lable></td>
			</tr>
			<tr>
				<td style="background-color:#F0FFF0"> <#if "${rbydate}"!=""> <p><font size="5" color="#FF6600">${bydate}</font>天</p>
                   <p style="font-size:14px;">${rbydate}到期</p>
                   <#else> 未设置
                   </#if></td>
				<td style="background-color:#F0FFF0"><#if mileage??><p><font size="5" color="#FF6600">${mileage}</font>公里</p>
                   <p style="font-size:14px;">当前里程${currentMileage}公里</p>
                   <#else> 未设置
                   </#if>
                   </td>
			</tr>
			<tr>
				<td style="text-align:left">
					 <lable style="color:#35A5E6">距下次年检还剩</lable>
				</td>
				<td style="text-align:left">
					 <lable style="color:#35A5E6">距下次续保还剩</lable>
				</td>
			</tr>
			<tr>
				<td style="background-color:#F0FFF0"> <#if "${ryearCheckDate}"!=""> <p><font size="5" color="#FF6600">${yearCheckDate}</font>天</p>
                   <p style="font-size:14px;">${ryearCheckDate}到期</p>
                   <#else> 未设置
                   </#if></td>
				<td style="background-color:#F0FFF0"><#if "${rxbdate}"!=""><p><font size="5" color="#FF6600">${xbdate}</font>天</p>
                   <p style="font-size:14px;">${rxbdate}到期</p>
                   <#else> 未设置
                   </#if>
                   </td>
			</tr>
			
			<tr>
				<td style="text-align:left">
					 <lable style="color:#35A5E6">距下次驾照年审还剩</lable>
				</td>
				<td style="text-align:left">
					 <lable style="color:#35A5E6">距下次驾照换证还剩</lable>
				</td>
			</tr>
			<tr>
				<td style="background-color:#F0FFF0"> <#if "${rlicenseYearDate}"!=""> <p><font size="5" color="#FF6600">${licenseYearDate}</font>天</p>
                   <p style="font-size:14px;">${rlicenseYearDate}到期</p>
                   <#else> 未设置
                   </#if></td>
				<td style="background-color:#F0FFF0"><#if "${rchangeDate}"!=""><p><font size="5" color="#FF6600">${changeDate}</font>天</p>
                   <p style="font-size:14px;">${rchangeDate}到期</p>
                   <#else> 未设置
                   </#if>
                   </td>
			</tr>
		
		</table>
	</div>
</div>
</div>
</body> 

</html> 