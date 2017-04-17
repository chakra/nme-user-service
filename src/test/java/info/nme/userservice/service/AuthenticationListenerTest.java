package info.nme.userservice.service;

import info.nme.userservice.api.model.UserApiData;
import info.nme.userservice.api.service.AuthenticationListener;
import info.nme.userservice.testsupport.AbstractRestControllerTest;
import info.nme.userservice.UserMicroserviceApplication;
import info.nme.userservice.api.service.StaticEndpointProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;

/**
 * @author Roland Krüger
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UserMicroserviceApplication.class)
@WebIntegrationTest(randomPort = true)
public class AuthenticationListenerTest extends AbstractRestControllerTest {

    private AuthenticationListener listener;

    @Value("${local.server.port}")
    private int port;

    @Before
    public void setUp() {
        setPort(port);
        deleteAllUsers();
        deleteAllAuthorities();
        listener = new AuthenticationListener(new StaticEndpointProvider("http://localhost:" + port));
        registerUser("alice", "passw0rd", "alice@example.com");
    }

    @Test
    public void testAuthenticationSuccess() {
        UserApiData user = new UserApiData();
        user.setUsername("alice");

        listener.onApplicationEvent(new InteractiveAuthenticationSuccessEvent(
                new UsernamePasswordAuthenticationToken(user, ""), getClass()));

        UserApiData alice = service().users().search().findByUsername("alice").getData().stream().findFirst().get();
        assertThat(alice.getLastLogin(), is(notNullValue()));
    }

    @Test
    public void testAuthenticationSuccessForInactiveUser() {
        UserApiData alice = service().users().search().findByUsername("alice").getData().stream().findFirst().get();
        alice.setEnabled(false);
        alice.getResource().getUpdateResource().update();

        listener.onApplicationEvent(new InteractiveAuthenticationSuccessEvent(
                new UsernamePasswordAuthenticationToken(alice, ""), getClass()));

        alice = service().users().search().findByUsername("alice").getData().stream().findFirst().get();
        assertThat(alice.getLastLogin(), is(nullValue()));
    }

}
