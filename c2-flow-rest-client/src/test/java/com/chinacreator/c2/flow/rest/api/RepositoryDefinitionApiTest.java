/**
 * 工作流接口文档
 * 工作流接口文档
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.chinacreator.c2.flow.rest.api;

import com.chinacreator.c2.flow.rest.ApiException;
import com.chinacreator.c2.flow.rest.model.WfProcessDefinitionActionRequest;
import com.chinacreator.c2.flow.rest.model.WfProcessDefinitionResponse;
import com.chinacreator.c2.flow.rest.model.PageListResponseWfProcessDefinitionResponse;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for RepositoryDefinitionApi
 */
public class RepositoryDefinitionApiTest {

    private final RepositoryDefinitionApi api = new RepositoryDefinitionApi();

    
    /**
     * 修改流程某些信息
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void executeProcessDefinitionActionTest() throws ApiException {
        String processDefinitionId = null;
        WfProcessDefinitionActionRequest body = null;
        // WfProcessDefinitionResponse response = api.executeProcessDefinitionAction(processDefinitionId, body);

        // TODO: test validations
    }
    
    /**
     * 获取流程定义模型
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getModelResourceTest() throws ApiException {
        String processDefinitionId = null;
        // Object response = api.getModelResource(processDefinitionId);

        // TODO: test validations
    }
    
    /**
     * 获取单个流程信息
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getProcessDefinitionTest() throws ApiException {
        String processDefinitionId = null;
        // WfProcessDefinitionResponse response = api.getProcessDefinition(processDefinitionId);

        // TODO: test validations
    }
    
    /**
     * 下载流程定义文件
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getProcessDefinitionResourceDataTest() throws ApiException {
        String processDefinitionId = null;
        // byte[] response = api.getProcessDefinitionResourceData(processDefinitionId);

        // TODO: test validations
    }
    
    /**
     * 流程定义列表
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getProcessDefinitionsTest() throws ApiException {
        String category = null;
        String categoryLike = null;
        String categoryNotEquals = null;
        String key = null;
        String keyLike = null;
        String name = null;
        String nameLike = null;
        String resourceName = null;
        String resourceNameLike = null;
        Integer version = null;
        Boolean suspended = null;
        Boolean latest = null;
        String deploymentId = null;
        // PageListResponseWfProcessDefinitionResponse response = api.getProcessDefinitions(category, categoryLike, categoryNotEquals, key, keyLike, name, nameLike, resourceName, resourceNameLike, version, suspended, latest, deploymentId);

        // TODO: test validations
    }
    
}
