var suspendButton = function (controlState) {
	this.state=controlState;
};
suspendButton.prototype = {
	setDisabled:function(disabled){
		this.state.disabled=disabled;
	},
	setVisible:function(visible){
		this.state.visible=visible;
	}
}
directives.directive('suspendButton', ['$http','Messenger','FormContainerFactory',function($http,Messenger,FormContainerFactory ){
	return {
		link:function(scope, element, attrs){
			var userId =  scope.subject.principal;
			scope.suspendProcessInstance = function(processInstanceId) {
				if(!processInstanceId){
					Messenger.error("流程实例ID为空，请设置参数$params.processInstanceId");
					return;
				}
				$http.get("ws/suspendProcessInstance",{
					params:{
						processInstanceId:processInstanceId,
						wfOperator:{
							userId:userId,
							businessData:{}
						}
					}
				}).success(function(data){
					if(data.result_out.result=='200'){
						Messenger.post({'message': "挂起成功！",'type': 'success',});
					}
				});
			}
			var form = FormContainerFactory.findForm(scope);
			if (angular.isUndefined(scope.$state[attrs.id])) {
				scope.$state[attrs.id] = {};
			}
			form[attrs.id] = new suspendButton(scope.$state[attrs.id]);
		}
	};
}]);
