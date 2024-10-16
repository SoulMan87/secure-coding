package com.securecoding.demo.web.login.api;

import com.securecoding.demo.web.login.api.model.WebLoginRequestModel;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.naming.AuthenticationException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping(value = "/error", produces = {"text/html"})
    public String error(HttpServletRequest request, Model model) {
        model.addAttribute ("loginRequestModel", WebLoginRequestModel.builder ().build ());

        var requestUri = Optional.ofNullable (request.getAttribute (RequestDispatcher.ERROR_REQUEST_URI))
                .orElse ("login");

        var statusCode = request.getAttribute (RequestDispatcher.ERROR_STATUS_CODE);
        var exception = request.getAttribute (RequestDispatcher.ERROR_STATUS_CODE);

        if (statusCode != null && exception != null) {
            model.addAttribute ("error", statusCode.toString () + " - " + getRootCause (exception));
            return requestUri.toString ();
        }
        return handleAuthenticationException (request, model, requestUri, statusCode);
    }

    private String handleAuthenticationException(HttpServletRequest request, Model model, Object requestUri, Object statusCode) {
        var authenticationException = getLastAutException (request);

        if (authenticationException != null) {
            model.addAttribute ("error", HttpServletResponse.SC_UNAUTHORIZED + " - " +
                                         authenticationException.getLocalizedMessage ());
            return requestUri.toString ();
        }
        setErrorAttribute (model, statusCode);
        return "error";
    }

    private void setErrorAttribute(Model model, Object statusCode) {
        var errorMessage = Optional.ofNullable (statusCode)
                .map (Object::toString)
                .orElse (HttpServletResponse.SC_UNAUTHORIZED + " - Authorization error!");
        model.addAttribute ("error", errorMessage);
    }

    private AuthenticationException getLastAutException(HttpServletRequest request) {
        return Optional.ofNullable (request.getAttribute ("SPRING_SECURITY_LAST_EXCEPTION"))
                .or (() -> Optional.ofNullable (request.getSession ().getAttribute ("SPRING_SECURITY_LAST_EXCEPTION")))
                .map (AuthenticationException.class::cast)
                .orElse (null);
    }

    private String getRootCause(Object exception) {
        return Stream.iterate ((Throwable) exception, Objects::nonNull, Throwable::getCause)
                .reduce ((first, second) -> second)
                .map (Throwable::getMessage)
                .orElse ("Unknown error");
    }
}