package com.yingzhiyun.yingquxue.OkBean;

import java.io.Serializable;
import java.util.List;

public class SubjectInfoBean implements Serializable {

    /**
     * status : 200
     * hint : null
     * result : [{"detail":[{"teachingMaterialId":107,"subject":"数学","schoolType":"高中","title":"必修一"},{"teachingMaterialId":108,"subject":"数学","schoolType":"高中","title":"必修二"},{"teachingMaterialId":109,"subject":"数学","schoolType":"高中","title":"必修三"},{"teachingMaterialId":110,"subject":"数学","schoolType":"高中","title":"必修四"},{"teachingMaterialId":111,"subject":"数学","schoolType":"高中","title":"必修五"},{"teachingMaterialId":112,"subject":"数学","schoolType":"高中","title":"选修一"},{"teachingMaterialId":113,"subject":"数学","schoolType":"高中","title":"选修二"},{"teachingMaterialId":114,"subject":"数学","schoolType":"高中","title":"选修三"},{"teachingMaterialId":115,"subject":"数学","schoolType":"高中","title":"选修四"},{"teachingMaterialId":116,"subject":"数学","schoolType":"高中","title":"数学竞赛"},{"teachingMaterialId":117,"subject":"数学","schoolType":"高中","title":"其他"}],"title":"教材同步"},{"typeOptions":[{"typeId":"1","title":"理科"},{"typeId":"2","title":"文科"}],"detail":[{"teachingMaterialId":118,"subject":"数学","schoolType":"高中","title":"开学考试"},{"teachingMaterialId":119,"subject":"数学","schoolType":"高中","title":"月考"},{"teachingMaterialId":120,"subject":"数学","schoolType":"高中","title":"期中"},{"teachingMaterialId":121,"subject":"数学","schoolType":"高中","title":"期末"},{"teachingMaterialId":122,"subject":"数学","schoolType":"高中","title":"学业"},{"teachingMaterialId":123,"subject":"数学","schoolType":"高中","title":"联考"},{"teachingMaterialId":124,"subject":"数学","schoolType":"高中","title":"调研"},{"teachingMaterialId":125,"subject":"数学","schoolType":"高中","title":"单元测试"},{"teachingMaterialId":126,"subject":"数学","schoolType":"高中","title":"竞赛"},{"teachingMaterialId":127,"subject":"数学","schoolType":"高中","title":"模拟预测"},{"teachingMaterialId":128,"subject":"数学","schoolType":"高中","title":"真题"},{"teachingMaterialId":129,"subject":"数学","schoolType":"高中","title":"专题汇编"},{"teachingMaterialId":130,"subject":"数学","schoolType":"高中","title":"同步测试"},{"teachingMaterialId":131,"subject":"数学","schoolType":"高中","title":"自主招生"}],"title":"试题试卷"},{"detail":[{"teachingMaterialId":132,"subject":"数学","schoolType":"高中","title":"集合和常用逻辑用语"},{"teachingMaterialId":133,"subject":"数学","schoolType":"高中","title":"函数与导数"},{"teachingMaterialId":134,"subject":"数学","schoolType":"高中","title":"平面向量"},{"teachingMaterialId":135,"subject":"数学","schoolType":"高中","title":"数列不等式"},{"teachingMaterialId":136,"subject":"数学","schoolType":"高中","title":"立体几何"},{"teachingMaterialId":137,"subject":"数学","schoolType":"高中","title":"解析几何"},{"teachingMaterialId":138,"subject":"数学","schoolType":"高中","title":"计数原理与概率统计"},{"teachingMaterialId":139,"subject":"数学","schoolType":"高中","title":"算法与框图"},{"teachingMaterialId":140,"subject":"数学","schoolType":"高中","title":"矩阵与行列式"},{"teachingMaterialId":141,"subject":"数学","schoolType":"高中","title":"几何证明选将"}],"title":"知识点"},{"detail":[{"teachingMaterialId":149,"subject":"数学","schoolType":"高中","title":"真题在线"},{"teachingMaterialId":150,"subject":"数学","schoolType":"高中","title":"备考策略"},{"teachingMaterialId":151,"subject":"数学","schoolType":"高中","title":"一轮复习"},{"teachingMaterialId":152,"subject":"数学","schoolType":"高中","title":"二轮复习"},{"teachingMaterialId":153,"subject":"数学","schoolType":"高中","title":"三轮冲刺"},{"teachingMaterialId":991,"subject":"数学","schoolType":"高中","title":"高考真题"}],"title":"高考"},{"detail":[{"teachingMaterialId":142,"subject":"数学","schoolType":"高中","title":"同步备考"},{"teachingMaterialId":143,"subject":"数学","schoolType":"高中","title":"知识点专题"},{"teachingMaterialId":144,"subject":"数学","schoolType":"高中","title":"高考复习"},{"teachingMaterialId":145,"subject":"数学","schoolType":"高中","title":"模拟/摸底/预测"},{"teachingMaterialId":146,"subject":"数学","schoolType":"高中","title":"高考真题"},{"teachingMaterialId":147,"subject":"数学","schoolType":"高中","title":"试题汇编"},{"teachingMaterialId":148,"subject":"数学","schoolType":"高中","title":"其他"}],"title":"专辑"},{"detail":[{"teachingMaterialId":154,"subject":"数学","schoolType":"高中","title":"资讯要闻"},{"teachingMaterialId":155,"subject":"数学","schoolType":"高中","title":"数学新闻"},{"teachingMaterialId":156,"subject":"数学","schoolType":"高中","title":"高考数学"},{"teachingMaterialId":157,"subject":"数学","schoolType":"高中","title":"数学故事"},{"teachingMaterialId":158,"subject":"数学","schoolType":"高中","title":"趣味数学"},{"teachingMaterialId":159,"subject":"数学","schoolType":"高中","title":"数学达人"},{"teachingMaterialId":160,"subject":"数学","schoolType":"高中","title":"教学教研"},{"teachingMaterialId":161,"subject":"数学","schoolType":"高中","title":"教学体会"}],"title":"资讯"}]
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
        /**
         * detail : [{"teachingMaterialId":107,"subject":"数学","schoolType":"高中","title":"必修一"},{"teachingMaterialId":108,"subject":"数学","schoolType":"高中","title":"必修二"},{"teachingMaterialId":109,"subject":"数学","schoolType":"高中","title":"必修三"},{"teachingMaterialId":110,"subject":"数学","schoolType":"高中","title":"必修四"},{"teachingMaterialId":111,"subject":"数学","schoolType":"高中","title":"必修五"},{"teachingMaterialId":112,"subject":"数学","schoolType":"高中","title":"选修一"},{"teachingMaterialId":113,"subject":"数学","schoolType":"高中","title":"选修二"},{"teachingMaterialId":114,"subject":"数学","schoolType":"高中","title":"选修三"},{"teachingMaterialId":115,"subject":"数学","schoolType":"高中","title":"选修四"},{"teachingMaterialId":116,"subject":"数学","schoolType":"高中","title":"数学竞赛"},{"teachingMaterialId":117,"subject":"数学","schoolType":"高中","title":"其他"}]
         * title : 教材同步
         * typeOptions : [{"typeId":"1","title":"理科"},{"typeId":"2","title":"文科"}]
         */

        private String title;
        private List<DetailBean> detail;
        private List<TypeOptionsBean> typeOptions;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<DetailBean> getDetail() {
            return detail;
        }

        public void setDetail(List<DetailBean> detail) {
            this.detail = detail;
        }

        public List<TypeOptionsBean> getTypeOptions() {
            return typeOptions;
        }

        public void setTypeOptions(List<TypeOptionsBean> typeOptions) {
            this.typeOptions = typeOptions;
        }

        public static class DetailBean implements Serializable{
            /**
             * teachingMaterialId : 107
             * subject : 数学
             * schoolType : 高中
             * title : 必修一
             */

            private int teachingMaterialId;
            private String subject;
            private String schoolType;
            private String title;

            public int getTeachingMaterialId() {
                return teachingMaterialId;
            }

            public void setTeachingMaterialId(int teachingMaterialId) {
                this.teachingMaterialId = teachingMaterialId;
            }

            public String getSubject() {
                return subject;
            }

            public void setSubject(String subject) {
                this.subject = subject;
            }

            public String getSchoolType() {
                return schoolType;
            }

            public void setSchoolType(String schoolType) {
                this.schoolType = schoolType;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class TypeOptionsBean implements Serializable{
            /**
             * typeId : 1
             * title : 理科
             */

            private String typeId;
            private String title;

            public String getTypeId() {
                return typeId;
            }

            public void setTypeId(String typeId) {
                this.typeId = typeId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
