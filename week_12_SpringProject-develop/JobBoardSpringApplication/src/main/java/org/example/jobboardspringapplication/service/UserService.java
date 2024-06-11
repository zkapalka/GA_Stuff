package org.example.jobboardspringapplication.service;

import org.example.jobboardspringapplication.exceptions.UserException;
import org.example.jobboardspringapplication.model.Address;
import org.example.jobboardspringapplication.model.Comment;
import org.example.jobboardspringapplication.model.Post;
import org.example.jobboardspringapplication.model.User;
import org.example.jobboardspringapplication.repo.IAddressRepo;
import org.example.jobboardspringapplication.repo.IPostRepo;
import org.example.jobboardspringapplication.repo.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final IUserRepo userRepo;
    private final IAddressRepo addressRepo;

    @Autowired
    public UserService(IUserRepo userRepo, IPostRepo postRepo, IAddressRepo addressRepo){
        this.userRepo = userRepo;
        this.addressRepo = addressRepo;
    }


    //CREATE
    public User createUser(User user) {
        try {
            String email = user.getEmail();
            if (!email.endsWith(".com")) {
                throw new UserException("Email address must end with '.com'");
            }
            if (userRepo.existsByEmail(email)) {
                throw new UserException("User with email " + email + " already exists");
            }
            return userRepo.save(user);
        } catch (DataIntegrityViolationException e) {
            System.err.println("Error creating user: " + e.getMessage());
            throw new UserException("Failed to create user: " + e.getMessage());
        }
    }

    public User addAddressToUser(Integer id, Address address) throws Exception {
        User user = userRepo.findById(id).orElseThrow(() -> new Exception("User with id " + id + " not found"));
        address.setUser(user);
        user.setAddress(address);
        addressRepo.save(address);
        userRepo.save(user);

        return user;
    }

    //READ
    public List<User> getAllUsers() {
        try {
            return userRepo.findAll();
        } catch (DataAccessException e) {
            System.err.println("An error occurred while retrieving users: " + e.getMessage());
            throw new DataAccessResourceFailureException("Failed to retrieve users", e);
        }
    }


    public Optional<User> getUserById(Integer id) {
        try {
            return userRepo.findById(id);
        } catch (DataAccessException e) {
            System.err.println("An error occurred while retrieving the user with ID " + id + ": " + e.getMessage());
            throw new UserException("Failed to get user: " + e.getMessage());
        }
    }

    public List<Post> getUserPosts(Integer userId) {
        try {
            Optional<User> userOptional = userRepo.findById(userId);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                return user.getPosts();
            } else {
                throw new UserException("User with ID " + userId + " not found");
            }
        } catch (Exception e) {
            throw new UserException("Error retrieving posts for user with ID " + userId);
        }
    }

    public List<Comment> getAllCommentsForUser(Integer userId) {
        try {
            Optional<User> userOptional = userRepo.findById(userId);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                return user.getComments();
            } else {
                throw new UserException("User with ID " + userId + " not found");
            }
        } catch (Exception e) {
            throw new UserException("Error retrieving user");
        }
    }

    //UPDATE
    public User saveUser(User user) {
        try {
            return userRepo.save(user);
        } catch (DataAccessException e) {
            System.err.println("An error occurred while updating the user" + e.getMessage());
            throw new UserException("Failed to update user: " + e.getMessage());
        }
    }

    public User updateAddressOfUser(Integer userId, Address address) throws Exception {
        try {
            Optional<User> userOptional = userRepo.findById(userId);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                Address existingAddress = user.getAddress();
                if (existingAddress != null) {
                    existingAddress.setStreet(address.getStreet());
                    existingAddress.setCity(address.getCity());
                    existingAddress.setState(address.getState());
                    existingAddress.setZipCode(address.getZipCode());
                    existingAddress.setCountry(address.getCountry());
                    return userRepo.save(user);
                } else {
                    throw new Exception("User does not have an existing address");
                }
            } else {
                throw new Exception("User not found");
            }
        } catch (Exception e) {
            throw new Exception("Error updating user address: " + e.getMessage());
        }
    }

    //DELETE
    public void deleteUserById(Integer id) {
        try {
            userRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new UserException("User with ID " + id + " not found");
        } catch (DataAccessException e) {
            System.err.println("An error occurred while trying to delete the user" + e.getMessage());
            throw new UserException("Failed to delete user:  " + e.getMessage());
        }
    }
}
