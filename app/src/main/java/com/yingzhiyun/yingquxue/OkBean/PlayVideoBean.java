package com.yingzhiyun.yingquxue.OkBean;

public class PlayVideoBean {
    /**
     * status : 200
     * hint : null
     * result : {"videoUrl":"https://126.cdn-vod.huaweicloud.com/asset/e316b42e824df58a498bcc56ff48678b/play_video/8c48f9988c3e18b001031b2383115da2_1.m3u8?auth_info=IKEMA3yVHTvmjJq6ANwxkC1ibC1aVjMOjYpe8iHa41VLlZErhTL2nbz6hBKBK6ChA/GqPCsxfnaRNplqzE32/7EIjCN8odQW4HxFkRr2CEyhavNgZ5HB6vEM+aX1Js48GYpzUGY9p40Rgw3Sy11BrQtKEccPCE64fHA4sXb3sYch0YWBK5uyzVwS+heeVJMq.efa0401664fcb60f2ca6ba62d6961815"}
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
         * videoUrl : https://126.cdn-vod.huaweicloud.com/asset/e316b42e824df58a498bcc56ff48678b/play_video/8c48f9988c3e18b001031b2383115da2_1.m3u8?auth_info=IKEMA3yVHTvmjJq6ANwxkC1ibC1aVjMOjYpe8iHa41VLlZErhTL2nbz6hBKBK6ChA/GqPCsxfnaRNplqzE32/7EIjCN8odQW4HxFkRr2CEyhavNgZ5HB6vEM+aX1Js48GYpzUGY9p40Rgw3Sy11BrQtKEccPCE64fHA4sXb3sYch0YWBK5uyzVwS+heeVJMq.efa0401664fcb60f2ca6ba62d6961815
         */

        private String videoUrl;

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }
    }
}
