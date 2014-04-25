package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import java.util.Random;

public class RPGEnemy extends RPGActor {
	Wallet wallet;
	public RPGEnemy() {
		wallet = new Wallet();
		// TODO Auto-generated constructor stub
	}
	
	public void generateStats(Avatar avatarIn)
	{
		int statPool = avatarIn.getStatStruct().getStatPool();
		wallet.setGold(statPool * 2);
		for(int i = 0; i < statPool; i++)
		{
			Random rand = new Random(System.currentTimeMillis() * 113);
			int chosenStat = rand.nextInt(6);
			switch (chosenStat)
			{
			case 1: statStruct.addCharisma(1);
				break;
			case 2: statStruct.addConstitution(1);
				break;
			case 3: statStruct.addDexterity(1);
				break;
			case 4: statStruct.addIntellect(1);
				break;
			case 5: statStruct.addStrength(1);
				break;
			case 6: statStruct.addWisdom(1);
				break;
		    default:
					
			}
		}
	}

}
