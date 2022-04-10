package com.enes.user.service;

import com.enes.user.entity.User;
import com.enes.user.repo.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
public class UserService implements IUserService{

    @Value("${delete.user.directory.url}")
    private String delete_user_directory_url;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private WebClient.Builder builder;

    @Override
    public User saveUser(User user){
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findUser(long id){
        return userRepository.findById(id);
    }

    @Override
    public void deleteUser(long id){
        userRepository.deleteById(id);
        builder.build()
                .delete().uri(delete_user_directory_url+id)
                .retrieve()
                .bodyToMono(Boolean.class).block();
    }
}
