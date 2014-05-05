package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import java.util.Random;

public class RPGEnemy extends RPGActor {
	private Wallet wallet;
	public RPGEnemy() {
		wallet = new Wallet();
		this.setName("Serithrasas");
	}
	
	@Override
	public void calculateLevel() {
		mLevel = (int) Math.ceil(Stats.getStatPool() / 3);
	}

	public void generateStats(Avatar avatarIn, Companion companionIn)
	{
		int statBoostFromCompanion = 0;
		if (companionIn != null) {
			statBoostFromCompanion = (int) (0.8 * avatarIn.getStatStruct().getStatPool() + 0.7 * avatarIn.getBattlesWon());
		}
		int statPool = (int)(0.3 * avatarIn.getStatStruct().getStatPool() + 0.75 * avatarIn.getBattlesWon() + statBoostFromCompanion);
		wallet.setGold(statPool * 2);
		for(int i = 0; i < statPool; i++)
		{
			Random rand = new Random(System.nanoTime());
			int chosenStat = Math.abs((rand.nextInt() % 6)) + 1;
			switch (chosenStat)
			{
			case 1: Stats.addCharisma(1);
				break;
			case 2: Stats.addConstitution(1);
				break;
			case 3: Stats.addDexterity(1);
				break;
			case 4: Stats.addIntellect(1);
				break;
			case 5: Stats.addStrength(1);
				break;
			case 6: Stats.addWisdom(1);
				break;
		    default:
					
			}
		}
	}

	public Wallet getWallet() {
		return wallet;
	}

}
