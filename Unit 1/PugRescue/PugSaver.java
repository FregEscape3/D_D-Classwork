import java.util.ArrayList;
import java.util.Objects;

public class PugSaver {

	// Moves every dog whose breed is "Pug" in the list to the back of the list
	public static void rescuePugs(ArrayList<Dog> list) {
		for (int i = list.size() - 1; i >= 0; i--) {
			if (list.get(i).getBreed().equals("Pug")) {
				Dog lulu = list.remove(i);
				list.add(lulu);
			}
		}
	}
}
