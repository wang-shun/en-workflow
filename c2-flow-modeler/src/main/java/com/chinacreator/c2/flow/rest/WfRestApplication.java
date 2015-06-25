package com.chinacreator.c2.flow.rest;

import org.activiti.rest.common.api.DefaultResource;
import org.activiti.rest.common.application.ActivitiRestApplication;
import org.activiti.rest.common.filter.JsonpFilter;
//import org.activiti.rest.diagram.application.DiagramServicesInit;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import com.chinacreator.c2.flow.modeler.ModelerServicesInit;

public class WfRestApplication extends ActivitiRestApplication {
  
  public WfRestApplication() {
    super();
  }
  /**
   * Creates a root Restlet that will receive all incoming calls.
   */
  @Override
  public synchronized Restlet createInboundRoot() {
    Router router = new Router(getContext());
    router.attachDefault(DefaultResource.class);
    ModelerServicesInit.attachResources(router);
//    DiagramServicesInit.attachResources(router);
    JsonpFilter jsonpFilter = new JsonpFilter(getContext());
    jsonpFilter.setNext(router);
    return jsonpFilter;
  }

}
