Lost_Chronicle
==============

Wireless Project

TODO:

-Finish merging everyone's projects. This mostly consists of resolving issues relating to events (mostly a naming issue)

-Complete a lot of classes - Attribute, Class, Avatar, RPGActor, Enemy, RPG_Battle, Companion (rpg related methods/fields).



General Class Descriptions:
------------------------------------------------------------------------------------------------------------------
-Attributes Class : This class will have methods which will take in particular stats as parameters and produce a resulting
integer/float which will correspond to Attack, Health, Defence, etc. 
  ex. public int calculateAttack(int strength, int dexterity) {
            return 300 + (strength * 70 + dexterity * 25);
        }
    One of these methods will be produced for each of the 7 attribute types.
-Avatar Class : This class will have methods for producing special attacks based on particular stats and other methods
    and attributes needed in the RPG Battle. (Like making money and leveling up perhaps)
    
-Companion_Activity : COMPLETED* - This is the hosting activity for the "Kanojo" portion of the game. This includes the
    kanojo store and kanojo character screen. It talks with the database managers to fulfill requests from its fragments.
    Fragments belonging to it include the KanojoStore and KanojoInformationFragment. The KanojoInfoCommunicator
    interface is also implemented to facilitate communication between fragments and Companion_Activity.
    
-Companion : Parcelable class which will need to generate it's own Stat structure based on the current Avatar's stats
    and the "type" of the companion. For instance the Charisma companion will distribute more of the Avatar's stat pool
    to its own Charisma stat than any other stat. It will also need special abilities similar to Avatar which are basically
    copies of the Avatar's special abilities. Only one special ability per companion. More would be great if we had time.
    
-CompanionDataSource : COMPLETE - Database Manager class which can manipulate the Companion table of the database to 
    answer query, insert, and delete requests pertaining to Companion objects.

-CompanionTable : COMPLETE - Mostly a set of constant values pertaining to companions which are hard coded into the 
    database on creation. 

-EventScheduler : COMPLETE* - Database Manager class which can manipulate the events table of the database to answer
    query, insert, and delete requests pertaining to Event objects.
    
-Event :    Class used to represent "events" scheduled by the user about something they are going to do. It includes
    the time the event starts, ends, title of event, description, type of event (as in stat increased in game), 
    a float evaluation of the event (how well you think you did based on a scale of 1-10), and difficulty of the event
    which is a float on a scale of 1-10. These events are used to determine stat growth of the RPG Avatar.
    
-EventCommunicator : Interface to facilitate communication between the hosting event activity Scheduler_Activity and its
    fragments.

-KanojoInfoCommunicator : COMPLETE Interface to facilitate communication between the hosting companion activity Companion_Activity
    and its KanojoInformationFragment.

-KanojoStore : COMPLETE - Fragment which consists of a GridView. In each of the cells is a Companion which you can 
    purchase for in-game gold or fake "real-life money". The Companions must be unlocked in sequence and can only be
    bought if you have enough in-game gold or simply do the fake microtransaction.

-MicrotransactionDialog : COMPLETE - Dialog Fragment which displays the fake microtransaction page. Simply hit the
    continue button to instantly purchase the Purchasable implementing class with no down sides!

-Purchasable : COMPLETE - Interface which must be implemented by classes which are going to be bought in the app such
    as the Hair and Companion classes. Has methods which capture the essence of items which are "purchasable"

-PurchasableAdapter : COMPLETE - Class which extends an Adapter. Used to populate the store fragments' GridViews with
    Purchasable objects.
    
-PurchaseDialogFragment : COMPLETE - Class which extends a Dialog. Used by the store fragments to prompt the user
    to purchase the Purchasable with either in-game gold, continue to the microtransaction dialog, or cancel the purchase.
    
-RPG_BATTLE : Hosting fragment for the RPG Battle portion of the app. Has two buttons. One takes you to the Avatar 
    character pager (not yet implemented) and the other takes you to the RPG Battle Screen (not yet implemented) where
    you can battle enemies and win gold to buy hair and more companions!

-RPG Actor  : Ancestor class of the Avatar, Companion, and Enemy (not yet implemented) classes. Has common fields
    and methods shared by all RPG Battle participants.
    
-Scheduler_Activity : Hosting class of the scheduling portion of the app. This includes scheduling new Events which
    when completed, will increase the Avatar's stats. You can also view the history of all the events you've scheduled

-Selection_Screen : Basically the home screen which you can use to navigate to the RPG Battle portion, Companion portion,
    or the Event portion of the app. The Companion button changes depending on who your current Companion is.

-Stat : Essentially just a structure to consolidate the various possible stats of the RPG Actor descendants along 
    with convienence methods to set, get, and addTo any of t he 6 stats. The stats are as follows: Charisma,
    Strength, Constitution, Intelligence, Wisdom, and Dexterity

-StoreCommunicator : Interface used to facilitate communication between the HairStore (not yet implemented)/KanojoStore
    and their hosting activities. The methods specified within this interface deal only with classes which implement
    the Purchasable interface.
