<!DOCTYPE html> 

<html> 

  <head> 

	<title>Collapsible Content Demo</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="pragma" content="no-cache" />
	
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
		<script src="http://code.highcharts.com/highcharts.js"></script>
	<style>
	 
	</style>
	<script type="text/javascript">
	
	
		var cosPoints1= [];
		var cosPoints2 = [];
		var ticks= [];
		<#if volist??>
			<#list volist as list>
				cosPoints1.push(${list.totalMileage});//里程
				cosPoints2.push(${list.totalOil});//油耗
				ticks.push('${list.fromDay?string('yyyy-MM-dd')}');
			</#list>
		</#if>
				  
	$(function () {
    $('#container').highcharts({
        chart: {
            type: 'line'
        },
        title: {
            text: ''
        },
        
        xAxis: {
            categories: ticks
        },
        
        tooltip: {
            enabled: false,
            formatter: function() {
                return '<b>'+ this.series.name +'</b><br/>'+
                    this.x +': '+ this.y +'°C';
            }
        },
        plotOptions: {
            line: {
                dataLabels: {
                    enabled: true
                },
                enableMouseTracking: false
            }
        },
        series: [{
            name: '里程',
            data: cosPoints1
        }, {
            name: '油耗',
            data: cosPoints2
        }]
    });
});				
		function  mongthReport(){
			window.location.href ="/carManager/carReport?openid=${openid}&org_code=${orgCode}&flag=2";
		}
		</script>


</head> 

	<body> 
	
				<div >
					<div style="width:50%;float:left">周行车报告</div>
			
					<div ><a onclick="mongthReport()">月行车报告</a></div>
				</div>
				<hr>
				<#if vo??>
				<div>
				
					<#if driveTime??>
					<div style="width:33.3%;float:left">
						行驶时常
						<p> ${driveTime}</P>
					</div>
					</#if>
					<#if vo.totalMileage??>
					<div style="width:33.3%;float:left">
						本周里程
						<p>${vo.totalMileage?string.number}公里</p>
					</div>
					</#if>
					<#if vo.oilMoney??>
					<div style="width:33.3%;float:left">
						本周油费
						<p>${vo.oilMoney?string.number}元</p>
					</div>
					</#if>
					<#if vo.avgOil??>
					<div style="width:33.3%;float:left">
						本周油耗
						<p>${vo.avgOil?string.number}升/百公里</p>
					</div>
					</#if>
					<#if vo.avgSpeed??>
					<div >
						平均速度
						<p>${vo.avgSpeed?string.number}公里/小时</p>
					</div>
					</#if>
				</div>
				<div>
				</div>
				<div>
				
				<hr>
				</div>
				</#if>
				
				<div id="container" style="min-width:300px;max-height:400px;"></div>
		
	</body> 

</html> 