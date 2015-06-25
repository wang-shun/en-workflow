var FlowDefinitioner=function(element){
	this.element=element;
};
FlowDefinitioner.prototype={
	setUrl:function(urlPre,processDefinitionId){
		if(processDefinitionId && processDefinitionId!=""){
			this.element.attr("src",urlPre+processDefinitionId);
		}else{
			this.element.attr("src",'');
		}
	}	
};

directives.directive('c2flowdefinitioner', [
		'$location',
		'FormContainerFactory',
		function($location, FormContainer) {
			return {
				link : function(scope, element, attrs) {
					var form = FormContainer.findForm(scope);
					form[attrs.id]=new FlowDefinitioner(element);
					
					var processDefinitionId = scope.$params.processDefinitionId;
					console.log(scope.$params.processDefinitionId);
					if(processDefinitionId){
						var srcUrlPre = 'workflow/diagram-viewer/processDefView.jsp?processDefinitionId=';
						form[attrs.id].setUrl(srcUrlPre+processDefinitionId);
					}
					
				}
			};

		} ]);