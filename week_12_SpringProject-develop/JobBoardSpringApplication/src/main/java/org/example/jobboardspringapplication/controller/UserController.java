package org.example.jobboardspringapplication.controller;

import org.example.jobboardspringapplication.DTO.NotificationsDTO;
import org.example.jobboardspringapplication.exceptions.UserException;
import org.example.jobboardspringapplication.model.Address;
import org.example.jobboardspringapplication.model.Comment;
import org.example.jobboardspringapplication.model.Post;
import org.example.jobboardspringapplication.model.User;
import org.example.jobboardspringapplication.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    //CREATE
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            User createdUser = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (Exception e) {
            logger.error("Error creating user", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PostMapping("/{id}/addresses")
    public ResponseEntity<User> addAddressToUser(@PathVariable Integer id, @RequestBody Address address) {
        try {
            User userAddress = userService.addAddressToUser(id, address);
            return ResponseEntity.status(HttpStatus.CREATED).body(userAddress);
        } catch (Exception e) {
            logger.error("Error adding address to user", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //READ
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            return ResponseEntity.status(HttpStatus.OK).body(users);
        } catch (Exception e) {
            logger.error("Error getting all users", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        try {
            Optional<User> userOptional = userService.getUserById(id);
            return userOptional.map(user -> ResponseEntity.status(HttpStatus.OK).body(user))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (Exception e) {
            logger.error("Error getting user by ID", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}/posts")
    public ResponseEntity<List<Post>> getUserPosts(@PathVariable Integer id) {
        try {
            List<Post> userPosts = userService.getUserPosts(id);
            return ResponseEntity.ok().body(userPosts);
        } catch (Exception e) {
            logger.error("Error getting posts for user", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}/addresses")
    public ResponseEntity<Address> getAddressOfUser(@PathVariable Integer id) {
        try {
            Optional<User> userOptional = userService.getUserById(id);
            if (userOptional.isPresent()) {
                Address address = userOptional.get().getAddress();
                if (address != null) {
                    return ResponseEntity.ok().body(address);
                } else {
                    return ResponseEntity.notFound().build();
                }
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Error getting address of user", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<Comment>> getAllCommentsForUser(@PathVariable Integer id) {
        try {
            List<Comment> userComments = userService.getAllCommentsForUser(id);
            return ResponseEntity.ok().body(userComments);
        } catch (UserException e) {
            logger.error("Error getting comments for user", e);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Unexpected error getting comments for user", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/{id}/notifications")
    public ResponseEntity<List<NotificationsDTO>> getUserNotifications(@PathVariable Integer id) {
        String notificationURL = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/notifications/user/" + id)
                .queryParam("userID", id)
                .toUriString();
        try {
            ResponseEntity<NotificationsDTO[]> responseEntity = restTemplate.getForEntity(notificationURL, NotificationsDTO[].class);
            List<NotificationsDTO> notifications = Arrays.asList(responseEntity.getBody());
            return ResponseEntity.ok().body(notifications);
        } catch (RestClientException e) {
            logger.error("Error getting notifications for user", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }



    //UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user) {
        try {
            if (userService.getUserById(id).isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            user.setUserID(id);
            User updatedUser = userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
        } catch (Exception e) {
            logger.error("Error updating user", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}/addresses")
    public ResponseEntity<User> updateAddressOfUser(@PathVariable Integer id, @RequestBody Address address) {
        try {
            User user = userService.updateAddressOfUser(id, address);
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            logger.error("Error updating address of user", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Integer id) {
        try {
            if (userService.getUserById(id).isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            userService.deleteUserById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            logger.error("Error deleting user by ID", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

