directives.directive('c2flowholidaycalender', [
		'$location',
		'FormContainerFactory',
		function($location, FormContainer) {
			return {
				link : function(scope, element, attrs) {
					
					/*var processInstanceId = scope.$params.processInstanceId;
					var processDefinitionId = scope.$params.processDefinitionId;*/

					var srcUrl = 'holidayCalender/Calender.jsp';
					console.log(12322);
					$("#c2-flow-holidaycalender").attr("src",srcUrl);
				}
			};

		} ]);