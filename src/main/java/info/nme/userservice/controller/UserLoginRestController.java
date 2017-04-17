package info.nme.userservice.controller;

import info.nme.userservice.api._internal.RestApiConstants;
import info.nme.userservice.api.model.UserApiData;
import info.nme.userservice.model.User;
import info.nme.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @author Roland Krüger
 */
@RestController
@RequestMapping("/" + RestApiConstants.LOGIN_USER_RESOURCE)
public class UserLoginRestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "{userId}", method = RequestMethod.POST)
    public ResponseEntity loginUser(@PathVariable Long userId) {
        User user = userService.findById(userId);

        if (user == null ||
                !user.isEnabled() ||
                !user.isAccountNonExpired() ||
                !user.isAccountNonLocked() ||
                !user.isCredentialsNonExpired()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        user.setLastLogin(LocalDateTime.now());
        userService.save(user);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity loginUser(@RequestBody UserApiData userApiData) {
        //User user = userService.findById(userId);
        User user = userService.findUserByUsername(userApiData.getUsername());

        if (user == null ||
                !user.isEnabled() ||
                !user.isAccountNonExpired() ||
                !user.isAccountNonLocked() ||
                !user.isCredentialsNonExpired()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        user.setLastLogin(LocalDateTime.now());
        userService.save(user);

        return new ResponseEntity(HttpStatus.OK);
    }
}
