package c20463336;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ie.tudublin.Visual;

public class testvisual extends Visual {

    Minim minim;
    AudioPlayer song;

    float[] x = new float[100];
    float[] y = new float[100];
    float[] speed = new float[100];

    float color1 = 35;
    float color2 = 45;
    float color3 = 65;
    float color4 = 75;

    public void settings()
    {
        fullScreen();
    }

    public void keyPressed()
    {
        if (key == '1')
        {
            song.play();

            smooth();
        }

        if (key == '2')
        {
            song.pause();
        }

        if (key == '3')
        {
            song.cue(0);
        }
    }

    public void setup()
    {
        colorMode(HSB);

        minim = new Minim(this);
        song = minim.loadFile("enemy.mp3");

        int i = 0;
        while(i < 100)
        {
            x[i] = random(width);
            y [i]= random(width);
            speed[i] = random(1,5);
            i = i + 1;
        }
    }

    public void draw()
    {
        int i = 0;
        while(i<100)
        {
            point(x[i],y[i]);
            x[i] =  x[i] - speed[i];

            if(x[i] < 0)
            {
                x[i] = width;
            }

            i =  i + 1;
        }

        noStroke();
        fill(0, 5);
        rect(0,0,width,height);
        pushMatrix();
        translate(width/2, height/2);
        rotate(radians(frameCount % 360 * 2));

        for(int j = 0; j < 360; j++)
        {
      
            if(song.mix.get(j)*200 > 50)
            {
                stroke(color1,100,100);
            }
            else 
            {
            stroke(color2,100,100);
            }
            line(cos(j)*50, sin(j)*50, cos(j)*abs(song.left.get(j))*200 + cos(j)*50, sin(j)*abs(song.right.get(j))*200 + sin(j)*50);
        }

        for(int k = 360; k > 0; k--)
        {
      
            if(song.mix.get(k)*200 > 25)
            {
                stroke(color3,100,100);
            }
            else
            {
                stroke(color4,100,100);
            }
            line(cos(k)*50, sin(k)*50, cos(k)*abs(song.right.get(k))*200 + cos(k)*50, sin(k)*abs(song.left.get(k))*200 + sin(k)*50);   
        }
  
        popMatrix();
    }    
}
