package TeamPack;

import ddf.minim.*;
import ie.tudublin.Visual;
import processing.core.*;

public class TeamCode extends Visual {

    Minim minim;
    AudioPlayer song;
    PImage pauseIcon;
    PImage playIcon;

    float[] x = new float[100];
    float[] y = new float[100];
    float[] speed = new float[100];
    float volume = 0;

    // Setup
    public void settings()
    {
        fullScreen();
    }

    // Play Controls
    public void keyPressed()
    {
        // Setting SPACE as a pause and play button
        if (key == ' ')
        {
            System.out.println(song.position());
            if (!song.isPlaying())
            {
                song.play();
            }
            else
            {
                song.pause();
            }

            smooth();
        }

        // Setting R as a restart button
        if (key == 'r' || key == 'R')
        {
            song.cue(0);
        }

        // Setting Up and Down arrow keys as the volume buttons
        if (keyCode == UP && volume < 0)
        {
            volume++;
        }

        if (keyCode == DOWN)
        {
            volume--;
        }
    }

    // On load
    public void setup()
    {
        colorMode(RGB);
        
        // Create a new Minim and load the song
        minim = new Minim(this);
        song = minim.loadFile("enemy.mp3");

        // Load the images for pause and play
        pauseIcon = loadImage("pause.png");
        pauseIcon.resize(25, 25);
        playIcon = loadImage("play.png");
        playIcon.resize(25, 25);

        // Populates location of stars, for random stars
        for (int i = 0; i < 100; i++)
        {
            x[i] = random(width);
            y[i] = random(height);
            speed[i] = random(1,5);
        }
    }

    // Play-pause assets
    public void icons()
    {
        if (song.isPlaying())
        {
            image(pauseIcon, width * 0.10f, height * 0.95f - 20);
        }
        else
        {
            image(playIcon, width * 0.10f, height * 0.95f - 20);
        }
    }

    // Timeline
    public void timeline()
    {
        float maxLen = song.length();
        float currentPer = floor((song.position() / maxLen) * 100);
        if (currentPer == 0)
        {
            currentPer = 1;
        }
        stroke(60, 172, 212);
        fill(60, 172, 212);
        rect(0, height - 10, (currentPer / 100) * 1600, height - 40);
    }

    // Volume
    public void volume()
    {
        song.setGain(volume);
        fill(255);
        textSize(20);
        text("Volume: " + (floor(song.getGain()) + 100), width * 0.015f, height * 0.95f);
    }

    // Draw
    public void draw()
    {
        for(int i = 0; i < 100; i++)
        {
            stroke(72, 200, 255);
            point(x[i], y[i]);
            x[i] = x[i] - speed[i];

            if(x[i] < 0)
            {
                x[i] = width;
            }
        }

        noStroke();
        fill(0, 5);
        rect(0, 0, width, height);
        pushMatrix();
        translate(width / 2, height / 2);
        rotate(radians(frameCount % 360 * 2));

        for(int j = 0; j < 360; j++)
        {
            if(song.mix.get(j) * 200 > 50)
            {
                stroke(153, 63, 196);
            }
            else 
            {
                stroke(157, 42, 179);
            }
            line(cos(j) * 25, sin(j) * 25, cos(j) * abs(song.left.get(j)) * 75 + cos(j) * 25, sin(j) * abs(song.right.get(j)) * 75 + sin(j) * 25);
        }

        for(int k = 360; k > 0; k--)
        {
      
            if(song.mix.get(k) * 200 > 25)
            {
                stroke(56, 34, 130);
            }
            else
            {
                stroke(123, 48, 228);
            }
            line(cos(k) * 100, sin(k) * 100, cos(k) * abs(song.right.get(k)) * 85 + cos(k) * 100, sin(k) * abs(song.left.get(k)) * 85 + sin(k) * 100);   
        }

        for(int l = 0; l < 360; l++)
        {
      
            if(song.mix.get(l) * 200 > 25)
            {
                stroke(70, 45, 207);
            }
            else
            {
                stroke(69, 61, 169);
            }
            line(cos(l) * 175, sin(l) * 175, cos(l) * abs(song.right.get(l)) * 150 + cos(l) * 175, sin(l) * abs(song.left.get(l)) * 150 + sin(l) * 175);   
        }
  
        popMatrix();
        volume();
        icons();
        timeline();
    }    
}
