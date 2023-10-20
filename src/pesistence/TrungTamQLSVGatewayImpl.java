package pesistence;

import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;
import domain.*;


public class TrungTamQLSVGatewayImpl implements TrungTamQLSVGateway{
    private Connection connection;

    public TrungTamQLSVGatewayImpl() {
        String DB_NAME = "ltudvj";
        String DB_URL = "jdbc:mysql://localhost:3306/";
        String USER_NAME = "root";
        String PASSWORD = "123456789";
        try {
            connection = DriverManager.getConnection(DB_URL + DB_NAME, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Kết nối không thành công \n" + e.toString(), "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }
    @Override
    public void capnhatSV(SinhVien sinhvien) {
        String sql = "UPDATE sinhvien SET tenSV = ?, nganh = ?, lop = ?, khoa = ?, bac = ?, danghoc = ? WHERE maSV = ?";
        try {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, sinhvien.getTenSV());
                statement.setString(2, sinhvien.getNganh());
                statement.setString(3, sinhvien.getLop());
                statement.setString(4,sinhvien.getKhoa());
                statement.setString(5, sinhvien.getBac());
                statement.setBoolean(6, sinhvien.isDangHoc());
                statement.setString(7, sinhvien.getMaSV()); 
                if (statement.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "Cập nhật thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Cập nhật không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                System.out.println(throwable.getMessage());
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println(throwable.getMessage());
        }
    }


    @Override
    public List<SinhVien> loadDSSV() {
        List<SinhVien> dsSinhVien = new ArrayList<>();
        String sql = "SELECT * FROM SinhVien;";
        try (Statement statement = connection.createStatement()) { 
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String maSV = resultSet.getString("maSV");
                String tenSV = resultSet.getString("tenSV");
                String nganh = resultSet.getString("nganh");
                String lop = resultSet.getString("lop");
                String khoa = resultSet.getString("khoa");
                String bac = resultSet.getString("bac");
                Boolean dangHoc = resultSet.getBoolean("dangHoc");
                dsSinhVien.add(new SinhVien(maSV, tenSV, nganh, lop, khoa, bac, dangHoc));
            }
            return dsSinhVien;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void themSV(SinhVien sinhvien) {
        String sql = "INSERT INTO SinhVien (maSV, tenSV, nganh, lop, khoa, bac, danghoc) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, sinhvien.getMaSV()); 
            statement.setString(2, sinhvien.getTenSV());
            statement.setString(3, sinhvien.getNganh());
            statement.setString(4, sinhvien.getLop());
            statement.setString(5,sinhvien.getKhoa());
            statement.setString(6, sinhvien.getBac());
            statement.setBoolean(7, sinhvien.isDangHoc());
            if (statement.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Thêm không thành công", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public SinhVien xemThongTin1SV(String maSV) {
        String sql = "SELECT * FROM SinhVien WHERE maSV = '" + maSV + "'";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String tenSV = resultSet.getString("tenSV");
                String nganh = resultSet.getString("nganh");
                String lop = resultSet.getString("lop");
                String khoa = resultSet.getString("khoa");
                String bac = resultSet.getString("bac");
                Boolean dangHoc = resultSet.getBoolean("dangHoc");

                return new SinhVien(maSV, tenSV, nganh, lop, khoa, bac, dangHoc);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public void xoaSV(String maSV) {
        try {
            String sql = "DELETE FROM SinhVien WHERE maSV = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, maSV);

                int rowsDeleted = statement.executeUpdate();
                if (rowsDeleted > 0) {
                    JOptionPane.showMessageDialog(null, "Xóa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy sinh viên có mã sinh viên tương ứng", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println(throwable.getMessage());
        }
    }



}
