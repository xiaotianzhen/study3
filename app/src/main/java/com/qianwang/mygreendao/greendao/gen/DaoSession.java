package com.qianwang.mygreendao.greendao.gen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.qianwang.mygreendao.bean.User;

import com.qianwang.mygreendao.greendao.gen.UserDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 管理制定模式下所有可用的DAO对象
 * 能对实体进行插入、加载、更新、刷新、删除操作。
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig userDaoConfig;

    private final UserDao userDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        userDao = new UserDao(userDaoConfig, this);

        registerDao(User.class, userDao);
    }
    
    public void clear() {
        userDaoConfig.clearIdentityScope();
    }

    public UserDao getUserDao() {
        return userDao;
    }

}
