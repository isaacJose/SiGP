package br.gov.rn.pm.sigp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.gov.rn.pm.sigp.model.Posto;

@Repository("postoRepository")
public interface PostoRepository extends JpaRepository<Posto, Long> {
    
    @Query(value = "SELECT * FROM Posto p", nativeQuery=true)
    public List<Posto> findByUserId(@Param("userId") Long userId);

}
