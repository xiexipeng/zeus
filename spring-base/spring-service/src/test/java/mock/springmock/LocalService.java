package mock.springmock;

import mock.MockResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p> 本地服务 </p>
 *
 * @author zhousi@maihaoche.com
 * @date 2019/8/19 下午3:29
 * @Version V1.0
 */
@Service
public class LocalService {

    @Autowired
    private ApiService apiService;

    public void executeService(Long id) {
        System.out.println("id is: " + id);
        System.out.println("verify result is: " + apiService.getMockResult(id));
    }

    public MockResult getMockeResult(Long id) {
        if (verify(id)){
            return new MockResult(1L,"yyf");
        }
        return new MockResult(2L,"ldd");
    }

    public boolean verify(Long id) {
        return id > 10 ? true : false;
    }
}
