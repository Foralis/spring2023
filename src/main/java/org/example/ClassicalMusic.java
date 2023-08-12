package org.example;

import org.springframework.stereotype.Component;

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

    public void doMyInit(){
        System.out.println("doing my initialization");
    }

    public void doMyDestroy(){
        System.out.println("doing my destruction");
    }
}
