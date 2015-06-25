var stopButton = function (controlState) {
	this.state=controlState;
};
stopButton.prototype = {
	setDisabled:function(disabled){
		this.state.disabled=disabled;
	},
	setVisible:function(visible){
		this.state.visible=visible;
	}
}
directives.directive('stopButton', ['$http','Messenger','FormContainerFactory',function($http,Messenger,FormContainerFactory ){
	return {
		link:function(scope, element, attrs){
			var userId =  scope.subject.principal;
			scope.goAnyWhere = function(processInstanceId, deleteReason) {
				if(!processInstanceId){
					Messenger.error("流程实例ID为空，请设置参数$params.processInstanceId");
					return;
				}
				if(!deleteReason){
					Messenger.success("终止原因为空，请设置参数$params.deleteReason");
				}
				$http.get("ws/stopProcessInstance",{
					params:
					{
						processDefinitionId:processDefinitionId,
						deleteReason:deleteReason,
						wfOperator:{
							userId:userId,
							businessData:{}
					    }
					}			
				}).success(function(data){
					if(data.result_out.result=='200'){
						Messenger.post({
						    'message': "终止流操作成功！",
						    'type': 'success',
						});
					}			
				});
			}
			var form = FormContainerFactory.findForm(scope);
			if (angular.isUndefined(scope.$state[attrs.id])) {
				scope.$state[attrs.id] = {};
			}
			form[attrs.id] = new stopButton(scope.$state[attrs.id]);
		}
	};
}]);