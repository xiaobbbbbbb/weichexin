<!DOCTYPE html> 

<html> 

	<head> 

		<title>车信</title> 
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
				margin-top:10px;
			 }
			 img{
			 	max-width:40px;
				width:expression(document.body.clientWidth > 40?"40px":"auto" );
				border:0;
				margin-right:100px;
			 }
		</style>
		<script type="text/javascript">
			function update(){
				window.location.href="/myfs/updatePage?id=${fid}";
			}
		</script>


	</head> 

	<body> 

	
		<div data-role="page" id="home" data-theme="d"> 
			<div data-role="content"> 
				<#if carSource==1>
				<div  class="ui-grid-a ">
      					<div class="ui-block-a" style="text-align:center" width="70%">
                 			<h3>基本资料</h3>
      				 	</div>
       					<div class="ui-block-b" style="text-align:right" width="30%">
      						<img  src="/static/wcx/update.png" onclick="update()"/>
       					</div>
    				</div>
				<#else>
				<div><h3>基本资料</h3></div>
				</#if>
				<hr>
				<table style="width: 100%;">
						<tr>
							<td style="width: 30%;text-align: right;padding-right: 10px;font-size: 18px;">邮箱</td>
							<td style="width: 70%;text-align: left;padding-right: 10px;">${email}<br/> <hr style="border-color:#FFFFFF"/></td>
						</tr>
						<tr>
							<td style="width: 30%;text-align: right;padding-right: 10px;font-size: 18px;">车牌号</td>
							<td style="width: 70%;text-align: left;padding-right: 10px;">${carNo}<br/> <hr style="border-color:#FFFFFF"/></td>
						</tr>
						<tr>
							<td style="width: 30%;text-align: right;padding-right: 10px;font-size: 18px;">车猫号</td>
							<td style="width: 70%;text-align: left;padding-right: 10px;"> ${deviceNo}<br/> <hr  style="border-color:#FFFFFF"/></td>
						</tr>
						<tr>
							<td style="width: 30%;text-align: right;padding-right: 10px;font-size: 18px;">车型</td>
							<td style="width: 70%;text-align: left;padding-right: 10px;">${carModel}<br/> <hr style="border-color:#FFFFFF"/></td>
						</tr>
					</table>
				
			</div>
		</div>
	</body> 

</html> 