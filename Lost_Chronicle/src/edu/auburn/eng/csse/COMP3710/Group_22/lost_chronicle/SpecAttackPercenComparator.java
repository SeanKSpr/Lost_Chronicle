package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import java.util.Comparator;

public class SpecAttackPercenComparator implements Comparator<SpecialAttackPercentage> {

	public SpecAttackPercenComparator() {

	}

	@Override
	public int compare(SpecialAttackPercentage lhs, SpecialAttackPercentage rhs) {
		if (lhs.getPercentChance() < rhs.getPercentChance()) {
			return 1;
		}
		if (lhs.getPercentChance() > rhs.getPercentChance()) {
			return -1;
		}
		else {
			return 0;
		}
	}

}
