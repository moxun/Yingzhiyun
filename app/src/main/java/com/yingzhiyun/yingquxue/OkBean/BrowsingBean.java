package com.yingzhiyun.yingquxue.OkBean;

import java.io.Serializable;
import java.util.List;

public class BrowsingBean implements Serializable {


    /**
     * status : 200
     * hint : null
     * result : [{"effective":"长期有效","teacherName":"邓虎","teacherHead":"http://192.168.1.20/yzyFiles//userHead/denghu.png","subject":"英语","price":29,"id":9,"signUpNumber":"0","title":"《王者归来-2020高考英语书面表达高分秘籍》","type":"course"},{"effective":"长期有效","teacherName":"王玲","teacherHead":"http://192.168.1.20/yzyFiles//userHead/wangling.png","subject":"语文","price":0,"id":7,"signUpNumber":"5","title":"《玲姐讲作文》--5步拿下2020年高考议论文","type":"course"},{"effective":"长期有效","teacherName":"刘露露","teacherHead":"http://192.168.1.20/yzyFiles//userHead/liululu.png","subject":"数学","price":49,"id":8,"signUpNumber":"1","title":"《直击2020高考--函数核心考点全覆盖》","type":"course"},{"effective":"长期有效","teacherName":"张鹏飞","teacherHead":"http://192.168.1.20/yzyFiles//userHead/zhangpengfei.png","subject":"化学","price":99,"id":10,"signUpNumber":"0","title":"《直击2020高考\u2014\u2014化学高频考点全覆盖》","type":"course"},{"file_path":"http://192.168.1.20/yzy/app/courseWare/49/courseWare/7dcb239b-8887-4fa4-9e30-bf4be5900146-1561447850522.pdf","labelList":["必修三","pdf","教材同步"],"id":2407,"title":"(必修三自主综合检测）","type":"pdf","read_volume":"0","add_time":"2019-06-25","file_size":"5.82MB"}]
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

    public static class ResultBean implements Serializable{
        public boolean isCollection() {
            return collection;
        }

        public void setCollection(boolean collection) {
            this.collection = collection;
        }

        /**
         * effective : 长期有效
         * teacherName : 邓虎
         * teacherHead : http://192.168.1.20/yzyFiles//userHead/denghu.png
         * subject : 英语
         * price : 29
         * id : 9
         * signUpNumber : 0
         * title : 《王者归来-2020高考英语书面表达高分秘籍》
         * type : course
         * file_path : http://192.168.1.20/yzy/app/courseWare/49/courseWare/7dcb239b-8887-4fa4-9e30-bf4be5900146-1561447850522.pdf
         * labelList : ["必修三","pdf","教材同步"]
         * read_volume : 0
         * add_time : 2019-06-25
         * file_size : 5.82MB
         */
        private boolean collection;
        private String effective;
        private String teacherName;
        private String teacherHead;
        private String subject;
        private double price;
        private int id;
        private String signUpNumber;
        private String title;
        private String type;
        private String file_path;
        private String read_volume;
        private String add_time;
        private String file_size;
        private List<String> labelList;
        private boolean vip;

        public void setPrice(double price) {
            this.price = price;
        }

        public boolean isVip() {
            return vip;
        }

        public void setVip(boolean vip) {
            this.vip = vip;
        }

        public String getEffective() {
            return effective;
        }

        public void setEffective(String effective) {
            this.effective = effective;
        }

        public String getTeacherName() {
            return teacherName;
        }

        public void setTeacherName(String teacherName) {
            this.teacherName = teacherName;
        }

        public String getTeacherHead() {
            return teacherHead;
        }

        public void setTeacherHead(String teacherHead) {
            this.teacherHead = teacherHead;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSignUpNumber() {
            return signUpNumber;
        }

        public void setSignUpNumber(String signUpNumber) {
            this.signUpNumber = signUpNumber;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getFile_path() {
            return file_path;
        }

        public void setFile_path(String file_path) {
            this.file_path = file_path;
        }

        public String getRead_volume() {
            return read_volume;
        }

        public void setRead_volume(String read_volume) {
            this.read_volume = read_volume;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getFile_size() {
            return file_size;
        }

        public void setFile_size(String file_size) {
            this.file_size = file_size;
        }

        public List<String> getLabelList() {
            return labelList;
        }

        public void setLabelList(List<String> labelList) {
            this.labelList = labelList;
        }
    }
}
