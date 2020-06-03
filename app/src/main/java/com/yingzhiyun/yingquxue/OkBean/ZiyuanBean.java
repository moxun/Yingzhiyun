package com.yingzhiyun.yingquxue.OkBean;

import java.io.Serializable;
import java.util.List;

public class ZiyuanBean implements Serializable {

    /**
     * status : 200
     * hint : 查询成功
     * result : [{"add_time":"2019-06-04 10:36:15","title":"高三语文模拟试题（二）","id":185,"file_path":"http://192.168.1.2/yzy/app/courseWare/073add09-3ac4-4dfb-8434-a0e3387b61bb-1559615775932.docx","file_size":"0.04MB","type":"file","img_url":null,"collection":false,"labelList":["单元测试","课件（word）","试题试卷"]},{"add_time":"2019-06-04 10:36:43","title":"高三语文模拟试题（三）","id":186,"file_path":"http://192.168.1.2/yzy/app/courseWare/1ce890e5-326f-42f6-9984-0f38e9b13987-1559615803280.docx","file_size":"0.04MB","type":"file","img_url":null,"collection":false,"labelList":["单元测试","课件（word）","试题试卷"]},{"add_time":"2019-06-04 10:37:15","title":"高三语文模拟试题（四）","id":187,"file_path":"http://192.168.1.2/yzy/app/courseWare/867ebdf2-2f3f-4720-a4c3-e777e8fac5df-1559615835778.docx","file_size":"0.04MB","type":"file","img_url":null,"collection":false,"labelList":["单元测试","课件（word）","试题试卷"]},{"add_time":"2019-06-04 10:37:43","title":"高三语文模拟试题（五）","id":188,"file_path":"http://192.168.1.2/yzy/app/courseWare/f843a968-97f7-4b19-9a33-8d52683380b0-1559615863727.docx","file_size":"0.17MB","type":"file","img_url":null,"collection":false,"labelList":["单元测试","课件（word）","试题试卷"]}]
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

    public static class ResultBean implements Serializable{
        /**
         * add_time : 2019-06-04 10:36:15
         * title : 高三语文模拟试题（二）
         * id : 185
         * file_path : http://192.168.1.2/yzy/app/courseWare/073add09-3ac4-4dfb-8434-a0e3387b61bb-1559615775932.docx
         * file_size : 0.04MB
         * type : file
         * img_url : null
         * collection : false
         * labelList : ["单元测试","课件（word）","试题试卷"]
         */
        private String read_volume;
        private String add_time;
        private String title;
        private int id;
        private String file_path;
        private String file_size;
        private String type;
        private String img_url;
        private int countSignUp;
        private boolean vip;

        public boolean isVip() {
            return vip;
        }

        public void setVip(boolean vip) {
            this.vip = vip;
        }

        private int time;

        public int getCountSignUp() {
            return countSignUp;
        }

        public void setCountSignUp(int countSignUp) {
            this.countSignUp = countSignUp;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        private boolean collection;
        private String effective;
        private int teacherId;
        private String teacherName;
        private String teacherHead;
        private String subject;
        private String courseImg;
        private boolean isCollection;
        private double price;
        private String teacherLabel;
        private String signUpNumber;

        public String getEffective() {
            return effective;
        }

        public void setEffective(String effective) {
            this.effective = effective;
        }

        public int getTeacherId() {
            return teacherId;
        }

        public void setTeacherId(int teacherId) {
            this.teacherId = teacherId;
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

        public String getCourseImg() {
            return courseImg;
        }

        public void setCourseImg(String courseImg) {
            this.courseImg = courseImg;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getTeacherLabel() {
            return teacherLabel;
        }

        public void setTeacherLabel(String teacherLabel) {
            this.teacherLabel = teacherLabel;
        }

        public String getSignUpNumber() {
            return signUpNumber;
        }

        public void setSignUpNumber(String signUpNumber) {
            this.signUpNumber = signUpNumber;
        }


        public String getRead_volume() {
            return read_volume;
        }

        public void setRead_volume(String read_volume) {
            this.read_volume = read_volume;
        }

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

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public boolean isCollection() {
            return collection;
        }

        public void setCollection(boolean collection) {
            this.collection = collection;
        }


        public boolean isFolderCollection() {
            return isCollection;
        }

        public void setFolderCollection(boolean collection) {
            this.isCollection = collection;
        }
        public List<String> getLabelList() {
            return labelList;
        }

        public void setLabelList(List<String> labelList) {
            this.labelList = labelList;
        }
    }
}
