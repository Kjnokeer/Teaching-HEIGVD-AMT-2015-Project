package ch.heigvd.amt.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import io.probedock.client.annotations.ProbeTest;
import java.io.IOException;
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
    // Clé api avec le bon format mais qui n'existe pas dans la base de données
    private final String fakeApiKey = "092abcde6ec143c0bbe132253263e34s";
    // Clé api d'une application existante sans utilisateurs finaux
    private final String apiKeyWithUsers = "092abcde6ec143c0bbe132253263e340";
    // Clé api d'une application existante avec utilisateurs finaux
    private final String apiKeyWithoutUsers = "8def28edc4504a93b1acf88475f4d707";
    // Id utilisateur à modifier
    private final String idUserToModify = "1";
    // Id utilisateur à modifier
    private final String idUserToDisplay = "2";
    // Id utilisateur à supprimer
    private final String idUserToDelete = "3";

    private Client client;
    private ObjectMapper mapper;
    private JsonNodeFactory factory;

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
         * Requête GET à l'api pour récupérer liste d'utilisateurs d'une
         * application
         */
        WebTarget target = client.target(baseUrl).path("users").queryParam("apiKey", emptyApiKey);
        Response response = target.request().get();

        /**
         * L'api doit renvoyer un code 401
         */
        assertThat(response.getStatus()).isEqualTo(Response.Status.UNAUTHORIZED.getStatusCode());

        /**
         * Récupérer la réponse et contrôler qu'elle n'est pas vide
         */
        String jsonPayload = response.readEntity(String.class);
        assertThat(jsonPayload).isNotNull();
        assertThat(jsonPayload).isNotEmpty();

        /**
         * L'api doit renvoyer le bon code d'erreur
         */
        JsonNode root = mapper.readTree(jsonPayload);
        assertThat(root.get("error").asText().compareToIgnoreCase(messageError));
    }

    @Test
    @ProbeTest(tags = "REST")
    public void sendingGetUsersWithFakeApiKeyReturn401() throws IOException {
        String messageError = "An invalid API key was provided with the API request";
        /**
         * Requête GET à l'api pour récupérer liste d'utilisateurs d'une
         * application
         */
        WebTarget target = client.target(baseUrl).path("users").queryParam("apiKey", fakeApiKey);
        Response response = target.request().get();

        /**
         * L'api doit renvoyer un code 401
         */
        assertThat(response.getStatus()).isEqualTo(Response.Status.UNAUTHORIZED.getStatusCode());

        /**
         * Récupérer la réponse et contrôler qu'elle n'est pas vide
         */
        String jsonPayload = response.readEntity(String.class);
        assertThat(jsonPayload).isNotNull();
        assertThat(jsonPayload).isNotEmpty();

        /**
         * L'api doit renvoyer le bon code d'erreur
         */
        JsonNode root = mapper.readTree(jsonPayload);
        assertThat(root.get("error").asText().compareToIgnoreCase(messageError));
    }

    @Test
    @ProbeTest(tags = "REST")
    public void sendingGetUsersToExistingApiKeyReturnOk() throws IOException {
        /**
         * Requête GET à l'api pour récupérer liste d'utilisateurs d'une
         * application
         */
        WebTarget target = client.target(baseUrl).path("users").queryParam("apiKey", apiKeyWithUsers);
        Response response = target.request().get();

        /**
         * L'api doit renvoyer un code 200
         */
        assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());

        /**
         * Récupérer la réponse et contrôler qu'elle n'est pas vide
         */
        String jsonPayload = response.readEntity(String.class);
        assertThat(jsonPayload).isNotNull();
        assertThat(jsonPayload).isNotEmpty();

        /**
         * Formater au format JSON
         */
        JsonNode[] rootNodeasArray = mapper.readValue(jsonPayload, JsonNode[].class);
        assertThat(rootNodeasArray).isNotNull();
        assertThat(rootNodeasArray).isNotEmpty();

        /**
         * L'api doit renvoyer les informations suivantes
         */
        for (JsonNode users : rootNodeasArray) {
            assertThat(users.get("id")).isNotNull();
            assertThat(users.get("firstname")).isNotNull();
            assertThat(users.get("lastname")).isNotNull();
        }
    }

    @Test
    @ProbeTest(tags = "REST")
    public void sendingGetUsersEmptyToExistingApiKeyReturnOkEmpty() throws IOException {
        /**
         * Requête GET à l'api pour récupérer liste d'utilisateurs d'une
         * application
         */
        WebTarget target = client.target(baseUrl).path("users").queryParam("apiKey", apiKeyWithoutUsers);
        Response response = target.request().get();

        /**
         * L'api doit renvoyer un code 200
         */
        assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());

        /**
         * Récupérer la réponse et contrôler qu'elle n'est pas vide
         */
        String jsonPayload = response.readEntity(String.class);
        assertThat(jsonPayload).isNotNull();
        assertThat(jsonPayload).isNotEmpty();

        /**
         * Formater au format JSON
         */
        JsonNode[] rootNodeasArray = mapper.readValue(jsonPayload, JsonNode[].class);
        /**
         * L'api doit renvoyer une valeur non null, mais vide
         */
        assertThat(rootNodeasArray).isNotNull();
        assertThat(rootNodeasArray).isEmpty();
    }

    @Test
    @ProbeTest(tags = "REST")
    public void itShouldBePossibleToCreateAnEndUser() throws IOException {
        /**
         * Création de l'utilisateur au format JSON
         */
        String firstName = "Thibaud";
        String lastName = "Duchoud";
        JsonNode userInfo = factory.objectNode().put("firstName", firstName)
                .put("lastName", lastName);

        /**
         * Requête POST à l'api pour ajouter l'utilisateur créé auparavant à
         * l'application
         */
        WebTarget target = client.target(baseUrl).path("users").queryParam("apiKey", apiKeyWithUsers);
        Response response = target.request().post(Entity.entity(userInfo, "application/json"));

        /**
         * L'api doit renvoyer un code 201
         */
        assertThat(response.getStatus()).isEqualTo(Response.Status.CREATED.getStatusCode());

        /**
         * L'api doit renvoyer les informations de l'utilisateur correctes
         */
        String jsonPayload = response.readEntity(String.class);
        JsonNode root = mapper.readTree(jsonPayload);
        assertThat(root.get("id")).isNotNull();
        assertThat(root.get("firstname").asText().compareToIgnoreCase(firstName));
        assertThat(root.get("lastname").asText().compareToIgnoreCase(lastName));
    }
    
    @Test
    @ProbeTest(tags = "REST")
    public void itShouldBePossibleToModifyAnEndUser() throws IOException {
        /**
         * Création de l'utilisateur au format JSON
         */
        String firstName = "Mario";
        String lastName = "Modif";
        JsonNode userInfo = factory.objectNode().put("firstName", firstName)
                .put("lastName", lastName);

        /**
         * Requête POST à l'api pour ajouter l'utilisateur créé auparavant à
         * l'application
         */
        WebTarget target = client.target(baseUrl).path("users").path(idUserToModify).queryParam("apiKey", apiKeyWithUsers);
        Response response = target.request().put(Entity.entity(userInfo, "application/json"));

        /**
         * L'api doit renvoyer un code 201
         */
        assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());

        /**
         * L'api doit renvoyer les informations de l'utilisateur correctes
         */
        String jsonPayload = response.readEntity(String.class);
        JsonNode root = mapper.readTree(jsonPayload);
        assertThat(root.get("id").asText().compareToIgnoreCase(idUserToModify));
        assertThat(root.get("firstname").asText().compareToIgnoreCase(firstName));
        assertThat(root.get("lastname").asText().compareToIgnoreCase(lastName));
    }
    
    @Test
    @ProbeTest(tags = "REST")
    public void itShouldByPossibleToDisplayAnEndUser() throws IOException {        
        String firstName = "Thibaud";
        String lastName = "Duchoud";
        /**
         * Requête GET à l'api pour récupérer liste d'utilisateurs d'une
         * application
         */
        WebTarget target = client.target(baseUrl).path("users").path(idUserToDisplay).queryParam("apiKey", apiKeyWithUsers);
        Response response = target.request().get();

        /**
         * L'api doit renvoyer un code 200
         */
        assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());

        /**
         * Récupérer la réponse et contrôler qu'elle n'est pas vide
         */
        String jsonPayload = response.readEntity(String.class);
        assertThat(jsonPayload).isNotNull();
        assertThat(jsonPayload).isNotEmpty();

        /**
         * L'api doit renvoyer les informations de l'utilisateur correctes
         */
        JsonNode root = mapper.readTree(jsonPayload);
        assertThat(root.get("firstname").asText().compareToIgnoreCase(firstName));
        assertThat(root.get("lastname").asText().compareToIgnoreCase(lastName));
    }
    
    @Test
    @ProbeTest(tags = "REST")
    public void itShouldByPossibleToDeleteAnEndUser() throws IOException {        
        String messageInfo = "User successfully deleted";
        /**
         * Requête GET à l'api pour récupérer liste d'utilisateurs d'une
         * application
         */
        WebTarget target = client.target(baseUrl).path("users").path(idUserToDelete).queryParam("apiKey", apiKeyWithUsers);
        Response response = target.request().delete();

        /**
         * L'api doit renvoyer un code 200
         */
        assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());

        /**
         * Récupérer la réponse et contrôler qu'elle n'est pas vide
         */
        String jsonPayload = response.readEntity(String.class);
        assertThat(jsonPayload).isNotNull();
        assertThat(jsonPayload).isNotEmpty();

        /**
         * L'api doit renvoyer le bon message
         */
        JsonNode root = mapper.readTree(jsonPayload);
        assertThat(root.get("information").asText().compareToIgnoreCase(messageInfo));
    }
    
    @Test
    @ProbeTest(tags = "REST")
    public void sendingGetBadgesOfUserEmptyToExistingApiKeyReturnOkEmpty() throws IOException {
        /**
         * Requête GET à l'api pour récupérer liste d'utilisateurs d'une
         * application
         */
        WebTarget target = client.target(baseUrl).path(idUserToDisplay).path("badges").queryParam("apiKey", apiKeyWithUsers);
        Response response = target.request().get();

        /**
         * L'api doit renvoyer un code 200
         */
        assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());

        /**
         * Récupérer la réponse et contrôler qu'elle n'est pas vide
         */
        String jsonPayload = response.readEntity(String.class);
        assertThat(jsonPayload).isNotNull();
        assertThat(jsonPayload).isNotEmpty();

        /**
         * Formater au format JSON
         */
        JsonNode[] rootNodeasArray = mapper.readValue(jsonPayload, JsonNode[].class);
        /**
         * L'api doit renvoyer une valeur non null, mais vide
         */
        assertThat(rootNodeasArray).isNotNull();
        assertThat(rootNodeasArray).isEmpty();
    }
}
