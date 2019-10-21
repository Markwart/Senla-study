package by.senla.study.board.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthHelper {

    private AuthHelper() {
    }

    public static Integer getLoggedUserId() {
    	
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (!(authentication instanceof ExtendedUsernamePasswordAuthenticationToken)) {
            return null;
        }

        ExtendedUsernamePasswordAuthenticationToken extendedAuthentication = (ExtendedUsernamePasswordAuthenticationToken) authentication;
        return extendedAuthentication.getId();
    }
}
