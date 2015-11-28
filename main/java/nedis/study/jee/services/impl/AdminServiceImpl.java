package nedis.study.jee.services.impl;

import nedis.study.jee.dao.AccountDao;
import nedis.study.jee.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nedis.study.jee.services.AdminService;

import java.util.List;

/**
 * @author nedis
 * @version 1.0
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public List<Account> loadAllUser() {
        return accountDao.findAll();
    }

    @Override
    public Account getAccount(Long userId) {
        return accountDao.findById(userId);
    }
}
