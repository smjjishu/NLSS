package com.smj.comm.dingtalkChat;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class testcase {

    /****
     * 发送各个类型事列
     */

    private DingtalkChatbotClient client = new DingtalkChatbotClient();

    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public void testSendTextMessage() throws Exception {
        TextMessage message = new TextMessage("我就是我, 是不一样的烟火");
        SendResult result = client.send(chatbotWebhookConfig.CHATBOT_WEBHOOK, message);
        System.out.println(result);
    }


    public void testSendTextMessageWithAt() throws Exception {
        TextMessage message = new TextMessage("我就是我, 是不一样的烟火");
        ArrayList<String> atMobiles = new ArrayList<String>();
        atMobiles.add("137xxxx3310");
        message.setAtMobiles(atMobiles);

        SendResult result = client.send(chatbotWebhookConfig.CHATBOT_WEBHOOK, message);
        System.out.println(result);
    }


    public void testSendTextMessageWithAtAll() throws Exception {
        TextMessage message = new TextMessage("我就是我, 是不一样的烟火");
        message.setIsAtAll(true);

        SendResult result = client.send(chatbotWebhookConfig.CHATBOT_WEBHOOK, message);
        System.out.println(result);
    }


    public void testSendTextMessageWithAtAndAtAll() throws Exception {
        TextMessage message = new TextMessage("我就是我, 是不一样的烟火");
        ArrayList<String> atMobiles = new ArrayList<String>();
        atMobiles.add("186xxxx0822");
        message.setAtMobiles(atMobiles);
        message.setIsAtAll(true);

        SendResult result = client.send(chatbotWebhookConfig.CHATBOT_WEBHOOK, message);
        System.out.println(result);
    }

    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------

    public void testSendLinkMessage() throws Exception {
        LinkMessage message = new LinkMessage();
        message.setTitle("时代的火车向前开");
        message.setText("这个即将发布的新版本，创始人陈航（花名“无招”）称它为“红树林”。\n" +
                "而在此之前，每当面临重大升级，产品经理们都会取一个应景的代号，这一次，为什么是“红树林”？\"");
        message.setMessageUrl("https://mp.weixin.qq.com/s?spm=a219a.7629140.0.0.EUDyWG&__biz=MzA4NjMwMTA2Ng==&mid=2650316842&idx=1&sn=60da3ea2b29f1dcc43a7c8e4a7c97a16&scene=2&srcid=09189AnRJEdIiWVaKltFzNTw&from=timeline&isappinstalled=0&key=&ascene=2&uin=&devicetype=android-23&version=26031933&nettype=WIFI");
        message.setPicUrl("https://img.alicdn.com/tps/TB1XLjqNVXXXXc4XVXXXXXXXXXX-170-64.png");

        SendResult result = client.send(chatbotWebhookConfig.CHATBOT_WEBHOOK, message);
        System.out.println(result);
    }


    public void testSendLinkMessageWithoutIcon() throws Exception {
        LinkMessage message = new LinkMessage();
        message.setTitle("时代的火车向前开");
        message.setMessageUrl("https://mp.weixin.qq.com/s?spm=a219a.7629140.0.0.EUDyWG&__biz=MzA4NjMwMTA2Ng==&mid=2650316842&idx=1&sn=60da3ea2b29f1dcc43a7c8e4a7c97a16&scene=2&srcid=09189AnRJEdIiWVaKltFzNTw&from=timeline&isappinstalled=0&key=&ascene=2&uin=&devicetype=android-23&version=26031933&nettype=WIFI");
        message.setText("这个即将发布的新版本，创始人陈航（花名“无招”）称它为“红树林”。\n" +
                "而在此之前，每当面临重大升级，产品经理们都会取一个应景的代号，这一次，为什么是“红树林”？\"");
        SendResult result = client.send(chatbotWebhookConfig.CHATBOT_WEBHOOK, message);
        System.out.println(result);
    }

    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public void testSendMarkdownMessage() throws Exception {
        MarkdownMessage message = new MarkdownMessage();
        message.setTitle("This is a markdown message");

        message.add(MarkdownMessage.getHeaderText(1, "header 1"));
        message.add(MarkdownMessage.getHeaderText(2, "header 2"));
        message.add(MarkdownMessage.getHeaderText(3, "header 3"));
        message.add(MarkdownMessage.getHeaderText(4, "header 4"));
        message.add(MarkdownMessage.getHeaderText(5, "header 5"));
        message.add(MarkdownMessage.getHeaderText(6, "header 6"));

        message.add(MarkdownMessage.getReferenceText("reference text"));
        message.add("\n\n");

        message.add("normal text");
        message.add("\n\n");

        message.add(MarkdownMessage.getBoldText("Bold Text"));
        message.add("\n\n");

        message.add(MarkdownMessage.getItalicText("Italic Text"));
        message.add("\n\n");

        ArrayList<String> orderList = new ArrayList<String>();
        orderList.add("order item1");
        orderList.add("order item2");
        message.add(MarkdownMessage.getOrderListText(orderList));
        message.add("\n\n");

        ArrayList<String> unorderList = new ArrayList<String>();
        unorderList.add("unorder item1");
        unorderList.add("unorder item2");
        message.add(MarkdownMessage.getUnorderListText(unorderList));
        message.add("\n\n");

        message.add(MarkdownMessage.getImageText("http://img01.taobaocdn.com/top/i1/LB1GCdYQXXXXXXtaFXXXXXXXXXX"));
        message.add(MarkdownMessage.getLinkText("This is a link", "dtmd://dingtalkclient/sendMessage?content=linkmessage"));
        message.add(MarkdownMessage.getLinkText("中文跳转", "dtmd://dingtalkclient/sendMessage?content=" + URLEncoder.encode("链接消息", "UTF-8")));

        SendResult result = client.send(chatbotWebhookConfig.CHATBOT_WEBHOOK, message);
        System.out.println(result);
    }




    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------

    public void testSingleTargetActionCardMessage() throws Exception {
        SingleTargetActionCardMessage message = new SingleTargetActionCardMessage();
        message.setBannerUrl("http://img01.taobaocdn.com/top/i1/LB1GCdYQXXXXXXtaFXXXXXXXXXX");
        message.setTitle("2017年你要读的十本书");
        message.setBriefTitle("2017年你要读的十本书");
        message.setBriefText("书籍是人类进步的阶梯.生活里没有书籍,就好像大地没有阳光;智慧里没有书籍,就好像鸟儿没有翅膀.一本好书就是一位好老师,它可以塑造一个完美的灵魂");
        message.setSingleTitle("查看更多");
        message.setSingleURL("http://www.dingtalk.com");

        SendResult result = client.send(chatbotWebhookConfig.CHATBOT_WEBHOOK, message);
        System.out.println(result);

    }


    public void testSendSingleTargetActionCardMessageWithoutBanner() throws Exception {
        SingleTargetActionCardMessage message = new SingleTargetActionCardMessage();
        message.setTitle("2017年你要读的十本书");
        message.setBriefTitle("2017年你要读的十本书");
        message.setBriefText("书籍是人类进步的阶梯”.生活里没有书籍,就好像大地没有阳光;智慧里没有书籍,就好像鸟儿没有翅膀.一本好书就是一位好老师,它可以塑造一个完美的灵魂");
        message.setSingleTitle("查看更多");
        message.setSingleURL("http://www.dingtalk.com");

        SendResult result = client.send(chatbotWebhookConfig.CHATBOT_WEBHOOK, message);
        System.out.println(result);
    }


    public void testSendSingleTargetActionCardMessageHideAvatar() throws Exception {
        SingleTargetActionCardMessage message = new SingleTargetActionCardMessage();
        message.setTitle("2017年你要读的十本书");
        message.setBriefTitle("2017年你要读的十本书");
        message.setHideAvatar(true);
        message.setBriefText("书籍是人类进步的阶梯”.生活里没有书籍,就好像大地没有阳光;智慧里没有书籍,就好像鸟儿没有翅膀.一本好书就是一位好老师,它可以塑造一个完美的灵魂");
        message.setSingleTitle("查看更多");
        message.setSingleURL("http://www.dingtalk.com");

        SendResult result = client.send(chatbotWebhookConfig.CHATBOT_WEBHOOK, message);
        System.out.println(result);
    }


    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public void testSendMultipleFeedCardMessage() throws Exception {
        FeedCardMessage message = new FeedCardMessage();

        List<FeedCardMessageItem> items = new ArrayList<FeedCardMessageItem>();
        FeedCardMessageItem item1 = new FeedCardMessageItem();
        item1.setTitle("心灵鸡汤1");
        item1.setPicURL("https://img.alicdn.com/tps/TB1XLjqNVXXXXc4XVXXXXXXXXXX-170-64.png");
        item1.setMessageURL("http://www.dingtalk.com");
        items.add(item1);

        FeedCardMessageItem item2 = new FeedCardMessageItem();
        item2.setTitle("心灵鸡汤2");
        item2.setPicURL("https://img.alicdn.com/tps/TB1XLjqNVXXXXc4XVXXXXXXXXXX-170-64.png");
        item2.setMessageURL("http://www.dingtalk.com");
        items.add(item2);

        FeedCardMessageItem item3 = new FeedCardMessageItem();
        item3.setTitle("心灵鸡汤3");
        item3.setPicURL("https://img.alicdn.com/tps/TB1XLjqNVXXXXc4XVXXXXXXXXXX-170-64.png");
        item3.setMessageURL("http://www.dingtalk.com");
        items.add(item3);

        message.setFeedItems(items);

        System.out.println(message.toJsonString());

        SendResult result = client.send(chatbotWebhookConfig.CHATBOT_WEBHOOK, message);
        System.out.println(result);
    }



    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------

    public void testSendActionCardMessage() throws Exception {
        ActionCardMessage message = new ActionCardMessage();
        message.setBriefText("亲，小秘没有看懂你的问题哦，换个说法问问小秘看~你也可以试试以下问题");
        ActionCardAction action1 = new ActionCardAction("考勤打卡", "http://www.dingtalk.com");
        message.addAction(action1);
        ActionCardAction action2 = new ActionCardAction("办公电话", "http://www.dingtalk.com");
        message.addAction(action2);
        ActionCardAction action3 = new ActionCardAction("智能客服", "http://www.dingtalk.com");
        message.addAction(action3);
        ActionCardAction action4 = new ActionCardAction("更多问题", "http://www.dingtalk.com");
        message.addAction(action4);

        SendResult result = client.send(chatbotWebhookConfig.CHATBOT_WEBHOOK, message);
        System.out.println(result);
    }


    public void testSendActionCardMessageWithTitle() throws Exception {
        ActionCardMessage message = new ActionCardMessage();
        message.setTitle("钉钉功能推荐");

        message.setBriefTitle("创建团队，让工作沟通更加安全高效");
        message.setBriefText("国内广受认可的企业级应用市场入驻标准高、产品质量高，入驻即代表得到市场认可，实力获得钉钉背书");
        ActionCardAction action1 = new ActionCardAction("邀请群成员创建团队", "http://www.dingtalk.com");
        message.addAction(action1);

        SendResult result = client.send(chatbotWebhookConfig.CHATBOT_WEBHOOK, message);
        System.out.println(result);
    }


    public void testSendActionCardMessageWithBanner() throws Exception {
        ActionCardMessage message = new ActionCardMessage();
        message.setTitle("钉钉功能推荐");
        message.setBannerURL("http://img01.taobaocdn.com/top/i1/LB1GCdYQXXXXXXtaFXXXXXXXXXX");
        message.setBriefTitle("创建团队，让工作沟通更加安全高效");
        message.setBriefText("国内广受认可的企业级应用市场入驻标准高、产品质量高，入驻即代表得到市场认可，实力获得钉钉背书");
        ActionCardAction action1 = new ActionCardAction("查看详情", "http://www.dingtalk.com");
        message.addAction(action1);

        ActionCardAction action2 = new ActionCardAction("不感兴趣", "http://www.dingtalk.com");
        message.addAction(action2);

        SendResult result = client.send(chatbotWebhookConfig.CHATBOT_WEBHOOK, message);
        System.out.println(result);
    }


    public void testSendActionCardMessageWithHorizontalButton() throws Exception {
        ActionCardMessage message = new ActionCardMessage();
        message.setBannerURL("http://img01.taobaocdn.com/top/i1/LB1GCdYQXXXXXXtaFXXXXXXXXXX");
        message.setTitle("创建团队，让工作沟通更加安全高效");
        message.setBriefText("国内广受认可的企业级应用市场入驻标准高、产品质量高，入驻即代表得到市场认可，实力获得钉钉背书");
        ActionCardAction action1 = new ActionCardAction("查看详情", "http://www.dingtalk.com");
        message.addAction(action1);

        ActionCardAction action2 = new ActionCardAction("不感兴趣", "http://www.dingtalk.com");
        message.addAction(action2);
        message.setActionButtonStyle(ActionButtonStyle.HORIZONTAL);

        SendResult result = client.send(chatbotWebhookConfig.CHATBOT_WEBHOOK, message);
        System.out.println(result);
    }


    public void testSendActionCardMessageWithHorizontalButtonWithoutAvatar() throws Exception {
        ActionCardMessage message = new ActionCardMessage();
        message.setBannerURL("http://img01.taobaocdn.com/top/i1/LB1GCdYQXXXXXXtaFXXXXXXXXXX");
        message.setTitle("创建团队，让工作沟通更加安全高效");
        message.setBriefText("国内广受认可的企业级应用市场入驻标准高、产品质量高，入驻即代表得到市场认可，实力获得钉钉背书");
        ActionCardAction action1 = new ActionCardAction("查看详情", "dtmd://dingtalkclient/sendMessage?content=world");
        message.addAction(action1);


        ActionCardAction action2 = new ActionCardAction("不感兴趣", URLEncoder.encode("dtmd://dingtalkclient/sendMessage?content=不感兴趣", "UTF-8"));
        message.addAction(action2);
        message.setActionButtonStyle(ActionButtonStyle.HORIZONTAL);
        message.setHideAvatar(true);

        SendResult result = client.send(chatbotWebhookConfig.CHATBOT_WEBHOOK, message);
        System.out.println(result);
    }


    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------

}
