directives.directive('c2flowdiagramviewer', [
		'$location',
		'FormContainerFactory',
		function($location, FormContainer) {
			return {
				link : function(scope, element, attrs) {
					
					var processInstanceId = scope.$params.processInstanceId;
					var processDefinitionId = scope.$params.processDefinitionId;

					var srcUrl = 'workflow/diagram-viewer/index.jsp?processInstanceId='+processInstanceId+'&processDefinitionId='+processDefinitionId;
					$("#c2-flow-diagramviewer").attr("src",srcUrl);
				}
			};

		} ]);