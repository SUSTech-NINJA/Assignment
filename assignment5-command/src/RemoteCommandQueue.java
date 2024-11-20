import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class RemoteCommandQueue {
    Queue<Command> commands;
    Command undoCommand;

    public RemoteCommandQueue() {
        commands = new ArrayDeque<>();
    }

    /**
     * only add command but not execute
     * @param command
     */
    public void buttonPressed(Command command) {
        commands.add(command);
    }

    /**
     * execute the command in the queue by first-in-first-out principle
     * if there is no command in the queue display "no command"
     */
    public void commandExecute() {
        if (commands.isEmpty()) {
            System.out.println("no command");
        } else {
            Command command = commands.poll();
            command.execute();
            undoCommand = command;
        }
    }

    /**
     * undo the previous command
     */
    public void commandUndo() {
        if (undoCommand != null) {
            undoCommand.undo();
            undoCommand = null;
        } else {
            System.out.println("no command to undo");
        }
    }
}