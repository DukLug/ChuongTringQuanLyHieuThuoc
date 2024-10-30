package controller;

import java.util.ArrayList;
import dao.NhanVienDAO;
import entity.NhanVien;

public class NhanVienCTR {
    
    private NhanVienDAO nvDAO;

    // Constructor
    public NhanVienCTR() {
        nvDAO = new NhanVienDAO(); // Khởi tạo DAO để làm việc với cơ sở dữ liệu
    }

    /**
     * Hàm tìm kiếm nhân viên theo mã nhân viên.
     * @param maNV Mã nhân viên cần tìm kiếm.
     * @return Danh sách nhân viên tìm được (ArrayList<NhanVien>).
     */
    public ArrayList<NhanVien> timKiemTheoMaNV(String maNV) {
        return nvDAO.timNhanVienTheoMa(maNV);
    }

    /**
     * Hàm tìm kiếm nhân viên theo số điện thoại.
     * @param sdt Số điện thoại cần tìm kiếm.
     * @return Danh sách nhân viên tìm được (ArrayList<NhanVien>).
     */
    public ArrayList<NhanVien> timKiemTheoSDT(String sdt) {
        return nvDAO.timNhanVienTheoSDT(sdt);
    }

    /**
     * Hàm tìm kiếm nhân viên theo họ tên.
     * @param hoTen Họ tên cần tìm kiếm.
     * @return Danh sách nhân viên tìm được (ArrayList<NhanVien>).
     */
    public ArrayList<NhanVien> timKiemTheoHoTen(String hoTen) {
        return nvDAO.timNhanVienTheoTen(hoTen);
    }

    /**
     * Hàm lấy danh sách tất cả nhân viên.
     * @return Danh sách tất cả nhân viên (ArrayList<NhanVien>).
     */
    public ArrayList<NhanVien> layDanhSachTatCaNhanVien() {
        return nvDAO.layDanhSachTatCaNhanVien();
    }
    
    /**
     * Hàm thêm nhân viên mới vào cơ sở dữ liệu.
     * @param nv Đối tượng NhanVien cần thêm.
     * @return true nếu thêm thành công, false nếu có lỗi xảy ra.
     */
    public boolean themNhanVien(NhanVien nv) {
        return nvDAO.themNhanVien(nv); // Gọi phương thức trong DAO để thêm nhân viên
    }
    public boolean capNhatNhanVien(NhanVien nv) {
        return nvDAO.capNhatNhanVien(nv); // Gọi phương thức trong DAO để cập nhật thông tin nhân viên
    }
    
    
}
