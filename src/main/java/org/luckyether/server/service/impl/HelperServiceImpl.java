package org.luckyether.server.service.impl;

import org.luckyether.server.model.TransactionHelper;
import org.luckyether.server.repository.HelperRepository;
import org.luckyether.server.service.HelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Igor Hnes on 9/5/17.
 */
@Service
public class HelperServiceImpl implements HelperService {

    @Autowired
    private HelperRepository helperRepository;
    private static final Long HELPER_ID = 1L;

    /**
     * {@inheritDoc}.
     */
    @Override
    public TransactionHelper getHelper() {
        return helperRepository.findOne(HELPER_ID);
    }
}
