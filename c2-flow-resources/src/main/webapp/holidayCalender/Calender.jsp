<%@ page contentType="text/html; charset=utf-8"%>
<%
	/* String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String processDefinitionId = request
			.getParameter("processDefinitionId");
	String processInstanceId = request
			.getParameter("processInstanceId");
	String processName = processDefinitionId.substring(0,
			processDefinitionId.lastIndexOf(":"));

	String dataUrl = path
			+ "/workflow/monitor/wfmonitor?processInstanceId="
			+ processInstanceId;*/
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>
<html xmlns=http://www.w3.org/1999/xhtml>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<style>
    .main{
        width:600px;
        height:280px;
        background:#EAF8F5;
        margin-left: auto;
        margin-right: auto;
        overflow:hidden; 
        -webkit-border-radius: 10px;
        -moz-border-radius: 10px;
    }
    .title{
        text-align:center;
    }
    .date{
        float:left;
        padding-left:31px;
    }
    .date1{
        float:left;
        width:20px;
        height:20px;
        padding-top:10px;
        padding-left:30px;
        padding-right:30px;
        cursor:pointer;
    }
    .date2{
        float:left;
        width:20px;
        height:20px;
        padding-top:10px;
        padding-left:30px;
        padding-right:30px;
        background-color:rgb(236, 218, 191);
        cursor:pointer;
    }
    .content{
        margin-left:25px;
    }
</style><!-- 
<script src="../assets/jquery.min.js"></script>
<script type="text/javascript" src="../assets/js/c2-all.js"></script>
	<link rel="stylesheet" href="../assets/compatible/dup/messenger.css"/>
	<link rel="stylesheet" href="../assets/compatible/dup/messenger-theme-flat.css"/>
 -->
<script type="text/javascript" src="jquery-1.8.0.min.js"></script>
    <script>
        function getTime(year,month,day){
            y = year;
            m = month;
            d = day;
            var myDate = new Date(year,month-1,day);
            return myDate;
        }
        function days_in_month(year, month) {
            y = year;
            m = month;
            return 32 - new Date(y, m - 1, 32).getDate();
        }
        
        function setHoliday(day, month, year, t){
        	$.ajax({
            	async:false,
            	type:"POST",
            	data:JSON.stringify({"wfHoliday":{"yHoliday":year+"","mHoliday":month+"","holiday":year+"-"+month+"-"+day}}),
            	url:"<%=basePath%>ws/setHoliday",
            	dataType:"json",
            	contentType:'application/json;charset=UTF-8',   
            	success:function(data){
            		if(day.indexOf(0)=="0"){
            			day = day.substring(1,day.length);
            		}
            		if(data.result){
	            		$("#"+t).attr("class", "date2");
            		}else{
            			$("#"+t).attr("class", "date1");
            		}
            	}
            });
        }
        
        function setDefaultYearHoliday(){
        	var yHoliday = $("#yearsel option:selected").text();
        	$.ajax({
            	async:false,
            	type:"POST",
            	data:JSON.stringify({"wfHoliday":{"yHoliday":yHoliday+""}}),
            	url:"<%=basePath%>ws/getHolidayList",
            	dataType:"json",
            	contentType:'application/json;charset=UTF-8',   
            	success:function(data){
					if(data.result && data.result.length>0){
						if(confirm("已存在的当年节假日将会删除后重置为默认设置，确认进行操作？")){
							$.ajax({
		                    	async:false,
		                    	type:"POST",
		                    	data:JSON.stringify({"yHoliday":yHoliday}),
		                    	url:"<%=basePath%>ws/setDefaultYearHoliday",
		                    	dataType:"json",
		                    	contentType:'application/json;charset=UTF-8',   
		                    	success:function(data){
		        	       		    var year = $("#yearsel option:selected").text();
		        	                var month = $("#monthsel option:selected").text();
		        	                view(year,month);
		                    		alert("设置成功");
		                    	}
		                    });
						}
					}else{
						$.ajax({
	                    	async:false,
	                    	type:"POST",
	                    	data:JSON.stringify({"yHoliday":yHoliday}),
	                    	url:"<%=basePath%>ws/setDefaultYearHoliday",
	                    	dataType:"json",
	                    	contentType:'application/json;charset=UTF-8',   
	                    	success:function(data){
	        	       		    var year = $("#yearsel option:selected").text();
	        	                var month = $("#monthsel option:selected").text();
	        	                view(year,month);
	                    		alert("设置成功");
	                    	}
	                    });
					}
                	
                	
            	}
            });
        	
        }
        
        function view(year,month){
        	var monthP = month+"";
        	if(monthP.length==1){
        		monthP="0"+month;
         	}
            $.ajax({
            	async:false,
            	type:"POST",
            	data:JSON.stringify({"wfHoliday":{"yHoliday":year+"","mHoliday":monthP+""}}),
            	url:"<%=basePath%>ws/getHolidayList",
            	dataType:"json",
            	contentType:'application/json;charset=UTF-8',   
            	success:function(data){
            		 var result = data.result;
            		 var oobj = {};
            		 for(var i in result)
           			 {
            			 var holid = result[i].holiday;
            			 var day = holid.substring(holid.lastIndexOf('-')+1,holid.length);
            			 if(day.indexOf('0')==0){
            				 day = day.substring(1,day.length);
            			 }
            			 oobj[day]=result[i];
           			 }
            		 var w = getTime(year,month,1).getDay()-1;
            		 
            		//yyc fixbug begine
            		 var hh =getTime(year,month,1).getDay();
            		 if(hh==0){
            			 w = getTime(year,month,1).getDay()+6;
            		 }
            		//yyc fixbug end
            		
                     var num = days_in_month(year,month);
                     var index = 1;
                     var data = new Array();
                     for(var d = 0; d < num+w; d++){
                         if(d<w){
                             data[d] = '';
                         }else{
                             data[d] = index;
                             index++;
                         }                
                     }
                     $("#content").html('');
                     for(var k =0;k < data.length;k++){
                         if(k%7==0){
                        	 if(oobj[data[k]])
                       		 {
	                             $("#content").append("<div id='t"+k+"' class='date2'>"+data[k]+"</div><br>");
                       		 }else{
                       			 $("#content").append("<div id='t"+k+"' class='date1'>"+data[k]+"</div><br>");
                       		 }
                         }else{
                        	 if(oobj[data[k]])
                        	 {
	                             $("#content").append("<div id='t"+k+"' class='date2'>"+data[k]+"</div>");
                        	 }else{
                        		 $("#content").append("<div id='t"+k+"' class='date1'>"+data[k]+"</div>");
                        	 }
                         }
                     }
                     $("#content > div").mouseover(function(){
                         if($(this).text()!=''){
                             //$(this).css('background','red');
                         }
                     });
                     $("#content > div").mouseout(function(){
                         if($(this).text()!=''){
                             //$(this).css('background','#EAF8F5');
                         }
                     });
                     $("div[id^=t]").each(function(){
             			$(this).click(function(){
             				//alert($(this).text());
             				var holiday = $(this).text();
             				var yHoliday = $("#yearsel option:selected").text();
             				var mHoliday = $("#monthsel option:selected").text();
             				if(holiday.length==1){
             					holiday = "0"+holiday;
             				}
             				if(mHoliday.length==1){
             					mHoliday = "0"+mHoliday;
             				}
             				var t = $(this).attr("id");
             				
             				setHoliday(holiday, mHoliday, yHoliday, t);
             			});
             		});
            	}
            });
           
        }

        $(document).ready(function (){
            for(var t = 1970; t < 2999; t++){
                $("#yearsel").append("<option value ='"+t+"'>"+t+"</option>");
            }
            for(var y = 1; y < 13;y++){
                $("#monthsel").append("<option value ='"+y+"'>"+y+"</option>");
            }
            var year = new Date().getFullYear();
            var month = new Date().getMonth()+1;
            var day = new Date().getDate();
            var w = getTime(year,month,1).getDay()-1;
            
            //yyc fixbug begine
            var hh =getTime(year,month,1).getDay();
	   		 if(hh==0){
	   			 w = getTime(year,month,1).getDay()+6;
	   		 }
	   	    //yyc fixbug end
            var num = day + w -1;
            $("#yearsel").change(function(){
                year = $("#yearsel option:selected").text();
                month = $("#monthsel option:selected").text();
                view(year,month);
            });
            $("#monthsel").change(function(){
                year = $("#yearsel option:selected").text();
                month = $("#monthsel option:selected").text();
                view(year,month);
            });
            var oDate = ['星期一','星期二','星期三','星期四','星期五','星期六','星期日',];
            for(var i = 0;i < 7;i++){
                $("#title").append("<div class='date'><b>"+oDate[i]+"</b></div>");
            }
            $("#yearsel option[value='"+year+"']").attr("selected", true);
            $("#monthsel option[value='"+month+"']").attr("selected", true);
            view(year,month);
            //标记当前日期
           // $("#t"+num).css('background','yellow');
    	});
       // });
    </script>
</head>
    <body>
        <div id="main" class="main">
        <div style="padding-left:19px;height:35px">
        <select id="yearsel">
            </select>年
            <select id="monthsel">
            </select>月
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="button" value="保存当年默认设置（双休日休假）" onclick="setDefaultYearHoliday();"></input>
        </div>
                        <!-- <br><br> -->
           
            <div id="title" class="title">
            </div>
            <div id="content" class="content">
            </div>
        </div>
    </body>
</html>