-- phpMyAdmin SQL Dump
-- version 3.1.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Aug 05, 2009 at 05:23 PM
-- Server version: 5.0.67
-- PHP Version: 5.2.6-2ubuntu4.2

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `ASTRACLIENT`
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

INSERT INTO `AppData` (`appid`, `tag`, `data`) VALUES
(1, 'desc', 'play a football match'),
(1, 'owner', 'admin@astra'),
(1, 'cdate', '2009-08-05'),
(1, 'publishable', '1'),
(1, 'state', 'on'),
(1, 'RULE:admin@astra:play_football_0', NULL),
(1, 'RULE:admin@astra:play_football_0-reverse', NULL),
(2, 'desc', 'play a handball match'),
(2, 'owner', 'admin@astra'),
(2, 'cdate', '2009-08-05'),
(2, 'publishable', '1'),
(2, 'state', 'on'),
(2, 'RULE:admin@astra:play_handball_0', NULL),
(2, 'RULE:admin@astra:play_handball_0-reverse', NULL),
(3, 'desc', 'play an amateur basketball match'),
(3, 'owner', 'admin@astra'),
(3, 'cdate', '2009-08-05'),
(3, 'publishable', '1'),
(3, 'state', 'on'),
(3, 'RULE:admin@astra:play_basketball_match_0', NULL),
(3, 'RULE:admin@astra:play_basketball_match_0-reverse', NULL),
(4, 'desc', 'go jogging to the forest'),
(4, 'owner', 'admin@astra'),
(4, 'cdate', '2009-08-05'),
(4, 'publishable', '1'),
(4, 'state', 'on'),
(4, 'RULE:admin@astra:go_jogging_0', NULL),
(4, 'RULE:admin@astra:go_jogging_0-reverse', NULL),
(5, 'desc', 'go to the gym to train'),
(5, 'owner', 'admin@astra'),
(5, 'cdate', '2009-08-05'),
(5, 'publishable', '1'),
(5, 'state', 'on'),
(5, 'RULE:admin@astra:go_to_the_gym_0', NULL),
(5, 'RULE:admin@astra:go_to_the_gym_0-reverse', NULL),
(6, 'desc', 'going to have a coffee'),
(6, 'owner', 'admin@astra'),
(6, 'cdate', '2009-08-05'),
(6, 'publishable', '1'),
(6, 'state', 'on'),
(6, 'RULE:admin@astra:coffee_0', NULL),
(6, 'RULE:admin@astra:coffee_0-reverse', NULL),
(7, 'desc', 'go to a Spanish restaurant to have some tapas'),
(7, 'owner', 'admin@astra'),
(7, 'cdate', '2009-08-05'),
(7, 'publishable', '1'),
(7, 'state', 'on'),
(7, 'RULE:admin@astra:tapas_0', NULL),
(7, 'RULE:admin@astra:tapas_0-reverse', NULL),
(8, 'desc', 'going to an Italian restaurant to have a romantic dinner'),
(8, 'owner', 'admin@astra'),
(8, 'cdate', '2009-08-05'),
(8, 'publishable', '1'),
(8, 'state', 'on'),
(8, 'RULE:admin@astra:romantic_dinner_0', NULL),
(8, 'RULE:admin@astra:romantic_dinner_0-reverse', NULL),
(9, 'desc', 'going out to have some beers'),
(9, 'owner', 'admin@astra'),
(9, 'cdate', '2009-08-05'),
(9, 'publishable', '1'),
(9, 'state', 'on'),
(9, 'RULE:admin@astra:beer_0', NULL),
(9, 'RULE:admin@astra:beer_0-reverse', NULL),
(10, 'desc', 'going out to dance in some disco'),
(10, 'owner', 'admin@astra'),
(10, 'cdate', '2009-08-05'),
(10, 'publishable', '1'),
(10, 'state', 'on'),
(10, 'RULE:admin@astra:dance_0', NULL),
(10, 'RULE:admin@astra:dance_0-reverse', NULL),
(11, 'desc', 'i miss you and i am thinking of you'),
(11, 'owner', 'admin@astra'),
(11, 'cdate', '2009-08-05'),
(11, 'publishable', '1'),
(11, 'state', 'on'),
(11, 'RULE:admin@astra:thinking_of_you_0', NULL),
(11, 'RULE:admin@astra:thinking_of_you_0-reverse', NULL),
(12, 'desc', 'i feel sad'),
(12, 'owner', 'admin@astra'),
(12, 'cdate', '2009-08-05'),
(12, 'publishable', '1'),
(12, 'state', 'on'),
(12, 'RULE:admin@astra:sad_0', NULL),
(12, 'RULE:admin@astra:sad_0-reverse', NULL),
(13, 'desc', 'i am really happy'),
(13, 'owner', 'admin@astra'),
(13, 'cdate', '2009-08-05'),
(13, 'publishable', '1'),
(13, 'state', 'on'),
(13, 'RULE:admin@astra:happy_0', NULL),
(13, 'RULE:admin@astra:happy_0-reverse', NULL),
(14, 'desc', 'I think of you, and I am really angry'),
(14, 'owner', 'admin@astra'),
(14, 'cdate', '2009-08-05'),
(14, 'publishable', '1'),
(14, 'state', 'on'),
(14, 'RULE:admin@astra:hating_you_0', NULL),
(14, 'RULE:admin@astra:hating_you_0-reverse', NULL),
(15, 'desc', 'I am missing you so much'),
(15, 'owner', 'admin@astra'),
(15, 'cdate', '2009-08-05'),
(15, 'publishable', '1'),
(15, 'state', 'on'),
(15, 'RULE:admin@astra:missing_you_0', NULL),
(15, 'RULE:admin@astra:missing_you_0-reverse', NULL),
(16, 'desc', 'watch a movie in the cinema'),
(16, 'owner', 'admin@astra'),
(16, 'cdate', '2009-08-05'),
(16, 'publishable', '1'),
(16, 'state', 'on'),
(16, 'RULE:admin@astra:cinema_0', NULL),
(16, 'RULE:admin@astra:cinema_0-reverse', NULL),
(17, 'desc', 'go to a museum '),
(17, 'owner', 'admin@astra'),
(17, 'cdate', '2009-08-05'),
(17, 'publishable', '1'),
(17, 'state', 'on'),
(17, 'RULE:admin@astra:museum_0', NULL),
(17, 'RULE:admin@astra:museum_0-reverse', NULL),
(18, 'desc', 'going to that new photography exposition'),
(18, 'owner', 'admin@astra'),
(18, 'cdate', '2009-08-05'),
(18, 'publishable', '1'),
(18, 'state', 'on'),
(18, 'RULE:admin@astra:photography_exposition_0', NULL),
(18, 'RULE:admin@astra:photography_exposition_0-reverse', NULL),
(19, 'desc', 'go to a live music concert'),
(19, 'owner', 'admin@astra'),
(19, 'cdate', '2009-08-05'),
(19, 'publishable', '1'),
(19, 'state', 'on'),
(19, 'RULE:admin@astra:concert_0', NULL),
(19, 'RULE:admin@astra:concert_0-reverse', NULL),
(20, 'desc', 'go sightseeing in our city'),
(20, 'owner', 'admin@astra'),
(20, 'cdate', '2009-08-05'),
(20, 'publishable', '1'),
(20, 'state', 'on'),
(20, 'RULE:admin@astra:go_sightseeing_0', NULL),
(20, 'RULE:admin@astra:go_sightseeing_0-reverse', NULL),
(21, 'desc', 'i am at work, in my office'),
(21, 'owner', 'admin@astra'),
(21, 'cdate', '2009-08-05'),
(21, 'publishable', '1'),
(21, 'state', 'on'),
(21, 'RULE:admin@astra:office_0', NULL),
(21, 'RULE:admin@astra:office_0-reverse', NULL),
(22, 'desc', 'i am at home'),
(22, 'owner', 'admin@astra'),
(22, 'cdate', '2009-08-05'),
(22, 'publishable', '1'),
(22, 'state', 'on'),
(22, 'RULE:admin@astra:home_0', NULL),
(22, 'RULE:admin@astra:home_0-reverse', NULL),
(26, 'cdate', '2009-08-05'),
(26, 'owner', 'admin@astra'),
(26, 'desc', 'i am at my girlfriend home'),
(24, 'desc', 'i am going to be in my mother village'),
(24, 'owner', 'admin@astra'),
(24, 'cdate', '2009-08-05'),
(24, 'publishable', '1'),
(24, 'state', 'on'),
(24, 'RULE:admin@astra:village_0', NULL),
(24, 'RULE:admin@astra:village_0-reverse', NULL),
(25, 'desc', 'i am at my parents home'),
(25, 'owner', 'admin@astra'),
(25, 'cdate', '2009-08-05'),
(25, 'publishable', '1'),
(25, 'state', 'on'),
(25, 'RULE:admin@astra:my_parents_0', NULL),
(25, 'RULE:admin@astra:my_parents_0-reverse', NULL),
(26, 'publishable', '1'),
(26, 'state', 'on'),
(26, 'RULE:admin@astra:girlfriend_home_0', NULL),
(26, 'RULE:admin@astra:girlfriend_home_0-reverse', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `AwarenessApplication`
--

CREATE TABLE IF NOT EXISTS `AwarenessApplication` (
  `APPID` int(11) NOT NULL auto_increment,
  `OWNER` varchar(255) default NULL,
  `TARGET` varchar(255) default NULL,
  `AWARENESSTYPE` varchar(255) default NULL,
  `ACTIVE` tinyint(1) default NULL,
  `STATE` text,
  `APPTYPE` varchar(50) default NULL,
  `BACKENDAPPID` int(11) default NULL,
  PRIMARY KEY  (`APPID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=27 ;

--
-- Dumping data for table `AwarenessApplication`
--

INSERT INTO `AwarenessApplication` (`APPID`, `OWNER`, `TARGET`, `AWARENESSTYPE`, `ACTIVE`, `STATE`, `APPTYPE`, `BACKENDAPPID`) VALUES
(1, 'admin@astra', 'admin@astra', 'play_football', NULL, NULL, 'eu.ist.astra.aam.impl.NimbusApplication', NULL),
(2, 'admin@astra', 'admin@astra', 'play_handball', NULL, NULL, 'eu.ist.astra.aam.impl.NimbusApplication', NULL),
(3, 'admin@astra', 'admin@astra', 'play_basketball_match', NULL, NULL, 'eu.ist.astra.aam.impl.NimbusApplication', NULL),
(4, 'admin@astra', 'admin@astra', 'go_jogging', NULL, NULL, 'eu.ist.astra.aam.impl.NimbusApplication', NULL),
(5, 'admin@astra', 'admin@astra', 'go_to_the_gym', NULL, NULL, 'eu.ist.astra.aam.impl.NimbusApplication', NULL),
(6, 'admin@astra', 'admin@astra', 'coffee', NULL, NULL, 'eu.ist.astra.aam.impl.NimbusApplication', NULL),
(7, 'admin@astra', 'admin@astra', 'tapas', NULL, NULL, 'eu.ist.astra.aam.impl.NimbusApplication', NULL),
(8, 'admin@astra', 'admin@astra', 'romantic_dinner', NULL, NULL, 'eu.ist.astra.aam.impl.NimbusApplication', NULL),
(9, 'admin@astra', 'admin@astra', 'beer', NULL, NULL, 'eu.ist.astra.aam.impl.NimbusApplication', NULL),
(10, 'admin@astra', 'admin@astra', 'dance', NULL, NULL, 'eu.ist.astra.aam.impl.NimbusApplication', NULL),
(11, 'admin@astra', 'admin@astra', 'thinking_of_you', NULL, NULL, 'eu.ist.astra.aam.impl.NimbusApplication', NULL),
(12, 'admin@astra', 'admin@astra', 'sad', NULL, NULL, 'eu.ist.astra.aam.impl.NimbusApplication', NULL),
(13, 'admin@astra', 'admin@astra', 'happy', NULL, NULL, 'eu.ist.astra.aam.impl.NimbusApplication', NULL),
(14, 'admin@astra', 'admin@astra', 'hating_you', NULL, NULL, 'eu.ist.astra.aam.impl.NimbusApplication', NULL),
(15, 'admin@astra', 'admin@astra', 'missing_you', NULL, NULL, 'eu.ist.astra.aam.impl.NimbusApplication', NULL),
(16, 'admin@astra', 'admin@astra', 'cinema', NULL, NULL, 'eu.ist.astra.aam.impl.NimbusApplication', NULL),
(17, 'admin@astra', 'admin@astra', 'museum', NULL, NULL, 'eu.ist.astra.aam.impl.NimbusApplication', NULL),
(18, 'admin@astra', 'admin@astra', 'photography_exposition', NULL, NULL, 'eu.ist.astra.aam.impl.NimbusApplication', NULL),
(19, 'admin@astra', 'admin@astra', 'concert', NULL, NULL, 'eu.ist.astra.aam.impl.NimbusApplication', NULL),
(20, 'admin@astra', 'admin@astra', 'go_sightseeing', NULL, NULL, 'eu.ist.astra.aam.impl.NimbusApplication', NULL),
(21, 'admin@astra', 'admin@astra', 'office', NULL, NULL, 'eu.ist.astra.aam.impl.NimbusApplication', NULL),
(22, 'admin@astra', 'admin@astra', 'home', NULL, NULL, 'eu.ist.astra.aam.impl.NimbusApplication', NULL),
(26, 'admin@astra', 'admin@astra', 'girlfriend_home', NULL, NULL, 'eu.ist.astra.aam.impl.NimbusApplication', NULL),
(24, 'admin@astra', 'admin@astra', 'village', NULL, NULL, 'eu.ist.astra.aam.impl.NimbusApplication', NULL),
(25, 'admin@astra', 'admin@astra', 'my_parents', NULL, NULL, 'eu.ist.astra.aam.impl.NimbusApplication', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `CommunityApps`
--

CREATE TABLE IF NOT EXISTS `CommunityApps` (
  `appid` int(11) NOT NULL,
  `cid` varchar(255) NOT NULL,
  PRIMARY KEY  (`appid`,`cid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `CommunityApps`
--


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

INSERT INTO `Persistency` (`UID`, `DATAKEY`, `DATA`) VALUES
('admin@astra', 'eu.ist.astra.aam.impl.AwarenessApplicationManagerImpl', '<?xml version="1.0" encoding="UTF-8"?>\n<root><application appID="admin@astra:cinema" type="eu.ist.astra.aam.impl.NimbusApplication" owner="admin@astra" desc="watch a movie in the cinema" state="on" publishable="1" cdate="2009-08-05" RULES="[admin@astra:cinema_0, admin@astra:cinema_0-reverse]" active="false"><statevalue></statevalue><rule>admin@astra:cinema_0</rule><rule>admin@astra:cinema_0-reverse</rule></application><application appID="admin@astra:sad" type="eu.ist.astra.aam.impl.NimbusApplication" owner="admin@astra" desc="i feel sad" state="on" publishable="1" cdate="2009-08-05" RULES="[admin@astra:sad_0, admin@astra:sad_0-reverse]" active="false"><statevalue></statevalue><rule>admin@astra:sad_0</rule><rule>admin@astra:sad_0-reverse</rule></application><application appID="admin@astra:play_handball" type="eu.ist.astra.aam.impl.NimbusApplication" owner="admin@astra" desc="play a handball match" state="on" publishable="1" cdate="2009-08-05" RULES="[admin@astra:play_handball_0, admin@astra:play_handball_0-reverse]" active="false"><statevalue></statevalue><rule>admin@astra:play_handball_0</rule><rule>admin@astra:play_handball_0-reverse</rule></application><application appID="admin@astra:tapas" type="eu.ist.astra.aam.impl.NimbusApplication" owner="admin@astra" desc="go to a Spanish restaurant to have some tapas" state="on" publishable="1" cdate="2009-08-05" RULES="[admin@astra:tapas_0, admin@astra:tapas_0-reverse]" active="false"><statevalue></statevalue><rule>admin@astra:tapas_0</rule><rule>admin@astra:tapas_0-reverse</rule></application><application appID="admin@astra:dance" type="eu.ist.astra.aam.impl.NimbusApplication" owner="admin@astra" desc="going out to dance in some disco" state="on" publishable="1" cdate="2009-08-05" RULES="[admin@astra:dance_0, admin@astra:dance_0-reverse]" active="false"><statevalue></statevalue><rule>admin@astra:dance_0</rule><rule>admin@astra:dance_0-reverse</rule></application><application appID="admin@astra:photography_exposition" type="eu.ist.astra.aam.impl.NimbusApplication" owner="admin@astra" desc="going to that new photography exposition" state="on" publishable="1" cdate="2009-08-05" RULES="[admin@astra:photography_exposition_0, admin@astra:photography_exposition_0-reverse]" active="false"><statevalue></statevalue><rule>admin@astra:photography_exposition_0</rule><rule>admin@astra:photography_exposition_0-reverse</rule></application><application appID="admin@astra:go_sightseeing" type="eu.ist.astra.aam.impl.NimbusApplication" owner="admin@astra" desc="go sightseeing in our city" state="on" publishable="1" cdate="2009-08-05" RULES="[admin@astra:go_sightseeing_0, admin@astra:go_sightseeing_0-reverse]" active="false"><statevalue></statevalue><rule>admin@astra:go_sightseeing_0</rule><rule>admin@astra:go_sightseeing_0-reverse</rule></application><application appID="admin@astra:office" type="eu.ist.astra.aam.impl.NimbusApplication" owner="admin@astra" desc="i am at work, in my office" state="on" publishable="1" cdate="2009-08-05" RULES="[admin@astra:office_0, admin@astra:office_0-reverse]" active="false"><statevalue></statevalue><rule>admin@astra:office_0</rule><rule>admin@astra:office_0-reverse</rule></application><application appID="admin@astra:play_football" type="eu.ist.astra.aam.impl.NimbusApplication" owner="admin@astra" desc="play a football match" state="on" publishable="1" cdate="2009-08-05" RULES="[admin@astra:play_football_0, admin@astra:play_football_0-reverse]" active="false"><statevalue></statevalue><rule>admin@astra:play_football_0</rule><rule>admin@astra:play_football_0-reverse</rule></application><application appID="admin@astra:coffee" type="eu.ist.astra.aam.impl.NimbusApplication" owner="admin@astra" desc="going to have a coffee" state="on" publishable="1" cdate="2009-08-05" RULES="[admin@astra:coffee_0, admin@astra:coffee_0-reverse]" active="false"><statevalue></statevalue><rule>admin@astra:coffee_0</rule><rule>admin@astra:coffee_0-reverse</rule></application><application appID="admin@astra:go_to_the_gym" type="eu.ist.astra.aam.impl.NimbusApplication" owner="admin@astra" desc="go to the gym to train" state="on" publishable="1" cdate="2009-08-05" RULES="[admin@astra:go_to_the_gym_0, admin@astra:go_to_the_gym_0-reverse]" active="false"><statevalue></statevalue><rule>admin@astra:go_to_the_gym_0</rule><rule>admin@astra:go_to_the_gym_0-reverse</rule></application><application appID="admin@astra:missing_you" type="eu.ist.astra.aam.impl.NimbusApplication" owner="admin@astra" desc="I am missing you so much" state="on" publishable="1" cdate="2009-08-05" RULES="[admin@astra:missing_you_0, admin@astra:missing_you_0-reverse]" active="false"><statevalue></statevalue><rule>admin@astra:missing_you_0</rule><rule>admin@astra:missing_you_0-reverse</rule></application><application appID="admin@astra:beer" type="eu.ist.astra.aam.impl.NimbusApplication" owner="admin@astra" desc="going out to have some beers" state="on" publishable="1" cdate="2009-08-05" RULES="[admin@astra:beer_0, admin@astra:beer_0-reverse]" active="false"><statevalue></statevalue><rule>admin@astra:beer_0</rule><rule>admin@astra:beer_0-reverse</rule></application><application appID="admin@astra:concert" type="eu.ist.astra.aam.impl.NimbusApplication" owner="admin@astra" desc="go to a live music concert" state="on" publishable="1" cdate="2009-08-05" RULES="[admin@astra:concert_0, admin@astra:concert_0-reverse]" active="false"><statevalue></statevalue><rule>admin@astra:concert_0</rule><rule>admin@astra:concert_0-reverse</rule></application><application appID="admin@astra:thinking_of_you" type="eu.ist.astra.aam.impl.NimbusApplication" owner="admin@astra" desc="i miss you and i am thinking of you" state="on" publishable="1" cdate="2009-08-05" RULES="[admin@astra:thinking_of_you_0, admin@astra:thinking_of_you_0-reverse]" active="false"><statevalue></statevalue><rule>admin@astra:thinking_of_you_0</rule><rule>admin@astra:thinking_of_you_0-reverse</rule></application><application appID="admin@astra:go_jogging" type="eu.ist.astra.aam.impl.NimbusApplication" owner="admin@astra" desc="go jogging to the forest" state="on" publishable="1" cdate="2009-08-05" RULES="[admin@astra:go_jogging_0, admin@astra:go_jogging_0-reverse]" active="false"><statevalue></statevalue><rule>admin@astra:go_jogging_0</rule><rule>admin@astra:go_jogging_0-reverse</rule></application><application appID="admin@astra:my_parents" type="eu.ist.astra.aam.impl.NimbusApplication" owner="admin@astra" desc="i am at my parents home" state="on" publishable="1" cdate="2009-08-05" RULES="[admin@astra:my_parents_0, admin@astra:my_parents_0-reverse]" active="false"><statevalue></statevalue><rule>admin@astra:my_parents_0</rule><rule>admin@astra:my_parents_0-reverse</rule></application><application appID="admin@astra:girlfriend_home" type="eu.ist.astra.aam.impl.NimbusApplication" owner="admin@astra" desc="i am at my girlfriend home" state="on" publishable="1" cdate="2009-08-05" RULES="[admin@astra:girlfriend_home_0, admin@astra:girlfriend_home_0-reverse]" active="true"><statevalue></statevalue><rule>admin@astra:girlfriend_home_0</rule><rule>admin@astra:girlfriend_home_0-reverse</rule></application><application appID="admin@astra:happy" type="eu.ist.astra.aam.impl.NimbusApplication" owner="admin@astra" desc="i am really happy" state="on" publishable="1" cdate="2009-08-05" RULES="[admin@astra:happy_0, admin@astra:happy_0-reverse]" active="false"><statevalue></statevalue><rule>admin@astra:happy_0</rule><rule>admin@astra:happy_0-reverse</rule></application><application appID="admin@astra:girlfriend\\''s_place" type="eu.ist.astra.aam.impl.NimbusApplication" owner="admin@astra" desc="i am going to be at my girlfriend\\''s home" state="on" publishable="1" cdate="2009-08-05" active="false"><statevalue></statevalue></application><application appID="admin@astra:home" type="eu.ist.astra.aam.impl.NimbusApplication" owner="admin@astra" desc="i am at home" state="on" publishable="1" cdate="2009-08-05" RULES="[admin@astra:home_0, admin@astra:home_0-reverse]" active="false"><statevalue></statevalue><rule>admin@astra:home_0</rule><rule>admin@astra:home_0-reverse</rule></application><application appID="admin@astra:hating_you" type="eu.ist.astra.aam.impl.NimbusApplication" owner="admin@astra" desc="I think of you, and I am really angry" state="on" publishable="1" cdate="2009-08-05" RULES="[admin@astra:hating_you_0, admin@astra:hating_you_0-reverse]" active="false"><statevalue></statevalue><rule>admin@astra:hating_you_0</rule><rule>admin@astra:hating_you_0-reverse</rule></application><application appID="admin@astra:romantic_dinner" type="eu.ist.astra.aam.impl.NimbusApplication" owner="admin@astra" desc="going to an Italian restaurant to have a romantic dinner" state="on" publishable="1" cdate="2009-08-05" RULES="[admin@astra:romantic_dinner_0, admin@astra:romantic_dinner_0-reverse]" active="false"><statevalue></statevalue><rule>admin@astra:romantic_dinner_0</rule><rule>admin@astra:romantic_dinner_0-reverse</rule></application><application appID="admin@astra:village" type="eu.ist.astra.aam.impl.NimbusApplication" owner="admin@astra" desc="i am going to be in my mother village" state="on" publishable="1" cdate="2009-08-05" RULES="[admin@astra:village_0, admin@astra:village_0-reverse]" active="false"><statevalue></statevalue><rule>admin@astra:village_0</rule><rule>admin@astra:village_0-reverse</rule></application><application appID="admin@astra:play_basketball_match" type="eu.ist.astra.aam.impl.NimbusApplication" owner="admin@astra" desc="play an amateur basketball match" state="on" publishable="1" cdate="2009-08-05" RULES="[admin@astra:play_basketball_match_0, admin@astra:play_basketball_match_0-reverse]" active="false"><statevalue></statevalue><rule>admin@astra:play_basketball_match_0</rule><rule>admin@astra:play_basketball_match_0-reverse</rule></application><application appID="admin@astra:museum" type="eu.ist.astra.aam.impl.NimbusApplication" owner="admin@astra" desc="go to a museum " state="on" publishable="1" cdate="2009-08-05" RULES="[admin@astra:museum_0, admin@astra:museum_0-reverse]" active="false"><statevalue></statevalue><rule>admin@astra:museum_0</rule><rule>admin@astra:museum_0-reverse</rule></application></root>'),
('rules', 'admin@astra:play_football_0', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:play_football</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:play_football_0</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:play_football_0-reverse', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:play_football</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:play_football_0-reverse</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:play_handball_0', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:play_handball</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:play_handball_0</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:play_handball_0-reverse', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:play_handball</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:play_handball_0-reverse</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:play_basketball_match_0', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:play_basketball_match</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:play_basketball_match_0</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:play_basketball_match_0-reverse', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:play_basketball_match</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:play_basketball_match_0-reverse</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:go_jogging_0', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:go_jogging</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:go_jogging_0</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:go_jogging_0-reverse', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:go_jogging</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:go_jogging_0-reverse</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:go_to_the_gym_0', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:go_to_the_gym</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:go_to_the_gym_0</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:go_to_the_gym_0-reverse', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:go_to_the_gym</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:go_to_the_gym_0-reverse</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:coffee_0', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:coffee</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:coffee_0</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:coffee_0-reverse', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:coffee</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:coffee_0-reverse</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:tapas_0', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:tapas</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:tapas_0</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:tapas_0-reverse', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:tapas</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:tapas_0-reverse</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:romantic_dinner_0', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:romantic_dinner</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:romantic_dinner_0</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:romantic_dinner_0-reverse', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:romantic_dinner</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:romantic_dinner_0-reverse</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:beer_0', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:beer</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:beer_0</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:beer_0-reverse', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:beer</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:beer_0-reverse</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:dance_0', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:dance</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:dance_0</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:dance_0-reverse', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:dance</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:dance_0-reverse</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:thinking_of_you_0', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:thinking_of_you</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:thinking_of_you_0</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:thinking_of_you_0-reverse', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:thinking_of_you</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:thinking_of_you_0-reverse</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:sad_0', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:sad</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:sad_0</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:sad_0-reverse', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:sad</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:sad_0-reverse</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:happy_0', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:happy</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:happy_0</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:happy_0-reverse', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:happy</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:happy_0-reverse</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:hating_you_0', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:hating_you</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:hating_you_0</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:hating_you_0-reverse', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:hating_you</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:hating_you_0-reverse</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:missing_you_0', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:missing_you</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:missing_you_0</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:missing_you_0-reverse', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:missing_you</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:missing_you_0-reverse</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:cinema_0', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:cinema</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:cinema_0</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:cinema_0-reverse', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:cinema</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:cinema_0-reverse</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:museum_0', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:museum</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:museum_0</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:museum_0-reverse', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:museum</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:museum_0-reverse</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:photography_exposition_0', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:photography_exposition</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:photography_exposition_0</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:photography_exposition_0-reverse', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:photography_exposition</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:photography_exposition_0-reverse</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:concert_0', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:concert</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:concert_0</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:concert_0-reverse', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:concert</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:concert_0-reverse</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:go_sightseeing_0', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:go_sightseeing</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:go_sightseeing_0</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:go_sightseeing_0-reverse', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:go_sightseeing</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:go_sightseeing_0-reverse</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:office_0', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:office</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:office_0</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:office_0-reverse', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:office</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:office_0-reverse</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:home_0', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:home</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:home_0</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:home_0-reverse', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:home</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:home_0-reverse</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:village_0', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:village</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:village_0</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:village_0-reverse', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:village</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:village_0-reverse</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:my_parents_0', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:my_parents</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:my_parents_0</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:my_parents_0-reverse', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:my_parents</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:my_parents_0-reverse</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:girlfriend_home_0', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:girlfriend_home</NAME><COMPARISON_OPERATOR>EQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:girlfriend_home_0</RULE_NAME></RULE></RULES>\n'),
('rules', 'admin@astra:girlfriend_home_0-reverse', '<?xml version="1.0" encoding="UTF-8"?>\n<RULES><RULE><CONDITION><TYPE>Awareness</TYPE><NAME>inPlace</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></CONDITION><RESULT><TYPE>InvokeMessage</TYPE><NAME>admin@astra:girlfriend_home</NAME><COMPARISON_OPERATOR>NEQ</COMPARISON_OPERATOR><VALUE>true</VALUE></RESULT><RULE_NAME>admin@astra:girlfriend_home_0-reverse</RULE_NAME></RULE></RULES>\n');

-- --------------------------------------------------------

--
-- Table structure for table `TagNode`
--

CREATE TABLE IF NOT EXISTS `TagNode` (
  `ID` int(10) unsigned NOT NULL auto_increment,
  `UID` varchar(255) NOT NULL,
  `APPLICATION_ID` varchar(255) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `TagNode`
--

