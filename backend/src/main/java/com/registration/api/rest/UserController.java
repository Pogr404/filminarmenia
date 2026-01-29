package com.registration.api.rest;

import com.registration.ex.ErrorDetails;
import com.registration.constants.RoutConstants;
import com.registration.request.UserRequest;
import com.registration.security.RequiredAdminUser;
import com.registration.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(RoutConstants.BASE_URL + "${user.service.version}" + RoutConstants.USERS)
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Create user with specified parameters.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserRequest.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid request to send endpoint", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))
            }),
            @ApiResponse(responseCode = "409", description = "User already exists", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))
            }),
            @ApiResponse(responseCode = "500", description = "Error occurred while updating user", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))
            }),
    })

    @PostMapping
    public UserRequest createUser(@RequestBody @Valid UserRequest userRequest) {
        log.info("received request to create user");
        UserRequest user = userService.createUser(userRequest);
        log.info("User created successfully");
        return user;
    }

    @GetMapping("/{id}")
    @RequiredAdminUser
    public UserRequest getUserById(@PathVariable UUID id) {

        return userService.getUserById(id);
    }

    @GetMapping
    public List<UserRequest> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/{id}")
    @RequiredAdminUser
    public UserRequest updateUser(@PathVariable UUID id, @RequestBody UserRequest userRequest) {
        return userService.updateUser(id, userRequest);
    }

    @DeleteMapping("/{id}")
    @RequiredAdminUser
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }
}