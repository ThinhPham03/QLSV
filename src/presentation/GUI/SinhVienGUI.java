package presentation.GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import domain.*;
import presentation.*;
import presentation.Command.*;

public class SinhVienGUI extends JFrame{
    private JTextField maSVTextField, tenSVTextField, nganhTextField, lopTextField, khoaTextField, bacTextField;
    private JComboBox<String> trangthaiComboBox;
    private JButton luuButton, huyButton;
    private int choice;
    private String maSV;
    
    public SinhVienGUI(QuanLySVGUI viewRemote, NguoiQuanLy modelRemote, QuanLySVController controllerRemote, int choice) {
        this.choice = choice;
        JPanel inputPanel;
        if(choice == 0) {
            inputPanel = new JPanel(new GridLayout(10, 2));
        } else {
            inputPanel = new JPanel(new GridLayout(9, 2));
        }
        maSVTextField = new JTextField();
        tenSVTextField = new JTextField();
        nganhTextField = new JTextField();
        lopTextField = new JTextField();
        khoaTextField = new JTextField();
        bacTextField = new JTextField();
        trangthaiComboBox = new JComboBox<>();
        trangthaiComboBox.setSelectedIndex(-1);
        trangthaiComboBox.addItem("Đang học");
        trangthaiComboBox.addItem("Tạm dừng");
        luuButton = new JButton("Lưu");
        huyButton = new JButton("Hủy");

        luuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkException(modelRemote)) {
                    if(choice == 0) {
                        themSV(modelRemote, controllerRemote);
                        dispose();
                    } else if (choice == 1) {
                        capnhatSV(modelRemote, controllerRemote);
                        dispose();
                    }
                }
            }
        });
        huyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        inputPanel.add(new JLabel());
        inputPanel.add(new JLabel());
        if (choice == 0) {
            inputPanel.add(new JLabel("Mã sinh viên:"));
            inputPanel.add(maSVTextField);
        }
        inputPanel.add(new JLabel("Tên sinh viên:"));
        inputPanel.add(tenSVTextField);
        inputPanel.add(new JLabel("Chuyên ngành:"));
        inputPanel.add(nganhTextField);
        inputPanel.add(new JLabel("Lớp học:"));
        inputPanel.add(lopTextField);
        inputPanel.add(new JLabel("Khoa:"));
        inputPanel.add(khoaTextField);
        inputPanel.add(new JLabel("Bậc:"));
        inputPanel.add(bacTextField);
        inputPanel.add(new JLabel("Trạng thái:"));

        inputPanel.add(trangthaiComboBox);
        inputPanel.add(new JLabel());
        inputPanel.add(new JLabel());
        inputPanel.add(luuButton);
        inputPanel.add(huyButton);

        if (choice == 0) {
            setTitle("Thêm mới thông tin sinh viên");
        } else{
            setTitle("Cập nhật lại thông tin sinh viên");
        }
        setSize(600, 300);
        setLocation(660, 390);    
        setDefaultCloseOperation(SinhVienGUI.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(inputPanel);
    }
    
    public void themSV(NguoiQuanLy modelRemote, QuanLySVController controllerRemote) {
        Command themSV = new Them(modelRemote, luuThongTin());
        controllerRemote.execute(themSV);
    }

    public void capnhatSV(NguoiQuanLy modelRemote, QuanLySVController controllerRemote) {
        Command capnhatSV = new CapNhat(modelRemote, luuThongTin());
        controllerRemote.execute(capnhatSV);
    }

    public SinhVien luuThongTin() {
        String tenSV = tenSVTextField.getText();
        String nganh = nganhTextField.getText();
        String lop = lopTextField.getText();
        String khoa = khoaTextField.getText();
        String bac = bacTextField.getText();
        boolean trangthai;
        if (trangthaiComboBox.getSelectedIndex() == 0) {
            trangthai = true;
        } else{
            trangthai = false;
        }
        if(choice == 0) {
            maSV = maSVTextField.getText();
        }
        return new SinhVien(maSV, tenSV, nganh, lop, khoa, bac, trangthai);
    }

    public boolean checkException(NguoiQuanLy modelRemote) {
        if(choice == 0) {
            if(maSVTextField.getText() == null || maSVTextField.getText().length() > 10) {
                JOptionPane.showMessageDialog(this, "Mã sinh viên là chuỗi 10 kí tự bất kì", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }else if(modelRemote.xemThongTin1SV(maSVTextField.getText()) != null) {
                JOptionPane.showMessageDialog(this, "Mã sinh viên vừa nhập đã tồn tại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        }
        
        if(tenSVTextField.getText() == null) {
            JOptionPane.showMessageDialog(this, "Tên sinh viên không được để trống", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        
        if(nganhTextField.getText() == null) {
            JOptionPane.showMessageDialog(this, "Chuyên ngành không được để trống", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        if(lopTextField.getText() == null) {
            JOptionPane.showMessageDialog(this, "Lớp không được để trống", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        if(khoaTextField.getText() == null) {
            JOptionPane.showMessageDialog(this, "Khoa không được để trống", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        if(trangthaiComboBox.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Trạng thái không được để trống", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        return true;
    }

    public JTextField getMaSVTextField() {
        return maSVTextField;
    }

    public JTextField getTenSVTextField() {
        return tenSVTextField;
    }

    public JTextField getNganhTextField() {
        return nganhTextField;
    }

    public JTextField getLopTextField() {
        return lopTextField;
    }

    public JTextField getKhoaTextField() {
        return khoaTextField;
    }

    public JTextField getBacTextField() {
        return bacTextField;
    }

    public void setMaSV(String maHang) {
        this.maSV = maHang;

    }
    public JComboBox<String> getTrangthaiComboBox() {
        return trangthaiComboBox;
    }
}
