package br.gov.rn.pm.sigp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.rn.pm.sigp.model.Posto;
import br.gov.rn.pm.sigp.repository.PostoRepository;

@Service
public class PostoService {

    @Autowired
    private PostoRepository repository;
    
    public List<Posto> findAll() {
        return repository.findAll();
    }
    
    public List<Posto> findByUserId(Long user) {
        return repository.findByUserId(user);
    }

    public Posto findOne(Long id) {
        return repository.findOne(id);
    }
    
    
    public Posto save(Posto posto) {
        return repository.saveAndFlush(posto);
    }

    public void delete(Long id) {
        repository.delete(id);
    }
}
