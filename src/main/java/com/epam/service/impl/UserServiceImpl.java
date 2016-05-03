package com.epam.service.impl;

import com.epam.dao.UserDao;
import com.epam.exception.DatabaseException;
import com.epam.exception.EntityNotFoundException;
import com.epam.exception.InternalServerException;
import com.epam.model.User;
import com.epam.model.internal.SessionUser;
import com.epam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Sahak_Babayan on 3/31/2016.
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalServerException.class, propagation = Propagation.REQUIRES_NEW)
    public void add(User user) throws InternalServerException {
        try {
            // encrypts user's password
            String encPassword = new BCryptPasswordEncoder().encode(user.getPassword());
            user.setPassword(encPassword);

            userDao.add(user);
        } catch (DatabaseException e) {
            throw new InternalServerException(e);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws InternalServerException {
        try {
            User user = userDao.getByEmail(email);
            if (user == null) {
                throw new UsernameNotFoundException("err.msg.invalid.credential");
            }
            return new SessionUser(user);
        } catch (DatabaseException e) {
            throw new InternalServerException(e);
        }
    }

    @Override
    public User getByID(long id) throws InternalServerException {
        try {
            return userDao.getByID(id);
        }catch (DatabaseException | EntityNotFoundException e) {
            throw new InternalServerException(e);
        }
    }

    @Override
    public boolean isEmailExist(String email) throws InternalServerException {
        try {
            return userDao.isEmailExist(email);
        } catch (DatabaseException e) {
            throw new InternalServerException(e);
        }
    }

    @Override
    public boolean isEmailExist(String email, Long excludedUserID) throws InternalServerException {
        try {
            return userDao.isEmailExist(email, excludedUserID);
        } catch (DatabaseException e) {
            throw new InternalServerException(e);
        }
    }

    @Override
    public User getByEmail(String email) throws InternalServerException {
        return null;
    }

    @Override
    public List<User> getAll() throws InternalServerException {
        return null;
    }

    @Override
    public List<User> getAll(List<Long> excludedUserIDs) throws InternalServerException {
        return null;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalServerException.class, propagation = Propagation.REQUIRES_NEW)
    public void edit(User user) throws InternalServerException {

    }

    @Override
    @Transactional(readOnly = false, rollbackFor = InternalServerException.class, propagation = Propagation.REQUIRES_NEW)
    public void removeByID(long id) throws InternalServerException {

    }
}
