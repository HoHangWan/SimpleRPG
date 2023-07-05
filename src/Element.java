import java.util.ArrayList;
import java.util.Arrays;

public class Element {
	static ArrayList<String> elements = new ArrayList<String>(Arrays.asList("Null","Fire","Water","Metal","Wood","Earth","Wind","Thunder","Star","Light","Dark","Life","Death","Psychic","Space","Time"));
	
	static String readele(int type) {
		return elements.get(type);
	}
	
}
