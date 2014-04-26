package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import java.util.Random;

import android.content.Context;
import android.media.MediaPlayer;

public class Jukebox {
	int songs[] = {R.raw.battle_five, R.raw.battle_four, R.raw.battle_one, R.raw.battle_three, R.raw.battle_two,
				   R.raw.character_screen_music, R.raw.companion_music, R.raw.selection_screen, R.raw.theme};
	MediaPlayer player;
	Context currentContext;
	static final int SELECTION_SCREEN = 1, CHARACTER_SCREEN = 2, COMPANION_SCREEN = 3;
	static final int BATTLE_SCREEN = 4;
	public Jukebox(Context context) {
		currentContext = context;
	}
	public void stop()
	{
		player.stop();
	}
	public void start(int screenChoice)
	{
		switch(screenChoice)
		{
		case 1:
			player = MediaPlayer.create(currentContext, R.raw.selection_screen);
			player.start();
			player.setLooping(true);
			break;
		case 2:
			player = MediaPlayer.create(currentContext, R.raw.character_screen_music);
			player.start();
			player.setLooping(true);
			break;
		case 3:
			player = MediaPlayer.create(currentContext, R.raw.companion_music);
			player.start();
			player.setLooping(true);
			break;
		case 4:
			Random rand = new Random(System.currentTimeMillis());
			int songChoice = rand.nextInt(6);
			switch(songChoice)
			{
			case 1:
				player = MediaPlayer.create(currentContext, R.raw.theme);
				player.start();
				player.setLooping(true);
				break;
			case 2:
				player = MediaPlayer.create(currentContext, R.raw.battle_one);
				player.start();
				player.setLooping(true);
				break;
			case 3:
				player = MediaPlayer.create(currentContext, R.raw.battle_two);
				player.start();
				player.setLooping(true);
				break;
			case 4:
				player = MediaPlayer.create(currentContext, R.raw.battle_three);
				player.start();
				player.setLooping(true);
				break;
			case 5:
				player = MediaPlayer.create(currentContext, R.raw.battle_four);
				player.start();
				player.setLooping(true);
				break;
			case 6:
				player = MediaPlayer.create(currentContext, R.raw.battle_five);
				player.start();
				player.setLooping(true);
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}
		
	}

}
