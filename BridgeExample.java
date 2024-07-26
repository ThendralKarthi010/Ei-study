public class BridgeExample {
    abstract class RemoteControl {
        protected Device device;
    
        public RemoteControl(Device device) {
            this.device = device;
        }
    
        public abstract void turnOn();
        public abstract void turnOff();
    }
    
    // Implementor interface
    interface Device {
        void powerOn();
        void powerOff();
    }
    
    // Concrete implementor
    class TV implements Device {
        @Override
        public void powerOn() {
            System.out.println("TV is now ON");
        }
    
        @Override
        public void powerOff() {
            System.out.println("TV is now OFF");
        }
    }
    
    // Refined abstraction
    class TVRemote extends RemoteControl {
        public TVRemote(Device device) {
            super(device);
        }
    
        @Override
        public void turnOn() {
            device.powerOn();
        }
    
        @Override
        public void turnOff() {
            device.powerOff();
        }
    }
    
    public class Main {
        public static void main(String[] args) {
            Device tv = new TV();
            RemoteControl remote = new TVRemote(tv);
    
            remote.turnOn();
            remote.turnOff();
        }
    }
    
}
// Abstraction
