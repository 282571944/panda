package com.watermelon.luomi.panda.net;

import java.util.List;

/**
 * Created by luomi on 2016-09-19.
 */
public class NewsResponse {

    /**
     * date : 20160919
     * stories : [{"images":["http://pic1.zhimg.com/4bbae19c99cc2a0ed34c958403d4f258.jpg"],"type":0,"id":8806064,"ga_prefix":"091914","title":"酒价涨了 60%，但葡萄酒投资并不好赚钱"},{"images":["http://pic3.zhimg.com/d1e7ca1da916add1a2a912a5ae2cd342.jpg"],"type":0,"id":8808308,"ga_prefix":"091913","title":"未来社会，叫外卖会变得比自己做饭更常见吗？"},{"images":["http://pic3.zhimg.com/b446a2d6f78317ef93011b88ac519296.jpg"],"type":0,"id":8805126,"ga_prefix":"091912","title":"大误 · 你的头顶，有数字"},{"images":["http://pic3.zhimg.com/cc6c247f8e82be64dfbbf382fd5f5142.jpg"],"type":0,"id":8805100,"ga_prefix":"091911","title":"在日本，人工智能用来分拣垃圾，效果拔群"},{"images":["http://pic4.zhimg.com/262166314fce115641bfd99ae8d11a3f.jpg"],"type":0,"id":8807202,"ga_prefix":"091910","title":"都知道这本书，可演员的「自我修养」到底是什么？"},{"images":["http://pic3.zhimg.com/414f4a727c28a62f460836af003b67d2.jpg"],"type":0,"id":8806978,"ga_prefix":"091909","title":"有些食品，不使用食品添加剂反而不安全"},{"images":["http://pic4.zhimg.com/2252573af3978930967359f1eb8be9ff.jpg"],"type":0,"id":8807166,"ga_prefix":"091908","title":"微信的聊天记录是如何保存在 iOS 里的？"},{"images":["http://pic4.zhimg.com/dec30dd913200fc87bd69f192e695457.jpg"],"type":0,"id":8807183,"ga_prefix":"091907","title":"修一个开放式厨房之前，需要考虑这些优缺点"},{"images":["http://pic4.zhimg.com/be7aca41a7cbed373f7432ff66248e7f.jpg"],"type":0,"id":8805266,"ga_prefix":"091907","title":"提离职被老板加薪挽留，该怎么办？"},{"images":["http://pic3.zhimg.com/26522a1a6132994690b7c54fe4f444d2.jpg"],"type":0,"id":8806732,"ga_prefix":"091907","title":"爸妈帮忙带孩子，方便了小两口，但对老人真的好吗？"},{"images":["http://pic3.zhimg.com/a1e926535a640fe105b1b339ac794c36.jpg"],"type":0,"id":8807000,"ga_prefix":"091907","title":"读读日报 24 小时热门 TOP 5 · 为奴十一年"},{"images":["http://pic1.zhimg.com/5bc366e8b75f6538f84d84425791137c.jpg"],"type":0,"id":8805365,"ga_prefix":"091906","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"http://pic1.zhimg.com/4ae1394f47f54ed2fb7f969cec783cb8.jpg","type":0,"id":8808308,"ga_prefix":"091913","title":"未来社会，叫外卖会变得比自己做饭更常见吗？"},{"image":"http://pic1.zhimg.com/621056fba1c7145c9e57a0ebf7e63208.jpg","type":0,"id":8807000,"ga_prefix":"091907","title":"读读日报 24 小时热门 TOP 5 · 为奴十一年"},{"image":"http://pic3.zhimg.com/79dc4f78f0d37264aaf00f847b9e232e.jpg","type":0,"id":8805266,"ga_prefix":"091907","title":"提离职被老板加薪挽留，该怎么办？"},{"image":"http://pic2.zhimg.com/9202ea82470d65e41aab49ec928f3635.jpg","type":0,"id":8806732,"ga_prefix":"091907","title":"爸妈帮忙带孩子，方便了小两口，但对老人真的好吗？"},{"image":"http://pic2.zhimg.com/34afc742188aaa7707a016ffc35cdc9d.jpg","type":0,"id":8802910,"ga_prefix":"091817","title":"知乎好问题 · 有哪些好吃又健康而且适合在家做的甜品？"}]
     */

    private String date;
    /**
     * images : ["http://pic1.zhimg.com/4bbae19c99cc2a0ed34c958403d4f258.jpg"]
     * type : 0
     * id : 8806064
     * ga_prefix : 091914
     * title : 酒价涨了 60%，但葡萄酒投资并不好赚钱
     */
    private List<StoriesBean> stories;
    /**
     * image : http://pic1.zhimg.com/4ae1394f47f54ed2fb7f969cec783cb8.jpg
     * type : 0
     * id : 8808308
     * ga_prefix : 091913
     * title : 未来社会，叫外卖会变得比自己做饭更常见吗？
     */

    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private List<String> images;
        private String date;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }

    public static class TopStoriesBean {
        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private String date;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }
}
