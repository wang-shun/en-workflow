var claimCompleteButton = function (controlState) {
	this.state=controlState;
};
claimCompleteButton.prototype = {
	setDisabled:function(disabled){
		this.state.disabled=disabled;
	},
	setVisible:function(visible){
		this.state.visible=visible;
	}
}
directives.directive('claimCompleteButton', ['$http','Messenger','FormContainerFactory',function($http,Messenger,FormContainerFactory ){
	return {
		link:function(scope, element, attrs){
			var userId =  scope.subject.principal;
			scope.claimCompleteTask = function(taskId, variables) {
				if(!taskId){
					Messenger.error("任务ID为空，请设置参数$params.taskId");
					return;
				}
				if(!variables){
					Messenger.error("流程变量为空，如果流程定义有变量，请设置参数$params.variables={...}");
				}
				$http.get("ws/operateTaskTmp",{
					params:{
						taskId:taskId,
						action:'claim_complete',
						userToDelegateTo:1,
						variables:variables,
						wfOperator:{
							userId:userId,
							businessData:{}
					    }
					}
				}).success(function(data){
					if(data.result_out.result=='200'){
						Messenger.post({
					    'message': "签收且完成任务成功，下一步任务ID:"+data.result_out.nextTaskId+"！",
					    'type': 'success',
						});
					}			
				});
			}
			var form = FormContainerFactory.findForm(scope);
			if (angular.isUndefined(scope.$state[attrs.id])) {
				scope.$state[attrs.id] = {};
			}
			form[attrs.id] = new claimCompleteButton(scope.$state[attrs.id]);
		}
	};
}]);

