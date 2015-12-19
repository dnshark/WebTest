package nedis.study.jee.services.admin.impl;

import nedis.study.jee.components.EntityBuilder;
import nedis.study.jee.dao.AccountDao;
import nedis.study.jee.entities.Account;
import nedis.study.jee.entities.AccountRole;
import nedis.study.jee.entities.Role;
import nedis.study.jee.forms.admin.AdminForm;
import nedis.study.jee.services.allAccess.CommonService;
import nedis.study.jee.utils.Calculation;
import nedis.study.jee.utils.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nedis.study.jee.services.admin.AdminService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nedis
 * @version 1.0
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private EntityBuilder entityBuilder;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private CommonService commonService;

    @Override
    public List<Account> loadAllUser(int page,int count) {
        return accountDao.listAccounts((page - 1) * count, count);
    }

    @Override
    public Account getAccount(Long userId) {
        return accountDao.findById(userId);
    }

    @Override
    @Transactional
    //NEDIS
    public void updateUser(Long userId, AdminForm form) {
        Account account = accountDao.findById(userId);
        String password = account.getPassword();
        ReflectionUtils.copyByFields(account, form);
        if (form.getPassword()=="") {//NEDIS
            account.setPassword(password);
        }

        commonService.initRoles(form.getCheckRoles(),account);

        accountDao.update(account);
    }

    private void copyFormToUser(AdminForm form, Account account) {
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
    public Account addUser(AdminForm form) {
        Account account = commonService.addAccount(form);
        return account;
    }

    @Override
    public List<String> getRoles(Account user) {
        List<AccountRole> list = user.getAccountRoles();
        List<String> roles = new ArrayList<String>();
        for (AccountRole accountRole : list) {
            roles.add(accountRole.getRole().toString());
        }
        return roles;
    }

    @Override
    public AdminForm getAdminForm(Model model, Account user) {
        AdminForm adminForm = new AdminForm();
        List<Role> list = commonService.listAllRoles();
        adminForm.setAllRoles(list);
        ReflectionUtils.copyByFields(adminForm, user);
        adminForm.setCheckRoles(getRoles(user));
        return adminForm;
    }

    @Override
    public Account buildAccount() {
        Account account = entityBuilder.buildAccount();
        List list = new ArrayList<Role>();
        account.setAccountRoles(list);
        return account;
    }

    @Override
    public int getUsersMaxPageList(Integer count) {
        return  Calculation.getMaxPage(accountDao.getListCount(),count);
    }
}
