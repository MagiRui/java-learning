package com.magirui.akka.less01;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

/**
 * 〈Akka的HelloWorld〉
 *
 * @author magirui
 * @create 2018-06-07 下午6:41
 */
public class HelloWord extends UntypedActor{

    ActorRef greeter;

    @Override
    public void preStart(){

        greeter = getContext().actorOf(Props.create(Greeter.class), "greeter");
        System.out.println("Greeter Actor Path:" + greeter.path());
        greeter.tell(Greeter.Msg.GREET, getSelf());
    }

    @Override
    public void onReceive(Object msg){

        if (msg == Greeter.Msg.DONE){

            greeter.tell(Greeter.Msg.GREET, getSelf());
            getContext().stop(getSelf());
        }else {

            unhandled(msg);
        }
    }
}
