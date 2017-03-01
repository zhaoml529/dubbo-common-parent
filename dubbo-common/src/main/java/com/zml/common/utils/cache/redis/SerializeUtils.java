package com.zml.common.utils.cache.redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;
import org.springframework.data.redis.serializer.SerializationException;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
* 不使用spring-data-redis自带的json序列化工具，一切操作基于string
**/
public class SerializeUtils{
	private static Logger logger = Logger.getLogger(SerializeUtils.class);
	
	public static final String EMPTY_JSON = "{}";
	
	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
	
	protected ObjectMapper objectMapper = new ObjectMapper();
	public SerializeUtils(){
		
	}
	
	/**
	 * java-object as json-string
	 * @param object
	 * @return
	 */
	public String seriazileAsString(Object object){
		if (object== null) {
			return EMPTY_JSON;
		}
		try {
			return this.objectMapper.writeValueAsString(object);
		} catch (Exception ex) {
			throw new SerializationException("Could not write JSON: " + ex.getMessage(), ex);
		}
	}
	
	/**
	 * json-string to java-object
	 * @param str
	 * @return
	 */
	public <T> T deserializeAsObject(String str,Class<T> clazz){
		if(str == null || clazz == null){
			return null;
		}
		try{
			return this.objectMapper.readValue(str, clazz);
		}catch (Exception ex) {
			throw new SerializationException("Could not write JSON: " + ex.getMessage(), ex);
		}
	}
	
	/**
	 * 序列化
	 * 
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public static byte[] serialize(Object object) throws Exception {
		if(object == null) return null;
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			// 序列化
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			byte[] bytes = baos.toByteArray();
			return bytes;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	/**
	 * 反序列化
	 * 
	 * @param bytes
	 * @return
	 * @throws Exception
	 */
	public static Object unSerialize(byte[] bytes) throws Exception {
		if(bytes == null) return null;
		ByteArrayInputStream bais = null;
		try {
			// 反序列化
			bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}
	
}