var resumeButton = function (controlState) {
	this.state=controlState;
};
resumeButton.prototype = {
	setDisabled:function(disabled){
		this.state.disabled=disabled;
	},
	setVisible:function(visible){
		this.state.visible=visible;
	}
}
directives.directive('resumeButton', ['$http','Messenger','FormContainerFactory',function($http,Messenger,FormContainerFactory ){
	return {
		link:function(scope, element, attrs){
			var userId =  scope.subject.principal;
			scope.resumeProcessInstance = function(processInstanceId) {
				if(!processInstanceId){
					Messenger.error("流程实例ID为空，请设置参数$params.processInstanceId");
					return;
				}
				$http.get("ws/activateProcessInstance",{
					params:{
						processInstanceId:processInstanceId,
						wfOperator:{
							userId:userId,
							businessData:{}
						}
					}
				}).success(function(data){
					if(data.result_out.result=='200'){
						Messenger.post({'message': "恢复成功！",'type': 'success',});
					}
				});
			}
			var form = FormContainerFactory.findForm(scope);
			if (angular.isUndefined(scope.$state[attrs.id])) {
				scope.$state[attrs.id] = {};
			}
			form[attrs.id] = new resumeButton(scope.$state[attrs.id]);
		}
	};
}]);
