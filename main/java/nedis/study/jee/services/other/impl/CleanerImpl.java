package nedis.study.jee.services.other.impl;

import nedis.study.jee.dao.AccountDao;
import nedis.study.jee.services.other.Cleaner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Дмитрий on 18.12.2015.
 */
@Service("CleanerService")
public class CleanerImpl implements Cleaner{
    @Autowired
    AccountDao accountDao;

    @Override
    @Transactional
    public void clearNotConfirmedUsers(){
        accountDao.clearNotConfirmedUsers();
    }
}
