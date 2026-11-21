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
-- Databáza: `movies`
--

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `genre`
--

CREATE TABLE `genre` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_slovak_ci;

--
-- Sťahujem dáta pre tabuľku `genre`
--

INSERT INTO `genre` (`id`, `name`) VALUES
(1, 'Romance'),
(2, 'Sci-fi'),
(28, 'Action'),
(29, 'Drama'),
(30, 'Horror'),
(31, 'Thriller'),
(32, 'Comedy');

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `movie`
--

CREATE TABLE `movie` (
  `id` int(11) NOT NULL,
  `title` varchar(50) NOT NULL,
  `length` int(11) NOT NULL,
  `year` int(11) NOT NULL,
  `link` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_slovak_ci;

--
-- Sťahujem dáta pre tabuľku `movie`
--

INSERT INTO `movie` (`id`, `title`, `length`, `year`, `link`) VALUES
(1, 'Titanic', 194, 1998, 'https://www.imdb.com/title/tt0120338/'),
(2, 'Avatar', 162, 2009, 'https://www.imdb.com/title/tt0499549/'),
(16, 'Napoleon', 158, 2023, 'https://www.imdb.com/title/tt13287846/'),
(17, 'Forrest Gump', 142, 1994, 'https://www.imdb.com/title/tt0109830/'),
(18, 'Thanksgiving', 106, 2023, 'https://www.imdb.com/title/tt1448754/'),
(19, 'The Wolf of Wall Street', 181, 2013, 'https://www.imdb.com/title/tt0993846/');

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `movie_genres`
--

CREATE TABLE `movie_genres` (
  `id` int(11) NOT NULL,
  `movie_id` int(11) NOT NULL,
  `genre_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_slovak_ci;

--
-- Sťahujem dáta pre tabuľku `movie_genres`
--

INSERT INTO `movie_genres` (`id`, `movie_id`, `genre_id`) VALUES
(1, 1, 1),
(25, 2, 2),
(33, 16, 28),
(43, 17, 29),
(44, 17, 1),
(45, 18, 30),
(46, 18, 29),
(47, 19, 32);

--
-- Kľúče pre exportované tabuľky
--

--
-- Indexy pre tabuľku `genre`
--
ALTER TABLE `genre`
  ADD PRIMARY KEY (`id`);

--
-- Indexy pre tabuľku `movie`
--
ALTER TABLE `movie`
  ADD PRIMARY KEY (`id`);

--
-- Indexy pre tabuľku `movie_genres`
--
ALTER TABLE `movie_genres`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pre exportované tabuľky
--

--
-- AUTO_INCREMENT pre tabuľku `genre`
--
ALTER TABLE `genre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT pre tabuľku `movie`
--
ALTER TABLE `movie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT pre tabuľku `movie_genres`
--
ALTER TABLE `movie_genres`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
