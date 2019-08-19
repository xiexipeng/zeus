package mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p> 实际服务 </p>
 *
 * @author zhousi@maihaoche.com
 * @date 2019/8/19 下午3:00
 * @Version V1.0
 */
public class RealityService {

    private MockService mockService;

    public RealityService(MockService mockService) {
        this.mockService = mockService;
    }

    public void executeService(Long id){
        System.out.println("id is: "+id);
        System.out.println("verify result is: "+mockService.verifyId(id));
    }
}
