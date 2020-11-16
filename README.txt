LENGENDS: MONSTERS & HEROES
Tanner Braun, tsbraun@bu.edu, U65930557

COMPILATION:
    Navigate to the folder containing all of the files for L:M&H and run javac *.java.

EXECUTION:
    While still in the same folder as for compilation, run "java LegendsMonstersHeroes" and the program should start!

ENTITIES:
- Actor.java:
    Abstract class the represents the generic 'Actor' in a video game
which can either be a player or an enemy.  Handles things they all 
share like taking damage, defense, dodge chance, etc. This works well
because there are many ways in battle that both heroes and monsters 
act similarly.
- Monster.java:
    The monster class is a specific implementation of the Actor class
that can represent many different types of monsters in an rpg.
Monsters can naturally attack other characters as well as have
their own "classes" in MonsterType.
- Hero.java:
    The hero class is an abstract class that represents an individual hero.  
This class extends actors to encompass all hero-specific stats such as
gold and mana, as well as hero-specific abilities such as healing and
levelling up. Also for the hero I changed the health and mana to have a cap 
and starting value of lvl * 1000. Heroes also can make unarmed attacks before
they find weapons.
    HERO CLASSES:
        These classes mainly implement the different levelUp methods for each different class.
    - Paladin.java:
        The Paladin class represents a hero that has the paladin class. Paladins 
    specialize in the dexterity and strength stats. 
    -Sorcerer.java:
        The Sorcerer class represents a hero that has the sorcerer class. Sorcerers 
    specialize in the dexterity and agility stats.
    - Warrior.java:
        The Warrior class represents a hero that has the warrior class. Warriors 
    specialize in the strength and agility stats.

ITEMS:
-Item.java:
    An item is any piece of equipment that can be equipped or bought in
a market square. Items all have a name, cost, required level, and
an affect amount. This amount can be damage, attribute increase, or
damage reduction. Item is an abstract class to be implemented by
other specific types of items.
-Armor.java:
    The armor class extends Item as it is a type of item
that contributes to the defense of its wearer/owner.
-Potion.java:
    Potions are one time use items that can increase given attributes by a
given amount. The potion class handles what happens when a given hero
drinks this potion.
-AttackItem.java:
    Attack items is another abstract class that deals specifically
with items that have their own damage value/attack action. Attack
items handle calculating the damage they do with their owner
and also actually carrying out the attack against an enemy. When 
working with attack items, I decided to take a more classic RPG 
approach where the hero's linked attribute is their base damage and
the item's damage is determined randomly on every attack (with its 
original damage stat being its max value).
-Weapon.java:
    Weapons are physical objects that heroes can use to do damage to monsters.
Weapons represent one specific type of item that you can attack with. 
-Spell.java:
    Spell is an abstract class that represents a magical attack in our game.
All spells require an amount of mana to be spent in order to cast, and 
also have an extra affect based on their spell type.
    -FireSpell.java, IceSpell.java, LightningSpell.java
        The main thing that these classes do is implement attackTarget so that
    the spell's extra affects happen.


LISTS:
-ItemList.java, MonsterList.java, HeroList.java:
    In order to import the stat blocks for characters and items that we were given,
I placed all of these stats into their own lists. These lists kind of operate like 
factories like we talked about this week in class where things like the game object
will make calls to these lists when a random character or item is needed. These lists
also allow you to request specific types of items or heroes from the list as well as set 
different conditions for what you're requesting (such as a monster being below a certain lvl).

IO:
-IO.java:
    The IO class is an abstract parent class to all of the specific implementations 
of IO  needed to run a Legends game. Here some generally helpful IO functions are  
defined for its children. IO classes are the primary way to get a user's input or 
display important information to a user.

OVERWORLD:
- Player.java:
    The Player class generally stores metadata around the user playing this game
such as their specific piece and name.
-Piece.java:
    A piece is used to represent a player's location on a given board.
-Space.java:
    Space represents a space on a game board.  It keeps track of any pieces on the space
and allows users to add and remove pieces from the space.
-LegendSpace.java:
    LegendSpace is an extension of the space class that adds a 
space's type to the board. Spaces can be common, a market,
or impassable.
-Board.java:
    Board represents a physical board by using a 2D array (of which you could opt to use only one dimension) composed
of Space objects.  Board allows outside Objects to access specific spaces on the board given their coordinates.
-LegendBoard.java:
    LegendBoard is a reimplimentation of board for L:M&H. It now allows a user to access
the type of a space and keeps track of the main party's position.
-LegendsGame.java:
    LegendsGame serves as a point where all of the data needed to
run a game comes together. Through the LegendsGame class, a user
can access all of the other relevant pieces needed to run a game.
-LegendsIO.java:
    The LegendsIO class deals directly with the user and routing their
commands to the proper game logic while also directing the user
on valid inputs. It handles getting the first information needed to 
actually create a new LegendsGame object, and once the game is running,
is coupled closely with the LegendsActions class.
-LegendsActions.java:
    This class is used to handle all of the possible actions of the player. It contains
all of the necessary background logic and computations that need to be done
for each action.
-LegendsMonstersHeroes.java:
    LegendsMonsterHeroes is the jumping off point for a game of
L:M&H. This class only serves to start up the game initially.

INVENTORY:
-Inventory.java:
    Inventory manages a hero's items that they have stored in their bag
and also equipping and stowing their items. In order to access any
of their items, a player must first access their inventory object.
-InventoryIO.java:
    InventoryIO handles interacting with the user while they are in their inventory.
It takes in user inputs and tries to perform the input actions on the specified
inventory.

MARKET:
-Market.java:
    The Market class simulates a market in the game world.  Here the market's
items and interactions are stored and handled. When a market is created,
it is randomly filled with a bunch of items.
-MarketIO.java:
    MarketIO pulls the user out of the overarching LegendsIO interface
so that they can make more market-specific commands. MarketIO also
has to handle a lot of invalid input loops for when trying to
purchase and item and deciding what hero is purchasing the item.

BATTLE:
-Battle.java:
    The Battle Class represents an ongoing battle within the game
world. It handles the AI behind the monsters and their attacks
and deals with resolving a battle.
-BattleIO.java:
    The BattleIO class is an extension of IO.  This class interacts with the player
during a battle, and gets their commands over each round. On a hero's turn, the
player has to select a target and a possible action for that target.


