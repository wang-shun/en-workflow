package com.en.cool.flow.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;

import com.chinacreator.c2.ioc.ApplicationContextManager;

public class EnManagerServiceImpl {

	public void saveModel(String modelId, String tenantId, String name,
			String description, String bpmn2_xml, String svg_xml) throws Exception{
		RepositoryService repositoryService = 
				ApplicationContextManager.getContext().getBean(RepositoryService.class);
		ObjectMapper objectMapper = new ObjectMapper();
		Model model = repositoryService.getModel(modelId);

		ObjectNode modelJson = (ObjectNode) objectMapper.readTree(model
				.getMetaInfo());

		modelJson.put(ModelDataJsonConstants.MODEL_NAME, name);
		modelJson.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
		model.setMetaInfo(modelJson.toString());
		model.setName(name);

		repositoryService.saveModel(model);

		repositoryService.addModelEditorSource(model.getId(),
				bpmn2_xml.getBytes("utf-8"));

		InputStream svgStream = new ByteArrayInputStream(
				svg_xml.getBytes("utf-8"));
		TranscoderInput input = new TranscoderInput(svgStream);

		PNGTranscoder transcoder = new PNGTranscoder();
		// Setup output
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		TranscoderOutput output = new TranscoderOutput(outStream);

		// Do the transformation
		transcoder.transcode(input, output);
		final byte[] result = outStream.toByteArray();
		repositoryService.addModelEditorSourceExtra(model.getId(), result);
		outStream.close();
	}
}
