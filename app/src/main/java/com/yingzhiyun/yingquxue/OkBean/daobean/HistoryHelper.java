package com.yingzhiyun.yingquxue.OkBean.daobean;


import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.dao.DaoMaster;
import com.yingzhiyun.yingquxue.dao.DaoSession;
import com.yingzhiyun.yingquxue.dao.SearchHistoryDao;

import java.util.List;

/**
 * Created by Administrator on 2019/1/23.
 */

public class HistoryHelper {
    private static HistoryHelper sLoginHelper;
    private final SearchHistoryDao mDaoDao;

    private HistoryHelper(){
        //数据库初始化
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(MyApp.getMyApp(),"hittory.db");
        //获取可读数据库
        DaoMaster daoMaster = new DaoMaster(openHelper.getWritableDatabase());
        //获取表管理器
        DaoSession daoSession = daoMaster.newSession();
        //获取我们要操作表的工具类；
        mDaoDao=daoSession.getSearchHistoryDao();
    }
    public static HistoryHelper getInstance() {
        if (sLoginHelper == null) {
            synchronized (HistoryHelper.class) {
                if (sLoginHelper == null) {
                    sLoginHelper = new HistoryHelper();
                }
            }
        }

        return sLoginHelper;
    }
    public void insert(SearchHistory seclectorBean){
        mDaoDao.insert(seclectorBean);
    }

    public void delete(SearchHistory seclectorBean){
        mDaoDao.delete(seclectorBean);
    }

    public List<SearchHistory> query(){
        return  mDaoDao.queryBuilder().list();
    }


    public boolean queryLikeId(String search) {
        List<SearchHistory> list = mDaoDao.queryBuilder().where(SearchHistoryDao.Properties.Title.eq(search)).list();
        if(list.size()==0){
            return  true;
        }
        return false;
    }

    public void deleteAll(){

        mDaoDao.deleteAll();
    }

    public void insertAll(List<SearchHistory> likeBeans){
        mDaoDao.insertInTx(likeBeans);
    }

    public  void update(SearchHistory student){
        mDaoDao.update(student);
    }
}
