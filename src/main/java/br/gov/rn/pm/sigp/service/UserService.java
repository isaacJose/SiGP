package br.gov.rn.pm.sigp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.gov.rn.pm.sigp.model.Role;
import br.gov.rn.pm.sigp.model.User;
import br.gov.rn.pm.sigp.repository.RoleRepository;
import br.gov.rn.pm.sigp.repository.UserRepository;

@Service
public class UserService {
    
    private static final int ACTIVE = 1;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * @param user A new user with default role (USER_ROLE).
     */
    public void save(User user) {
        //TODO Implementar a criação das Roles básicas (ADMIN e USER) na configuração do spring
        Role userRole = roleRepository.findByRole(Role.USER_ROLE);

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(ACTIVE);
        
        user.setNewRole(userRole);
        userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findOne(Long id) {
        return userRepository.findOne(id);
    }
    
    public void delete(Long id) {
        userRepository.delete(id);
    }
}
