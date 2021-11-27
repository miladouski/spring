package com.spring.project.controllers;

import com.spring.project.interfaces.AccountDAO;
import com.spring.project.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

import static com.spring.project.constants.ConstantsView.*;

@Controller
public class AuthorizationController {

    private final AccountDAO accountDAO;

    @Autowired
    public AuthorizationController(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @GetMapping("/")
    public String rootPage() {
        return ROOT_PAGE_REDIRECT;
    }

   @GetMapping("/login")
    public String loginPage() {
        return LOGIN_PAGE;
   }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute(ACCOUNT_FORM_ATTRIBUTE, new Account());
        return REGISTRATION_PAGE;
    }

    @PostMapping("/registration")
    public String addAccount(@ModelAttribute(ACCOUNT_FORM_ATTRIBUTE) @Valid Account accountForm, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return REGISTRATION_PAGE;
        }
        if(!accountDAO.saveAccount(accountForm)){
            model.addAttribute(USERNAME_ERROR_ATTRIBUTE, USERNAME_ERROR_MESSAGE);
            return REGISTRATION_PAGE;
        }
        return ROOT_PAGE_REDIRECT;
    }
}
