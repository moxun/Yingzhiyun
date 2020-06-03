package com.yingzhiyun.yingquxue.OkBean;

import java.util.List;

public class MyCollectBean {

    /**
     * status : 200
     * hint : 查询成功
     * result : [{"add_time":"2019-07-31 15:05:41","title":"篮球大使教你打篮球","id":4,"file_path":"http://www.yingzhiyunwenhua.cn/yzy/app/indexFile/1db84852-eff8-44d8-aa9d-06f9dbe65f46-1561010636591.mp4","file_size":"1.09MB","type":"video","img_url":null,"collection":true,"labelList":[]},{"add_time":"2019-08-01 10:24:57","title":"名师辅导测试","id":6,"file_path":"http://www.yingzhiyunwenhua.cn/yzy/app/indexFile/5d377485-1e68-41a3-983b-0a685aff0e94-1561010727269.mp4","file_size":"9.12MB","type":"video","img_url":null,"collection":true,"labelList":[]}]
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

    public static class ResultBean {
        /**
         * add_time : 2019-07-31 15:05:41
         * title : 篮球大使教你打篮球
         * id : 4
         * file_path : http://www.yingzhiyunwenhua.cn/yzy/app/indexFile/1db84852-eff8-44d8-aa9d-06f9dbe65f46-1561010636591.mp4
         * file_size : 1.09MB
         * type : video
         * img_url : null
         * collection : true
         * labelList : []
         */

        private String add_time;
        private String title;
        private int id;
        private String file_path;
        private String file_size;
        private String type;
        private Object img_url;
        private boolean collection;
        private List<?> labelList;

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
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

        public List<?> getLabelList() {
            return labelList;
        }

        public void setLabelList(List<?> labelList) {
            this.labelList = labelList;
        }
    }
}
