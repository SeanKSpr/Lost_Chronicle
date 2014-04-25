package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import android.content.Context;
import android.media.MediaPlayer;

public class Jukebox {
	MediaPlayer player;
	public Jukebox(Context context) {
		player = MediaPlayer.create(context, R.raw.theme);
		player.setLooping(true);
		player.start();
	}
	public void stop()
	{
		player.stop();
	}
	public void start()
	{
		player.start();
		player.setLooping(true);
	}

}
