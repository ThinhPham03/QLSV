package presentation.Command;

import domain.NguoiQuanLy;

public abstract class Command {
    protected NguoiQuanLy modelRemote;
    
    public Command(NguoiQuanLy modelRemote) {
        this.modelRemote = modelRemote;
    }

    public abstract void execute();
}
