-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hostiteľ: 127.0.0.1
-- Čas generovania: So 15.Nov 2025, 11:38
-- Verzia serveru: 10.4.28-MariaDB
-- Verzia PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Databáza: `javafx`
--

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(120) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Sťahujem dáta pre tabuľku `category`
--

INSERT INTO `category` (`id`, `name`, `created_at`) VALUES
(1, 'Beverages', '2025-08-25 16:24:10'),
(2, 'Snacks', '2025-08-25 16:24:10'),
(3, 'Books', '2025-08-25 16:24:10'),
(4, 'Hardware', '2025-08-25 16:24:10'),
(5, 'Electronics', '2025-08-25 16:24:10');

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `invoice`
--

CREATE TABLE `invoice` (
  `id` int(11) NOT NULL,
  `date` date NOT NULL,
  `status` varchar(55) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Sťahujem dáta pre tabuľku `invoice`
--

INSERT INTO `invoice` (`id`, `date`, `status`) VALUES
(1, '2025-09-17', 'prepared'),
(2, '2025-09-16', 'in evidence');

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `invoice_items`
--

CREATE TABLE `invoice_items` (
  `id` int(11) NOT NULL,
  `id_product` int(11) NOT NULL,
  `id_invoice` int(11) NOT NULL,
  `amount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Sťahujem dáta pre tabuľku `invoice_items`
--

INSERT INTO `invoice_items` (`id`, `id_product`, `id_invoice`, `amount`) VALUES
(1, 23, 1, 5),
(2, 22, 1, 1),
(3, 4, 1, 1),
(4, 1, 2, 3),
(5, 2, 2, 2);

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `quantity` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_at` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Sťahujem dáta pre tabuľku `product`
--

INSERT INTO `product` (`id`, `name`, `price`, `quantity`, `category_id`, `created_at`, `updated_at`) VALUES
(1, 'Coffee', 6.30, 4, 1, '2025-08-25 16:24:19', '0000-00-00 00:00:00'),
(2, 'Tea', 4.25, 3, 1, '2025-08-25 16:24:19', '0000-00-00 00:00:00'),
(3, 'Screwdriver', 8.90, 10, 4, '2025-08-25 16:24:19', '0000-00-00 00:00:00'),
(4, 'HDMI Cable 2m', 12.50, 2, 5, '2025-08-25 16:24:19', '0000-00-00 00:00:00'),
(5, 'USB-C Charger 30W', 24.90, 8, 5, '2025-08-25 16:24:19', '0000-00-00 00:00:00'),
(6, 'Chips Salted', 1.80, 25, 2, '2025-08-25 16:24:19', '0000-00-00 00:00:00'),
(7, 'Algorithms Book', 39.99, 7, 3, '2025-08-25 16:24:19', '0000-00-00 00:00:00'),
(21, 'Recipes', 12.80, 2, 3, '2025-08-29 15:49:09', '2025-08-29 15:49:09'),
(22, 'VGA Cable', 12.38, 2, 5, '2025-08-29 17:08:03', '2025-08-29 17:08:03'),
(23, 'USB 2GB', 2.50, 1, 5, '2025-08-29 17:08:19', '2025-08-29 17:08:19'),
(24, 'USB 64GB', 24.00, 3, 5, '2025-08-29 17:08:29', '2025-08-29 17:08:29'),
(25, 'USB 24GB', 12.50, 2, 5, '2025-08-29 17:31:12', '2025-08-29 17:31:12'),
(30, 'HDMI', 10.00, 2, 5, '2025-10-28 16:33:56', '2025-10-28 16:33:56');

--
-- Kľúče pre exportované tabuľky
--

--
-- Indexy pre tabuľku `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexy pre tabuľku `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`id`);

--
-- Indexy pre tabuľku `invoice_items`
--
ALTER TABLE `invoice_items`
  ADD PRIMARY KEY (`id`);

--
-- Indexy pre tabuľku `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pre exportované tabuľky
--

--
-- AUTO_INCREMENT pre tabuľku `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pre tabuľku `invoice`
--
ALTER TABLE `invoice`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pre tabuľku `invoice_items`
--
ALTER TABLE `invoice_items`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pre tabuľku `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
