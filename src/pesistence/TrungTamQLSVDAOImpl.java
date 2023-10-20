package pesistence;

import java.util.List;

import domain.SinhVien;

public class TrungTamQLSVDAOImpl implements TrungTamQLSVDAO{
    private TrungTamQLSVGateway khoGatewayRemote;
    
    public TrungTamQLSVDAOImpl(TrungTamQLSVGateway khoGatewayRemote) {
        this.khoGatewayRemote = khoGatewayRemote;
    }

    @Override
    public void capnhatSV(SinhVien sinhvien) {
        khoGatewayRemote.capnhatSV(sinhvien);
    }
        
    @Override
    public void themSV(SinhVien sinhvien) {
        khoGatewayRemote.themSV(sinhvien);
    }

    @Override
    public void xoaSV(String maSV) {
        khoGatewayRemote.xoaSV(maSV);
    }

    @Override
    public List<SinhVien> loadDSSV() {
        return khoGatewayRemote.loadDSSV();
    }

    
    @Override
    public SinhVien xemThongTin1SV(String maSV) {
        return khoGatewayRemote.xemThongTin1SV(maSV);
    }
}
