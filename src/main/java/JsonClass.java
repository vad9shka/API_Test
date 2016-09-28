import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by Администратор on 24.09.2016.
 */
public class JsonClass {

    public static void main(String[] args) throws IOException {

        String name = "Ivan";
        int age = 25;
        Gson gson = new Gson();
        String string = gson.toJson(name);
        System.out.println(string);


        User userObj = new User(25,"Ivan","Ivanov");
        String jsonString = gson.toJson(userObj);
        System.out.println(jsonString);

        try(FileReader reader = new FileReader("C:\\Java_Projects\\API_Test\\stringJson.json")){
            User read = gson.fromJson(reader,User.class);
            System.out.println(read);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        //отправка post запроса на определенный урл
        User userObj2 = new User(30, "Sergey", "Petrov");//есть каркас для json
        String postUrl = "https://vk.com/";//урл отправки запроса
        //HttpClient httpClient = new DefaultHttpClient();
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(postUrl);
        StringEntity postingString = new StringEntity(gson.toJson(userObj2));
        post.setEntity(postingString);
        post.setHeader("Content-type", "application/json");
        HttpResponse response = httpClient.execute(post);
        System.out.println(response);
        //httpClient.getConnectionManager().shutdown();


        //Get запрос json
        //httpClient = new DefaultHttpClient();

        HttpGet getRequest = new HttpGet("http://restcountries.eu/rest/v1/callingcode/7");
        getRequest.addHeader("accept", "application/json");
        HttpResponse response1 = httpClient.execute(getRequest);

        BufferedReader br = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));

        System.out.println(br);
        String output;
        //выводим json ответ от сервера
//        while((output = br.readLine())!=null){
//            System.out.println(output);
//        }

        gson.fromJson(br, JSONObject.class);
        //gson.fromJson(br, capitals.class);//чтобы распарсить json, необходимо создать новый класс, в котором указать все поля, совпадающие с ключами в json,
        //эти ключи - это будут переменные в классе, чтобы на их основе сформировать объект этого json
        gson.fromJson(br, List.class);// если json сам представляет массив, т.е. [{}]



    }
}
