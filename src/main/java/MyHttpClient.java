import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MyHttpClient {

    private final Gson MYGSON = new Gson();
    private final String MYURI = "https://jsonplaceholder.typicode.com/users";
    private final String MYURI_COMMENT="https://jsonplaceholder.typicode.com";
    private final String USER_NAME = "?username=Leopoldo_Corkery";
    private final int ID = 1;
    private final HttpClient HTTP = HttpClient.newHttpClient();
private final File P=new File("src\\main\\resouces\\user-X-post-Y-comments.json");
    public void post() throws IOException, InterruptedException {
        String s = MYGSON.toJson(getP());

        HttpRequest build = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(s))
                .uri(URI.create(MYURI))
                .header("Content-type", "application/json")
                .build();
        HttpResponse<String> send = HTTP.send(build, HttpResponse.BodyHandlers.ofString());
        System.out.println(send.body());
    }

    public void put(Person p) throws IOException, InterruptedException {
        String s = MYGSON.toJson(p);
        HttpRequest build = HttpRequest.newBuilder()
                .PUT(HttpRequest.BodyPublishers.ofString(s))
                .uri(URI.create(MYURI + "/" + ID))
                .header("Content-type", "application/json")
                .build();
        HttpResponse<String> send = HTTP.send(build, HttpResponse.BodyHandlers.ofString());
        System.out.println(send.body());

    }

    public void delete() throws IOException, InterruptedException {
        HttpRequest build = HttpRequest.newBuilder()
                .uri(URI.create(MYURI + "/" + ID))
                .header("Content-type", "application/json")
                .DELETE()
                .build();
        HttpResponse<String> send = HTTP.send(build, HttpResponse.BodyHandlers.ofString());
        System.out.println(send.statusCode());
    }

    public void getAll() throws IOException, InterruptedException {
        HttpRequest build = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(MYURI))
                .header("Content-type", "application/json")
                .build();
        HttpResponse<String> send = HTTP.send(build, HttpResponse.BodyHandlers.ofString());
        System.out.println(send.body());
    }

    public void getId() throws IOException, InterruptedException {
        HttpRequest build = HttpRequest.newBuilder()
                .header("Content-type", "application/json")
                .uri(URI.create(MYURI + "/" + ID))
                .GET()
                .build();
        HttpResponse<String> send = HTTP.send(build, HttpResponse.BodyHandlers.ofString());
        System.out.println(send.body());

    }
public void getByUserName() throws IOException, InterruptedException {
    HttpRequest build = HttpRequest.newBuilder()
            .uri(URI.create(MYURI + USER_NAME))
            .header("Content-type", "application/json")
            .GET()
            .build();
    HttpResponse<String> send = HTTP.send(build, HttpResponse.BodyHandlers.ofString());
    System.out.println(send.body());

}
    private int getIdByPost() throws IOException, InterruptedException {
        HttpRequest build = HttpRequest.newBuilder()

                .header("Content-type", "application/json")
                .uri(URI.create(MYURI + "/" + ID+"/posts"))
                .GET()
                .build();
        HttpResponse<String> send = HTTP.send(build, HttpResponse.BodyHandlers.ofString());
        List<Post> posts = MYGSON.fromJson(send.body(), new TypeToken<List<Post>>() {
        }.getType());
        Post  max = Collections.max(posts, Comparator.comparing(Post::getId));
      return max.getId();

    }
    public void getIdByComments() throws IOException, InterruptedException {
        HttpRequest build = HttpRequest.newBuilder()

                .header("Content-type", "application/json")
                .uri(URI.create(MYURI_COMMENT + "/" + "posts"+"/"+getIdByPost()+"/"+"comments"))
                .GET()
                .build();
        HttpResponse<Path> send = HTTP.send(build, HttpResponse.BodyHandlers.ofFile(Paths.get("src\\main\\resources\\user-"+ID+"-post-"+getIdByPost()+"-comments.json")));
        System.out.println(send.body());
    }
    public void task() throws IOException, InterruptedException {
        HttpRequest build = HttpRequest.newBuilder()
                .GET()
                .header("Content-type", "application/json")
                .uri(URI.create("https://jsonplaceholder.typicode.com/users/"+ID+"/todos"))
                .build();
        HttpResponse<String> send = HTTP.send(build, HttpResponse.BodyHandlers.ofString());
        List<Task> task = MYGSON.fromJson(send.body(), new TypeToken<List<Task>>() {
        }.getType());
        List<Task> collect = task.stream().filter(task1 -> !task1.isCompleted()).collect(Collectors.toList());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String s = gson.toJson(collect);
        System.out.println(s);
    }
    public Person getP() {
        Person person = new Person();

        person.setId(1);
        person.setName("Vova");
        person.setUsername("Vova P");
        person.setEmail("V@gmail.com");
        person.setAddress(setA());
        person.setPhone("dggdg");
        person.setWebsite("gsdfdg");
        person.setCompany(setC());


        return person;
    }

    public Address setA() {
        Address address = new Address();
        address.setStreet("Kol");
        address.setSuite("dsgg");
        address.setCity("Kiev");
        address.setZipcode("fsdfd");
        address.setGeo(setG());
        return address;
    }

    public Geo setG() {
        Geo geo = new Geo();
        geo.setLnt("dfgdfg");
        geo.setLat("fgdfg");
        return geo;
    }

    public Company setC() {
        Company company = new Company();
        company.setName("fsdf");
        company.setBs("fssf");
        company.setCatchPhrase("dfsdf");
        return company;
    }


}
