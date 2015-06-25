var startButton = function(controlState) {
	this.state = controlState;
};
startButton.prototype = {
	setDisabled : function(disabled) {
		this.state.disabled = disabled;
	},
	setVisible : function(visible) {
		this.state.visible = visible;
	}
};
directives
		.directive(
				'startButton',
				['$http','Messenger',
						'FormContainerFactory',
						function($http,Messenger,FormContainerFactory) {
							return {
								scope : true,
								link : function(scope, element, attrs) {
									var userId =  scope.subject.principal;
									scope.startWorkflow = function(
											processDefinitionId, businessKey,
											moduleId, variables) {
										if(!processDefinitionId){
											Messenger.error("流程定义ID为空，请设置参数$params.processDefinitionId");
											return;
										}
										if(!businessKey){
											Messenger.error("业务ID为空，请设置参数$params.businessKey");
											return;
										}
										if(!moduleId){
											Messenger.error("事项ID为空，请设置参数$params.moduleId");
											return;
										}
										if(!variables){
											Messenger.error("流程变量为空，如果流程定义有变量，请设置参数$params.variables={...}");
										}
										//alert("processDefinitionId=" + processDefinitionId);
										//alert("businessKey=" + businessKey);
										//alert("moduleId=" + moduleId);
										//alert("variables=" + variables);
										$http.get("ws/startWorkFlow",{
											params : {
												bussinessId : businessKey,
												processDefinitionId : processDefinitionId,
												variables : variables,
												bussinessId : businessKey,
												wfOperator : {
													userId : userId,
													businessData : {
														moduleId : moduleId,
														businessKey : businessKey
													}
												}
											}
										}).success(function(data){
											if(data.result_out.result=='200'){
												Messenger.post({
												    'message': "开启成功，产生流程实例ID:"+data.result_out.processInstanceId+"！",
												    'type': 'success',
												});
											}
										});
									}
									var form = FormContainerFactory
											.findForm(scope);
									if (angular
											.isUndefined(scope.$state[attrs.id])) {
										scope.$state[attrs.id] = {};
									}
									form[attrs.id] = new startButton(
											scope.$state[attrs.id]);
								}
							};
						} ]);
