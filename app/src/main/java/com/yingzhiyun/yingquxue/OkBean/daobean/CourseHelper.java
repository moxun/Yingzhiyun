package com.yingzhiyun.yingquxue.OkBean.daobean;

import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.dao.CourseSearchBeanDao;
import com.yingzhiyun.yingquxue.dao.DaoMaster;
import com.yingzhiyun.yingquxue.dao.DaoSession;
import com.yingzhiyun.yingquxue.dao.SearchHistoryDao;

import java.util.List;

public class CourseHelper {
    private static CourseHelper sLoginHelper;
    private final CourseSearchBeanDao mDaoDao;

    private CourseHelper() {
        //数据库初始化
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(MyApp.getMyApp(), "course.db");
        //获取可读数据库
        DaoMaster daoMaster = new DaoMaster(openHelper.getWritableDatabase());
        //获取表管理器
        DaoSession daoSession = daoMaster.newSession();
        //获取我们要操作表的工具类；
        mDaoDao = daoSession.getCourseSearchBeanDao();
    }

    public static CourseHelper getInstance() {
        if (sLoginHelper == null) {
            synchronized (HistoryHelper.class) {
                if (sLoginHelper == null) {
                    sLoginHelper = new CourseHelper();
                }
            }
        }

        return sLoginHelper;
    }

    public void insert(CourseSearchBean seclectorBean) {
        mDaoDao.insert(seclectorBean);
    }

    public void delete(CourseSearchBean seclectorBean) {
        mDaoDao.delete(seclectorBean);
    }

    public List<CourseSearchBean> query() {
        return mDaoDao.queryBuilder().list();
    }


    public boolean queryLikeId(String search) {
        List<CourseSearchBean> list = mDaoDao.queryBuilder().where(CourseSearchBeanDao.Properties.Title.eq(search)).list();
        if (list.size() == 0) {
            return true;
        }
        return false;
    }

    public void deleteAll() {

        mDaoDao.deleteAll();
    }

    public void insertAll(List<CourseSearchBean> likeBeans) {
        mDaoDao.insertInTx(likeBeans);
    }

    public void update(CourseSearchBean student) {
        mDaoDao.update(student);
    }
}
