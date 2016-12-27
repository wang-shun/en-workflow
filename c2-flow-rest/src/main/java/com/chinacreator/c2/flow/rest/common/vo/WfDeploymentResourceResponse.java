package com.chinacreator.c2.flow.rest.common.vo;


public class WfDeploymentResourceResponse {
  private String id;
  private String mediaType;
  private DeploymentResourceType type;
  
  public WfDeploymentResourceResponse(String resourceId, 
          String mediaType, DeploymentResourceType type) {
    setId(resourceId);
    setMediaType(mediaType);
    
    this.type = type;
    if(type == null) {
      this.type = DeploymentResourceType.RESOURCE;
    }
  }
  
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public void setMediaType(String mimeType) {
    this.mediaType = mimeType;
  }
  public String getMediaType() {
    return mediaType;
  } 
  public String getType() {
    switch (type) {
    case PROCESS_DEFINITION:
      return "processDefinition";
    case PROCESS_IMAGE:
      return "processImage";
    default:
      return "resource";
    }
  }
  
  /**
   * Possible types of resources in a deployment.
   * TODO: move to activiti API
   * 
   * @author Frederik Heremans
   */
  public enum DeploymentResourceType {
    /**
     * A simple resource in a deployment.
     */
    RESOURCE,
    /**
     * A resource that contains a process-definition.
     */
    PROCESS_DEFINITION,
    /**
     * A resource that is a process-definition image.
     */
    PROCESS_IMAGE;
  }
}
