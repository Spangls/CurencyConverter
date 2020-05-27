package ru.mpt.convertor.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.mpt.convertor.model.User;
import ru.mpt.convertor.repos.UserRepo;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByEmail(username);
    }

    public User findByEmail(String email) {
        return userRepo.findByEmail(email.trim());
    }

    public User findByPasswordResetCode(String code){
        return userRepo.findByPasswordResetCode(code);
    }

    public List<User> findAll(){
        return userRepo.findAll();
    }

    public Optional<User> findById(Integer id){
        return userRepo.findById(id);
    }

    public User save(User user){
        return userRepo.save(user);
    }
}
