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
			 
		</style>
		<script type="text/javascript">
			$(function() {
		 	});
		</script>


	</head> 

	<body> 
		
		<div data-role="page" id="home" data-theme="c">
			<div data-role="content" class="ui-body ui-body-c ui-corner-all"> 
				<div ><h3>您的预约保养</h3></div>
				
				<hr>
				<div>
					<lable>${reserveDate}  ${dayofweek} ${timePoint}</lable><br>
					<lable style="color: green; margin: 15px; display: inline-block; font-size: 20px;">正在受理中... </lable><br>
					<lable >若受理我们会在第一时间用微信通知您，谢谢！</lable>
					<input type="button"  disabled="true" style="background-color:#808080" value="再次预约"/>
				</div>
			</div>
		</div>
		
	</body> 

</html> 