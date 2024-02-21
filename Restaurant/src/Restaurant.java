public class Restaurant {
    //Cookable chef = new Chef();
    Cookable chef = new NewChef();
    //인터페이스의 객체는 구현한 클래스의 생성자를 생성 할 수 있다.
    //spring은 객체 생성을 허용하지 않음(계속 객체를 바꾸면 결합도가 생성됨)
    //spring이 객체를 스스로 생성해줌(결합도를 없애줌)

    public void job(){
        chef.cook();
    }

}
