package bg.verbo.project.web.utils;

import java.util.Random;

import bg.verbo.project.web._aux.Config;

public class PlayerUtil {
	private static Random rand = new Random();
	private static String[] firstNames = { "Isaak", "Zia", "David", "Daniel", "Josiah", "Jabex", "Eleazar", "Julia", "Adam", "Zibiah", "Simeon",
			"Jesus", "Matthias", "Angel", "Abram", "Ira", "Mark", "Zion", "Elias", "Kelton", "Dionte", "Ivaylo", "Wren",
			"Stoyan", "Alexey", "Borislav", "Daniil", "Robert",	"Andrey", "Valentin", "Valery", "Katerina", "Timotey", "Slavik",
			"Eoin", "Akim", "Semyon", "Maxim", "Radoslav", "Teodor", "Venedict", "Nikolay", "Makari", "Mladen", "Timur", "Tommy",
			"Waylan", "Yordan", "Artur", "Nazariy", "Jaylan", "Valeri", "Viktor", "Gavrie", "Maksimilian", "Benedikt",
			"Gennadi", "Nazar", "Evgeni", "Alexander", "Dimitur", "Ivan", "Denislav", "Anatoliy", "Histo", "Petar", "Daniel",
			"Matvei", "Plamen", "Boyan", "Arkady", "Vitaly", "Braylen"
	};
	
	private static String[] lastNames = { "Verbovskiy", "Strinski", "Neshev", "Velkov", "Vasilev", "Trifonov", "Vurbanov", "Stoyanov", "Nedelkov",
			"Mladenov", "Georgiev", "Ivanov", "Popov", "Angelov", "Novikov", "Abramovich", "Solovyov", "Morozov", "Kuznetsov", "Zaytsev", "Sokolov", "Vasilyev", "Bogdanov",
			"Vorobyov", "Petrov", "Volkov", "Smirnov", "Lebedev", "Kozlov", "Semyonov", "Silva", "Golubev", "Pavlov", "Vinogradov",
			"Middleton", "LeBlanc", "Miles", "Maximov", "Radoslavov", "Teodoror", "Waters", "Gomez", "Kim", "Makarov", "Jennings", "Barton",
			"Fry", "Baker", "Odling", "Russo", "Neal", "Valeriev", "Viktorov", "May", "Klein", "Long",
			"Kvyatt", "Alexandrov", "Hurst", "Ramsey", "Skywalker", "Walker", "Knapp", "Spence", "Histov", "Petar", "Sheppard",
			"Torres", "Kane", "Boyanov", "Meza", "Pruitt", "Robles"
	};
	
	public static String generateName() {
			return firstNames[rand.nextInt(firstNames.length)] + " " +
				lastNames[rand.nextInt(lastNames.length)];
	}
	
	public static Integer generateHealth() {
		return rand.nextInt(Config.MAX_HEALTH);
	}	
	
	public static Integer generatePower() {
		return rand.nextInt(Config.MAX_POWER);
	}

	public static String generateTypeCode() {
		switch(rand.nextInt(Config.TYPES_COUNT)) {
		case 1:
			return "D01";
		
		case 2:
			return "T01";
			
		case 3:
			return "T02";
			
		case 4:
			return "T012";
			
		default:
			return "T013";
		}
	}
}
