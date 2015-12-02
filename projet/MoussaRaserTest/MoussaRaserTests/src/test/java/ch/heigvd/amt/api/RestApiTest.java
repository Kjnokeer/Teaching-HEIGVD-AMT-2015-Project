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

/**
 * This class contains methods to test the REST API. We use Jersey Client to
 * facilitate the preparation of HTTP requests and the inspection of HTTP
 * responses (you will notice that Jersey Client provides a "fluent" API style).
 *
 * Note that on the server side, we use DTO classes and we let Jersey handle the
 * serialization and deserialization of payloads. We could have done the same
 * thing on the client side, but instead we have used the Jackson JSON library
 * and we deal with JSON trees ourselves. One reason for this choice is simply
 * that we wanted to provide an example and show what can be done.
 *
 * But another reason is to reduce the risk of introducing changes in the API
 * payloads. Indeed, if we wanted to write as little code as possible, we would
 * extract the DTO classes in a separate .jar file, which we would include both
 * in the server project and in the test project (via a maven dependency). This
 * way, when adding a property to a DTO, it would automatically be available on
 * the server and on the testing side. While this seems highly beneficial, it
 * means that the tests might sometimes fail to detect changes in the API. An
 * intermediate solution would be to have two separate DTO packages (one on the
 * server side and on the test side). In that case, if the DTO is changed on the
 * server side, then the DTO has to explicitely be changed on the test side (and
 * this is when the developer realizes that the API has changed, if he was not
 * aware yet).
 *
 * @author Olivier Liechti
 */
public class RestApiTest {

    private final String baseUrl = "http://localhost:8080/MoussaRaser/";

    Client client;

    ObjectMapper mapper;

    JsonNodeFactory factory;

    @Before
    public void setup() {
        client = ClientBuilder.newClient();
        mapper = new ObjectMapper();
        factory = new JsonNodeFactory(false);
    }
}
