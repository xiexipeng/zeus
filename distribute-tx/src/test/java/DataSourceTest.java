import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * <p> 数据源测试 </p>
 *
 * @author zhousi@maihaoche.com
 * @date 2019/8/4 下午5:19
 * @Version V1.0
 */
public class DataSourceTest extends BaseTest {

    private static final String testSql = "insert into account (name, phone, amount) values ('xxp','15158890800',20)";

    @Autowired
    @Qualifier("jdbcTemplateTx1")
    private JdbcTemplate jdbcTemplateTx1;


    @Autowired
    @Qualifier("jdbcTemplateTx2")
    private JdbcTemplate jdbcTemplateTx2;

    @Test
    public void testJdbcTemplateTx1(){
        jdbcTemplateTx1.update(testSql);
        jdbcTemplateTx2.update(testSql);
    }
}
