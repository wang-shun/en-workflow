var goanywhereButton = function (controlState) {
	this.state=controlState;
};
goanywhereButton.prototype = {
	setDisabled:function(disabled){
		this.state.disabled=disabled;
	},
	setVisible:function(visible){
		this.state.visible=visible;
	}
}
directives.directive('goanywhereButton', ['$http','Messenger','FormContainerFactory',function($http,Messenger,FormContainerFactory ){
	return {
		link:function(scope, element, attrs){
			var userId =  scope.subject.principal;
			scope.goAnyWhere = function(isStart, moduleId, bussinessKey, 
					processDefinitionId, currenTaskId, destTaskDefinitionKey,useHisAssignee, variables) {
				if(!isStart){
					Messenger.error("是否启动为空，请设置参数$params.isStart=[true|false]");
					return;
				}
				if(isStart == 'true'){
					if(!bussinessKey){
						Messenger.error("业务标识为空，请设置参数$params.bussinessKey");
						return;
					}
					if(!processDefinitionId){
						Messenger.error("流程定义ID为空，请设置参数$params.processDefinitionId");
						return;
					}
					if(!moduleId){
						Messenger.error("事项ID为空，请设置参数$params.moduleId");
						return;
					}
				}else{
					if(!currenTaskId){
						Messenger.error("当前任务ID为空，请设置参数$params.currenTaskId");
						return;
					}
					if(!useHisAssignee){
						Messenger.success("是否使用历史执行人为空，请设置参数$params.useHisAssignee=[true|false]");
					}
				}
				if(!destTaskDefinitionKey){
					Messenger.error("自由流目标活动定义ID为空，请设置参数$params.destTaskDefinitionKey");
					return;
				}
				$http.get("ws/goAnyWhere",{
					params:
					{
						isStart:isStart,
						bussinessKey:bussinessKey,
						processDefinitionId:processDefinitionId,
						currenTaskId:currenTaskId,
						destTaskDefinitionKey:destTaskDefinitionKey,
						useHisAssignee:useHisAssignee,
						wfOperator:{
							userId:userId,
							businessData:{
								moduleId:moduleId,
								businessKey:bussinessKey
							}
					    },
					    variables:variables
					}			
				}).success(function(data){
					if(data.result_out.result=='200'){
						Messenger.post({
						    'message': "自由流操作成功，产生任务ID:"+data.result_out.nextTaskId+"！",
						    'type': 'success',
						});
					}			
				});
			}
			var form = FormContainerFactory.findForm(scope);
			if (angular.isUndefined(scope.$state[attrs.id])) {
				scope.$state[attrs.id] = {};
			}
			form[attrs.id] = new goanywhereButton(scope.$state[attrs.id]);
		}
	};
}]);