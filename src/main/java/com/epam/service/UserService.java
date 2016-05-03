package com.epam.service;

import com.epam.exception.DatabaseException;
import com.epam.exception.EntityNotFoundException;
import com.epam.exception.InternalServerException;
import com.epam.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * Created by Sahak_Babayan on 3/31/2016.
 */
public interface UserService extends UserDetailsService{

    void add(User user) throws InternalServerException;

    User getByID(long id) throws InternalServerException;

    boolean isEmailExist(String email) throws InternalServerException;

    boolean isEmailExist(String email, Long excludedUserID) throws InternalServerException;

    User getByEmail(String email) throws InternalServerException;

    List<User> getAll() throws InternalServerException;

    List<User> getAll(List<Long> excludedUserIDs) throws InternalServerException;

    void edit(User user) throws InternalServerException;

    void removeByID(long id) throws InternalServerException;

}
