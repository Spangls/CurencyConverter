package ru.mpt.convertor.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mpt.convertor.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);
}
