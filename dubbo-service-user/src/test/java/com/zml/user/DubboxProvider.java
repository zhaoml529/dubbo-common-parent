package com.zml.user;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DubboxProvider {

	public static void main(String[] args) {
//        new Thread(new Runnable() {
//            public void run() {
//                try {
//                    Thread.sleep(20000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                ProtocolConfig.destroyAll();
//            }
//        }).start();
        com.alibaba.dubbo.container.Main.main(args);
    }
}
