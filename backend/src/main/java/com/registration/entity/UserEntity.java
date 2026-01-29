package com.registration.entity;

import com.registration.constants.DatabaseConstants;
import com.registration.enums.Role;
import com.registration.enums.Status;
import com.registration.request.UserRequest;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = DatabaseConstants.SCHEMA_NAME, name = DatabaseConstants.USER_TABLE_NAME)
public class UserEntity {

    @Id
    @Column(name = "user_id")
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    private UUID id;
    @Column(name = "first_name")
    private String name;
    @Column(name = "last_name")
    private String surname;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "verification_code")
    private String verifyCode;
    @Column(name = "reset_token")
    private String resetToken;

    public UserEntity(UserRequest request) {
        id = request.getId();
        name = request.getName();
        surname = request.getSurname();
        email = request.getEmail();
        password = request.getPassword();
        role = request.getRole();
        status = request.getStatus();
        verifyCode = request.getVerifyCode();
        resetToken = request.getResetToken();

    }

    public UserRequest toUserRequest() {
        return new UserRequest(id, name, surname, email, password, role, status, verifyCode, resetToken);

    }
}
