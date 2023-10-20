package presentation.Command;

import domain.NguoiQuanLy;

public class Xoa extends Command{
    private String maHang;
    
    public Xoa(NguoiQuanLy modelRemote, String maHang) {
        super(modelRemote);
        this.maHang = maHang;
    }

    @Override
    public void execute() {
        modelRemote.xoaSV(maHang);
    }
}
