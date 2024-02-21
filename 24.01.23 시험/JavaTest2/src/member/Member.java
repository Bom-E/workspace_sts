package member;

public class Member {

    private String name;
    private String id;
    private String pw;
    private int age;

    void setInfo(String name, String id, String pw, int age){
        this.name = name;
        this.id = id;
        this.pw = pw;
        this.age = age;
    }

    void showInfo(){
        System.out.println("이름 :" + name);
        System.out.println("아이디 :" + id);
        System.out.println("패스워드 :" + pw);
        System.out.println("나이 :" + age);
    }

    boolean isLogin(String id, String pw){
        boolean isFind = false;
        if(id.equals(this.id) && pw.equals(this.pw)){
            isFind = true;
            System.out.println("로그인 가능");
        }
        else {
            System.out.println("로그인 불가능");
        }

        return isFind;
    }
}
