package presentation;

import java.util.List;
import domain.SinhVien;

public interface Subscriber {
    void update(List<SinhVien> dsSinhVien);
}
