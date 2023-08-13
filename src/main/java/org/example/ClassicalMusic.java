package org.example;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component

public class ClassicalMusic implements Music{
    private ClassicalMusic() {
    }

    public static ClassicalMusic getClassicalMusic(){
        return new ClassicalMusic();
    }

    @Override
    public String getSong() {
        return "Symphony";
    }

    @PostConstruct
    public void doMyInit(){
        System.out.println("doing my initialization");
    }

    @PreDestroy
    public void doMyDestroy(){
        System.out.println("doing my destruction");
    }
}
