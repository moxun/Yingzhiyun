package com.yingzhiyun.yingquxue.OkBean;

import java.util.List;

public class BashiinfoBean {


    /**
     * status : 200
     * hint : null
     * result : {"isCollection":false,"detail":[{"name":"语文","subjectDetail":[{"add_time":"2019-09-29","title":"八市重点高中联盟2020届高三9月\u201c领军考试\u201d语文试题&答案","id":8453,"file_path":"http://192.168.0.120/yzy/app/courseWare/8453/courseWare/362fb7a5-3707-464f-af28-38e5f0bbcd0a-1569723990840.pdf","file_size":"1.18MB","read_volume":656,"type":"pdf","img_url":null,"collection":false,"labelList":["联考","pdf","试题试卷"]}],"id":9},{"name":"数学","subjectDetail":[{"add_time":"2019-09-29","title":"八市重点高中联盟2020届高三9月\u201c领军考试\u201d文科数学试题&答案","id":8450,"file_path":"http://192.168.0.120/yzy/app/courseWare/8450/courseWare/998ff91d-b1ec-4f97-92f8-62a95423d7a0-1569723911262.pdf","file_size":"0.57MB","read_volume":347,"type":"pdf","img_url":null,"collection":false,"labelList":["联考","pdf","试题试卷"]},{"add_time":"2019-09-29","title":"八市重点高中联盟2020届高三9月\u201c领军考试\u201d理科数学试题&答案","id":8447,"file_path":"http://192.168.0.120/yzy/app/courseWare/8447/courseWare/7f2fe6d9-2082-44f6-bd9d-9a516e00a105-1569723821528.pdf","file_size":"0.64MB","read_volume":383,"type":"pdf","img_url":null,"collection":false,"labelList":["联考","pdf","试题试卷"]}],"id":6},{"name":"英语","subjectDetail":[{"add_time":"2019-09-29","title":"八市重点高中联盟2020届高三9月\u201c领军考试\u201d英语试题&答案","id":8452,"file_path":"http://192.168.0.120/yzy/app/courseWare/8452/courseWare/1b547d6d-69b1-4a1b-b10b-78433d8d7b9c-1569723972043.pdf","file_size":"0.76MB","read_volume":234,"type":"pdf","img_url":null,"collection":false,"labelList":["联考","pdf","试题试卷"]}],"id":7},{"name":"物理","subjectDetail":[{"add_time":"2019-09-29","title":"八市重点高中联盟2020届高三9月\u201c领军考试\u201d物理试题&答案","id":8451,"file_path":"http://192.168.0.120/yzy/app/courseWare/8451/courseWare/825fa974-d3eb-4467-8aa9-749dc4e6ee4e-1569723944434.pdf","file_size":"0.87MB","read_volume":205,"type":"pdf","img_url":null,"collection":false,"labelList":["联考","pdf","试题试卷"]}],"id":10},{"name":"化学","subjectDetail":[{"add_time":"2019-09-29","title":"八市重点高中联盟2020届高三9月\u201c领军考试\u201d化学试题&答案","id":8446,"file_path":"http://192.168.0.120/yzy/app/courseWare/8446/courseWare/e650b131-f923-4f3a-a5d9-e24c645d9074-1569723799293.pdf","file_size":"0.86MB","read_volume":197,"type":"pdf","img_url":null,"collection":false,"labelList":["联考","pdf","试题试卷"]}],"id":11},{"name":"生物","subjectDetail":[{"add_time":"2019-09-29","title":"八市重点高中联盟2020届高三9月\u201c领军考试\u201d生物试题&答案","id":8449,"file_path":"http://192.168.0.120/yzy/app/courseWare/8449/courseWare/68574e7a-541e-41e1-b7f7-9ee17ffe36e3-1569723884934.pdf","file_size":"0.94MB","read_volume":210,"type":"pdf","img_url":null,"collection":false,"labelList":["联考","pdf","试题试卷"]}],"id":12},{"name":"政治","subjectDetail":[{"add_time":"2019-09-29","title":"八市重点高中联盟2020届高三9月\u201c领军考试\u201d政治试题&答案","id":8454,"file_path":"http://192.168.0.120/yzy/app/courseWare/8454/courseWare/8405a1dd-d4d6-4ad4-a29b-96f68e55164c-1569724011418.pdf","file_size":"0.68MB","read_volume":91,"type":"pdf","img_url":null,"collection":false,"labelList":["联考","pdf","试题试卷"]}],"id":13},{"name":"历史","subjectDetail":[{"add_time":"2019-09-29","title":"八市重点高中联盟2020届高三9月\u201c领军考试\u201d历史试题&答案","id":8448,"file_path":"http://192.168.0.120/yzy/app/courseWare/8448/courseWare/8ecb30a2-490f-4fa5-affc-ab3e42871b46-1569723846231.pdf","file_size":"1.01MB","read_volume":92,"type":"pdf","img_url":null,"collection":false,"labelList":["联考","pdf","试题试卷"]}],"id":14},{"name":"地理","subjectDetail":[{"add_time":"2019-09-29","title":"八市重点高中联盟2020届高三9月\u201c领军考试\u201d地理试题&答案","id":8445,"file_path":"http://192.168.0.120/yzy/app/courseWare/8445/courseWare/1e6f6866-5a66-4ddd-8798-8f3d59db09bb-1569723777684.pdf","file_size":"1.07MB","read_volume":92,"type":"pdf","img_url":null,"collection":false,"labelList":["联考","pdf","试题试卷"]}],"id":8},{"name":"文科综合","subjectDetail":[],"id":27},{"name":"理科综合","subjectDetail":[],"id":28}]}
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
         * isCollection : false
         * detail : [{"name":"语文","subjectDetail":[{"add_time":"2019-09-29","title":"八市重点高中联盟2020届高三9月\u201c领军考试\u201d语文试题&答案","id":8453,"file_path":"http://192.168.0.120/yzy/app/courseWare/8453/courseWare/362fb7a5-3707-464f-af28-38e5f0bbcd0a-1569723990840.pdf","file_size":"1.18MB","read_volume":656,"type":"pdf","img_url":null,"collection":false,"labelList":["联考","pdf","试题试卷"]}],"id":9},{"name":"数学","subjectDetail":[{"add_time":"2019-09-29","title":"八市重点高中联盟2020届高三9月\u201c领军考试\u201d文科数学试题&答案","id":8450,"file_path":"http://192.168.0.120/yzy/app/courseWare/8450/courseWare/998ff91d-b1ec-4f97-92f8-62a95423d7a0-1569723911262.pdf","file_size":"0.57MB","read_volume":347,"type":"pdf","img_url":null,"collection":false,"labelList":["联考","pdf","试题试卷"]},{"add_time":"2019-09-29","title":"八市重点高中联盟2020届高三9月\u201c领军考试\u201d理科数学试题&答案","id":8447,"file_path":"http://192.168.0.120/yzy/app/courseWare/8447/courseWare/7f2fe6d9-2082-44f6-bd9d-9a516e00a105-1569723821528.pdf","file_size":"0.64MB","read_volume":383,"type":"pdf","img_url":null,"collection":false,"labelList":["联考","pdf","试题试卷"]}],"id":6},{"name":"英语","subjectDetail":[{"add_time":"2019-09-29","title":"八市重点高中联盟2020届高三9月\u201c领军考试\u201d英语试题&答案","id":8452,"file_path":"http://192.168.0.120/yzy/app/courseWare/8452/courseWare/1b547d6d-69b1-4a1b-b10b-78433d8d7b9c-1569723972043.pdf","file_size":"0.76MB","read_volume":234,"type":"pdf","img_url":null,"collection":false,"labelList":["联考","pdf","试题试卷"]}],"id":7},{"name":"物理","subjectDetail":[{"add_time":"2019-09-29","title":"八市重点高中联盟2020届高三9月\u201c领军考试\u201d物理试题&答案","id":8451,"file_path":"http://192.168.0.120/yzy/app/courseWare/8451/courseWare/825fa974-d3eb-4467-8aa9-749dc4e6ee4e-1569723944434.pdf","file_size":"0.87MB","read_volume":205,"type":"pdf","img_url":null,"collection":false,"labelList":["联考","pdf","试题试卷"]}],"id":10},{"name":"化学","subjectDetail":[{"add_time":"2019-09-29","title":"八市重点高中联盟2020届高三9月\u201c领军考试\u201d化学试题&答案","id":8446,"file_path":"http://192.168.0.120/yzy/app/courseWare/8446/courseWare/e650b131-f923-4f3a-a5d9-e24c645d9074-1569723799293.pdf","file_size":"0.86MB","read_volume":197,"type":"pdf","img_url":null,"collection":false,"labelList":["联考","pdf","试题试卷"]}],"id":11},{"name":"生物","subjectDetail":[{"add_time":"2019-09-29","title":"八市重点高中联盟2020届高三9月\u201c领军考试\u201d生物试题&答案","id":8449,"file_path":"http://192.168.0.120/yzy/app/courseWare/8449/courseWare/68574e7a-541e-41e1-b7f7-9ee17ffe36e3-1569723884934.pdf","file_size":"0.94MB","read_volume":210,"type":"pdf","img_url":null,"collection":false,"labelList":["联考","pdf","试题试卷"]}],"id":12},{"name":"政治","subjectDetail":[{"add_time":"2019-09-29","title":"八市重点高中联盟2020届高三9月\u201c领军考试\u201d政治试题&答案","id":8454,"file_path":"http://192.168.0.120/yzy/app/courseWare/8454/courseWare/8405a1dd-d4d6-4ad4-a29b-96f68e55164c-1569724011418.pdf","file_size":"0.68MB","read_volume":91,"type":"pdf","img_url":null,"collection":false,"labelList":["联考","pdf","试题试卷"]}],"id":13},{"name":"历史","subjectDetail":[{"add_time":"2019-09-29","title":"八市重点高中联盟2020届高三9月\u201c领军考试\u201d历史试题&答案","id":8448,"file_path":"http://192.168.0.120/yzy/app/courseWare/8448/courseWare/8ecb30a2-490f-4fa5-affc-ab3e42871b46-1569723846231.pdf","file_size":"1.01MB","read_volume":92,"type":"pdf","img_url":null,"collection":false,"labelList":["联考","pdf","试题试卷"]}],"id":14},{"name":"地理","subjectDetail":[{"add_time":"2019-09-29","title":"八市重点高中联盟2020届高三9月\u201c领军考试\u201d地理试题&答案","id":8445,"file_path":"http://192.168.0.120/yzy/app/courseWare/8445/courseWare/1e6f6866-5a66-4ddd-8798-8f3d59db09bb-1569723777684.pdf","file_size":"1.07MB","read_volume":92,"type":"pdf","img_url":null,"collection":false,"labelList":["联考","pdf","试题试卷"]}],"id":8},{"name":"文科综合","subjectDetail":[],"id":27},{"name":"理科综合","subjectDetail":[],"id":28}]
         */

        private boolean isCollection;
        private List<DetailBean> detail;

        public boolean isIsCollection() {
            return isCollection;
        }

        public void setIsCollection(boolean isCollection) {
            this.isCollection = isCollection;
        }

        public List<DetailBean> getDetail() {
            return detail;
        }

        public void setDetail(List<DetailBean> detail) {
            this.detail = detail;
        }

        public static class DetailBean {
            /**
             * name : 语文
             * subjectDetail : [{"add_time":"2019-09-29","title":"八市重点高中联盟2020届高三9月\u201c领军考试\u201d语文试题&答案","id":8453,"file_path":"http://192.168.0.120/yzy/app/courseWare/8453/courseWare/362fb7a5-3707-464f-af28-38e5f0bbcd0a-1569723990840.pdf","file_size":"1.18MB","read_volume":656,"type":"pdf","img_url":null,"collection":false,"labelList":["联考","pdf","试题试卷"]}]
             * id : 9
             */

            private String name;

            public List<ZiyuanBean.ResultBean> getDetail() {
                return detail;
            }

            public void setDetail(List<ZiyuanBean.ResultBean> detail) {
                this.detail = detail;
            }

            private int id;
            private List<ZiyuanBean.ResultBean> subjectDetail;
            private List<ZiyuanBean.ResultBean> detail;
            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public List<ZiyuanBean.ResultBean> getSubjectDetail() {
                return subjectDetail;
            }

            public void setSubjectDetail(List<ZiyuanBean.ResultBean> subjectDetail) {
                this.subjectDetail = subjectDetail;
            }

            public static class SubjectDetailBean {
                /**
                 * add_time : 2019-09-29
                 * title : 八市重点高中联盟2020届高三9月“领军考试”语文试题&答案
                 * id : 8453
                 * file_path : http://192.168.0.120/yzy/app/courseWare/8453/courseWare/362fb7a5-3707-464f-af28-38e5f0bbcd0a-1569723990840.pdf
                 * file_size : 1.18MB
                 * read_volume : 656
                 * type : pdf
                 * img_url : null
                 * collection : false
                 * labelList : ["联考","pdf","试题试卷"]
                 */

                private String add_time;
                private String title;
                private int id;
                private String file_path;
                private String file_size;
                private int read_volume;
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

                public int getRead_volume() {
                    return read_volume;
                }

                public void setRead_volume(int read_volume) {
                    this.read_volume = read_volume;
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
}
