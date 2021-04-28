-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Apr 28, 2021 at 05:13 PM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `agileplanning`
--

-- --------------------------------------------------------

--
-- Table structure for table `collaborator-projects`
--

CREATE TABLE `collaborator-projects` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `uid` bigint(20) UNSIGNED NOT NULL,
  `projectid` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `collaborator-projects`
--

INSERT INTO `collaborator-projects` (`id`, `uid`, `projectid`) VALUES
(1, 1, 1),
(2, 2, 1),
(3, 3, 1),
(4, 4, 1);

-- --------------------------------------------------------

--
-- Table structure for table `collaborators`
--

CREATE TABLE `collaborators` (
  `name` text NOT NULL,
  `uid` bigint(20) UNSIGNED NOT NULL,
  `passwd` text NOT NULL,
  `type` text NOT NULL,
  `salt` varbinary(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `collaborators`
--

INSERT INTO `collaborators` (`name`, `uid`, `passwd`, `type`, `salt`) VALUES
('Noe', 1, 'c117f92c35fc4f91c5201d05b60171bb', 'admin', 0xdc2264e2669bacbeeb5d07b1887bdda0),
('Mau', 2, '08c4a6c7c2bd357935c092a63a265db5', 'admin', 0xf0fd204aa91c332bc1ea239080569355),
('Diana', 3, '97bea6d616bf16997449973cfef46ee1', 'admin', 0xddbd558ab099b596f1385a6a804be585),
('Pepe', 4, '99313b401e4fce34370377b6eb17c6d8', 'admin', 0x031b008f8c2be0851f2a696bbfdd87d0);

-- --------------------------------------------------------

--
-- Table structure for table `crc`
--

CREATE TABLE `crc` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `projectid` bigint(20) UNSIGNED NOT NULL,
  `class` text NOT NULL,
  `superclass` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `crc-collaborations`
--

CREATE TABLE `crc-collaborations` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `crc` bigint(20) UNSIGNED NOT NULL,
  `collaboration` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `crc-responsibilities`
--

CREATE TABLE `crc-responsibilities` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `crc` bigint(20) UNSIGNED NOT NULL,
  `responsibility` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `projects`
--

CREATE TABLE `projects` (
  `name` text NOT NULL,
  `description` text NOT NULL,
  `date` text NOT NULL,
  `id` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `projects`
--

INSERT INTO `projects` (`name`, `description`, `date`, `id`) VALUES
('Adopcion de mascotas', 'Aplicacion web para adopcion de mascotas', 'Wed Apr 28 2021 00:28:44 GMT-0500 (Central Daylight Time)', 1);

-- --------------------------------------------------------

--
-- Table structure for table `user-stories`
--

CREATE TABLE `user-stories` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `projectid` bigint(20) UNSIGNED NOT NULL,
  `title` text NOT NULL,
  `description` text NOT NULL,
  `date` text NOT NULL,
  `value` int(11) NOT NULL,
  `observations` text NOT NULL,
  `days` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `collaborator-projects`
--
ALTER TABLE `collaborator-projects`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD KEY `collaborator-project` (`projectid`);

--
-- Indexes for table `collaborators`
--
ALTER TABLE `collaborators`
  ADD PRIMARY KEY (`uid`);

--
-- Indexes for table `crc`
--
ALTER TABLE `crc`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD KEY `crc-project` (`projectid`);

--
-- Indexes for table `crc-collaborations`
--
ALTER TABLE `crc-collaborations`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD KEY `crc-collaboration` (`crc`);

--
-- Indexes for table `crc-responsibilities`
--
ALTER TABLE `crc-responsibilities`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD KEY `crc-responsibility` (`crc`);

--
-- Indexes for table `projects`
--
ALTER TABLE `projects`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user-stories`
--
ALTER TABLE `user-stories`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD KEY `story-project` (`projectid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `collaborator-projects`
--
ALTER TABLE `collaborator-projects`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `collaborators`
--
ALTER TABLE `collaborators`
  MODIFY `uid` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `crc`
--
ALTER TABLE `crc`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `crc-collaborations`
--
ALTER TABLE `crc-collaborations`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `crc-responsibilities`
--
ALTER TABLE `crc-responsibilities`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `projects`
--
ALTER TABLE `projects`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `user-stories`
--
ALTER TABLE `user-stories`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `collaborator-projects`
--
ALTER TABLE `collaborator-projects`
  ADD CONSTRAINT `collaborator-project` FOREIGN KEY (`projectid`) REFERENCES `projects` (`id`);

--
-- Constraints for table `crc`
--
ALTER TABLE `crc`
  ADD CONSTRAINT `crc-project` FOREIGN KEY (`projectid`) REFERENCES `projects` (`id`);

--
-- Constraints for table `crc-collaborations`
--
ALTER TABLE `crc-collaborations`
  ADD CONSTRAINT `crc-collaboration` FOREIGN KEY (`crc`) REFERENCES `crc` (`id`);

--
-- Constraints for table `crc-responsibilities`
--
ALTER TABLE `crc-responsibilities`
  ADD CONSTRAINT `crc-responsibility` FOREIGN KEY (`crc`) REFERENCES `crc` (`id`);

--
-- Constraints for table `user-stories`
--
ALTER TABLE `user-stories`
  ADD CONSTRAINT `story-project` FOREIGN KEY (`projectid`) REFERENCES `projects` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
