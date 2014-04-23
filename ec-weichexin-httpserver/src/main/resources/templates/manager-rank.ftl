<!DOCTYPE html> 

<html> 

	<head> 

		<title>环保达人</title> 
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
				vertical-align :center;
			 }
			 
			
			.rank{
				max-width:60px;
				width:expression(this.width>40px?"60px":"auto");
				border:0;

			}
			
			._header{
				max-width:80px;
				width:expression(this.width>80px?"80px":"auto");
				border:0;
			}
			.carNo{
				max-width:50px;
				width:expression(this.width>50px?"50px":"auto");
				border:0;
			}
			* {margin:0px;padding:0px;}
			.cont {margin:10px auto;padding:0px 0px;}
			.cont img {vertical-align:middle;}
			.score {margin:20px auto;padding:5px 0px;}
		</style>
		<script type="text/javascript">
			$(function() {
		 	});
		</script>


	</head> 

	<body> 

	
		<div data-role="page" id="home" data-theme="d"> 
			<div data-role="content" class="cont"> 
				<div class="ui-body">
				<#if vo.myRank??><#if vo.myRank.order??>我的排名：${vo.myRank.order} 名  </#if>  <#if vo.myRank.score??>我的得分：${vo.myRank.score}分</#if>
				<#elseif "${deviceNo}"!="">您上周没有行车数据,暂无排名<#else>
				</#if></div>
				<div class="ui-grid-b" >
		    		<div class="ui-block-a" ><img class="_header" src="/static/wcx/rank.png"/></div>
		    		<div class="ui-block-b" ><img class="_header" src="/static/wcx/car.png"/></div>
		    		<div class="ui-block-c" ><img class="_header" src="/static/wcx/point.png"/></div>
				</div>
		  
				
				<#if vo.list??>
				<#list vo.list as vo>
					<div class="ui-grid-b cont">
						<div class="ui-block-a cont" stlye="width:30%">
						<#if vo.order==1><img class="rank" src="/static/wcx/rank1.png"/>
						<#elseif vo.order==2><img class="rank" src="/static/wcx/rank2.png"/>
						<#elseif vo.order==3><img class="rank" src="/static/wcx/rank3.png"/>
						<#else><font size="5">${vo.order}</font>
						</#if></div>
						<div class="ui-block-b " stlye="width:40%;"><img class="carNo" src='${vo.logoUrl}'/><p>${vo.carNo}</p></div>
						<div class="ui-block-c score" stlye="width:30%"><font size="5">${vo.score}</font></div>
					</div>
					<hr width="90%" color="#DDDDDD">
			　 	</#list>
				</#if>
			</div>
		</div>
	</body> 

</html> 