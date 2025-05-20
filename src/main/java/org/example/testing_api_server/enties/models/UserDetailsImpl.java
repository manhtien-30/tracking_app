package org.example.testing_api_server.enties.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.List;
@Data
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String username;
    private String password;
    private boolean isEnabled;
    private Collection<? extends GrantedAuthority> authorities;
    public static UserDetailsImpl build(Account account) {
        List<GrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority(account.getRole().name()));
        return new UserDetailsImpl(
                account.getId(),
                account.getUsername(),
                account.getPassword(),
                account.getIsActive(),
                authorities
        );
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

    @Override
    public boolean isEnabled() {
        return true;
    }
}
