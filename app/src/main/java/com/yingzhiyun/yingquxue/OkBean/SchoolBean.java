package com.yingzhiyun.yingquxue.OkBean;

import java.io.Serializable;
import java.util.List;

public class SchoolBean implements Serializable {

    /**
     * status : 200
     * hint : null
     * result : [{"id":71,"name":"沁阳市第一中学","address":"河南省焦作市"},{"id":72,"name":"河南省武陡县第一中学","address":"河南省焦作市"},{"id":74,"name":"温县第一高级中学","address":"河南省焦作市"},{"id":75,"name":"济源市第一中学","address":"河南省焦作市"},{"id":76,"name":"焦作市第一中学","address":"河南省焦作市"},{"id":77,"name":"博爱县第一中学","address":"河南省焦作市"},{"id":79,"name":"修武县第一中学","address":"河南省焦作市"},{"id":80,"name":"河南省孟州市第一高级中学","address":"河南省焦作市"},{"id":89,"name":"开封市第一中学","address":"河南省开封市"},{"id":92,"name":"通许县第一高级中学","address":"河南省开封市"},{"id":93,"name":"开封县第一高级中学","address":"河南省开封市"},{"id":98,"name":"河南省卢氏县第一高级中学","address":"河南省三门峡市"},{"id":102,"name":"河南省灵宝市第一高级中学","address":"河南省三门峡市"},{"id":103,"name":"三门峡市第一高级中学","address":"河南省三门峡市"},{"id":105,"name":"三门峡市第一中学","address":"河南省三门峡市"},{"id":108,"name":"临颍县第一高级中学","address":"河南省漯河市"},{"id":110,"name":"舞阳县第一高级中学","address":"河南省漯河市"},{"id":113,"name":"舞钢市第一高级中学","address":"河南省漯河市"},{"id":114,"name":"河南省长垣县第一中学","address":"河南省新乡市"},{"id":116,"name":"原阳县第一高级中学","address":"河南省新乡市"},{"id":118,"name":"新乡市第一中学","address":"河南省新乡市"},{"id":119,"name":"延津县第一高级中学","address":"河南省新乡市"},{"id":120,"name":"河南省新乡市第一中学","address":"河南省新乡市"},{"id":122,"name":"获嘉县第一中学","address":"河南省新乡市"},{"id":124,"name":"河南省封丘县第一中学","address":"河南省新乡市"},{"id":125,"name":"卫辉市第一中学","address":"河南省新乡市"},{"id":126,"name":"河南省新乡县第一中学","address":"河南省新乡市"},{"id":130,"name":"郏县第一高级中学","address":"河南省平顶山市"},{"id":132,"name":"汝州市第一高级中学","address":"河南省平顶山市"},{"id":133,"name":"鲁山县第一高级中学","address":"河南省平顶山市"},{"id":134,"name":"平顶山市第一高级中学","address":"河南省平顶山市"},{"id":136,"name":"平顶山市第一中学","address":"河南省平顶山市"},{"id":137,"name":"宝丰县第一高级中学","address":"河南省平顶山市"},{"id":166,"name":"确山县第一高级中学","address":"河南省驻马店市"},{"id":168,"name":"平舆县第一高级中学","address":"河南省驻马店市"},{"id":169,"name":"河南省遂平县第一高级中学","address":"河南省驻马店市"},{"id":170,"name":"沁阳县第一高级中学","address":"河南省驻马店市"},{"id":172,"name":"新蔡县第一高级中学","address":"河南省驻马店市"},{"id":173,"name":"正阳县第一高级中学","address":"河南省驻马店市"},{"id":174,"name":"上蔡县第一高级中学","address":"河南省驻马店市"},{"id":175,"name":"驻马店市第一高级中学","address":"河南省驻马店市"},{"id":182,"name":"安阳县第一高级中学","address":"河南省安阳市"},{"id":183,"name":"安阳市第一中学","address":"河南省安阳市"},{"id":184,"name":"滑县第一高级中学","address":"河南省安阳市"},{"id":185,"name":"林州市第一中学","address":"河南省安阳市"},{"id":189,"name":"汤阴县第一中学北校区","address":"河南省安阳市"},{"id":191,"name":"河南省安阳市内黄县第一高级中学","address":"河南省安阳市"},{"id":193,"name":"河南省安阳市内黄县第一高级中学分校","address":"河南省安阳市"},{"id":195,"name":"内黄县第一实验高中","address":"河南省安阳市"},{"id":199,"name":"商丘市第一高级中学","address":"河南省商丘市"},{"id":209,"name":"夏邑县第一高级中学","address":"河南省商丘市"},{"id":212,"name":"濮阳市第一高级中学","address":"河南省濮阳市"},{"id":213,"name":"濮阳市油田第一中学","address":"河南省濮阳市"},{"id":216,"name":"濮阳县第一中学","address":"河南省濮阳市"},{"id":217,"name":"清丰县第一高级中学","address":"河南省濮阳市"},{"id":218,"name":"南乐县第一高级中学","address":"河南省濮阳市"},{"id":222,"name":"襄城县第一高级中学","address":"河南省许昌市"},{"id":226,"name":"长葛市第一高级中学","address":"河南省许昌市"},{"id":227,"name":"许昌县第一高级中学","address":"河南省许昌市"},{"id":231,"name":"禹州市第一高级中学","address":"河南省许昌市"},{"id":242,"name":"浚县第一中学","address":"河南省鹤壁市"},{"id":244,"name":"鹤壁市第一中学","address":"河南省鹤壁市"}]
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

    public static class ResultBean implements Serializable {
        /**
         * id : 71
         * name : 沁阳市第一中学
         * address : 河南省焦作市
         */

        private int id;
        private String name;
        private String address;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
