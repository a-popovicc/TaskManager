package io.github.apopovicc.taskmanager.service.task;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceImplTest extends TaskServiceTest{

    @Override
    protected TaskService getInstance() {
        return new TaskServiceImpl(userRepository);
    }
}