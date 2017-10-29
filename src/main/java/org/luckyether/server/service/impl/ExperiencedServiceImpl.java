package org.luckyether.server.service.impl;

import org.luckyether.server.model.Experienced;
import org.luckyether.server.repository.ExperiencedRepository;
import org.luckyether.server.service.ExperiencedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Igor Hnes on 10/29/17.
 */
@Service
public class ExperiencedServiceImpl implements ExperiencedService {

    @Autowired
    private ExperiencedRepository experiencedRepository;

    /**
     * {@inheritDoc}.
     */
    @Override
    public void save(Experienced experienced) {
        experiencedRepository.save(experienced);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public List<Experienced> getBetweenId(Long from, Long to) {
        return experiencedRepository.getBetweenId(from, to);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public Long countById() {
        return experiencedRepository.countById();
    }
}
