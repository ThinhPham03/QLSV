package presentation;

import presentation.Command.Command;

//Controller - CommandProsser
public class QuanLySVController {
    public void execute(Command command){
        command.execute();
    }
}
