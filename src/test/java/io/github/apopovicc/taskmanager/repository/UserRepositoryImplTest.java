package io.github.apopovicc.taskmanager.repository;

import io.github.apopovicc.taskmanager.storage.JsonStorage;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserRepositoryImplTest extends UserRepositoryTest {
    @Mock
    JsonStorage jsonStorage;

    @Override
    protected UserRepository getInstance() {

        when(jsonStorage.loadUsers())
                .thenReturn(new ArrayList<>());

        return new UserRepositoryImpl(jsonStorage);
    }
}