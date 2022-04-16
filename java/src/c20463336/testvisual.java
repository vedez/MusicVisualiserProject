package c20463336;

import ie.tudublin.Visual;

public class testvisual extends Visual {

    public void settings()
    {
        fullScreen();
    }

    public void keyPressed()
    {
        if (key == '1')
        {
            getAudioPlayer().play();
        }

        if (key == '2')
        {
            getAudioPlayer().pause();
        }

        if (key == '3')
        {
            getAudioPlayer().cue(0);
        }
    }

    public void setup()
    {
        colorMode(RGB);

        startMinim();

        loadAudio("enemy.mp3");
    }

    float radius = 200;

    float smoothedBoxSize = 0;

    float rot = 0;

    public void draw()
    {
    
    }
    
}
