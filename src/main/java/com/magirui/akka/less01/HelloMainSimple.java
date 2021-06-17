package com.magirui.akka.less01;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;

/**
 * 〈哈喽〉
 *
 * @author magirui
 * @create 2018-06-07 下午8:15
 */
public class HelloMainSimple {

    public static void main(String[] args){

        ActorSystem system = ActorSystem.create("Hello", ConfigFactory.load("samplehello.conf"));
        ActorRef a = system.actorOf(Props.create(HelloWord.class), "HelloWorld");
        System.out.println("HelloWord Actor Path:" + a.path());
    }
}
