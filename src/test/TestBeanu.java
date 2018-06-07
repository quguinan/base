package test;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import my.util.MD5FileUtil;
import my.util.MyBigDecimalConvert;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.SqlDateConverter;


public class TestBeanu {
	private BigDecimal m;

	public BigDecimal getM() {
		return m;
	}

	public void setM(BigDecimal m) {
		this.m = m;
	}
	
	
	static {
		ConvertUtils.register(new SqlDateConverter(null), java.sql.Date.class);
		ConvertUtils.register(new Converter() {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf_time = new SimpleDateFormat("yyyy-MM-dd HH:mm");

			@SuppressWarnings("rawtypes")
			public Object convert(Class type, Object value) {
				if (value == null)
					return null;
				if (value instanceof Date)
					return (value);
				try {
					return sdf_time.parse(value.toString());
				} catch (ParseException e) {
					try {
						return sdf.parse(value.toString());
					} catch (ParseException e1) {
						return null;
					}
				}
			}
		}, java.util.Date.class);
		
		ConvertUtils.register(new MyBigDecimalConvert(), java.math.BigDecimal.class);
		
		
//		ConvertUtils.register(new Converter() {
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			SimpleDateFormat sdf_time = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//
//			@SuppressWarnings("rawtypes")
//			public Object convert(Class type, Object value) {
//				if (value == null)
//					return null;
//				if (value instanceof Date)
//					return (value);
//				try {
//					return sdf_time.parse(value.toString());
//				} catch (ParseException e) {
//					try {
//						return sdf.parse(value.toString());
//					} catch (ParseException e1) {
//						return null;
//					}
//				}
//			}
//		}, java.math.BigDecimal.class);
	}

	
	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
		Map<String,String> map = new HashMap<String,String>();
		map.put("m", "");
		
		TestBeanu tb = new TestBeanu();
		
		BeanUtils.populate(tb, map);
		
		System.out.println(tb.m);
		
		
		System.out.println(MD5FileUtil.getMD5String("test").length());
		
		
	}
	
}
