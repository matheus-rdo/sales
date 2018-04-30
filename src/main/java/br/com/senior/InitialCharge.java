package br.com.senior;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.senior.model.domain.user.User;
import br.com.senior.model.repository.user.UserRepository;

@Component
public class InitialCharge {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void postInit() {
        if (userRepository.count() == 0) {
            createDefaultUsers();
        }
    }

    private void createDefaultUsers() {
        User admin = new User("admin", "admin", true);
        User seller = new User("vendas", "vendas");
        userRepository.saveAll(Arrays.asList(admin, seller));
    }

}
