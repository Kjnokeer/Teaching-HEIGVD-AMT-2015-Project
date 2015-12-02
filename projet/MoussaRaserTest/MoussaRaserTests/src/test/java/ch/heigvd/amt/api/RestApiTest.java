package ch.heigvd.amt.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.probedock.client.annotations.ProbeTest;
import java.io.IOException;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import org.junit.Before;

public class RestApiTest {

    private final String baseUrl = "http://localhost:8080/MoussaRaser/api";

    Client client;

    ObjectMapper mapper;

    JsonNodeFactory factory;

    @Before
    public void setup() {
        client = ClientBuilder.newClient();
        mapper = new ObjectMapper();
        factory = new JsonNodeFactory(false);
    }
    
    
    // Test Get Users
    @Test
    @ProbeTest(tags = "REST")
    public void sendingGetUsersWithoutApiKeyReturn401() throws IOException {
        String emptyApiKey = "";
        String messageError = "No API key was provided with the API request";
        /**
         * Send the POST request with the JSON payload to create a new Sector
         */
        WebTarget target = client.target(baseUrl).path("users").queryParam("apiKey", emptyApiKey);

        Response response = target.request().get();

        assertThat(response.getStatus()).isEqualTo(Response.Status.UNAUTHORIZED.getStatusCode());

        String jsonPayload = response.readEntity(String.class);
        assertThat(jsonPayload).isNotNull();
        assertThat(jsonPayload).isNotEmpty();
        
        System.out.println(jsonPayload);

        JsonNode[] rootNodeasArray = mapper.readValue(jsonPayload, JsonNode[].class);
        assertThat(rootNodeasArray).isNotNull();
        assertThat(rootNodeasArray).isNotEmpty();

        for (JsonNode users : rootNodeasArray) {
            assertThat(users.get("error").asText().compareToIgnoreCase(messageError));
        }
    }
    
    @Test
    @ProbeTest(tags = "REST")
    public void sendingGetUsersWithFakeApiKeyReturn401() throws IOException {
        String fakeApiKey = "092abcde6ec143c0bbe132253263e34s";
        String messageError = "An invalid API key was provided with the API request";
        /**
         * Send the POST request with the JSON payload to create a new Sector
         */
        WebTarget target = client.target(baseUrl).path("users").queryParam("apiKey", fakeApiKey);

        Response response = target.request().get();

        assertThat(response.getStatus()).isEqualTo(Response.Status.UNAUTHORIZED.getStatusCode());

        String jsonPayload = response.readEntity(String.class);
        assertThat(jsonPayload).isNotNull();
        assertThat(jsonPayload).isNotEmpty();
        
        System.out.println(jsonPayload);

        JsonNode[] rootNodeasArray = mapper.readValue(jsonPayload, JsonNode[].class);
        assertThat(rootNodeasArray).isNotNull();
        assertThat(rootNodeasArray).isNotEmpty();

        for (JsonNode users : rootNodeasArray) {
            assertThat(users.get("error").asText().compareToIgnoreCase(messageError));
        }
    }
    
    @Test
    @ProbeTest(tags = "REST")
    public void sendingGetUsersToExistingApiKeyReturnOk() throws IOException {
        String apiKeyWithUsers = "092abcde6ec143c0bbe132253263e340";
        /**
         * Send the POST request with the JSON payload to create a new Sector
         */
        WebTarget target = client.target(baseUrl).path("users").queryParam("apiKey", apiKeyWithUsers);

        Response response = target.request().get();

        assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());

        String jsonPayload = response.readEntity(String.class);
        assertThat(jsonPayload).isNotNull();
        assertThat(jsonPayload).isNotEmpty();

        JsonNode[] rootNodeasArray = mapper.readValue(jsonPayload, JsonNode[].class);
        assertThat(rootNodeasArray).isNotNull();
        assertThat(rootNodeasArray).isNotEmpty();

        for (JsonNode users : rootNodeasArray) {
            assertThat(users.get("firstname")).isNotNull();
            assertThat(users.get("lastname")).isNotNull();
        }
    }
    
    @Test
    @ProbeTest(tags = "REST")
    public void sendingGetUsersEmptyToExistingApiKeyReturnOkEmpty() throws IOException {
        String apiKeyWithoutUsers = "8def28edc4504a93b1acf88475f4d707";
        /**
         * Send the POST request with the JSON payload to create a new Sector
         */
        WebTarget target = client.target(baseUrl).path("users").queryParam("apiKey", apiKeyWithoutUsers);

        Response response = target.request().get();

        assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());

        String jsonPayload = response.readEntity(String.class);
        assertThat(jsonPayload).isNotNull();
        assertThat(jsonPayload).isNotEmpty();

        System.out.println(jsonPayload);

        JsonNode[] rootNodeasArray = mapper.readValue(jsonPayload, JsonNode[].class);
        assertThat(rootNodeasArray).isNotNull();
        assertThat(rootNodeasArray).isEmpty();
    }
}
