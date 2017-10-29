package org.luckyether.server.service.impl;

import org.luckyether.server.model.Newbie;
import org.luckyether.server.repository.NewbieRepository;
import org.luckyether.server.service.NewbieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Igor Hnes on 10/29/17.
 */
@Service
public class NewbieServiceImpl implements NewbieService {

    @Autowired
    private NewbieRepository newbieRepository;

    /**
     * {@inheritDoc}.
     */
    @Override
    public void save(Newbie newbie) {
        newbieRepository.save(newbie);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public List<Newbie> getBetweenId(Long from, Long to) {
        return newbieRepository.getBetweenId(from, to);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public Long countById() {
        return newbieRepository.countById();
    }
}
