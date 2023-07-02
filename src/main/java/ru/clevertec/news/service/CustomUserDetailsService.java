package ru.clevertec.news.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * The CustomUserDetailsService interface represents the service for user-related operations.
 * It provides methods to interact with the underlying data layer and perform CRUD operations on users.
 */
public interface CustomUserDetailsService extends UserDetailsService {

    /**
     * Retrieves a user with the specified username.
     *
     * @param username The username of the user to retrieve.
     * @return The User object with the specified username.
     */
    public UserDetails loadUserByUsername(String username);
}
