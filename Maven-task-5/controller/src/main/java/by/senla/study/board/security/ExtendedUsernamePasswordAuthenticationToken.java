package by.senla.study.board.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

@SuppressWarnings("serial")
public class ExtendedUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private Integer id;

    public ExtendedUsernamePasswordAuthenticationToken(final Integer id, final Object principal,
            final Object credentials, final Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

}
