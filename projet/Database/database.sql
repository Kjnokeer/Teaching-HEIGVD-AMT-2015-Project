-- phpMyAdmin SQL Dump
-- version 4.4.12
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Ven 02 Octobre 2015 à 10:12
-- Version du serveur :  5.6.25
-- Version de PHP :  5.6.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `moussaraser`
--

-- --------------------------------------------------------

--
-- Structure de la table `apikey`
--

CREATE TABLE IF NOT EXISTS `apikey` (
  `apiId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `application`
--

CREATE TABLE IF NOT EXISTS `application` (
  `appId` int(11) NOT NULL,
  `ApiKey_apiId` int(11) NOT NULL,
  `users_useId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `enduser`
--

CREATE TABLE IF NOT EXISTS `enduser` (
  `endId` int(11) NOT NULL,
  `Application_appId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `rolId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `useId` int(11) NOT NULL,
  `useEmail` varchar(255) NOT NULL,
  `usePassword` char(64) NOT NULL,
  `useFirstName` varchar(25) NOT NULL,
  `useLastName` varchar(25) NOT NULL,
  `useRegistrationDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Contenu de la table `users`
--

INSERT INTO `users` (`useId`, `useEmail`, `usePassword`, `useFirstName`, `useLastName`, `useRegistrationDate`) VALUES
(1, 'mario.ferreira@heig-vd.ch', 'fe2592b42a727e977f055947385b709cc82b16b9a87f88c6abf3900d65d0cdc3', 'Mario', 'Ferreira', '2015-10-02 08:11:11'),
(2, 'jerome.moret@heig-vd.ch', 'd74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1', 'Jérôme', 'Moret', '2015-10-02 08:11:11'),
(3, 'thibaud.duchoud@heig-vd.ch', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'Thibaud', 'Duchoud', '2015-10-02 08:12:00'),
(4, 'mathias.dolt@heig-vd.ch', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', 'Mathias', 'Dolt', '2015-10-02 08:12:00');

-- --------------------------------------------------------

--
-- Structure de la table `users_has_role`
--

CREATE TABLE IF NOT EXISTS `users_has_role` (
  `users_useId` int(11) NOT NULL,
  `Role_rolId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Index pour les tables exportées
--

--
-- Index pour la table `apikey`
--
ALTER TABLE `apikey`
  ADD PRIMARY KEY (`apiId`);

--
-- Index pour la table `application`
--
ALTER TABLE `application`
  ADD PRIMARY KEY (`appId`,`ApiKey_apiId`,`users_useId`),
  ADD KEY `fk_Application_ApiKey_idx` (`ApiKey_apiId`),
  ADD KEY `fk_Application_users1_idx` (`users_useId`);

--
-- Index pour la table `enduser`
--
ALTER TABLE `enduser`
  ADD PRIMARY KEY (`endId`,`Application_appId`),
  ADD KEY `fk_endUser_Application1_idx` (`Application_appId`);

--
-- Index pour la table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`rolId`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`useId`);

--
-- Index pour la table `users_has_role`
--
ALTER TABLE `users_has_role`
  ADD PRIMARY KEY (`users_useId`,`Role_rolId`),
  ADD KEY `fk_users_has_Role_Role1_idx` (`Role_rolId`),
  ADD KEY `fk_users_has_Role_users1_idx` (`users_useId`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `apikey`
--
ALTER TABLE `apikey`
  MODIFY `apiId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `application`
--
ALTER TABLE `application`
  MODIFY `appId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `enduser`
--
ALTER TABLE `enduser`
  MODIFY `endId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `role`
--
ALTER TABLE `role`
  MODIFY `rolId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `useId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `application`
--
ALTER TABLE `application`
  ADD CONSTRAINT `fk_Application_ApiKey` FOREIGN KEY (`ApiKey_apiId`) REFERENCES `apikey` (`apiId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Application_users1` FOREIGN KEY (`users_useId`) REFERENCES `users` (`useId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `enduser`
--
ALTER TABLE `enduser`
  ADD CONSTRAINT `fk_endUser_Application1` FOREIGN KEY (`Application_appId`) REFERENCES `application` (`appId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `users_has_role`
--
ALTER TABLE `users_has_role`
  ADD CONSTRAINT `fk_users_has_Role_Role1` FOREIGN KEY (`Role_rolId`) REFERENCES `role` (`rolId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_users_has_Role_users1` FOREIGN KEY (`users_useId`) REFERENCES `users` (`useId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
