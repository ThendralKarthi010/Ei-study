public class CoffeeTemplateExample {
    
}
// Abstract class defining the template method
abstract class CoffeeTemplate {
    // Template method
    public final void makeCoffee() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    abstract void boilWater();
    abstract void brew();
    abstract void pourInCup();
    abstract void addCondiments();
}

// Concrete class implementing the template method
class Coffee extends CoffeeTemplate {
    @Override
    void boilWater() {
        System.out.println("Boiling water");
    }

    @Override
    void brew() {
        System.out.println("Dripping Coffee through filter");
    }

    @Override
    void pourInCup() {
        System.out.println("Pouring coffee into cup");
    }

    @Override
    void addCondiments() {
        System.out.println("Adding sugar and milk");
    }
}

public class Main {
    public static void main(String[] args) {
        Coffee coffee = new Coffee();
        coffee.makeCoffee();
    }
}