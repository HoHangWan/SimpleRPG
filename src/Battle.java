import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Battle {
	Random rand = new Random();
	ArrayList<BattleState> creatureInvolved;
	ArrayList<PlayerState> playerInvolved;
	ArrayList<EnemyState> enemyInvolved;
	int phrase;
	
	public void start (Enemy enemy, int quantity, Journey jour) {
		
		phrase = 0;
		
		int baseLevel = Guild.playerList.get(0).lev;
		int levelshift =  rand.nextInt(1,10);
		
		if (baseLevel <5)
			enemy.lev = baseLevel;
		else 
		{
			if (levelshift ==1)
				enemy.lev = baseLevel -1;
			else if (levelshift <6)
				enemy.lev = baseLevel;
			else if (levelshift <8)
				enemy.lev = baseLevel +1;
			else if (levelshift <10)
				enemy.lev = baseLevel +2;
			else
				enemy.lev = baseLevel +3;
		}		
		enemy.createRealEnemy();
		
		creatureInvolved = new ArrayList<BattleState>();
		enemyInvolved = new ArrayList<EnemyState>();
		
		for (int i=0; i< quantity; i++) 
		{
			EnemyState ene = new EnemyState(enemy);
			enemyInvolved.add(ene);
			creatureInvolved.add(ene);
		}
		
		playerInvolved = new ArrayList<PlayerState>();
		for (PlayerState state : jour.playersState)
		{
			playerInvolved.add(state);
			creatureInvolved.add(state);
		}
		
		System.out.println("\nBattle Start");
		printAllPlayers();
		printAllEnemies();
		
		firstPhrase();
		
		while (phrase <2)
		{
			startPhrase();
		}
		System.out.println("End of Test.");
	}
		public void firstPhrase() {
			BattleState firstMove = null;
			int max = 0;
			for (BattleState creature: creatureInvolved)
			{
				int check = rand.nextInt(1, creature.rank_remedy) + rand.nextInt(0,creature.check_Stat(5)+1);
				if (check > max)
				{
					max = check;
					firstMove = creature;
				}
			}
			System.out.println("\n" + firstMove.name + " has the highest perception. " + firstMove.name + " moves first.");
			firstMove.action(this);
			damagePhrase();
		}
		public void startPhrase() {
			int round = 1;
			System.out.println("Round " + round + ":");
			for (BattleState creature : creatureInvolved)
				creature.dex_judge = rand.nextInt(1,creature.rank_remedy) + rand.nextInt(0,creature.check_Stat(2)+1);
			
			Collections.sort(creatureInvolved);
			
			for (BattleState creature: creatureInvolved)
			{
				System.out.println("\n" + creature.name + " dex check: " + creature.dex_judge );
				creature.action(this);
				damagePhrase();
				
				if (phrase == 2)
					return;

			}
		}
		
		public void damagePhrase() {

			for (BattleState creature: creatureInvolved)
			{
				
				
				if (creature.check_fatal())
				{
					if (creature instanceof PlayerState)
						playerInvolved.remove(creature);
					else if (creature instanceof EnemyState)
						enemyInvolved.remove(creature);
				}
				
			}	
				if (playerInvolved.isEmpty())
				{
					System.out.println("All players fall. Loses.");
					phrase = 2;
				}
				else if (enemyInvolved.isEmpty())
				{	
					System.out.println("All enemies fall. Wins.");
					phrase = 2;
				}	
		}
		
		public void printAllPlayers() {
			System.out.println("\nPlayers");
			int count = 1;
			for (PlayerState player: playerInvolved)
			{
				System.out.println(count);
				player.printInfo();
				count++;
			}
				
		}
		
		public void printAllEnemies() {
			System.out.println("\nEnemies");
			int count = 1;
			for (EnemyState enemy: enemyInvolved)
			{
				System.out.println(count);
				enemy.printInfo();
				count++;
			}
		}
		
		public void printAll() {
			printAllPlayers();
			printAllEnemies();
		}
		
}
	

