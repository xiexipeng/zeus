package mock.springmock;

import mock.MockResult;

/**
 * <p> 远程API接口 </p>
 *
 * @author zhousi@maihaoche.com
 * @date 2019/8/19 下午3:27
 * @Version V1.0
 */
public interface ApiService {

    MockResult getMockResult(Long id);
}
