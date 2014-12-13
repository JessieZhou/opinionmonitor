package com.hhhy.crawler.www_eeo_com_cn;

import com.hhhy.crawler.www_cnstock_com.Controller;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * User: Ghost
 * Date: 14-7-14
 * Time: 上午10:14
 * To change this template use File | Settings | File Templates.
 */
public class Tiimer extends TimerTask {
    Controller controller;

    public Tiimer(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        this.controller.spyHistory.clear();
        this.controller.keyWordsList.clear();

        File keyFile = new File(this.controller.keyWordsLocation);
        try {
            BufferedReader br = new BufferedReader(new FileReader(keyFile));
            String line = "";
            while((line = br.readLine())!=null){
                this.controller.keyWordsList.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Timer timer = new Timer();
        timer.schedule(new TimerParse(this.controller),0,5*60*1000);
    }
    public static void main(String[] args){
        Timer hexunTimer = new Timer();
        //hexunTimer.schedule(new Tiimer(new Controller()),0,24*60*60000);
    }
}