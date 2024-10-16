package com.securecoding.demo.web.login.api;

import com.securecoding.demo.web.login.api.model.WebLoginRequestModel;
import com.securecoding.demo.web.login.service.UserService;
import com.securecoding.demo.web.login.validator.InputValidator;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor(onConstructor_ = @__(@Autowired))
public class WebLoginController {

    private static final Logger LOG = LoggerFactory.getLogger (WebLoginController.class);

    private final UserService userService;

    private final InputValidator inputValidator;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute ("loginRequestModel", WebLoginRequestModel.builder ().build ());
        return "login";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/user_info")
    public String userInfo(@RequestParam String userId, Model model) {
        inputValidator.isValidUserId (userId);
        var user = userService.getUserInfo (userId);
        if (user.isPresent ()) {
            LOG.info ("Returning user information for user {}", user);
            model.addAttribute ("user", user.get ());
        } else {
            LOG.error ("Couldn't find user with id: {}", userId);
        }
        return "user_info";
    }

}
