package org.example;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        //Music music = context.getBean("musicBean", Music.class);

        //MusicPlayer musicPlayer = new MusicPlayer(music);

        MusicPlayer firstMusicPlayer = context.getBean("musicPlayer", MusicPlayer.class);
        MusicPlayer secondMusicPlayer = context.getBean("musicPlayer", MusicPlayer.class);

        firstMusicPlayer.playMusic();

        firstMusicPlayer.setVolume(100);
        firstMusicPlayer.setName("xiaomi");

        System.out.println("firstMusicPlayer name = " + firstMusicPlayer.getName());
        System.out.println("firstMusicPlayer volume = " + firstMusicPlayer.getVolume());
        

        System.out.println("secondMusicPlayer name = " + secondMusicPlayer.getName());
        System.out.println("secondMusicPlayer volume = " + secondMusicPlayer.getVolume());
        context.close();

    }
}
