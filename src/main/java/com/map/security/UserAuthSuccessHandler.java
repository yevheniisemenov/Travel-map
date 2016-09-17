package com.map.security;

import com.map.dto.RoleDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.map.common.Constants.Web.HOME_URI;

/**
 * @author Andrew Pasika
 */
@Slf4j
@Service
public class UserAuthSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response
            , Authentication authentication) throws IOException, ServletException {
        handle(request, response, authentication);
        clearAuthAttributes(request);
    }

    private void clearAuthAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }

    private void handle(HttpServletRequest request, HttpServletResponse response
            , Authentication authentication) throws IOException {
        String targetURI = defineTargetURI(authentication);

        if (response.isCommitted()) {
            log.error("Unable to redirect response to {} since it has been already committed.", targetURI);
        }
        request.getSession().setAttribute("username", authentication.getPrincipal());
        response.sendRedirect(targetURI);
    }

    private String defineTargetURI(Authentication authentication) {
        if (authentication.getAuthorities().stream().anyMatch((o) -> o.equals(RoleDto.ADMIN))) {
            return HOME_URI; // TODO: 9/13/16 change to admin page
        } else if (authentication.getAuthorities().stream().anyMatch((o) -> o == RoleDto.USER)) {
            return HOME_URI;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
