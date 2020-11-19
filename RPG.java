
/**
* @Description:  RPG is an abstract class that includes the basic structure of a RPG.Handles players and factories of heroes,items and monsters

* @Author: Zhengyu Zhang
* @Date: 2020/11/19
*/

public abstract class RPG {


    protected Player player;
    protected ItemList itemList;



    protected HeroList heroList;
    protected MonsterList monsterList;

    public RPG(){
        itemList = new ItemList();
        heroList = new HeroList();
        monsterList = new MonsterList();
    }

    public ItemList getItemList() {
        return itemList;
    }

    public MonsterList getMonsterList() {
        return monsterList;
    }

    public Player getPlayer() {
        return player;
    }

    public HeroList getHeroList() {
        return heroList;
    }

}
