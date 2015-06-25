package com.chinacreator.c2.flow.modeler;

//import org.activiti.rest.diagram.services.ProcessDefinitionDiagramLayoutResource;
import org.activiti.rest.editor.main.EditorRestResource;
import org.activiti.rest.editor.main.PluginRestResource;
import org.activiti.rest.editor.main.StencilsetRestResource;
//import org.activiti.rest.editor.model.ModelEditorJsonRestResource;
import org.restlet.routing.Router;

import com.chinacreator.c2.flow.modeler.model.ModelEditorJsonRestResource;
import com.chinacreator.c2.flow.modeler.model.ModelSaveRestResource;
import com.chinacreator.c2.flow.modeler.services.ProcessDefinitionDiagramLayoutResource;
import com.chinacreator.c2.flow.modeler.services.ProcessInstanceHighlightsResource;

public class ModelerServicesInit {

  public static void attachResources(Router router) {
    router.attach("/model/{modelId}/json", ModelEditorJsonRestResource.class);
    router.attach("/model/{modelId}/save", ModelSaveRestResource.class);
    
    router.attach("/editor", EditorRestResource.class);
    router.attach("/editor/plugins", PluginRestResource.class);
    router.attach("/editor/stencilset", StencilsetRestResource.class);
    
    // 流程监控图的资源访问
    
    // 这各地方进行了高亮的改造 --by yicheng.yang 20140805
    router.attach("/process-instance/{processInstanceId}/highlights", ProcessInstanceHighlightsResource.class);
    router.attach("/process-instance/{processInstanceId}/diagram-layout", ProcessDefinitionDiagramLayoutResource.class);
    router.attach("/process-definition/{processDefinitionId}/diagram-layout", ProcessDefinitionDiagramLayoutResource.class);
    
  }
}
