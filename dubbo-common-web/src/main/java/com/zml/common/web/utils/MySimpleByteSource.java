package com.zml.common.web.utils;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;

import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

/**
 * 是由于 SimpleByteSource 没有实现序列化接口导致。
 * 在集成redis缓存， 开启缓存认证时候报错
 * <property name="authenticationCachingEnabled" value="true" />
 * 自定义MySimpleByteSource继承SimpleByteSource，并实现序列化接口
 * @author zhaomingliang
 * @date 2017年2月27日
 */
public class MySimpleByteSource extends SimpleByteSource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8451585740425761925L;
	
/*	public MySimpleByteSource(ByteSource source) {
		super(source);
	}

	public MySimpleByteSource(File file) {
		super(file);
	}

	public MySimpleByteSource(InputStream stream) {
		super(stream);
	}
	
	public MySimpleByteSource(String string) {
		super(string);
	}*/
	
	public MySimpleByteSource(byte[] bytes) {
		super(bytes);
	}
	
/*	public MySimpleByteSource(char[] chars) {
		super(chars);
	}*/

}
