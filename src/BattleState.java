import java.util.ArrayList;
import java.util.Arrays;

public abstract class BattleState implements Comparable<BattleState>{
String name;
	
	Creature playchar;
	//state for battle, 1 for available, 0 for unavailable
	int state;
	ArrayList<Integer> buff;
	int HP;
	int HP_loss;
	int armour;
	int MP;
	int MP_loss;
	int rank_remedy;
	int dex_judge;

public BattleState(Creature c) {
	playchar = c;
	name = playchar.name;
	state = 1;
	buff = new ArrayList<Integer>(Arrays.asList(0,0,0,0,0,0));
	HP = 10*c.rank + (1+c.rank)* c.status.get(1);
	HP_loss = 0;
	armour = 0;
	MP = 10*c.rank + (1+c.rank)* c.status.get(4);
	MP_loss = 0;
	rank_remedy = c.rank * 10;
	dex_judge = 0;
}

public int check_Stat(int key) {
	int value = playchar.status.get(key) + buff.get(key);
	if (value >0)
		return value;
	else 
		return 0;
}	

public int getHP() {
	int value = HP - HP_loss + armour;
	if (value >0)
		return value;
	else 
		return 0;
}

public int getMP() {
	int value = MP - MP_loss;
	if (value >0)
		return value;
	else 
		return 0;
}

public int getDex_judge() {
	return this.dex_judge;
}

public void printInfo() {
	System.out.println(playchar.name + " level: " + playchar.lev + " rank: " + playchar.rank);
	System.out.println("HP: " + this.getHP() + "/" + HP);
	System.out.println("MP: " + this.getMP() + "/" + MP);
	System.out.println("Strength: " + this.check_Stat(0));
	System.out.println("Constituton: " + this.check_Stat(1));
	System.out.println("Dexterity: " + this.check_Stat(2));
	System.out.println("Intelligence: " + this.check_Stat(3));
	System.out.println("Mana: " + this.check_Stat(4));
	System.out.println("Perception: " + this.check_Stat(5));
	
}

public boolean check_fatal() {
	if (this.getHP()==0)
	{
		state = 0;
		System.out.println(this.name + " 's HP goes to zero. " + this.name + " falls.");
		return true;
	}
	return false;
}

@Override 
public int compareTo(BattleState creature) {
	int comparedex = ((BattleState) creature).getDex_judge();
	return comparedex - this.dex_judge;	
}

abstract public void action(Battle field);

abstract public void be_attacked(Battle field, Attack attack);

}


