CREATE DATABASE QLCHang
GO
USE QLCHang
GO

CREATE TABLE HOADON(
    maHD nvarchar(10) NOT NULL PRIMARY KEY,
    ngayLap datetime NOT NULL,
    maNV nvarchar(10) NOT NULL,
    maKH nvarchar(10) NOT NULL
)

CREATE TABLE KHACHHANG(
    maKH nvarchar(10) NOT NULL PRIMARY KEY,
    tenKH nvarchar(50) NOT NULL,
    gioiTinh nvarchar(10) NULL,
    soDT varchar(15) NULL
)

CREATE TABLE LOAISANPHAM(
    maLoai nvarchar(10) NOT NULL PRIMARY KEY,
    tenLoai nvarchar(50) NOT NULL
)

CREATE TABLE SANPHAM(
    maSP nvarchar(10) NOT NULL PRIMARY KEY,
    tenSP nvarchar(50) NOT NULL,
    donGia int NOT NULL,
    maLoai nvarchar(10) NOT NULL,
    tinhTrang nvarchar(50) NOT NULL,
    ngaySX date NOT NULL,
    FOREIGN KEY (maLoai) REFERENCES LOAISANPHAM(maLoai)
)

CREATE TABLE NHANVIEN(
    maNV nvarchar(10) NOT NULL PRIMARY KEY,
    tenNV nvarchar(50) NOT NULL,
    diaChi nvarchar(50) NOT NULL,
    soDT varchar(15) NOT NULL,
    trangThai nvarchar(50) NOT NULL,
    ngaySinh date NOT NULL,
    vaiTro nvarchar(20) NOT NULL,
    email nvarchar(50) NOT NULL,
    gioiTinh nvarchar(10) NOT NULL
)

CREATE TABLE TAIKHOAN(
    maNV nvarchar(10) NOT NULL PRIMARY KEY,
    tenDangNhap nvarchar(50) NOT NULL,
    matKhau nvarchar(50) NOT NULL,
    FOREIGN KEY (maNV) REFERENCES NHANVIEN(maNV)
)

ALTER TABLE HOADON
ADD CONSTRAINT FK_HOADON_NHANVIEN FOREIGN KEY (maNV) REFERENCES NHANVIEN(maNV)

ALTER TABLE HOADON
ADD CONSTRAINT FK_HOADON_KHACHHANG FOREIGN KEY (maKH) REFERENCES KHACHHANG(maKH)

CREATE TABLE CHITIETHOADON(
    maHD nvarchar(10) NOT NULL,
    maSP nvarchar(10) NOT NULL,
    giaBan int NOT NULL,
    soLuong int NOT NULL,
    FOREIGN KEY (maHD) REFERENCES HOADON(maHD),
    FOREIGN KEY (maSP) REFERENCES SANPHAM(maSP),
    PRIMARY KEY (maHD, maSP)
)



INSERT INTO NHANVIEN (maNV, tenNV, diaChi, soDT, trangThai, ngaySinh, vaiTro, email, gioiTinh) VALUES
(N'NV001', N'Trần Văn Nam',     N'123 Lê Lợi, Quận 1, TP.HCM',    N'0901234567', N'Đang làm',      '1990-05-12', N'Quản lý',     N'nam.tran@example.com', N'Nam'),
(N'NV002', N'Lê Thị Hồng',      N'45 Nguyễn Huệ, Quận 1, TP.HCM',  N'0912345678', N'Đã thôi việc', '1992-08-22', N'Nhân viên',   N'hong.le@example.com', N'Nữ'),
(N'NV003', N'Phạm Minh Tuấn',   N'78 Hai Bà Trưng, Hà Nội',        N'0934567890', N'Đang làm',      '1988-11-05', N'Nhân viên',   N'tuan.pham@example.com', N'Nam'),
(N'NV004', N'Nguyễn Thị Lan',   N'10 Trường Chinh, Đà Nẵng',       N'0945678901', N'Đang làm',      '1995-03-18', N'Nhân viên',   N'lan.nguyen@example.com', N'Nữ'),
(N'NV005', N'Đỗ Đức Anh',       N'9 Pasteur, Quận 3, TP.HCM',      N'0978901234', N'Đang làm',      '1993-01-09', N'Nhân viên',   N'ducanh.do@example.com', N'Nam'),
(N'NV006', N'Hồ Thanh Bình',    N'21 Cách Mạng, Quận Bình Thạnh',  N'0987654321', N'Đã thôi việc', '1985-07-21', N'Nhân viên',   N'binh.ho@example.com', N'Nam'),
(N'NV007', N'Trịnh Công Sơn',   N'234 Nguyễn Trãi, Hà Nội',        N'0908765432', N'Đang làm',      '1979-12-15', N'Nhân viên',   N'son.trinh@example.com', N'Nam'),
(N'NV008', N'Hoàng Thị Yến',    N'88 Phan Đình Phùng, Huế',        N'0938765432', N'Đang làm',      '1996-06-06', N'Nhân viên',   N'yen.hoang@example.com', N'Nữ'),
(N'NV009', N'Ngô Văn Hiếu',     N'9 Nguyễn Văn Cừ, TP.HCM',        N'0919876543', N'Đã thôi việc', '1987-09-10', N'Nhân viên',   N'hieu.ngo@example.com', N'Nam'),
(N'NV010', N'Lý Minh Châu',     N'41 Đống Đa, Đà Nẵng',            N'0965432187', N'Đang làm',      '1991-04-11', N'Nhân viên',   N'chau.ly@example.com', N'Nữ'),
(N'NV011', N'Huỳnh Anh Tú',     N'7 Lê Văn Sỹ, TP.HCM',            N'0934567812', N'Đang làm',      '1989-03-02', N'Nhân viên',   N'tu.huynh@example.com', N'Nam'),
(N'NV012', N'Nguyễn Nhật Hạ',   N'39 Lý Thường Kiệt, Hà Nội',      N'0976543211', N'Đang làm',      '1994-12-24', N'Nhân viên',   N'ha.nguyen@example.com', N'Nữ'),
(N'NV013', N'Vũ Hồng Phúc',     N'25 Võ Văn Tần, Quận 3',          N'0954321789', N'Đã thôi việc', '1983-10-20', N'Nhân viên',   N'phuc.vu@example.com', N'Nam'),
(N'NV014', N'Bùi Gia Khang',    N'60 Trần Hưng Đạo, Quận 5',       N'0923456789', N'Đang làm',      '1990-02-28', N'Nhân viên',   N'khang.bui@example.com', N'Nam'),
(N'NV015', N'Phan Thanh Mai',   N'84 Bạch Đằng, Hà Nội',           N'0912789456', N'Đã thôi việc', '1992-11-07', N'Nhân viên',   N'mai.phan@example.com', N'Nữ'),
(N'NV016', N'Tống Văn Thành',   N'17 Trần Quốc Toản, Huế',         N'0901234987', N'Đang làm',      '1986-08-14', N'Nhân viên',   N'thanh.tong@example.com', N'Nam'),
(N'NV017', N'Trương Mỹ Linh',   N'102 Hùng Vương, Đà Nẵng',        N'0987123456', N'Đã thôi việc', '1997-05-30', N'Nhân viên',   N'linh.truong@example.com', N'Nữ'),
(N'NV018', N'Lâm Thị Diễm',     N'3/2 Nguyễn Thị Minh Khai, Q10',  N'0903334445', N'Đang làm',      '1993-07-08', N'Nhân viên',   N'diem.lam@example.com', N'Nữ'),
(N'NV019', N'Đặng Tuấn Kiệt',   N'55 Cộng Hòa, Tân Bình',          N'0944556677', N'Đã thôi việc', '1988-09-19', N'Nhân viên',   N'kiet.dang@example.com', N'Nam'),
(N'NV020', N'Hà Thị Quỳnh',     N'70 Nguyễn Thái Học, Hà Nội',     N'0911223344', N'Đang làm',      '1995-10-01', N'Nhân viên',   N'quynh.ha@example.com', N'Nữ');




INSERT INTO TAIKHOAN (maNV, tenDangNhap, matKhau) VALUES
(N'NV001', N'nv1', N'123456'),
(N'NV002', N'nv2', N'123456'),
(N'NV003', N'nv3', N'123456'),
(N'NV004', N'nv4', N'123456'),
(N'NV005', N'nv5', N'123456'),
(N'NV006', N'nv6', N'123456'),
(N'NV007', N'nv7', N'123456'),
(N'NV008', N'nv8', N'123456'),
(N'NV009', N'nv9', N'123456'),
(N'NV010', N'nv10', N'123456'),
(N'NV011', N'nv11', N'123456'),
(N'NV012', N'nv12', N'123456'),
(N'NV013', N'nv13', N'123456'),
(N'NV014', N'nv14', N'123456'),
(N'NV015', N'nv15', N'123456'),
(N'NV016', N'nv16', N'123456'),
(N'NV017', N'nv17', N'123456'),
(N'NV018', N'nv18', N'123456'),
(N'NV019', N'nv19', N'123456'),
(N'NV020', N'nv20', N'123456');
--SELECT * FROM TAIKHOAN K JOIN NHANVIEN V ON K.maNV = V.maNV where tenDangNhap = 'nv1' and matKhau = '123'
--select * from TAIKHOAN


INSERT INTO LOAISANPHAM (maLoai, tenLoai) VALUES
('LSP01', N'Đồ uống'),
('LSP02', N'Đồ ăn nhanh'),
('LSP03', N'Đồ dùng cá nhân'),
('LSP04', N'Văn phòng phẩm'),
('LSP05', N'Đồ dùng y tế');

INSERT INTO SANPHAM (maSP, tenSP, donGia, maLoai, tinhTrang, ngaySX) VALUES
-- LSP01 - Đồ uống
('SP001', N'Coca Cola lon 330ml', 12000, 'LSP01', N'Còn bán', '2025-02-01'),
('SP002', N'Trà xanh Không độ 500ml', 13000, 'LSP01', N'Còn bán', '2025-01-20'),
('SP003', N'Nước suối Lavie 500ml', 8000, 'LSP01', N'Còn bán', '2025-03-10'),
('SP004', N'Nước tăng lực Sting dâu', 13000, 'LSP01', N'Còn bán', '2025-01-28'),
('SP005', N'Trà sữa TEA+ Plus', 16000, 'LSP01', N'Còn bán', '2025-03-01'),

-- LSP02 - Đồ ăn nhanh
('SP006', N'Mì Hảo Hảo tôm chua cay', 5500, 'LSP02', N'Còn bán', '2025-01-10'),
('SP007', N'Bánh gạo One One', 9000, 'LSP02', N'Còn bán', '2025-02-15'),
('SP008', N'Snack Oishi bò nướng', 11000, 'LSP02', N'Còn bán', '2025-03-05'),
('SP009', N'Xúc xích CP ăn liền', 13000, 'LSP02', N'Còn bán', '2025-03-12'),
('SP010', N'Cơm cháy chà bông', 18000, 'LSP02', N'Còn bán', '2025-03-08'),

-- LSP03 - Đồ dùng cá nhân
('SP011', N'Bàn chải Colgate', 15000, 'LSP03', N'Còn bán', '2025-02-01'),
('SP012', N'Kem đánh răng P/S 100g', 20000, 'LSP03', N'Còn bán', '2025-03-01'),
('SP013', N'Khăn giấy Bless you mini', 7000, 'LSP03', N'Còn bán', '2025-03-15'),
('SP014', N'Dao cạo râu Gillette', 25000, 'LSP03', N'Còn bán', '2025-02-20'),
('SP015', N'Lược nhựa nhỏ', 12000, 'LSP03', N'Còn bán', '2025-02-05'),

-- LSP04 - Văn phòng phẩm
('SP016', N'Bút bi Thiên Long', 5000, 'LSP04', N'Còn bán', '2025-01-15'),
('SP017', N'Sổ tay A5', 18000, 'LSP04', N'Còn bán', '2025-02-12'),
('SP018', N'Bút xóa Flexoffice', 12000, 'LSP04', N'Còn bán', '2025-03-02'),
('SP019', N'Giấy note vàng 3x3', 10000, 'LSP04', N'Còn bán', '2025-03-10'),
('SP020', N'Kéo cắt giấy nhỏ', 15000, 'LSP04', N'Còn bán', '2025-02-28'),

-- LSP05 - Đồ dùng y tế
('SP021', N'Khẩu trang y tế 4 lớp (10 cái)', 12000, 'LSP05', N'Còn bán', '2025-03-10'),
('SP022', N'Nước rửa tay Lifebuoy 50ml', 18000, 'LSP05', N'Còn bán', '2025-02-25'),
('SP023', N'Vitamin C sủi lọ 10 viên', 25000, 'LSP05', N'Còn bán', '2025-03-01'),
('SP024', N'Nhiệt kế điện tử', 95000, 'LSP05', N'Còn bán', '2025-01-30'),
('SP025', N'Băng cá nhân Urgo (10 miếng)', 15000, 'LSP05', N'Còn bán', '2025-02-18');



INSERT INTO KHACHHANG (maKH, tenKH, gioiTinh, soDT) VALUES
(N'KH001', N'Nguyễn Thị Mai Lan',      N'Nữ',  '0912737915'),
(N'KH002', N'Phạm Minh Đức',          N'Nam',  '0911737916'),
(N'KH003', N'Lê Thị Bích Ngọc',       N'Nữ',  '0934567890'),
(N'KH004', N'Trần Quốc Huy',          N'Nam',  '0945678901'),
(N'KH005', N'Hoàng Thị Thu Hà',       N'Nữ',  '0967890123'),
(N'KH006', N'Ngô Văn Nam',            N'Nam',  '0978901234'),
(N'KH007', N'Vũ Thị Kim Oanh',        N'Nữ',  '0989012345'),
(N'KH008', N'Đỗ Anh Tuấn',            N'Nam',  '0990123456'),
(N'KH009', N'Bùi Thị Hồng Nhung',     N'Nữ',  '0911223344'),
(N'KH010', N'Tạ Văn Bình',            N'Nam',  '0922334455'),
(N'KH011', N'Mai Thị Xuân',           N'Nữ',  '0933445566'),
(N'KH012', N'Đinh Văn Khánh',         N'Nam',  '0944556677'),
(N'KH013', N'Lý Thị Thanh',           N'Nữ',  '0955667788'),
(N'KH014', N'Chu Văn Thành',          N'Nam',  '0966778899'),
(N'KH015', N'Trịnh Thị Yến',          N'Nữ',  '0977889900'),
(N'KH016', N'Cao Hữu Phước',          N'Nam',  '0988990011'),
(N'KH017', N'Nguyễn Thị Mỹ Linh',     N'Nữ',  '0999001122'),
(N'KH018', N'Trần Văn Kiên',          N'Nam',  '0900112233'),
(N'KH019', N'Lê Thị Diễm Quỳnh',      N'Nữ',  '0911223345'),
(N'KH020', N'Phan Văn Duy',           N'Nam',  '0922334466');



INSERT INTO HOADON (maHD, ngayLap, maNV, maKH) VALUES
(N'HD001', '2025-04-25 14:30', N'NV001', N'KH002'),
(N'HD002', '2025-04-18 09:12', N'NV007', N'KH004'),
(N'HD003', '2025-04-07 17:45', N'NV003', N'KH001'),
(N'HD004', '2025-03-28 11:06', N'NV002', N'KH008'),
(N'HD005', '2025-03-19 15:20', N'NV008', N'KH003'),
(N'HD006', '2025-03-10 10:55', N'NV006', N'KH006'),
(N'HD007', '2025-03-02 08:44', N'NV005', N'KH001'),
(N'HD008', '2025-02-24 18:10', N'NV004', N'KH005'),
(N'HD009', '2025-02-14 13:52', N'NV002', N'KH010'),
(N'HD010', '2025-02-08 15:25', N'NV007', N'KH009'),
(N'HD011', '2025-02-05 12:40', N'NV003', N'KH012'),
(N'HD012', '2025-02-01 09:20', N'NV008', N'KH020'),
(N'HD013', '2025-04-21 08:00', N'NV001', N'KH014'),
(N'HD014', '2025-03-17 14:35', N'NV004', N'KH007'),
(N'HD015', '2025-02-22 11:11', N'NV005', N'KH017'),
(N'HD016', '2025-04-03 19:00', N'NV006', N'KH002'),
(N'HD017', '2025-03-13 16:40', N'NV008', N'KH003'),
(N'HD018', '2025-02-18 13:00', N'NV007', N'KH015'),
(N'HD019', '2025-03-25 15:00', N'NV002', N'KH006'),
(N'HD020', '2025-04-12 18:20', N'NV003', N'KH018'),
(N'HD021', '2025-04-05 10:50', N'NV004', N'KH011'),
(N'HD022', '2025-03-06 13:15', N'NV006', N'KH019'),
(N'HD023', '2025-02-27 17:30', N'NV005', N'KH008'),
(N'HD024', '2025-02-10 09:05', N'NV001', N'KH016'),
(N'HD025', '2025-03-30 14:25', N'NV007', N'KH013');



INSERT INTO CHITIETHOADON (maHD, maSP, giaBan, soLuong) VALUES
('HD001', 'SP001', 18000, 3),
('HD001', 'SP006', 9000, 2),
-- Hóa đơn HD001
('HD002', 'SP003', 12000, 5),
-- Hóa đơn HD002
('HD003', 'SP010', 29000, 4),
('HD003', 'SP019', 14000, 2),
-- Hóa đơn HD003
('HD004', 'SP011', 23000, 3),
('HD004', 'SP022', 27000, 1),
('HD004', 'SP001', 18000, 1),
-- Hóa đơn HD004
('HD005', 'SP005', 24000, 2),
-- Hóa đơn HD005
('HD006', 'SP013', 11000, 6),
('HD006', 'SP024', 143000, 1),
-- Hóa đơn HD006
('HD007', 'SP008', 18000, 2),
('HD007', 'SP018', 17000, 2),
-- Hóa đơn HD007
('HD008', 'SP007', 14000, 3),
-- Hóa đơn HD008
('HD009', 'SP009', 21000, 1),
('HD009', 'SP025', 23000, 2),
-- Hóa đơn HD009
('HD010', 'SP017', 25000, 2),
-- Hóa đơn HD010
('HD011', 'SP015', 18000, 4),
-- Hóa đơn HD011
('HD012', 'SP001', 18000, 5),
-- Hóa đơn HD012
('HD013', 'SP014', 38000, 3),
('HD013', 'SP010', 29000, 2),
-- Hóa đơn HD013
('HD014', 'SP004', 12000, 3),
-- Hóa đơn HD014
('HD015', 'SP005', 24000, 6),
('HD015', 'SP024', 143000, 1),
-- Hóa đơn HD015
('HD016', 'SP023', 18000, 2),
('HD016', 'SP016', 7000, 5),
-- Hóa đơn HD016
('HD017', 'SP022', 25000, 3),
-- Hóa đơn HD017
('HD018', 'SP020', 21000, 2),
('HD018', 'SP012', 30000, 2),
-- Hóa đơn HD018
('HD019', 'SP009', 19500, 2),
-- Hóa đơn HD019
('HD020', 'SP005', 24000, 5),
('HD020', 'SP018', 17000, 3),
-- Hóa đơn HD020
('HD021', 'SP001', 18000, 4),
-- Hóa đơn HD021
('HD022', 'SP014', 38000, 1),
('HD022', 'SP017', 25000, 2),
-- Hóa đơn HD022
('HD023', 'SP002', 20000, 5),
('HD023', 'SP006', 9000, 4),
-- Hóa đơn HD023
('HD024', 'SP025', 23000, 3),
-- Hóa đơn HD024
('HD025', 'SP021', 18000, 3),
('HD025', 'SP022', 27000, 1),
('HD025', 'SP023', 38000, 1);
-- Hóa đơn HD025



