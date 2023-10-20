package domain;

//Model
public interface NguoiQuanLy extends Publisher{
    void themSV(SinhVien sinhvien);
    void capnhatSV(SinhVien sinhvien);
    void xoaSV(String maSV);
    void loadDSSV(); 
    SinhVien xemThongTin1SV(String maSV);
}
