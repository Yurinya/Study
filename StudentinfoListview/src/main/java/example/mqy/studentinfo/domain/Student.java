package example.mqy.studentinfo.domain;

/**
 * Created by MQY on 2016/3/21.
 */
public class Student {
    private String id;
    private String name;
    private String sex;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ID:" + id + "   Name:" + name + "   Sex:" + sex ;
    }
}
