var claimButton = function (controlState) {
	this.state=controlState;
};
claimButton.prototype = {
	setDisabled:function(disabled){
		this.state.disabled=disabled;
	},
	setVisible:function(visible){
		this.state.visible=visible;
	}
}

directives.directive('claimButton',['$http','Messenger','FormContainerFactory',
						function($http,Messenger,FormContainerFactory) {
							return {
								scope : true,
								link : function(scope, element, attrs) {
									var userId =  scope.subject.principal;
									scope.claimTask = function(taskId) {
										if(!taskId){
											Messenger.error("任务ID为空，请设置参数$params.taskId");
											return;
										}
										$http.get("ws/operateTaskTmp",
												{
														params:
														{
															taskId:taskId,
															action:'claim',
															userToDelegateTo:1,
															variables:{},
															wfOperator:{
																userId:userId,
																businessData:{}
														    }
														}
												}
												).success(function(data){
														if(data.result_out.result=='200'){
															Messenger.post({
															    'message': "签收成功！",
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
									form[attrs.id] = new claimButton(
											scope.$state[attrs.id]);
								}
							};
						} ]);
