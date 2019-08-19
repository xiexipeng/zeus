package mock;

/**
 * <p> mock结果 </p>
 *
 * @author zhousi@maihaoche.com
 * @date 2019/8/19 下午3:15
 * @Version V1.0
 */
public class MockResult {

    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MockResult(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "MockResult{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
