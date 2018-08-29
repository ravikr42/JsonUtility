package com.wordpress.ravikr42;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * @author ravik
 * 
 *         JsonUtil contains list of utility methods which can be used to day to
 *         day JSON Operations
 * 
 */

public class JsonUtil {

	private JsonUtil() {
	}

	private static Log logger = LogFactory.getLog(JsonUtil.class);

	public static String mapToJsonFormat(Map<String, Object> inputMap) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonText = "";
		try {
			jsonText = mapper.writeValueAsString(inputMap);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return jsonText;
	}
	
	public static String toPrettyJsonFormat(String jsonText){
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = parser.parse(jsonText).getAsJsonObject();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(jsonObject);
	}
	
	public static String toPrettyJsonFormat(Map<String, Object> inputMap){
		String jsonText = mapToJsonFormat(inputMap);
		return toPrettyJsonFormat(jsonText);
	}
	
	public static String readJsonFile(String jsonFilePath){
		String jsonText = null;
		try {
			jsonText = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return jsonText;
	}
	
	public static JsonArray getJsonArray(String jsonText, String jsonKey){
		JsonParser parser = new JsonParser();
		Object object = parser.parse(jsonText);
		JsonObject jsonObject = (JsonObject) object;
		JsonArray jsonArray = (JsonArray) jsonObject.get(jsonKey);
		
		if(jsonArray instanceof JsonArray){
			return jsonArray;
		}else{
			throw new NullPointerException("Error Message");
		}
	}
	
	

}
