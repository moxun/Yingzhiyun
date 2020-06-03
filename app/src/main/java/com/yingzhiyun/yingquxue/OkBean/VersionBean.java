package com.yingzhiyun.yingquxue.OkBean;

public class VersionBean {

    /**
     * status : 200
     * hint : null
     * result : {"can":0,"downloadPath":"http://www.guanjiumaoyi/appDownload/yzy-1.0.0.apk","description":"1、修复bug\\r\\n2、优化haha体验","version":"2.2.3","isForced":1}
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
         * can : 0
         * downloadPath : http://www.guanjiumaoyi/appDownload/yzy-1.0.0.apk
         * description : 1、修复bug\r\n2、优化haha体验
         * version : 2.2.3
         * isForced : 1
         */

        private int can;
        private String downloadPath;
        private String description;
        private String version;
        private int isForced;

        public int getCan() {
            return can;
        }

        public void setCan(int can) {
            this.can = can;
        }

        public String getDownloadPath() {
            return downloadPath;
        }

        public void setDownloadPath(String downloadPath) {
            this.downloadPath = downloadPath;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public int getIsForced() {
            return isForced;
        }

        public void setIsForced(int isForced) {
            this.isForced = isForced;
        }
    }
}
