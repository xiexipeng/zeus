package schema;

import BO.ServiceConfig;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * <p>  </p>
 *
 * @author zhousi@maihaoche.com
 * @date 2019/3/26 下午5:53
 * @Version
 */
public class ServiceNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("serviceConfig",new ServiceBeanDefinitionParse(ServiceConfig.class));
    }
}
