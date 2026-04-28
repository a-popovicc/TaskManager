package io.github.apopovicc.taskmanager.security.user;

import io.github.apopovicc.taskmanager.model.User;
import io.github.apopovicc.taskmanager.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        UUID userId = UUID.fromString(username);

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found: " + userId));

        return new UserPrincipal(user.getId(), user.getEmail());
    }

    public UserDetails loadUserById(UUID id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found: " + id));

        return new UserPrincipal(user.getId(), user.getEmail());
    }
}
