package br.gov.rn.pm.sigp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.rn.pm.sigp.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

     User findByEmail(String email);

}
