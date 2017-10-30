package org.luckyether.server.service.impl;

import lombok.val;
import org.luckyether.server.dto.UserDTO;
import org.luckyether.server.exception.RequestException;
import org.luckyether.server.exception.code.ErrorCode;
import org.luckyether.server.model.Role;
import org.luckyether.server.model.User;
import org.luckyether.server.repository.UserRepository;
import org.luckyether.server.security.PasswordEncoder;
import org.luckyether.server.service.EmailBuilder;
import org.luckyether.server.service.UserService;
import org.luckyether.server.service.impl.email.RecoverBuilderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collections;

/**
 * @author Igor Hnes on 8/12/17.
 */
@Service
@PropertySource("classpath:email/email.properties")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    private EmailBuilder<User> emailStrategy = new RecoverBuilderImpl();

    @Value("${password.length}")
    private Integer newPasswordLength;

    /**
     * {@inheritDoc}.
     */
    @Override
    public UserDTO create(UserDTO userDTO) throws RequestException {
        User user = fromDTO(userDTO);
        if (userRepository.findByEmail(userDTO.getEmail()) != null) {
            throw new RequestException(ErrorCode.USER_EXISTS, "EtherUser already exists");
        }
        final User save = userRepository.save(user);
        return toFrom(save);
    }

    /**
     * @return {@link User} from {@link UserDTO}.
     */
    private User fromDTO(UserDTO userDTO) {
        User user = new User();
        if (userDTO.getEnabled() != null) {
            user.setEnabled(userDTO.getEnabled());
        } else {
            user.setEnabled(true);
        }
        user.setId(userDTO.getId());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());

        Role role = new Role();
        role.setName("CLIENT");
        role.setUser(user);
        user.setRoles(Collections.singletonList(role));
        return user;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public UserDTO getUserByUserEmail(String email) {
        return toFrom(userRepository.findByEmail(email));
    }

    /**
     * @return {@link UserDTO} from {@link User}.
     */
    private UserDTO toFrom(User user) {
        return new UserDTO(user.getEmail(), user.isEnabled(), user.getId(), user.getPassword());
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void changePassword(String email) {
        Assert.notNull(email, "email must not be null");
        val userDTO = this.getUserByUserEmail(email);
        User user = fromDTO(userDTO);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = user.getPassword();
        String encodedPassword = encoder.encode(password);
        user.setPassword(encodedPassword);
        val message = this.emailStrategy.buildMessage(user);
//        emailService.sendMessage(message);
        userRepository.saveAndFlush(user);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void update(User user) {
        System.out.println("User id: " + user.getId() + " " + user.toString());
        userRepository.saveAndFlush(user);
    }
}
