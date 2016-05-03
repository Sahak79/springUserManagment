package com.epam.dao;

import com.epam.exception.DatabaseException;
import com.epam.exception.EntityNotFoundException;
import com.epam.model.User;

import java.util.List;

/**
 * Created by Sahak_Babayan on 3/30/2016.
 */
public interface UserDao {

    void add(User user) throws DatabaseException;

    User getByID(long id) throws EntityNotFoundException, DatabaseException;

    boolean isEmailExist(String email) throws DatabaseException;

    boolean isEmailExist(String email, Long excludedUserID) throws DatabaseException;

    User getByEmail(String email) throws DatabaseException;

    List<User> getAll() throws DatabaseException;

    List<User> getAll(List<Long> excludedUserIDs) throws DatabaseException;

    void edit(User user) throws EntityNotFoundException, DatabaseException;

    void removeByID(long id) throws EntityNotFoundException, DatabaseException;

}
