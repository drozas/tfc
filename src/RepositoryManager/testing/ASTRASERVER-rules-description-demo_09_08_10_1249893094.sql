-- phpMyAdmin SQL Dump
-- version 3.1.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Aug 10, 2009 at 10:31 AM
-- Server version: 5.0.67
-- PHP Version: 5.2.6-2ubuntu4.2

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `ASTRASERVER`
--

-- --------------------------------------------------------

--
-- Table structure for table `AppAccess`
--

CREATE TABLE IF NOT EXISTS `AppAccess` (
  `appid` int(11) NOT NULL,
  `uid` varchar(255) NOT NULL,
  PRIMARY KEY  (`appid`,`uid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `AppAccess`
--


-- --------------------------------------------------------

--
-- Table structure for table `AppData`
--

CREATE TABLE IF NOT EXISTS `AppData` (
  `appid` int(11) NOT NULL,
  `tag` varchar(255) NOT NULL,
  `data` text,
  PRIMARY KEY  (`appid`,`tag`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `AppData`
--


-- --------------------------------------------------------

--
-- Table structure for table `AwarenessApplication`
--

CREATE TABLE IF NOT EXISTS `AwarenessApplication` (
  `APPID` int(11) NOT NULL auto_increment,
  `OWNER` varchar(255) default NULL,
  `TARGET` varchar(255) default NULL,
  `NAME` varchar(255) default NULL,
  `AWARENESSTYPE` varchar(255) default NULL,
  `ACTIVE` tinyint(1) default NULL,
  `STATE` text,
  `DESCRIPTION` text,
  `APPTYPE` varchar(50) default NULL,
  PRIMARY KEY  (`APPID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `AwarenessApplication`
--


-- --------------------------------------------------------

--
-- Table structure for table `Community`
--

CREATE TABLE IF NOT EXISTS `Community` (
  `CID` varchar(255) NOT NULL,
  `OWNER` varchar(255) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  PRIMARY KEY  (`CID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Community`
--

INSERT INTO `Community` (`CID`, `OWNER`, `DESCRIPTION`) VALUES
('com_official:official', 'com_official', 'official'),
('com_colleagues_at_NTNU:colleagues_at_NTNU', 'com_colleagues_at_NTNU', 'colleagues_at_NTNU'),
('com_friends:friends', 'com_friends', 'friends');

-- --------------------------------------------------------

--
-- Table structure for table `CommunityApps`
--

CREATE TABLE IF NOT EXISTS `CommunityApps` (
  `appid` int(11) NOT NULL,
  `cid` varchar(255) NOT NULL,
  `description` varchar(255) default NULL,
  PRIMARY KEY  (`appid`,`cid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `CommunityApps`
--


-- --------------------------------------------------------

--
-- Table structure for table `CommunityProperty`
--

CREATE TABLE IF NOT EXISTS `CommunityProperty` (
  `CID` varchar(255) NOT NULL default '',
  `TAG` varchar(255) NOT NULL default '',
  `DATA` varchar(255) default NULL,
  PRIMARY KEY  (`CID`,`TAG`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `CommunityProperty`
--


-- --------------------------------------------------------

--
-- Table structure for table `CommunityRequests`
--

CREATE TABLE IF NOT EXISTS `CommunityRequests` (
  `CID` varchar(255) NOT NULL,
  `UID` varchar(255) default NULL,
  `STATUS` int(11) default NULL,
  `STAMP` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`CID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `CommunityRequests`
--


-- --------------------------------------------------------

--
-- Table structure for table `CommunitySharedSpace`
--

CREATE TABLE IF NOT EXISTS `CommunitySharedSpace` (
  `CID` varchar(255) NOT NULL default '',
  `TAG` varchar(255) NOT NULL default '',
  `DATA` text,
  PRIMARY KEY  (`CID`,`TAG`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `CommunitySharedSpace`
--

INSERT INTO `CommunitySharedSpace` (`CID`, `TAG`, `DATA`) VALUES
('tag:com_official,official', 'avatar_original', 'files/pics/unknown-48.jpg'),
('tag:com_official,official', 'avatar_48', 'files/pics/unknown-48.jpg'),
('tag:com_official,official', 'description', ''),
('tag:com_official,official', 'keywords', ''),
('tag:com_official,official', 'cdate', '2009-08-05'),
('tag:com_colleagues_at_NTNU,colleagues_at_NTNU', 'avatar_original', 'files/pics/unknown-48.jpg'),
('tag:com_colleagues_at_NTNU,colleagues_at_NTNU', 'avatar_48', 'files/pics/unknown-48.jpg'),
('tag:com_colleagues_at_NTNU,colleagues_at_NTNU', 'description', ''),
('tag:com_colleagues_at_NTNU,colleagues_at_NTNU', 'keywords', ''),
('tag:com_colleagues_at_NTNU,colleagues_at_NTNU', 'cdate', '2009-08-10'),
('tag:com_friends,friends', 'avatar_original', 'files/pics/unknown-48.jpg'),
('tag:com_friends,friends', 'avatar_48', 'files/pics/unknown-48.jpg'),
('tag:com_friends,friends', 'description', ''),
('tag:com_friends,friends', 'keywords', ''),
('tag:com_friends,friends', 'cdate', '2009-08-10');

-- --------------------------------------------------------

--
-- Table structure for table `Persistency`
--

CREATE TABLE IF NOT EXISTS `Persistency` (
  `UID` varchar(255) NOT NULL default '',
  `DATAKEY` varchar(255) NOT NULL default '',
  `DATA` mediumtext,
  PRIMARY KEY  (`UID`,`DATAKEY`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Persistency`
--


-- --------------------------------------------------------

--
-- Table structure for table `SharedApplication`
--

CREATE TABLE IF NOT EXISTS `SharedApplication` (
  `APPLICATION_ID` varchar(255) NOT NULL,
  `UID` varchar(255) NOT NULL,
  `APPLICATION_TYPE` varchar(255) NOT NULL,
  `SHARING_DATE` varchar(255) NOT NULL,
  `DESCRIPTION` text NOT NULL,
  PRIMARY KEY  (`APPLICATION_ID`,`UID`),
  KEY `UID` (`UID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `SharedApplication`
--

INSERT INTO `SharedApplication` (`APPLICATION_ID`, `UID`, `APPLICATION_TYPE`, `SHARING_DATE`, `DESCRIPTION`) VALUES
('admin@astra:beer', 'admin@astra', 'nimbus', '2009-08-05 16:57:00.928', 'going out to have some beers'),
('admin@astra:cinema', 'admin@astra', 'nimbus', '2009-08-05 17:00:07.199', 'watch a movie in the cinema'),
('admin@astra:coffee', 'admin@astra', 'nimbus', '2009-08-05 17:00:13.256', 'going to have a coffee'),
('admin@astra:concert', 'admin@astra', 'nimbus', '2009-08-05 17:00:19.623', 'go to a live music concert'),
('admin@astra:dance', 'admin@astra', 'nimbus', '2009-08-05 17:00:28.863', 'going out to dance in some disco'),
('admin@astra:girlfriend_home', 'admin@astra', 'nimbus', '2009-08-05 17:16:48.376', 'i am at my girlfriend home'),
('admin@astra:go_jogging', 'admin@astra', 'nimbus', '2009-08-05 17:01:02.342', 'go jogging to the forest'),
('admin@astra:go_sightseeing', 'admin@astra', 'nimbus', '2009-08-05 17:01:09.166', 'go sightseeing in our city'),
('admin@astra:go_to_the_gym', 'admin@astra', 'nimbus', '2009-08-05 17:01:15.825', 'go to the gym to train'),
('admin@astra:happy', 'admin@astra', 'nimbus', '2009-08-05 17:01:23.751', 'i am really happy'),
('admin@astra:hating_you', 'admin@astra', 'nimbus', '2009-08-05 17:01:32.686', 'I think of you, and I am really angry'),
('admin@astra:home', 'admin@astra', 'nimbus', '2009-08-05 17:01:44.15', 'i am at home'),
('admin@astra:missing_you', 'admin@astra', 'nimbus', '2009-08-05 17:01:55.238', 'I am missing you so much'),
('admin@astra:museum', 'admin@astra', 'nimbus', '2009-08-05 17:02:04.837', 'go to a museum '),
('admin@astra:my_parents', 'admin@astra', 'nimbus', '2009-08-05 17:02:12.779', 'i am at my parents home'),
('admin@astra:office', 'admin@astra', 'nimbus', '2009-08-05 17:02:21.885', 'i am at work, in my office'),
('admin@astra:photography_exposition', 'admin@astra', 'nimbus', '2009-08-05 17:02:30.43', 'going to that new photography exposition'),
('admin@astra:play_basketball_match', 'admin@astra', 'nimbus', '2009-08-05 17:02:37.228', 'play an amateur basketball match'),
('admin@astra:play_football', 'admin@astra', 'nimbus', '2009-08-05 17:02:46.533', 'play a football match'),
('admin@astra:play_handball', 'admin@astra', 'nimbus', '2009-08-05 17:02:53.301', 'play a handball match'),
('admin@astra:romantic_dinner', 'admin@astra', 'nimbus', '2009-08-05 17:03:01.78', 'going to an Italian restaurant to have a romantic dinner'),
('admin@astra:sad', 'admin@astra', 'nimbus', '2009-08-05 17:03:10.205', 'i feel sad'),
('admin@astra:tapas', 'admin@astra', 'nimbus', '2009-08-05 17:03:16.35', 'go to a Spanish restaurant to have some tapas'),
('admin@astra:thinking_of_you', 'admin@astra', 'nimbus', '2009-08-05 17:03:22.652', 'i miss you and i am thinking of you'),
('admin@astra:village', 'admin@astra', 'nimbus', '2009-08-05 17:03:28.325', 'i am going to be in my mother village'),
('drozas@astra:3Conditions', 'drozas@astra', 'nimbus', '2009-08-10 10:24:49.071', 'application to test the rules description'),
('drozas@astra:ApplicationCondition', 'drozas@astra', 'nimbus', '2009-08-10 10:24:57.584', 'Just to show description for InvokeMessage type');

-- --------------------------------------------------------

--
-- Table structure for table `SharedIn`
--

CREATE TABLE IF NOT EXISTS `SharedIn` (
  `UID` varchar(255) NOT NULL,
  `APPLICATION_ID` varchar(255) NOT NULL,
  `CID` varchar(255) NOT NULL,
  PRIMARY KEY  (`UID`,`APPLICATION_ID`,`CID`),
  KEY `APPLICATION_ID` (`APPLICATION_ID`,`UID`),
  KEY `CID` (`CID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `SharedIn`
--

INSERT INTO `SharedIn` (`UID`, `APPLICATION_ID`, `CID`) VALUES
('admin@astra', 'admin@astra:beer', 'com_official:official'),
('admin@astra', 'admin@astra:cinema', 'com_official:official'),
('admin@astra', 'admin@astra:coffee', 'com_official:official'),
('admin@astra', 'admin@astra:concert', 'com_official:official'),
('admin@astra', 'admin@astra:dance', 'com_official:official'),
('admin@astra', 'admin@astra:girlfriend_home', 'com_official:official'),
('admin@astra', 'admin@astra:go_jogging', 'com_official:official'),
('admin@astra', 'admin@astra:go_sightseeing', 'com_official:official'),
('admin@astra', 'admin@astra:go_to_the_gym', 'com_official:official'),
('admin@astra', 'admin@astra:happy', 'com_official:official'),
('admin@astra', 'admin@astra:hating_you', 'com_official:official'),
('admin@astra', 'admin@astra:home', 'com_official:official'),
('admin@astra', 'admin@astra:missing_you', 'com_official:official'),
('admin@astra', 'admin@astra:museum', 'com_official:official'),
('admin@astra', 'admin@astra:my_parents', 'com_official:official'),
('admin@astra', 'admin@astra:office', 'com_official:official'),
('admin@astra', 'admin@astra:photography_exposition', 'com_official:official'),
('admin@astra', 'admin@astra:play_basketball_match', 'com_official:official'),
('admin@astra', 'admin@astra:play_football', 'com_official:official'),
('admin@astra', 'admin@astra:play_handball', 'com_official:official'),
('admin@astra', 'admin@astra:romantic_dinner', 'com_official:official'),
('admin@astra', 'admin@astra:sad', 'com_official:official'),
('admin@astra', 'admin@astra:tapas', 'com_official:official'),
('admin@astra', 'admin@astra:thinking_of_you', 'com_official:official'),
('admin@astra', 'admin@astra:village', 'com_official:official'),
('drozas@astra', 'drozas@astra:3Conditions', 'com_colleagues_at_NTNU:colleagues_at_NTNU'),
('drozas@astra', 'drozas@astra:3Conditions', 'com_friends:friends'),
('drozas@astra', 'drozas@astra:ApplicationCondition', 'com_colleagues_at_NTNU:colleagues_at_NTNU'),
('drozas@astra', 'drozas@astra:ApplicationCondition', 'com_friends:friends');

-- --------------------------------------------------------

--
-- Table structure for table `SharedRule`
--

CREATE TABLE IF NOT EXISTS `SharedRule` (
  `RULE_ID` varchar(255) NOT NULL,
  `UID` varchar(255) NOT NULL,
  `APPLICATION_ID` varchar(255) NOT NULL,
  `XML_DATA` text NOT NULL,
  PRIMARY KEY  (`RULE_ID`,`UID`,`APPLICATION_ID`),
  KEY `APPLICATION_ID` (`APPLICATION_ID`,`UID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `SharedRule`
--

INSERT INTO `SharedRule` (`RULE_ID`, `UID`, `APPLICATION_ID`, `XML_DATA`) VALUES
('admin@astra:beer_0', 'admin@astra', 'admin@astra:beer', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:beer</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:beer_0</RULE_NAME></RULE></RULES>\n'),
('admin@astra:beer_0-reverse', 'admin@astra', 'admin@astra:beer', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:beer</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:beer_0-reverse</RULE_NAME></RULE></RULES>\n'),
('admin@astra:cinema_0', 'admin@astra', 'admin@astra:cinema', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:cinema</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:cinema_0</RULE_NAME></RULE></RULES>\n'),
('admin@astra:cinema_0-reverse', 'admin@astra', 'admin@astra:cinema', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:cinema</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:cinema_0-reverse</RULE_NAME></RULE></RULES>\n'),
('admin@astra:coffee_0', 'admin@astra', 'admin@astra:coffee', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:coffee</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:coffee_0</RULE_NAME></RULE></RULES>\n'),
('admin@astra:coffee_0-reverse', 'admin@astra', 'admin@astra:coffee', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:coffee</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:coffee_0-reverse</RULE_NAME></RULE></RULES>\n'),
('admin@astra:concert_0', 'admin@astra', 'admin@astra:concert', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:concert</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:concert_0</RULE_NAME></RULE></RULES>\n'),
('admin@astra:concert_0-reverse', 'admin@astra', 'admin@astra:concert', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:concert</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:concert_0-reverse</RULE_NAME></RULE></RULES>\n'),
('admin@astra:dance_0', 'admin@astra', 'admin@astra:dance', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:dance</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:dance_0</RULE_NAME></RULE></RULES>\n'),
('admin@astra:dance_0-reverse', 'admin@astra', 'admin@astra:dance', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:dance</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:dance_0-reverse</RULE_NAME></RULE></RULES>\n'),
('admin@astra:go_jogging_0', 'admin@astra', 'admin@astra:go_jogging', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:go_jogging</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:go_jogging_0</RULE_NAME></RULE></RULES>\n'),
('admin@astra:go_jogging_0-reverse', 'admin@astra', 'admin@astra:go_jogging', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:go_jogging</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:go_jogging_0-reverse</RULE_NAME></RULE></RULES>\n'),
('admin@astra:go_sightseeing_0', 'admin@astra', 'admin@astra:go_sightseeing', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:go_sightseeing</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:go_sightseeing_0</RULE_NAME></RULE></RULES>\n'),
('admin@astra:go_sightseeing_0-reverse', 'admin@astra', 'admin@astra:go_sightseeing', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:go_sightseeing</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:go_sightseeing_0-reverse</RULE_NAME></RULE></RULES>\n'),
('admin@astra:go_to_the_gym_0', 'admin@astra', 'admin@astra:go_to_the_gym', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:go_to_the_gym</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:go_to_the_gym_0</RULE_NAME></RULE></RULES>\n'),
('admin@astra:go_to_the_gym_0-reverse', 'admin@astra', 'admin@astra:go_to_the_gym', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:go_to_the_gym</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:go_to_the_gym_0-reverse</RULE_NAME></RULE></RULES>\n'),
('admin@astra:happy_0', 'admin@astra', 'admin@astra:happy', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:happy</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:happy_0</RULE_NAME></RULE></RULES>\n'),
('admin@astra:happy_0-reverse', 'admin@astra', 'admin@astra:happy', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:happy</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:happy_0-reverse</RULE_NAME></RULE></RULES>\n'),
('admin@astra:hating_you_0', 'admin@astra', 'admin@astra:hating_you', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:hating_you</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:hating_you_0</RULE_NAME></RULE></RULES>\n'),
('admin@astra:hating_you_0-reverse', 'admin@astra', 'admin@astra:hating_you', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:hating_you</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:hating_you_0-reverse</RULE_NAME></RULE></RULES>\n'),
('admin@astra:home_0', 'admin@astra', 'admin@astra:home', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:home</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:home_0</RULE_NAME></RULE></RULES>\n'),
('admin@astra:home_0-reverse', 'admin@astra', 'admin@astra:home', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:home</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:home_0-reverse</RULE_NAME></RULE></RULES>\n'),
('admin@astra:missing_you_0', 'admin@astra', 'admin@astra:missing_you', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:missing_you</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:missing_you_0</RULE_NAME></RULE></RULES>\n'),
('admin@astra:missing_you_0-reverse', 'admin@astra', 'admin@astra:missing_you', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:missing_you</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:missing_you_0-reverse</RULE_NAME></RULE></RULES>\n'),
('admin@astra:museum_0', 'admin@astra', 'admin@astra:museum', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:museum</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:museum_0</RULE_NAME></RULE></RULES>\n'),
('admin@astra:museum_0-reverse', 'admin@astra', 'admin@astra:museum', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:museum</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:museum_0-reverse</RULE_NAME></RULE></RULES>\n'),
('admin@astra:my_parents_0', 'admin@astra', 'admin@astra:my_parents', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:my_parents</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:my_parents_0</RULE_NAME></RULE></RULES>\n'),
('admin@astra:my_parents_0-reverse', 'admin@astra', 'admin@astra:my_parents', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:my_parents</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:my_parents_0-reverse</RULE_NAME></RULE></RULES>\n'),
('admin@astra:office_0', 'admin@astra', 'admin@astra:office', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:office</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:office_0</RULE_NAME></RULE></RULES>\n'),
('admin@astra:office_0-reverse', 'admin@astra', 'admin@astra:office', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:office</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:office_0-reverse</RULE_NAME></RULE></RULES>\n'),
('admin@astra:photography_exposition_0', 'admin@astra', 'admin@astra:photography_exposition', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:photography_exposition</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:photography_exposition_0</RULE_NAME></RULE></RULES>\n'),
('admin@astra:photography_exposition_0-reverse', 'admin@astra', 'admin@astra:photography_exposition', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:photography_exposition</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:photography_exposition_0-reverse</RULE_NAME></RULE></RULES>\n'),
('admin@astra:play_basketball_match_0', 'admin@astra', 'admin@astra:play_basketball_match', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:play_basketball_match</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:play_basketball_match_0</RULE_NAME></RULE></RULES>\n'),
('admin@astra:play_basketball_match_0-reverse', 'admin@astra', 'admin@astra:play_basketball_match', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:play_basketball_match</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:play_basketball_match_0-reverse</RULE_NAME></RULE></RULES>\n'),
('admin@astra:play_football_0', 'admin@astra', 'admin@astra:play_football', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:play_football</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:play_football_0</RULE_NAME></RULE></RULES>\n'),
('admin@astra:play_football_0-reverse', 'admin@astra', 'admin@astra:play_football', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:play_football</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:play_football_0-reverse</RULE_NAME></RULE></RULES>\n'),
('admin@astra:play_handball_0', 'admin@astra', 'admin@astra:play_handball', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:play_handball</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:play_handball_0</RULE_NAME></RULE></RULES>\n'),
('admin@astra:play_handball_0-reverse', 'admin@astra', 'admin@astra:play_handball', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:play_handball</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:play_handball_0-reverse</RULE_NAME></RULE></RULES>\n'),
('admin@astra:romantic_dinner_0', 'admin@astra', 'admin@astra:romantic_dinner', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:romantic_dinner</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:romantic_dinner_0</RULE_NAME></RULE></RULES>\n'),
('admin@astra:romantic_dinner_0-reverse', 'admin@astra', 'admin@astra:romantic_dinner', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:romantic_dinner</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:romantic_dinner_0-reverse</RULE_NAME></RULE></RULES>\n'),
('admin@astra:sad_0', 'admin@astra', 'admin@astra:sad', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:sad</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:sad_0</RULE_NAME></RULE></RULES>\n'),
('admin@astra:sad_0-reverse', 'admin@astra', 'admin@astra:sad', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:sad</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:sad_0-reverse</RULE_NAME></RULE></RULES>\n'),
('admin@astra:tapas_0', 'admin@astra', 'admin@astra:tapas', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:tapas</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:tapas_0</RULE_NAME></RULE></RULES>\n'),
('admin@astra:tapas_0-reverse', 'admin@astra', 'admin@astra:tapas', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:tapas</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:tapas_0-reverse</RULE_NAME></RULE></RULES>\n'),
('admin@astra:thinking_of_you_0', 'admin@astra', 'admin@astra:thinking_of_you', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:thinking_of_you</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:thinking_of_you_0</RULE_NAME></RULE></RULES>\n'),
('admin@astra:thinking_of_you_0-reverse', 'admin@astra', 'admin@astra:thinking_of_you', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:thinking_of_you</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:thinking_of_you_0-reverse</RULE_NAME></RULE></RULES>\n'),
('admin@astra:village_0', 'admin@astra', 'admin@astra:village', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:village</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:village_0</RULE_NAME></RULE></RULES>\n'),
('admin@astra:village_0-reverse', 'admin@astra', 'admin@astra:village', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:village</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:village_0-reverse</RULE_NAME></RULE></RULES>\n'),
('admin@astra:girlfriend_home_0', 'admin@astra', 'admin@astra:girlfriend_home', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:girlfriend_home</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:girlfriend_home_0</RULE_NAME></RULE></RULES>\n'),
('admin@astra:girlfriend_home_0-reverse', 'admin@astra', 'admin@astra:girlfriend_home', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:girlfriend_home</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:girlfriend_home_0-reverse</RULE_NAME></RULE></RULES>\n'),
('drozas@astra:3Conditions_0', 'drozas@astra', 'drozas@astra:3Conditions', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>AND</TYPE><CONDITION_PART><CONDITION><TYPE>Awareness</TYPE><NAME>watchingTV</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><CONDITION><TYPE>Service</TYPE><NAME>Sofa@occupied</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION></CONDITION_PART></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>drozas@astra:3Conditions</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>drozas@astra:3Conditions_0</RULE_NAME></RULE></RULES>\n'),
('drozas@astra:3Conditions_0-reverse', 'drozas@astra', 'drozas@astra:3Conditions', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>OR</TYPE><CONDITION_PART><CONDITION><TYPE>Awareness</TYPE><NAME>watchingTV</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><CONDITION><TYPE>Service</TYPE><NAME>Sofa@occupied</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION></CONDITION_PART></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>drozas@astra:3Conditions</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>drozas@astra:3Conditions_0-reverse</RULE_NAME></RULE></RULES>\n'),
('drozas@astra:ApplicationCondition_0', 'drozas@astra', 'drozas@astra:ApplicationCondition', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>InvokeMessage</TYPE><NAME>drozas@astra:3Conditions</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>drozas@astra:ApplicationCondition</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>drozas@astra:ApplicationCondition_0</RULE_NAME></RULE></RULES>\n'),
('drozas@astra:ApplicationCondition_0-reverse', 'drozas@astra', 'drozas@astra:ApplicationCondition', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>InvokeMessage</TYPE><NAME>drozas@astra:3Conditions</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>drozas@astra:ApplicationCondition</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>drozas@astra:ApplicationCondition_0-reverse</RULE_NAME></RULE></RULES>\n');

-- --------------------------------------------------------

--
-- Table structure for table `Tag`
--

CREATE TABLE IF NOT EXISTS `Tag` (
  `ID` int(10) unsigned NOT NULL auto_increment,
  `UID` varchar(255) NOT NULL,
  `APPLICATION_ID` varchar(255) NOT NULL,
  `CID` varchar(255) NOT NULL default 'public',
  `NAME` varchar(255) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=105 ;

--
-- Dumping data for table `Tag`
--

INSERT INTO `Tag` (`ID`, `UID`, `APPLICATION_ID`, `CID`, `NAME`) VALUES
(1, 'admin@astra', 'admin@astra:beer', 'public', 'beer'),
(2, 'admin@astra', 'admin@astra:beer', 'public', 'fun'),
(3, 'admin@astra', 'admin@astra:beer', 'public', 'social'),
(4, 'admin@astra', 'admin@astra:beer', 'public', 'friends'),
(5, 'admin@astra', 'admin@astra:cinema', 'public', 'social'),
(6, 'admin@astra', 'admin@astra:cinema', 'public', 'movies'),
(7, 'admin@astra', 'admin@astra:cinema', 'public', 'film'),
(8, 'admin@astra', 'admin@astra:cinema', 'public', 'fun'),
(9, 'admin@astra', 'admin@astra:coffee', 'public', 'break'),
(10, 'admin@astra', 'admin@astra:coffee', 'public', 'social'),
(11, 'admin@astra', 'admin@astra:coffee', 'public', 'relax'),
(12, 'admin@astra', 'admin@astra:coffee', 'public', 'talk'),
(13, 'admin@astra', 'admin@astra:concert', 'public', 'music'),
(14, 'admin@astra', 'admin@astra:concert', 'public', 'fun'),
(15, 'admin@astra', 'admin@astra:concert', 'public', 'rock'),
(16, 'admin@astra', 'admin@astra:concert', 'public', 'social'),
(17, 'admin@astra', 'admin@astra:dance', 'public', 'party'),
(18, 'admin@astra', 'admin@astra:dance', 'public', 'friends'),
(19, 'admin@astra', 'admin@astra:dance', 'public', 'beer'),
(20, 'admin@astra', 'admin@astra:dance', 'public', 'fun'),
(104, 'admin@astra', 'admin@astra:girlfriend_home', 'public', 'busy'),
(103, 'admin@astra', 'admin@astra:girlfriend_home', 'public', 'place'),
(102, 'admin@astra', 'admin@astra:girlfriend_home', 'public', 'home'),
(101, 'admin@astra', 'admin@astra:girlfriend_home', 'public', 'gf'),
(25, 'admin@astra', 'admin@astra:go_jogging', 'public', 'sport'),
(26, 'admin@astra', 'admin@astra:go_jogging', 'public', 'run'),
(27, 'admin@astra', 'admin@astra:go_jogging', 'public', 'excercise'),
(28, 'admin@astra', 'admin@astra:go_jogging', 'public', 'fit'),
(29, 'admin@astra', 'admin@astra:go_sightseeing', 'public', 'tourism'),
(30, 'admin@astra', 'admin@astra:go_sightseeing', 'public', 'cultural'),
(31, 'admin@astra', 'admin@astra:go_sightseeing', 'public', 'guide'),
(32, 'admin@astra', 'admin@astra:go_sightseeing', 'public', 'museum'),
(33, 'admin@astra', 'admin@astra:go_to_the_gym', 'public', 'sport'),
(34, 'admin@astra', 'admin@astra:go_to_the_gym', 'public', 'fit'),
(35, 'admin@astra', 'admin@astra:go_to_the_gym', 'public', 'health'),
(36, 'admin@astra', 'admin@astra:go_to_the_gym', 'public', 'muscle'),
(37, 'admin@astra', 'admin@astra:happy', 'public', 'feeling'),
(38, 'admin@astra', 'admin@astra:happy', 'public', 'nice'),
(39, 'admin@astra', 'admin@astra:happy', 'public', 'great'),
(40, 'admin@astra', 'admin@astra:happy', 'public', 'love'),
(41, 'admin@astra', 'admin@astra:hating_you', 'public', 'angry'),
(42, 'admin@astra', 'admin@astra:hating_you', 'public', 'feelings'),
(43, 'admin@astra', 'admin@astra:hating_you', 'public', 'hate'),
(44, 'admin@astra', 'admin@astra:hating_you', 'public', 'furious'),
(45, 'admin@astra', 'admin@astra:home', 'public', 'place'),
(46, 'admin@astra', 'admin@astra:home', 'public', 'home'),
(47, 'admin@astra', 'admin@astra:home', 'public', 'location'),
(48, 'admin@astra', 'admin@astra:home', 'public', 'safe'),
(49, 'admin@astra', 'admin@astra:missing_you', 'public', 'love'),
(50, 'admin@astra', 'admin@astra:missing_you', 'public', 'nostalgy'),
(51, 'admin@astra', 'admin@astra:missing_you', 'public', 'feeling'),
(52, 'admin@astra', 'admin@astra:missing_you', 'public', 'lovely'),
(53, 'admin@astra', 'admin@astra:museum', 'public', 'paintings'),
(54, 'admin@astra', 'admin@astra:museum', 'public', 'history'),
(55, 'admin@astra', 'admin@astra:museum', 'public', 'cultural'),
(56, 'admin@astra', 'admin@astra:museum', 'public', 'tourism'),
(57, 'admin@astra', 'admin@astra:my_parents', 'public', 'family'),
(58, 'admin@astra', 'admin@astra:my_parents', 'public', 'mother'),
(59, 'admin@astra', 'admin@astra:my_parents', 'public', 'place'),
(60, 'admin@astra', 'admin@astra:my_parents', 'public', 'father'),
(61, 'admin@astra', 'admin@astra:office', 'public', 'work'),
(62, 'admin@astra', 'admin@astra:office', 'public', 'job'),
(63, 'admin@astra', 'admin@astra:office', 'public', 'place'),
(64, 'admin@astra', 'admin@astra:office', 'public', 'office'),
(65, 'admin@astra', 'admin@astra:photography_exposition', 'public', 'pics'),
(66, 'admin@astra', 'admin@astra:photography_exposition', 'public', 'cultural'),
(67, 'admin@astra', 'admin@astra:photography_exposition', 'public', 'sightseeing'),
(68, 'admin@astra', 'admin@astra:photography_exposition', 'public', 'camera'),
(69, 'admin@astra', 'admin@astra:play_basketball_match', 'public', 'sport'),
(70, 'admin@astra', 'admin@astra:play_basketball_match', 'public', 'health'),
(71, 'admin@astra', 'admin@astra:play_basketball_match', 'public', 'ball'),
(72, 'admin@astra', 'admin@astra:play_basketball_match', 'public', 'amateur'),
(73, 'admin@astra', 'admin@astra:play_football', 'public', 'ball'),
(74, 'admin@astra', 'admin@astra:play_football', 'public', 'sport'),
(75, 'admin@astra', 'admin@astra:play_football', 'public', 'goalkeeper'),
(76, 'admin@astra', 'admin@astra:play_football', 'public', 'offside'),
(77, 'admin@astra', 'admin@astra:play_handball', 'public', 'sport'),
(78, 'admin@astra', 'admin@astra:play_handball', 'public', 'ball'),
(79, 'admin@astra', 'admin@astra:play_handball', 'public', 'fun'),
(80, 'admin@astra', 'admin@astra:play_handball', 'public', 'amateur'),
(81, 'admin@astra', 'admin@astra:romantic_dinner', 'public', 'restaurant'),
(82, 'admin@astra', 'admin@astra:romantic_dinner', 'public', 'meal'),
(83, 'admin@astra', 'admin@astra:romantic_dinner', 'public', 'food'),
(84, 'admin@astra', 'admin@astra:romantic_dinner', 'public', 'social'),
(85, 'admin@astra', 'admin@astra:sad', 'public', 'lonely'),
(86, 'admin@astra', 'admin@astra:sad', 'public', 'feeling'),
(87, 'admin@astra', 'admin@astra:sad', 'public', 'nostalgy'),
(88, 'admin@astra', 'admin@astra:sad', 'public', 'terrible'),
(89, 'admin@astra', 'admin@astra:tapas', 'public', 'restaurant'),
(90, 'admin@astra', 'admin@astra:tapas', 'public', 'food'),
(91, 'admin@astra', 'admin@astra:tapas', 'public', 'social'),
(92, 'admin@astra', 'admin@astra:tapas', 'public', 'spain'),
(93, 'admin@astra', 'admin@astra:thinking_of_you', 'public', 'nostalgy'),
(94, 'admin@astra', 'admin@astra:thinking_of_you', 'public', 'love'),
(95, 'admin@astra', 'admin@astra:thinking_of_you', 'public', 'feeling'),
(96, 'admin@astra', 'admin@astra:thinking_of_you', 'public', 'hug'),
(97, 'admin@astra', 'admin@astra:village', 'public', 'parents'),
(98, 'admin@astra', 'admin@astra:village', 'public', 'mother'),
(99, 'admin@astra', 'admin@astra:village', 'public', 'place'),
(100, 'admin@astra', 'admin@astra:village', 'public', 'rustic');

-- --------------------------------------------------------

--
-- Table structure for table `User`
--

CREATE TABLE IF NOT EXISTS `User` (
  `UID` varchar(255) NOT NULL,
  `USERNAME` varchar(255) default NULL,
  `PASSWORD` varchar(255) default NULL,
  PRIMARY KEY  (`UID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `User`
--

INSERT INTO `User` (`UID`, `USERNAME`, `PASSWORD`) VALUES
('admin@astra', 'admin', 'admin'),
('drozas@astra', 'drozas', 'drozas');

-- --------------------------------------------------------

--
-- Table structure for table `UserCommunities`
--

CREATE TABLE IF NOT EXISTS `UserCommunities` (
  `UID` varchar(255) NOT NULL default '',
  `CID` varchar(255) NOT NULL default '',
  `ROLE` int(11) default NULL,
  PRIMARY KEY  (`UID`,`CID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `UserCommunities`
--

INSERT INTO `UserCommunities` (`UID`, `CID`, `ROLE`) VALUES
('admin@astra', 'com_official:official', 0),
('drozas@astra', 'com_colleagues_at_NTNU:colleagues_at_NTNU', 0),
('drozas@astra', 'com_friends:friends', 0),
('drozas@astra', 'com_official:official', 0);

-- --------------------------------------------------------

--
-- Table structure for table `UserProfileData`
--

CREATE TABLE IF NOT EXISTS `UserProfileData` (
  `UID` varchar(255) NOT NULL default '',
  `TAG` varchar(255) NOT NULL default '',
  `DATA` varchar(255) default NULL,
  PRIMARY KEY  (`UID`,`TAG`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `UserProfileData`
--

INSERT INTO `UserProfileData` (`UID`, `TAG`, `DATA`) VALUES
('admin@astra', 'localserver', '127.0.0.1:10000'),
('drozas@astra', 'localserver', '127.0.0.1:10000');
