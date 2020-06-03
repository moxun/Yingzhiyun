package com.yingzhiyun.yingquxue.OkBean;

import java.io.Serializable;
import java.util.List;

public class MyTiBean implements Serializable {


    /**
     * status : 200
     * hint : 查询成功
     * result : [{"addTime":"2019-08-08 19:00:44","id4ItemBank":215,"th":2,"status":"false","msg":null,"score":5,"totalSize":null,"type":"RadioSelect","title":null,"rightKey":[{"width":null,"height":null,"contentType":"text","content":"D"}],"analysis":[{"width":null,"height":null,"contentType":"text","content":"解析：略"}],"stemContents":[{"width":null,"height":null,"contentType":"text","content":"投掷两粒骰子,得到其向上的点数分别为m、n,则复数(m+ni)(n-mi)为实数的概率为(　 )"}],"optionsList":[{"text":[{"width":null,"height":null,"contentType":"latex","content":"\\frac{1}{4}"}],"value":"A","userSelected":false,"right":false},{"text":[{"width":null,"height":null,"contentType":"latex","content":"\\frac{1}{3}"}],"value":"B","userSelected":true,"right":false},{"text":[{"width":null,"height":null,"contentType":"latex","content":"\\frac{1}{12}"}],"value":"C","userSelected":false,"right":false},{"text":[{"width":null,"height":null,"contentType":"latex","content":"\\frac{1}{6}"}],"value":"D","userSelected":false,"right":true}],"stemList":[],"imgList":[],"collection":true},{"addTime":"2019-08-08 19:00:44","id4ItemBank":216,"th":3,"status":"false","msg":null,"score":5,"totalSize":null,"type":"RadioSelect","title":null,"rightKey":[{"width":null,"height":null,"contentType":"text","content":"A"}],"analysis":[{"width":null,"height":null,"contentType":"text","content":"解析：略"}],"stemContents":[{"width":null,"height":null,"contentType":"text","content":"已知复数"},{"width":null,"height":null,"contentType":"latex","content":"a+bi=\\frac{(1+i)^{2}}{(1+i)}"},{"width":null,"height":null,"contentType":"text","content":"(i是虚数单位,"},{"width":null,"height":null,"contentType":"latex","content":"a,b\\in R"},{"width":null,"height":null,"contentType":"text","content":",)则a+b=（  ）"}],"optionsList":[{"text":[{"width":null,"height":null,"contentType":"latex","content":"-2"}],"value":"A","userSelected":false,"right":true},{"text":[{"width":null,"height":null,"contentType":"latex","content":"-1"}],"value":"B","userSelected":true,"right":false},{"text":[{"width":null,"height":null,"contentType":"latex","content":"0"}],"value":"C","userSelected":false,"right":false},{"text":[{"width":null,"height":null,"contentType":"latex","content":"2"}],"value":"D","userSelected":false,"right":false}],"stemList":[],"imgList":[],"collection":false},{"addTime":"2019-08-08 23:17:20","id4ItemBank":217,"th":1,"status":"false","msg":null,"score":5,"totalSize":null,"type":"RadioSelect","title":null,"rightKey":[{"width":null,"height":null,"contentType":"text","content":"C"}],"analysis":[{"width":null,"height":null,"contentType":"text","content":"解析：略"}],"stemContents":[{"width":null,"height":null,"contentType":"text","content":"若(1+i)z=2，则复数z的共轭复数为（）"}],"optionsList":[{"text":[{"width":null,"height":null,"contentType":"latex","content":"1-i"}],"value":"A","userSelected":false,"right":false},{"text":[{"width":null,"height":null,"contentType":"latex","content":"i"}],"value":"B","userSelected":true,"right":false},{"text":[{"width":null,"height":null,"contentType":"latex","content":"1+i"}],"value":"C","userSelected":false,"right":true},{"text":[{"width":null,"height":null,"contentType":"latex","content":"1"}],"value":"D","userSelected":false,"right":false}],"stemList":[],"imgList":[],"collection":false},{"addTime":"2019-08-08 23:17:20","id4ItemBank":218,"th":4,"status":"false","msg":null,"score":5,"totalSize":null,"type":"RadioSelect","title":null,"rightKey":[{"width":null,"height":null,"contentType":"text","content":"C"}],"analysis":[{"width":null,"height":null,"contentType":"text","content":"解析：略"}],"stemContents":[{"width":null,"height":null,"contentType":"text","content":"已知"},{"width":null,"height":null,"contentType":"latex","content":"a,b"},{"width":null,"height":null,"contentType":"text","content":"均为正实数，且"},{"width":null,"height":null,"contentType":"latex","content":"a+b=3"},{"width":null,"height":null,"contentType":"text","content":"则"},{"width":null,"height":null,"contentType":"latex","content":"\\frac{1}{a}+\\frac{1}{b}"},{"width":null,"height":null,"contentType":"text","content":"的最小值为"}],"optionsList":[{"text":[{"width":null,"height":null,"contentType":"latex","content":"\\frac{2}{3}"}],"value":"A","userSelected":false,"right":false},{"text":[{"width":null,"height":null,"contentType":"latex","content":"\\frac{2\\sqrt{2}}{3}"}],"value":"B","userSelected":true,"right":false},{"text":[{"width":null,"height":null,"contentType":"latex","content":"\\frac{4}{3}"}],"value":"C","userSelected":false,"right":true},{"text":[{"width":null,"height":null,"contentType":"latex","content":"\\frac{4\\sqrt{2}}{3}"}],"value":"D","userSelected":false,"right":false}],"stemList":[],"imgList":[],"collection":false}]
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
         * addTime : 2019-08-08 19:00:44
         * id4ItemBank : 215
         * th : 2
         * status : false
         * msg : null
         * score : 5
         * totalSize : null
         * type : RadioSelect
         * title : null
         * rightKey : [{"width":null,"height":null,"contentType":"text","content":"D"}]
         * analysis : [{"width":null,"height":null,"contentType":"text","content":"解析：略"}]
         * stemContents : [{"width":null,"height":null,"contentType":"text","content":"投掷两粒骰子,得到其向上的点数分别为m、n,则复数(m+ni)(n-mi)为实数的概率为(　 )"}]
         * optionsList : [{"text":[{"width":null,"height":null,"contentType":"latex","content":"\\frac{1}{4}"}],"value":"A","userSelected":false,"right":false},{"text":[{"width":null,"height":null,"contentType":"latex","content":"\\frac{1}{3}"}],"value":"B","userSelected":true,"right":false},{"text":[{"width":null,"height":null,"contentType":"latex","content":"\\frac{1}{12}"}],"value":"C","userSelected":false,"right":false},{"text":[{"width":null,"height":null,"contentType":"latex","content":"\\frac{1}{6}"}],"value":"D","userSelected":false,"right":true}]
         * stemList : []
         * imgList : []
         * collection : true
         */

        private String addTime;
        private int id4ItemBank;
        private int th;
        private String status;
        private Object msg;
        private String score;
        private Object totalSize;
        private String type;
        private Object title;
        private boolean collection;
        private List<RightKeyBean> rightKey;
        private List<AnalysisBean> analysis;
        private List<StemContentsBean> stemContents;
        private List<OptionsListBean> optionsList;
        private List<StemListBean> stemList;
        private List<String> imgList;

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public int getId4ItemBank() {
            return id4ItemBank;
        }

        public void setId4ItemBank(int id4ItemBank) {
            this.id4ItemBank = id4ItemBank;
        }

        public int getTh() {
            return th;
        }

        public void setTh(int th) {
            this.th = th;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getMsg() {
            return msg;
        }

        public void setMsg(Object msg) {
            this.msg = msg;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public Object getTotalSize() {
            return totalSize;
        }

        public void setTotalSize(Object totalSize) {
            this.totalSize = totalSize;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getTitle() {
            return title;
        }

        public void setTitle(Object title) {
            this.title = title;
        }

        public boolean isCollection() {
            return collection;
        }

        public void setCollection(boolean collection) {
            this.collection = collection;
        }

        public List<RightKeyBean> getRightKey() {
            return rightKey;
        }

        public void setRightKey(List<RightKeyBean> rightKey) {
            this.rightKey = rightKey;
        }

        public List<AnalysisBean> getAnalysis() {
            return analysis;
        }

        public void setAnalysis(List<AnalysisBean> analysis) {
            this.analysis = analysis;
        }

        public List<StemContentsBean> getStemContents() {
            return stemContents;
        }

        public void setStemContents(List<StemContentsBean> stemContents) {
            this.stemContents = stemContents;
        }

        public List<OptionsListBean> getOptionsList() {
            return optionsList;
        }

        public void setOptionsList(List<OptionsListBean> optionsList) {
            this.optionsList = optionsList;
        }

        public List<StemListBean> getStemList() {
            return stemList;
        }

        public void setStemList(List<StemListBean> stemList) {
            this.stemList = stemList;
        }

        public List<String> getImgList() {
            return imgList;
        }

        public void setImgList(List<String> imgList) {
            this.imgList = imgList;
        }

        public static class StemListBean implements Serializable{
            /**
             * addTime : null
             * id4ItemBank : null
             * th : 1
             * status : true
             * msg : 本题的正确答案是:A,您的答案是:A,回答正确
             * score : null
             * totalSize : null
             * type : RadioSelect
             * title : null
             * rightKey : [{"width":null,"height":null,"contentType":"text","content":"A"}]
             * analysis : [{"width":null,"height":null,"contentType":"text","content":"这个解析呢，是没有滴，我也不会做啊"}]
             * stemContents : [{"width":null,"height":null,"contentType":"text","content":"Autumn blues is also called autumn _______."}]
             * optionsList : [{"text":[{"width":null,"height":null,"contentType":"text","content":"depression "}],"value":"A","userSelected":true,"right":true},{"text":[{"width":null,"height":null,"contentType":"text","content":"sunshine "}],"value":"B","userSelected":false,"right":false},{"text":[{"width":null,"height":null,"contentType":"text","content":"blue music"}],"value":"C","userSelected":false,"right":false},{"text":[{"width":null,"height":null,"contentType":"text","content":"cold winds"}],"value":"D","userSelected":false,"right":false}]
             * stemList : []
             * imgList : []
             * collection : false
             */

            private Object addTime;
            private Object id4ItemBank;
            private int th;
            private String status;
            private String msg;
            private String score;
            private Object totalSize;
            private String type;
            private Object title;
            private boolean collection;
            private List<ResultBean.StemListBean.RightKeyBean> rightKey;
            private List<ResultBean.StemListBean.AnalysisBean> analysis;
            private List<ResultBean.StemListBean.StemContentsBeanX> stemContents;
            private List<ResultBean.OptionsListBean> optionsList;
            private List<?> stemList;
            private List<String> imgList;

            public Object getAddTime() {
                return addTime;
            }

            public void setAddTime(Object addTime) {
                this.addTime = addTime;
            }

            public Object getId4ItemBank() {
                return id4ItemBank;
            }

            public void setId4ItemBank(Object id4ItemBank) {
                this.id4ItemBank = id4ItemBank;
            }

            public int getTh() {
                return th;
            }

            public void setTh(int th) {
                this.th = th;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getMsg() {
                return msg;
            }

            public void setMsg(String msg) {
                this.msg = msg;
            }

            public Object getScore() {
                return score;
            }

            public void setScore(String score) {
                this.score = score;
            }

            public Object getTotalSize() {
                return totalSize;
            }

            public void setTotalSize(Object totalSize) {
                this.totalSize = totalSize;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public Object getTitle() {
                return title;
            }

            public void setTitle(Object title) {
                this.title = title;
            }

            public boolean isCollection() {
                return collection;
            }

            public void setCollection(boolean collection) {
                this.collection = collection;
            }

            public List<ResultBean.StemListBean.RightKeyBean> getRightKey() {
                return rightKey;
            }

            public void setRightKey(List<ResultBean.StemListBean.RightKeyBean> rightKey) {
                this.rightKey = rightKey;
            }

            public List<ResultBean.StemListBean.AnalysisBean> getAnalysis() {
                return analysis;
            }

            public void setAnalysis(List<ResultBean.StemListBean.AnalysisBean> analysis) {
                this.analysis = analysis;
            }

            public List<ResultBean.StemListBean.StemContentsBeanX> getStemContents() {
                return stemContents;
            }

            public void setStemContents(List<ResultBean.StemListBean.StemContentsBeanX> stemContents) {
                this.stemContents = stemContents;
            }

            public List<ResultBean.OptionsListBean> getOptionsList() {
                return optionsList;
            }

            public void setOptionsList(List<ResultBean.OptionsListBean> optionsList) {
                this.optionsList = optionsList;
            }

            public List<?> getStemList() {
                return stemList;
            }

            public void setStemList(List<?> stemList) {
                this.stemList = stemList;
            }

            public List<String> getImgList() {
                return imgList;
            }

            public void setImgList(List<String> imgList) {
                this.imgList = imgList;
            }

            public static class RightKeyBean implements Serializable{
                /**
                 * width : null
                 * height : null
                 * contentType : text
                 * content : A
                 */

                private Object width;
                private Object height;
                private String contentType;
                private String content;

                public Object getWidth() {
                    return width;
                }

                public void setWidth(Object width) {
                    this.width = width;
                }

                public Object getHeight() {
                    return height;
                }

                public void setHeight(Object height) {
                    this.height = height;
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

            public static class AnalysisBean implements Serializable{
                /**
                 * width : null
                 * height : null
                 * contentType : text
                 * content : 这个解析呢，是没有滴，我也不会做啊
                 */

                private String width;
                private String height;
                private String contentType;
                private String content;

                public String getWidth() {
                    return width;
                }

                public void setWidth(String width) {
                    this.width = width;
                }

                public String getHeight() {
                    return height;
                }

                public void setHeight(String height) {
                    this.height = height;
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

            public static class StemContentsBeanX implements Serializable{
                /**
                 * width : null
                 * height : null
                 * contentType : text
                 * content : Autumn blues is also called autumn _______.
                 */

                private String width;
                private String height;
                private String contentType;
                private String content;

                public String getWidth() {
                    return width;
                }

                public void setWidth(String width) {
                    this.width = width;
                }

                public String getHeight() {
                    return height;
                }

                public void setHeight(String height) {
                    this.height = height;
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

            public static class OptionsListBean implements Serializable{
                /**
                 * text : [{"width":null,"height":null,"contentType":"text","content":"depression "}]
                 * value : A
                 * userSelected : true
                 * right : true
                 */

                private String value;
                private boolean userSelected;
                private boolean right;
                private List<OptionsListBean.TextBean> text;

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public boolean isUserSelected() {
                    return userSelected;
                }

                public void setUserSelected(boolean userSelected) {
                    this.userSelected = userSelected;
                }

                public boolean isRight() {
                    return right;
                }

                public void setRight(boolean right) {
                    this.right = right;
                }

                public List<ResultBean.StemListBean.OptionsListBean.TextBean> getText() {
                    return text;
                }

                public void setText(List<ResultBean.StemListBean.OptionsListBean.TextBean> text) {
                    this.text = text;
                }

                public static class TextBean implements Serializable{
                    /**
                     * width : null
                     * height : null
                     * contentType : text
                     * content : depression
                     */

                    private Object width;
                    private Object height;
                    private String contentType;
                    private String content;

                    public Object getWidth() {
                        return width;
                    }

                    public void setWidth(Object width) {
                        this.width = width;
                    }

                    public Object getHeight() {
                        return height;
                    }

                    public void setHeight(Object height) {
                        this.height = height;
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
        }
        public static class RightKeyBean implements Serializable{
            /**
             * width : null
             * height : null
             * contentType : text
             * content : D
             */

            private Object width;
            private Object height;
            private String contentType;
            private String content;

            public Object getWidth() {
                return width;
            }

            public void setWidth(Object width) {
                this.width = width;
            }

            public Object getHeight() {
                return height;
            }

            public void setHeight(Object height) {
                this.height = height;
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

        public static class AnalysisBean implements Serializable {
            /**
             * width : null
             * height : null
             * contentType : text
             * content : 解析：略
             */

            private String width;
            private String height;
            private String contentType;
            private String content;

            public String getWidth() {
                return width;
            }

            public void setWidth(String width) {
                this.width = width;
            }

            public String getHeight() {
                return height;
            }

            public void setHeight(String height) {
                this.height = height;
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

        public static class StemContentsBean implements Serializable{
            /**
             * width : null
             * height : null
             * contentType : text
             * content : 投掷两粒骰子,得到其向上的点数分别为m、n,则复数(m+ni)(n-mi)为实数的概率为(　 )
             */

            private String width;
            private String height;
            private String contentType;
            private String content;

            public String getWidth() {
                return width;
            }

            public void setWidth(String width) {
                this.width = width;
            }

            public String getHeight() {
                return height;
            }

            public void setHeight(String height) {
                this.height = height;
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

        public static class OptionsListBean implements Serializable{
            /**
             * text : [{"width":null,"height":null,"contentType":"latex","content":"\\frac{1}{4}"}]
             * value : A
             * userSelected : false
             * right : false
             */

            private String value;
            private boolean userSelected;
            private boolean right;
            private List<TextBean> text;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public boolean isUserSelected() {
                return userSelected;
            }

            public void setUserSelected(boolean userSelected) {
                this.userSelected = userSelected;
            }

            public boolean isRight() {
                return right;
            }

            public void setRight(boolean right) {
                this.right = right;
            }

            public List<TextBean> getText() {
                return text;
            }

            public void setText(List<TextBean> text) {
                this.text = text;
            }

            public static class TextBean  implements Serializable{
                /**
                 * width : null
                 * height : null
                 * contentType : latex
                 * content : \frac{1}{4}
                 */

                private String width;
                private String height;
                private String contentType;
                private String content;

                public String getWidth() {
                    return width;
                }

                public void setWidth(String width) {
                    this.width = width;
                }

                public String getHeight() {
                    return height;
                }

                public void setHeight(String height) {
                    this.height = height;
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
    }
}
