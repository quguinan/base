package test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.codec.binary.Base64InputStream;
import org.apache.commons.codec.binary.Base64OutputStream;
import org.apache.commons.io.IOUtils;

import my.dao.pool.DBManager;

public class Test {
	public static void main(String[] args) {
		//测试通过gz压缩->base64编码后字符串  
		String queryString = "I am still alive";  
		ByteArrayOutputStream bos = new ByteArrayOutputStream();  
		Base64OutputStream b64os = new Base64OutputStream(bos);  
		try {
			GZIPOutputStream gout = new GZIPOutputStream(b64os);  
			gout.write(queryString.getBytes("UTF-8"));  
			gout.close();  
			b64os.close();  
			  
			byte b1[] = bos.toByteArray();  
			  
			System.out.println("Encode:" + new String(b1));  
			  
//		      String s1 = null;  
			InputStream bais = new ByteArrayInputStream(b1);  
			Base64InputStream b64io = new Base64InputStream(bais);   
			GZIPInputStream gin = new GZIPInputStream(b64io);
			System.out.println(IOUtils.toString(gin,"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		//toString 方法建议制定编码，否则采用系统默认编码，出现中文编码错误的问题  
		  
		  
	}
	

}
