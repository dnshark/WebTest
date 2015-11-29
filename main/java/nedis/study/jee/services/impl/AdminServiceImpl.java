package nedis.study.jee.services.impl;

import nedis.study.jee.dao.AccountDao;
import nedis.study.jee.entities.Account;
import nedis.study.jee.forms.UserForm;
import nedis.study.jee.services.CommonService;
import nedis.study.jee.utils.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nedis.study.jee.services.AdminService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author nedis
 * @version 1.0
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private CommonService commonService;

    @Override
    public List<Account> loadAllUser() {
        return accountDao.findAll();
    }

    @Override
    public Account getAccount(Long userId) {
        return accountDao.findById(userId);
    }

    @Override
    @Transactional
    public void updateUser(Long userId, UserForm form) {
        Account account = accountDao.findById(userId);
        //ReflectionUtils.copyByFields(account, form); NEDIS не получается копировать все поля, так как теряется информация если поле не заполнено в форме
        copyFormToUser(form, account);
        accountDao.update(account);
    }

    private void copyFormToUser(UserForm form, Account account) {
        account.setActive(form.getActive());
        account.setEmail(form.getEmail());
        account.setLogin(form.getLogin());
        account.setFio(form.getFio());
        account.setConfirmed(form.getConfirmed());
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        accountDao.delete(accountDao.findById(userId));
    }

    @Override
    public Account addUser(UserForm form) {
        Account account = commonService.addAccount(form);
        return account;
    }
}
