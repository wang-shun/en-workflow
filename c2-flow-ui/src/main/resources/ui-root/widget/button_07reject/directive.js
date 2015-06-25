var rejectButton = function (controlState) {
	this.state=controlState;
};
rejectButton.prototype = {
	setDisabled:function(disabled){
		this.state.disabled=disabled;
	},
	setVisible:function(visible){
		this.state.visible=visible;
	}
}
directives.directive('rejectButton', ['$http','Messenger','FormContainerFactory',function($http,Messenger,FormContainerFactory ){
	return {
		link:function(scope, element, attrs){
			var userId =  scope.subject.principal;
			scope.rejectTask = function(taskId, rejectMessage) {
				alert("taskId=" + taskId);
				alert("rejectMessage=" + rejectMessage);
				if(!taskId){
					Messenger.error("任务ID为空，请设置参数$params.taskId");
					return;
				}
				if(!rejectMessage){
					Messenger.success("驳回理由为空，请设置参数$params.rejectMessage");
				}
				$http.get("ws/reject",{
					 params:{
							currenTaskId:taskId,
							rejectMessage:rejectMessage,
							variables:{},
							wfOperator:{
								userId:userId,
								businessData:{}
						    }
						}				
					}).success(function(data){
						if(data.result_out.result=='200'){
							Messenger.post({
							    'message': "回退任务成功,产生任务ID"+data.result_out.nextTaskId+"！",
							    'type': 'success',
								});
						}		
					});
			}
			var form = FormContainerFactory.findForm(scope);
			if (angular.isUndefined(scope.$state[attrs.id])) {
				scope.$state[attrs.id] = {};
			}
			form[attrs.id] = new rejectButton(scope.$state[attrs.id]);
		}
	};
}]);
