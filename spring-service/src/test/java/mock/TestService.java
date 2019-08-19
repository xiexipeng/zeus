package mock;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * <p> 测试服务 </p>
 *
 * @author zhousi@maihaoche.com
 * @date 2019/8/19 下午3:02
 * @Version V1.0
 */
public class TestService {

    private MockService mockService;
    private RealityService realityService;

    @Before
    public void setUp(){
     mockService = mock(MockService.class);
     when(mockService.verifyId(1L)).thenReturn(new MockResult(1L,"xxp"));
     realityService = new RealityService(mockService);
    }

    @Test
    public void testVerify(){
        realityService.executeService(2L);
    }
}
