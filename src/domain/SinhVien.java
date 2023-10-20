package domain;

public class SinhVien {
    private String maSV, tenSV, nganh, lop, khoa, bac;
    private boolean dangHoc;

    public SinhVien(String maSV, String tenSV, String nganh, String lop, String khoa, String bac, boolean dangHoc) {
        this.maSV = maSV;
        this.tenSV = tenSV;
        this.nganh = nganh;
        this.lop = lop;
        this.khoa = khoa;
        this.bac = bac;
        this.dangHoc = dangHoc;
    }
    public String getMaSV() {
        return maSV;
    }
    public String getTenSV() {
        return tenSV;
    }
    public String getNganh() {
        return nganh;
    }
    public String getLop() {
        return lop;
    }
    public String getKhoa() {
        return khoa;
    }
    public String getBac() {
        return bac;
    }
    public boolean isDangHoc() {
        return dangHoc;
    }
}
