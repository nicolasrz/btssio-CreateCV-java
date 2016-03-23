-- phpMyAdmin SQL Dump
-- version 3.3.9.1
-- http://www.phpmyadmin.net
--
-- Serveur: localhost
-- Généré le : Mer 10 Avril 2013 à 23:42
-- Version du serveur: 5.5.9
-- Version de PHP: 5.2.17

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `applietudiants`
--

-- --------------------------------------------------------

--
-- Structure de la table `cv_centres_dinteret`
--

CREATE TABLE IF NOT EXISTS `cv_centres_dinteret` (
  `id_centres_dinteret` int(11) NOT NULL AUTO_INCREMENT,
  `centres_dinteret` varchar(255) NOT NULL,
  `cd_description` varchar(255) NOT NULL,
  `identifiant` varchar(255) NOT NULL,
  PRIMARY KEY (`id_centres_dinteret`),
  KEY `identifiant` (`identifiant`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Contenu de la table `cv_centres_dinteret`
--


-- --------------------------------------------------------

--
-- Structure de la table `cv_experience_professionnelle`
--

CREATE TABLE IF NOT EXISTS `cv_experience_professionnelle` (
  `id_experience_professionnelle` int(11) NOT NULL AUTO_INCREMENT,
  `periode` varchar(255) NOT NULL,
  `emploi` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `entreprise` varchar(255) NOT NULL,
  `lieu` varchar(255) NOT NULL,
  `identifiant` varchar(255) NOT NULL,
  PRIMARY KEY (`id_experience_professionnelle`),
  KEY `identifiant` (`identifiant`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Contenu de la table `cv_experience_professionnelle`
--


-- --------------------------------------------------------

--
-- Structure de la table `cv_formation`
--

CREATE TABLE IF NOT EXISTS `cv_formation` (
  `id_formation` int(11) NOT NULL AUTO_INCREMENT,
  `periode` varchar(255) NOT NULL,
  `diplome` varchar(255) NOT NULL,
  `lieu` varchar(255) NOT NULL,
  `identifiant` varchar(255) NOT NULL,
  PRIMARY KEY (`id_formation`),
  KEY `identifiant` (`identifiant`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Contenu de la table `cv_formation`
--


-- --------------------------------------------------------

--
-- Structure de la table `cv_informatique`
--

CREATE TABLE IF NOT EXISTS `cv_informatique` (
  `id_informatique` int(255) NOT NULL AUTO_INCREMENT,
  `competences` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `identifiant` varchar(255) NOT NULL,
  PRIMARY KEY (`id_informatique`),
  KEY `identifiant` (`identifiant`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Contenu de la table `cv_informatique`
--


-- --------------------------------------------------------

--
-- Structure de la table `cv_langues`
--

CREATE TABLE IF NOT EXISTS `cv_langues` (
  `id_langues` int(11) NOT NULL AUTO_INCREMENT,
  `langues` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `identifiant` varchar(255) NOT NULL,
  PRIMARY KEY (`id_langues`),
  KEY `identifiant` (`identifiant`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Contenu de la table `cv_langues`
--


-- --------------------------------------------------------

--
-- Structure de la table `cv_photo`
--

CREATE TABLE IF NOT EXISTS `cv_photo` (
  `id_photo` int(11) NOT NULL AUTO_INCREMENT,
  `photoBinaire` longblob NOT NULL,
  `identifiant` varchar(255) NOT NULL,
  PRIMARY KEY (`id_photo`),
  KEY `identifiant` (`identifiant`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=89 ;

--
-- Contenu de la table `cv_photo`
--


-- --------------------------------------------------------

--
-- Structure de la table `cv_titre`
--

CREATE TABLE IF NOT EXISTS `cv_titre` (
  `id_titre` int(11) NOT NULL AUTO_INCREMENT,
  `titre` varchar(255) NOT NULL,
  `identifiant` varchar(255) NOT NULL,
  PRIMARY KEY (`id_titre`),
  KEY `identifiant` (`identifiant`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `cv_titre`
--


-- --------------------------------------------------------

--
-- Structure de la table `image`
--

CREATE TABLE IF NOT EXISTS `image` (
  `name` varchar(255) NOT NULL,
  `img` blob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `image`
--


-- --------------------------------------------------------

--
-- Structure de la table `utilisateurs`
--

CREATE TABLE IF NOT EXISTS `utilisateurs` (
  `identifiant` varchar(50) NOT NULL,
  `mot_de_passe` varchar(50) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `courriel` varchar(100) NOT NULL,
  `num_de_telephone` varchar(10) NOT NULL,
  `date_de_naissance` varchar(10) NOT NULL,
  `adresse` varchar(255) NOT NULL,
  `ville` varchar(255) NOT NULL,
  `codePostal` varchar(11) NOT NULL,
  `pays` varchar(255) NOT NULL,
  PRIMARY KEY (`identifiant`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `utilisateurs`
--

INSERT INTO `utilisateurs` (`identifiant`, `mot_de_passe`, `nom`, `prenom`, `courriel`, `num_de_telephone`, `date_de_naissance`, `adresse`, `ville`, `codePostal`, `pays`) VALUES
('admin', '21232f297a57a5a743894a0e4a801fc3', 'DuJardin', 'Jean', 'jean@dujardin@gmail.com', '0664646464', '01/01/01', '14 rue des peupliers', 'Pau', '64000', 'France');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `cv_centres_dinteret`
--
ALTER TABLE `cv_centres_dinteret`
  ADD CONSTRAINT `cv_centres_dinteret_ibfk_1` FOREIGN KEY (`identifiant`) REFERENCES `utilisateurs` (`identifiant`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `cv_experience_professionnelle`
--
ALTER TABLE `cv_experience_professionnelle`
  ADD CONSTRAINT `cv_experience_professionnelle_ibfk_1` FOREIGN KEY (`identifiant`) REFERENCES `utilisateurs` (`identifiant`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `cv_formation`
--
ALTER TABLE `cv_formation`
  ADD CONSTRAINT `cv_formation_ibfk_1` FOREIGN KEY (`identifiant`) REFERENCES `utilisateurs` (`identifiant`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `cv_informatique`
--
ALTER TABLE `cv_informatique`
  ADD CONSTRAINT `cv_informatique_ibfk_1` FOREIGN KEY (`identifiant`) REFERENCES `utilisateurs` (`identifiant`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `cv_langues`
--
ALTER TABLE `cv_langues`
  ADD CONSTRAINT `cv_langues_ibfk_1` FOREIGN KEY (`identifiant`) REFERENCES `utilisateurs` (`identifiant`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `cv_photo`
--
ALTER TABLE `cv_photo`
  ADD CONSTRAINT `cv_photo_ibfk_1` FOREIGN KEY (`identifiant`) REFERENCES `utilisateurs` (`identifiant`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `cv_titre`
--
ALTER TABLE `cv_titre`
  ADD CONSTRAINT `cv_titre_ibfk_1` FOREIGN KEY (`identifiant`) REFERENCES `utilisateurs` (`identifiant`) ON DELETE CASCADE ON UPDATE CASCADE;
