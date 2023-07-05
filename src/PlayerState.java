import java.util.Random;
import java.util.Scanner;

public class PlayerState extends BattleState{
	
	Random rand = new Random();
	
	public PlayerState(Creature c) {
		super(c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void action(Battle field) {
		if (this.state ==0)
			return ;
		
		Scanner input = new Scanner(System.in);
		
		field.printAll();
		
		System.out.println("1 for normal attack, 2 for magic attack");
		
		int option = input.nextInt();
		
		// TODO Auto-generated method stub
		
		
		Attack attack = null;
		if (option == 1)
		{
			int power = rand.nextInt(1,rank_remedy) + rand.nextInt(1,this.check_Stat(0));
			attack = new Attack(this, 0 , power, "Normal Attack");
			
		}
		else 
		{
			int power = rand.nextInt(1,rank_remedy) + rand.nextInt(1,this.check_Stat(3)+5);
			attack = new Attack(this, rand.nextInt(1,15) , power, "Basic Magic");
			attack.name = "Basic " + Element.readele(attack.type) +" Magic";
		}
		
		BattleState target = null;
		
		if (field.enemyInvolved.size()>1)
		{
			System.out.println("Choose the target: (Start from 0)");
			option = input.nextInt();
			target = field.enemyInvolved.get(option);
		}
			
		else
			target = field.enemyInvolved.get(0);
		
		
		System.out.println(this.name + " use " + attack.name + " towards "+ target.name);
		target.be_attacked(field, attack);
		
	}
	
	@Override
	public void be_attacked(Battle field, Attack attack) {
		// TODO Auto-generated method stub
		if (attack.type ==0)
		{
			int counter = rand.nextInt(1,rank_remedy) + rand.nextInt(1,this.check_Stat(0));
			if (counter <= attack.power)
			{
				HP_loss += attack.power;
				System.out.println("Attack success. " + playchar.name + " suffers " + attack.power + " Damage");
				System.out.println(playchar.name + " HP: " + this.getHP() + "/" + this.HP);
			}
			else 
				System.out.println("Attack is not successful with counter " + counter + " over power " + attack.power);
			
		}
		else
		{
			int evasion = rand.nextInt(1,rank_remedy) + rand.nextInt(1,this.check_Stat(2));
			if (evasion <= attack.power)
			{
				
				HP_loss += attack.power;
				System.out.println("Attack success. " + playchar.name + " suffers " + attack.power + " Damage");
				System.out.println(playchar.name + " HP: " + this.getHP() + "/" + this.HP);
			}
			else
				System.out.println("Attack is missed with evasion " + evasion + " over rate " + attack.power);
		}
		
	}

	
}
