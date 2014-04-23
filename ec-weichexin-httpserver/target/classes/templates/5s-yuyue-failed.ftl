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
			function yuyue(){
				window.location.href="/myfs/yuyueSet?org_code=${orgCode}&openid=${openid}";
			}
		</script>


	</head> 

	<body> 
		
		<div data-role="page" id="home" data-theme="c">
			<div data-role="content"> 
				<div ><h3>您的预约保养</h3></div>
				
				<hr>
				<div>
					<lable>${reserveDate} ${dayofweek} ${timePoint} </lable><br>
					<lable style="color:red;margin: 15px;display: inline-block;font-size: 20px;">受理失败 </lable><br>
					<a data-role="button" onclick="yuyue()"  style="background:#FFA500" >再次预约</a>
				</div>
			</div>
		</div>
		
	</body> 

</html> 