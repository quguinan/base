package my.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class AppContext implements ApplicationContextAware {
	static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext _applicationContext)
			throws BeansException {
		applicationContext = _applicationContext;
	}

	public static <T> T getBean(Class<T> clazz) {
		if(applicationContext==null){
			throw new RuntimeException("spring 未初始化");
		}
		return (T) applicationContext.getBean(clazz);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName) {
		if(applicationContext==null){
			throw new RuntimeException("spring 未初始化");
		}
		return (T) applicationContext.getBean(beanName);
	}

}
