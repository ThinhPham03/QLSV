package presentation.Command;

import domain.SinhVien;
import domain.NguoiQuanLy;

public class Them extends Command{
    private SinhVien sinhvien;

    public Them(NguoiQuanLy modelRemote, SinhVien sinhvien) {
        super(modelRemote);
        this.sinhvien = sinhvien;
    }
    
    @Override
    public void execute() {
        modelRemote.themSV(sinhvien);
    }
}
