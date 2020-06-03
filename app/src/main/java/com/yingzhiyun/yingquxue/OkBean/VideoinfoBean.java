package com.yingzhiyun.yingquxue.OkBean;

import java.util.List;

public class VideoinfoBean {

    /**
     * status : 200
     * hint : 查询成功
     * result : {"recommend_list":[{"add_time":"2019-06-20 13:59:55","title":"医学专业临床视频","id":3,"file_path":"http://www.yingzhiyunwenhua.cn/yzy/app/indexFile/172e274d-c406-4f6f-8b2b-e0d1d4ab490f-1561010395193.mp4","file_size":"2.17MB","type":"video","img_url":"http://www.yingzhiyunwenhua.cn/yzyFiles/icons/8475f67e-499c-45f9-b738-d223f38f7f5c-1561010395210.png","collection":false,"labelList":[]},{"add_time":"2019-06-20 14:04:44","title":"专业教练教开车","id":5,"file_path":"http://www.yingzhiyunwenhua.cn/yzy/app/indexFile/1f58a915-b09d-4fc1-9175-3d31f39f4ad3-1561010684645.mp4","file_size":"9.12MB","type":"video","img_url":"http://www.yingzhiyunwenhua.cn/yzyFiles/icons/bca4892a-7eba-4e65-b1f9-cc65e2cd2189-1561010684789.png","collection":false,"labelList":[]}],"data":{"add_time":"2019-06-20 13:59:55","title":"医学专业临床视频","id":3,"file_path":"http://www.yingzhiyunwenhua.cn/yzy/app/indexFile/172e274d-c406-4f6f-8b2b-e0d1d4ab490f-1561010395193.mp4","file_size":"2.17MB","type":"video","img_url":null,"collection":false,"labelList":[]}}
     */

    private int status;
    private String hint;
    private ResultBean result;

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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * recommend_list : [{"add_time":"2019-06-20 13:59:55","title":"医学专业临床视频","id":3,"file_path":"http://www.yingzhiyunwenhua.cn/yzy/app/indexFile/172e274d-c406-4f6f-8b2b-e0d1d4ab490f-1561010395193.mp4","file_size":"2.17MB","type":"video","img_url":"http://www.yingzhiyunwenhua.cn/yzyFiles/icons/8475f67e-499c-45f9-b738-d223f38f7f5c-1561010395210.png","collection":false,"labelList":[]},{"add_time":"2019-06-20 14:04:44","title":"专业教练教开车","id":5,"file_path":"http://www.yingzhiyunwenhua.cn/yzy/app/indexFile/1f58a915-b09d-4fc1-9175-3d31f39f4ad3-1561010684645.mp4","file_size":"9.12MB","type":"video","img_url":"http://www.yingzhiyunwenhua.cn/yzyFiles/icons/bca4892a-7eba-4e65-b1f9-cc65e2cd2189-1561010684789.png","collection":false,"labelList":[]}]
         * data : {"add_time":"2019-06-20 13:59:55","title":"医学专业临床视频","id":3,"file_path":"http://www.yingzhiyunwenhua.cn/yzy/app/indexFile/172e274d-c406-4f6f-8b2b-e0d1d4ab490f-1561010395193.mp4","file_size":"2.17MB","type":"video","img_url":null,"collection":false,"labelList":[]}
         */

        private DataBean data;


        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }



        public static class DataBean {
            /**
             * add_time : 2019-06-20 13:59:55
             * title : 医学专业临床视频
             * id : 3
             * file_path : http://www.yingzhiyunwenhua.cn/yzy/app/indexFile/172e274d-c406-4f6f-8b2b-e0d1d4ab490f-1561010395193.mp4
             * file_size : 2.17MB
             * type : video
             * img_url : null
             * collection : false
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
            private List<String> labelList;

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

            public List<String> getLabelList() {
                return labelList;
            }

            public void setLabelList(List<String> labelList) {
                this.labelList = labelList;
            }
        }


    }
}
