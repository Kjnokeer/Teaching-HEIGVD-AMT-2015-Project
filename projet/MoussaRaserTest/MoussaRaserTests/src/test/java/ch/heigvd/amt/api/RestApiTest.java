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
    private final String apiKeyWithUsers = "a2f62188c9d74a3d82fa508ffa0cae6a";
    // Clé api d'une application existante avec utilisateurs finaux
    private final String apiKeyWithoutUsers = "93073845311d4b678e6ce04f8933067f";
    // Id utilisateur inexistant    
    private final String idFakeUser = "222222222";
    // Id utilisateur à modifier
    private final String idUserToModify = "1";
    // Id utilisateur à afficher
    private final String idUserToDisplay = "2";
    // Id utilisateur sans badges
    private final String idUserWithoutBadges = "2";
    // Id utilisateur sans rewards
    private final String idUserWithoutRewards = "2";
    // Id utilisateur avec badges
    private final String idUserWithBadges = "1";
    // Id utilisateur avec rewards
    private final String idUserWithRewards = "1";

    private Client client;
    private ObjectMapper mapper;
    private JsonNodeFactory factory;

    @Before
    public void setup() {
        client = ClientBuilder.newClient();
        mapper = new ObjectMapper();
        factory = new JsonNodeFactory(false);
    }

    /**
     * Tester l'api pour les fonctionnalités "Users"
     */
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
    public void itShouldBePossibleToDisplayAnEndUser() throws IOException {
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
    public void itShouldBePossibleToDeleteAnEndUser() throws IOException {
        String idUserToDelete;
        String messageInfo = "User successfully deleted";
        /**
         * Création de l'utilisateur à supprimer
         */

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
        idUserToDelete = String.valueOf(root.get("id"));

        /**
         * Suppression de l'utilisateur
         */
        /**
         * Requête DELETE pour supprimer un utilisateur
         */
        target = client.target(baseUrl).path("users").path(idUserToDelete).queryParam("apiKey", apiKeyWithUsers);
        response = target.request().delete();

        /**
         * L'api doit renvoyer un code 200
         */
        assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());

        /**
         * Récupérer la réponse et contrôler qu'elle n'est pas vide
         */
        jsonPayload = response.readEntity(String.class);
        assertThat(jsonPayload).isNotNull();
        assertThat(jsonPayload).isNotEmpty();

        /**
         * L'api doit renvoyer le bon message
         */
        root = mapper.readTree(jsonPayload);
        assertThat(root.get("information").asText().compareToIgnoreCase(messageInfo));
    }

    @Test
    @ProbeTest(tags = "REST")
    public void itShouldNotBePossibleToDeleteANotExistantEndUser() throws IOException {
        String messageError = "The user ID you requested was invalid";
        /**
         * Requête DELETE pour supprimer un utilisateur
         */
        WebTarget target = client.target(baseUrl).path("users").path(idFakeUser).queryParam("apiKey", apiKeyWithUsers);
        Response response = target.request().delete();

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
    public void sendingGetBadgesOfUserEmptyToExistingApiKeyReturnOkEmpty() throws IOException {
        /**
         * Requête GET à l'api pour récupérer liste de badges d'un utilisateur
         * d'une application
         */
        WebTarget target = client.target(baseUrl).path("users").path(idUserWithoutBadges).path("badges").queryParam("apiKey", apiKeyWithUsers);
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
    public void sendingGetBadgesOfUserToExistingApiKeyReturnOk() throws IOException {
        /**
         * Requête GET à l'api pour récupérer liste de badges d'un utilisateur
         * d'une application
         */
        WebTarget target = client.target(baseUrl).path("users").path(idUserWithBadges).path("badges").queryParam("apiKey", apiKeyWithUsers);
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
        for (JsonNode badges : rootNodeasArray) {
            assertThat(badges.get("id")).isNotNull();
            assertThat(badges.get("name")).isNotNull();
            assertThat(badges.get("category")).isNotNull();
            assertThat(badges.get("description")).isNotNull();
            assertThat(badges.get("image")).isNotNull();
        }
    }

    @Test
    @ProbeTest(tags = "REST")
    public void sendingGetRewardOfUserEmptyToExistingApiKeyReturnOkEmpty() throws IOException {
        /**
         * Requête GET à l'api pour récupérer liste des rewards d'un utilisateur
         * d'une application
         */
        WebTarget target = client.target(baseUrl).path("users").path(idUserWithoutRewards).path("rewards").queryParam("apiKey", apiKeyWithUsers);
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
    public void sendingGetRewardOfUserToExistingApiKeyReturnOk() throws IOException {
        /**
         * Requête GET à l'api pour récupérer liste de rewards reward d'un
         * utilisateur d'une application
         */
        WebTarget target = client.target(baseUrl).path("users").path(idUserWithRewards).path("rewards").queryParam("apiKey", apiKeyWithUsers);
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
        for (JsonNode badges : rootNodeasArray) {
            assertThat(badges.get("id")).isNotNull();
            assertThat(badges.get("name")).isNotNull();
            assertThat(badges.get("category")).isNotNull();
            assertThat(badges.get("description")).isNotNull();
            assertThat(badges.get("image")).isNotNull();
        }
    }

    @Test
    @ProbeTest(tags = "REST")
    public void itShouldBePossibleToAddABadgeToAnUser() throws IOException {
        String messageInfo = "New badge successfully added";
        /**
         * Création du badge au format JSON
         */
        String name = "Badge Test";
        String category = "Badge Test";
        String description = "Badge Test";
        String image = "http://www.pokepedia.fr/images/5/50/Badge_Cascade_Kanto.png";
        JsonNode badgeInfo = factory.objectNode().put("name", name)
                .put("category", category)
                .put("description", description)
                .put("image", image);

        /**
         * Requête POST à l'api pour ajouter un badge à un utilisateur créé
         * auparavant à l'application
         */
        WebTarget target = client.target(baseUrl).path("users").path(idUserWithRewards).path("badges").queryParam("apiKey", apiKeyWithUsers);
        Response response = target.request().post(Entity.entity(badgeInfo, "application/json"));

        /**
         * L'api doit renvoyer un code 201
         */
        assertThat(response.getStatus()).isEqualTo(Response.Status.CREATED.getStatusCode());

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
    public void itShouldBePossibleToAddARewardToAnUser() throws IOException {
        String messageInfo = "New reward successfully added";
        /**
         * Création du reward au format JSON
         */
        String name = "Reward Test";
        String category = "Reward Test";
        String description = "Reward Test";
        String image = "http://www.pokepedia.fr/images/5/50/Badge_Cascade_Kanto.png";
        JsonNode badgeInfo = factory.objectNode().put("name", name)
                .put("category", category)
                .put("description", description)
                .put("image", image);

        /**
         * Requête POST à l'api pour ajouter un reward à un utilisateur créé
         * auparavant à l'application
         */
        WebTarget target = client.target(baseUrl).path("users").path(idUserWithBadges).path("rewards").queryParam("apiKey", apiKeyWithUsers);
        Response response = target.request().post(Entity.entity(badgeInfo, "application/json"));

        /**
         * L'api doit renvoyer un code 201
         */
        assertThat(response.getStatus()).isEqualTo(Response.Status.CREATED.getStatusCode());

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

    /**
     * Tester l'api pour les fonctionnalités "Rewards"
     */
    @Test
    @ProbeTest(tags = "REST")
    public void sendingGetRewardsToExistingApiKeyReturnOk() throws IOException {
        /**
         * Requête GET à l'api pour récupérer la liste de rewards d'une
         * application
         */
        WebTarget target = client.target(baseUrl).path("rewards").queryParam("apiKey", apiKeyWithUsers);
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
            assertThat(users.get("name")).isNotNull();
            assertThat(users.get("category")).isNotNull();
            assertThat(users.get("description")).isNotNull();
            assertThat(users.get("image")).isNotNull();
        }
    }

    @Test
    @ProbeTest(tags = "REST")
    public void itShouldBePossibleToCreateAReward() throws IOException {
        /**
         * Création du reward au format JSON
         */
        String name = "Reward Test";
        String category = "Reward Test";
        String description = "Reward Test";
        String image = "http://www.pokepedia.fr/images/5/50/Badge_Cascade_Kanto.png";
        JsonNode badgeInfo = factory.objectNode().put("name", name)
                .put("category", category)
                .put("description", description)
                .put("image", image);

        /**
         * Requête POST à l'api pour créer un reward dans l'application
         */
        WebTarget target = client.target(baseUrl).path("rewards").queryParam("apiKey", apiKeyWithUsers);
        Response response = target.request().post(Entity.entity(badgeInfo, "application/json"));

        /**
         * L'api doit renvoyer un code 201
         */
        assertThat(response.getStatus()).isEqualTo(Response.Status.CREATED.getStatusCode());

        /**
         * Récupérer la réponse et contrôler qu'elle n'est pas vide
         */
        String jsonPayload = response.readEntity(String.class);
        assertThat(jsonPayload).isNotNull();
        assertThat(jsonPayload).isNotEmpty();

        /**
         * L'api doit renvoyer les informations suivantes
         */
        JsonNode root = mapper.readTree(jsonPayload);
        assertThat(root.get("name").asText().compareToIgnoreCase(name));
        assertThat(root.get("category").asText().compareToIgnoreCase(category));
        assertThat(root.get("description").asText().compareToIgnoreCase(description));
        assertThat(root.get("image").asText().compareToIgnoreCase(image));
    }

    /**
     * Tester l'api pour les fonctionnalités "Badges"
     */
    @Test
    @ProbeTest(tags = "REST")
    public void sendingGetBadgesToExistingApiKeyReturnOk() throws IOException {
        /**
         * Requête GET à l'api pour récupérer la liste de badges d'une
         * application
         */
        WebTarget target = client.target(baseUrl).path("badges").queryParam("apiKey", apiKeyWithUsers);
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
            assertThat(users.get("name")).isNotNull();
            assertThat(users.get("category")).isNotNull();
            assertThat(users.get("description")).isNotNull();
            assertThat(users.get("image")).isNotNull();
        }
    }

    @Test
    @ProbeTest(tags = "REST")
    public void itShouldBePossibleToCreateABadge() throws IOException {
        /**
         * Création du reward au format JSON
         */
        String name = "Badge Test";
        String category = "Badge Test";
        String description = "Badge Test";
        String image = "http://www.pokepedia.fr/images/5/50/Badge_Cascade_Kanto.png";
        JsonNode badgeInfo = factory.objectNode().put("name", name)
                .put("category", category)
                .put("description", description)
                .put("image", image);

        /**
         * Requête POST à l'api pour créer un badge dans l'application
         */
        WebTarget target = client.target(baseUrl).path("badges").queryParam("apiKey", apiKeyWithUsers);
        Response response = target.request().post(Entity.entity(badgeInfo, "application/json"));

        /**
         * L'api doit renvoyer un code 201
         */
        assertThat(response.getStatus()).isEqualTo(Response.Status.CREATED.getStatusCode());

        /**
         * Récupérer la réponse et contrôler qu'elle n'est pas vide
         */
        String jsonPayload = response.readEntity(String.class);
        assertThat(jsonPayload).isNotNull();
        assertThat(jsonPayload).isNotEmpty();

        /**
         * L'api doit renvoyer les informations suivantes
         */
        JsonNode root = mapper.readTree(jsonPayload);
        assertThat(root.get("name").asText().compareToIgnoreCase(name));
        assertThat(root.get("category").asText().compareToIgnoreCase(category));
        assertThat(root.get("description").asText().compareToIgnoreCase(description));
        assertThat(root.get("image").asText().compareToIgnoreCase(image));
    }
}
