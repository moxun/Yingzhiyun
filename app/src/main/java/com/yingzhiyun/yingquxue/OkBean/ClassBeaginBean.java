package com.yingzhiyun.yingquxue.OkBean;

import java.util.List;

public class ClassBeaginBean {

    /**
     * status : null
     * hint : null
     * result : {"courseTitle":"数学高三如何学好数学","effective":"2019.9.25-2020.1.1","courseDetail":{"teacherName":"R楠","teacherHead":"http://192.168.0.120/yzyFiles//userHead/0de88590-df13-4a2f-b884-0b5224a91ac5-1567062269600.JPG","courseBriefing":[{"contentType":"title","content":"课程简介"},{"contentType":"text","content":"课程是指学校学生所应学习的学科总和及其进程与安排。课程是对教育的目标、教学内容、教学活动方式的规划和设计，是教学计划、教学大纲等诸多方面实施过程的总和。广义的课程是指学校为实现培养目标而选择的教育内容及其进程的总和，它包括学校老师所教授的各门学科和有目的、有计划的教育活动。狭义的课程是指某一门学科"},{"contentType":"title","content":"适应人群"},{"contentType":"text","content":"高三学员"},{"contentType":"title","content":"课程内容"},{"contentType":"text","content":"一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习"}],"teacherLabel":"今天，又是充满希望的一天"},"courseOutline":[{"startTime":"2019-09-25 15:58","title":"1.四则运算"},{"startTime":"2019-09-26 15:58","title":"2.乘方"},{"startTime":"2019-09-27 15:58","title":"3.完结-总结"}]}
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
         * courseTitle : 数学高三如何学好数学
         * effective : 2019.9.25-2020.1.1
         * courseDetail : {"teacherName":"R楠","teacherHead":"http://192.168.0.120/yzyFiles//userHead/0de88590-df13-4a2f-b884-0b5224a91ac5-1567062269600.JPG","courseBriefing":[{"contentType":"title","content":"课程简介"},{"contentType":"text","content":"课程是指学校学生所应学习的学科总和及其进程与安排。课程是对教育的目标、教学内容、教学活动方式的规划和设计，是教学计划、教学大纲等诸多方面实施过程的总和。广义的课程是指学校为实现培养目标而选择的教育内容及其进程的总和，它包括学校老师所教授的各门学科和有目的、有计划的教育活动。狭义的课程是指某一门学科"},{"contentType":"title","content":"适应人群"},{"contentType":"text","content":"高三学员"},{"contentType":"title","content":"课程内容"},{"contentType":"text","content":"一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习"}],"teacherLabel":"今天，又是充满希望的一天"}
         * courseOutline : [{"startTime":"2019-09-25 15:58","title":"1.四则运算"},{"startTime":"2019-09-26 15:58","title":"2.乘方"},{"startTime":"2019-09-27 15:58","title":"3.完结-总结"}]
         */
        private String subject;
        private String courseTitle;
        private String effective;
        private String courseImg;
        private CourseDetailBean courseDetail;
        private List<CourseOutlineBean> courseOutline;

        public String getCourseImg() {
            return courseImg;
        }

        public void setCourseImg(String courseImg) {
            this.courseImg = courseImg;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getCourseTitle() {
            return courseTitle;
        }

        public void setCourseTitle(String courseTitle) {
            this.courseTitle = courseTitle;
        }

        public String getEffective() {
            return effective;
        }

        public void setEffective(String effective) {
            this.effective = effective;
        }

        public CourseDetailBean getCourseDetail() {
            return courseDetail;
        }

        public void setCourseDetail(CourseDetailBean courseDetail) {
            this.courseDetail = courseDetail;
        }

        public List<CourseOutlineBean> getCourseOutline() {
            return courseOutline;
        }

        public void setCourseOutline(List<CourseOutlineBean> courseOutline) {
            this.courseOutline = courseOutline;
        }

        public static class CourseDetailBean {
            /**
             * teacherName : R楠
             * teacherHead : http://192.168.0.120/yzyFiles//userHead/0de88590-df13-4a2f-b884-0b5224a91ac5-1567062269600.JPG
             * courseBriefing : [{"contentType":"title","content":"课程简介"},{"contentType":"text","content":"课程是指学校学生所应学习的学科总和及其进程与安排。课程是对教育的目标、教学内容、教学活动方式的规划和设计，是教学计划、教学大纲等诸多方面实施过程的总和。广义的课程是指学校为实现培养目标而选择的教育内容及其进程的总和，它包括学校老师所教授的各门学科和有目的、有计划的教育活动。狭义的课程是指某一门学科"},{"contentType":"title","content":"适应人群"},{"contentType":"text","content":"高三学员"},{"contentType":"title","content":"课程内容"},{"contentType":"text","content":"一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习一轮复习"}]
             * teacherLabel : 今天，又是充满希望的一天
             */
            private int teacherId;
            private String teacherName;
            private String teacherHead;

            public int getTeacherId() {
                return teacherId;
            }

            public void setTeacherId(int teacherId) {
                this.teacherId = teacherId;
            }

            private String teacherLabel;
            private List<CourseBriefingBean> courseBriefing;

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

            public String getTeacherLabel() {
                return teacherLabel;
            }

            public void setTeacherLabel(String teacherLabel) {
                this.teacherLabel = teacherLabel;
            }

            public List<CourseBriefingBean> getCourseBriefing() {
                return courseBriefing;
            }

            public void setCourseBriefing(List<CourseBriefingBean> courseBriefing) {
                this.courseBriefing = courseBriefing;
            }

            public static class CourseBriefingBean {
                /**
                 * contentType : title
                 * content : 课程简介
                 */

                private String contentType;
                private String content;
                private String videoPath;

                public String getVideoPath() {
                    return videoPath;
                }

                public void setVideoPath(String videoPath) {
                    this.videoPath = videoPath;
                }

                public String getContentType() {
                    return contentType;
                }

                public void setContentType(String contentType) {
                    this.contentType = contentType;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }
            }
        }

        public static class CourseOutlineBean {
            /**
             * startTime : 2019-09-25 15:58
             * title : 1.四则运算
             */
            private int id;
            private String videoPath;
            private String startTime;
            private String title;
            private String isFreeTask;
            private String liveStatus;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getIsFreeTask() {
                return isFreeTask;
            }

            public void setIsFreeTask(String isFreeTask) {
                this.isFreeTask = isFreeTask;
            }

            public String getLiveStatus() {
                return liveStatus;
            }

            public void setLiveStatus(String liveStatus) {
                this.liveStatus = liveStatus;
            }

            public String getVideoPath() {
                return videoPath;
            }

            public void setVideoPath(String videoPath) {
                this.videoPath = videoPath;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
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
