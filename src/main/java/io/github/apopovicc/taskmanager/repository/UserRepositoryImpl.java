package io.github.apopovicc.taskmanager.repository;

import io.github.apopovicc.taskmanager.model.User;
import io.github.apopovicc.taskmanager.storage.JsonStorage;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class  UserRepositoryImpl implements UserRepository {

    private final JsonStorage jsonStorage;
    private final List<User> users;

    public UserRepositoryImpl(JsonStorage jsonStorage) {
        this.jsonStorage = jsonStorage;
        this.users = jsonStorage.loadUsers();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return users.stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public Optional<User> findById(UUID id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    @Override
    public void saveUser(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(user.getId())) {
                users.set(i, user);
                break;
            }
        }
        jsonStorage.saveUsers(users);
    }
}
