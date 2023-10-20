package presentation.Command;

import domain.SinhVien;
import domain.NguoiQuanLy;

public class CapNhat extends Command{
    private SinhVien sinhvien;

    public CapNhat(NguoiQuanLy modelRemote, SinhVien sinhvien) {
        super(modelRemote);
        this.sinhvien = sinhvien;
    }

    @Override
    public void execute() {
        modelRemote.capnhatSV(sinhvien);
    }
    
}
