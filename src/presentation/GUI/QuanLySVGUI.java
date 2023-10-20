package presentation.GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import domain.*;
import presentation.*;
import presentation.Command.*;

//View
public class QuanLySVGUI extends JFrame implements Subscriber{
    private QuanLySVController controllerRemote;
    private NguoiQuanLy modelRemote;
    private DefaultTableModel tableModel;
    private JTable table;
    private JButton themButton, capnhatButton, xoaButton;

    public QuanLySVController getControllerRemote() {
        return controllerRemote;
    }

    public NguoiQuanLy getModelRemote() {
        return modelRemote;
    }

    public QuanLySVGUI() {
        setTitle("Quản lý sinh viên");
        setSize(1280, 400);
        setLocation(320, 340);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        modelRemote = new NguoiQuanLyImpl();
        modelRemote.subscribe(this);
        controllerRemote = new QuanLySVController();

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Mã sinh viên");
        tableModel.addColumn("Tên sinh viên");
        tableModel.addColumn("Chuyên ngành");
        tableModel.addColumn("Lớp học");
        tableModel.addColumn("Khoa");
        tableModel.addColumn("Bậc học");
        tableModel.addColumn("Trạng thái");
        table = new JTable(tableModel);
        table.setDefaultEditor(Object.class, null);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        themButton = new JButton("Thêm");
        themButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                themSV();
            }
        });

        capnhatButton = new JButton("Cập nhật");
        capnhatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                capnhatSVGUI(table.getSelectedRow());
            }
        });

        xoaButton = new JButton("Xóa");
        xoaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xoaSVGUI(table.getSelectedRow());
            }
        });

        JPanel functionPanel = new JPanel(new GridLayout(5, 0, 0, 10));


        functionPanel.add(new Label());
        functionPanel.add(themButton);
        functionPanel.add(capnhatButton);
        functionPanel.add(xoaButton);
        functionPanel.add(new Label());

        add(functionPanel, BorderLayout.EAST);
        xemDSSV();
    }
    
    @Override
    public void update(List<SinhVien> dsSinhVien) {
        while(tableModel.getRowCount() != 0) {
            tableModel.removeRow(0);
        }
        for (SinhVien sinhvien : dsSinhVien) {
            String trangthai;
            if(sinhvien.isDangHoc()) {
                trangthai = "Đang học";
            } else{
                trangthai = "Tạm dừng";
            }
            Object[] rowData = { sinhvien.getMaSV(), sinhvien.getTenSV(), sinhvien.getNganh(), sinhvien.getLop(), sinhvien.getKhoa(), sinhvien.getBac(), trangthai};
            tableModel.addRow(rowData); 
        }
    }

    public void xemDSSV() {
        modelRemote.loadDSSV();
    }
    
    public void themSV() {
        new SinhVienGUI(this, modelRemote, controllerRemote, 0).setVisible(true);
    }

    public void capnhatSVGUI(int rowIndex) {
        String maSV;
        SinhVien sinhvien;
        if( rowIndex == -1) {
            maSV = JOptionPane.showInputDialog(null, "Nhập mã sinh viên của sinh viên cần cập nhật");
            if(maSV != null) {
                sinhvien = xemThongTin1SV(maSV);
                capnhatSV(sinhvien);
            }
        } else{
            maSV = tableModel.getValueAt(rowIndex, 0).toString();
            sinhvien = xemThongTin1SV(maSV);
            capnhatSV(sinhvien);
        }
    }

    public void capnhatSV(SinhVien sinhvien) {
        SinhVienGUI temp = new SinhVienGUI(this, modelRemote, controllerRemote, 1);
        temp.setMaSV(sinhvien.getMaSV());
        temp.getMaSVTextField().setText(sinhvien.getMaSV());
        temp.getTenSVTextField().setText(sinhvien.getTenSV());
        temp.getNganhTextField().setText(sinhvien.getNganh());
        temp.getLopTextField().setText(sinhvien.getLop());
        temp.getKhoaTextField().setText(sinhvien.getKhoa());
        temp.getBacTextField().setText(sinhvien.getBac());
        if(sinhvien.isDangHoc()) {
            temp.getTrangthaiComboBox().setSelectedIndex(0);
        } else{
            temp.getTrangthaiComboBox().setSelectedIndex(1);
        }
        temp.setVisible(true);
    }

    public void xoaSVGUI(int rowIndex) {
        String maHang;
        if( rowIndex == -1) {
            maHang = JOptionPane.showInputDialog(null, "Nhập số mã sinh viên cần xóa");
            if(maHang != null) {
                xoaSV(maHang);
            }
        } else{
            maHang = tableModel.getValueAt(rowIndex, 0).toString();
            xoaSV(maHang);
        }
    }

    public void xoaSV(String maHang) {
        Command xoaHang = new Xoa(modelRemote, maHang);
        controllerRemote.execute(xoaHang);
    }

    public SinhVien xemThongTin1SV(String maHang) {
        return modelRemote.xemThongTin1SV(maHang);
    }
}
