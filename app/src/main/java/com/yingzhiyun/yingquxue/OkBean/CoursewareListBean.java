package com.yingzhiyun.yingquxue.OkBean;

import java.util.List;

public class CoursewareListBean {


    /**
     * status : 200
     * hint : null
     * result : [{"add_time":null,"title":"江苏省苏州中学2018-2019学年高一下学期5月月考数学试题&答案","id":7726,"file_path":"http://www.yingzhiyunwenhua.cn/yzy/app/courseWare/7de26f1e-7055-44e7-896d-b7a1444e4eb7-1563419956958.doc","file_size":"2.91MB","type":"file","img_url":null,"collection":false,"labelList":["六年级（五四学制）","课件（word）","教材同步"]}]
     */

    private int status;
    private Object hint;
    private List<ResultBean> result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getHint() {
        return hint;
    }

    public void setHint(Object hint) {
        this.hint = hint;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * add_time : null
         * title : 江苏省苏州中学2018-2019学年高一下学期5月月考数学试题&答案
         * id : 7726
         * file_path : http://www.yingzhiyunwenhua.cn/yzy/app/courseWare/7de26f1e-7055-44e7-896d-b7a1444e4eb7-1563419956958.doc
         * file_size : 2.91MB
         * type : file
         * img_url : null
         * collection : false
         * labelList : ["六年级（五四学制）","课件（word）","教材同步"]
         */

        private Object add_time;
        private String title;
        private int id;
        private String file_path;
        private String file_size;
        private String type;
        private Object img_url;
        private boolean collection;
        private List<String> labelList;

        public Object getAdd_time() {
            return add_time;
        }

        public void setAdd_time(Object add_time) {
            this.add_time = add_time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFile_path() {
            return file_path;
        }

        public void setFile_path(String file_path) {
            this.file_path = file_path;
        }

        public String getFile_size() {
            return file_size;
        }

        public void setFile_size(String file_size) {
            this.file_size = file_size;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getImg_url() {
            return img_url;
        }

        public void setImg_url(Object img_url) {
            this.img_url = img_url;
        }

        public boolean isCollection() {
            return collection;
        }

        public void setCollection(boolean collection) {
            this.collection = collection;
        }

        public List<String> getLabelList() {
            return labelList;
        }

        public void setLabelList(List<String> labelList) {
            this.labelList = labelList;
        }
    }
}
