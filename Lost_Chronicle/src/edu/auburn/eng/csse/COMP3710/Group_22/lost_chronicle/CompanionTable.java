package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import java.util.ArrayList;


public class CompanionTable {
	public static final String TABLE_COMPANION = "companion";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_THUMBNAIL_IMAGE_RESOURCE = "thumbnail";
	public static final String COLUMN_SELECTION_SCREEN_IMAGE = "menu_image";
	public static final String COLUMN_FULLVIEW_IMAGE_RESOURCE = "character_image";
	public static final String COLUMN_SPRITE_IMAGE_RESOURCE = "sprite_image";
	public static final String COLUMN_PRICE = "price";
	public static final String COLUMN_RANK = "rank";
	public static final String COLUMN_PURCHASED = "purchased";
	public static final String COLUMN_TYPE = "type";
	public static final String COLUMN_CURRENT_COMPANION = "active_companion";
	
	public static final String COMPANION_CREATE = "create table "
			+ TABLE_COMPANION + "(" + COLUMN_ID
			+ "," + COLUMN_NAME + "," + COLUMN_THUMBNAIL_IMAGE_RESOURCE
			+ "," + COLUMN_SELECTION_SCREEN_IMAGE + "," + COLUMN_FULLVIEW_IMAGE_RESOURCE + ","
			+ COLUMN_SPRITE_IMAGE_RESOURCE + "," + COLUMN_PRICE + "," + COLUMN_RANK + "," + COLUMN_PURCHASED + "," + COLUMN_TYPE 
			+ "," + COLUMN_CURRENT_COMPANION + ")";
	public static final String COMPANION_DROP = "DROP TABLE IF EXISTS " + TABLE_COMPANION;
	public static final String[] allColumns = {COLUMN_ID, COLUMN_NAME, COLUMN_THUMBNAIL_IMAGE_RESOURCE, COLUMN_SELECTION_SCREEN_IMAGE,
			COLUMN_FULLVIEW_IMAGE_RESOURCE, COLUMN_SPRITE_IMAGE_RESOURCE, COLUMN_PRICE, COLUMN_RANK, COLUMN_PURCHASED, 
			COLUMN_TYPE, COLUMN_CURRENT_COMPANION};
	
	private static final int RANK_1_COST = 100;
	private static final int RANK_2_COST = 200;
	private static final int RANK_3_COST = 300;
	private static final int RANK_4_COST = 400;
	 
	public static ArrayList<Companion> intializeCompanionTable() {
		Companion grea1, grea2, grea3, grea4, 
		cherub1, cherub2, cherub3, cherub4, 
		lisa1, lisa2, lisa3, lisa4, 
		mage1, mage2, mage3, mage4,
		aurelia1, aurelia2, aurelia3, aurelia4, 
		elven1, elven2, elven3, elven4;
		
		grea1 = new Companion();
		grea1.setFullViewResource(R.drawable.grea_the_dragonborn1);
		grea1.setThumbnailResource(R.drawable.grea_the_dragonborn_thumbnail1);
		grea1.setMainMenuImage(R.drawable.grea_the_dragonborn_button1);
		grea1.setSpriteResource(R.drawable.sprite_charisma);//----------------------------------CHANGE THESE TO SPRITE DRAWABLES
		grea1.setRank(1);
		grea1.setId(1);
		grea1.setType("Charisma");
		grea1.setPrice(RANK_1_COST);
		grea1.setName("Grea the Dragonborn");
		grea1.setPurchased(false);
		grea1.setActiveCompanion(false);
		
		grea2 = new Companion();
		grea2.setFullViewResource(R.drawable.grea_the_dragonborn2);
		grea2.setThumbnailResource(R.drawable.grea_the_dragonborn_thumbnail2);
		grea2.setSpriteResource(R.drawable.sprite_charisma);
		grea2.setMainMenuImage(R.drawable.grea_the_dragonborn_button2);
		grea2.setRank(2);
		grea2.setId(2);
		grea2.setType("Charisma");
		grea2.setPrice(RANK_2_COST);
		grea2.setName("Grea the Dragonborn");
		grea2.setPurchased(false);
		grea2.setActiveCompanion(false);
		
		grea3 = new Companion();
		grea3.setFullViewResource(R.drawable.grea_the_dragonborn3);
		grea3.setThumbnailResource(R.drawable.grea_the_dragonborn_thumbnail3);
		grea3.setMainMenuImage(R.drawable.grea_the_dragonborn_button3);
		grea3.setSpriteResource(R.drawable.sprite_charisma);
		grea3.setRank(3);
		grea3.setId(3);
		grea3.setType("Charisma");
		grea3.setPrice(RANK_3_COST);
		grea3.setName("Grea the Dragonborn");
		grea3.setPurchased(false);
		grea3.setActiveCompanion(false);
		
		grea4 = new Companion();
		grea4.setFullViewResource(R.drawable.grea_the_dragonborn4);
		grea4.setThumbnailResource(R.drawable.grea_the_dragonborn_thumbnail4);
		grea4.setMainMenuImage(R.drawable.grea_the_dragonborn_button4);
		grea4.setSpriteResource(R.drawable.sprite_charisma);
		grea4.setRank(4);
		grea4.setId(4);
		grea4.setType("Charisma");
		grea4.setPrice(RANK_4_COST);
		grea4.setName("Grea the Dragonborn");
		grea4.setPurchased(false);
		grea4.setActiveCompanion(false);
		
		cherub1 = new Companion();
		cherub1.setFullViewResource(R.drawable.steel_cherub1);
		cherub1.setThumbnailResource(R.drawable.steel_cherub_thumb1);
		cherub1.setMainMenuImage(R.drawable.steel_cherub_button1);
		cherub1.setSpriteResource(R.drawable.sprite_constitution);
		cherub1.setRank(1);
		cherub1.setId(5);
		cherub1.setType("Constitution");
		cherub1.setPrice(RANK_1_COST);
		cherub1.setName("Steel Cherub");
		cherub1.setPurchased(false);
		cherub1.setActiveCompanion(false);
		
		cherub2 = new Companion();
		cherub2.setFullViewResource(R.drawable.steel_cherub2);
		cherub2.setThumbnailResource(R.drawable.steel_cherub_thumb2);
		cherub2.setMainMenuImage(R.drawable.steel_cherub_button2);
		cherub2.setSpriteResource(R.drawable.sprite_constitution);
		cherub2.setRank(2);
		cherub2.setId(6);
		cherub2.setType("Constitution");
		cherub2.setPrice(RANK_2_COST);
		cherub2.setName("Steel Cherub");
		cherub2.setPurchased(false);
		cherub2.setActiveCompanion(false);
		
		cherub3 = new Companion();
		cherub3.setFullViewResource(R.drawable.steel_cherub3);
		cherub3.setThumbnailResource(R.drawable.steel_cherub_thumb3);
		cherub3.setMainMenuImage(R.drawable.steel_cherub_button3);
		cherub3.setSpriteResource(R.drawable.sprite_constitution);
		cherub3.setRank(3);
		cherub3.setId(7);
		cherub3.setType("Constitution");
		cherub3.setPrice(RANK_3_COST);
		cherub3.setName("Steel Cherub");
		cherub3.setPurchased(false);
		cherub3.setActiveCompanion(false);
		
		cherub4 = new Companion();
		cherub4.setFullViewResource(R.drawable.steel_cherub4);
		cherub4.setThumbnailResource(R.drawable.steel_cherub_thumb4);
		cherub4.setMainMenuImage(R.drawable.steel_cherub_button4);
		cherub4.setSpriteResource(R.drawable.sprite_constitution);
		cherub4.setRank(4);
		cherub4.setId(8);
		cherub4.setType("Constitution");
		cherub4.setPrice(RANK_4_COST);
		cherub4.setName("Steel Cherub");
		cherub4.setPurchased(false);
		cherub4.setActiveCompanion(false);
		
		lisa1 = new Companion();
		lisa1.setFullViewResource(R.drawable.sky_sergeant_lisa1);
		lisa1.setThumbnailResource(R.drawable.sky_sergeant_lisa_thumb1);
		lisa1.setMainMenuImage(R.drawable.sky_sergeant_lisa_button1);
		lisa1.setSpriteResource(R.drawable.sprite_dexterity);
		lisa1.setRank(1);
		lisa1.setId(9);
		lisa1.setType("Dexterity");
		lisa1.setPrice(RANK_1_COST);
		lisa1.setName("Sky Sergeant Lisa");
		lisa1.setPurchased(false);
		lisa1.setActiveCompanion(false);
		
		lisa2 = new Companion();
		lisa2.setFullViewResource(R.drawable.sky_sergeant_lisa2);
		lisa2.setThumbnailResource(R.drawable.sky_sergeant_lisa_thumb2);
		lisa2.setMainMenuImage(R.drawable.sky_sergeant_lisa_button2);
		lisa2.setSpriteResource(R.drawable.sprite_dexterity);
		lisa2.setRank(2);
		lisa2.setId(10);
		lisa2.setType("Dexterity");
		lisa2.setPrice(RANK_2_COST);
		lisa2.setName("Sky Sergeant Lisa");
		lisa2.setPurchased(false);
		lisa2.setActiveCompanion(false);
		
		lisa3 = new Companion();
		lisa3.setFullViewResource(R.drawable.sky_sergeant_lisa3);
		lisa3.setThumbnailResource(R.drawable.sky_sergeant_lisa_thumb3);
		lisa3.setMainMenuImage(R.drawable.sky_sergeant_lisa_button3);
		lisa3.setSpriteResource(R.drawable.sprite_dexterity);
		lisa3.setRank(3);
		lisa3.setId(11);
		lisa3.setType("Dexterity");
		lisa3.setPrice(RANK_3_COST);
		lisa3.setName("Sky Sergeant Lisa");
		lisa3.setPurchased(false);
		lisa3.setActiveCompanion(false);
		
		lisa4 = new Companion();
		lisa4.setFullViewResource(R.drawable.sky_sergeant_lisa4);
		lisa4.setThumbnailResource(R.drawable.sky_sergeant_lisa_thumb4);
		lisa4.setMainMenuImage(R.drawable.sky_sergeant_lisa_button4);
		lisa4.setSpriteResource(R.drawable.sprite_dexterity);
		lisa4.setRank(4);
		lisa4.setId(12);
		lisa4.setType("Dexterity");
		lisa4.setPrice(RANK_4_COST);
		lisa4.setName("Sky Sergeant Lisa");
		lisa4.setPurchased(false);
		lisa4.setActiveCompanion(false);
		
		mage1 = new Companion();
		mage1.setFullViewResource(R.drawable.arc_mage1);
		mage1.setThumbnailResource(R.drawable.arc_mage_thumb1);
		mage1.setMainMenuImage(R.drawable.arc_mage_button1);
		mage1.setSpriteResource(R.drawable.sprite_intellect);
		mage1.setRank(1);
		mage1.setId(13);
		mage1.setType("Intelligence");
		mage1.setPrice(RANK_1_COST);
		mage1.setName("Arc Mage");
		mage1.setPurchased(false);
		mage1.setActiveCompanion(false);
		
		mage2 = new Companion();
		mage2.setFullViewResource(R.drawable.arc_mage2);
		mage2.setThumbnailResource(R.drawable.arc_mage_thumb2);
		mage2.setMainMenuImage(R.drawable.arc_mage_button2);
		mage2.setSpriteResource(R.drawable.sprite_intellect);
		mage2.setRank(2);
		mage2.setId(14);
		mage2.setType("Intelligence");
		mage2.setPrice(RANK_2_COST);
		mage2.setName("Arc Mage");
		mage2.setPurchased(false);
		mage2.setActiveCompanion(false);
		
		mage3 = new Companion();
		mage3.setFullViewResource(R.drawable.arc_mage3);
		mage3.setThumbnailResource(R.drawable.arc_mage_thumb3);
		mage3.setMainMenuImage(R.drawable.arc_mage_button3);
		mage3.setSpriteResource(R.drawable.sprite_intellect);
		mage3.setRank(3);
		mage3.setId(15);
		mage3.setType("Intelligence");
		mage3.setPrice(RANK_3_COST);
		mage3.setName("Arc Mage");
		mage3.setPurchased(false);
		mage3.setActiveCompanion(false);
		
		mage4 = new Companion();
		mage4.setFullViewResource(R.drawable.arc_mage4);
		mage4.setThumbnailResource(R.drawable.arc_mage_thumb4);
		mage4.setMainMenuImage(R.drawable.arc_mage_button4);
		mage4.setSpriteResource(R.drawable.sprite_intellect);
		mage4.setRank(4);
		mage4.setId(16);
		mage4.setType("Intelligence");
		mage4.setPrice(RANK_4_COST);
		mage4.setName("Arc Mage");
		mage4.setPurchased(false);
		mage4.setActiveCompanion(false);
		
		aurelia1 = new Companion();
		aurelia1.setFullViewResource(R.drawable.aurelia1);
		aurelia1.setThumbnailResource(R.drawable.aurelia_thumb1);
		aurelia1.setMainMenuImage(R.drawable.aurelia_button1);
		aurelia1.setSpriteResource(R.drawable.sprite_strength);
		aurelia1.setRank(1);
		aurelia1.setId(17);
		aurelia1.setType("Strength");
		aurelia1.setPrice(RANK_1_COST);
		aurelia1.setName("Aurelia");
		aurelia1.setPurchased(false);
		aurelia1.setActiveCompanion(false);
		
		aurelia2 = new Companion();
		aurelia2.setFullViewResource(R.drawable.aurelia2);
		aurelia2.setThumbnailResource(R.drawable.aurelia_thumb2);
		aurelia2.setMainMenuImage(R.drawable.aurelia_button2);
		aurelia2.setSpriteResource(R.drawable.sprite_strength);
		aurelia2.setRank(2);
		aurelia2.setId(18);
		aurelia2.setType("Strength");
		aurelia2.setPrice(RANK_2_COST);
		aurelia2.setName("Aurelia");
		aurelia2.setPurchased(false);
		aurelia2.setActiveCompanion(false);
		
		aurelia3 = new Companion();
		aurelia3.setFullViewResource(R.drawable.aurelia3);
		aurelia3.setThumbnailResource(R.drawable.aurelia_thumb3);
		aurelia3.setMainMenuImage(R.drawable.aurelia_button3);
		aurelia3.setSpriteResource(R.drawable.sprite_strength);
		aurelia3.setRank(3);
		aurelia3.setId(19);
		aurelia3.setType("Strength");
		aurelia3.setPrice(RANK_3_COST);
		aurelia3.setName("Aurelia");
		aurelia3.setPurchased(false);
		aurelia3.setActiveCompanion(false);
		
		aurelia4 = new Companion();
		aurelia4.setFullViewResource(R.drawable.aurelia4);
		aurelia4.setThumbnailResource(R.drawable.aurelia_thumb4);
		aurelia4.setMainMenuImage(R.drawable.aurelia_button4);
		aurelia4.setSpriteResource(R.drawable.sprite_strength);
		aurelia4.setRank(4);
		aurelia4.setId(20);
		aurelia4.setType("Strength");
		aurelia4.setPrice(RANK_4_COST);
		aurelia4.setName("Aurelia");
		aurelia4.setPurchased(false);
		aurelia4.setActiveCompanion(false);
		
		elven1 = new Companion();
		elven1.setFullViewResource(R.drawable.elven_princess_mage1);
		elven1.setThumbnailResource(R.drawable.elven_princess_mage_thumb1);
		elven1.setMainMenuImage(R.drawable.elven_princess_mage_button1);
		elven1.setSpriteResource(R.drawable.sprite_wisdom);
		elven1.setRank(1);
		elven1.setId(21);
		elven1.setType("Wisdom");
		elven1.setPrice(RANK_1_COST);
		elven1.setName("Elven Princess Mage");
		elven1.setPurchased(false);
		elven1.setActiveCompanion(false);
		
		elven2 = new Companion();
		elven2.setFullViewResource(R.drawable.elven_princess_mage2);
		elven2.setThumbnailResource(R.drawable.elven_princess_mage_thumb2);
		elven2.setMainMenuImage(R.drawable.elven_princess_mage_button2);
		elven2.setSpriteResource(R.drawable.sprite_wisdom);
		elven2.setRank(2);
		elven2.setId(22);
		elven2.setType("Wisdom");
		elven2.setPrice(RANK_2_COST);
		elven2.setName("Elven Princess Mage");
		elven2.setPurchased(false);
		elven2.setActiveCompanion(false);
		
		elven3 = new Companion();
		elven3.setFullViewResource(R.drawable.elven_princess_mage3);
		elven3.setThumbnailResource(R.drawable.elven_princess_mage_thumb3);
		elven3.setMainMenuImage(R.drawable.elven_princess_mage_button3);
		elven3.setSpriteResource(R.drawable.sprite_wisdom);
		elven3.setRank(3);
		elven3.setId(23);
		elven3.setType("Wisdom");
		elven3.setPrice(RANK_3_COST);
		elven3.setName("Elven Princess Mage");
		elven3.setPurchased(false);
		elven3.setActiveCompanion(false);
		
		elven4 = new Companion();
		elven4.setFullViewResource(R.drawable.elven_princess_mage4);
		elven4.setThumbnailResource(R.drawable.elven_princess_mage_thumb4);
		elven4.setMainMenuImage(R.drawable.elven_princess_mage_button4);
		elven4.setSpriteResource(R.drawable.sprite_wisdom);
		elven4.setRank(4);
		elven4.setId(24);
		elven4.setType("Wisdom");
		elven4.setPrice(RANK_4_COST);
		elven4.setName("Elven Princess Mage");
		elven4.setPurchased(false);
		elven4.setActiveCompanion(false);
		
		ArrayList<Companion> list = new ArrayList<Companion>();
		list.add(grea1);
		list.add(cherub1);
		list.add(lisa1);
		list.add(mage1);
		list.add(aurelia1);
		list.add(elven1);
		
		list.add(grea2);
		list.add(cherub2);
		list.add(lisa2);
		list.add(mage2);
		list.add(aurelia2);
		list.add(elven2);
		
		list.add(grea3);
		list.add(cherub3);
		list.add(lisa3);
		list.add(mage3);
		list.add(aurelia3);
		list.add(elven3);
		
		list.add(grea4);
		list.add(cherub4);
		list.add(lisa4);
		list.add(mage4);
		list.add(aurelia4);
		list.add(elven4);
		return list;
	}
}
			
