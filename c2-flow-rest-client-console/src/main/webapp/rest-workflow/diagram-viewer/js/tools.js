/**
 * 工具类,单例模式
 * 
 * @author li.zhu@chinacreator.com
 */
var Tools = function(){
	
	return {
		// 检查是否为数字
		isDigital : function(x) {
			return !!(x + '').match(/^[0-9]+(.[0-9]{1,2})?$/);
		},
			
		// javascript获取上下文
		contextPath : function() {
			var location = document.location.toString();
			var contextPathTemp = "";
			if (location.indexOf("://") != -1) {
				contextPathTemp += location.substring(0, location.indexOf("//") + 2);
				location = location.substring(location.indexOf("//") + 2,
						location.length);
			}
			var index = location.indexOf("/");
			contextPathTemp += location.substring(0, index + 1);
			location = location.substring(index + 1);
			index = location.indexOf("/");
			contextPathTemp += location.substring(0, index + 1);
	
			return contextPathTemp;
		},
		
		// 替换非法字符
		replaceHtmlTag : function(str) {
			var tempStr = str;
			tempStr = tempStr.replace(/[<>\\'()&*\\/=]/g, '').replace(/or/g, '').replace(/select/g, '').replace(/delete/g, '').replace(/from/g, '').replace(/update/g, '');
			tempStr = tempStr.replace(/OR/g, '').replace(/SELECT/g, '').replace(/DELETE/g, '').replace(/FROM/g, '').replace(/UPDATE/g, '');
			return tempStr;
		},
		
		/**
		 * 给目标添加快捷键,依赖于Jquery实现
		 * 
		 * @param id 界面元素ID
		 * @param keyName 键盘值
		 */
		addAccessKey : function(id, keyName) {
			$('#'+id).attr("accesskey", keyName);
		},
		
		/**
		 * 字母和数字组合验证
		 * 
		 * @param str 字符串
		 */
		isLetterDigit: function (str){
			var rep = /^[A-Za-z0-9]*([A-Za-z]+\d+)|(\d+[A-Za-z]+)[A-Za-z0-9]*$/;
			if (!rep.exec(str)) return false;
			return true;
		},
		
		/**
		 * 
		 * 判断是否为undefined，或者null，或者空字符串，或者字符串'non'
		 */
		isNull: function(value){
			var b = false;
			// alert(typeof(value));
			// if (value != null && value != ''){//按这样写切换页面会出错 tgy 2013.07.16
			if (typeof(value)== 'object'){// tgy 2013.07.16
				if (value!=null)
					b=false;
				else
					b=true;
			}else if (value == undefined){
				b = true ;
			} else if (value=='NaN' || value=='none' || $.trim(value) == ''){
				b = true ;
			}
			// alert(b);
			return b;
		},
		
		/**
		 * 验证是否是身份证，简单判断
		 */
		isIdCard : function(value){
			var reg = /^(\d{6})(18|19|20)?(\d{2})([01]\d)([0123]\d)(\d{3})(\d|X)?$/;
			return reg.test(value);
		},
		
		/**
		 * 获取字符串的字节长度
		 */
		getStrByteLength : function(str){
			var w = 0; 
			for (var i=0; i<str.length; i++) { 
				var c = str.charCodeAt(i); 
				// 单字节加1
				if ((c >= 0x0001 && c <= 0x007e) || (0xff60<=c && c<=0xff9f)) { 
					w++; 
				} 
				else { 
					w+=2; 
				} 
			} 
			return w;
		},
		
		/**
		 * 检测字符串不能超过多少个字节。汉字为双字节。
		 * 
		 * @param str 源字符串
		 * @param maxlength 允许的最大数
		 */
		isOutOfMaxLength: function(str, maxlength){ 
			var w = Tools.getStrByteLength(str);
			
			if (w > maxlength) { 
				return false; 
			} 
			return true; 
		},
		
		/**
		 * 验证移动手机号码
		 * 
		 * @param mobile 手机号码
		 */
		validateMobile: function(mobile){
			var reg = /^((13[4-9])|(15[7-9])|(15[0-2])|(18[7-8]))[0-9]{8}$/;	
			if(!reg.test(mobile)){
				$.messager.defaults={ok:"确定",cancel:"取消"};
				$.messager.alert('',"手机号码不符合移动手机号码格式要求，请重新输入！",'error');
				return false;
			}else{
			    return true;
			}
		},
		
		/**
		 * 字符串比较,用于格式相同的日期比较
		 * 
		 * @param datestr1 日期字符串1
		 * @param datestr2 日期字符串2
		 * @return 如果datestr1 > datestr2,则返回true,否则返回false
		 */
		timesStringCompare: function (datestr1,datestr2){
			if (datestr1==null||datestr1==""||datestr2==null||datestr2==""){
				return false;
			}
			if (datestr1>datestr2){
				return true;
			}
		},
		
		/**
		 * 获取指定格式的日期字符串
		 * 
		 * @param date 日期（Date）
		 * @param str 格式
		 */
		dateFormat : function (date, str){
			str=str.replace(/yyyy/g,date.getFullYear());
			str=str.replace(/yy/g,date.getFullYear().toString().slice(2));
			var m = date.getMonth()+1;
			if(m < 10){
				m = '0'+m;
			}
			str = str.replace(/mm/g,m);
			var d = date.getDate();
			if(d < 10){
				d = '0'+d;
			}
			str = str.replace(/dd/g,d);
			str = str.replace(/wk/g,date.getDay());
			var h = date.getHours();
			if(h < 10){
				h = '0'+h;
			}
			str = str.replace(/hh/g,h);
			var mi = date.getMinutes();
			if(mi < 10){
			  mi = '0'+mi;
			}
			str = str.replace(/mi/g,mi);
			var sec = date.getSeconds();
			if(sec < 10){
				sec = '0'+sec;
			}
			str = str.replace(/ss/g,sec);
			str = str.replace(/ms/g,date.getMilliseconds());
			return str;
		},
		
		/**
		 * 判断是否为日期时间字符串，支持的格式：如2011-9-8、2011-9-8 12、2011-9-8 12:33、2011-9-8
		 * 12:33:45等
		 * 
		 * @param str
		 */
		isDateStr : function(str){
			var reg = /^((((1[8-9]\d{2})|([2-9]\d{3}))([-\/\._])(10|12|0?[13578])([-\/\._])(3[01]|[12][0-9]|0?[1-9]))|(((1[8-9]\d{2})|([2-9]\d{3}))([-\/\._])(11|0?[469])([-\/\._])(30|[12][0-9]|0?[1-9]))|(((1[8-9]\d{2})|([2-9]\d{3}))([-\/\._])(0?2)([-\/\._])(2[0-8]|1[0-9]|0?[1-9]))|(([2468][048]00)([-\/\._])(0?2)([-\/\._])(29))|(([3579][26]00)([-\/\._])(0?2)([-\/\._])(29))|(([1][89][0][48])([-\/\._])(0?2)([-\/\._])(29))|(([2-9][0-9][0][48])([-\/\._])(0?2)([-\/\._])(29))|(([1][89][2468][048])([-\/\._])(0?2)([-\/\._])(29))|(([2-9][0-9][2468][048])([-\/\._])(0?2)([-\/\._])(29))|(([1][89][13579][26])([-\/\._])(0?2)([-\/\._])(29))|(([2-9][0-9][13579][26])([-\/\._])(0?2)([-\/\._])(29)))( ((0?[0-9])|(1[0-9])|(2[0-3]))(:((0?[0-9])|([1-5][0-9]))(:((0?[0-9])|([1-5][0-9])))?)?)?$/;
			return reg.test(str);
		},
		
		uniencode : function(text) {    
		    text = escape(text.toString()).replace(/\+/g, "%2B");
		    var matches = text.match(/(%([0-9A-F]{2}))/gi); 
		    if (matches){ 
		        for (var matchid = 0; matchid < matches.length; matchid++) { 
		            var code = matches[matchid].substring(1,3); 
		            text = text.replace(matches[matchid], '%u00' + code);             
		        }    
		    }    
		    text = text.replace(/%u/g,"\\u");     
		    return text;
		},
	
		/**
		 * 表单属性验证，注意当未找到指定属性时会返回true
		 * 
		 * @param attrId 属性ID
		 * @param cnName 属性中文名
		 * @param minLength 最小长度，0表示可为空
		 * @param maxLength 最大长度，0表示不验证最大长度
		 * @param reg 格式正则表达式，false表示不使用正则表达式验证
		 * @param regDisc 正则表达式的说明
		 * @param maxFalseInfo 超过最大长度时的补充提示信息
		 * 
		 * 示例： checkField('name','姓名',1,32) 姓名必填且不超过32个字符
		 * checkField('postCode','邮编',1,0,/^\d{6}$/,'6位数字') 邮编必填且为6位数字
		 */
		checkField : function(attrId,cnname,minLength,maxLength,reg,regDisc,maxFalseInfo){
			if($('#'+attrId).attr('id')){
				var value = $('#'+attrId).val();
				if(minLength && value==''){
					alert('请输入'+cnname);
					return false;
				}
				if(minLength && value!='' && Tools.getStrByteLength(value)<minLength){
					alert(cnname+'不能少于'+minLength+'个字符（1个汉字为2个字符）');
					return false;
				}
				if(value!='' && maxLength && !Tools.strLengthCheck(value,maxLength)){
					alert(cnname+'不能超过'+maxLength+'个字符（1个汉字为2个字符）'+ (maxFalseInfo? ('，'+maxFalseInfo) : ''));
					return false;
				}
				if(value!='' && reg){
					if(!reg.test(value)){
						alert(cnname+ (regDisc? ('格式为'+regDisc) : '格式不正确')+'，请重新输入');
						return false;
					}
				}
			}
			return true;
		},
		
		/**
		 * 表单复选框属性验证，注意当未找到指定属性时会返回true
		 * 
		 * @param attrName 属性name
		 * @param cnName 属性中文名
		 * @param notNull 是否可空，true表示不可空
		 */
		checkCheckboxField : function(attrName,cnName,notNull){
			if(!$(':checkbox[name="'+attrName+'"]').length){
				return true;
			}
			var infoType = $(':checkbox[name="'+attrName+'"][checked]');
			if(!infoType.length && notNull){
				alert('请选择'+cnName);
				return false;
			}
			return true;
		},
		
		/**
		 * 表单单选属性验证，注意当未找到指定属性时会返回true
		 * 
		 * @param attrName 属性name
		 * @param cnName 属性中文名
		 * @param notNull 是否可空，true表示不可空
		 */
		checkRadioField : function(attrName,cnName,notNull){
			if(!$(':radio[name="'+attrName+'"]').length){
				return true;
			}
			var infoType = $(':radio[name="'+attrName+'"][checked]');
			if(!infoType.length && notNull){
				alert('请选择'+cnName);
				return false;
			}
			return true;
		},
		
		/**
		 * 获取窗口高宽尺寸
		 */
		findDimensions : function() { // 获取窗宽度
			var winWidth = 0;
			var winHeight = 0;
			if (window.innerWidth) {
				winWidth = window.innerWidth;
			} else if ((document.body) && (document.body.clientWidth)) {
				winWidth = document.body.clientWidth;// 获取窗口高度
			}
			if (window.innerHeight) {
				winHeight = window.innerHeight;
			} else if ((document.body) && (document.body.clientHeight)) {
				winHeight = document.body.clientHeight;// 通过深入Document内部对body进行检测，获取窗口大小
			}
			if (document.documentElement && document.documentElement.clientHeight && document.documentElement.clientWidth) {
				winHeight = document.documentElement.clientHeight;
				winWidth = document.documentElement.clientWidth;
			}
			return {"winHeight" : winHeight, "winWidth" : winWidth};
		},
		
		setElementHeight : function(elementeId,height1,height2){
			var winSize = Tools.findDimensions();// alert(winSize.winHeight);
			$('#'+elementeId).height( winSize.winHeight - height1);// alert($('#'+elementeId).height());
			
			$(window).resize(function() {
				var winHeight = Tools.findDimensions().winHeight;
				$('#'+elementeId).height(winHeight - height1);alert($('#'+elementeId).height());
			});
			
		},
		
		/**
		 * 表单栏位验证
		 */
		gotoPage : function(divId){
			// alert(divIdName);
			if($("#tabNav li.currentTab").attr("name") != (divId)){
				$("#tabNav li.currentTab").removeClass("currentTab"); 
				$("li[name='" + divId + "']").addClass("currentTab"); 
				$("#tabContent div").hide();
				$(divId).show();
			}
		},
		
		/**
		 * 使用jquery的ajax方法调用后台方法, 如果session失效会跳转到relogin.jsp页面进行重新登录系统
		 * url 请求的url
		 * paramObj 请求的参数对象
		 * backFun 调用成功后所调用的回调方法
		 * vdataType 返回数据类型text/json 默认为json
		 * vcontentType 发送信息至服务器时内容编码类型, 默认为application/x-www-form-urlencoded, 还可以设置成application/json;charset=UTF-8
		 */
		jqAjax : function(url, paramObj, backFun, vdataType, vcontentType) {
			if(!vdataType) {
				vdataType = 'json';
			}
			if(!vcontentType) {
				vcontentType = "application/x-www-form-urlencoded"
			}
			$.ajax({
				url : gPath + url,
				type : "post",
				contentType : vcontentType,  
				async : false,
				dataType : vdataType,
				data : paramObj,
				error : function () {
					window.top.$.messager.alert('系统提示', '操作失败，请稍后再试...', 'error', function() {
						//window.top.location = gPath + "/relogin.jsp";
					});
				}, 
				success : function(data) {
					if(data) {
						var html = $.trim(data);
						if(html.substring(0, 6) == "<html>") {// session失效控制
							window.top.location = gPath + "/relogin.jsp";
						} else if(backFun) {
							backFun(data);
						}
					}
				}
			});
		},
		 
		/*
		 * 判断指定控件是否可以编辑？
		 * param: ctrl 目标控件
		 * return: 布尔值  控件是否可以编辑
		 */
		canEdit : function(ctrl) {
			if(ctrl != null && ctrl.readOnly === false) { // 如果控件非只读
				if(ctrl.nodeName === "TEXTAREA") return true; // 文本域可编辑
				if(ctrl.nodeName === "INPUT" && ctrl.type === "text") return true; // 文本框可编辑
			}
			return false; // 其它都不可编辑
		},

		/*
		 * 屏蔽“退格”键的“页面回退”功能
		 */
		shieldBackSpace : function() {
			document.body.onkeydown = function() {
				if(event.keyCode === 8 && !Tools.canEdit(event.srcElement)) return false;
			};
		},
		
		/**
		 * 在系统顶层打开一个弹出窗口
		 * vtitle 窗口标题
		 * action url
		 * closeFun 关闭窗口时调用的方法
		 * vwidth 宽度
		 * vheight 高度
		 */
		openTopWindow : function(vtitle, action, closeFun, vwidth, vheight) {
			var fvwidth = (vwidth ? vwidth : 600);
			var fvheight = (vheight ? vheight : 300);
			var clientW = window.top.document.body.clientWidth;
			var clientH = window.top.document.body.clientHeight;
			window.top.$("#divSysLevel").window({
				title : vtitle,
				width : fvwidth,
				height : fvheight,
				top : (clientH - fvheight) * 0.5 < 0 ? 0 : (clientH - fvheight) * 0.5,
				left :(clientW - fvwidth) * 0.5 < 0 ? 0 :(clientW - fvwidth) * 0.5,
				modal : true,
				shadow : false,
				minimizable : false,
				onClose : function() {
					if(closeFun) {closeFun();}
					window.top.$("#frameSysLevel").attr('src', gPath + '/resource/jsp/null.html');
				}
			});
			window.top.$("#divSysLevel").window('open');
			window.top.$("#frameSysLevel").attr('src', action);
		}
	}
}()
