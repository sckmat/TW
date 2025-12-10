package com.yegorov.usermanagementservice.dao;

import com.yegorov.usermanagementservice.dto.Role;
import com.yegorov.usermanagementservice.dto.User;

import java.util.List;
import java.util.Map;

public interface UserRepository {

    Map<Integer , User> getAllUsers();
    List<Role> getRolesByUserId(int userId);
    User getUserById(int userId);
}
