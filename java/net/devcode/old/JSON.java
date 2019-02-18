package net.devcode.old;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JSON {
	
	private String result;
	
	public String convert(Object oo) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			result = mapper.writeValueAsString(oo);
		} catch (JsonGenerationException e) {
		} catch (JsonMappingException e) {
		} catch (IOException e) {
		}
		return result;
	}
	
}