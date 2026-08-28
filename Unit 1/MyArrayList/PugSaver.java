import java.util.ArrayList;
import java.util.Objects;

public class PugSaver {

	// Moves every dog whose breed is "Pug" in the list to the back of the list
	// All non-pugs must remain in the same relative order they were in originally
	// and all pugs must also remain in the same relative order they were in
	// originally
	public static void rescuePugs(ArrayList<Dog> list) {
		ArrayList<Dog> temp = new ArrayList<Dog>();
		for (int i = 0; i < list.size(); i++) {
			Dog dog = list.get(i);
			if (dog == null || dog.getBreed().equals("Pug")) {
				temp.add(dog);
			}
		}
		for (int i = 0; i < list.size(); i++) {
			Dog dog = list.get(i);
			if (dog != null && dog.getBreed().equals("Pug")) {
				temp.add(dog);
			}
		}
		for (int i = 0; i < list.size(); i++) {
			list.set(i, temp.get(i));
		}
	}
}
