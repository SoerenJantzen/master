package de.sjantzen.master.services.account;

import de.sjantzen.master.model.Account;
import de.sjantzen.master.model.Role;
import de.sjantzen.master.repositories.AccountRepository;
import de.sjantzen.master.repositories.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

    private static final Logger LOG = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Account findAccountByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    @Override
    public void saveAccount(Account account) {
        LOG.info("Save account: " + account.getFirstname() + " - " + account.getLastname() + " - " + account.getEmail()
        + " - " + account.getCompany().getName());
        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        account.setActive(1);
        Role accountRole = roleRepository.findByRole("ADMIN");
        account.setRoles(new HashSet<>(Arrays.asList(accountRole)));
        accountRepository.save(account);
    }
}
