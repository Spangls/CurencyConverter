package ru.mpt.convertor.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mpt.convertor.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    User findByPasswordResetCode(String code);
}
