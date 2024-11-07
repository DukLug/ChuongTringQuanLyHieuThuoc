create database NhaThuocLuongTam
use NhaThuocLuongTam
-- Table: LoaiSanPham
CREATE TABLE LoaiSanPham (
    MaLoai NVARCHAR(6) NOT NULL PRIMARY KEY,
    TenLoai NVARCHAR(20) NOT NULL
);
-- Table: NhaCungCap
CREATE TABLE NhaCungCap (
    MaNhaCungCap NVARCHAR(9) NOT NULL PRIMARY KEY,
    TenNhaCungCap NVARCHAR(50) NOT NULL,
	Sdt VARCHAR(14) NOT NULL CHECK (LEN(Sdt) >= 10 AND LEN(Sdt) <= 14),
	Email NVARCHAR(50) NOT NULL CHECK (Email LIKE '%@%.%'),
    DiaChi NVARCHAR(255) NOT NULL
);

-- Table: SanPham
CREATE TABLE SanPhamYTe (
    MaSanPham NVARCHAR(8) NOT NULL PRIMARY KEY,
    TenSanPham NVARCHAR(200) NOT NULL,
	NgaySanXuat DATE NOT NULL CHECK (NgaySanXuat <= GETDATE()),
	HanSuDung DATE NOT NULL,
	NuocSanXuat NVARCHAR(50) NOT NULL,
	TrangThai NVARCHAR(20) NOT NULL ,--CHECK (TrangThai IN ('Đang bán', 'Hết hàng', 'Ngưng bán'))
	GhiChu NVARCHAR(255),
	MoTa NVARCHAR(255),
	DangBaoChe NVARCHAR(50),
	Thue FLOAT NOT NULL CHECK (Thue >= 0 AND Thue <= 1),
	ThanhPhan NVARCHAR(255),
	DonViTinh NVARCHAR(20) NOT NULL ,--CHECK (DonViTinh IN ('Hộp', 'Vỉ', 'Viên'))
	MaNhaCungCap NVARCHAR(9) NOT NULL,
    MaLoai NVARCHAR(6) NOT NULL,
    GiaBan DECIMAL(19,4) NOT NULL CHECK (GiaBan > 0),
    FOREIGN KEY (MaLoai) REFERENCES LoaiSanPham(MaLoai),
	FOREIGN KEY (MaNhaCungCap) REFERENCES NhaCungCap(MaNhaCungCap) 
);
-- Table: NhanVien
CREATE TABLE NhanVien (
    MaNhanVien NVARCHAR(8) NOT NULL PRIMARY KEY,
    HoTen NVARCHAR(50) NOT NULL,
	Sdt VARCHAR (10) NOT NULL CHECK (LEN(Sdt) = 10),
	Cccd  VARCHAR (20) NOT NULL CHECK (LEN(Cccd) = 12),
	NgaySinh DATE NOT NULL CHECK (NgaySinh <= GETDATE() AND DATEDIFF(YEAR, NgaySinh, GETDATE()) >= 18),
	GioiTinh NVARCHAR(20) NOT NULL, -- CHECK (GioiTinh IN ('Nam', 'Nữ'))
    ChucVu NVARCHAR(20) NOT NULL ,--CHECK (ChucVu IN ('Nhân viên bán hàng', 'Chủ cửa hàng'))
    TrangThaiLamViec NVARCHAR(20) NOT NULL --CHECK (TrangThaiLamViec IN ('Đang làm', 'Đã nghỉ việc'))
);

-- Table: DonNhapHang
CREATE TABLE DonNhapHang (
    MaDonNhap NVARCHAR(14) NOT NULL PRIMARY KEY,
    NgayNhap DATE NOT NULL CHECK (NgayNhap <= GETDATE()),
    MaNhanVien NVARCHAR(8) NOT NULL,
    FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNhanVien)
);
-- Table: ChiTietDonNhap
CREATE TABLE ChiTietDonNhap (
	MaChiTietDonNhap NVARCHAR(18) NOT NULL PRIMARY KEY,
    MaSanPham NVARCHAR(8) NOT NULL ,
    SoLuong INT NOT NULL CHECK (SoLuong > 0),
    GiaNhap DECIMAL(19,4) NOT NULL CHECK (GiaNhap > 0),
	MaDonNhap NVARCHAR(14) NOT NULL,
    FOREIGN KEY (MaDonNhap) REFERENCES DonNhapHang(MaDonNhap),
    FOREIGN KEY (MaSanPham) REFERENCES SanPhamYTe(MaSanPham)
);


-- Table: TaiKhoan
CREATE TABLE TaiKhoan (
    MaTaiKhoan NVARCHAR(8) NOT NULL PRIMARY KEY,
    TenDangNhap NVARCHAR(20) NOT NULL,
    MatKhau VARCHAR(20) NOT NULL CHECK (LEN(MatKhau) >= 8 AND MatKhau LIKE '%[0-9]%' AND MatKhau LIKE '%[A-Za-z]%'),
    TrangThai NVARCHAR(20) NOT NULL  , --CHECK (TrangThai IN ('Hoạt động', 'Ngưng hoạt động'))
    FOREIGN KEY (MaTaiKhoan) REFERENCES NhanVien(MaNhanVien)
);

-- Table: LoHang
CREATE TABLE LoHang (
    MaLo NVARCHAR(14) NOT NULL PRIMARY KEY,
	NgaySanXuat DATE NOT NULL CHECK (NgaySanXuat <= GETDATE()),
	GiaNhap DECIMAL(19,4) NOT NULL CHECK (GiaNhap > 0),
    SoLuong INT NOT NULL CHECK (SoLuong > 0),
	ViTri NVARCHAR(20) NOT NULL,
	MaSanPham NVARCHAR(8) NOT NULL,
	MaChiTietDonNhap NVARCHAR(18) NOT NULL,
    FOREIGN KEY (MaSanPham) REFERENCES SanPhamYTe(MaSanPham),
	FOREIGN KEY (MaChiTietDonNhap) REFERENCES ChiTietDonNhap(MaChiTietDonNhap)
);

-- Table: DonHuyHang
CREATE TABLE DonHuyHang (
    MaDonHuyHang NVARCHAR(8) NOT NULL PRIMARY KEY,
    NgayHuy DATE NOT NULL CHECK (NgayHuy <= GETDATE()),
	soLuong INT NOT NULL CHECK (SoLuong > 0),
	MaLoHang NVARCHAR(14) NOT NULL,
	MaNhanVien NVARCHAR(8) NOT NULL,
    FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNhanVien),
	FOREIGN KEY (MaLoHang) REFERENCES LoHang(MaLo)
);


-- Table: KhachHang
CREATE TABLE KhachHang (
    MaKhachHang NVARCHAR(8) NOT NULL PRIMARY KEY,
    HoTen NVARCHAR(50) ,
	Sdt VARCHAR(14) CHECK ((LEN(Sdt) >= 10 AND LEN(Sdt) <= 14) OR LEN(Sdt) = 0),
	Cccd NVARCHAR(12)  CHECK (LEN(Cccd) = 12 OR LEN(Cccd) = 0),
    DiaChi NVARCHAR(255),
	DiemTichLuy INT NOT NULL CHECK (DiemTichLuy >= 0)
);

-- Table: KhuyenMai
CREATE TABLE KhuyenMai (
    MaKhuyenMai NVARCHAR(12) NOT NULL PRIMARY KEY,
	NgayBatDau DATE NOT NULL CHECK (NgayBatDau <= GETDATE()),
	NgayKetThuc DATE NOT NULL ,
    DieuKien NVARCHAR(50),
    ChietKhau FLOAT  NOT NULL CHECK (ChietKhau >= 0 AND ChietKhau <= 1),
	MaNhanVien NVARCHAR(8),
	FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNhanVien),
);

-- Table: HoaDon
CREATE TABLE HoaDon (
    MaHoaDon NVARCHAR(14) NOT NULL PRIMARY KEY,
	NgayTao DATE NOT NULL CHECK (NgayTao <= GETDATE()),
	DiemSuDung INT NOT NULL CHECK (DiemSuDung >= 0),
	ThanhTien DECIMAL(19,4) NOT NULL CHECK (ThanhTien >= 0),
    MaNhanVien NVARCHAR(8) NOT NULL,
    MaKhuyenMai NVARCHAR(12),
	MaKhachHang NVARCHAR(8) NOT NULL,
    FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNhanVien),
    FOREIGN KEY (MaKhuyenMai) REFERENCES KhuyenMai(MaKhuyenMai),
	FOREIGN KEY (MaKhachHang) REFERENCES KhachHang(MaKhachHang)
);

-- Table: ChiTietHoaDon
CREATE TABLE ChiTietHoaDon (
	MaChiTietHoaDon NVARCHAR(16) NOT NULL PRIMARY KEY,
    SoLuong INT NOT NULL CHECK (SoLuong > 0),
    TongTien DECIMAL(19,4) NOT NULL CHECK (TongTien > 0),
	MaHoaDon NVARCHAR(14) NOT NULL,
	MaSanPham NVARCHAR(8) NOT NULL,
	MaLo NVARCHAR(14) NOT NULL,
	MaLoHangThayThe NVARCHAR(14),
    FOREIGN KEY (MaHoaDon) REFERENCES HoaDon(MaHoaDon),
    FOREIGN KEY (MaSanPham) REFERENCES SanPhamYTe(MaSanPham),
	FOREIGN KEY (MaLo) REFERENCES LoHang(MaLo),
	FOREIGN KEY (MaLoHangThayThe) REFERENCES LoHang(MaLo),
);

-- đơn đổi trả
CREATE TABLE DonDoiTra (
    MaDonDoiTra NVARCHAR(17) NOT NULL PRIMARY KEY,
	NgayDoiTra DATE NOT NULL CHECK (NgayDoiTra <= GETDATE()),
	TienHoan DECIMAL(19,4) NOT NULL ,
    MaNhanVien NVARCHAR(8) NOT NULL,
    MaKhuyenMai NVARCHAR(12),
	MaKhachHang NVARCHAR(8) NOT NULL,
	MaHoaDon NVARCHAR(14) NOT NULL,
    FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNhanVien),
    FOREIGN KEY (MaKhuyenMai) REFERENCES KhuyenMai(MaKhuyenMai),
	FOREIGN KEY (MaKhachHang) REFERENCES KhachHang(MaKhachHang),
	FOREIGN KEY (MaHoaDon) REFERENCES HoaDon(MaHoaDon)
);

-- chi tiết đơn đổi trả
CREATE TABLE ChiTietDoiTra (
	MaChiTietDoiTra NVARCHAR(19) NOT NULL PRIMARY KEY,
    SoLuong INT NOT NULL CHECK (SoLuong > 0),
    GiaBan DECIMAL(19,4) NOT NULL CHECK (GiaBan > 0),
	MaDonDoiTra NVARCHAR(17) NOT NULL,
	MaSanPham NVARCHAR(8) NOT NULL,
	MaLo NVARCHAR(14) NOT NULL,
	MaLoHangThayThe NVARCHAR(14),
    FOREIGN KEY (MaDonDoiTra) REFERENCES DonDoiTra(MaDonDoiTra),
    FOREIGN KEY (MaSanPham) REFERENCES SanPhamYTe(MaSanPham),
	FOREIGN KEY (MaLo) REFERENCES LoHang(MaLo),
	FOREIGN KEY (MaLoHangThayThe) REFERENCES LoHang(MaLo),
);
---------------------------------------------------------------------------
--insert dữ liệu
--Loại sản phẩm
INSERT INTO LoaiSanPham (MaLoai, TenLoai)
VALUES
('LSP001', N'Thuốc kháng sinh'),
('LSP002', N'Thuốc giảm đau'),
('LSP003', N'Thuốc hạ sốt'),
('LSP004', N'Thuốc tiêu hóa'),
('LSP005', N'Thuốc kháng viêm'),
('LSP006', N'Thuốc bổ'),
('LSP007', N'Thực phẩm chức năng'),
('LSP008', N'Vật tư y tế'),
('LSP009', N'Thuốc hướng thần');

-- nhà cung cấp
INSERT INTO NhaCungCap (MaNhaCungCap, TenNhaCungCap, Sdt, Email, DiaChi)
VALUES 
('NCC000001', N'Nhà cung cấp Dược phẩm An Bình', '0912345678', 'anbinh@duocpham.com', N'Số 123, Đường ABC, Quận 1, TP.HCM'),
('NCC000002', N'Nhà cung cấp Dược phẩm Hoàng Gia', '0987654321', 'hoanggia@duocpham.vn', N'Số 456, Đường DEF, Quận 2, Hà Nội'),
('NCC000003', N'Nhà cung cấp Y tế Hòa Bình', '0909123456', 'hoabinh@yte.vn', N'Số 789, Đường GHI, Quận 3, TP. Đà Nẵng'),
('NCC000004', N'Thực phẩm chức năng Việt', '0989123456', 'vietthucpham@tpcn.com', N'Số 321, Đường JKL, Quận 4, TP.HCM'),
('NCC000005', N'Nhà cung cấp Vật tư Y tế Đông Á', '0901234567', 'donga@yte.vn', N'Số 654, Đường MNO, Quận 5, Hà Nội'),
('NCC000006', N'Nhà cung cấp Y tế Nam Phong', '0934567890', 'namphong@yte.com', N'Số 987, Đường PQR, Quận 6, TP. Đà Nẵng'),
('NCC000007', N'Thực phẩm chức năng An Lạc', '0912345679', 'anlac@tpcn.com', N'Số 159, Đường STU, Quận 7, TP.HCM'),
('NCC000008', N'Nhà cung cấp Dược phẩm Phú Quốc', '0976543210', 'phuquoc@duocpham.com', N'Số 753, Đường VWX, Quận 8, Hà Nội'),
('NCC000009', N'Nhà cung cấp Vật tư Y tế Thành Đạt', '0912345680', 'thanhdat@yte.vn', N'Số 951, Đường YZA, Quận 9, TP. Đà Nẵng'),
('NCC000010', N'Thực phẩm chức năng Hồng Phát', '0908765432', 'hongphat@tpcn.com', N'Số 852, Đường BCD, Quận 10, TP.HCM');

-- sản phẩm y tế
INSERT INTO SanPhamYTe (MaSanPham, TenSanPham, NgaySanXuat, HanSuDung, NuocSanXuat, TrangThai, GhiChu, MoTa, DangBaoChe, Thue, ThanhPhan, DonViTinh, MaNhaCungCap, MaLoai, GiaBan)
VALUES
-- thuốc kháng sinh (LSP001)
('SP000001', N'Amoxicillin', '2023-01-10', '2025-01-10', N'Việt Nam', N'Đang bán', N'Uống 2-3 lần/ngày', N'Trị nhiễm khuẩn đường hô hấp, tai, mũi, họng', N'Viên nén', 0.05, N'Amoxicillin, Acid clavulanic', N'Hộp', 'NCC000001', 'LSP001', 150000),
('SP000002', N'Cefuroxime', '2022-06-15', '2024-06-15', N'Pháp', N'Đang bán', N'Uống 2 lần/ngày', N'Trị viêm phổi, viêm xoang và nhiễm trùng da', N'Viên nén', 0.05, N'Cefuroxime, Sodium chloride', N'Hộp', 'NCC000002', 'LSP001', 200000),
('SP000003', N'Azithromycin', '2023-03-20', '2025-03-20', N'Mỹ', N'Hết hàng', N'Uống 1 lần/ngày', N'Dùng cho viêm họng, viêm phổi, viêm tai giữa', N'Viên nén', 0.08, N'Azithromycin, Lactose monohydrate', N'Hộp', 'NCC000003', 'LSP001', 180000),
('SP000004', N'Doxycycline', '2023-07-18', '2025-07-18', N'Nhật Bản', N'Đang bán', N'Uống 1-2 lần/ngày', N'Trị nhiễm khuẩn đường tiết niệu, sinh dục', N'Viên nang', 0.07, N'Doxycycline, Magnesium stearate', N'Vỉ', 'NCC000004', 'LSP001', 175000),
('SP000005', N'Gentamicin', '2023-02-05', '2025-02-05', N'Hàn Quốc', N'Ngưng bán', N'Tiêm theo chỉ định bác sĩ', N'Trị nhiễm khuẩn nặng ở phổi, da, xương', N'Dung dịch tiêm', 0.09, N'Gentamicin, Sodium sulfate', N'Hộp', 'NCC000005', 'LSP001', 155000),
('SP000006', N'Ciprofloxacin', '2022-11-20', '2024-11-20', N'Thái Lan', N'Đang bán', N'Uống 2 lần/ngày', N'Trị nhiễm khuẩn niệu đạo, dạ dày và da', N'Viên nén', 0.06, N'Ciprofloxacin, Hydrochloride', N'Hộp', 'NCC000006', 'LSP001', 210000),
('SP000007', N'Vancomycin', '2023-05-10', '2025-05-10', N'Mỹ', N'Đang bán', N'Tiêm truyền theo chỉ định bác sĩ', N'Dùng cho nhiễm khuẩn nặng do vi khuẩn kháng thuốc', N'Dung dịch tiêm', 0.08, N'Vancomycin, Dextrose', N'Hộp', 'NCC000007', 'LSP001', 230000),
('SP000008', N'Sulfamethoxazole', '2023-08-22', '2026-08-22', N'Việt Nam', N'Đang bán', N'Uống 2 lần/ngày', N'Trị nhiễm khuẩn tiết niệu và phổi', N'Viên nén', 0.07, N'Sulfamethoxazole, Trimethoprim', N'Hộp', 'NCC000008', 'LSP001', 140000),
('SP000009', N'Penicillin', '2022-09-15', '2024-09-15', N'Trung Quốc', N'Đang bán', N'Uống theo chỉ định bác sĩ', N'Trị nhiễm khuẩn do liên cầu và tụ cầu khuẩn', N'Viên nang', 0.05, N'Penicillin G, Potassium', N'Vỉ', 'NCC000009', 'LSP001', 160000),
('SP000010', N'Meropenem', '2023-01-10', '2025-01-10', N'Anh', N'Đang bán', N'Tiêm truyền theo chỉ định bác sĩ', N'Trị nhiễm khuẩn nặng toàn thân', N'Dung dịch tiêm', 0.06, N'Meropenem, Sodium bicarbonate', N'Hộp', 'NCC000010', 'LSP001', 270000),
-- thuốc giảm dau (LSP002)
('SP000011', N'Paracetamol', '2023-05-10', '2025-05-10', N'Việt Nam', N'Đang bán', N'Uống 1-2 viên mỗi 6 tiếng', N'Giảm đau và hạ sốt', N'Viên nén', 0.05, N'Paracetamol', N'Viên', 'NCC000001', 'LSP002', 5000),
('SP000012', N'Ibuprofen', '2023-06-15', '2025-06-15', N'Mỹ', N'Đang bán', N'Uống 1 viên mỗi 8 tiếng', N'Giảm đau và kháng viêm', N'Viên nén', 0.06, N'Ibuprofen', N'Viên', 'NCC000002', 'LSP002', 8000),
('SP000013', N'Aspirin', '2023-04-20', '2025-04-20', N'Nhật Bản', N'Ngưng bán', N'Uống 1 viên mỗi ngày', N'Giảm đau và ngừa đông máu', N'Viên nén', 0.07, N'Aspirin', N'Viên', 'NCC000003', 'LSP002', 6000),
('SP000014', N'Naproxen', '2023-03-30', '2025-03-30', N'Hàn Quốc', N'Đang bán', N'Uống 1 viên mỗi 12 tiếng', N'Giảm đau và kháng viêm', N'Viên nén', 0.05, N'Naproxen', N'Viên', 'NCC000004', 'LSP002', 7000),
('SP000015', N'Diclofenac', '2023-01-15', '2025-01-15', N'Việt Nam', N'Đang bán', N'Uống 1 viên mỗi 8 tiếng', N'Giảm đau và kháng viêm', N'Viên nén', 0.06, N'Diclofenac', N'Viên', 'NCC000005', 'LSP002', 9000),
('SP000016', N'Ketorolac', '2023-02-10', '2025-02-10', N'Mỹ', N'Đang bán', N'Uống 1 viên mỗi 6 tiếng', N'Giảm đau mạnh', N'Viên nén', 0.07, N'Ketorolac', N'Viên', 'NCC000006', 'LSP002', 12000),
('SP000017', N'Codeine', '2023-04-05', '2025-04-05', N'Nhật Bản', N'Đang bán', N'Uống 1 viên mỗi 6 tiếng', N'Giảm đau mãn tính', N'Viên nén', 0.06, N'Codeine', N'Viên', 'NCC000007', 'LSP002', 10000),
('SP000018', N'Oxycodone', '2023-03-25', '2025-03-25', N'Hàn Quốc', N'Ngưng bán', N'Uống 1 viên mỗi 12 tiếng', N'Giảm đau mãn tính', N'Viên nén', 0.08, N'Oxycodone', N'Viên', 'NCC000008', 'LSP002', 15000),
('SP000019', N'Tylenol', '2023-05-01', '2025-05-01', N'Việt Nam', N'Đang bán', N'Uống 1-2 viên mỗi 6 tiếng', N'Giảm đau và hạ sốt', N'Viên nén', 0.04, N'Paracetamol', N'Viên', 'NCC000009', 'LSP002', 4000),
('SP000020', N'Fenoprofen', '2023-06-01', '2025-06-01', N'Mỹ', N'Đang bán', N'Uống 1 viên mỗi 8 tiếng', N'Giảm đau và kháng viêm', N'Viên nén', 0.05, N'Fenoprofen', N'Viên', 'NCC000010', 'LSP002', 7500),
-- thuốc hạ sốt (LSP003)
('SP000021', N'Paracetamol', '2023-01-15', '2025-01-15', N'Việt Nam', N'Đang bán', N'Uống tối đa 4 viên/ngày', N'Tri hạ sốt và giảm đau', N'Viên nén', 0.05, N'Paracetamol, Calcium carbonate', N'Vỉ', 'NCC000001', 'LSP003', 70000),
('SP000022', N'Ibuprofen', '2022-12-10', '2024-12-10', N'Pháp', N'Đang bán', N'Uống 1-2 lần/ngày', N'Tri hạ sốt và đau nhức', N'Viên nang', 0.06, N'Ibuprofen, Sodium chloride', N'Hộp', 'NCC000002', 'LSP003', 80000),
('SP000023', N'Acetaminophen', '2023-02-20', '2025-02-20', N'Mỹ', N'Hết hàng', N'Uống 2-3 lần/ngày', N'Tri hạ sốt, giảm đau nhẹ', N'Viên nén', 0.04, N'Acetaminophen, Gelatin', N'Viên', 'NCC000003', 'LSP003', 60000),
('SP000024', N'Fenoprofen', '2023-03-01', '2025-03-01', N'Nhật Bản', N'Ngưng bán', N'Uống 2 lần/ngày', N'Tri hạ sốt và giảm đau', N'Viên nang', 0.05, N'Fenoprofen, Starch', N'Hộp', 'NCC000004', 'LSP003', 90000),
('SP000025', N'Dexamethasone', '2023-04-15', '2025-04-15', N'Hàn Quốc', N'Đang bán', N'Uống theo chỉ định bác sĩ', N'Tri sốt cao và viêm', N'Viên sủi', 0.07, N'Dexamethasone, Lactic acid', N'Vỉ', 'NCC000005', 'LSP003', 150000),
('SP000026', N'Kétorolac', '2023-01-30', '2025-01-30', N'Thái Lan', N'Đang bán', N'Uống theo chỉ định bác sĩ', N'Tri sốt và đau', N'Viên nén', 0.06, N'Kétorolac, Tromethamine', N'Hộp', 'NCC000006', 'LSP003', 85000),
('SP000027', N'Piroxicam', '2022-11-05', '2024-11-05', N'Việt Nam', N'Đang bán', N'Uống 1 lần/ngày', N'Tri hạ sốt và giảm viêm', N'Viên nang', 0.05, N'Piroxicam, Starch', N'Viên', 'NCC000007', 'LSP003', 78000),
('SP000028', N'Naproxen', '2023-06-15', '2025-06-15', N'Mỹ', N'Đang bán', N'Uống 2 lần/ngày', N'Tri hạ sốt và viêm', N'Viên nén', 0.06, N'Naproxen, Magnesium', N'Hộp', 'NCC000008', 'LSP003', 95000),
('SP000029', N'Aspirin', '2023-05-22', '2025-05-22', N'Anh', N'Đang bán', N'Uống theo chỉ định bác sĩ', N'Tri hạ sốt và giảm đau', N'Viên nén', 0.07, N'Aspirin, Starch', N'Hộp', 'NCC000009', 'LSP003', 85000),
('SP000030', N'Codeine', '2023-02-10', '2025-02-10', N'Pháp', N'Ngưng bán', N'Uống theo chỉ định bác sĩ', N'Tri đau và hạ sốt', N'Viên sủi', 0.08, N'Codeine, Phosphate', N'Vỉ', 'NCC000010', 'LSP003', 80000),
-- thuốc tiêu hóa (LSP004)
('SP000031', N'Omeprazole', '2023-01-12', '2025-01-12', N'Việt Nam', N'Đang bán', N'Uống 1 lần/ngày trước bữa ăn', N'Tri trào ngược dạ dày, viêm loét', N'Viên nang', 0.05, N'Omeprazole, Magnesium', N'Viên', 'NCC000001', 'LSP004', 90000),
('SP000032', N'Ranitidine', '2022-11-10', '2024-11-10', N'Pháp', N'Đang bán', N'Uống 1 lần/ngày vào buổi tối', N'Tri loét dạ dày và giảm axit', N'Viên nén', 0.05, N'Ranitidine, Lactose', N'Hộp', 'NCC000002', 'LSP004', 75000),
('SP000033', N'Lansoprazole', '2023-02-15', '2025-02-15', N'Mỹ', N'Ngưng bán', N'Uống 1 lần/ngày trước bữa ăn', N'Tri trào ngược dạ dày', N'Viên nang', 0.06, N'Lansoprazole, Sodium', N'Viên', 'NCC000003', 'LSP004', 85000),
('SP000034', N'Pantoprazole', '2023-05-20', '2025-05-20', N'Nhật Bản', N'Đang bán', N'Uống 1 lần/ngày trước bữa ăn', N'Tri loét dạ dày và giảm axit', N'Viên nén', 0.06, N'Pantoprazole, Sodium bicarbonate', N'Hộp', 'NCC000004', 'LSP004', 95000),
('SP000035', N'Famotidine', '2023-03-22', '2025-03-22', N'Hàn Quốc', N'Đang bán', N'Uống 1 lần/ngày trước bữa ăn', N'Tri viêm loét dạ dày và trào ngược', N'Viên nang', 0.05, N'Famotidine, Calcium', N'Viên', 'NCC000005', 'LSP004', 90000),
('SP000036', N'Sucralfate', '2023-01-15', '2025-01-15', N'Thái Lan', N'Đang bán', N'Uống 1-2 lần/ngày', N'Tri viêm loét dạ dày', N'Viên nén', 0.05, N'Sucralfate, Starch', N'Hộp', 'NCC000006', 'LSP004', 80000),
('SP000037', N'Domperidone', '2022-09-15', '2024-09-15', N'Việt Nam', N'Ngưng bán', N'Uống 1 lần/ngày', N'Tri buồn nôn và nôn', N'Viên nang', 0.06, N'Domperidone, Starch', N'Viên', 'NCC000007', 'LSP004', 70000),
('SP000038', N'Bismuth', '2023-06-30', '2025-06-30', N'Mỹ', N'Đang bán', N'Uống 1 lần/ngày', N'Tri tiêu chảy và khó tiêu', N'Viên nén', 0.07, N'Bismuth, Subsalicylate', N'Hộp', 'NCC000008', 'LSP004', 78000),
('SP000039', N'Proton Pump Inhibitor', '2023-04-12', '2025-04-12', N'Anh', N'Đang bán', N'Uống 1 lần/ngày', N'Tri các bệnh dạ dày', N'Viên nang', 0.06, N'Proton Pump Inhibitor', N'Vỉ', 'NCC000009', 'LSP004', 80000),
('SP000040', N'Esomeprazole', '2023-03-28', '2025-03-28', N'Pháp', N'Đang bán', N'Uống 1 lần/ngày', N'Tri bệnh trào ngược dạ dày', N'Viên nén', 0.07, N'Esomeprazole, Magnesium', N'Hộp', 'NCC000010', 'LSP004', 95000),
-- thuốc kháng viêm (LSP005)
('SP000041', N'Ibuprofen', '2023-04-10', '2025-04-10', N'Việt Nam', N'Đang bán', N'Uống 1-2 lần/ngày', N'Tri viêm và đau', N'Viên nén', 0.05, N'Ibuprofen, Starch', N'Hộp', 'NCC000001', 'LSP005', 80000),
('SP000042', N'Ketoprofen', '2023-01-05', '2025-01-05', N'Nhật Bản', N'Đang bán', N'Uống 1 lần/ngày', N'Tri viêm khớp', N'Viên nang', 0.06, N'Ketoprofen, Magnesium', N'Viên', 'NCC000002', 'LSP005', 90000),
('SP000043', N'Naproxen', '2022-11-10', '2024-11-10', N'Mỹ', N'Ngưng bán', N'Uống theo chỉ định bác sĩ', N'Tri viêm và giảm đau', N'Viên nén', 0.05, N'Naproxen, Sodium', N'Hộp', 'NCC000003', 'LSP005', 95000),
('SP000044', N'Diclofenac', '2023-03-15', '2025-03-15', N'Pháp', N'Đang bán', N'Uống 1 lần/ngày', N'Tri viêm và đau', N'Viên nang', 0.06, N'Diclofenac, Sodium', N'Viên', 'NCC000004', 'LSP005', 80000),
('SP000045', N'Salicylic Acid', '2023-02-01', '2025-02-01', N'Việt Nam', N'Đang bán', N'Uống 2 lần/ngày', N'Tri viêm và giảm đau', N'Viên nén', 0.07, N'Salicylic Acid, Calcium', N'Hộp', 'NCC000005', 'LSP005', 70000),
('SP000046', N'Meloxicam', '2023-01-10', '2025-01-10', N'Hàn Quốc', N'Đang bán', N'Uống theo chỉ định bác sĩ', N'Tri viêm và đau mãn tính', N'Viên nén', 0.07, N'Meloxicam, Povidone', N'Hộp', 'NCC000006', 'LSP005', 130000),
('SP000047', N'Piroxicam', '2022-10-15', '2024-10-15', N'Thái Lan', N'Đang bán', N'Uống 1 lần/ngày', N'Tri viêm và giảm đau', N'Viên nang', 0.06, N'Piroxicam, Starch', N'Viên', 'NCC000007', 'LSP005', 80000),
('SP000048', N'Flurbiprofen', '2023-05-25', '2025-05-25', N'Mỹ', N'Ngưng bán', N'Uống 1 lần/ngày', N'Tri viêm và giảm đau', N'Viên nén', 0.08, N'Flurbiprofen, Lactose', N'Hộp', 'NCC000008', 'LSP005', 75000),
('SP000049', N'Aspirin', '2023-02-20', '2025-02-20', N'Anh', N'Đang bán', N'Uống theo chỉ định bác sĩ', N'Tri viêm và giảm đau', N'Viên nang', 0.07, N'Aspirin, Starch', N'Viên', 'NCC000009', 'LSP005', 80000),
('SP000050', N'Rofecoxib', '2023-01-30', '2025-01-30', N'Pháp', N'Đang bán', N'Uống theo chỉ định bác sĩ', N'Tri viêm và đau', N'Viên nén', 0.08, N'Rofecoxib, Magnesium', N'Hộp', 'NCC000010', 'LSP005', 85000),
-- thuốc bổ (LSP006)
('SP000051', N'Vitamin C', '2023-05-15', '2025-05-15', N'Việt Nam', N'Đang bán', N'Uống 1 lần/ngày', N'Tăng cường miễn dịch', N'Viên nén', 0.04, N'Vitamin C, Gelatin', N'Viên', 'NCC000001', 'LSP006', 50000),
('SP000052', N'Vitamin D', '2023-04-12', '2025-04-12', N'Mỹ', N'Đang bán', N'Uống 1 lần/ngày', N'Tăng cường sức khỏe xương', N'Viên nén', 0.05, N'Vitamin D, Starch', N'Hộp', 'NCC000002', 'LSP006', 60000),
('SP000053', N'Vitamin B12', '2023-01-20', '2025-01-20', N'Nhật Bản', N'Ngưng bán', N'Uống 1 lần/ngày', N'Tăng cường sức khỏe thần kinh', N'Viên nén', 0.04, N'Vitamin B12, Lactose', N'Viên', 'NCC000003', 'LSP006', 70000),
('SP000054', N'Folic Acid', '2023-02-28', '2025-02-28', N'Pháp', N'Đang bán', N'Uống 1 lần/ngày', N'Tri ngăn ngừa thiếu máu', N'Viên nén', 0.05, N'Folic Acid, Gelatin', N'Hộp', 'NCC000004', 'LSP006', 55000),
('SP000055', N'Multivitamin', '2023-06-05', '2025-06-05', N'Hàn Quốc', N'Đang bán', N'Uống 1 lần/ngày', N'Tăng cường sức khỏe tổng thể', N'Viên nén', 0.06, N'Multivitamin, Starch', N'Viên', 'NCC000005', 'LSP006', 80000),
('SP000056', N'Coenzyme Q10', '2023-03-18', '2025-03-18', N'Thái Lan', N'Đang bán', N'Uống 1 lần/ngày', N'Tăng cường năng lượng', N'Viên nén', 0.07, N'Coenzyme Q10, Lactose', N'Hộp', 'NCC000006', 'LSP006', 120000),
('SP000057', N'Zinc', '2023-02-01', '2025-02-01', N'Việt Nam', N'Ngưng bán', N'Uống 1 lần/ngày', N'Tăng cường miễn dịch', N'Viên nén', 0.05, N'Zinc, Starch', N'Viên', 'NCC000007', 'LSP006', 45000),
('SP000058', N'Omega-3', '2023-04-10', '2025-04-10', N'Mỹ', N'Đang bán', N'Uống 1 lần/ngày', N'Tốt cho tim mạch', N'Viên nén', 0.06, N'Omega-3, Gelatin', N'Hộp', 'NCC000008', 'LSP006', 90000),
('SP000059', N'Calcium', '2023-03-12', '2025-03-12', N'Anh', N'Đang bán', N'Uống 1 lần/ngày', N'Tăng cường sức khỏe xương', N'Viên nén', 0.04, N'Calcium, Starch', N'Viên', 'NCC000009', 'LSP006', 55000),
('SP000060', N'Iron', '2023-05-20', '2025-05-20', N'Pháp', N'Ngưng bán', N'Uống 1 lần/ngày', N'Tri ngăn ngừa thiếu máu', N'Viên nén', 0.05, N'Iron, Lactose', N'Hộp', 'NCC000010', 'LSP006', 60000),
-- thực phẩm chức năng (LSP007)
('SP000061', N'Protein Powder', '2023-05-10', '2025-05-10', N'Việt Nam', N'Đang bán', N'Uống 1-2 lần/ngày', N'Tăng cường cơ bắp', N'Bột', 0.05, N'Whey Protein, Flavoring', N'Hộp', 'NCC000001', 'LSP007', 350000),
('SP000062', N'Fiber Supplement', '2023-06-01', '2025-06-01', N'Mỹ', N'Đang bán', N'Uống 1 lần/ngày', N'Tốt cho tiêu hóa', N'Dung dịch', 0.06, N'Fiber, Gelatin', N'Viên', 'NCC000002', 'LSP007', 200000),
('SP000063', N'Fish Oil', '2023-04-25', '2025-04-25', N'Nhật Bản', N'Đang bán', N'Uống 1 lần/ngày', N'Tốt cho tim mạch', N'Dung dịch', 0.07, N'Omega-3, Glycerin', N'Hộp', 'NCC000003', 'LSP007', 180000),
('SP000064', N'Green Tea Extract', '2023-03-15', '2025-03-15', N'Hàn Quốc', N'Ngưng bán', N'Uống 1-2 lần/ngày', N'Tăng cường sức khỏe', N'Dung dịch', 0.04, N'Green Tea, Gelatin', N'Viên', 'NCC000004', 'LSP007', 220000),
('SP000065', N'Calcium Supplement', '2023-01-10', '2025-01-10', N'Việt Nam', N'Đang bán', N'Uống 1 lần/ngày', N'Tăng cường xương', N'Dung dịch', 0.05, N'Calcium, Lactose', N'Hộp', 'NCC000005', 'LSP007', 120000),
('SP000066', N'Magnesium Supplement', '2023-02-20', '2025-02-20', N'Mỹ', N'Đang bán', N'Uống 1 lần/ngày', N'Tốt cho cơ bắp', N'Dung dịch', 0.06, N'Magnesium, Starch', N'Viên', 'NCC000006', 'LSP007', 150000),
('SP000067', N'Coenzyme Q10', '2023-04-12', '2025-04-12', N'Pháp', N'Đang bán', N'Uống 1 lần/ngày', N'Tăng cường năng lượng', N'Dung dịch', 0.07, N'Coenzyme Q10, Gelatin', N'Hộp', 'NCC000007', 'LSP007', 250000),
('SP000068', N'Probiotic', '2023-05-05', '2025-05-05', N'Hàn Quốc', N'Đang bán', N'Uống 1 lần/ngày', N'Tốt cho tiêu hóa', N'Dung dịch', 0.04, N'Probiotic, Starch', N'Viên', 'NCC000008', 'LSP007', 300000),
('SP000069', N'B-Complex', '2023-03-10', '2025-03-10', N'Nhật Bản', N'Ngưng bán', N'Uống 1 lần/ngày', N'Tăng cường sức khỏe', N'Dung dịch', 0.06, N'Vitamin B, Gelatin', N'Hộp', 'NCC000009', 'LSP007', 180000),
('SP000070', N'Zinc Supplement', '2023-02-18', '2025-02-18', N'Việt Nam', N'Đang bán', N'Uống 1 lần/ngày', N'Tăng cường miễn dịch', N'Dung dịch', 0.05, N'Zinc, Lactose', N'Viên', 'NCC000010', 'LSP007', 120000),
-- vât tư y tế (LSP008)
('SP000071', N'Bông y tế', '2023-06-15', '2025-06-15', N'Việt Nam', N'Đang bán', N'Sử dụng ngoài da', N'Sát trùng vết thương', NULL, 0.03, N'Cotton', N'Hộp', 'NCC000001', 'LSP008', 15000),
('SP000072', N'Găng tay y tế', '2023-05-20', '2025-05-20', N'Mỹ', N'Đang bán', N'Sử dụng 1 lần', N'Bảo vệ trong y tế', NULL, 0.04, N'Latex', N'Hộp', 'NCC000002', 'LSP008', 50000),
('SP000073', N'Khẩu trang y tế', '2023-04-28', '2025-04-28', N'Nhật Bản', N'Đang bán', N'Sử dụng 1 lần', N'Ngăn ngừa lây nhiễm', NULL, 0.02, N'Material', N'Hộp', 'NCC000003', 'LSP008', 30000),
('SP000074', N'Băng cá nhân', '2023-03-30', '2025-03-30', N'Hàn Quốc', N'Ngưng bán', N'Sử dụng ngoài da', N'Sát trùng vết thương', NULL, 0.03, N'Fabric', N'Hộp', 'NCC000004', 'LSP008', 25000),
('SP000075', N'Thuốc sát trùng', '2023-01-05', '2025-01-05', N'Việt Nam', N'Đang bán', N'Sử dụng ngoài da', N'Sát trùng vết thương', NULL, 0.05, N'Chlorhexidine', N'Hộp', 'NCC000005', 'LSP008', 80000),
('SP000076', N'Kim tiêm', '2023-04-12', '2025-04-12', N'Pháp', N'Đang bán', N'Sử dụng 1 lần', N'Chích thuốc', NULL, 0.04, N'Steel', N'Hộp', 'NCC000006', 'LSP008', 40000),
('SP000077', N'Tai nghe y tế', '2023-05-22', '2025-05-22', N'Nhật Bản', N'Đang bán', N'Sử dụng lại', N'Thăm khám sức khỏe', NULL, 0.05, N'Plastic', N'Hộp', 'NCC000007', 'LSP008', 120000),
('SP000078', N'Ống tiêm', '2023-02-10', '2025-02-10', N'Việt Nam', N'Ngưng bán', N'Sử dụng 1 lần', N'Tiêm thuốc', NULL, 0.03, N'Plastic', N'Hộp', 'NCC000008', 'LSP008', 50000),
('SP000079', N'Gạc y tế', '2023-01-25', '2025-01-25', N'Hàn Quốc', N'Đang bán', N'Sử dụng ngoài da', N'Sát trùng vết thương', NULL, 0.04, N'Fabric', N'Hộp', 'NCC000009', 'LSP008', 35000),
('SP000080', N'Máy đo huyết áp', '2023-03-05', '2025-03-05', N'Mỹ', N'Đang bán', N'Sử dụng lại', N'Thăm khám sức khỏe', NULL, 0.05, N'Material', N'Hộp', 'NCC000010', 'LSP008', 800000),
--thuốc hướng thần (LSP009)
('SP000081', N'Lorazepam', '2023-05-15', '2025-05-15', N'Việt Nam', N'Đang bán', N'Uống 1 lần/ngày', N'Giảm lo âu', N'Viên nén', 0.07, N'Lorazepam', N'Viên', 'NCC000001', 'LSP009', 200000),
('SP000082', N'Sertraline', '2023-06-10', '2025-06-10', N'Mỹ', N'Đang bán', N'Uống 1 lần/ngày', N'Chống trầm cảm', N'Viên nén', 0.06, N'Sertraline', N'Viên', 'NCC000002', 'LSP009', 220000),
('SP000083', N'Fluoxetine', '2023-07-05', '2025-07-05', N'Nhật Bản', N'Đang bán', N'Uống 1 lần/ngày', N'Chống trầm cảm', N'Viên nén', 0.05, N'Fluoxetine', N'Viên', 'NCC000003', 'LSP009', 210000),
('SP000084', N'Risperidone', '2023-04-22', '2025-04-22', N'Hàn Quốc', N'Đang bán', N'Uống 1 lần/ngày', N'Điều trị rối loạn tâm thần', N'Viên nén', 0.08, N'Risperidone', N'Viên', 'NCC000004', 'LSP009', 250000),
('SP000085', N'Quetiapine', '2023-03-18', '2025-03-18', N'Việt Nam', N'Đang bán', N'Uống 1 lần/ngày', N'Điều trị rối loạn tâm thần', N'Viên nén', 0.07, N'Quetiapine', N'Viên', 'NCC000005', 'LSP009', 230000),
('SP000086', N'Aripiprazole', '2023-02-10', '2025-02-10', N'Mỹ', N'Đang bán', N'Uống 1 lần/ngày', N'Điều trị rối loạn tâm thần', N'Viên nén', 0.05, N'Aripiprazole', N'Viên', 'NCC000006', 'LSP009', 240000),
('SP000087', N'Gabapentin', '2023-05-08', '2025-05-08', N'Nhật Bản', N'Đang bán', N'Uống 1 lần/ngày', N'Giảm đau', N'Viên nén', 0.06, N'Gabapentin', N'Viên', 'NCC000007', 'LSP009', 180000),
('SP000088', N'Lithium', '2023-04-15', '2025-04-15', N'Hàn Quốc', N'Đang bán', N'Uống 1 lần/ngày', N'Điều trị rối loạn tâm thần', N'Viên nén', 0.07, N'Lithium', N'Viên', 'NCC000008','LSP009', 220000),
('SP000089', N'Trazodone', '2023-03-12', '2025-03-12', N'Việt Nam', N'Đang bán', N'Uống 1 lần/ngày', N'Chống trầm cảm', N'Viên nén', 0.05, N'Trazodone', N'Viên', 'NCC000009', 'LSP009', 200000),
('SP000090', N'Clonazepam', '2023-02-20', '2025-02-20', N'Mỹ', N'Đang bán', N'Uống 1 lần/ngày', N'Giảm lo âu', N'Viên nén', 0.06, N'Clonazepam', N'Viên', 'NCC000010', 'LSP009', 250000);

-- nhân viên
INSERT INTO NhanVien (MaNhanVien, HoTen, Sdt, Cccd, NgaySinh, GioiTinh, ChucVu, TrangThaiLamViec)
VALUES
('NV000001', N'Trần Văn An', '0123456789', '123456789012', '1990-05-10', N'Nam', N'Nhân viên bán hàng', N'Đang làm'),
('NV000002', N'Lê Thị Diễm', '0987654321', '234567890123', '1985-03-15', N'Nữ', N'Nhân viên bán hàng', N'Đang làm'),
('NV000003', N'Nguyễn Văn Cường', '0123456788', '345678901234', '1992-07-20', N'Nam', N'Nhân viên bán hàng', N'Đang làm'),
('NV000004', N'Phạm Thị Dung', '0987654320', '456789012345', '1995-02-25', N'Nữ', N'Nhân viên bán hàng', N'Đang làm'),
('NV000005', N'Nguyễn Văn Liêm', '0123456787', '567890123456', '1980-12-30', N'Nam', N'Chủ cửa hàng', N'Đang làm');

-- đơn nhập hàng
INSERT INTO DonNhapHang (MaDonNhap, NgayNhap, MaNhanVien)
VALUES
('DN010920240001', '2024-09-01', 'NV000001'),
('DN010920240002', '2024-09-02', 'NV000002'),
('DN010920240003', '2024-09-03', 'NV000003'),
('DN010920240004', '2024-09-04', 'NV000001'),
('DN010920240005', '2024-09-05', 'NV000002'),
('DN010920240006', '2024-09-06', 'NV000004'),
('DN010920240007', '2024-09-07', 'NV000005'),
('DN010920240008', '2024-09-08', 'NV000001'),
('DN010920240009', '2024-09-09', 'NV000002'),
('DN010920240010', '2024-09-10', 'NV000003'),
('DN110920240001', '2024-09-11', 'NV000001'),
('DN110920240002', '2024-09-12', 'NV000002'),
('DN110920240003', '2024-09-13', 'NV000003'),
('DN110920240004', '2024-09-14', 'NV000004'),
('DN110920240005', '2024-09-15', 'NV000001'),
('DN110920240006', '2024-09-16', 'NV000002'),
('DN110920240007', '2024-09-17', 'NV000003'),
('DN110920240008', '2024-09-18', 'NV000004'),
('DN110920240009', '2024-09-19', 'NV000001'),
('DN110920240010', '2024-09-20', 'NV000002');

-- chi tiet đơn nhập
INSERT INTO ChiTietDonNhap (MaChiTietDonNhap, MaSanPham, SoLuong, GiaNhap, MaDonNhap)
VALUES
('CTDN01092024000100', 'SP000001', 50, 100.0000, 'DN010920240001'),
('CTDN01092024000201', 'SP000002', 30, 150.0000, 'DN010920240002'),
('CTDN01092024000302', 'SP000003', 20, 200.0000, 'DN010920240003'),
('CTDN01092024000403', 'SP000004', 15, 250.0000, 'DN010920240004'),
('CTDN01092024000504', 'SP000005', 25, 300.0000, 'DN010920240005'),
('CTDN01092024000605', 'SP000006', 10, 350.0000, 'DN010920240006'),
('CTDN01092024000706', 'SP000007', 5, 400.0000, 'DN010920240007'),
('CTDN01092024000807', 'SP000008', 8, 450.0000, 'DN010920240008'),
('CTDN01092024000908', 'SP000009', 12, 500.0000, 'DN010920240009'),
('CTDN01092024001009', 'SP000010', 40, 550.0000, 'DN010920240010'),
('CTDN11092024000100', 'SP000001', 20, 1000.0000, 'DN110920240001'),
('CTDN11092024000201', 'SP000002', 15, 1500.0000, 'DN110920240002'),
('CTDN11092024000302', 'SP000003', 10, 2000.0000, 'DN110920240003'),
('CTDN11092024000403', 'SP000004', 5, 2500.0000, 'DN110920240004'),
('CTDN1092024000504', 'SP000005', 25, 3000.0000, 'DN110920240005'),
('CTDN11092024000605', 'SP000006', 30, 3500.0000, 'DN110920240006'),
('CTDN11092024000706', 'SP000007', 12, 4000.0000, 'DN110920240007'),
('CTDN11092024000807', 'SP000008', 8, 4500.0000, 'DN110920240008'),
('CTDN11092024000908', 'SP000009', 4, 5000.0000, 'DN110920240009'),
('CTDN11092024001009', 'SP000010', 18, 5500.0000, 'DN110920240010');

-- tài khoản
INSERT INTO TaiKhoan (MaTaiKhoan, TenDangNhap, MatKhau, TrangThai)
VALUES
('NV000001', 'NV000001', 'An123456789012', 'Hoạt động'),
('NV000002', 'NV000002', 'Diem234567890123', 'Hoạt động'),
('NV000003', 'NV000003', 'Cuong345678901234', 'Ngưng hoạt động'),
('NV000004', 'NV000004', 'Dung456789012345', 'Hoạt động'),
('NV000005', 'AD000005', 'Liem567890123456', 'Hoạt động')

-- Lô hàng
INSERT INTO LoHang (MaLo, NgaySanXuat, GiaNhap, SoLuong, ViTri, MaSanPham, MaChiTietDonNhap)
VALUES
('LH010920240001', '2024-09-01', 1000.0000, 50, N'Kho 1', 'SP000001', 'CTDN01092024000100'),
('LH010920240002', '2024-09-02', 1500.0000, 30, N'Kho 2', 'SP000002', 'CTDN01092024000201'),
('LH010920240003', '2024-09-03', 2000.0000, 20, N'Kho 3', 'SP000003', 'CTDN01092024000302'),
('LH010920240004', '2024-09-04', 2500.0000, 15, N'Kho 4', 'SP000004', 'CTDN01092024000403'),
('LH010920240005', '2024-09-05', 3000.0000, 25, N'Kho 5', 'SP000005', 'CTDN01092024000504'),
('LH010920240006', '2024-09-06', 3500.0000, 10, N'Kho 1', 'SP000006', 'CTDN01092024000605'),
('LH010920240007', '2024-09-07', 4000.0000, 5, N'Kho 2', 'SP000007', 'CTDN01092024000706'),
('LH010920240008', '2024-09-08', 4500.0000, 8, N'Kho 3', 'SP000008', 'CTDN01092024000807'),
('LH010920240009', '2024-09-09', 5000.0000, 12, N'Kho 4', 'SP000009', 'CTDN01092024000908'),
('LH010920240010', '2024-09-10', 5500.0000, 40, N'Kho 5', 'SP000010', 'CTDN01092024001009');

-- đơn hủy hàng
INSERT INTO DonHuyHang (MaDonHuyHang, NgayHuy, SoLuong, MaLoHang, MaNhanVien)
VALUES
('DHH00001', '2024-09-11', 10, 'LH010920240001', 'NV000001'),
('DHH00002', '2024-09-12', 5, 'LH010920240002', 'NV000002'),
('DHH00003', '2024-09-13', 7, 'LH010920240003', 'NV000003'),
('DHH00004', '2024-09-14', 3, 'LH010920240004', 'NV000004'),
('DHH00005', '2024-09-15', 4, 'LH010920240005', 'NV000001'),
('DHH00006', '2024-09-16', 2, 'LH010920240006', 'NV000002'),
('DHH00007', '2024-09-17', 1, 'LH010920240007', 'NV000003'),
('DHH00008', '2024-09-18', 8, 'LH010920240008', 'NV000004'),
('DHH00009', '2024-09-19', 6, 'LH010920240009', 'NV000001'),
('DHH00010', '2024-09-20', 9, 'LH010920240010', 'NV000002');

--Khách hàng
INSERT INTO KhachHang (MaKhachHang, HoTen, Sdt, Cccd, DiaChi, DiemTichLuy)
VALUES
('KH000000', '', '', '', '', 0),
('KH000001', N'Nguyễn Văn Anh', '0123456789', '123456787654', N'Số 1, Đường A, Quận 1, TP.HCM', 100),
('KH000002', N'Trần Thị Bình', '0123456790', '123456787312', N'Số 2, Đường B, Quận 2, Hà Nội', 150),
('KH000003', N'Lê Văn Cao', '0123456791', '045676787655',N'Số 3, Đường C, Quận 3, Đà Nẵng', 200),
('KH000004', N'Nguyễn Thị Dương', '0123456792', '118766787657',N'Số 4, Đường D, Quận 4, Bình Dương', 250),
('KH000005', N'Phạm Văn Cường', '0123456793','332786787659', N'Số 5, Đường E, Quận 5, Cần Thơ', 300),
('KH000006', N'Trần Văn Quyến', '0123456794','775646788976', N'Số 6, Đường F, Quận 6, Hải Phòng', 350),
('KH000007', N'Lê Thị Nhung', '0123456795','227866787654', N'Số 7, Đường G, Quận 7, Hưng Yên', 400),
('KH000008', N'Nguyễn Văn Hùng', '0123456796','877676787654', N'Số 8, Đường H, Quận 8, Nha Trang', 450),
('KH000009', N'Trần Thị Hạnh', '0123456797','224566787654', N'Số 9, Đường I, Quận 9, Vĩnh Phúc', 500),
('KH000010', N'Lê Văn Quyết', '0123456798','864526787654', N'Số 10, Đường J, Quận 10, Thừa Thiên-Huế', 550),
('KH000011', N'Nguyễn Thị Trúc', '0123456799','875436787654', N'Số 11, Đường K, Quận 11, Long An', 600),
('KH000012', N'Phạm Văn Lộc', '0123456800','769216787654', N'Số 12, Đường L, Quận 12, Đồng Nai', 650),
('KH000013', N'Trần Văn Minh', '0123456801','267578926712', N'Số 13, Đường M, Quận 13, Bình Định', 700),
('KH000014', N'Lê Thị Nhung', '0123456802','772456787654', N'Số 14, Đường N, Quận 14, Kiên Giang', 750),
('KH000015', N'Nguyễn Văn Bảo', '0123456803','742236787654', N'Số 15, Đường O, Quận 15, Nghệ An', 800),
('KH000016', N'Trần Thị Phương', '0123456804','246236787654', N'Số 16, Đường P, Quận 16, Hà Tĩnh', 850),
('KH000017', N'Lê Văn Huy', '0123456805','095466787654', N'Số 17, Đường Q, Quận 17, Thanh Hóa', 900),
('KH000018', N'Nguyễn Thị Sáu', '0123456806','872456787654', N'Số 18, Đường R, Quận 18, Quảng Ninh', 950),
('KH000019', N'Phạm Văn Siêu', '0123456807','097246787654', N'Số 19, Đường S, Quận 19, Bình Thuận', 1000),
('KH000020', N'Trần Văn Tùng', '0123456808','762436787654', N'Số 20, Đường T, Quận 20, Bà Rịa-Vũng Tàu', 1050);

--khuyến mãi
INSERT INTO KhuyenMai (MaKhuyenMai, NgayBatDau, NgayKetThuc, DieuKien, ChietKhau, MaNhanVien)
VALUES
('KM0101202401', '2024-01-01', '2024-01-31', N'500000', 0.5, 'NV000001'),
('KM0102202402', '2024-02-01', '2024-02-28', N'300000', 0.15, 'NV000002'),
('KM0103202403', '2024-03-01', '2024-03-31', N'200000', 0.2, 'NV000003'),
('KM0104202404', '2024-04-01', '2024-04-30', N'500000', 0.05, 'NV000004'),
('KM0105202405', '2024-05-01', '2024-05-31', N'200000', 0.1, 'NV000005'),
('KM0106202406', '2024-06-01', '2024-06-30', N'150000', 0.25, 'NV000005'),
('KM0107202407', '2024-07-01', '2024-07-31', N'100000', 0.1, 'NV000004'),
('KM0108202408', '2024-08-01', '2024-08-31', N'100000', 0.1, 'NV000003'),
('KM0109202409', '2024-09-01', '2024-09-30', N'300000', 0.3, 'NV000002'),
('KM0110202410', '2024-10-01', '2024-10-31', N'150000', 0.15, 'NV000001');

-- hóa đơn
INSERT INTO HoaDon (MaHoaDon, NgayTao, DiemSuDung, ThanhTien, MaNhanVien, MaKhuyenMai, MaKhachHang)
VALUES
('HD010120240001', '2024-01-01', 10, 300000, 'NV000001', 'KM0101202401', 'KH000001'),
('HD010120240002', '2024-01-01', 5, 300000, 'NV000002', NULL, 'KH000002');



-- chi tiết hóa đơn
INSERT INTO ChiTietHoaDon (MaChiTietHoaDon, SoLuong, TongTien, MaHoaDon, MaSanPham, MaLo, MaLoHangThayThe)
VALUES
('CTHD601202400110', 2, 300000, 'HD010120240001', 'SP000001', 'LH010920240001', NULL),
('CTHD601202400111', 1, 300000, 'HD010120240002', 'SP000002', 'LH010920240002', NULL);



-- đơn đổi trả
INSERT INTO DonDoiTra (MaDonDoiTra, NgayDoiTra, TienHoan, MaNhanVien, MaKhuyenMai, MaKhachHang, MaHoaDon)
VALUES
('DDTH010120240001', '2024-10-20', 100000.0000, 'NV000001', NULL, 'KH000001', 'HD010120240001');


-- chi tiết đon đổi trả
INSERT INTO ChiTietDoiTra (MaChiTietDoiTra, SoLuong, GiaBan, MaDonDoiTra, MaSanPham, MaLo, MaLoHangThayThe)
VALUES
('CTDTH010120240001', 1, 150000.0000, 'DDTH010120240001', 'SP000001', 'LH010920240001', NULL);
