package hcmute.leettruyen.component;

import hcmute.leettruyen.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class Extractor {
    public Integer getUserIdFromToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof User user){
            return user.getId();
        }
        return null;
    }
}
