package org.luckyether.server.service;

import org.luckyether.server.model.TransactionHelper;

/**
 * @author Igor Hnes on 9/5/17.
 */
public interface HelperService {

    /**
     * @return instance of {@link TransactionHelper}.
     */
    TransactionHelper getHelper();
}
