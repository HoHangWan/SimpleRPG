import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BattleTest {

	static Enemy woodenDummy;
	static EnemyState woodenState;
	static Enemy Dragonxx;
	static EnemyState dragonState;
	static Dungeon testLand;
	static Battle testBattle;
	
	@BeforeAll
	static void initialise () 
	{	
		Guild.initialise();
		testLand = new Dungeon();
		testLand.createDungeon(1);
		
		
		woodenDummy = new Enemy();
		woodenDummy.createEnemy(testLand,1);
		woodenDummy.rank = 10;
		woodenDummy.status.set(0, 2);
		woodenDummy.status.set(1, 1000);
		woodenDummy.status.set(2, 2);
		
		woodenDummy.name = "Wooden Dummy";
			
		
	}
	
	@Test
	void HPTest() {
		woodenState = new EnemyState(woodenDummy);

		int test_value = woodenState.HP;
		assertEquals( 10*10 + 11*1000, test_value);
	}
	
	
	@Test
	void damageTest() {
		woodenState = new EnemyState(woodenDummy);
		
		Attack dragonSlash = new Attack(dragonState, 0 , 10000, "Dragon Slash");
		woodenState.be_attacked(testBattle, dragonSlash);
		assertEquals(1100, woodenState.getHP());
		assertEquals(10000, woodenState.HP_loss);
	}
	
	@Test 
	void weaknessDamageTest() {
		woodenState = new EnemyState(woodenDummy);
		
		woodenDummy.weakness.add(1);
		Attack frameThrow = new Attack(dragonState, 1 , 5000, "Framethrow");
		woodenState.be_attacked(testBattle, frameThrow);
		assertEquals(5100,woodenState.getHP());
		assertEquals(6000, woodenState.HP_loss);
	}
	
	@Test
	void checkFatalTest() {
		woodenState = new EnemyState(woodenDummy);
		Attack dForce = new Attack(dragonState, 1 , 50000, "Framethrow");
		woodenState.be_attacked(testBattle, dForce);
		assertTrue(woodenState.check_fatal());
	}
	
	@Test
	void buffTest() {
		woodenState = new EnemyState(woodenDummy);
		
		woodenState.buff.set(0,100);
		assertEquals(102, woodenState.check_Stat(0));
		
	}
	
	@Test
	void evasionTest() {
		woodenState = new EnemyState(woodenDummy);
		Attack tackle = new Attack(woodenState, 1 , 1 , "Tackle");
		woodenState.be_attacked(testBattle, tackle);
		assertEquals(0, woodenState.HP_loss);	
	}
	
	@Test
	void counterTest() {
		woodenState = new EnemyState(woodenDummy);
		Attack tackle = new Attack(woodenState, 0 , 1 , "Tackle");
		woodenState.be_attacked(testBattle, tackle);
		assertEquals(0, woodenState.HP_loss);	
	}
	
}
