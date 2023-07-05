
import java.util.ArrayList;
import java.util.Random;

import ReadWriteFile.*;

public class Player extends Creature {
	
	int index;
	int exp;
	ArrayList<Integer> growrate = new ArrayList<Integer>();
	Random rand = new Random(); 

//Generate new Player
public void newPlayer() {
	index = Guild.playno;
	StringBuilder builder = new StringBuilder();
	for (int i=0;i<3;i++)
		builder.append((char)rand.nextInt(65,90));
	this.name = builder.toString();
	lev = 1;
	rank = 1;
	for (int i = 0; i <6; i++)
		status.add(rand.nextInt(1, 10));
	growrate = new ArrayList<Integer>();
	for (int i = 0; i <6; i++)
		growrate.add(status.get(i)*10);
	exp=0;
	Guild.playno = Guild.playno+1;
	Guild.playerList.add(this);
}

//Print Status
public void printStat() {
	System.out.println("\nStatus of Player " + name + ":");
	System.out.println("Level: " + lev);
	System.out.println("Rank: " + rank);
	System.out.println("Strength: " + status.get(0));
	System.out.println("Constituton: " + status.get(1));
	System.out.println("Dexterity: " + status.get(2));
	System.out.println("Intelligence: " + status.get(3));
	System.out.println("Mana: " + status.get(4));
	System.out.println("Perception: " + status.get(5));
}

//Save
public void save() {
	PGPFile file = new PGPFile();
	file.openWriteFile("Player" + index+".txt");
	file.writeLine(name + " " + lev + " " + rank + " " + index);
	StringBuilder statusString = new StringBuilder();
	for (int i=0; i<6; i++)
		statusString.append(status.get(i)+" ");
	file.writeLine(statusString.toString());
	StringBuilder grow = new StringBuilder();
	for (int i =0; i < 6; i++)
		grow.append(growrate.get(i)+" ");
	file.writeLine(grow.toString());
	file.closeWriteFile();	
}

public void levelUp() {
	lev = lev + 1;
	for (int i = 0; i<6;i++) {
		int old = rand.nextInt(1,100);
		if (growrate.get(i)>old) {
			status.set(i,status.get(i) +1);
		}

	}
	this.printStat();
}

//Read
public void read(String fileName) {
	PGPFile file = new PGPFile();
	file.openReadFile(fileName);
	String line = file.readNextLine();
	String[] words = line.split(" ");
	name = words[0];
	lev = Integer.valueOf(words[1]);
	rank = Integer.valueOf(words[2]);
	index = Integer.valueOf(words[3]);
	//next line
	line = file.readNextLine();
	words = line.split(" ");
	for (int i = 0; i<6; i++)
		status.add(Integer.valueOf(words[i]));
	line = file.readNextLine();
	words = line.split(" ");
	for (int i = 0; i<6; i++)
		growrate.add(Integer.valueOf(words[i]));
}
public void printGrowth() {
	StringBuilder builder = new StringBuilder();
	for (int i=0; i < 5; i++)
		builder.append(growrate.get(i) + " ");
	builder.append(growrate.get(5));
	System.out.println("Growth Rate: " + builder.toString());
}

}
