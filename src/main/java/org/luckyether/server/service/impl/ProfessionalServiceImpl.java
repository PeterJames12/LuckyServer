package org.luckyether.server.service.impl;

import org.luckyether.server.model.Professional;
import org.luckyether.server.repository.ProfessionalRepository;
import org.luckyether.server.service.ProfessionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Igor Hnes on 10/29/17.
 */
@Service
public class ProfessionalServiceImpl implements ProfessionalService {

    @Autowired
    private ProfessionalRepository professionalRepository;

    /**
     * {@inheritDoc}.
     */
    @Override
    public void save(Professional professional) {
        professionalRepository.save(professional);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public List<Professional> getBetweenId(Long from, Long to) {
        return professionalRepository.getBetweenId(from, to);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public Long countById() {
        return professionalRepository.countById();
    }
}
