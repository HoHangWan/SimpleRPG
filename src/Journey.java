import java.util.ArrayList;
import java.util.Random;

public class Journey {
	Dungeon dun;
	int step;
	Random rand = new Random();
	ArrayList<PlayerState> playersState;
	
	public Journey(Dungeon dun) {
		this.dun = dun;
		step = 0;
		playersState = new ArrayList<PlayerState>();
		for (Player p : Guild.playerList)
		{
			PlayerState state = new PlayerState (p);
			playersState.add(state);
		}	
	}
	
	public void move() {
		
		step +=1;
		System.out.println("\nStep "+ step + ":");
		
		
		int luck = rand.nextInt(1,100);
		System.out.println("Luck: " + luck);
		
		if (luck <=20)
			System.out.println("Bad Event Occurs.");
		else if (luck >80)
			System.out.println("Lucky Event Occurs");
		else
			System.out.println("Nothing Special Happens");
		
		int encounter = rand.nextInt(1,100);
		if (encounter < dun.encounterRate)
			System.out.println("Encounter Enemy. Battle!");
			battlePrepare(1);
		
		if (step == dun.depth)
			this.end();
		
	}
	
	public void battlePrepare(int quantity) {
		Enemy enemy = new Enemy();
		
		if (dun.enemyList.isEmpty()==true)
		{
			enemy.createEnemy(dun, 1);
		}
		else 
		{
			int enemyKey = rand.nextInt(1,10);
			if (enemyKey <= dun.enemyList.size())
				enemy = Guild.enemylist.get(dun.enemyList.get(enemyKey));
			else
			{
				enemy.createEnemy(dun, 1);
			}
			
		}
		Battle battle = new Battle();
		battle.start(enemy, quantity, this);
		
	}
	
	
	public void end() {
		
		System.out.println("This is the end of Journey.");
	}
	
}
