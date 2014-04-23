<!DOCTYPE html> 

<html> 

  <head> 

	<title>行车报告</title> 
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="pragma" content="no-cache" />
	
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
		<script src="/static/highcharts/highcharts.js"></script>
	<style>
	  img{
		max-width:30px;
		width:expression(document.body.clientWidth > 30?"30px":"auto" );
		border:0
	}
	._p{margin-left:25px;}
	div.pos_abs{
	    width: auto;
	    position: fixed;
	    top: 0px;
	    left: 0px;
	    right: 0px;
		background:#EFEFEF;
    z-index: 99997;
   
	}
	</style>
	<script type="text/javascript">
	
	
		var cosPoints_week1= [];
		var cosPoints_week2 = [];
		var ticks_week= [];
		var ticks_week2=[];
		<#if volist??>
			<#list volist as list>
				cosPoints_week1.push(${list.totalMileage});//里程
				cosPoints_week2.push(${list.totalOil});//油耗
				ticks_week.push('${list.fromDay?string('yyyy-MM-dd')}');
			</#list>
		</#if>
		for(i=0;i<ticks_week.length;i++){
			 ticks_week2[i]=ticks_week[i].split("-")[1]+"-"+ticks_week[i].split("-")[2];
		}	
		
		var cosPoints_month1= [];
		var cosPoints_month2 = [];
		var ticks_month1= [];
		var ticks_month2=[];  
		<#if list_month??>
			<#list list_month as list>
				cosPoints_month1.push(${list.totalMileage});//里程
				cosPoints_month2.push(${list.totalOil});//油耗
				ticks_month1.push('${list.fromDay?string('yyyy-MM-dd')}');
			</#list>
		</#if>
		for(i=0;i<ticks_month1.length;i++){
			if(i!=0&&i!=ticks_month1.length-1){
				ticks_month2[i]="";
			}else {
			 ticks_month2[i]=ticks_month1[i];
			 }
		}
		Highcharts.setOptions({ 
    		colors: ['#E27A15', '#1196E3'] 
		}); 
	$(function () {
		$("#report_month").hide();
	    $('#container_week').highcharts({
	        chart: {
	            type: 'line'
	        },
	        title: {
	            text: ''
	        },
	        
	        xAxis: {
	            categories: ticks_week2
	        },
	        yAxis: {
	            title: {
	                text: ''
	            }
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
	            data: cosPoints_week1
	        }, {
	            name: '油耗',
	            data: cosPoints_week2
	        }]
	    	});
	    	
	    	 $('#container_month').highcharts({
	        chart: {
	            type: 'line'
	        },
	        title: {
	            text: ''
	        },
	        
	        xAxis: {
	            categories: ticks_month2
	        },
	        yAxis: {
	            title: {
	                text: ''
	            }
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
	            data: cosPoints_month1
	        }, {
	            name: '油耗',
	            data: cosPoints_month2
	        }]
	    	});
		});				
		
		function  changeReport(e){
			if(e==1){
				$("#report_week").show();
				
				$("#report_month").hide();
				$("#hr1").attr("color","#FF6600");
				$("#hr3").attr("color","#FF6600");
				$("#hr2").attr("color","#DDDDDD");
				$("#hr4").attr("color","#DDDDDD");
			}else{
				$("#report_week").hide();
				
				$("#report_month").show();
				$("#hr2").attr("color","#FF6600");
				$("#hr4").attr("color","#FF6600");
				$("#hr3").attr("color","#DDDDDD");
				$("#hr1").attr("color","#DDDDDD");
			}
		}
		</script>


</head> 

	<body> 
			
			<div id="report_week" >
				<div class="pos_abs">
					
					<table style="text-align:center;margin:auto;border-spacing:20px;">
						<th>
							<td>
								<a onclick="changeReport(1)"><b>周行车报告</b></a>
								<hr id="hr1" color="#FF6600">
							</td>
							<td>
								<a onclick="changeReport(2)"><b>月行车报告</b></a>
								<hr id="hr2" >
							</td>
						<th>
					</table>
				</div>
				<div style="margin-top:80px;">
				<#if vo??>
					<#if driveTime_week??>
					<div style="width:33.3%;float:left">
						<img src ="/static/wcx/time.png">行驶时长
						<p class="_p"> ${driveTime_week}</P>
					</div>
					</#if>
					<#if vo.totalMileage??>
					<div style="width:33.3%;float:left">
						<img src ="/static/wcx/mile.png">上周里程
						<p class="_p">${vo.totalMileage?string.number}公里</p>
					</div>
					</#if>
					<#if vo.oilMoney??>
					<div style="width:33.3%;float:left">
						<img src ="/static/wcx/oilfei.png">上周油费
						<p class="_p">${vo.oilMoney?string.number}元</p>
					</div>
					</#if>
					<#if vo.avgOil??>
					<div style="float:left;margin-right:10px">
						<img src ="/static/wcx/oil.png">上周油耗
						<p class="_p">${vo.avgOil?string.number}升/百公里</p>
					</div>
					</#if>
					<#if vo.avgSpeed??>
					<div  >
						<img src ="/static/wcx/speed.png">平均速度
						<p style="margin-left:50px">${vo.avgSpeed?string.number}公里/小时</p>
					</div>
					</#if>
				
				</#if>
				<div style="text-align:center">
				<div id="container_week" style="width:400px;height:400px;"></div>
				</div>
			</div>
			</div>
				<div id="report_month">
					<div class="pos_abs">
					<table style="text-align:center;margin:auto;border-spacing:20px;">
							<th>
								<td>
									<a onclick="changeReport(1)"><b>周行车报告</b></a>
									<hr id="hr3" color="#FF6600">
								</td>
								<td>
									<a onclick="changeReport(2)"><b>月行车报告</b></a>
									<hr id="hr4" >
								</td>
							<th>
						</table>
					</div>
					
					<div style="margin-top:80px">
					<#if vo_month??>
					
				
					<#if driveTime_month??>
					<div style="width:33.3%;float:left">
						<img src ="/static/wcx/time.png">行驶时长
						<p class="_p"> ${driveTime_month}</P>
					</div>
					</#if>
					<#if vo_month.totalMileage??>
					<div style="width:33.3%;float:left">
						<img src ="/static/wcx/mile.png">上月里程
						<p class="_p">${vo_month.totalMileage?string.number}公里</p>
					</div>
					</#if>
					<#if vo_month.oilMoney??>
					<div style="width:33.3%;float:left">
						<img src ="/static/wcx/oilfei.png">上月油费
						<p class="_p">${vo_month.oilMoney?string.number}元</p>
					</div>
					</#if>
					<#if vo_month.avgOil??>
					<div style="float:left;margin-right:10px">
						<img src ="/static/wcx/oil.png">上月油耗
						<p class="_p">${vo_month.avgOil?string.number}升/百公里</p>
					</div>
					</#if>
					<#if vo_month.avgSpeed??>
					<div >
						<img src ="/static/wcx/speed.png">平均速度
						<p style="margin-left:30px">${vo_month.avgSpeed?string.number}公里/小时</p>
					</div>
					</#if>
				
				</#if>
				</div>
				<div>
				<div id="container_month" style="width:1000px;height:400px;"></div>
				</div>
				</div>
		
	</body> 

</html> 