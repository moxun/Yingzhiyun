package com.yingzhiyun.yingquxue.OkBean;

public class BookinfoBean {

    /**
     * status : 200
     * hint : null
     * result : {"file_path":"http://192.168.0.120/yzy/app/courseWare/dedf3cc1-cd44-473b-885a-09e8211db278-1580808843623.pdf","title":"高一物理必修1"}
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
         * file_path : http://192.168.0.120/yzy/app/courseWare/dedf3cc1-cd44-473b-885a-09e8211db278-1580808843623.pdf
         * title : 高一物理必修1
         */

        private String file_path;
        private String title;

        public String getFile_path() {
            return file_path;
        }

        public void setFile_path(String file_path) {
            this.file_path = file_path;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
