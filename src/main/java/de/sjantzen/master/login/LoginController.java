package de.sjantzen.master.login;

import de.sjantzen.master.model.Account;
import de.sjantzen.master.model.Address;
import de.sjantzen.master.model.Company;
import de.sjantzen.master.model.User;
import de.sjantzen.master.services.account.AccountService;
import de.sjantzen.master.services.address.AddressService;
import de.sjantzen.master.services.company.CompanyService;
import de.sjantzen.master.services.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class LoginController {

    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private AddressService addressService;

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login() {
        LOG.info("/login action invoked.");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        Account account = new Account();
        Company company = new Company();
        modelAndView.addObject("account", account);
        modelAndView.addObject("company", company);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewAccount(@Valid Account account, @Valid Company company, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Account accountExists = accountService.findAccountByEmail(account.getEmail());
        if (accountExists != null) {
            bindingResult.rejectValue("email", "error.account", "There is already an account registered with this email.");
        }

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        }
        else {
            Company companyExists = companyService.findCompanyByName(company.getName());
            if (companyExists == null) {
                LOG.info("Save company with name: " + company.getName());

                companyService.saveCompany(company);
                company.getAddress().setCompany(company);
                addressService.saveAddress(company.getAddress());
                account.setCompany(company);
            }
            else {
                LOG.info("Company with name: " + company.getName() + " already exists.");
                account.setCompany(companyExists);
            }

            LOG.info("Save account");
            accountService.saveAccount(account);
            LOG.info("Account saved.");
            modelAndView.addObject("successMessage", "Account has been registered successfully.");
            modelAndView.addObject("account", new Account());
            modelAndView.setViewName("registration");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home() {
        LOG.info("/menu action invoked");
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountService.findAccountByEmail(auth.getName());
        modelAndView.addObject("accountName", "Welcome " + account.getFirstname() + " " + account.getLastname() + " (" + account.getEmail() + ")");
        modelAndView.addObject("adminMessage", "Content Available Only for Accounts with Admin Role.");
        modelAndView.setViewName("menu");
        return modelAndView;
    }
}
