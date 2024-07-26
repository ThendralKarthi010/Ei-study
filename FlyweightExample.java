import java.util.HashMap;
import java.util.Map;

// Flyweight interface
interface Character {
    void display();
}

// Concrete Flyweight
class ConcreteCharacter implements Character {
    private char symbol;

    public ConcreteCharacter(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public void display() {
        System.out.println("Character: " + symbol);
    }
}

// Flyweight Factory
class CharacterFactory {
    private static final Map<Character, Character> characters = new HashMap<>();

    public static Character getCharacter(char symbol) {
        Character character = characters.get(symbol);
        if (character == null) {
            character = new ConcreteCharacter(symbol);
            characters.put(symbol, character);
        }
        return character;
    }
}

public class Main {
    public static void main(String[] args) {
        Character c1 = CharacterFactory.getCharacter('A');
        Character c2 = CharacterFactory.getCharacter('A');
        Character c3 = CharacterFactory.getCharacter('B');

        c1.display();
        c2.display();
        c3.display();
    }
}