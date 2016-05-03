package com.epam.controller;

import com.epam.controller.util.Country;
import com.epam.model.User;
import com.epam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.*;

/**
 * Created by Sahak_Babayan on 3/30/2016.
 */
@Controller
public class RegistrationController {

    private UserService userService;

    private MessageSource messageSource;

    @Autowired
    RegistrationController(UserService userService, MessageSource messageSource) {
        this.userService = userService;
        this.messageSource = messageSource;
    }

    public static List<Country> getCountries(final Locale inLocale) {
        String[] countryCodes = Locale.getISOCountries();
        List<Country> countries = new ArrayList<Country>(countryCodes.length);
        for (String countryCode : countryCodes) {
            countries.add(new Country(countryCode, new Locale("", countryCode).getDisplayCountry(inLocale)));
        }
        Collections.sort(countries, new Comparator<Country>() {
            public int compare(Country c1, Country c2) {
                return c1.getName().compareTo(c2.getName());
            }
        });
        return countries;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(User user, Model model) {
        model.addAttribute("countries", getCountries(Locale.US));
        return "pages/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam("confirmPassword") String confirmPassword,
                           @Valid User user,
                           BindingResult result) {

        if(result.hasErrors()) {
            return "pages/register";
        }

        // validating password
        if(StringUtils.isEmpty(user.getPassword())) {
            String message = messageSource.getMessage("err.field.password.required", null, LocaleContextHolder.getLocale());
            result.addError(new FieldError("user", "password", message));
            return "pages/register";
        }

        if(!user.getPassword().equals(confirmPassword)) {
            String message = messageSource.getMessage("err.field.mismatched.confirm.password", null, LocaleContextHolder.getLocale());
            result.addError(new FieldError("user", "password", message));
            return "pages/register";
        }

        // checking whether the email address is already used
        boolean isEmailExist = userService.isEmailExist(user.getEmail());
        if(isEmailExist) {
            String message = messageSource.getMessage("err.field.email.exist", null, LocaleContextHolder.getLocale());
            result.addError(new FieldError("user", "email", message));
            return "pages/register";
        }

        // storing user's data
        userService.add(user);

        // going to login page
        return "redirect:pages/login";
    }

}
