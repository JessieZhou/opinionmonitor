package com.hhhy.crawler.www_eeo_com_cn;

import com.hhhy.crawler.www_cnstock_com.Controller;

import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * User: Ghost
 * Date: 14-7-14
 * Time: 上午10:09
 * To change this template use File | Settings | File Templates.
 */
public class TimerParse extends TimerTask {
    com.hhhy.crawler.www_cnstock_com.Controller controller;
    public TimerParse(Controller controller) {
        this.controller = controller;
    }
    @Override
    public void run() {
        System.out.println("a new round");
        for(String keyWord:controller.keyWordsList){
            this.controller.parseBoard(keyWord,"");
        }
    }
}