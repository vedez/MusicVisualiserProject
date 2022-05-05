package ie.tudublin;

import TeamPack.TeamCode;

public class Main
{	

	public void startUI()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new TeamCode());		
	}

	public static void main(String[] args)
	{
		Main main = new Main();
		main.startUI();			
	}
}