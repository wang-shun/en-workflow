var DiagramGenerator = {};
var pb1;
$(document)
		.ready(
				function() {
					var query_string = {};
					var query = window.location.search.substring(1);
					var vars = query.split("&");
					for ( var i = 0; i < vars.length; i++) {
						var pair = vars[i].split("=");
						query_string[pair[0]] = pair[1];
					}

					var processDefinitionId = query_string["processDefinitionId"];
					var processInstanceId = query_string["processInstanceId"];

					console.log("Initialize progress bar");

					pb1 = new $.ProgressBar({
						boundingBox : '#pb1',
						label : 'Progressbar!',
						on : {
							complete : function() {
								console.log("Progress Bar COMPLETE");
								this.set('label', 'complete!');
								if (processInstanceId) {
									ProcessDiagramGenerator
											.drawHighLights(processInstanceId);
								}
							},
							valueChange : function(e) {
								this.set('label', e.newVal + '%');
							}
						},
						value : 0
					});
					console.log("Progress bar inited");

					ProcessDiagramGenerator.options = {
						diagramBreadCrumbsId : "diagramBreadCrumbs",
						diagramHolderId : "diagramHolder",
						// diagramInfoId : "diagramInfo",
						on : {
							click : function(canvas, element, contextObject) {
								var mouseEvent = this;
								console
										.log(
												"[CLICK] mouseEvent: %o, canvas: %o, clicked element: %o, contextObject: %o",
												mouseEvent, canvas, element,
												contextObject);

								if (contextObject.getProperty("type") == "callActivity") {
									var processDefinitonKey = contextObject
											.getProperty("processDefinitonKey");
									var processDefinitons = contextObject
											.getProperty("processDefinitons");
									var processDefiniton = processDefinitons[0];
									console
											.log(
													"Load callActivity '"
															+ processDefiniton.processDefinitionKey
															+ "', contextObject: ",
													contextObject);

									// Load processDefinition
									ProcessDiagramGenerator
											.drawDiagram(processDefiniton.processDefinitionId);
								}
								// alert(contextObject.getProperty("type"));
								if (contextObject.getProperty("type") == "userTask") {
									 var actDefId = contextObject.getId();
									 $('#actInfo').dialog({
										title : '任务信息',
										width : 300,
										height : 100,
										closed : false,
										cache : false,
										href : taskInfoUrl + actDefId,
										modal : true
									 }); 

									 
									/* $.ajax({
										  async : false,
										  cache : false,      type: 'POST',      dataType : "html",
										  url: taskInfoUrl + actDefId,//请求的action路径
										  error: function () {//请求失败处理函数
										    	alert('请求失败');
										  },
										  success:function(data){ //请求成功后处理函数。
											  $.messager.show({
													title:'任务信息',
													msg:data,
													timeout:5000,
													showType:'slide'
											  });
										  }
									});*/
								}
							},
							rightClick : function(canvas, element,
									contextObject) {
								var mouseEvent = this;
								console
										.log(
												"[RIGHTCLICK] mouseEvent: %o, canvas: %o, clicked element: %o, contextObject: %o",
												mouseEvent, canvas, element,
												contextObject);
							},
							over : function(canvas, element, contextObject) {
								var mouseEvent = this;
								// console.log("[OVER] mouseEvent: %o, canvas:
								// %o, clicked element: %o, contextObject: %o",
								// mouseEvent, canvas, element, contextObject);

								// TODO: show tooltip-window with contextObject
								// info
								ProcessDiagramGenerator
										.showActivityInfo(contextObject);
							},
							out : function(canvas, element, contextObject) {
								var mouseEvent = this;
								// console.log("[OUT] mouseEvent: %o, canvas:
								// %o, clicked element: %o, contextObject: %o",
								// mouseEvent, canvas, element, contextObject);

								ProcessDiagramGenerator.hideInfo();
							}
						}
					};

					var baseUrl = window.document.location.protocol + "//"
							+ window.document.location.host + "/";
					var shortenedUrl = window.document.location.href.replace(
							baseUrl, "");
					baseUrl = baseUrl
							+ shortenedUrl.substring(0, shortenedUrl
									.indexOf("/"));

					ActivitiRest.options = {
						processInstanceHighLightsUrl : baseUrl
								+ "/service/process-instance/{processInstanceId}/highlights?callback=?",
						processDefinitionUrl : baseUrl
								+ "/service/process-definition/{processDefinitionId}/diagram-layout?callback=?",
						processDefinitionByKeyUrl : baseUrl
								+ "/service/process-definition/{processDefinitionKey}/diagram-layout?callback=?"
					};

					if (processDefinitionId) {
						ProcessDiagramGenerator
								.drawDiagram(processDefinitionId);

					} else {
						alert("processDefinitionId parameter is required");
					}
				});