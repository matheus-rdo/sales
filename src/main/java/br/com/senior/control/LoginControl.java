package br.com.senior.control;

import br.com.senior.model.domain.user.User;
import br.com.senior.model.repository.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LoginControl {
    
    @Autowired
    private UserRepository userRepository;
    
    public User logIn(String username, String password){
        return userRepository.findUserByUsernameAndPassword(username, password);
    }
    
}
