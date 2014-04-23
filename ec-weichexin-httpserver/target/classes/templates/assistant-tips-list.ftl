<!DOCTYPE html> 

<html> 

<head> 

	<title>优惠活动</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="pragma" content="no-cache" />
	<link rel="stylesheet" href="https://d10ajoocuyu32n.cloudfront.net/mobile/1.3.1/jquery.mobile-1.3.1.min.css">
	  
	<!-- jQuery and jQuery Mobile -->
	<script src="https://d10ajoocuyu32n.cloudfront.net/jquery-1.9.1.min.js"></script>
	<script src="https://d10ajoocuyu32n.cloudfront.net/mobile/1.3.1/jquery.mobile-1.3.1.min.js"></script> 
	  
	<script type="text/javascript">
	function detail(e){
		window.location.href="/tips/detail?id="+e;
	}
	</script>
	<style>
		
		img{
			max-width:80px;
			width:expression(document.body.clientWidth > 80?"80px":"auto" );
			border:0
		}
	</style>
</head> 

<body> 

	<div data-role="page" id="home"> 
		<div data-role="content"> 
			<ul style="-webkit-padding-start: 5px;">
			 <#list dtos as dto>
				<li style="border-bottom: solid 1px rgba(197, 196, 177, 0.88);list-style-type: none"><a style="display: inline-block;line-height: 40px;text-decoration: none;color: #333;" onclick="detail(${dto.id})" >${dto.title}</a></li>		
			</#list>
			</ul>	
		</div>
	</div>
</body> 

</html> 