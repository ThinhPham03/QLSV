package domain;

import java.util.List;
import pesistence.TrungTamQLSVDAO;
import pesistence.TrungTamQLSVDAOImpl;
import pesistence.TrungTamQLSVGatewayImpl;
import presentation.Subscriber;

public class NguoiQuanLyImpl implements NguoiQuanLy {
    private TrungTamQLSVDAO trungtamQuanLyRemote;
    private List<SinhVien> dsSinhVien;

    public NguoiQuanLyImpl() {
        trungtamQuanLyRemote = new TrungTamQLSVDAOImpl(new TrungTamQLSVGatewayImpl());
    }

    @Override
    public void themSV(SinhVien sinhvien) {
        trungtamQuanLyRemote.themSV(sinhvien);
        loadDSSV();
    }

    @Override
    public void capnhatSV(SinhVien sinhvien) {
        trungtamQuanLyRemote.capnhatSV(sinhvien);
        loadDSSV();
    }

    @Override
    public void xoaSV(String maHang) {
        trungtamQuanLyRemote.xoaSV(maHang);
        loadDSSV();
    }

    @Override
    public void loadDSSV() {
        dsSinhVien = trungtamQuanLyRemote.loadDSSV();
        notifySubscribers();
    }

    @Override
    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber); 
    }

    @Override
    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber); 
    }

    @Override
    public void notifySubscribers() {
		for(Subscriber s: subscribers) {
            s.update(dsSinhVien);
	    }
    }

    @Override
    public SinhVien xemThongTin1SV(String maHang) {
        return trungtamQuanLyRemote.xemThongTin1SV(maHang);
    }
}
