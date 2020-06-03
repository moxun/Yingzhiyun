package com.yingzhiyun.yingquxue.OkBean;

import java.util.List;

public class LeftBean {


    /**
     * status : 200
     * hint : null
     * result : {"detail":[{"img_url":"http://192.168.0.120/yzyFiles/icons/7f498a84-8736-4e28-aa16-af5be511d42c1578994637723.png","id":35,"title":"高三复习专辑","type":"folderList","read_volume":1270,"add_time":"2019-09-03"},{"img_url":"http://192.168.0.120/yzyFiles/icons/afe896d3-ebb8-4565-b5b9-eaacf8e74ff51578994661348.png","id":34,"title":"名校校本资料","type":"folderList","read_volume":914,"add_time":"2019-09-03"},{"img_url":"http://192.168.0.120/yzyFiles/icons/3670ef58-ac7a-4e71-acc6-f301a5126ce91578994654973.png","id":33,"title":"奥赛真题汇编","type":"folderList","read_volume":518,"add_time":"2019-09-03"},{"img_url":"http://192.168.0.120/yzyFiles/icons/6edc92ff-1bff-4fef-8e8d-e28a81feeeb91585021859833.png","id":345,"title":"必刷题","type":"mustDo","read_volume":67,"add_time":"2020-03-24"},{"img_url":"http://192.168.0.120/yzyFiles/icons/748ed676-a57a-42e3-9512-a77d62c8787b1578994646817.png","id":32,"title":"高考真题模拟","type":"folderList","read_volume":1764,"add_time":"2019-09-03"},{"img_url":"http://192.168.0.120/yzyFiles/icons/6f4dd4f8-9cf7-4d2e-aeb2-0d97806e1618-1567502591326.png","id":36,"title":"精选中招真题演练","type":"folderList","read_volume":428,"add_time":"2019-09-03"}],"title":"随时随地练"}
     */

    private int status;
    private Object hint;
    private ResultBean result;

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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * detail : [{"img_url":"http://192.168.0.120/yzyFiles/icons/7f498a84-8736-4e28-aa16-af5be511d42c1578994637723.png","id":35,"title":"高三复习专辑","type":"folderList","read_volume":1270,"add_time":"2019-09-03"},{"img_url":"http://192.168.0.120/yzyFiles/icons/afe896d3-ebb8-4565-b5b9-eaacf8e74ff51578994661348.png","id":34,"title":"名校校本资料","type":"folderList","read_volume":914,"add_time":"2019-09-03"},{"img_url":"http://192.168.0.120/yzyFiles/icons/3670ef58-ac7a-4e71-acc6-f301a5126ce91578994654973.png","id":33,"title":"奥赛真题汇编","type":"folderList","read_volume":518,"add_time":"2019-09-03"},{"img_url":"http://192.168.0.120/yzyFiles/icons/6edc92ff-1bff-4fef-8e8d-e28a81feeeb91585021859833.png","id":345,"title":"必刷题","type":"mustDo","read_volume":67,"add_time":"2020-03-24"},{"img_url":"http://192.168.0.120/yzyFiles/icons/748ed676-a57a-42e3-9512-a77d62c8787b1578994646817.png","id":32,"title":"高考真题模拟","type":"folderList","read_volume":1764,"add_time":"2019-09-03"},{"img_url":"http://192.168.0.120/yzyFiles/icons/6f4dd4f8-9cf7-4d2e-aeb2-0d97806e1618-1567502591326.png","id":36,"title":"精选中招真题演练","type":"folderList","read_volume":428,"add_time":"2019-09-03"}]
         * title : 随时随地练
         */

        private String title;
        private List<HomePagerBean.ResultBean.LeftBean.DetailBean> detail;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<HomePagerBean.ResultBean.LeftBean.DetailBean> getDetail() {
            return detail;
        }

        public void setDetail(List<HomePagerBean.ResultBean.LeftBean.DetailBean> detail) {
            this.detail = detail;
        }


    }
}
