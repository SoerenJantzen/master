package de.sjantzen.master.services.account;

import de.sjantzen.master.model.Account;

/**
 * Created by sJantzen on 09.01.2018.
 */
public interface AccountService {

    public Account findAccountByEmail(String email);

    public void saveAccount(Account account);
}
