package io.github.apopovicc.taskmanager.controller.user;

import io.github.apopovicc.taskmanager.dto.response.UserMeResponse;
import io.github.apopovicc.taskmanager.security.user.UserPrincipal;
import io.github.apopovicc.taskmanager.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserMeController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserMeResponse> getMe(
            @AuthenticationPrincipal UserPrincipal principal) {

        return ResponseEntity.ok(userService.getMe(principal.getId()));
    }
}
