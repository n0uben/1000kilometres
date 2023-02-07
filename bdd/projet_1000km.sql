-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : lun. 06 fév. 2023 à 13:41
-- Version du serveur : 8.0.31
-- Version de PHP : 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données : `projet_1000km`
--

-- --------------------------------------------------------

--
-- Structure de la table `carte`
--

DROP TABLE IF EXISTS `carte`;
CREATE TABLE IF NOT EXISTS `carte` (
  `idCarte` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(20) NOT NULL,
  `km` int DEFAULT NULL,
  `effet` varchar(100) NOT NULL,
  `image` varchar(25) DEFAULT NULL,
  `noTypeCarte` int DEFAULT NULL,
  PRIMARY KEY (`idCarte`)
) ;

--
-- Déchargement des données de la table `carte`
--

INSERT INTO `carte` (`nom`, `km`, `effet`, `image`, `noTypeCarte`) VALUES
('25', 25, 'Vous avancez de 25Km', '25kmh.png', 1),
('50', 50, 'Vous avancez de 50Km', '50kmh.png', 1),
('75', 75, 'Vous avancez de 75Km', '75kmh.png', 1),
('100', 100, 'Vous avancez de 100Km', '100kmh.png', 1),
('200', 200, 'Vous avancez de 200Km', '200kmh.png', 1),
('Accident', NULL, 'Vous faîtes un accident. Vous devez réparer votre véhicule pour repartir.', 'accident.png', 2),
('Crevaison', NULL, 'Un de vos pneus est crevé. Vous devez mettre la roue de secours pour repartir.', 'crevaison.png', 2),
('Panne d\'essence', NULL, 'Votre réservoir est vide. Vous devez remettre de l\'essence.', 'panne.png', 2),
('Feu rouge', NULL, 'Vous êtes bloqué au feu rouge. Vous devez attendre qu\'il passe au vert pour repartir.', 'feurouge.png', 2),
('Limite de vitesse', NULL, 'Votre vitesse est limitée à 50Km/h. Vous devez attendre la fin de limitation pour accélérer.', 'limite50', 2),
('Réparations', NULL, 'Votre véhicule est réparé, vous pouvez repartir !', 'reparations.png', 3),
('Roue de secours', NULL, 'Vous changez votre roue, vous pouvez repartir !', 'rouesecours.png', 3),
('Essence', NULL, 'Vous avez fait le plein, vous pouvez repartir !', 'essence.png', 3),
('Feu vert', NULL, 'Le feu passe au vert, vous pouvez repartir !', 'feuvert.png', 3),
('Fin de Limite', NULL, 'Fin de la limitation de vitesse, vous pouvez accélérer !', 'finlimite50.png', 3),
('As du volant', NULL, 'Vous êtes un as du volant, vous ne craignez plus les accidents.', 'as.png', 4),
('Increvable', NULL, 'Vos pneus sont increvables, vous ne craignez plus les crevaisons.', 'increvable.png', 4),
('Citerne d\'essence', NULL, 'Vous disposez d\'une citerne d\'essence, votre réservoir ne sera plus jamais vide.', 'citerne.png', 4),
('Priorité', NULL, 'Votre véhicule est prioritaire, vous pouvez griller les feux rouges en toute impunité.', 'prio.png', 4);

-- --------------------------------------------------------

--
-- Structure de la table `defosse`
--

DROP TABLE IF EXISTS `defosse`;
CREATE TABLE IF NOT EXISTS `defosse` (
  `idDesfosse` int NOT NULL AUTO_INCREMENT,
  `idCarte` int DEFAULT NULL,
  `idPartie` int DEFAULT NULL,
  PRIMARY KEY (`idDesfosse`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `estposee`
--

DROP TABLE IF EXISTS `estposee`;
CREATE TABLE IF NOT EXISTS `estposee` (
  `idZone` int NOT NULL,
  `idCarte` int NOT NULL,
  `position` int DEFAULT NULL,
  PRIMARY KEY (`idZone`,`idCarte`)
) ;

-- --------------------------------------------------------

--
-- Structure de la table `joueur`
--

DROP TABLE IF EXISTS `joueur`;
CREATE TABLE IF NOT EXISTS `joueur` (
  `idJoueur` int NOT NULL AUTO_INCREMENT,
  `kmParcouru` int DEFAULT NULL,
  `peutAvancer` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idJoueur`)
) ;

-- --------------------------------------------------------

--
-- Structure de la table `main`
--

DROP TABLE IF EXISTS `main`;
CREATE TABLE IF NOT EXISTS `main` (
  `idMain` int NOT NULL AUTO_INCREMENT,
  `idCarte` int DEFAULT NULL,
  `idJoueur` int DEFAULT NULL,
  PRIMARY KEY (`idMain`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `partie`
--

DROP TABLE IF EXISTS `partie`;
CREATE TABLE IF NOT EXISTS `partie` (
  `idPartie` int NOT NULL AUTO_INCREMENT,
  `nombreJoueur` int DEFAULT NULL,
  `dureeTour` int DEFAULT NULL,
  `idJoueur` int DEFAULT NULL,
  PRIMARY KEY (`idPartie`)
) ;

-- --------------------------------------------------------

--
-- Structure de la table `pioche`
--

DROP TABLE IF EXISTS `pioche`;
CREATE TABLE IF NOT EXISTS `pioche` (
  `idPioche` int NOT NULL AUTO_INCREMENT,
  `idCarte` int DEFAULT NULL,
  `idPartie` int DEFAULT NULL,
  PRIMARY KEY (`idPioche`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `sontamis`
--

DROP TABLE IF EXISTS `sontamis`;
CREATE TABLE IF NOT EXISTS `sontamis` (
  `idUtilisateur1` int NOT NULL,
  `idUtilisateur2` int NOT NULL,
  PRIMARY KEY (`idUtilisateur1`,`idUtilisateur2`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `typecarte`
--

DROP TABLE IF EXISTS `typecarte`;
CREATE TABLE IF NOT EXISTS `typecarte` (
  `noTypeCarte` int NOT NULL AUTO_INCREMENT,
  `nomTypeCarte` varchar(20) NOT NULL,
  PRIMARY KEY (`noTypeCarte`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Déchargement des données de la table `typecarte`
--

INSERT INTO `typecarte` (`nomTypeCarte`) VALUES
('kilometre'),
('attaque'),
('parade'),
('joker');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `idUtilisateur` int NOT NULL AUTO_INCREMENT,
  `pseudo` varchar(15) DEFAULT NULL,
  `motDePasse` varchar(20) DEFAULT NULL,
  `nbPartiesJouees` int DEFAULT NULL,
  `nbPartiesGagnees` int DEFAULT NULL,
  PRIMARY KEY (`idUtilisateur`)
) ;

-- --------------------------------------------------------

--
-- Structure de la table `zonedejeu`
--

DROP TABLE IF EXISTS `zonedejeu`;
CREATE TABLE IF NOT EXISTS `zonedejeu` (
  `idZone` int NOT NULL AUTO_INCREMENT,
  `idJoueur` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`idZone`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
