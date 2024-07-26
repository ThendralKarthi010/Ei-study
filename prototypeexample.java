public class prototypeexample {
    
}
// Prototype interface
interface Shape extends Cloneable {
    Shape clone();
    void draw();
}

// Concrete prototype class
class Rectangle implements Shape {
    @Override
    public Shape clone() {
        Rectangle clone = null;
        try {
            clone = (Rectangle) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }

    @Override
    public void draw() {
        System.out.println("Drawing Rectangle");
    }
}

public class Main {
    public static void main(String[] args) {
        Rectangle originalRectangle = new Rectangle();
        Shape clonedRectangle = originalRectangle.clone();
        clonedRectangle.draw();
    }
}