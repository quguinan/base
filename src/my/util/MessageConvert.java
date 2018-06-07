package my.util;

import java.io.IOException;

import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;

public class MessageConvert extends MappingJacksonHttpMessageConverter{
	@Override
	protected void writeInternal(Object object, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		outputMessage.getHeaders().add("Access-Control-Allow-Origin", "*");
		super.writeInternal(object, outputMessage);
	}
}
