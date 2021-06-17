package com.magirui.akka.less01;

import akka.actor.UntypedActor;

/**
 * 〈我的Hello World〉
 *
 * @author magirui
 * @create 2018-06-07 下午7:21
 */
public class Greeter extends UntypedActor{

    public static enum Msg {

        GREET, DONE;
    }


    public void onReceive(Object msg){

        if (msg == Msg.GREET){

            System.out.println("Hello World!");
            getSender().tell(Msg.DONE, getSelf());
        }

        unhandled(msg);
    }
}
