package pesistence;

import java.util.List;
import domain.SinhVien;

public interface TrungTamQLSVDAO {
    void themSV(SinhVien sinhvien);
    void capnhatSV(SinhVien sinhvien);
    void xoaSV(String maSV);
    List<SinhVien> loadDSSV(); 
    SinhVien xemThongTin1SV(String maSV);
}
