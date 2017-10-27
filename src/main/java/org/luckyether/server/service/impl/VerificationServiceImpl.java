package org.luckyether.server.service.impl;

import lombok.val;
import org.apache.commons.lang3.RandomUtils;
import org.luckyether.server.model.Verification;
import org.luckyether.server.repository.VerificationRepository;
import org.luckyether.server.service.EmailBuilder;
import org.luckyether.server.service.VerificationService;
import org.luckyether.server.service.impl.email.VerificationBuilderImpl;
import org.luckyether.server.util.CodeStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Igor Hnes on 8/12/17.
 */
@Service
@Transactional
@PropertySource("classpath:security/security.config.properties")
public class VerificationServiceImpl implements VerificationService {

    @Autowired
    private VerificationRepository verificationRepository;

    private EmailBuilder<Verification> emailBuilder = new VerificationBuilderImpl();

    @Value("${min.length}")
    private int minValue;
    @Value("${max.length}")
    private int maxValue;

    /**q
     * {@inheritDoc}.
     */
    @Override
    public void verificateUserByCode(String email) {
        int code = RandomUtils.nextInt(minValue, maxValue);
        Verification verification = new Verification();
        verification.setCode(code);
        verification.setEmail(email);
        val message = emailBuilder.buildMessage(verification);
//        emailService.sendMessage(message);
        verificationRepository.save(verification);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public String verificateUserAfter(Verification verification) {
        final Verification verificate = verificationRepository.findByEmail(verification.getEmail());
        if (verification.getCode() == verificate.getCode()) {
            verificationRepository.delete(verificate);
            return CodeStatus.SUCCESS.name();
        }
        return CodeStatus.FAIL.name();
    }
}
