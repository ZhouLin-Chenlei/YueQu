package com.community.yuequ.util;


import com.community.yuequ.modle.RProgramDetail;

import java.util.List;

public class HtmlUtil
{

    //css样式,隐藏header
    private static final String HIDE_HEADER_STYLE = "<style>div.headline{display:none;}</style>";

    //css style tag,需要格式化
    private static final String NEEDED_FORMAT_CSS_TAG = "<link rel=\"stylesheet\" type=\"text/css\" href=\"%s\"/>";

    // js script tag,需要格式化
    private static final String NEEDED_FORMAT_JS_TAG = "<script src=\"%s\"></script>";

    public static final String MIME_TYPE = "text/html; charset=utf-8";

    public static final String ENCODING = "utf-8";

    private HtmlUtil()
    {

    }

    /**
     * 根据css链接生成Link标签
     *
     * @param url String
     * @return String
     */
    public static String createCssTag(String url)
    {

        return String.format(NEEDED_FORMAT_CSS_TAG, url);
    }

    /**
     * 根据多个css链接生成Link标签
     *
     * @param urls List<String>
     * @return String
     */
    public static String createCssTag(List<String> urls)
    {

        final StringBuilder sb = new StringBuilder();
        for (String url : urls)
        {
            sb.append(createCssTag(url));
        }
        return sb.toString();
    }

    /**
     * 根据js链接生成Script标签
     *
     * @param url String
     * @return String
     */
    public static String createJsTag(String url)
    {

        return String.format(NEEDED_FORMAT_JS_TAG, url);
    }

    /**
     * 根据多个js链接生成Script标签
     *
     * @param urls List<String>
     * @return String
     */
    public static String createJsTag(List<String> urls)
    {

        final StringBuilder sb = new StringBuilder();
        for (String url : urls)
        {
            sb.append(createJsTag(url));
        }
        return sb.toString();
    }

    /**
     * 根据样式标签,html字符串,js标签
     * 生成完整的HTML文档
     *
     * @param html string
     * @param css  string
     * @param js   string
     * @return string
     */
    private static String createHtmlData(String html, String css, String js)
    {

        return css.concat(HIDE_HEADER_STYLE).concat(html).concat(js);
    }

    /**
     * 生成完整的HTML文档
     *
     * @return String
     */
    public static String createHtmlData(RProgramDetail detail)
    {

        final String css = HtmlUtil.createCssTag(detail.getCss());
        final String js = HtmlUtil.createJsTag(detail.getJs());
        return createHtmlData(detail.getBody(), css, js)/*htmlText*/;
    }
    static String htmlText = "<link rel=\"stylesheet\" type=\"text/css\" href=\"http://news-at.zhihu.com/css/news_qa.auto.css?v=4b3e3\"/><style>div.headline{display:none;}</style><div class=\"main-wrap content-wrap\">\n" +
            "<div class=\"headline\">\n" +
            "\n" +
            "<div class=\"img-place-holder\"></div>\n" +
            "\n" +
            "\n" +
            "\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"content-inner\">\n" +
            "\n" +
            "\n" +
            "\n" +
            "<div class=\"question\">\n" +
            "<h2 class=\"question-title\">以我所有 面对世界</h2>\n" +
            "<div class=\"answer\">\n" +
            "\n" +
            "<div class=\"meta\">\n" +
            "<img class=\"avatar\" src=\"http://pic3.zhimg.com/7151cadb708671e84de7873640301d52_is.jpg\">\n" +
            "<span class=\"author\">丁钰，</span><span class=\"bio\">要看日剧戳我专栏</span>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"content\">\n" +
            "<p>《百元之恋》是那种看的时候蛮爽，回忆的时候更爽的片子。</p>\n" +
            "<p>不是简单的看到废柴主人公逆袭成为拳击手的逆转爽感，也不是运动选手克服种种障碍终于站到了赛场的励志爽感，并不是这样，30 多岁的一子，没有热血激情，没有亮晶晶的眼神，她不是一个励志人物，周身弥漫着的气息，更接近于对生活的沉默妥协。故事里有许多让人咂舌的情节，现实感里带着冷漠荒谬，却若无其事地一个个展开，展开，展开，一子始终是冷淡的，站在自己的圈里，那双缺乏生机的双眼，除了在面对爱情和拳击时情绪涌动，其余时候几乎毫无波动。</p>\n" +
            "<p>一子自己说过，她是只值一百日元的女人，她已经 32 岁，周围的这个世界就是这样了，她的人生就是这样了，只值一百日元。和家人的关系，打工的便利店，遇到的人，追求的爱情，都是一团稀烂。一子毫不挣扎地接受着一切，不断地失去重要的东西，最后她发现有一样东西也许不会失去，那就是拳击。</p>\n" +
            "<p><img class=\"content-image\" src=\"https://pic2.zhimg.com/b0af7d4728acb114635f07a0ab913255_b.jpg\" alt=\"\" /></p>\n" +
            "<p>因为练习拳击，一子渐渐发生了改变，她仍然没有变成典型的励志人物，没有浑身洋溢着四射的激情，没有闪耀的梦想追求&hellip;&hellip;她只是变得结实、沉稳、坚定，对生活有了自己的掌控力。这种力是内在的，从生活里磨练出来的，比励志人物的热血激情更有能量。看完以后的很多天，我心里都像燃着一小撮火苗似的，回想着一子疯狂练习的模样。一子这样的人为什么能站到拳击的赛场上，好像很不可思议，又好像很理所当然。相对于生活现实的残酷，看似残酷的拳击反而更公平些，坚持练习，练习，练习，最后总有希望站在赛场上挥舞拳头，在比赛结束时和对手互相拥抱拍肩。</p>\n" +
            "<p>但现实毕竟是现实，光是为了能站在拳击场上，已经用尽了一子全部的心力。比赛开始了，又是一轮残酷的吊打，显而易见，一子虽然很努力，但很难赢得比赛。这场比赛确实打得很精彩，很燃，但也让我慢慢恢复了清醒，豪情减退，回到生活。这场比赛是生活对一子拼尽全力的总结，她自己也很清楚，努力不代表赢，成功看实力不看血汗。&ldquo;我想赢&hellip;&hellip;&rdquo;她哭着说出这句话，其中饱含了无力与辛酸。看透了人生的残酷，只是不甘心。毕竟胜利的滋味&hellip;&hellip;是那么的好。</p>\n" +
            "<p><img class=\"content-image\" src=\"https://pic2.zhimg.com/f34356a5b6ed1fb7f8c77b9f3401a901_b.jpg\" alt=\"\" /></p>\n" +
            "<p>即使付出 100%的汗水，也还是有跨越不去的鸿沟，这是我们身为普通人的无奈，无法克服，就只能面对。我们掌控不了很多东西，甚至无法控制自己，我们时常有心无力，身不由己。时间是座大钟而我们都是被推动的齿轮，我们能改变的很少，最容易也最难改变的是自己。</p>\n" +
            "<p>有时觉得人活着挺累的，有太多撼动不了的障碍，一场风雨就能被打趴下。曾经听过一句话，努力会不会成功我不知道，但不努力一定会更差劲。当时听了觉得真残酷，后来一想，人活这一生，本无什么大事，为生活努力就是生存的意义，既来之则安之，也没有更好的办法，大多数人都是这么活的。</p>\n" +
            "<p>村上春树在谈自己跑步这件事时说，痛楚难以避免，而磨难可以选择。而我们只能凭着手头现有的东西去面对世界。</p>\n" +
            "<p>在《百元之恋》中，把主角设定为废柴大龄女，其实是一种对观众的温柔。我们大部分人的人生都比一子要好得太多了。普通人观看《百元之恋》，就好像毛姆《圣诞假日》中的中产阶级青年遇到身世悲惨的妓女，尽管为这样的故事感概万分，潜意识里还是有一段安全距离。看完了电影，隔离了影像，时间淡化，琐事分心&hellip;&hellip;心灵的颤动将被抚平，自己的生活还在继续，有序、亲切、轻松。</p>\n" +
            "<p>在看完《百元之恋》的一个月后，把这部电影推荐给你们。</p>\n" +
            "\n" +
            "<div class=\"view-more\"><a href=\"http://zhuanlan.zhihu.com/p/20126002\">查看知乎讨论</a></div>\n" +
            "\n" +
            "</div>\n" +
            "</div>\n" +
            "</div>\n" +
            "\n" +
            "\n" +
            "</div>\n" +
            "</div>";

}
