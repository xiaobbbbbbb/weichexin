<!DOCTYPE html> 

<html> 

<head> 

	<title>车学社</title> 
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
			img{
			 	max-width:300px;
				width:expression(document.body.clientWidth > 300?"300px":"auto" );
				border:0
			 }
		</style>
	<script type="text/javascript">
	$(function() {
	  
	});
	</script>
	
</head> 

<body> 

	<div data-role="page" id="home"> 
		<div data-role="content" > 
			<div>
			<h3>${tip.title}</h3>
			</div>
			<hr>
			<#if tip.image!=""> <div><img src="${ctx}${tip.image}"/> </div></#if>
			<div  style="text-align:left;word-wrap: break-word; word-break: normal;"><span>&nbsp;&nbsp;&nbsp;&nbsp;${tip.content}</span> </div>
	
		</div>
	</div>
</body> 

</html> 