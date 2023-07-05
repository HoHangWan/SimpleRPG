
public class Attack {
	int power;
	BattleState attacker;
	int type;
	String name;
	
	public Attack(BattleState attacker, int type, int power, String name) {
		this.power = power;
		this.type = type;
		this.attacker = attacker;
		this.name = name;
	}
	
}
