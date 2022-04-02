package stas;

import java.util.List;

import stas.entities.Cat;

public class Main {
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			Cat cat = new Cat();
			cat.setName(getRandomName());
			cat.setAge(i + 1);
			HibernateConnector.save(cat);
		}

		List<Cat> cats = HibernateConnector.getAll(Cat.class);
		cats.forEach(System.out::println);
	}

	private static String getRandomName() {
		String[] names = {"Murzik", "Barsik", "Innokentiy", "Stepan", "Venyiamin"};
		return names[(int) (Math.random() * names.length)];
	}
}
