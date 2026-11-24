-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hostiteľ: 127.0.0.1
-- Čas generovania: Po 08.Sep 2025, 09:50
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
-- Databáza: `javafx8a`
--

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(75) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Sťahujem dáta pre tabuľku `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
(1, 'food'),
(2, 'fuel'),
(3, 'coffee'),
(4, 'garden');

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `expense`
--

CREATE TABLE `expense` (
  `id` int(11) NOT NULL,
  `price` double NOT NULL,
  `date` date NOT NULL,
  `shop_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `shop`
--

CREATE TABLE `shop` (
  `id` int(11) NOT NULL,
  `name` varchar(75) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Sťahujem dáta pre tabuľku `shop`
--

INSERT INTO `shop` (`id`, `name`) VALUES
(1, 'kaufland'),
(2, 'billa'),
(3, 'tesco'),
(4, 'lidl'),
(5, 'obi'),
(6, 'hornbach'),
(7, 'starbucks'),
(8, 'shell');

--
-- Kľúče pre exportované tabuľky
--

--
-- Indexy pre tabuľku `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexy pre tabuľku `expense`
--
ALTER TABLE `expense`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_expense_shop_idx` (`shop_id`),
  ADD KEY `fk_expense_category1_idx` (`category_id`);

--
-- Indexy pre tabuľku `shop`
--
ALTER TABLE `shop`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pre exportované tabuľky
--

--
-- AUTO_INCREMENT pre tabuľku `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pre tabuľku `expense`
--
ALTER TABLE `expense`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pre tabuľku `shop`
--
ALTER TABLE `shop`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Obmedzenie pre exportované tabuľky
--

--
-- Obmedzenie pre tabuľku `expense`
--
ALTER TABLE `expense`
  ADD CONSTRAINT `fk_expense_category1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_expense_shop` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
