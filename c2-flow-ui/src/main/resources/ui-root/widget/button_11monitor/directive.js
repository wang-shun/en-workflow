var monitorButton = function (controlState) {
	this.state=controlState;
};
monitorButton.prototype = {
	setDisabled:function(disabled){
		this.state.disabled=disabled;
	},
	setVisible:function(visible){
		this.state.visible=visible;
	}
}

directives.directive('monitorButton', ['Modal','FormContainerFactory',function(Modal,FormContainerFactory ){
	return {
		link:function(scope, element, attrs){
			var userId =  scope.subject.principal;
			if(!processInstanceId){
				Messenger.error("流程实例ID为空，请设置参数$params.processInstanceId");
				return;
			}
			if(!processDefinitionId){
				Messenger.error("流程定义ID为空，请设置参数$params.processDefinitionId");
				return;
			}
			scope.monitorProcessInstance = function(processInstanceId, processDefinitionId) {
				Modal.open('f/monitorProcess?processInstanceId='+processInstanceId+'&processDefinitionId='+processDefinitionId,{processInstanceId:processInstanceId,processDefinitionId:processDefinitionId},function(){});	
			}
			var form = FormContainerFactory.findForm(scope);
			if (angular.isUndefined(scope.$state[attrs.id])) {
				scope.$state[attrs.id] = {};
			}
			form[attrs.id] = new monitorButton(scope.$state[attrs.id]);
		}
	};
}]);
