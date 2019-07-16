package com.xxp.domin;

/**
 * <p> 数据库死锁测试表 </p>
 *
 * @author zhousi@maihaoche.com
 * @date 2019/7/16 下午8:06
 * @Version V1.0
 */

public class LockDemo {

    private Long id;

    private String name;

    private String ability;

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

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    @Override
    public String toString() {
        return "LockDemo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ability='" + ability + '\'' +
                '}';
    }
}
