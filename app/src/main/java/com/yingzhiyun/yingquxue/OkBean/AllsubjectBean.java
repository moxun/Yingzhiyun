package com.yingzhiyun.yingquxue.OkBean;

import com.yingzhiyun.yingquxue.units.BaseExpandableRecyclerViewAdapter;

import java.util.List;

public class AllsubjectBean {

    /**
     * status : 200
     * hint : 查询成功
     * result : [{"name":"初中","detail":[{"size":null,"imgPath":"http://www.yingzhiyunwenhua.cn/yzyFiles//icons/wuli.png","name":"物理","subjectId":15},{"size":null,"imgPath":"http://www.yingzhiyunwenhua.cn/yzyFiles//icons/huaxue.png","name":"化学","subjectId":16},{"size":null,"imgPath":"http://www.yingzhiyunwenhua.cn/yzyFiles//icons/shengwu.png","name":"生物","subjectId":17},{"size":null,"imgPath":"http://www.yingzhiyunwenhua.cn/yzyFiles//icons/zhengzhi.png","name":"政治","subjectId":18},{"size":null,"imgPath":"http://www.yingzhiyunwenhua.cn/yzyFiles//icons/lishi.png","name":"历史","subjectId":19},{"size":null,"imgPath":"http://www.yingzhiyunwenhua.cn/yzyFiles//icons/dili.png","name":"地理","subjectId":20},{"size":null,"imgPath":"http://www.yingzhiyunwenhua.cn/yzyFiles//icons/kexue.png","name":"科学","subjectId":21},{"size":null,"imgPath":"http://www.yingzhiyunwenhua.cn/yzyFiles//icons/lishiyushehui.png","name":"历史与社会","subjectId":22},{"size":null,"imgPath":"http://www.yingzhiyunwenhua.cn/yzyFiles//icons/xinxi.png","name":"信息","subjectId":24},{"size":null,"imgPath":"http://www.yingzhiyunwenhua.cn/yzyFiles//icons/yuwen.png","name":"语文","subjectId":2},{"size":null,"imgPath":"http://www.yingzhiyunwenhua.cn/yzyFiles//icons/shuxue.png","name":"数学","subjectId":3},{"size":null,"imgPath":"http://www.yingzhiyunwenhua.cn/yzyFiles//icons/yingyu.png","name":"英语","subjectId":4}]},{"name":"高中","detail":[{"size":null,"imgPath":"http://www.yingzhiyunwenhua.cn/yzyFiles//icons/huaxue.png","name":"化学","subjectId":11},{"size":null,"imgPath":"http://www.yingzhiyunwenhua.cn/yzyFiles//icons/shengwu.png","name":"生物","subjectId":12},{"size":null,"imgPath":"http://www.yingzhiyunwenhua.cn/yzyFiles//icons/zhengzhi.png","name":"政治","subjectId":13},{"size":null,"imgPath":"http://www.yingzhiyunwenhua.cn/yzyFiles//icons/lishi.png","name":"历史","subjectId":14},{"size":null,"imgPath":"http://www.yingzhiyunwenhua.cn/yzyFiles//icons/xinxi.png","name":"信息","subjectId":23},{"size":null,"imgPath":"http://www.yingzhiyunwenhua.cn/yzyFiles//icons/wenzong.png","name":"文科综合","subjectId":27},{"size":null,"imgPath":"http://www.yingzhiyunwenhua.cn/yzyFiles//icons/lizong.png","name":"理科综合","subjectId":28},{"size":null,"imgPath":"http://www.yingzhiyunwenhua.cn/yzyFiles//icons/shuxue.png","name":"数学","subjectId":6},{"size":null,"imgPath":"http://www.yingzhiyunwenhua.cn/yzyFiles//icons/yingyu.png","name":"英语","subjectId":7},{"size":null,"imgPath":"http://www.yingzhiyunwenhua.cn/yzyFiles//icons/dili.png","name":"地理","subjectId":8},{"size":null,"imgPath":"http://www.yingzhiyunwenhua.cn/yzyFiles//icons/yuwen.png","name":"语文","subjectId":9},{"size":null,"imgPath":"http://www.yingzhiyunwenhua.cn/yzyFiles//icons/wuli.png","name":"物理","subjectId":10}]}]
     */

    private int status;
    private String hint;
    private List<ResultBean> result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean implements BaseExpandableRecyclerViewAdapter.BaseGroupBean<ResultBean.DetailBean> {
        public ResultBean(String name) {
            this.name = name;

        }

        /**
         * name : 初中
         * detail : [{"size":null,"imgPath":"http://www.yingzhiyunwenhua.cn/yzyFiles//icons/wuli.png","name":"物理","subjectId":15},{"size":null,"imgPath":"http://www.yingzhiyunwenhua.cn/yzyFiles//icons/huaxue.png","name":"化学","subjectId":16},{"size":null,"imgPath":"http://www.yingzhiyunwenhua.cn/yzyFiles//icons/shengwu.png","name":"生物","subjectId":17},{"size":null,"imgPath":"http://www.yingzhiyunwenhua.cn/yzyFiles//icons/zhengzhi.png","name":"政治","subjectId":18},{"size":null,"imgPath":"http://www.yingzhiyunwenhua.cn/yzyFiles//icons/lishi.png","name":"历史","subjectId":19},{"size":null,"imgPath":"http://www.yingzhiyunwenhua.cn/yzyFiles//icons/dili.png","name":"地理","subjectId":20},{"size":null,"imgPath":"http://www.yingzhiyunwenhua.cn/yzyFiles//icons/kexue.png","name":"科学","subjectId":21},{"size":null,"imgPath":"http://www.yingzhiyunwenhua.cn/yzyFiles//icons/lishiyushehui.png","name":"历史与社会","subjectId":22},{"size":null,"imgPath":"http://www.yingzhiyunwenhua.cn/yzyFiles//icons/xinxi.png","name":"信息","subjectId":24},{"size":null,"imgPath":"http://www.yingzhiyunwenhua.cn/yzyFiles//icons/yuwen.png","name":"语文","subjectId":2},{"size":null,"imgPath":"http://www.yingzhiyunwenhua.cn/yzyFiles//icons/shuxue.png","name":"数学","subjectId":3},{"size":null,"imgPath":"http://www.yingzhiyunwenhua.cn/yzyFiles//icons/yingyu.png","name":"英语","subjectId":4}]
         */

        private String name;
        private List<DetailBean> detail;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<DetailBean> getDetail() {
            return detail;
        }

        public void setDetail(List<DetailBean> detail) {
            this.detail = detail;
        }

        @Override
        public int getChildCount() {
            if(detail!=null){
                return detail.size();
            }else{
                return  0;
            }

        }

        @Override
        public DetailBean getChildAt(int childIndex) {
            return detail.size() <= childIndex ? null : detail.get(childIndex);
        }

        @Override
        public boolean isExpandable() {
            return getChildCount() > 0;
        }

        public static class DetailBean {
            /**
             * size : null
             * imgPath : http://www.yingzhiyunwenhua.cn/yzyFiles//icons/wuli.png
             * name : 物理
             * subjectId : 15
             */

            private Object size;
            private String imgPath;
            private String name;
            private int subjectId;

            public Object getSize() {
                return size;
            }

            public void setSize(Object size) {
                this.size = size;
            }

            public String getImgPath() {
                return imgPath;
            }

            public void setImgPath(String imgPath) {
                this.imgPath = imgPath;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getSubjectId() {
                return subjectId;
            }

            public void setSubjectId(int subjectId) {
                this.subjectId = subjectId;
            }
        }
    }
}
