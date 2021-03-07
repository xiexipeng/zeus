package mock.springmock;

import mock.MockResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * <p> 测试spring+mock </p>
 *
 * @author zhousi@maihaoche.com
 * @date 2019/8/19 下午3:30
 * @Version V1.0
 */
public class TestSpringMockService  {

    @InjectMocks
    private LocalService localService = new LocalService();

    @Mock
    private ApiService apiService;

    private LocalService partLocalService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        Mockito.when(apiService.getMockResult(1L)).thenReturn(new MockResult(1L,"xxp"));
        partLocalService = Mockito.spy(LocalService.class);
        Mockito.doReturn(true).when(partLocalService).verify(Mockito.anyLong());
    }

    @Test
    public void testSpringMock(){
        localService.executeService(1L);
    }

    @Test
    public void testPrivateMethod(){
        MockResult mockeResult = partLocalService.getMockeResult(1L);
        System.out.println(mockeResult);
        Assert.assertEquals("yyf",mockeResult.getName());
    }
}
