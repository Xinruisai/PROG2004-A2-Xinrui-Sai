public abstract class Person {
    // 3个基础属性：姓名、年龄、身份证号
    private String name;
    private int age;
    private String idCard;

    // 无参构造器
    public Person() {}

    // 带参构造器（初始化所有属性）
    public Person(String name, int age, String idCard) {
        this.name = name;
        this.age = age;
        this.idCard = idCard;
    }

    // Getter和Setter（获取/修改属性值）
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}
}
