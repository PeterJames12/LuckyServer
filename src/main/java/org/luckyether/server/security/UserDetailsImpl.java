package org.luckyether.server.security;

import lombok.Getter;
import org.luckyether.server.model.Role;
import org.luckyether.server.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Andre on July 2017.
 */
@Getter
public class UserDetailsImpl implements UserDetails {

    private static final long SERIAL_VERSION_UID = -3413337096852642718L;

    private Collection<? extends GrantedAuthority> authorities;
    private String password;
    private String username;
    private boolean enabled;

    public UserDetailsImpl(User user) {
        this.password = user.getPassword();
        this.username = user.getEmail();
        this.authorities = translate(user.getRoles());
        this.enabled = user.isEnabled();
    }

    /**
     * @return Collection of roles.
     */
    private Collection<? extends GrantedAuthority> translate(List<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            String name = role.getName().toUpperCase();
            if (!name.startsWith("ROLE_")) {
                name = "ROLE_" + name;
            }
            authorities.add(new SimpleGrantedAuthority(name));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
