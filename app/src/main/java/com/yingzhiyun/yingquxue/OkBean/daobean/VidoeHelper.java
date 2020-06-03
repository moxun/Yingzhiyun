package com.yingzhiyun.yingquxue.OkBean.daobean;

import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.dao.DaoMaster;
import com.yingzhiyun.yingquxue.dao.DaoSession;
import com.yingzhiyun.yingquxue.dao.SearchHistoryDao;
import com.yingzhiyun.yingquxue.dao.VideoDaoDao;

import java.util.List;

public class VidoeHelper {
    private static VidoeHelper sLoginHelper;
    private final VideoDaoDao mDaoDao;

    private VidoeHelper(){
        //数据库初始化
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(MyApp.getMyApp(),"hittory.db");
        //获取可读数据库
        DaoMaster daoMaster = new DaoMaster(openHelper.getWritableDatabase());
        //获取表管理器
        DaoSession daoSession = daoMaster.newSession();
        //获取我们要操作表的工具类；
        mDaoDao=daoSession.getVideoDaoDao();
    }
    public static VidoeHelper getInstance() {
        if (sLoginHelper == null) {
            synchronized (VidoeHelper.class) {
                if (sLoginHelper == null) {
                    sLoginHelper = new VidoeHelper();
                }
            }
        }

        return sLoginHelper;
    }
    public void insert(VideoDao seclectorBean){
        mDaoDao.insert(seclectorBean);
    }

    public void delete(VideoDao seclectorBean){
        mDaoDao.delete(seclectorBean);
    }

    public List<VideoDao> query(){
        return  mDaoDao.queryBuilder().list();
    }


    public boolean queryLikeId(String search) {
        List<VideoDao> list = mDaoDao.queryBuilder().where(VideoDaoDao.Properties.Title.eq(search)).list();
        if(list.size()==0){
            return  true;
        }
        return false;
    }

    public void deleteAll(){

        mDaoDao.deleteAll();
    }

    public void insertAll(List<VideoDao> likeBeans){
        mDaoDao.insertInTx(likeBeans);
    }

    public  void update(VideoDao student){
        mDaoDao.update(student);
    }
}
