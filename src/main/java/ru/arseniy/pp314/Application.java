package ru.arseniy.pp314;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import ru.arseniy.pp314.model.User;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@SpringBootApplication
public class Application {

    static final String URL_USERS = "http://91.241.64.178:7081/api/users";

    public static void main(String[] args) throws URISyntaxException {
        SpringApplication.run(Application.class, args);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(URL_USERS, HttpMethod.GET, entity, String.class);

        List<String> cookies = response.getHeaders().get("Set-Cookie");
        headers.set("Cookie", cookies.stream().collect(Collectors.joining(";")));

        User myUser = new User(3L, "James", "Brown", (byte)31);
        URI uri = new URI(URL_USERS);

        RequestEntity<User> requestPostEntity = new RequestEntity<>(myUser,headers, HttpMethod.POST, uri);
        System.out.println(restTemplate.exchange(uri, HttpMethod.POST, requestPostEntity, String.class));

        User myUser2 = new User(3L, "Thomas", "Shelby", (byte)31);
        RequestEntity<User> requestPutEntity = new RequestEntity<>(myUser2,headers, HttpMethod.PUT, uri);
        System.out.println(restTemplate.exchange(uri, HttpMethod.PUT, requestPutEntity, String.class));

        URI deleteUri = new URI(URL_USERS + "/3");
        RequestEntity<User> requestDeleteEntity = new RequestEntity<>(myUser,headers, HttpMethod.DELETE, deleteUri);
        System.out.println(restTemplate.exchange(URL_USERS + "/3", HttpMethod.DELETE, requestDeleteEntity, String.class));

    }

}
