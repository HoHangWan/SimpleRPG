import java.util.Random;

public class EnemyState extends BattleState {

	Random rand = new Random();
	Enemy playchar;
	
	public EnemyState(Enemy ene) {
		super(ene);
		// TODO Auto-generated constructor stub
		playchar = ene;
	}


	@Override
	public void action(Battle field) {
		// TODO Auto-generated method stub
		if (this.state ==0)
			return ;
		Attack attack = null;
		if (this.check_Stat(0)>this.check_Stat(3))
		{
			int power = rand.nextInt(1,rank_remedy) + rand.nextInt(0,this.check_Stat(0)+1);
			attack = new Attack(this, 0 , power, "Normal Attack");
			
		}
		else
		{
			int power = rand.nextInt(1,rank_remedy) + rand.nextInt(0,this.check_Stat(3)+1);
			attack = new Attack(this, playchar.element , power, "Basic " + Element.readele(playchar.element)+ " Attack");
		}
		
		BattleState target = null;
		if (field.playerInvolved.size()>1)
			target = field.playerInvolved.get(rand.nextInt(0,field.playerInvolved.size()-1));
		else
			target = field.playerInvolved.get(0);
		
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
			int evasion = rand.nextInt(1,rank_remedy) + rand.nextInt(0,this.check_Stat(2)+1);
			if (evasion <= attack.power)
			{
				if (playchar.weakness.contains(attack.type)==true)
				{
					HP_loss += Math.floor(attack.power * 1.2);
					System.out.println("Weakness ("+ Element.readele(attack.type)+ ") is attacked. " + playchar.name + " suffers " + Math.floor(attack.power * 1.2) + " Damage");	
				}
				else
				{
					HP_loss += attack.power;
					System.out.println("Attack success. " + playchar.name + " suffers " + attack.power + " Damage");
				}
				System.out.println(playchar.name + " HP: " + this.getHP() + "/" + this.HP);
			}
			else
				System.out.println("Attack is missed with evasion " + evasion + " over rate " + attack.power);
		}
		
	}

}
