public class StateExample {
    
}
// State interface
interface State {
    void insertCoin();
    void ejectCoin();
    void turnCrank();
    void dispense();
}

// Concrete states
class NoCoinState implements State {
    private VendingMachine machine;

    public NoCoinState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertCoin() {
        System.out.println("Coin inserted");
        machine.setState(machine.getHasCoinState());
    }

    @Override
    public void ejectCoin() {
        System.out.println("No coin to eject");
    }

    @Override
    public void turnCrank() {
        System.out.println("Insert coin first");
    }

    @Override
    public void dispense() {
        System.out.println("Insert coin first");
    }
}

class HasCoinState implements State {
    private VendingMachine machine;

    public HasCoinState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertCoin() {
        System.out.println("Coin already inserted");
    }

    @Override
    public void ejectCoin() {
        System.out.println("Coin returned");
        machine.setState(machine.getNoCoinState());
    }

    @Override
    public void turnCrank() {
        System.out.println("Crank turned");
        machine.setState(machine.getSoldState());
    }

    @Override
    public void dispense() {
        System.out.println("No item dispensed");
    }
}

class SoldState implements State {
    private VendingMachine machine;

    public SoldState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertCoin() {
        System.out.println("Please wait, dispensing item");
    }

    @Override
    public void ejectCoin() {
        System.out.println("Cannot eject coin, item already sold");
    }

    @Override
    public void turnCrank() {
        System.out.println("Turning crank twice doesn't help");
    }

    @Override
    public void dispense() {
        System.out.println("Item dispensed");
        machine.setState(machine.getNoCoinState());
    }
}

// Context
class VendingMachine {
    private State noCoinState;
    private State hasCoinState;
    private State soldState;
    private State state;

    public VendingMachine() {
        noCoinState = new NoCoinState(this);
        hasCoinState = new HasCoinState(this);
        soldState = new SoldState(this);
        state = noCoinState;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getNoCoinState() {
        return noCoinState;
    }

    public State getHasCoinState() {
        return hasCoinState;
    }

    public State getSoldState() {
        return soldState;
    }

    public void insertCoin() {
        state.insertCoin();
    }

    public void ejectCoin() {
        state.ejectCoin();
    }

    public void turnCrank() {
        state.turnCrank();
        state.dispense();
    }
}

public class Main {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();

        machine.insertCoin();
        machine.turnCrank();
    }
}