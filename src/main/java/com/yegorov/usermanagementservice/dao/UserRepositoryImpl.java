package com.yegorov.usermanagementservice.dao;

import com.yegorov.usermanagementservice.dto.Role;
import com.yegorov.usermanagementservice.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private JdbcTemplate template;

    @Override
    public Map<Integer, User> getAllUsers() {
        HashMap<Integer, User> users = new HashMap<>();

        List<User> temp = template.query(
                    """
                        select *
                        from ums.users u
                                 left join ums.users_has_roles uhr on u.id = uhr.users_id
                                 left join ums.roles r on uhr.roles_id = r.id
                                 left join ums.last_visit lv on u.last_visit_id = lv.id;
                        """,
                (rs, rowNum) -> new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email")
                )
                );

        temp.forEach(user -> users.put(user.getId(), user));

        return users;
    }

    @Override
    public List<Role> getRolesByUserId(int userId) {
        String sql = """
            select *
            from ums.roles r
                     join ums.users_has_roles uhr on r.id = uhr.roles_id
            where uhr.users_id = ?
            """;

        return template.query(
                sql,
                (rs, rowNum) -> new Role(
                        rs.getInt("id"),
                        rs.getString("role"),
                        rs.getString("description")
                ),
                userId
        );
    }

    @Override
    public User getUserById(int userId) {
        String sql = """
            select *
            from ums.users u
            where u.id = ?
            """;

        return template.queryForObject(
                sql,
                (rs, rowNum) -> new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email")
                ),
                userId
        );
    }
}
