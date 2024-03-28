package com.develhope.spring.user;

import java.util.HashSet;
import java.util.Set;

import com.develhope.spring.role.Role;
import com.develhope.spring.user.userdto.UserRequest;
import com.develhope.spring.user.userdto.UserResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<Role> role;

    public static UserModel mapEntityToModel(User user) {
        return new UserModel(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole());
    }

    public static User mapModelToEntity(UserModel userModel) {
        return new User(
                userModel.getId(),
                userModel.getFirstName(),
                userModel.getLastName(),
                userModel.getEmail(),
                userModel.getPassword(),
                userModel.getRole());
    }

    public static UserResponse mapModelToResponse(UserModel userModel) {
        return new UserResponse(
                userModel.getId(),
                userModel.getFirstName(),
                userModel.getLastName(),
                userModel.getEmail(),
                userModel.getPassword(),
                convertRoleToString(userModel.getRole()));
    }




    public static UserModel mapRequestToModel(UserRequest userRequest) {
        return new UserModel(
                null,
                userRequest.getFirstName(),
                userRequest.getLastName(),
                userRequest.getEmail(),
                userRequest.getPassword(),
                convertStringToRole(userRequest.getRole())
        );
    }

    private static Set<Role> convertStringToRole(Set<String> role) {
        Set<Role> rolesSet = new HashSet<>();
        Role roleToAdd = new Role();

        for (String roleString : role){
            switch (roleString){
                case "ROLE_ADMIN":
                    roleToAdd.setId(null);
                    roleToAdd.setName(Role.RoleType.ADMIN);
                    break;
                case "ROLE_CUSTOMER":
                    roleToAdd.setId(null);
                    roleToAdd.setName(Role.RoleType.CUSTOMER);
                    break;
                case "ROLE_SELLER":
                    roleToAdd.setId(null);
                    roleToAdd.setName(Role.RoleType.SELLER);
                    break;
                default:
                    return null;
            }
            rolesSet.add(roleToAdd);
        }
        return rolesSet;
    }

    private static Set<String> convertRoleToString(Set<Role> roles) {
        Set<String> rolesString = new HashSet<>();
        for (Role role : roles){
            switch (role.getName().toString()){
                case "ROLE_ADMIN":
                    rolesString.add("ROLE_ADMIN");
                    break;
                case "ROLE_CUSTOMER":
                    rolesString.add("ROLE_CUSTOMER");
                    break;
                case "ROLE_SELLER":
                    rolesString.add("ROLE_SELLER");
                    break;
                default:
                    return null;
            }
        }
        return rolesString;
    }
}


