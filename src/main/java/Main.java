import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Person person = new Person();
        person.setUsername("sdgsd");
        person.setName("sdgd");

        MyHttpClient myHttpClient = new MyHttpClient();
        System.out.println("-----------Task1--------");
        System.out.println("___________Post_________");
        myHttpClient.post();
        System.out.println("___________Put__________");
        myHttpClient.put(person);
        System.out.println("___________Delete_______");
        myHttpClient.delete();
        System.out.println("___________GetAll_______");
        myHttpClient.getAll();
        System.out.println("___________GetId________");
        myHttpClient.getId();
        System.out.println("___________GetByUserName__________");
        myHttpClient.getByUserName();
        System.out.println("------------Task2--------");
        myHttpClient.getIdByComments();
        System.out.println("-----------Task3_________");
        myHttpClient.task();
    }
}
