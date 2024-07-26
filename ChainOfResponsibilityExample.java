public class ChainOfResponsibilityExample {
    
}
// Handler abstract class
abstract class Logger {
    public static int INFO = 1;
    public static int DEBUG = 2;
    public static int ERROR = 3;

    protected int level;
    protected Logger nextLogger;

    public void setNextLogger(Logger nextLogger) {
        this.nextLogger = nextLogger;
    }

    public void logMessage(int level, String message) {
        if (this.level <= level) {
            write(message);
        }
        if (nextLogger != null) {
            nextLogger.logMessage(level, message);
        }
    }

    abstract protected void write(String message);
}

// Concrete handlers
class ConsoleLogger extends Logger {
    public ConsoleLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Console Logger: " + message);
    }
}

class FileLogger extends Logger {
    public FileLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("File Logger: " + message);
    }
}

public class Main {
    public static void main(String[] args) {
        Logger consoleLogger = new ConsoleLogger(Logger.INFO);
        Logger fileLogger = new FileLogger(Logger.DEBUG);

        consoleLogger.setNextLogger(fileLogger);

        consoleLogger.logMessage(Logger.INFO, "This is an information.");
        consoleLogger.logMessage(Logger.DEBUG, "This is a debug message.");
    }
}