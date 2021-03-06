package info.nme.userservice.service;

import info.nme.userservice.model.User;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @author Roland Krüger
 */
public interface UserService {

    User findUserByUsername(String username);

    User findByRegistrationConfirmationToken(String confirmationToken);

    List<User> getUserList(int page, int size, Sort.Direction sort);

    void delete(Long id);

    User save(User user);

    User findById(Long userId);
}
