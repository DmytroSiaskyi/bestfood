package com.bestfood.controller;

import com.bestfood.components.LoggedInUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController extends BaseController {
    @Value("#{properties['homepage.user']}")
    private String userHomepage;

    @Value("#{properties['homepage.admin']}")
    private String adminHomepage;

    /**
     * Login form
     *
     * @param request  {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @param model    {@link Model}
     * @param user     {@link LoggedInUser}
     * @param login_error  Integer error param
     * @return         resolved view
     */
    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login(HttpServletRequest request,
                        HttpServletResponse response,
                        Model model,
                        @RequestParam(required = false) Integer login_error,
                        LoggedInUser user) {
        if (user.isAdmin()) {
            return "redirect:" + adminHomepage;
        } else if (user.isUser()) {
            return "redirect:" + userHomepage;
        }

        boolean error = false;
        if (login_error != null) {
            Object exception = request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
            if (exception instanceof AuthenticationServiceException) {
                error = true;
            } else if (exception instanceof BadCredentialsException) {
                error = true;
            }
        }

        String redirect = "/login?form=1";
        DefaultSavedRequest savedRequest = (DefaultSavedRequest) new HttpSessionRequestCache().getRequest(request, response);
        if (savedRequest != null) {
            String uri = savedRequest.getRequestURI();
            if (uri != null)
                redirect = uri;
        }

        model.addAttribute("redirect", redirect);
        model.addAttribute("err", error);

        return "login";
    }

    /**
     * Abort user session
     *
     * @param request {@link HttpServletRequest}
     * @return        redirect on home
     */
    @RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        SecurityContextHolder.clearContext();
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
