package com.khoa.play.mappers;

import com.khoa.play.jooq.tables.pojos.User;
import com.khoa.play.jooq.tables.records.UserRecord;
import com.khoa.play.models.LoginDTO;
import com.khoa.play.models.UserDTO;
import com.khoa.play.utils.PasswordUtil;

/**
 * @author Khoa
 * @created 7/28/2019
 */
public class UserMapper {

    public User toPojo(UserRecord record) {
        //Long   id,
        //        String email,
        //        String password,
        //        String status
        return new User(record.getId(), record.getEmail(), record.getPassword(), record.getStatus());
    }

    public User toPojo(LoginDTO login) throws Exception {
        return new User(null, login.getEmail(), PasswordUtil.createPassword(login.getPassword()), "NORMAL");
    }

    public UserDTO toDto(User user) {
        return new UserDTO(user.getId(), user.getEmail(), user.getPassword(), user.getStatus());
    }

    public UserDTO toDto(UserRecord record) {
        return new UserDTO(record.getId(), record.getEmail(), record.getPassword(), record.getStatus());
    }
}
