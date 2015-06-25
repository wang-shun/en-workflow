directives.directive('c2floweditor', [
		'$location',
		'FormContainerFactory',
		function($location, FormContainer) {
			return {
				link : function(scope, element, attrs) {
					var opType = scope.$params.opType;
					var name = scope.$params.name;
					var key = scope.$params.key;
					var desc = scope.$params.desc;
					var procDefId = scope.$params.procDefId;

					var srcUrl = "";
					if (opType == "create") {
						srcUrl = "workflow/model/create?name=" + name + "&key="
								+ key + "&description=" + desc;
					} else {
						srcUrl = "workflow/model/editProcessByConvert/"
								+ procDefId;
					}
//					document.getElementById("c2-flow-editor").src=srcUrl;
					$("#c2-flow-editor").attr("src",srcUrl);
				}
			};

		} ]);
