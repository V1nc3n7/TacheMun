-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:8889
-- Généré le :  Dim 11 nov. 2018 à 10:22
-- Version du serveur :  5.7.23
-- Version de PHP :  7.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `id7426850_androidtask`
--

-- --------------------------------------------------------

--
-- Structure de la table `AssigneTache`
--

DROP TABLE IF EXISTS `AssigneTache`;
CREATE TABLE `AssigneTache` (
  `ID_AssigneTache` int(11) NOT NULL,
  `ID_MembreAssigneur` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `ID_Tache` int(11) DEFAULT NULL,
  `message` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ID_tacheur` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT 'qui realisera la tache',
  `dateHeure_AssigneTache` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `Contacts`
--

DROP TABLE IF EXISTS `Contacts`;
CREATE TABLE `Contacts` (
  `ID_Contact` int(11) NOT NULL,
  `ID_Proprietaire` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `ID_Utilisateur` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `DateHeure_Contact` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `Contacts`
--

INSERT INTO `Contacts` (`ID_Contact`, `ID_Proprietaire`, `ID_Utilisateur`, `DateHeure_Contact`) VALUES
(7, 'Baltazar', 'Gontran', '2018-10-12 19:37:06'),
(8, 'Baltazar', 'DonaldDuck', '2018-10-12 19:37:06'),
(9, 'Romeo', 'Juliette', '2018-10-12 19:37:06'),
(10, 'DonaldDuck', 'Gontran', '2018-10-12 19:37:06'),
(11, 'Fifi', 'Riri', '2018-10-12 19:37:06'),
(12, 'Loulou', 'Fifi', '2018-10-12 19:37:06');

-- --------------------------------------------------------

--
-- Structure de la table `Droits`
--

DROP TABLE IF EXISTS `Droits`;
CREATE TABLE `Droits` (
  `ID_Droits` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `email_disposable`
--

DROP TABLE IF EXISTS `email_disposable`;
CREATE TABLE `email_disposable` (
  `id` int(10) UNSIGNED NOT NULL,
  `domaine` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `email_disposable`
--

INSERT INTO `email_disposable` (`id`, `domaine`) VALUES
(1, '0815.ru0clickemail.com'),
(2, '0-mail.com'),
(3, '0wnd.net'),
(4, '0wnd.org'),
(5, '10minutemail.com'),
(6, '20minutemail.com'),
(7, '2prong.com'),
(8, '3d-painting.com'),
(9, '4warding.com'),
(10, '4warding.net'),
(11, '4warding.org'),
(12, '9ox.net'),
(13, 'a-bc.net'),
(14, 'ag.us.to'),
(15, 'amilegit.com'),
(16, 'anonbox.net'),
(17, 'anonymbox.com'),
(18, 'antichef.com'),
(19, 'antichef.net'),
(20, 'antispam.de'),
(21, 'baxomale.ht.cx'),
(22, 'beefmilk.com'),
(23, 'binkmail.com'),
(24, 'bio-muesli.net'),
(25, 'bobmail.info'),
(26, 'bodhi.lawlita.com'),
(27, 'bofthew.com'),
(28, 'brefmail.com'),
(29, 'bsnow.net'),
(30, 'bugmenot.com'),
(31, 'bumpymail.com'),
(32, 'casualdx.com'),
(33, 'chogmail.com'),
(34, 'cool.fr.nf'),
(35, 'correo.blogos.net'),
(36, 'cosmorph.com'),
(37, 'courriel.fr.nf'),
(38, 'courrieltemporaire.com'),
(39, 'curryworld.de'),
(40, 'cust.in'),
(41, 'dacoolest.com'),
(42, 'dandikmail.com'),
(43, 'deadaddress.com'),
(44, 'despam.it'),
(45, 'despam.it'),
(46, 'devnullmail.com'),
(47, 'dfgh.net'),
(48, 'digitalsanctuary.com'),
(49, 'discardmail.com'),
(50, 'discardmail.de'),
(51, 'disposableaddress.com'),
(52, 'disposeamail.com'),
(53, 'disposemail.com'),
(54, 'dispostable.com'),
(55, 'dm.w3internet.co.ukexample.com'),
(56, 'dodgeit.com'),
(57, 'dodgit.com'),
(58, 'dodgit.org'),
(59, 'dontreg.com'),
(60, 'dontsendmespam.de'),
(61, 'dump-email.info'),
(62, 'dumpyemail.com'),
(63, 'e4ward.com'),
(64, 'email60.com'),
(65, 'emailias.com'),
(66, 'emailias.com'),
(67, 'emailinfive.com'),
(68, 'emailmiser.com'),
(69, 'emailtemporario.com.br'),
(70, 'emailwarden.com'),
(71, 'enterto.com'),
(72, 'ephemail.net'),
(73, 'explodemail.com'),
(74, 'fakeinbox.com'),
(75, 'fakeinformation.com'),
(76, 'fansworldwide.de'),
(77, 'fastacura.com'),
(78, 'filzmail.com'),
(79, 'fixmail.tk'),
(80, 'fizmail.com'),
(81, 'frapmail.com'),
(82, 'garliclife.com'),
(83, 'gelitik.in'),
(84, 'get1mail.com'),
(85, 'getonemail.com'),
(86, 'getonemail.net'),
(87, 'girlsundertheinfluence.com'),
(88, 'gishpuppy.com'),
(89, 'goemailgo.com'),
(90, 'great-host.in'),
(91, 'greensloth.com'),
(92, 'greensloth.com'),
(93, 'gsrv.co.uk'),
(94, 'guerillamail.biz'),
(95, 'guerillamail.com'),
(96, 'guerillamail.net'),
(97, 'guerillamail.org'),
(98, 'guerrillamail.biz'),
(99, 'guerrillamail.com'),
(100, 'guerrillamail.de'),
(101, 'guerrillamail.net'),
(102, 'guerrillamail.org'),
(103, 'guerrillamailblock.com'),
(104, 'haltospam.com'),
(105, 'hidzz.com'),
(106, 'hotpop.com'),
(107, 'ieatspam.eu'),
(108, 'ieatspam.info'),
(109, 'ihateyoualot.info'),
(110, 'imails.info'),
(111, 'inboxclean.com'),
(112, 'inboxclean.org'),
(113, 'incognitomail.com'),
(114, 'incognitomail.net'),
(115, 'ipoo.org'),
(116, 'irish2me.com'),
(117, 'jetable.com'),
(118, 'jetable.fr.nf'),
(119, 'jetable.net'),
(120, 'jetable.org'),
(121, 'jnxjn.com'),
(122, 'junk1e.com'),
(123, 'kasmail.com'),
(124, 'kaspop.com'),
(125, 'klzlk.com'),
(126, 'kulturbetrieb.info'),
(127, 'kurzepost.de'),
(128, 'kurzepost.de'),
(129, 'lifebyfood.com'),
(130, 'link2mail.net'),
(131, 'litedrop.com'),
(132, 'lookugly.com'),
(133, 'lopl.co.cc'),
(134, 'lr78.com'),
(135, 'maboard.com'),
(136, 'mail.by'),
(137, 'mail.mezimages.net'),
(138, 'mail4trash.com'),
(139, 'mailbidon.com'),
(140, 'mailcatch.com'),
(141, 'maileater.com'),
(142, 'mailexpire.com'),
(143, 'mailin8r.com'),
(144, 'mailinator.com'),
(145, 'mailinator.net'),
(146, 'mailinator2.com'),
(147, 'mailincubator.com'),
(148, 'mailme.lv'),
(149, 'mailmetrash.com'),
(150, 'mailmoat.com'),
(151, 'mailnator.com'),
(152, 'mailnull.com'),
(153, 'mailzilla.org'),
(154, 'mbx.cc'),
(155, 'mega.zik.dj'),
(156, 'meltmail.com'),
(157, 'mierdamail.com'),
(158, 'mintemail.com'),
(159, 'mjukglass.nu'),
(160, 'mobi.web.id'),
(161, 'moburl.com'),
(162, 'moncourrier.fr.nf'),
(163, 'monemail.fr.nf'),
(164, 'monmail.fr.nf'),
(165, 'mt2009.com'),
(166, 'mx0.wwwnew.eu'),
(167, 'mycleaninbox.net'),
(168, 'myspamless.com'),
(169, 'mytempemail.com'),
(170, 'mytrashmail.com'),
(171, 'netmails.net'),
(172, 'neverbox.com'),
(173, 'no-spam.ws'),
(174, 'nobulk.com'),
(175, 'noclickemail.com'),
(176, 'nogmailspam.info'),
(177, 'nomail.xl.cx'),
(178, 'nomail2me.com'),
(179, 'nospam.ze.tc'),
(180, 'nospam4.us'),
(181, 'nospamfor.us'),
(182, 'nowmymail.com'),
(183, 'objectmail.com'),
(184, 'obobbo.com'),
(185, 'odaymail.com'),
(186, 'onewaymail.com'),
(187, 'ordinaryamerican.net'),
(188, 'owlpic.com'),
(189, 'pookmail.com'),
(190, 'privymail.de'),
(191, 'proxymail.eu'),
(192, 'punkass.com'),
(193, 'putthisinyourspamdatabase.com'),
(194, 'quickinbox.com'),
(195, 'rcpt.at'),
(196, 'recode.me'),
(197, 'recursor.net'),
(198, 'regbypass.comsafe-mail.net'),
(199, 'safetymail.info'),
(200, 'sandelf.de'),
(201, 'saynotospams.com'),
(202, 'selfdestructingmail.com'),
(203, 'sendspamhere.com'),
(204, 'sharklasers.com'),
(205, 'shieldedmail.com'),
(206, 'shiftmail.com'),
(207, 'skeefmail.com'),
(208, 'slopsbox.com'),
(209, 'slushmail.com'),
(210, 'smaakt.naar.gravel'),
(211, 'smellfear.com'),
(212, 'snakemail.com'),
(213, 'sneakemail.com'),
(214, 'sofort-mail.de'),
(215, 'sogetthis.com'),
(216, 'soodonims.com'),
(217, 'spam.la'),
(218, 'spamavert.com'),
(219, 'spambob.net'),
(220, 'spambob.org'),
(221, 'spambog.com'),
(222, 'spambog.de'),
(223, 'spambog.ru'),
(224, 'spambox.info'),
(225, 'spambox.us'),
(226, 'spamcannon.com'),
(227, 'spamcannon.net'),
(228, 'spamcero.com'),
(229, 'spamcorptastic.com'),
(230, 'spamcowboy.com'),
(231, 'spamcowboy.net'),
(232, 'spamcowboy.org'),
(233, 'spamday.com'),
(234, 'spamex.com'),
(235, 'spamfree.eu'),
(236, 'spamfree24.com'),
(237, 'spamfree24.de'),
(238, 'spamfree24.eu'),
(239, 'spamfree24.info'),
(240, 'spamfree24.net'),
(241, 'spamfree24.org'),
(242, 'spamgourmet.com'),
(243, 'spamgourmet.net'),
(244, 'spamgourmet.org'),
(245, 'spamherelots.com'),
(246, 'spamhereplease.com'),
(247, 'spamhole.com'),
(248, 'spamify.com'),
(249, 'spaminator.de'),
(250, 'spamkill.info'),
(251, 'spaml.com'),
(252, 'spaml.de'),
(253, 'spammotel.com'),
(254, 'spamobox.com'),
(255, 'spamspot.com'),
(256, 'spamthis.co.uk'),
(257, 'spamthisplease.com'),
(258, 'speed.1s.fr'),
(259, 'suremail.info'),
(260, 'tempalias.com'),
(261, 'tempe-mail.com'),
(262, 'tempemail.biz'),
(263, 'tempemail.com'),
(264, 'tempemail.net'),
(265, 'tempinbox.co.uk'),
(266, 'tempinbox.com'),
(267, 'tempomail.fr'),
(268, 'temporaryemail.net'),
(269, 'temporaryinbox.com'),
(270, 'tempymail.com'),
(271, 'thankyou2010.com'),
(272, 'thisisnotmyrealemail.com'),
(273, 'throwawayemailaddress.com'),
(274, 'tilien.com'),
(275, 'tmailinator.com'),
(276, 'tradermail.info'),
(277, 'trash-amil.com'),
(278, 'trash-mail.at'),
(279, 'trash-mail.com'),
(280, 'trash-mail.de'),
(281, 'trash2009.com'),
(282, 'trashmail.at'),
(283, 'trashmail.com'),
(284, 'trashmail.me'),
(285, 'trashmail.net'),
(286, 'trashmailer.com'),
(287, 'trashymail.com'),
(288, 'trashymail.net'),
(289, 'trillianpro.com'),
(290, 'tyldd.com'),
(291, 'tyldd.com'),
(292, 'uggsrock.com'),
(293, 'wegwerfmail.de'),
(294, 'wegwerfmail.net'),
(295, 'wegwerfmail.org'),
(296, 'wh4f.org'),
(297, 'whyspam.me'),
(298, 'willselfdestruct.com'),
(299, 'winemaven.info'),
(300, 'wronghead.com'),
(301, 'wuzupmail.net'),
(302, 'xoxy.net'),
(303, 'yogamaven.com'),
(304, 'yopmail.com'),
(305, 'yopmail.fr'),
(306, 'yopmail.net'),
(307, 'yuurok.com'),
(308, 'zippymail.info'),
(309, 'zoemail.com');

-- --------------------------------------------------------

--
-- Structure de la table `Groupe`
--

DROP TABLE IF EXISTS `Groupe`;
CREATE TABLE `Groupe` (
  `ID_Groupe` int(11) NOT NULL,
  `ID_createurGroupe` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `nom_Groupe` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `dateCreation_Groupe` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `description_Groupe` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `prive_Groupe` tinyint(4) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `Groupe`
--

INSERT INTO `Groupe` (`ID_Groupe`, `ID_createurGroupe`, `nom_Groupe`, `dateCreation_Groupe`, `description_Groupe`, `prive_Groupe`) VALUES
(1, 'Zeus', 'OLYMPUS', '2018-11-11 10:13:31', 'Les dieux de l\'Olympe', 1),
(2, 'Loulou', 'RFLDUCKS', '2018-11-11 10:13:31', 'Les neveux de Donald', 1),
(3, 'XavierLeChauve', 'XMen', '2018-11-11 10:13:31', 'Les X-Men', 1),
(4, 'Mirach', 'Groupe mirach', '2018-11-11 10:21:13', 'test', 0);

--
-- Déclencheurs `Groupe`
--
DROP TRIGGER IF EXISTS `Cree_Membre_apres_creationGroupe`;
DELIMITER $$
CREATE TRIGGER `Cree_Membre_apres_creationGroupe` AFTER INSERT ON `Groupe` FOR EACH ROW BEGIN


  INSERT INTO `ListeTache` (`nom_ListeTache` , `boolPerso_ListeTache`, `description_ListeTache` 
 , `dateHeureCreation_ListeTache`, `echeanceTotale_ListeTache`) VALUES 
 ('Liste principale',0,'Liste principale contenant les tâches à réaliser par les membres du groupe'
 ,NEW.dateCreation_Groupe,NULL);
 
 
 INSERT INTO `ListeTacheGroupe` (`ID_Groupe`, `ID_ListeTache`, `ID_CreateurMembre`) 
 VALUES (NEW.ID_Groupe,(select MAX(ID_ListeTache) from ListeTache ) , New.ID_createurGroupe);

INSERT INTO `Membre`
 (`pseudoUtilisateur_Membre`, `dateAdhesion_Membre`, `ID_Groupe`) 
 VALUES (NEW.ID_createurGroupe,NEW.dateCreation_Groupe,NEW.ID_Groupe); 
 
 

 
 END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `ListeTache`
--

DROP TABLE IF EXISTS `ListeTache`;
CREATE TABLE `ListeTache` (
  `ID_ListeTache` int(11) NOT NULL,
  `nom_ListeTache` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `boolPerso_ListeTache` tinyint(1) NOT NULL COMMENT '1= liste Perso ,0=liste groupe',
  `description_ListeTache` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dateHeureCreation_ListeTache` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `echeanceTotale_ListeTache` datetime DEFAULT NULL,
  `couleur` int(11) DEFAULT NULL COMMENT 'couleur de la liste'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `ListeTache`
--

INSERT INTO `ListeTache` (`ID_ListeTache`, `nom_ListeTache`, `boolPerso_ListeTache`, `description_ListeTache`, `dateHeureCreation_ListeTache`, `echeanceTotale_ListeTache`, `couleur`) VALUES
(1, 'listeDucks1', 0, NULL, '2018-10-12 20:10:57', NULL, NULL),
(14, 'Liste principale', 0, 'Liste principale contenant les tâches à réaliser par les membres du groupe', '2018-10-10 04:11:13', NULL, NULL),
(19, 'Liste principale', 1, 'Liste principale contenant les tâches à réaliser', '2018-10-30 21:20:02', NULL, NULL),
(20, 'Liste principale', 1, 'Liste principale contenant les tâches à réaliser', '2018-10-30 21:20:02', NULL, NULL),
(21, 'Liste principale', 0, 'Liste principale contenant les tâches à réaliser par les membres du groupe', '2018-10-08 07:00:17', NULL, NULL),
(34, 'Liste principale', 1, 'Liste principale contenant les tâches à réaliser', '2018-10-09 11:17:15', NULL, NULL),
(35, 'Liste principale', 1, 'Liste principale contenant les tâches à réaliser', '2018-10-09 11:17:15', NULL, NULL),
(36, 'Liste principale', 1, 'Liste principale contenant les tâches à réaliser', '2018-10-09 11:17:15', NULL, NULL),
(37, 'Liste principale', 1, 'Liste principale contenant les tâches à réaliser', '2018-10-09 11:17:15', NULL, NULL),
(38, 'Liste principale', 1, 'Liste principale contenant les tâches à réaliser', '2018-10-09 11:17:15', NULL, NULL),
(39, 'Liste principale', 1, 'Liste principale contenant les tâches à réaliser', '2018-10-09 11:17:15', NULL, NULL),
(40, 'Liste principale', 1, 'Liste principale contenant les tâches à réaliser', '2018-10-09 11:17:15', NULL, NULL),
(41, 'Liste principale', 1, 'Liste principale contenant les tâches à réaliser', '2018-10-09 11:17:15', NULL, NULL),
(42, 'Liste principale', 1, 'Liste principale contenant les tâches à réaliser', '2018-10-09 11:17:15', NULL, NULL),
(43, 'Liste principale', 1, 'Liste principale contenant les tâches à réaliser', '2018-10-09 11:17:15', NULL, NULL),
(44, 'Liste principale', 1, 'Liste principale contenant les tâches à réaliser', '2018-10-09 11:17:15', NULL, NULL),
(45, 'Liste principale', 1, 'Liste principale contenant les tâches à réaliser', '2018-10-09 11:17:15', NULL, NULL),
(46, 'Liste principale', 0, 'Liste principale contenant les tâches à réaliser par les membres du groupe', '2018-11-11 10:21:13', NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `ListeTacheGroupe`
--

DROP TABLE IF EXISTS `ListeTacheGroupe`;
CREATE TABLE `ListeTacheGroupe` (
  `ID_ListeTacheGroupe` int(11) NOT NULL,
  `ID_Groupe` int(11) NOT NULL,
  `ID_ListeTache` int(11) NOT NULL,
  `ID_CreateurMembre` varchar(32) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `ListeTacheGroupe`
--

INSERT INTO `ListeTacheGroupe` (`ID_ListeTacheGroupe`, `ID_Groupe`, `ID_ListeTache`, `ID_CreateurMembre`) VALUES
(1, 2, 1, 'Riri'),
(4, 3, 14, 'XavierLeChauve'),
(5, 1, 21, 'Zeus'),
(6, 4, 46, 'Mirach');

-- --------------------------------------------------------

--
-- Structure de la table `Membre`
--

DROP TABLE IF EXISTS `Membre`;
CREATE TABLE `Membre` (
  `ID_Membre` int(11) NOT NULL,
  `pseudoUtilisateur_Membre` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `dateAdhesion_Membre` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ID_Groupe` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `Membre`
--

INSERT INTO `Membre` (`ID_Membre`, `pseudoUtilisateur_Membre`, `dateAdhesion_Membre`, `ID_Groupe`) VALUES
(1, 'Fifi', '2018-10-08 06:14:00', 2),
(2, 'Riri', '2018-10-08 06:14:00', 2),
(3, 'Loulou', '2018-10-08 06:14:00', 2),
(16, 'XavierLeChauve', '2018-10-10 04:11:13', 3),
(17, 'Zeus', '2018-10-08 07:00:17', 1),
(18, 'Hermes', '2018-10-30 21:22:00', 1),
(31, 'Poséidon', '2018-10-30 21:48:47', 1),
(32, 'Hadès', '2018-10-30 21:48:47', 1),
(33, 'Déméter', '2018-10-30 21:48:47', 1),
(34, 'Hestia', '2018-10-30 21:48:47', 1),
(35, 'Héra', '2018-10-30 21:48:47', 1),
(36, 'Aphrodite', '2018-10-30 21:48:47', 1),
(37, 'Apollon', '2018-10-30 21:48:47', 1),
(38, 'Artémis', '2018-10-30 21:48:47', 1),
(39, 'Athéna', '2018-10-30 21:48:47', 1),
(40, 'Arès', '2018-10-30 21:48:47', 1),
(41, 'Héphaïstos', '2018-10-30 21:48:47', 1),
(42, 'Dionysos', '2018-10-30 21:48:47', 1),
(43, 'Mirach', '2018-11-11 10:21:13', 4);

-- --------------------------------------------------------

--
-- Structure de la table `PartageListe`
--

DROP TABLE IF EXISTS `PartageListe`;
CREATE TABLE `PartageListe` (
  `ID_PartageListe` int(11) NOT NULL,
  `ID_ProprietaireListe` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `ID_ListeTache` int(11) NOT NULL,
  `ID_UtilisateurInvite` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `dateHeurePartage` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `messagePartage` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `ProprietaireListe`
--

DROP TABLE IF EXISTS `ProprietaireListe`;
CREATE TABLE `ProprietaireListe` (
  `ID_ProprietaireListe` int(11) NOT NULL,
  `pseudo_Utilisateur_Proprietaire` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `ID_ListeTache` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `ProprietaireListe`
--

INSERT INTO `ProprietaireListe` (`ID_ProprietaireListe`, `pseudo_Utilisateur_Proprietaire`, `ID_ListeTache`) VALUES
(3, 'Zeus', 19),
(4, 'Hermes', 20),
(17, 'Poséidon', 34),
(18, 'Hadès', 35),
(19, 'Déméter', 36),
(20, 'Hestia', 37),
(21, 'Héra', 38),
(22, 'Aphrodite', 39),
(23, 'Apollon', 40),
(24, 'Artémis', 41),
(25, 'Athéna', 42),
(26, 'Arès', 43),
(27, 'Héphaïstos', 44),
(28, 'Dionysos', 45);

-- --------------------------------------------------------

--
-- Structure de la table `RealiseTache`
--

DROP TABLE IF EXISTS `RealiseTache`;
CREATE TABLE `RealiseTache` (
  `ID_RealiseTache` int(11) NOT NULL,
  `ID_realiseurTache` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `ID_Tache` int(11) NOT NULL,
  `dateHeureRealisation` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `details_RealiseTache` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `Tache`
--

DROP TABLE IF EXISTS `Tache`;
CREATE TABLE `Tache` (
  `ID_Tache` int(11) NOT NULL,
  `ID_ListeTache` int(11) NOT NULL,
  `ID_createurTache` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `libelle_Tache` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `description_Tache` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dateHeureCreation_Tache` timestamp NULL DEFAULT NULL,
  `numero_Tache` int(11) NOT NULL,
  `prioriteTache` int(11) NOT NULL,
  `echeance_Tache` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `Utilisateur`
--

DROP TABLE IF EXISTS `Utilisateur`;
CREATE TABLE `Utilisateur` (
  `pseudo_Utilisateur` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `dateInscription_Utilisateur` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `motDePasse_Utilisateur` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `mail_Utilisateur` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `Utilisateur`
--

INSERT INTO `Utilisateur` (`pseudo_Utilisateur`, `dateInscription_Utilisateur`, `motDePasse_Utilisateur`, `mail_Utilisateur`) VALUES
('Aphrodite', '2018-10-09 11:17:15', '97d7458d9d99af25c91f3a50aca66b58e3a9dfa1', 'aphrodite@olympe.gouv'),
('Apollon', '2018-10-09 11:17:15', '2346bd49f3482f908ee2692e0cc8970349c7870a', 'apollon@olympe.gouv'),
('Arès', '2018-10-09 11:17:15', '6ab6bfcdd4aaa4a7fd669956c6ac71f1dfdcc59a', 'arès@olympe.gouv'),
('Artémis', '2018-10-09 11:17:15', '7626a90f320eeb4e901fea39f7fe7981bc47124a', 'artémis@olympe.gouv'),
('Athéna', '2018-10-09 11:17:15', '82b46aadddae257b1138ad46726fbe2335f675bd', 'athéna@olympe.gouv'),
('Baltazar', '2018-10-12 18:19:01', '92664c8b5e12c9e589082da70f8f0ae93ebadd03', 'picsou@mail.com'),
('Déméter', '2018-10-09 11:17:15', '767a5c48963842a5b73dade6d6f9b32b9f4eee75', 'déméter@olympe.gouv'),
('Dionysos', '2018-10-09 11:17:15', '6134cdc88bed7e233c6df96d3be692479d67ea0a', 'dionysos@olympe.gouv'),
('DonaldDuck', '2018-10-12 18:19:01', '5ece240085b9ad85b64896082e3761c54ef581de', 'duck@mail.com'),
('Fifi', '2018-10-10 18:18:12', '4ca19d35e1ed1f92c5c3b41a434e50f5321cc96e', 'fifi@mail.com'),
('Gontran', '2018-10-12 18:19:01', 'bb4b19a002fff5c0748c4971457e96a7a5b41007', 'g.bonheur@mail.com'),
('Hadès', '2018-10-09 11:17:15', '74448995e62ca4dd35520f6daddb812f2775851e', 'hadès@olympe.gouv'),
('Héphaïstos', '2018-10-09 11:17:15', 'e760c72b05d5640f276a09964e3bf830cdd01f00', 'héphaïstos@olympe.gouv'),
('Héra', '2018-10-09 11:17:15', '65d0f2be0e5c31224d637393d9f58932ad2d099f', 'héra@olympe.gouv'),
('Hermes', '2018-10-30 21:20:02', '486b575ab9b42f8383c353dde366bc582a35e842', 'hermes@olympe.gouv'),
('Hestia', '2018-10-09 11:17:15', '799787f790faa68b4fd1aa8a3912af1e27e8ceae', 'hestia@olympe.gouv'),
('Juliette', '2018-10-12 18:11:00', 'bda3442e2fb6a5b7851fd4ce68277169632a0580', 'Capulet@mail.com'),
('Loulou', '2018-10-10 18:18:05', 'eeba817249902f0c735e59078fa324b077c84b7b', 'loulou@mail.com'),
('Magneto', '2018-09-17 18:11:00', '016fb208b0effd0c11626a9ae3d86b05bba6aa53', 'Magneto@gmail.com'),
('Mirach', '2018-10-12 19:45:00', '02074bf34fd32c2e0213939973defdb237a4f272', 'Mirach@gmail.com'),
('Optimus', '2018-03-23 17:59:00', 'e426ddc6348218f1f801c4bc1d259bcc33a08629', 'Optimus@gmail.com'),
('Poséidon', '2018-10-09 11:17:15', '04b86393ebfba04118610e25226c21698d6de85f', 'poséidon@olympe.gouv'),
('Prime', '2018-10-12 20:13:00', '2533d6c74ece4f64b719ce0c4dd0cf2af0dfe36c', 'Prime@gmail.com'),
('Riri', '2018-10-02 11:12:12', 'ae52bde76416737a960d8a5564e3639f85eaddf3', 'riri@mail.com'),
('Romeo', '2018-10-12 18:11:00', '78f5c499b948b9ee7c99eb80d34e13a8ab249370', 'Montaigu@gmail.com'),
('Toto', '2018-10-13 17:59:58', '0b9c2625dc21ef05f6ad4ddf47c5f203837aa32c', 'toto@hotmail.fr'),
('XavierLeChauve', '1998-09-14 06:00:00', 'c5cba48610a6dd1409885580e4a50a9894014ea2', 'charles.xavier@gmail.com'),
('Zeus', '2018-10-30 21:20:02', 'fc58d173ddb3c6636e00ec1f54b83e9467c2ffbe', 'zeus@olympe.gouv');

--
-- Déclencheurs `Utilisateur`
--
DROP TRIGGER IF EXISTS `Cree_utilisateur_ListeTache_proprietaireListeTaches`;
DELIMITER $$
CREATE TRIGGER `Cree_utilisateur_ListeTache_proprietaireListeTaches` AFTER INSERT ON `Utilisateur` FOR EACH ROW BEGIN

  INSERT INTO `ListeTache` (`nom_ListeTache` , `boolPerso_ListeTache`, `description_ListeTache` 
 , `dateHeureCreation_ListeTache`, `echeanceTotale_ListeTache`) VALUES 
 ('Liste principale',1,'Liste principale contenant les tâches à réaliser'
 ,NEW.dateInscription_Utilisateur,NULL);

INSERT INTO `ProprietaireListe` (`pseudo_Utilisateur_Proprietaire`, `ID_ListeTache`) VALUES (New.pseudo_Utilisateur, (SELECT max(ID_ListeTache) from ListeTache  ));

END
$$
DELIMITER ;
DROP TRIGGER IF EXISTS `suppr_ListeTache_avec_Utilisateur`;
DELIMITER $$
CREATE TRIGGER `suppr_ListeTache_avec_Utilisateur` BEFORE DELETE ON `Utilisateur` FOR EACH ROW BEGIN
DELETE FROM ListeTache WHERE ListeTache.ID_ListeTache=(SELECT ProprietaireListe.ID_ListeTache FROM ProprietaireListe WHERE ProprietaireListe.pseudo_Utilisateur_Proprietaire=OLD.pseudo_Utilisateur);
END
$$
DELIMITER ;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `AssigneTache`
--
ALTER TABLE `AssigneTache`
  ADD PRIMARY KEY (`ID_AssigneTache`),
  ADD KEY `fk_AssigneTache_Tache_idx` (`ID_Tache`),
  ADD KEY `fk_AssigneTache_Membre` (`ID_tacheur`),
  ADD KEY `fk_AssigneTache_Membre2` (`ID_MembreAssigneur`);

--
-- Index pour la table `Contacts`
--
ALTER TABLE `Contacts`
  ADD PRIMARY KEY (`ID_Contact`),
  ADD KEY `fk_Contacts_Utlisateur1` (`ID_Proprietaire`),
  ADD KEY `fk_Contacts_Utlisateur2` (`ID_Utilisateur`);

--
-- Index pour la table `Droits`
--
ALTER TABLE `Droits`
  ADD PRIMARY KEY (`ID_Droits`);

--
-- Index pour la table `email_disposable`
--
ALTER TABLE `email_disposable`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `Groupe`
--
ALTER TABLE `Groupe`
  ADD PRIMARY KEY (`ID_Groupe`),
  ADD KEY `fk_Groupe_Utilisateur_idx` (`ID_createurGroupe`);

--
-- Index pour la table `ListeTache`
--
ALTER TABLE `ListeTache`
  ADD PRIMARY KEY (`ID_ListeTache`);

--
-- Index pour la table `ListeTacheGroupe`
--
ALTER TABLE `ListeTacheGroupe`
  ADD PRIMARY KEY (`ID_ListeTacheGroupe`),
  ADD KEY `fk_ListeTacheGroupe_Utilisateur_idx` (`ID_CreateurMembre`),
  ADD KEY `fk_ListeTacheGroupe_Groupe_idx` (`ID_Groupe`),
  ADD KEY `fk_ListeTacheGroupe_ListeTache_idx` (`ID_ListeTache`);

--
-- Index pour la table `Membre`
--
ALTER TABLE `Membre`
  ADD PRIMARY KEY (`ID_Membre`),
  ADD KEY `fk_Menmbre_Groupe_idx` (`ID_Groupe`),
  ADD KEY `fk_Membre_Utilisateur_idx` (`pseudoUtilisateur_Membre`);

--
-- Index pour la table `PartageListe`
--
ALTER TABLE `PartageListe`
  ADD PRIMARY KEY (`ID_PartageListe`),
  ADD KEY `fk_PartageListe_ListeTache` (`ID_ListeTache`),
  ADD KEY `fk_PartageListe_Utilisateur_Proprio` (`ID_ProprietaireListe`),
  ADD KEY `fk_PartageListe_Utilisateur_Invite` (`ID_UtilisateurInvite`);

--
-- Index pour la table `ProprietaireListe`
--
ALTER TABLE `ProprietaireListe`
  ADD PRIMARY KEY (`ID_ProprietaireListe`),
  ADD KEY `fk_proprietaireListe_Liste_idx` (`ID_ListeTache`),
  ADD KEY `fk_ProprietaireListe_Utilisateur_idx` (`pseudo_Utilisateur_Proprietaire`);

--
-- Index pour la table `RealiseTache`
--
ALTER TABLE `RealiseTache`
  ADD PRIMARY KEY (`ID_RealiseTache`),
  ADD KEY `fk_RealiseTache_Utilisateur_idx` (`ID_realiseurTache`),
  ADD KEY `fk_RealiseTache_Tache_idx` (`ID_Tache`);

--
-- Index pour la table `Tache`
--
ALTER TABLE `Tache`
  ADD PRIMARY KEY (`ID_Tache`),
  ADD KEY `fk_Tache_ListeTache_idx` (`ID_ListeTache`),
  ADD KEY `fk_Tache_Utilisateur` (`ID_createurTache`);

--
-- Index pour la table `Utilisateur`
--
ALTER TABLE `Utilisateur`
  ADD PRIMARY KEY (`pseudo_Utilisateur`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `AssigneTache`
--
ALTER TABLE `AssigneTache`
  MODIFY `ID_AssigneTache` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `Contacts`
--
ALTER TABLE `Contacts`
  MODIFY `ID_Contact` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT pour la table `Droits`
--
ALTER TABLE `Droits`
  MODIFY `ID_Droits` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `email_disposable`
--
ALTER TABLE `email_disposable`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=310;

--
-- AUTO_INCREMENT pour la table `Groupe`
--
ALTER TABLE `Groupe`
  MODIFY `ID_Groupe` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `ListeTache`
--
ALTER TABLE `ListeTache`
  MODIFY `ID_ListeTache` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT pour la table `ListeTacheGroupe`
--
ALTER TABLE `ListeTacheGroupe`
  MODIFY `ID_ListeTacheGroupe` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `Membre`
--
ALTER TABLE `Membre`
  MODIFY `ID_Membre` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT pour la table `PartageListe`
--
ALTER TABLE `PartageListe`
  MODIFY `ID_PartageListe` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `ProprietaireListe`
--
ALTER TABLE `ProprietaireListe`
  MODIFY `ID_ProprietaireListe` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT pour la table `RealiseTache`
--
ALTER TABLE `RealiseTache`
  MODIFY `ID_RealiseTache` int(11) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `AssigneTache`
--
ALTER TABLE `AssigneTache`
  ADD CONSTRAINT `fk_AssigneTache_Membre` FOREIGN KEY (`ID_tacheur`) REFERENCES `Membre` (`pseudoUtilisateur_Membre`),
  ADD CONSTRAINT `fk_AssigneTache_Membre2` FOREIGN KEY (`ID_MembreAssigneur`) REFERENCES `Membre` (`pseudoUtilisateur_Membre`),
  ADD CONSTRAINT `fk_AssigneTache_Tache` FOREIGN KEY (`ID_Tache`) REFERENCES `Tache` (`ID_Tache`);

--
-- Contraintes pour la table `Contacts`
--
ALTER TABLE `Contacts`
  ADD CONSTRAINT `fk_Contacts_Utlisateur1` FOREIGN KEY (`ID_Proprietaire`) REFERENCES `Utilisateur` (`pseudo_Utilisateur`),
  ADD CONSTRAINT `fk_Contacts_Utlisateur2` FOREIGN KEY (`ID_Utilisateur`) REFERENCES `Utilisateur` (`pseudo_Utilisateur`);

--
-- Contraintes pour la table `Groupe`
--
ALTER TABLE `Groupe`
  ADD CONSTRAINT `fk_Groupe_Utilisateur` FOREIGN KEY (`ID_createurGroupe`) REFERENCES `Utilisateur` (`pseudo_Utilisateur`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `ListeTacheGroupe`
--
ALTER TABLE `ListeTacheGroupe`
  ADD CONSTRAINT `fk_ListeTacheGroupe_Groupe` FOREIGN KEY (`ID_Groupe`) REFERENCES `Groupe` (`ID_Groupe`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_ListeTacheGroupe_ListeTache` FOREIGN KEY (`ID_ListeTache`) REFERENCES `ListeTache` (`ID_ListeTache`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_ListeTacheGroupe_Utilisateur` FOREIGN KEY (`ID_CreateurMembre`) REFERENCES `Utilisateur` (`pseudo_Utilisateur`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `Membre`
--
ALTER TABLE `Membre`
  ADD CONSTRAINT `fk_Membre_Groupe` FOREIGN KEY (`ID_Groupe`) REFERENCES `Groupe` (`ID_Groupe`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_Membre_Utilisateur` FOREIGN KEY (`pseudoUtilisateur_Membre`) REFERENCES `Utilisateur` (`pseudo_Utilisateur`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `PartageListe`
--
ALTER TABLE `PartageListe`
  ADD CONSTRAINT `fk_PartageListe_ListeTache` FOREIGN KEY (`ID_ListeTache`) REFERENCES `ListeTache` (`ID_ListeTache`),
  ADD CONSTRAINT `fk_PartageListe_Utilisateur_Invite` FOREIGN KEY (`ID_UtilisateurInvite`) REFERENCES `Utilisateur` (`pseudo_Utilisateur`),
  ADD CONSTRAINT `fk_PartageListe_Utilisateur_Proprio` FOREIGN KEY (`ID_ProprietaireListe`) REFERENCES `ProprietaireListe` (`pseudo_Utilisateur_Proprietaire`);

--
-- Contraintes pour la table `ProprietaireListe`
--
ALTER TABLE `ProprietaireListe`
  ADD CONSTRAINT `fk_ProprietaireListe_ListeTache` FOREIGN KEY (`ID_ListeTache`) REFERENCES `ListeTache` (`ID_ListeTache`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_ProprietaireListe_Utilisateur` FOREIGN KEY (`pseudo_Utilisateur_Proprietaire`) REFERENCES `Utilisateur` (`pseudo_Utilisateur`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `Tache`
--
ALTER TABLE `Tache`
  ADD CONSTRAINT `fk_Tache_ListeTache` FOREIGN KEY (`ID_ListeTache`) REFERENCES `ListeTache` (`ID_ListeTache`),
  ADD CONSTRAINT `fk_Tache_Utilisateur` FOREIGN KEY (`ID_createurTache`) REFERENCES `Utilisateur` (`pseudo_Utilisateur`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
