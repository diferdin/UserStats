package functional.hotels.apps;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotels.apps.UserStatsApplication;
import com.hotels.apps.config.UserStatsConfiguration;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UserStatsFunctionalTest {
    private final String USERID = "6";
    private final String BOOKINGS_ENDPOINT = "bookings";

    private Client client;

    @Before
    public void setup() {
        client = new JerseyClientBuilder().build();
    }

    @Rule
    public final DropwizardAppRule<UserStatsConfiguration> RULE =
            new DropwizardAppRule<>(UserStatsApplication.class, getConfigFilePath());

    @Test
    public void shouldReturnTheNumberOfBookingsForGivenUser() throws IOException {

        Response response = client.target(String.format("http://localhost:%d/user/%s/%s", RULE.getLocalPort(), USERID, BOOKINGS_ENDPOINT))
                .request()
                .get();

        assertThat(response.getStatus(), is(200));

        JsonNode jsonNode = toJsonNode(response);
        assertThat(jsonNode.toString(), containsString("bookings"));

        assertLinksInJson(jsonNode, BOOKINGS_ENDPOINT);

        assertThat(jsonNode.get("bookings").asInt(), is(14));
    }

    @Test
    public void shouldReturnTotalBookingValueForGivenUser() throws IOException {

        String VALUE_ENDPOINT = "value";

        Response response = client.target(String.format("http://localhost:%d/user/%s/%s/%s", RULE.getLocalPort(), USERID, BOOKINGS_ENDPOINT, VALUE_ENDPOINT))
                .request()
                .get();

        assertThat(response.getStatus(), is(200));

        JsonNode jsonNode = toJsonNode(response);
        assertThat(jsonNode.toString(), containsString("value"));

        assertLinksInJson(jsonNode, String.format("%s/%s", BOOKINGS_ENDPOINT, VALUE_ENDPOINT));

        assertThat(jsonNode.get("value").asDouble(), is(6872.0));
    }

    @Test
    public void shouldReturnAverageLengthOfStayForGivenUser() throws IOException {

        String AVG_STAY_LENGTH_ENDPOINT = "average-stay";

        Response response = client.target(String.format("http://localhost:%d/user/%s/%s/%s", RULE.getLocalPort(), USERID, BOOKINGS_ENDPOINT, AVG_STAY_LENGTH_ENDPOINT))
                .request()
                .get();

        assertThat(response.getStatus(), is(200));

        JsonNode jsonNode = toJsonNode(response);
        assertThat(jsonNode.toString(), containsString("averageStayLength"));

        assertLinksInJson(jsonNode, String.format("%s/%s", BOOKINGS_ENDPOINT, AVG_STAY_LENGTH_ENDPOINT));

        assertThat(jsonNode.get("averageStayLength").asDouble(), is(6.14));
    }

    private String getConfigFilePath() {
        return new File("config.yml").getAbsolutePath();
    }

    private JsonNode toJsonNode(Response response) throws IOException {
        String bookingJson = response.readEntity(String.class);
        return new ObjectMapper().readTree(bookingJson);
    }



    private void assertLinksInJson(JsonNode jsonNode, String path) {
        assertThat(jsonNode.toString(), containsString("_links"));
        assertThat(jsonNode.get("_links").toString(), containsString(String.format("user/%s/%s", USERID, path)));
    }
}