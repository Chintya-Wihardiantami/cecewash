CREATE DATABASE IF NOT EXISTS cecewash;
USE cecewash;

CREATE TABLE IF NOT EXISTS pesanan_laundry (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nama_pelanggan VARCHAR(255) NOT NULL,
    nomor_hp VARCHAR(15) NOT NULL,
    jenis_layanan VARCHAR(100) NOT NULL,
    berat DOUBLE NOT NULL,
    total_harga INT NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'BARU'
);

INSERT INTO pesanan_laundry
(nama_pelanggan, nomor_hp, jenis_layanan, berat, total_harga, status)
VALUES
('Budi Santoso', '081234567890', 'REGULER', 3.0, 21000, 'BARU');
