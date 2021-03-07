package BO;

/**
 * <p> 服务配置 </p>
 *
 * @author zhousi@maihaoche.com
 * @date 2019/3/26 下午2:08
 * @Version V1.0
 */

public class ServiceConfig {

    private String serviceName;

    private long timeOut;

    private int retries;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public long getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(long timeOut) {
        this.timeOut = timeOut;
    }

    public int getRetries() {
        return retries;
    }

    public void setRetries(int retries) {
        this.retries = retries;
    }
}
