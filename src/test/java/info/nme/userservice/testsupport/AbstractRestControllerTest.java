package info.nme.userservice.testsupport;

import info.nme.userservice.api.model.UserApiData;
import info.nme.userservice.api.resources.AuthoritiesResource;
import info.nme.userservice.api.resources.UserService;
import info.nme.userservice.api.resources.UsersResource;
import info.nme.userservice.api.UserServiceAPI;
import info.nme.userservice.api.model.AuthorityApiData;
import info.nme.userservice.api.model.UserRegistrationApiData;
import org.junit.After;
import org.springframework.http.ResponseEntity;

/**
 * @author Roland Krüger
 */
public abstract class AbstractRestControllerTest {

    private final static String CONTEXT_PATH = "http://localhost:";

    private static UserService service;
    private static int port = 8080;

    protected static void setPort(int port) {
        AbstractRestControllerTest.port = port;
    }

    protected static UserService service() {
        if (service == null) {
            service = UserServiceAPI.init(CONTEXT_PATH + port);
        }
        return service;
    }

    @After
    public void tearDown(){
        deleteAllUsers();
        deleteAllAuthorities();
    }

    protected static void deleteAllUsers() {
        UsersResource users;
        do {
            users = service().users();
            users.getData().stream().forEach(user -> user.getResource().delete());
        } while (users.hasNext());
    }

    protected static void deleteAllAuthorities() {
        AuthoritiesResource authorities;
        do {
            authorities = service().authorities();
            authorities.getData().stream().forEach(authority -> authority.getResource().delete());
        } while (authorities.hasNext());
    }

    protected static AuthorityApiData createAuthority(String authority) {
        AuthorityApiData authorityApiData = new AuthorityApiData();
        authorityApiData.setAuthority(authority);
        authorityApiData.setDescription("The " + authority + " role");
        return service().authorities().create(authorityApiData).getBody();
    }

    protected static UserApiData registerUser(String username, String password, String email) {
        ResponseEntity<UserRegistrationApiData> registrationResponse =
                service()
                        .userRegistrations()
                        .create(username, password, email);

        service()
                .userRegistrations()
                .findByToken(registrationResponse.getBody().getRegistrationConfirmationToken())
                .confirmRegistration();

        return service()
                .users()
                .search()
                .findByUsername(username).getData().stream().findFirst().get();
    }

}
