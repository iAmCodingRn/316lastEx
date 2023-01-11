package kata.RestTemplate.lastEx;

import kata.RestTemplate.lastEx.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {
	private static final String URL = "http://94.198.50.185:7081/api/users";
	private static final User postUserEntity = new User(3L, "James", "Brown", (byte) 17);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		fetchData();
	}

	public static void fetchData() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = restTemplate.headForHeaders(URL);


		String sessionInfo = headers.getFirst("Set-Cookie");

		HttpHeaders entityHeaders = new HttpHeaders();
		entityHeaders.add(HttpHeaders.COOKIE, sessionInfo);

		HttpEntity<User> postHttpEntity = new HttpEntity<>(postUserEntity, entityHeaders);

		ResponseEntity<String> postResponseEntity = restTemplate.exchange(URL, HttpMethod.POST, postHttpEntity, String.class);

		String firstPart = postResponseEntity.getBody();

		postUserEntity.setName("Thomas");
		postUserEntity.setLastName("Shelby");

		HttpEntity<User> putHttpEntity = new HttpEntity<>(postUserEntity, entityHeaders);

		ResponseEntity<String> putResponseEntity = restTemplate.exchange(URL, HttpMethod.PUT, putHttpEntity, String.class);

		String secondPart = putResponseEntity.getBody();

		HttpEntity<String> deleteHttpEntity = new HttpEntity<>(entityHeaders);

		ResponseEntity<String> deleteResponseEntity = restTemplate.exchange(URL + "/3", HttpMethod.DELETE, deleteHttpEntity, String.class);

		String thirdPart = deleteResponseEntity.getBody();

		System.out.println(firstPart + secondPart + thirdPart);

		System.exit(0);
	}

}