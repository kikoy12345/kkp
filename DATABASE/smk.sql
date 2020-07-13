-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 13, 2020 at 06:28 AM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `smk`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `nip` varchar(50) NOT NULL,
  `nama_admin` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `sebagai` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `nip`, `nama_admin`, `password`, `sebagai`) VALUES
(1, '1', 'ritno', '1', 'admin'),
(2, '202007060005', 'deni', '111', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `guru`
--

CREATE TABLE `guru` (
  `id_guru` int(11) NOT NULL,
  `nip` varchar(50) NOT NULL,
  `nama_guru` varchar(50) NOT NULL,
  `kode_mapel` varchar(50) NOT NULL,
  `sebagai` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `guru`
--

INSERT INTO `guru` (`id_guru`, `nip`, `nama_guru`, `kode_mapel`, `sebagai`, `password`) VALUES
(11, '3', 'nita', 'MP-009', 'guru', '3'),
(12, '1', 'deni', 'MP-003', 'guru', '3'),
(16, '2', 'Hafiz', 'MP-005', 'guru', '2'),
(17, '4', 'Putri', 'MP-006', 'guru', '4');

-- --------------------------------------------------------

--
-- Table structure for table `jadwal`
--

CREATE TABLE `jadwal` (
  `id` int(11) NOT NULL,
  `tahun` varchar(50) NOT NULL,
  `kelas` varchar(50) NOT NULL,
  `jam` varchar(50) NOT NULL,
  `hari` varchar(50) NOT NULL,
  `kode_mapel` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jadwal`
--

INSERT INTO `jadwal` (`id`, `tahun`, `kelas`, `jam`, `hari`, `kode_mapel`) VALUES
(1, '2018/2019', 'Kelas 10', '08:00 - 08:35', 'Senin', 'MP-003'),
(2, '2018/2019', 'Kelas 10', '08:35 - 09:10', 'Senin', 'MP-003'),
(3, '2018/2019', 'Kelas 10', '09:10 - 09:45', 'Senin', 'MP-002'),
(4, '2018/2019', 'Kelas 10', '10:10 - 10:45', 'Senin', 'MP-002'),
(5, '2018/2019', 'Kelas 10', '10:45 - 11:20', 'Senin', 'MP-006'),
(6, '2018/2019', 'Kelas 10', '11:20 - 11:55', 'Senin', 'MP-006'),
(7, '2018/2019', 'Kelas 10', '12:15 - 12:50', 'Senin', 'MP-005'),
(8, '2018/2019', 'Kelas 10', '12:50 - 13:25', 'Senin', 'MP-005'),
(9, '2018/2019', 'Kelas 10', '13:25 - 14:00', 'Senin', 'MP-007'),
(10, '2018/2019', 'Kelas 10', '14:00 - 15:00', 'Senin', 'MP-007'),
(11, '2018/2019', 'Kelas 11', '08:00 - 08:35', 'Senin', 'MP-011'),
(12, '2018/2019', 'Kelas 11', '08:35 - 09:10', 'Senin', 'MP-011'),
(13, '2018/2019', 'Kelas 11', '09:10 - 09:45', 'Senin', 'MP-001'),
(14, '2018/2019', 'Kelas 11', '10:10 - 10:45', 'Senin', 'MP-001'),
(15, '2018/2019', 'Kelas 11', '10:45 - 11:20', 'Senin', 'MP-008'),
(16, '2018/2019', 'Kelas 11', '11:20 - 11:55', 'Senin', 'MP-008'),
(17, '2018/2019', 'Kelas 11', '12:15 - 12:50', 'Senin', 'MP-013'),
(18, '2018/2019', 'Kelas 11', '12:15 - 12:50', 'Senin', 'MP-013'),
(19, '2018/2019', 'Kelas 11', '12:50 - 13:25', 'Senin', 'MP-009'),
(20, '2018/2019', 'Kelas 11', '13:25 - 14:00', 'Senin', 'MP-009'),
(21, '2018/2019', 'Kelas 12', '14:00 - 15:00', 'Senin', 'MP-020'),
(22, '2018/2019', 'Kelas 12', '08:00 - 08:35', 'Senin', 'MP-020'),
(23, '2018/2019', 'Kelas 12', '08:35 - 09:10', 'Senin', 'MP-018'),
(24, '2018/2019', 'Kelas 12', '09:10 - 09:45', 'Senin', 'MP-018'),
(25, '2020/2021', 'Kelas 10', '08:00 - 08:35', 'Senin', 'MP-003'),
(26, '2020/2021', 'Kelas 10', '08:35 - 09:10', 'Senin', 'MP-003'),
(27, '2020/2021', 'Kelas 10', '09:10 - 09:45', 'Senin', 'MP-005'),
(28, '2020/2021', 'Kelas 10', '10:10 - 10:45', 'Senin', 'MP-005'),
(29, '2020/2021', 'Kelas 10', '10:45 - 11:20', 'Senin', 'MP-009'),
(30, '2020/2021', 'Kelas 10', '11:20 - 11:55', 'Senin', 'MP-009'),
(31, '2020/2021', 'Kelas 10', '08:00 - 08:35', 'Selasa', 'MP-003'),
(32, '2020/2021', 'Kelas 10', '08:00 - 08:35', 'Selasa', 'MP-003'),
(33, '2020/2021', 'Kelas 10', '09:10 - 09:45', 'Selasa', 'MP-005'),
(34, '2020/2021', 'Kelas 10', '10:10 - 10:45', 'Selasa', 'MP-005'),
(35, '2020/2021', 'Kelas 10', '12:50 - 13:25', 'Selasa', 'MP-006'),
(36, '2020/2021', 'Kelas 10', '13:25 - 14:00', 'Selasa', 'MP-006'),
(37, '2020/2021', 'Kelas 10', '08:35 - 09:10', 'Rabu', 'MP-009'),
(38, '2020/2021', 'Kelas 10', '09:10 - 09:45', 'Rabu', 'MP-009'),
(39, '2020/2021', 'Kelas 10', '10:10 - 10:45', 'Rabu', 'MP-005'),
(40, '2020/2021', 'Kelas 10', '11:20 - 11:55', 'Rabu', 'MP-005'),
(41, '2020/2021', 'Kelas 10', '13:25 - 14:00', 'Rabu', 'MP-006'),
(42, '2020/2021', 'Kelas 10', '14:00 - 15:00', 'Rabu', 'MP-006'),
(63, '2020/2021', 'Kelas 10', '08:00 - 08:35', 'Senin', 'MP-005'),
(64, '2020/2021', 'Kelas 10', '08:35 - 09:10', 'Senin', 'MP-005'),
(65, '2020/2021', 'Kelas 10', '09:10 - 09:45', 'Senin', 'MP-003'),
(66, '2020/2021', 'Kelas 10', '10:10 - 10:45', 'Senin', 'MP-003'),
(67, '2020/2021', 'Kelas 11', '10:45 - 11:20', 'Senin', 'MP-006'),
(68, '2020/2021', 'Kelas 11', '11:20 - 11:55', 'Senin', 'MP-006'),
(69, '2020/2021', 'Kelas 11', '08:00 - 08:35', 'Selasa', 'MP-006'),
(70, '2020/2021', 'Kelas 11', '08:35 - 09:10', 'Selasa', 'MP-006'),
(71, '2020/2021', 'Kelas 11', '09:10 - 09:45', 'Selasa', 'MP-009'),
(72, '2020/2021', 'Kelas 11', '10:10 - 10:45', 'Selasa', 'MP-009'),
(73, '2020/2021', 'Kelas 11', '14:00 - 15:00', 'Selasa', 'MP-005'),
(74, '2020/2021', 'Kelas 11', '10:45 - 11:20', 'Selasa', 'MP-005'),
(75, '2020/2021', 'Kelas 11', '08:35 - 09:10', 'Rabu', 'MP-003'),
(76, '2020/2021', 'Kelas 11', '12:15 - 12:50', 'Rabu', 'MP-003'),
(77, '2020/2021', 'Kelas 11', '09:10 - 09:45', 'Rabu', 'MP-005'),
(78, '2020/2021', 'Kelas 11', '10:10 - 10:45', 'Rabu', 'MP-005'),
(79, '2020/2021', 'Kelas 12', '08:00 - 08:35', 'Senin', 'MP-005'),
(80, '2020/2021', 'Kelas 12', '08:35 - 09:10', 'Senin', 'MP-005'),
(81, '2020/2021', 'Kelas 11', '10:45 - 11:20', 'Rabu', 'MP-009'),
(82, '2020/2021', 'Kelas 11', '11:20 - 11:55', 'Rabu', 'MP-009'),
(84, '2020/2021', 'Kelas 12', '09:10 - 09:45', 'Senin', 'MP-009'),
(85, '2020/2021', 'Kelas 12', '10:10 - 10:45', 'Senin', 'MP-009'),
(86, '2020/2021', 'Kelas 12', '10:45 - 11:20', 'Senin', 'MP-006'),
(87, '2020/2021', 'Kelas 12', '11:20 - 11:55', 'Senin', 'MP-006'),
(88, '2020/2021', 'Kelas 12', '08:00 - 08:35', 'Selasa', 'MP-006'),
(89, '2020/2021', 'Kelas 12', '08:35 - 09:10', 'Selasa', 'MP-006'),
(90, '2020/2021', 'Kelas 12', '09:10 - 09:45', 'Selasa', 'MP-003'),
(91, '2020/2021', 'Kelas 12', '10:10 - 10:45', 'Selasa', 'MP-003'),
(92, '2020/2021', 'Kelas 12', '10:45 - 11:20', 'Selasa', 'MP-009'),
(93, '2020/2021', 'Kelas 12', '11:20 - 11:55', 'Selasa', 'MP-009'),
(94, '2020/2021', 'Kelas 12', '08:00 - 08:35', 'Rabu', 'MP-005'),
(95, '2020/2021', 'Kelas 12', '08:35 - 09:10', 'Rabu', 'MP-005'),
(96, '2020/2021', 'Kelas 12', '09:10 - 09:45', 'Rabu', 'MP-003'),
(97, '2020/2021', 'Kelas 12', '10:10 - 10:45', 'Rabu', 'MP-003'),
(98, '2020/2021', 'Kelas 12', '10:45 - 11:20', 'Rabu', 'MP-006'),
(99, '2020/2021', 'Kelas 12', '11:20 - 11:55', 'Rabu', 'MP-006');

-- --------------------------------------------------------

--
-- Table structure for table `kkm`
--

CREATE TABLE `kkm` (
  `id` int(11) NOT NULL,
  `nip` varchar(50) NOT NULL,
  `kkm` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kkm`
--

INSERT INTO `kkm` (`id`, `nip`, `kkm`) VALUES
(1, '202003030007', '60');

-- --------------------------------------------------------

--
-- Table structure for table `mapel`
--

CREATE TABLE `mapel` (
  `id_mapel` int(11) NOT NULL,
  `kode_mapel` varchar(50) NOT NULL,
  `mapel` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mapel`
--

INSERT INTO `mapel` (`id_mapel`, `kode_mapel`, `mapel`) VALUES
(1, 'MP-001', 'Adminitrasi Infrastruktur Jaringan'),
(2, 'MP-002', 'Adminitrasi Sistem Jaringan'),
(3, 'MP-003', 'WAN'),
(4, 'MP-004', 'Teknologi Layanan Jaringan'),
(5, 'MP-005', 'Komputer dan Jaringan Dasar'),
(6, 'MP-006', 'Produk Kreatif dan KWU'),
(7, 'MP-007', 'Pendidikan Agama Islam'),
(8, 'MP-008', 'Fisika'),
(9, 'MP-009', 'Sejarah Indonesia'),
(10, 'MP-010', 'Bahasa Inggris'),
(11, 'MP-011', 'Bahasa Indonesia'),
(12, 'MP-012', 'Kimia'),
(13, 'MP-013', 'Seni Budaya'),
(14, 'MP-014', 'Simulasi Digital'),
(15, 'MP-015', 'PKN'),
(16, 'MP-016', 'Dasar Desain Grafis'),
(17, 'MP-017', 'Matematika'),
(18, 'MP-018', 'Sistem Komputer'),
(19, 'MP-019', 'Penjaskes'),
(20, 'MP-020', 'Pemrograman Dasar'),
(21, 'MP-021', 'Java');

-- --------------------------------------------------------

--
-- Table structure for table `nilai`
--

CREATE TABLE `nilai` (
  `id` int(11) NOT NULL,
  `tahun` varchar(50) NOT NULL,
  `nis` varchar(50) NOT NULL,
  `nip` varchar(50) NOT NULL,
  `kode_mapel` varchar(50) NOT NULL,
  `semester` varchar(50) NOT NULL,
  `nilai_harian` varchar(10) NOT NULL,
  `nilai_tugas` varchar(50) NOT NULL,
  `nilai_pat` varchar(50) NOT NULL,
  `nilai_pas` varchar(50) NOT NULL,
  `nilai_akhir` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `nilai`
--

INSERT INTO `nilai` (`id`, `tahun`, `nis`, `nip`, `kode_mapel`, `semester`, `nilai_harian`, `nilai_tugas`, `nilai_pat`, `nilai_pas`, `nilai_akhir`) VALUES
(21, '2020/2021', '20200629048', '2', 'MP-005', 'Semester 1', '5', '5', '5', '5', '5.0'),
(22, '2020/2021', '20200629049', '2', 'MP-005', 'Semester 1', '7', '7', '7', '7', '7.0'),
(23, '2020/2021', '20200629050', '2', 'MP-005', 'Semester 1', '60', '56', '77', '89', '70.0'),
(24, '2020/2021', '20200629051', '2', 'MP-005', 'Semester 1', '8', '8', '8', '8', '8.0'),
(25, '2020/2021', '20200629052', '2', 'MP-005', 'Semester 1', '56', '76', '67', '67', '66.0'),
(26, '2020/2021', '20200629048', '1', 'MP-003', 'Semester 1', '56', '56', '57', '78', '61.0'),
(27, '2020/2021', '20200629049', '1', 'MP-003', 'Semester 1', '78', '56', '78', '78', '72.0'),
(28, '2020/2021', '20200629050', '1', 'MP-003', 'Semester 1', '60', '56', '77', '89', '70.0'),
(29, '2020/2021', '20200629051', '1', 'MP-003', 'Semester 1', '56', '88', '45', '88', '69.0'),
(30, '2020/2021', '20200629052', '1', 'MP-003', 'Semester 1', '89', '45', '78', '89', '75.0'),
(31, '2020/2021', '20200629053', '1', 'MP-003', 'Semester 1', '56', '45', '78', '98', '69.0'),
(32, '2020/2021', '20200629054', '1', 'MP-003', '2020/2021', '56', '56', '89', '77', '69.0'),
(33, '2020/2021', '20200629055', '1', 'MP-003', 'Semester 1', '56', '78', '89', '77', '75.0'),
(34, '2020/2021', '20200629056', '1', 'MP-003', 'Semester 1', '56', '45', '89', '95', '71.0'),
(35, '2020/2021', '20200629057', '1', 'MP-003', 'Semester 1', '87', '45', '89', '67', '72.0'),
(36, '2020/2021', '20200629058', '2', 'MP-005', 'Semester 1', '56', '78', '88', '85', '76.0'),
(37, '2020/2021', '20200629059', '2', 'MP-005', 'Semester 1', '56', '78', '46', '46', '56.0'),
(38, '2020/2021', '20200629060', '2', 'MP-005', 'Semester 1', '70', '78', '46', '60', '63.0'),
(39, '2020/2021', '20200629061', '2', 'MP-005', 'Semester 1', '67', '78', '79', '78', '75.0'),
(40, '2020/2021', '20200629062', '2', 'MP-005', 'Semester 1', '67', '43', '34', '67', '52.0'),
(41, '2020/2021', '20200629063', '2', 'MP-005', 'Semester 1', '78', '76', '89', '66', '77.0'),
(42, '2020/2021', '20200629053', '2', 'MP-005', 'Semester 1', '58', '46', '77', '76', '64.0'),
(43, '2020/2021', '20200629054', '2', 'MP-005', 'Semester 1', '58', '46', '66', '55', '56.0'),
(44, '2020/2021', '20200629055', '2', 'MP-005', 'Semester 1', '58', '78', '86', '83', '76.0'),
(45, '2020/2021', '20200629056', '2', 'MP-005', 'Semester 1', '79', '68', '75', '86', '77.0'),
(46, '2020/2021', '20200629057', '2', 'MP-005', 'Semester 1', '56', '69', '80', '85', '72.0'),
(47, '2020/2021', '20200629058', '1', 'MP-003', 'Semester 1', '70', '65', '70', '65', '67.0'),
(48, '2020/2021', '20200629059', '1', 'MP-003', 'Semester 1', '80', '67', '80', '68', '73.0'),
(49, '2020/2021', '20200629060', '1', 'MP-003', 'Semester 1', '56', '34', '34', '56', '45.0'),
(50, '2020/2021', '20200629061', '1', 'MP-003', 'Semester 1', '56', '56', '89', '78', '69.0');

-- --------------------------------------------------------

--
-- Table structure for table `setting`
--

CREATE TABLE `setting` (
  `id_set` int(11) NOT NULL,
  `tahun` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `setting`
--

INSERT INTO `setting` (`id_set`, `tahun`) VALUES
(2, '2018/2019'),
(3, '2019/2020');

-- --------------------------------------------------------

--
-- Table structure for table `siswa`
--

CREATE TABLE `siswa` (
  `id_siswa` int(11) NOT NULL,
  `tahun` varchar(50) NOT NULL,
  `nis` varchar(15) NOT NULL,
  `nama_siswa` varchar(50) NOT NULL,
  `semester` varchar(50) NOT NULL,
  `kelas` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `siswa`
--

INSERT INTO `siswa` (`id_siswa`, `tahun`, `nis`, `nama_siswa`, `semester`, `kelas`) VALUES
(1, '2020/2021', '20200203001', 'Lukman Hakim Saksono', 'Semester 1', 'Kelas 10'),
(2, '2020/2021', '20200203002', 'januar', 'Semester 1', 'Kelas 10'),
(3, '2020/2021', '20200203003', 'July', 'Semester 1', 'Kelas 10'),
(4, '2020/2021', '20200203004', 'Jaenal', 'Semester 1', 'Kelas 10'),
(5, '2020/2021', '20200203005', 'Marvin', 'Semester 1', 'Kelas 10'),
(6, '2020/2021', '20200203006', 'Zaki Akhmad', 'Semester 1', 'Kelas 10'),
(7, '2020/2021', '20200203007', 'Alif Fariza Putra', 'Semester 1', 'Kelas 10'),
(8, '2020/2021', '20200209008', 'Lusi', 'Semester 1', 'Kelas 10'),
(9, '2020/2021', '20200209009', 'Lila', 'Semester 1', 'Kelas 10'),
(10, '2020/2021', '20200209010', 'Boni', 'Semester 1', 'Kelas 10'),
(11, '2020/2021', '20200209011', 'Sintia', 'Semester 1', 'Kelas 10'),
(12, '2020/2021', '20200209012', 'Fadli Akbar', 'Semester 1', 'Kelas 10'),
(13, '2020/2021', '20200209013', 'Lili Marvin', 'Semester 1', 'Kelas 10'),
(14, '2020/2021', '20200209014', 'Jeson Lee', 'Semester 1', 'Kelas 10'),
(15, '2020/2021', '20200209015', 'Kwan Yen', 'Semester 1', 'Kelas 10'),
(16, '2020/2021', '20200209016', 'Fajri', 'Semester 1', 'Kelas 10'),
(17, '2020/2021', '20200209017', 'Karim', 'Semester 1', 'Kelas 10'),
(18, '2020/2021', '20200209018', 'Rizal', 'Semester 1', 'Kelas 10'),
(19, '2020/2021', '20200209019', 'Sirun', 'Semester 1', 'Kelas 11'),
(20, '2020/2021', '20200209020', 'Indah', 'Semester 1', 'Kelas 11'),
(21, '2020/2021', '20200209021', 'Nonot', 'Semester 1', 'Kelas 11'),
(22, '2020/2021', '20200209022', 'zed', 'Semester 1', 'Kelas 11'),
(23, '2020/2021', '20200209023', 'Rozak', 'Semester 1', 'Kelas 11'),
(24, '2020/2021', '20200209024', 'Ilyas', 'Semester 1', 'Kelas 11'),
(25, '2020/2021', '20200209025', 'Bendol', 'Semester 1', 'Kelas 11'),
(26, '2020/2021', '20200209026', 'Kikoy', 'Semester 1', 'Kelas 11'),
(27, '2020/2021', '20200209027', 'Rudi', 'Semester 1', 'Kelas 11'),
(28, '2020/2021', '20200209028', 'Budi', 'Semester 1', 'Kelas 11'),
(29, '2020/2021', '20200209029', 'Irvan', 'Semester 1', 'Kelas 11'),
(30, '2020/2021', '20200209030', 'Yani', 'Semester 1', 'Kelas 11'),
(41, '2020/2021', '20200628041', 'beri', 'Semester 1', 'Kelas 11'),
(42, '2020/2021', '20200628042', 'bocil', 'Semester 1', 'Kelas 11'),
(43, '2020/2021', '20200628043', 'Lukman', 'Semester 1', 'Kelas 11'),
(44, '2020/2021', '20200628044', 'jon', 'Semester 1', 'Kelas 11'),
(45, '2020/2021', '20200628045', 'jery', 'Semester 1', 'Kelas 11'),
(46, '2020/2021', '20200628046', 'pondi', 'Semester 1', 'Kelas 11'),
(47, '2020/2021', '20200628047', 'reni', 'Semester 1', 'Kelas 11'),
(48, '2020/2021', '20200629048', 'Pandi', 'Semester 1', 'Kelas 12'),
(49, '2020/2021', '20200629049', 'lala', 'Semester 1', 'Kelas 12'),
(50, '2020/2021', '20200629050', 'pam', 'Semester 1', 'Kelas 12'),
(51, '2020/2021', '20200629051', 'luss', 'Semester 1', 'Kelas 12'),
(52, '2020/2021', '20200629052', 'lila', 'Semester 1', 'Kelas 12'),
(53, '2020/2021', '20200629053', 'rahmat', 'Semester 1', 'Kelas 12'),
(54, '2020/2021', '20200629054', 'back', 'Semester 1', 'Kelas 12'),
(55, '2020/2021', '20200629055', 'tri', 'Semester 1', 'Kelas 12'),
(56, '2020/2021', '20200629056', 'luna', 'Semester 1', 'Kelas 12'),
(57, '2020/2021', '20200629057', 'sisi', 'Semester 1', 'Kelas 12'),
(58, '2020/2021', '20200629058', 'jordan', 'Semester 1', 'Kelas 12'),
(59, '2020/2021', '20200629059', 'mamat', 'Semester 1', 'Kelas 12'),
(60, '2020/2021', '20200629060', 'muna', 'Semester 1', 'Kelas 12'),
(61, '2020/2021', '20200629061', 'luna', 'Semester 1', 'Kelas 12'),
(62, '2020/2021', '20200629062', 'beri', 'Semester 1', 'Kelas 12'),
(63, '2020/2021', '20200629063', 'lusa', 'Semester 1', 'Kelas 12'),
(64, '2020/2021', '20200704064', 'asep', 'Semester 1', 'Kelas 12');

-- --------------------------------------------------------

--
-- Table structure for table `wali`
--

CREATE TABLE `wali` (
  `id` int(11) NOT NULL,
  `nip` varchar(50) NOT NULL,
  `wali_kelas` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `wali`
--

INSERT INTO `wali` (`id`, `nip`, `wali_kelas`) VALUES
(20, '1', 'Kelas 10'),
(21, '202006280003', 'Kelas 12'),
(22, '2', 'Kelas 11');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `guru`
--
ALTER TABLE `guru`
  ADD PRIMARY KEY (`id_guru`);

--
-- Indexes for table `jadwal`
--
ALTER TABLE `jadwal`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `kkm`
--
ALTER TABLE `kkm`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `mapel`
--
ALTER TABLE `mapel`
  ADD PRIMARY KEY (`id_mapel`);

--
-- Indexes for table `nilai`
--
ALTER TABLE `nilai`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `setting`
--
ALTER TABLE `setting`
  ADD PRIMARY KEY (`id_set`);

--
-- Indexes for table `siswa`
--
ALTER TABLE `siswa`
  ADD PRIMARY KEY (`id_siswa`);

--
-- Indexes for table `wali`
--
ALTER TABLE `wali`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `guru`
--
ALTER TABLE `guru`
  MODIFY `id_guru` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `jadwal`
--
ALTER TABLE `jadwal`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=100;

--
-- AUTO_INCREMENT for table `kkm`
--
ALTER TABLE `kkm`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `mapel`
--
ALTER TABLE `mapel`
  MODIFY `id_mapel` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `nilai`
--
ALTER TABLE `nilai`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT for table `setting`
--
ALTER TABLE `setting`
  MODIFY `id_set` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `siswa`
--
ALTER TABLE `siswa`
  MODIFY `id_siswa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=65;

--
-- AUTO_INCREMENT for table `wali`
--
ALTER TABLE `wali`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
