package leon.ssi.util;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;



public class SpringUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext;
	

	public final void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public static Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}
	
	public static Map getBeansByType(Class beanType) {
		return applicationContext.getBeansOfType(beanType);
	}
	
}
