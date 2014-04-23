<!DOCTYPE html> 

<html> 

  <head> 

	<title>行车</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="pragma" content="no-cache" />
	
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
		<script src="http://code.highcharts.com/highcharts.js"></script>
	<style>
	 img{
		max-width:30px;
		width:expression(document.body.clientWidth > 30?"30px":"auto" );
		border:0
	}
	</style>
	<script type="text/javascript">
	
	
		var cosPoints1= [];
		var cosPoints2 = [];
		var ticks= [];
		var ticks2=[];	
		<#if volist??>
			<#list volist as list>
				cosPoints1.push(${list.totalMileage});//里程
				cosPoints2.push(${list.totalOil});//油耗
				ticks.push('${list.fromDay?string('yyyy-MM-dd')}');
			</#list>
		</#if>
		for(i=0;i<ticks.length;i++){
			if(i!=0&&i!=ticks.length-1){
				ticks2[i]="";
			}else {
			 ticks2[i]=ticks[i];
			 }
		}
			  
	$(function () {
    $('#container').highcharts({
        chart: {
            type: 'line'
        },
        title: {
            text: ''
        },
        
        xAxis: {
            categories: ticks2
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
		function  weekReport(){
			window.location.href ="/carManager/carReport?openid=${openid}&org_code=${orgCode}&flag=0";
		}
		</script>


</head> 

	<body> 
	
				<div>
					<table style="text-align:center;margin:auto;border-spacing:20px;">
						<th>
							<td>
								<a onclick="weekReport()"><b>周行车报告</b></a>
								<hr>
							</td>
							<td>
								<b>月行车报告</b>
								<hr color="#FF6600">
							</td>
						<th>
					</table>
				</div>
				<#if vo??>
				<div>
				
					<#if driveTime??>
					<div style="width:33.3%;float:left">
						<img src ="/static/wcx/time.png">行驶时长
						<p> ${driveTime}</P>
					</div>
					</#if>
					<#if vo.totalMileage??>
					<div style="width:33.3%;float:left">
						<img src ="/static/wcx/mile.png">上周里程
						<p>${vo.totalMileage?string.number}公里</p>
					</div>
					</#if>
					<#if vo.oilMoney??>
					<div style="width:33.3%;float:left">
						<img src ="/static/wcx/oilfei.png">上周油费
						<p>${vo.oilMoney?string.number}元</p>
					</div>
					</#if>
					<#if vo.avgOil??>
					<div style="width:33.3%;float:left">
						<img src ="/static/wcx/oil.png">上周油耗
						<p>${vo.avgOil?string.number}升/百公里</p>
					</div>
					</#if>
					<#if vo.avgSpeed??>
					<div >
						<img src ="/static/wcx/speed.png">平均速度
						<p>${vo.avgSpeed?string.number}公里/小时</p>
					</div>
					</#if>
				</div>
				</#if>
				
				<div id="container" style="min-width:300px;max-height:400px;"></div>
		
	</body> 

</html> 