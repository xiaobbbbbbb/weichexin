<!DOCTYPE html> 

<html> 

<head> 

	<title>违章详情</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="pragma" content="no-cache" />
	<link rel="stylesheet" href="https://d10ajoocuyu32n.cloudfront.net/mobile/1.3.1/jquery.mobile-1.3.1.min.css">
	  
	<!-- jQuery and jQuery Mobile -->
	<script src="https://d10ajoocuyu32n.cloudfront.net/jquery-1.9.1.min.js"></script>
	<script src="https://d10ajoocuyu32n.cloudfront.net/mobile/1.3.1/jquery.mobile-1.3.1.min.js"></script> 
	  
	<style>
	</style>
	<script type="text/javascript">
	$(function() {
	  
	});
	</script>

</head> 

<body> 

	<div data-role="page" id="home"> 
		<div data-role="content"> 
			<div >
				<h3>${carNo}</h3>
			</div>
			<div>
				<#list itemlist as item>
					<div>
						<p>${item.trafficTime?datetime}</p>
						<p>${item.item}</P>
						<p><#if item.points??>扣${item.points}分</#if><#if item.money??>,罚${item.money}款</#if></p>
						<img src="/static/wcx/mark.png"/>${item.address}
						<hr>
					</div>
				</#list>
			</div>
		
		</div>
	</div>
</body> 

</html> 