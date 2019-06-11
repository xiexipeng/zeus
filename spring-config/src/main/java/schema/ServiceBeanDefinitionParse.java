package schema;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * <p>  </p>
 *
 * @author zhousi@maihaoche.com
 * @date 2019/3/26 下午9:24
 * @Version
 */
public class ServiceBeanDefinitionParse implements BeanDefinitionParser {

    private final Class<?> beanClass;

    public ServiceBeanDefinitionParse(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(beanClass);
        beanDefinition.setLazyInit(false);
        String serviceName = element.getAttribute("serviceName");
        if (!StringUtils.isEmpty(serviceName)) {
            beanDefinition.getPropertyValues().addPropertyValue("serviceName", serviceName);
        }
        int retries = Integer.valueOf(element.getAttribute("retries"));
        beanDefinition.getPropertyValues().addPropertyValue("retries", retries);
        long timeOut = Long.valueOf(element.getAttribute("timeOut"));
        beanDefinition.getPropertyValues().addPropertyValue("timeOut", timeOut);
        parserContext.getRegistry().registerBeanDefinition(serviceName,beanDefinition);
        return beanDefinition;
    }
}
