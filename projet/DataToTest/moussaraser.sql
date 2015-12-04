-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Ven 04 Décembre 2015 à 11:54
-- Version du serveur :  5.6.24
-- Version de PHP :  5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

SET foreign_key_checks = 0;

--
-- Base de données :  `moussaraser`
--

--
-- Contenu de la table `apikey`
--

INSERT INTO `apikey` (`ID`, `APIKEY`) VALUES
(1, 'a2f62188c9d74a3d82fa508ffa0cae6a'),
(2, '93073845311d4b678e6ce04f8933067f');

--
-- Contenu de la table `application`
--

INSERT INTO `application` (`ID`, `DESCRIPTION`, `ENABLED`, `NAME`, `CREATOR_ID`, `APIKEY_ID`) VALUES
(1, 'Application with users', 0, 'AppWithUsers', 1, 1),
(2, 'Application without users', 0, 'AppWithoutUsers', 1, 2);

--
-- Contenu de la table `badge`
--

INSERT INTO `badge` (`ID`, `CATEGORY`, `DESCRIPTION`, `IMAGE`, `NAME`, `APPLICATION_ID`) VALUES
(1, 'Category 1', 'Badge 1', 'http://www.pokepedia.fr/images/5/50/Badge_Cascade_Kanto.png', 'Badge 1', 1),
(2, 'Category 2', 'Badge 1', 'http://www.pokepedia.fr/images/5/50/Badge_Cascade_Kanto.png', 'Badge 2', 1);

--
-- Contenu de la table `enduser`
--

INSERT INTO `enduser` (`ID`, `FIRSTNAME`, `LASTNAME`, `REGISTRATIONDATE`, `SCORE`, `APPLICATION_ID`) VALUES
(1, 'Mario', 'Ferreira', '2015-12-04 00:00:00', 0, 1),
(2, 'Thibaud', 'Duchoud', '2015-12-04 00:00:00', 0, 1),
(3, 'Mathias', 'Dolt', '2015-12-02 00:00:00', 0, 1);

--
-- Contenu de la table `enduser_badge`
--

INSERT INTO `enduser_badge` (`EndUser_ID`, `badges_ID`) VALUES
(1, 1);

--
-- Contenu de la table `enduser_reward`
--

INSERT INTO `enduser_reward` (`EndUser_ID`, `rewards_ID`) VALUES
(1, 1);

--
-- Contenu de la table `reward`
--

INSERT INTO `reward` (`ID`, `CATEGORY`, `DESCRIPTION`, `IMAGE`, `NAME`, `APPLICATION_ID`) VALUES
(1, 'Category 1', 'Reward 1', 'https://www.key.com/kco/images/21_rewards_red_20x20.png', 'Reward 1', 1),
(2, 'Category 2', 'Reward 2', 'https://www.key.com/kco/images/21_rewards_red_20x20.png', 'Reward 2', 1);

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`ID`, `EMAIL`, `FIRSTNAME`, `LASTNAME`, `PASSWORD`) VALUES
(1, 'mario.ferreira@heig-vd.ch', 'Mario', 'Ferreira', '3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4');
SET foreign_key_checks = 1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
