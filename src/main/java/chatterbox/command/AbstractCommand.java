package chatterbox.command;

public abstract class AbstractCommand implements Command {
    @Override
    public boolean isExit() {
        return false;
    }
}