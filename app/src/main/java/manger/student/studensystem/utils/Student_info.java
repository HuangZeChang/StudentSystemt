package manger.student.studensystem.utils;


public class Student_info {
    private String name;
    private String sex;
    private String id;//学号
    private String password;//学生登录密码
    private String phone;//手机号
    private int MathScore;//数学成绩
    private int ChineseScore;//语文成绩
    private int EnglishScore;//英语成绩
    private int order;//名次
    public Student_info(int chineseScore, int englishScore, int mathScore, String id, String name,String sex,String phone,String password,int order) {

        this.id = id;
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.sex = sex;
        ChineseScore = chineseScore;
        EnglishScore = englishScore;
        MathScore = mathScore;
        this.order=order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getMathScore() {
        return MathScore;
    }

    public void setMathScore(int mathScore) {
        MathScore = mathScore;
    }

    public int getChineseScore() {
        return ChineseScore;
    }

    public void setChineseScore(int chineseScore) {
        ChineseScore = chineseScore;
    }

    public int getEnglishScore() {
        return EnglishScore;
    }

    public void setEnglishScore(int englishScore) {
        EnglishScore = englishScore;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
