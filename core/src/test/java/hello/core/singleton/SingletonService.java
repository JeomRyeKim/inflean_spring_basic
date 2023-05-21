package hello.core.singleton;

public class SingletonService {

    //1. static 영역에 객체를 딱 1개만 생성해둔다.
    // 자신 자신을 내부에 private으로 하나 가지고 있는데 static으로 갖고 있음
    // -> 클래스 레벨로 올라가기 때문에 딱 하나만 존재하게 됨. static 영역에 하나만 만들어져서 올라감
    // JVM이 뜰 때 SingletonService의 static 영역에 new 객체(자기 자신)을 생성한 뒤 변수 instance에 참조를 넣어둠.
    // 그래서 한 번 만들어진 뒤에 SingletonService를 new로 생성할 수 있는 곳은 아무 곳에도 없음.
    private static final SingletonService instance = new SingletonService();

    //2. public으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용한다.
    public static SingletonService getInstance() {
        return instance;
    }

    /**
     * 예를 들어서 누가 임의로 막 만들어버린다면? 그래서 막기 위해서 private 생성자를 씀. 의외로 private 생성자를 자주 사용함.
     * public static void main(String[] args) {
     *      SingletonService singletonService1 = new SingletonService();
     *      SingletonService singletonService2 = new SingletonService();
     * }
     */
    //3. 생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다.
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
