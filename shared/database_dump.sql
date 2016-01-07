-- phpMyAdmin SQL Dump
-- version 2.11.11.3
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 07, 2016 at 04:03 PM
-- Server version: 5.5.45
-- PHP Version: 5.3.29

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Database: `cmpe451_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `COMMENT`
--

CREATE TABLE IF NOT EXISTS `COMMENT` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `OWNER_ID` bigint(20) NOT NULL,
  `POST_ID` bigint(20) NOT NULL,
  `CONTENT` text NOT NULL,
  `POST_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `LAST_EDITED_DATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`ID`),
  KEY `OWNER_ID` (`OWNER_ID`),
  KEY `POST_ID` (`POST_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=12 ;

--
-- Dumping data for table `COMMENT`
--

INSERT INTO `COMMENT` (`ID`, `OWNER_ID`, `POST_ID`, `CONTENT`, `POST_DATE`, `LAST_EDITED_DATE`) VALUES
(1, 15, 7, 'This is a really interesting story. So that is how tea came from eastern to western cultures.', '2015-11-17 11:12:31', '2015-11-17 11:05:19'),
(2, 15, 8, 'Great story bro !', '2015-11-17 12:24:23', '2015-11-17 12:24:23'),
(3, 16, 25, 'I also remember having a casette with this song in it. Good old days.', '2015-12-08 11:35:16', '2015-12-08 11:35:16'),
(4, 19, 6, 'This is a magnificent story', '2015-12-11 17:54:04', '2015-12-11 17:54:04'),
(5, 26, 32, 'How wonderful picture is this !! ', '2015-12-21 22:54:35', '2015-12-21 22:54:35'),
(6, 15, 34, 'The wall looks astonishing from the pictures! And I can''t believe that villagers want to get rid of stones and use boring concrete instead.', '2016-01-06 17:11:58', '2016-01-06 17:11:58'),
(7, 33, 34, 'Then I guess they must be pretty boring people :)\r\nBut yeah, the wall really looks awesome. I need to go Yalikoy someday. ', '2016-01-06 17:34:00', '2016-01-06 17:34:00'),
(8, 16, 31, 'I cant believe I missed this on my trip to Rome. Guess I''ll have to go again sometime!', '2016-01-06 17:42:30', '2016-01-06 17:42:30'),
(9, 31, 35, 'That is so interesting. Love horses. Although, I would prefer the run wild and free. \r\n\r\nAnatolia is just amazing.', '2016-01-06 17:50:43', '2016-01-06 17:50:43'),
(10, 26, 45, 'That''s an awesome contribution to this heritage ! Anyone who visits Denizli should stop by Buldan and shop there.', '2016-01-07 08:55:21', '2016-01-07 08:55:21'),
(11, 33, 51, 'Love this song <3', '2016-01-07 13:37:56', '2016-01-07 13:37:56');

-- --------------------------------------------------------

--
-- Table structure for table `COMMENT_VOTE`
--

CREATE TABLE IF NOT EXISTS `COMMENT_VOTE` (
  `MEMBER_ID` bigint(20) NOT NULL,
  `COMMENT_ID` bigint(20) NOT NULL,
  `VOTE_TYPE` bit(1) NOT NULL,
  PRIMARY KEY (`MEMBER_ID`,`COMMENT_ID`),
  KEY `COMMENT_ID` (`COMMENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `COMMENT_VOTE`
--

INSERT INTO `COMMENT_VOTE` (`MEMBER_ID`, `COMMENT_ID`, `VOTE_TYPE`) VALUES
(13, 3, ''),
(15, 4, '\0'),
(15, 7, ''),
(16, 1, ''),
(16, 2, ''),
(16, 3, ''),
(16, 4, ''),
(19, 1, ''),
(19, 2, ''),
(19, 4, ''),
(22, 2, ''),
(33, 9, '');

-- --------------------------------------------------------

--
-- Table structure for table `FOLLOW`
--

CREATE TABLE IF NOT EXISTS `FOLLOW` (
  `FOLLOWER_ID` bigint(20) NOT NULL,
  `FOLLOWEE_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`FOLLOWER_ID`,`FOLLOWEE_ID`),
  KEY `FOLLOWEE_ID` (`FOLLOWEE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `FOLLOW`
--

INSERT INTO `FOLLOW` (`FOLLOWER_ID`, `FOLLOWEE_ID`) VALUES
(21, 8),
(15, 13),
(19, 13),
(29, 13),
(16, 15),
(19, 15),
(26, 15),
(36, 15),
(19, 16),
(21, 19),
(22, 19),
(23, 19),
(24, 19),
(19, 21),
(33, 21),
(19, 23),
(29, 23),
(19, 24),
(36, 26),
(16, 29),
(35, 29),
(39, 29),
(33, 31),
(36, 31),
(38, 31),
(29, 36),
(35, 36);

-- --------------------------------------------------------

--
-- Table structure for table `FOLLOW_HERITAGE`
--

CREATE TABLE IF NOT EXISTS `FOLLOW_HERITAGE` (
  `FOLLOWER_ID` bigint(20) NOT NULL,
  `HERITAGE_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`FOLLOWER_ID`,`HERITAGE_ID`),
  KEY `HERITAGE_ID` (`HERITAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `FOLLOW_HERITAGE`
--

INSERT INTO `FOLLOW_HERITAGE` (`FOLLOWER_ID`, `HERITAGE_ID`) VALUES
(13, 10),
(21, 10),
(16, 12),
(19, 12),
(23, 17),
(25, 17),
(29, 17),
(19, 18),
(15, 20),
(15, 21),
(15, 22),
(21, 22),
(36, 23),
(31, 26),
(36, 28),
(36, 29),
(36, 31),
(36, 32),
(21, 35),
(36, 35),
(29, 36),
(36, 36),
(39, 37);

-- --------------------------------------------------------

--
-- Table structure for table `FOLLOW_TAG`
--

CREATE TABLE IF NOT EXISTS `FOLLOW_TAG` (
  `FOLLOWER_ID` bigint(20) NOT NULL,
  `TAG_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`FOLLOWER_ID`,`TAG_ID`),
  KEY `TAG_ID` (`TAG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `FOLLOW_TAG`
--

INSERT INTO `FOLLOW_TAG` (`FOLLOWER_ID`, `TAG_ID`) VALUES
(29, 1),
(29, 6),
(29, 9),
(13, 11),
(19, 14),
(28, 14),
(19, 15),
(29, 45);

-- --------------------------------------------------------

--
-- Table structure for table `GAMIFICATION`
--

CREATE TABLE IF NOT EXISTS `GAMIFICATION` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_ID` bigint(20) NOT NULL,
  `POST_NUM` bigint(20) DEFAULT '0',
  `POST_LEVEL` int(11) DEFAULT '0',
  `HERITAGE_NUM` bigint(20) DEFAULT '0',
  `HERITAGE_LEVEL` int(11) DEFAULT '0',
  `FOLLOWER_NUM` bigint(20) DEFAULT '0',
  `FOLLOWER_LEVEL` int(11) DEFAULT '0',
  `FOLLOWEE_NUM` bigint(20) DEFAULT '0',
  `FOLLOWEE_LEVEL` int(11) DEFAULT '0',
  `COMMENT_NUM` bigint(20) DEFAULT '0',
  `COMMENT_LEVEL` int(11) DEFAULT '0',
  `UPVOTE_NUM` bigint(20) DEFAULT '0',
  `UPVOTE_LEVEL` int(11) DEFAULT '0',
  `DOWNVOTE_NUM` bigint(20) DEFAULT '0',
  `DOWNVOTE_LEVEL` int(11) DEFAULT '0',
  `OVERALL_LEVEL` int(11) DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `USER_ID` (`USER_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=14 ;

--
-- Dumping data for table `GAMIFICATION`
--

INSERT INTO `GAMIFICATION` (`ID`, `USER_ID`, `POST_NUM`, `POST_LEVEL`, `HERITAGE_NUM`, `HERITAGE_LEVEL`, `FOLLOWER_NUM`, `FOLLOWER_LEVEL`, `FOLLOWEE_NUM`, `FOLLOWEE_LEVEL`, `COMMENT_NUM`, `COMMENT_LEVEL`, `UPVOTE_NUM`, `UPVOTE_LEVEL`, `DOWNVOTE_NUM`, `DOWNVOTE_LEVEL`, `OVERALL_LEVEL`) VALUES
(1, 15, 8, 1, 0, 0, 3, 1, 1, 1, 3, 1, 4, 1, 1, 1, 1),
(2, 33, 1, 1, 0, 0, 0, 0, 2, 1, 2, 1, 4, 1, 0, 0, 0),
(3, 31, 3, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0),
(4, 34, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(5, 35, 1, 1, 0, 0, 0, 0, 2, 1, 0, 0, 0, 0, 0, 0, 0),
(6, 36, 3, 1, 0, 0, 2, 1, 3, 1, 0, 0, 0, 0, 0, 0, 0),
(7, 38, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0),
(8, 26, 0, 0, 0, 0, 0, 0, 1, 1, 2, 1, 1, 1, 0, 0, 0),
(9, 29, 1, 1, 0, 0, 3, 1, 3, 1, 0, 0, 3, 1, 0, 0, 0),
(10, 16, 2, 1, 0, 0, 1, 1, 2, 1, 2, 1, 8, 1, 1, 1, 1),
(11, 39, 2, 1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0),
(12, 19, 0, 0, 0, 0, 4, 1, 6, 1, 1, 1, 6, 1, 5, 1, 0),
(13, 22, 2, 1, 0, 0, 0, 0, 1, 1, 0, 0, 3, 1, 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `HERITAGE`
--

CREATE TABLE IF NOT EXISTS `HERITAGE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(64) NOT NULL,
  `PLACE` varchar(64) DEFAULT NULL,
  `POST_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `EVENT_DATE` timestamp NULL DEFAULT NULL,
  `DESCRIPTION` text NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=39 ;

--
-- Dumping data for table `HERITAGE`
--

INSERT INTO `HERITAGE` (`ID`, `NAME`, `PLACE`, `POST_DATE`, `EVENT_DATE`, `DESCRIPTION`) VALUES
(10, 'Halloween', 'All Over the World', '2015-11-17 02:40:59', NULL, 'Halloween is celebrated by millions of people in multiple countries. For most it is a fun time for kids who put on costumes and going door-to-door to get candy. But it is also known as a time of witches, ghouls, goblins, and ghosts.\r\n\r\nThe word, Halloween, is derived from the term, "All Hallows Eve," which occurred on Oct. 31. "All Saints Day" or "All Hallows Day" was the next Day, Nov. 1. Therefore, Halloween is the eve of All Saints Day.\r\n'),
(11, 'Japanese Tea Ceremony', 'Japan', '2015-11-17 02:54:18', NULL, 'The Japanese tea ceremony is called Chanoyu, Sado or simply Ocha in Japanese. It is a choreographic ritual of preparing and serving Japanese green tea, called Matcha, together with traditional Japanese sweets to balance with the bitter taste of the tea. Preparing tea in this ceremony means pouring all one''s attention into the predefined movements. The whole process is not about drinking tea, but is about aesthetics, preparing a bowl of tea from one''s heart. The host of the ceremony always considers the guests with every movement and gesture. Even the placement of the tea utensils is considered from the guests view point (angle), especially the main guests called the Shokyaku.'),
(12, 'Didjeridu', 'Australia', '2015-11-17 12:20:13', NULL, 'The didgeridoo is a wind instrument developed by Indigenous Australians of northern Australia potentially within the last 1,500 years and still in widespread use today both in Australia and around the world. It is sometimes described as a natural wooden trumpet or drone pipe. Musicologists classify it as a brass aerophone.\r\n\r\nThere are no reliable sources stating the exact age of didgeridoo. Archaeological studies of rock art in Northern Australia suggest that the people of the Kakadu region of the Northern Territory have been using the didgeridoo for less than 1,000 years, based on the dating of paintings on cave walls and shelters from this period. A clear rock painting in Ginga Wardelirrhmeng, on the northern edge of the Arnhem Land plateau, from the freshwater period shows a didgeridoo player and two songmen participating in an Ubarr Ceremony.'),
(17, 'Christmas', 'All Over the World', '2015-12-08 03:05:56', NULL, 'Christmas or Christmas is an annual festival commemorating the birth of Jesus Christ, observed most commonly on December 25 as a religious and cultural celebration among billions of people around the world. A feast central to the Christian liturgical year, it is prepared for by the season of Advent or Nativity Fast and is prolonged by the Octave of Christmas and further by the season of Christmastide. Christmas Day is a public holiday in many of the world''s nations, is celebrated culturally by a large number of non-Christian people, and is an integral part of the Christmas and holiday season.'),
(18, 'Henna Weddings', 'Islamic Regions', '2015-12-08 08:53:24', NULL, 'Islamic weddings are steeped in centuries-old traditions and rituals. For one thing, it is generally believed that the best day for the ceremony to take place is on Thursday, since Friday is the holy day among Muslims. Another tradition is mehndi, or "henna" night. Two nights before the wedding, the bride is surrounded by women from her side of the family, who paint designs on her hands, arms, and feet. While henna night is an artistic and beautiful display, the same cannot be said for a custom practiced in parts of Scotland called "the blackening". It involves friends of the bride and groom tying the two together in bathtubs, large crates, or behind pickup trucks before parading them through the streets to be pelted by passersby with an array of disgusting material. This fun-filled tradition is believed to ward off evil spirits, and it also provides a bonding moment for the couple that symbolizes the hardships they are to endure and conquer together.'),
(19, 'Sinterklaas', 'Utrecht, Hollanda', '2015-12-08 08:38:10', NULL, 'Sinterklaas or Sint-Nicolaas is a mythical figure with legendary, historical and folkloric origins based on Saint Nicholas. Other names for the figure include De Sint ("The Saint"), De Goedheiligman ("The Good Holy Man"), and De Goede Sint ("The Good Saint") in Dutch; Saint-Nicolas in French; Sinteklaas in Frisian; and Kleeschen and Zinniklos in Luxembourgish.\r\n\r\nSinterklaas is celebrated annually with the giving of gifts on 5 December, the night before Saint Nicholas Day in the Northern Netherlands and on the morning of 6 December, Saint Nicholas Day itself, in the (Roman Catholic) southern provinces, Belgium, Luxembourg and Northern France (French Flanders, Lorraine and Artois). He is also well known in territories of the former Dutch Empire, including Aruba, Bonaire and CuraÃ§ao.'),
(20, 'Mona Lisa', 'Ile-de-France, France', '2015-12-21 23:55:51', NULL, 'The Mona Lisa  or La Gioconda, French: La Joconde is a half-length portrait of a woman by the Italian artist Leonardo da Vinci, which has been acclaimed as "the best known, the most visited, the most written about, the most sung about, the most parodied work of art in the world".\r\n\r\nThe painting, thought to be a portrait of Lisa Gherardini, the wife of Francesco del Giocondo, is in oil on a white Lombardy poplar panel, and is believed to have been painted between 1503 and 1506. Leonardo may have continued working on it as late as 1517. It was acquired by King Francis I of France and is now the property of the French Republic, on permanent display at the Louvre Museum in Paris since 1797.\r\n\r\nThe subject''s expression, which is frequently described as enigmatic, the monumentality of the composition, the subtle modeling of forms, and the atmospheric illusionism were novel qualities that have contributed to the continuing fascination and study of the work.'),
(21, 'Cappadocia', 'Nevsehir, Turkey', '2015-12-21 23:54:06', NULL, 'Cappadocia (Turkish: Kapadokya) is a historical region in Central Anatolia, largely in the Nevsehir, Kayseri, Aksaray, and Nigde Provinces in Turkey. \r\n\r\nIn the time of Herodotus, the Cappadocians were reported as occupying the whole region from Mount Taurus to the vicinity of the Euxine (Black Sea). Cappadocia, in this sense, was bounded in the south by the chain of the Taurus Mountains that separate it from Cilicia, to the east by the upper Euphrates and the Armenian Highland, to the north by Pontus, and to the west by Lycaonia and eastern Galatia.\r\n\r\nThe name, traditionally used in Christian sources throughout history, continues in use as an international tourism concept to define a region of exceptional natural wonders, in particular characterized by fairy chimneys and a unique historical and cultural heritage.'),
(22, 'Calzone', 'Italy', '2015-12-21 21:34:08', NULL, 'A calzone is an Italian filled oven pizza, originating in Naples, and shaped as a folded pizza. It resembles a half-moon and is made of salted bread dough. A typical calzone is baked in an oven and stuffed with salami or ham, mozzarella, ricotta and Parmesan or pecorino cheese, amalgamated with an egg. Its regional variations include other ingredients that are normally associated with pizza toppings. Calzones of smaller size can also be fried in olive oil.'),
(23, 'Anastasian Wall', 'Yalikoy, Turkey', '2016-01-05 20:58:33', NULL, 'Yalikoy is a historic place on the Black Sea, in Thrace. You can find parts of Anastasian Wall there. Quite impressive.\r\n\r\nhttps://youtu.be/7Q2R5-1b_Ec is an amateur video'),
(24, 'Diwali Festival of Lights', 'Karnataka, India', '2016-01-05 21:49:06', NULL, 'A Hindu festival of lights celebrated in India. '),
(25, 'Cirit', 'Anatolia', '2016-01-06 16:56:57', NULL, 'Turks used to spend most of their time with horses. They used to go everywhere with horses and use them in battles to gain speed and maneuver advantage. \r\nCirit is a Turkish traditional war training sport that is performed in Anatolian and Central Asia. The main objective is to throw javelins to opposite teams'' rider and get score. Cirit was performed during fests, weddings or training horsemen.'),
(26, 'Kars Goose', 'Kars, Turkiye', '2016-01-06 21:14:13', NULL, 'Geese are widely bred Kars and neighbooring regions. Every year geese are slaughtered by the end of autumn. They are salted and put outside to dry. In winter geese are cocked in Tandirs. Goose meat usually is served by bulghur.'),
(27, 'Coffee', 'Turkey', '2016-01-06 17:52:46', NULL, ''),
(28, 'coffee', 'turkey', '2016-01-06 17:55:32', '2016-01-06 00:00:00', 'Why is Event showing up now? In cultural heritage? \r\n\r\nCoffee is a big deal in Turkey. It is a strong strong coffee that can be enjoyed black, or with different levels of sugar. But never milk. \r\n\r\nPeople often drink coffee after meals. And there is a big traditional of fortune telling based on the coffee grounds.\r\n\r\nIt is also a symbol of human connection, hospitality, and friendship'),
(29, 'Pamukkale', 'Denizli, Turkey', '2016-01-06 18:43:24', NULL, 'Pamukkale, meaning "cotton castle" in Turkish, is a natural site in Denizli Province in southwestern Turkey. The city contains hot springs and travertines, terraces of carbonate minerals left by the flowing water. It is located in Turkey''s Inner Aegean region, in the River Menderes valley, which has a temperate climate for most of the year.\r\nThe ancient Greco-Roman and Byzantine city of Hierapolis was built on top of the white "castle" which is in total about 2,700 metres (8,860 ft) long, 600 m (1,970 ft) wide and 160 m (525 ft) high. It can be seen from the hills on the opposite side of the valley in the town of Denizli, 20 km away.'),
(30, 'Indigenous Philippine Music', 'Philippine', '2016-01-07 02:33:45', NULL, 'Despite colonial rule of more than 400 years, the Philippines continues to take pride in its indigenous music. Not only has indigenous music been preserved, but it has also helped enrich contemporary Philippine music, fusing the old with the new, the original with the borrowed, the indigenous with the foreign.\r\n\r\nWhat we now regard as popular or folk music is a product of such fusion. One example is the pasyon, a verse narrative on the life of Jesus Christ. While the pasyon was originally based on material used by Spanish friars to convert the natives to Christianity, it was also transformed by Filipinos into something that reflected its own identity, dreams, and traditions. Other Filipino musical forms also emerged as a result of the intersection of indigenous and Spanish musical traditions. These include the talindaw (a boat song), the awit (a song in slow triple time), the tagulaylay (a mournful song), the sambotani (a song sung at a feast), the kumintang (a war song that later became a love song), among others, all of which flourished during the Spanish colonial period.'),
(31, 'Salvador Dali', 'Spain', '2016-01-07 06:54:23', NULL, 'Salvador Dali was born on May 11, 1904, in Figueres, Spain. From an early age, Dali was encouraged to practice his art and would eventually go on to study at an academy in Madrid. In the 1920s, he went to Paris and began interacting with artists such as Picasso, Magritte and Miro, which led to Dali''s first Surrealist phase. He is perhaps best known for his 1931 painting The Persistence of Memory, showing melting clocks in a landscape setting. The rise of fascist leader Francisco Franco in Spain led to the artist''s expulsion from the Surrealist movement, but that didn''t stop him from painting. Dali died in Figueres in 1989.'),
(32, 'Angkor Thom', 'Cambodia', '2016-01-07 07:38:59', NULL, 'Angkor Thom located in present day Cambodia, was the last and most enduring capital city of the Khmer empire. It was established in the late twelfth century by King Jayavarman7.At the centre of the city is Jayavarman''s state temple, the Bayon, with the other major sites clustered around the Victory Square immediately to the north.Angkor Thom was established as the capital of Jayavarman7 empire, and was the centre ofhis massive building programme. One inscription found in the city refers to Jayavarman as the groom and the city as his bride.Angkor Thom seems not to be the first Khmer capital on the site, however. Yasodharapura, dating from three centuries earlier, was centred slightly further northwest, and Angkor Thom overlapped parts of it. The most notable earlier temples within the city are the former state temple of Baphuon, and Phimeanakas, which was incorporated into the Royal Palace. The Khmers did not draw any clear distinctions between Angkor Thom and Yashodharapura even in the fourteenth century an inscription used the earlier name.The name of Angkor Thom great city was in use from the 16th century.'),
(33, 'Joan Miro', '', '2016-01-07 07:45:20', NULL, 'Joan Miro i Ferra (20 April 1893, 25 December 1983) was a Spanish painter, sculptor, and ceramicist born in Barcelona. A museum dedicated to his work, the Fundacio Joan Miro, was established in his native city of Barcelona in 1975, and another, the Fundacio Pilar i Joan Miro, was established in his adoptive city of Palma de Mallorca in 1981.\r\n\r\nEarning international acclaim, his work has been interpreted as Surrealism, a sandbox for the subconscious mind, a re-creation of the childlike, and a manifestation of Catalan pride. In numerous interviews dating from the 1930s onwards, Miro expressed contempt for conventional painting methods as a way of supporting bourgeois society, and famously declared an "assassination of painting" in favour of upsetting the visual elements of established painting.'),
(34, 'Moonlight Sonata', '', '2016-01-07 08:00:12', '2016-01-07 00:00:00', 'The Piano Sonata No. 14 in C-sharp minor "Quasi una fantasia", Op. 27, No. 2, popularly known as the Moonlight Sonata, is a piano sonata by Ludwig van Beethoven. It was completed in 1801 and dedicated in 1802 to his pupil, Countess Giulietta Guicciardi\r\n\r\nThis piece is one of Beethoven''s most popular compositions for the piano, and it was a popular favorite even in his own day. Beethoven wrote the Moonlight Sonata in his early thirties, and did so after he had finished with some commissioned work; there is no evidence that he was commissioned to write this sonata.\r\n\r\nThe first edition of the score is headed Sonata quasi una fantasia, a title this work shares with its companion piece, Op. 27, No. 1. Grove Music Online translates the Italian title as "sonata in the manner of a fantasy". Translated more literally, this is "sonata almost a fantasy".\r\n\r\nThe name "Moonlight Sonata" comes from remarks made by the German music critic and poet Ludwig Rellstab. In 1832, five years after Beethoven''s death, Rellstab likened the effect of the first movement to that of moonlight shining upon Lake Lucerne. Within ten years, the name "Moonlight Sonata" ("Mondscheinsonate" in German) was being used in German and English publications. Later in the nineteenth century, the sonata was universally known by that name.'),
(35, 'Manneken Pis', 'Belgium', '2016-01-07 08:08:21', NULL, 'The 61 cm tall bronze statue on the corner of Rue de l''Etuve and Rue des Grands Carmes was made in 1619 by Brussels sculptor Hieronimus Duquesnoy the Elder, father of the more famous Francois Duquesnoy. The figure has been repeatedly stolen, the current statue dates from 1965. The original restored version is kept at the Maison du Roi/Broodhuis on the Grand Place.\r\n\r\nThere are several legends behind this statue, but the most famous is the one about Duke Godfrey 3 of Leuven. In 1142, the troops of this two-year-old lord were battling against the troops of the Berthouts, the lords of Grimbergen, in Ransbeke ,now Neder Over Heembeek. The troops put the infant lord in a basket and hung the basket in a tree to encourage them. From there, the boy urinated on the troops of the Berthouts, who eventually lost the battle.\r\n\r\nAnother legend states that in the 14th century, Brussels was under siege by a foreign power. The city had held its ground for some time, so the attackers conceived of a plan to place explosive charges at the city walls. A little boy named Julianske happened to be spying on them as they were preparing. He urinated on the burning fuse and thus saved the city. There was at the time ,middle of the 15th century, perhaps as early as 1388, a similar statue made of stone. The statue was stolen several times.\r\n\r\nAnother story ,old often to tourists, tells of a wealthy merchant who, during a visit to the city with his family, had his beloved young son go missing. The merchant hastily formed a search party that scoured all corners of the city until the boy was found happily urinating in a small garden. The merchant, as a gift of gratitude to the locals who helped out during the search, had the fountain built.\r\n\r\nAnother legend was that a small boy went missing from his mother when shopping in the centre of the city. The woman, panic-stricken by the loss of her child, called upon everyone she came across, including the mayor of the city. A city wide search began and when at last the child was found, he was urinating on the corner of a small street. The story was passed down over time and the statue erected as a tribute to the wellknown legend.\r\n\r\nAnother legend tells of the young boy who was awoken by a fire and was able to put out the fire with his urine, in the end this helped stop the king''s castle from burning down.'),
(36, 'Drama Bridge', 'Makedonia Thraki, Yunanistan', '2016-01-07 09:35:31', NULL, 'The town of Drama, which was then a part of the Ottoman Empire, is now in the East Macedonia and Thrace region of Greece. What is called a bridge in the song is actually an aqueduct between the villages of Nikiforos (Turkish: Nusratli) and Karyafiton (Turkish: Kozlukoy). When viewed from the valley the aqueduct looks like a bridge.'),
(37, 'Tsar Samuel Fortress', 'Ohrid, Makedonya (FYROM)', '2016-01-07 09:26:22', '2003-01-01 00:00:00', 'Samuels Fortress is a fortress in the old town of Ohrid, Republic of Macedonia. It was the capital of the First Bulgarian Empire during the rule of Tsar Samuel at the turn of the 10th century. Today, this historical monument is a major tourist attraction and was renovated in 2003.\r\n\r\nAccording to recent excavations by Macedonian archaeologists, it was contended that this fortress was built on the place of an earlier fortification, dated to the 4th century BCE, which was probably built by King Philip II of Macedon.'),
(38, 'Asiklik Tradition (Ashik)', '', '2016-01-07 09:41:10', NULL, 'An ashiq, ashik, or ashough is a mystic bard, balladeer, or troubadour who accompanied his song, be it a hikaye (Persian: dastan, a traditional epic or a romantic tale) or a shorter original composition, with a long necked lute (saz). The modern Azerbaijani ashiq is a professional musician who usually serves an apprenticeship, masters playing saz, and builds up a varied but individual repertoire of Turkic folk songs. The word ashiq ("in love, lovelorn") is subjective forms derives from ishq (love), related to Avestan "to wish, desire, search". The Turkish term that ashik superseded was ozan. In the early armies of the Turks, as far back as that of Attila, the ruler was invariably accompanied by an ozan. The heroic poems, which they recited to the accompaniment of the kopuz, flattered the sensibilities of an entire people.\r\n\r\nMastering in playing saz is the essential requirement for an ashik. This instrument, a variant of which is known as baglama, is a stringed musical instrument and belongs to the family of long-necked lutes.\r\n\r\nThe most spread poetry genres are gerayly, qoshma and tajnis.\r\n\r\nFamous ashiks: Yunus Emre, Kaygusuz Abdal, Pir Sultan Abdal, Karacaoglan, Jivani, Asik Veysel, Asik Mahzuni Serif, Neset Ertas, Ali Ekber Cicek.');

-- --------------------------------------------------------

--
-- Table structure for table `HERITAGE_POST`
--

CREATE TABLE IF NOT EXISTS `HERITAGE_POST` (
  `HERITAGE_ID` bigint(20) NOT NULL,
  `POST_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`HERITAGE_ID`,`POST_ID`),
  KEY `POST_ID` (`POST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `HERITAGE_POST`
--

INSERT INTO `HERITAGE_POST` (`HERITAGE_ID`, `POST_ID`) VALUES
(10, 6),
(11, 7),
(12, 8),
(11, 9),
(17, 25),
(18, 26),
(17, 27),
(17, 28),
(17, 29),
(20, 30),
(22, 31),
(21, 32),
(23, 33),
(23, 34),
(25, 35),
(26, 36),
(20, 37),
(28, 38),
(29, 39),
(30, 40),
(31, 41),
(32, 42),
(33, 43),
(32, 44),
(29, 45),
(36, 46),
(36, 47),
(37, 48),
(38, 49),
(35, 50),
(36, 51);

-- --------------------------------------------------------

--
-- Table structure for table `MEDIA`
--

CREATE TABLE IF NOT EXISTS `MEDIA` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `POST_OR_HERITAGE_ID` bigint(20) NOT NULL,
  `MEDIA_LINK` varchar(256) NOT NULL,
  `MEDIA_TYPE` int(11) NOT NULL,
  `POST_OR_HERITAGE` bit(1) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=50 ;

--
-- Dumping data for table `MEDIA`
--

INSERT INTO `MEDIA` (`ID`, `POST_OR_HERITAGE_ID`, `MEDIA_LINK`, `MEDIA_TYPE`, `POST_OR_HERITAGE`) VALUES
(3, 10, 'http://res.cloudinary.com/bounswe2015group8/image/upload/v1449573552/halloween-imagine_xufgkd.jpg', 0, ''),
(4, 11, 'http://res.cloudinary.com/bounswe2015group8/image/upload/v1449573656/teaceremony_pczjlo.jpg', 0, ''),
(5, 7, 'http://res.cloudinary.com/bounswe2015group8/image/upload/v1449573543/Japanese-Tea-Ceremony_ld9mfy.jpg', 0, '\0'),
(6, 12, 'http://res.cloudinary.com/bounswe2015group8/image/upload/v1449573485/Australiandidgeridoos_krivj8.jpg', 0, ''),
(7, 24, 'http://res.cloudinary.com/bounswe2015group8/image/upload/v1449498025/cg29az6cstrdlwhqgyqw.png', 0, '\0'),
(8, 17, 'http://res.cloudinary.com/bounswe2015group8/image/upload/v1449543957/sz2aj7m5zoyaaw7nanm8.jpg', 0, ''),
(9, 25, 'http://res.cloudinary.com/bounswe2015group8/video/upload/v1449545984/ltc4e4r96wnr0mj14hm9.mp4', 2, '\0'),
(10, 18, 'http://res.cloudinary.com/bounswe2015group8/image/upload/v1449547070/qbavxjniebahbsihpvif.jpg', 0, ''),
(11, 26, 'http://res.cloudinary.com/bounswe2015group8/video/upload/v1449547578/h8emmjfd43eicncbv98u.flv', 2, '\0'),
(12, 19, 'http://res.cloudinary.com/bounswe2015group8/image/upload/v1449563891/xlss47h8l9i3zeabvxs6.jpg', 0, ''),
(13, 28, 'http://res.cloudinary.com/bounswe2015group8/image/upload/v1449564044/aw3ze3bpv3jl11te7fja.jpg', 0, '\0'),
(14, 20, 'http://res.cloudinary.com/bounswe2015group8/image/upload/v1450732245/nrfvvx4m9dgld5lzonbt.jpg', 0, ''),
(15, 30, 'http://res.cloudinary.com/bounswe2015group8/image/upload/v1450732945/u13bfdkmjxvqyhxjrb1w.jpg', 0, '\0'),
(16, 21, 'http://res.cloudinary.com/bounswe2015group8/image/upload/v1450733377/pwiyvnxhqljxtanjf8rz.jpg', 0, ''),
(17, 22, 'http://res.cloudinary.com/bounswe2015group8/image/upload/v1450733648/umnyoongcp8u5uxkefjk.jpg', 0, ''),
(18, 31, 'http://res.cloudinary.com/bounswe2015group8/image/upload/v1450733980/rbsjo33vny6rc2vmgvzp.jpg', 0, '\0'),
(19, 32, 'http://res.cloudinary.com/bounswe2015group8/image/upload/v1450734368/jfnqqtgqcfmfcs6xnlyt.jpg', 0, '\0'),
(20, 23, 'http://res.cloudinary.com/bounswe2015group8/image/upload/v1452027513/cwsnmvxqvas9aajdk1eh.jpg', 0, ''),
(21, 24, 'http://res.cloudinary.com/bounswe2015group8/image/upload/v1452030547/duqbcwmcrj7ssuwz1cig.png', 0, ''),
(22, 34, 'http://res.cloudinary.com/bounswe2015group8/image/upload/v1452035478/agvtvjdwooqgkdu7nyfa.jpg', 0, '\0'),
(23, 23, 'http://res.cloudinary.com/bounswe2015group8/image/upload/v1452036000/wudndk5jle9us0dzvdmv.jpg', 0, ''),
(24, 23, 'http://res.cloudinary.com/bounswe2015group8/image/upload/v1452036040/lyqzqwfvbqbvwoccivnp.jpg', 0, ''),
(25, 35, 'http://res.cloudinary.com/bounswe2015group8/image/upload/v1452099735/ejo7bvvvnmulkhx5utqr.jpg', 0, '\0'),
(26, 26, 'http://res.cloudinary.com/bounswe2015group8/image/upload/v1452100491/bqyazinoou3u85ylt9t8.jpg', 0, ''),
(27, 36, 'http://res.cloudinary.com/bounswe2015group8/image/upload/v1452100696/cpkj6hyluw8yzxfuxivs.jpg', 0, '\0'),
(28, 28, 'http://res.cloudinary.com/bounswe2015group8/image/upload/v1452102933/qfwny9bkz0qol1a7hndf.png', 0, ''),
(29, 38, 'http://res.cloudinary.com/bounswe2015group8/image/upload/v1452103830/r8j0vnxpofbegpbyyvnd.jpg', 0, '\0'),
(30, 29, 'http://res.cloudinary.com/bounswe2015group8/image/upload/v1452105806/bxiz1w3u22lzlodl7uxa.jpg', 0, ''),
(31, 39, 'http://res.cloudinary.com/bounswe2015group8/image/upload/v1452106412/jssltclnsnwenxci0fbl.jpg', 0, '\0'),
(32, 39, 'http://res.cloudinary.com/bounswe2015group8/image/upload/v1452131249/jsjwxzvkm28pwtgfsunf.jpg', 0, '\0'),
(33, 30, 'http://res.cloudinary.com/bounswe2015group8/image/upload/v1452134026/myhwgfvxy59s07zblaio.jpg', 0, ''),
(34, 40, 'http://res.cloudinary.com/bounswe2015group8/video/upload/v1452135014/gpx2lcliy3uw1pic7p5h.mp4', 2, '\0'),
(35, 31, 'http://res.cloudinary.com/bounswe2015group8/image/upload/v1452149664/rdatkytrs15vkzkrwf2k.jpg', 0, ''),
(36, 41, 'http://res.cloudinary.com/bounswe2015group8/image/upload/v1452150091/h4xeazpua4mvfqtdilgu.jpg', 0, '\0'),
(37, 32, 'http://res.cloudinary.com/bounswe2015group8/video/upload/v1452171108/Lost_Temples-_Lost_City_of_Angkor_Wat_uxpf5a.mp4', 2, ''),
(38, 33, 'http://res.cloudinary.com/bounswe2015group8/image/upload/v1452152720/gusvrpnqz447gy3atopf.jpg', -1, ''),
(39, 42, 'http://res.cloudinary.com/bounswe2015group8/image/upload/v1452152739/z5nwcgvdeoeftglnvh1g.jpg', 0, '\0'),
(40, 43, 'http://res.cloudinary.com/bounswe2015group8/image/upload/v1452152929/ioivbbhinblsel0rsfyv.jpg', 0, '\0'),
(41, 44, 'http://res.cloudinary.com/bounswe2015group8/image/upload/v1452153001/js3g5hpfmvks6auxp8eo.jpg', 0, '\0'),
(42, 45, 'http://res.cloudinary.com/bounswe2015group8/image/upload/v1452153284/luwpeif9fisyh0p9lnii.jpg', 0, '\0'),
(43, 34, 'http://res.cloudinary.com/bounswe2015group8/video/upload/v1452153614/ewggmo9mjqw1rhq8ghqy.mp3', 2, ''),
(44, 35, 'http://res.cloudinary.com/bounswe2015group8/image/upload/v1452154102/p8ffxdaekmt2qgrpq192.jpg', 0, ''),
(45, 46, 'http://res.cloudinary.com/bounswe2015group8/video/upload/v1452157245/zxpjkgtihvctcdglr9kh.mp3', 1, '\0'),
(46, 47, 'http://res.cloudinary.com/bounswe2015group8/video/upload/v1452157924/hasnkbtg5ue38botailm.mp3', 1, '\0'),
(47, 37, 'http://res.cloudinary.com/bounswe2015group8/image/upload/v1452158783/ndplegbavgvgiuq1st3k.jpg', 0, ''),
(48, 48, 'http://res.cloudinary.com/bounswe2015group8/image/upload/v1452159021/kaapp8mknhgv17hopqtd.jpg', 0, '\0'),
(49, 38, 'http://res.cloudinary.com/bounswe2015group8/image/upload/v1452159671/lu6ojwq5xvsvocbufuwv.jpg', 0, '');

-- --------------------------------------------------------

--
-- Table structure for table `MEMBER`
--

CREATE TABLE IF NOT EXISTS `MEMBER` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(64) NOT NULL,
  `PASSWORD` varchar(128) NOT NULL,
  `EMAIL` varchar(64) NOT NULL,
  `PROFILE_PICTURE` varchar(256) DEFAULT NULL,
  `BIOGRAPHY` text,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=40 ;

--
-- Dumping data for table `MEMBER`
--

INSERT INTO `MEMBER` (`ID`, `USERNAME`, `PASSWORD`, `EMAIL`, `PROFILE_PICTURE`, `BIOGRAPHY`) VALUES
(1, 'gokcan', 'nowthatIcannottell', 'gokcantali@gmail.com', '', NULL),
(2, 'mehmet', 'muhammed', 'ahmet@lokum.com', '', NULL),
(3, 'Kola', 'disko', 'Whatareyou', '', NULL),
(4, 'mert', 'mert', 'mert.yasin@gmail.com', '', NULL),
(5, 'bugrahan', '123', 'bugrahan@451.co', '', NULL),
(6, 'chulgyun', 'chulgyun', 'chulgyun@gmail.com', '', NULL),
(7, 'clarkent', '$2a$10$kU/mfH3iCFuv/Ynytgm3rufYqfAz.Fzuyozi92w/d.pyyknBO/LJ2', 'iamsuperman@dc.com', '', NULL),
(8, 'goktug', '$2a$10$H5YGQgXXlTUL5WS38p4Lnecir0j3GhTtdsTSrBoG3ncapTTW.OB36', 'goktug', '', NULL),
(9, 'kaka', '$2a$10$l4XvVb1nSSXfMywrZ5ksZ./scWz/ntQIBKQKuHDTpLJfcAx2o8Kc6', 'kaka', '', NULL),
(10, 'kola', '$2a$10$4OmHjPgC6WbOcoWdVFBWY.3Y4s6WMg/NGPfe5O2MXbxHlcnGxHPvm', 'kola', '', NULL),
(11, 'haha', '$2a$10$sQ34df27auT.kDwVIboTBeNFodhEDqFlUpeJSzddkdyxz.w6UdCQ.', 'haha', '', NULL),
(12, 'ako', '$2a$10$Y/yK86MEkiBcmzbhPbDaseF.sq5MQFnAscmPewu3qdkCBXTrY3VRi', 'ako', '', NULL),
(13, 'tsubasa', '$2a$10$5k5q3iUoUulQ5Xn955m4Z.DMxRjRE.KEzcgSca.AqVV7JiLtm/KOi', 'captain.tsubasa@gmail.com', 'http://res.cloudinary.com/bounswe2015group8/image/upload/v1450748855/wnsqmesgoyh98qvgv07n.jpg', 'The best football player in the whole world, who will win the World Cup one day. But I am not an Alex'),
(15, 'basaktugce', '$2a$10$W0uwtVym3RrTNWMbLYtu8ueEFfXRqJ2tpx8PQTPUbOcu.9phQKuNS', 'basaktugceeskili@gmail.com', 'http://res.cloudinary.com/bounswe2015group8/image/upload/v1450772868/wtf3ockv9hbo34dwpo8m.jpg', 'Bogazici University, CmpE. Traveler junkie ^.^'),
(16, 'doruk', '$2a$10$WlESTVrxYjKKoa/Fw0dVtOCfV5B9FrxEOMNuG0by5qX5klmsNIjDi', 'doruk.kilitcioglu@boun.edu.tr', '', NULL),
(17, 'Beeflover', '$2a$10$1OOXMw3qQl78Ao5MK.EjD.y7nPZC/7WiMyGO8d0CiScBLFnrxy07i', 'beef@beef.com', '', NULL),
(18, 'bugrahan', '$2a$10$jVTcUDV2BtGxpvfe4iTeNOJvzhPeFzh0Hr5sbozGAejCQ749k/XDK', 'bugrahanmemis@gmail.com', '', NULL),
(19, 'ali', '$2a$10$y32JudqKE.IcoUSXBfvhh.DqZo2i4Ynhq7XEV92r2LF0./kHD7sW6', 'muhammedarslanbenzer@gmail.com', 'http://res.cloudinary.com/bounswe2015group8/image/upload/v1450786856/ihmj9dtcgq4hhazkhejb.jpg', 'Sana bir karsilik verecegim/\nTopragi desen boguk sesimle'),
(20, 'sj', '$2a$10$V5TtFFCWfPiRf3ullFo.L.W5mms6n0WkuZ6NjegfVDih1oZ8.0gpy', '', '', NULL),
(21, 'bugrahanmemis', '$2a$10$ahHKnY8Y//dTSBgriJJGRulG3VagfFRdgAIRns4EH.r1lJDcV5KJe', 'bugrahanmemis@gmail.com', 'https://res.cloudinary.com/bounswe2015group8/image/upload/v1450787865/ztkglterax7tp1i1zw3h.jpg', NULL),
(22, 'mali', '$2a$10$liU7FBiw/SyJXoW.fOBUMurA22pLD7Tpmm.7B5ftTxLSvJoDjAtgq', 'muhammed_ali_36@hotmail.comh', 'http://pixdaus.com/files/items/pics/4/68/62468_19b1dc0e4b7806b0df81e340fcd2c1b6_large.jpg', NULL),
(23, 'goktugerce', '$2a$10$GweWRwsW8sV2GPdoN1kDmerseeVdbVL/v.2kN74izGzX7UDXuh4I2', 'goktugercegurel@gmail.com', '', NULL),
(24, 'meryem90', '$2a$10$R63IcoZXwnHbP9aGinqDueYj0cePsloLYMaOZgYSD/zZLcX5iicg6', 'meryem@miryem.com', '', NULL),
(25, 'mrtysn', '$2a$10$Rwd.1Swd8ln/1Re5nAMx3O4gqCXvDSoQLQFV3Vn7VPGNOy1p0lCXi', 'mert.yasin@gmail.com', '', NULL),
(26, 'luka', '$2a$10$a3jNAmVQvkY6dv1KUNj1feQ0gLJSqt7yDniZWn/LogemrWWUHJrUK', 'luka@gmail.com', '', 'Hi everyone ! '),
(27, 'peterparker', '$2a$10$zge.ZCWlUM1odg6WdBrft.BiRNMy6EuCN6mZ6g0c.ia4XT0NelF4S', 'spiderman@marvel.com', '', NULL),
(28, 'gokcantali', '$2a$10$l560QcI5rDa93SYVUzZM1uAQQQyUYKwMUXH.OfgJz/YJD1iJttxo.', 'gokcantali@cancan.com', '', ''),
(29, 'poohdini', '$2a$10$Ew8wvJQxVGdv850cqliAl.Vs67rk3i4P5v4LVZwRirKn.BzTqY4WK', 'poohdini@jr.com', 'http://res.cloudinary.com/bounswe2015group8/image/upload/v1451722078/rkyd9ugorj3lj7f3dpng.png', ''),
(30, 'ayhan', '$2a$10$awtSmpwt/TnzOOpvLn7tfOQV5v5aB9ov3Dj9LZcbZu0QPVfwHQ0Eq', 'loi@gmail.com', '', ''),
(31, 'uskudarli', '$2a$10$uYUTgqdXlDlYl1cqIKdzqOiEdyojry3geNlrQwyutGTjDzGSNMwv.', 'suzan.uskudarli@boun.edu.tr', '', ''),
(32, 'atakanarikan', '$2a$10$xANOCR1Kjry0gz476NrUXueR1.VJl01Loe8Jawzh9uAkenq01DVlS', 'marqhen@gmail.com', '', ''),
(33, 'cantali', '$2a$10$hmjQINUL/CM7pkZ8nryvBehWQjBOXNAlDSOrl26FI5MxvlPpc9LHS', 'cancan@cantali.tali', '', ''),
(34, 'bombaci', '$2a$10$TFyfeq2rStMg0JthvI.yvOiXLXX0JR6lT0.0rcDzVQwboYLpK/qRu', 'bombaci@bombaci', '', ''),
(35, 'salman', '$2a$10$VTeIewaUIG0peku68k9Wze0CJCXhe6KKVaONCKNzCwmay/WSF9I6q', 'berk.salmanoglu@hotmail.com', '', ''),
(36, 'siray', '$2a$10$E2fFmz4wDapp3QO4GKn48.FqTsNvwTmZYix3Hr4CqIANhmJmqD4Aa', 'ocak.siray@gmail.com', 'http://res.cloudinary.com/bounswe2015group8/image/upload/v1452179316/vmew1wmbyxpaywdaswev.jpg', '\n'),
(37, 'müs', '$2a$10$MaiJCQ6s408ld2EyCmbMv.YzauPA1GlQ21v0FBkRy9rKt6B.d0sgC', 'do@rambler.ru', '', ''),
(38, 'mertyasin', '$2a$10$l.McahBUmi/kMStCWq7wIePfBo/AWrZC1RY4IW1SfvbshkedEFx4e', 'mert.yasin@gmail.com', '', ''),
(39, 'nikopolidis', '$2a$10$Z/huIKSZrujvyM9bMaP9r.zpxIbk/n0qB8058wuwoHHnONm1XjEym', 'nikopolidis@gmail.com', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `POST`
--

CREATE TABLE IF NOT EXISTS `POST` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `OWNER` bigint(20) NOT NULL,
  `TYPE` int(11) NOT NULL,
  `POST_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `LAST_EDITED_DATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `TITLE` varchar(128) DEFAULT NULL,
  `CONTENT` text NOT NULL,
  `PLACE` text,
  PRIMARY KEY (`ID`),
  KEY `OWNER` (`OWNER`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=52 ;

--
-- Dumping data for table `POST`
--

INSERT INTO `POST` (`ID`, `OWNER`, `TYPE`, `POST_DATE`, `LAST_EDITED_DATE`, `TITLE`, `CONTENT`, `PLACE`) VALUES
(6, 15, 0, '2015-11-17 02:49:45', '2015-11-30 22:11:11', 'Halloween Misunderstanding', 'Halloween was a few days ago.  I know that some other countries celebrate Halloween, but many do not.  Even though Halloween is portrayed in American movies, many exchange students and other foreign visitors are not fully aware of the way we celebrate this holiday.  This year I heard perhaps the funniest misunderstanding of Halloween traditions by an exchange student.\r\n\r\nA friend of mine is hosting a young lady from China this year.  The host family explained to their student the concept of "trick-or-treating" and told the Chinese girl that she could answer the door and hand out candy to the kids that come to the house.  Everyone thought that she understood what to do, but having never heard of Halloween or "trick-or-treating" the Chinese girl was still confused on the concept.  When the first little boy in costume came to the door, said "trick or treat" and opened his bag of candy, the Chinese girl reached into his bag, grabbed a piece of candy and said "thank you!"  The little boy was so confused he just left.\r\n', NULL),
(7, 13, 0, '2015-11-17 02:58:01', '2015-11-17 02:58:02', 'The History of the Ceremony', 'In the middle of the 16th century the first Westerners, the Jesuits, arrived in Japan, and at that same time a Japanese man named Rikyu was developing a new approach to the ancient practice of serving tea with some food. It did not take long for the Jesuits to discover and develop an admiration for tea practices and to incorporate them into their everyday life in Japan. But the relationship between western civilization and the tea ceremony came to an abrupt halt when Tokugawa Ieyasu, the Shogun, forced Westerners out of Japan and shut the doors on them for almost 300 years. Although the doors re-opened in 1868, it took almost 100 years for Westerners to develop an interest in the tea ceremony to the extent that they would begin to practice it and not simply view it as a quaint, inscrutable custom of the Japanese', NULL),
(8, 16, 0, '2015-11-17 12:21:13', '2016-01-06 12:41:20', 'The sound that enriches the Sydney Harbour ', 'I saw this instrument being played at Sydney Harbour, by two aborginal people aiming to continue their musical traditions that has been going on for over a thousand years. It has a rich, electronic-esque sound; and you can hear how tribal and raw it sounds. I recommend everyone who has the chance to go to Australia to hear this instrument being played, for it is a show better heard live.', 'New South Wales, Australia'),
(9, 8, 0, '2015-11-17 17:50:26', '2015-11-17 17:50:26', 'My experience', 'I had the chance to witness this firsthand on my visit to Japan. It is fascinating to see how fast they have embraced tea.', NULL),
(25, 15, 0, '2015-12-08 03:39:42', '2015-12-08 03:39:42', 'Jingle Bells', 'When I was a child, I used to listen to this song a lot whenever Christmas arrived. I don''t know the reason, but this song always managed to put a smile on my face, even when I was really upset.', 'New York, U.S'),
(26, 24, 0, '2015-12-08 04:06:16', '2015-12-08 04:06:16', 'My Friend''s Henna Night', 'This is a rather modern Henna Night of my best friend (the groom) that took place 2 night prior to the wedding.', 'Istanbul, Turkey'),
(27, 13, 0, '2015-12-08 04:10:18', '2015-12-08 04:10:18', 'The Gold Wrapping Paper - An Inspirational Short Christmas Story', 'Once upon a time, there was a man who worked very hard just to keep food on the table for his family. This particular year a few days before Christmas, he punished his little five-year-old daughter after learning that she had used up the family''s only roll of expensive gold wrapping paper.\r\n\r\nAs money was tight, he became even more upset when on Christmas Eve he saw that the child had used all of the expensive gold paper to decorate one shoebox she had put under the Christmas tree. He also was concerned about where she had gotten money to buy what was in the shoebox.\r\n\r\nNevertheless, the next morning the little girl, filled with excitement, brought the gift box to her father and said, "This is for you, Daddy!"\r\n\r\nAs he opened the box, the father was embarrassed by his earlier overreaction, now regretting how he had punished her.\r\n\r\nBut when he opened the shoebox, he found it was empty and again his anger flared. "Don''t you know, young lady," he said harshly, "when you give someone a present, there''s supposed to be something inside the package!"\r\n\r\nThe little girl looked up at him with sad tears rolling from her eyes and whispered: "Daddy, it''s not empty. I blew kisses into it until it was all full."\r\n\r\nThe father was crushed. He fell on his knees and put his arms around his precious little girl. He begged her to forgive him for his unnecessary anger.\r\n\r\nAn accident took the life of the child only a short time later. It is told that the father kept this little gold box by his bed for all the years of his life. Whenever he was discouraged or faced difficult problems, he would open the box, take out an\r\n\r\nimaginary kiss, and remember the love of this beautiful child who had put it there.\r\n\r\nIn a very real sense, each of us has been given an invisible golden box filled with unconditional love and kisses from our children, family, friends and God. There is no more precious possession anyone could hold.', 'Unknown'),
(28, 23, 0, '2015-12-08 08:40:44', '2015-12-08 08:40:44', 'Sinterklaas', 'Sinterklaas or Sint-Nicolaas is a mythical figure with legendary, historical and folkloric origins based on Saint Nicholas. Other names for the figure include De Sint ("The Saint"), De Goedheiligman ("The Good Holy Man"), and De Goede Sint ("The Good Saint") in Dutch; Saint-Nicolas in French; Sinteklaas in Frisian; and Kleeschen and Zinniklos in Luxembourgish.\r\n\r\nSinterklaas is celebrated annually with the giving of gifts on 5 December, the night before Saint Nicholas Day in the Northern Netherlands and on the morning of 6 December, Saint Nicholas Day itself, in the (Roman Catholic) southern provinces, Belgium, Luxembourg and Northern France (French Flanders, Lorraine and Artois). He is also well known in territories of the former Dutch Empire, including Aruba, Bonaire and Curacao.', 'Utrecht, Netherlands'),
(29, 13, 0, '2015-12-08 12:49:31', '2015-12-08 12:49:31', 'Good old days', 'Ishizaki and I used to play football using snowballs in Christmas. It was hard to form a snowball as hard as to play football, but it was totally \r\n\r\nworth it.', 'Tokyo, Japan'),
(30, 15, 0, '2015-12-21 21:22:24', '2015-12-21 21:22:24', 'Visiting Louvre', 'Last month I visited Louvre Museum in Paris, and it was amazing. But I think, they advertise Mona Lisa painting too much. If you go there, you will see this face everywhere, literally everywhere. Because it is on all souvenirs that you would like to take home, such as magnets, dayplanners, posters, books etc. And on each map it is specifically marked as if everyone wants to see it first. Of course I admire Leonardo''s artwork, but there is not only Mona Lisa, there are a lot of paintings, for me, better than Mona Lisa. Just an opinion.\r\n\r\n', 'Louvre Museum'),
(31, 15, 0, '2015-12-21 21:39:39', '2015-12-21 21:39:39', 'Not only a pizza but also a dessert !', 'There is a restaurant in Rome which is called Dar Poeta, it is very close to Trastevere. So, eventhough Calzone is known as a meal, they have Calzone with nutella and ricotta on the menu as a dessert. It is only made here, so I highly suggest you to go and taste this wonderful food.', 'Dar Poeta'),
(32, 15, 0, '2015-12-21 21:46:07', '2015-12-21 21:46:07', 'Hot Air Balloons', 'Here is the photo I took from one of these beautiful balloons. It is an amazing adventure, do not ever skip doing this if you have a chance to visit Cappadocia !', 'Cappadoccia'),
(33, 31, 0, '2016-01-05 22:51:56', '2016-01-05 22:51:56', 'Searching for the Anastasian Wall ', '', 'Yalikoy'),
(34, 31, 0, '2016-01-05 23:11:16', '2016-01-05 23:20:39', 'Searching for the Anastasian Wall', 'I love ancient ruins and had been curious about the so-called Anastasian Wall remains in Trakya (Thrace) [OOPS, Cant put the location... locations are very important for cultural artifacts].\r\nI went with a friend for an entire day and search high and low for this wall. I stopped at traditional coffee houses (kiraathane or kahve) and spoke with locals (all men, of course) about the wall. They did not know what I was talking about at first. Then they realized it was the walls they used to play on as kids, which they called "Kale" as in fort. \r\n\r\nSeems there were much more of the wall remains not so long ago. Alas, in the recent decades people have taken the large stones that made up the wall and used them to build houses and walls and mosques and what not. The villagers don''t like these stones anymore. They want to get rid of them and use concrete instead!!! Those beautiful stones... Hard to imagine. Anyway, how can you get rid of huge stones. To make matters worse, these stones are declared as historically valuable and they can not be destroyed. So, they are grumbling.\r\n\r\nBack to basics...\r\n\r\nThe Anastasian Wall stretched from Yalikoy (Geolocation of Yalikoy) to Silivri (Geolocation of Silivri) essentially a wall from the Black Sea to the Marmara Sea to keep invaders out.\r\n\r\nRelated material: http://anastasianwall.blogspot.com.tr/ [How can I relate material and stories to each other?]\r\n\r\nMy video: <iframe width="420" height="315" src="https://www.youtube.com/embed/7Q2R5-1b_Ec" frameborder="0" allowfullscreen></iframe>\r\n\r\nMy Pictures:\r\n\r\nI have many picture I want to add..', 'Yalikoy to Silivri, Trakya, Turkey'),
(35, 22, 0, '2016-01-06 23:41:04', '2016-01-06 17:02:13', 'Cirit in Kars', 'Last year I watched a cirit game in Kars. That was epic and I felt in medieval times where horses are still used in battles.', 'Kars, Turkiye'),
(36, 22, 0, '2016-01-06 23:41:38', '2016-01-06 17:18:15', 'Goose meat is the most delicious meat', 'In our village kaz are herded by all families. I have never eated someting even close to goose meat.', 'Kars-Karakale'),
(37, 16, 0, '2016-01-06 17:39:06', '2016-01-06 17:39:06', 'Second Mona Lisa', 'I have found an article that questions whether Leonardo''s painting subject is real Lisa Gherardini or not. Check it out:\n\nhttp://www.telegraph.co.uk/art/artists/mona-lisa-discovery-scientist-claims-secret-second-portrait-of-r/', NULL),
(38, 31, 0, '2016-01-06 23:40:49', '2016-01-06 18:10:29', 'The memory of a cup of coffee lasts for 40 years -- the folk story', 'So, the coffee story...\r\n\r\nIt seems there was a man named Iskender Bilge who sold and made coffee. He was actually a coffee seller, but people of all walks of life would come there to have coffee, visit, and get some advice at times. His coffee was known with the bitter taste. I guess you could call it full flavored.\r\n\r\nOne day a infidel came to his shop. It was full of people. Among all the Turks there was a Greek Captain man  in the corner smoking  a water pipe.\r\n\r\nThe janissary announced that he was offering coffee to all the wise, kind, insightful persons, with the exception of the infidel in the corner. The captain sighed and went on smoking his pipe.\r\n\r\nThe wise owner of the shop made coffee as asked. After serving the coffee  he made a couple of more cups and went to sit and have some coffee with the captain.\r\n\r\nThe janissary exclaimed, "Hey! Didn''t I say except the infidel?"\r\n\r\nThe wise man said this is my offering not yours. He went on visiting with the Captain for a good long time. The Captain was so impressed that he entered this event in his journal.\r\n\r\nYears go by... 40 years to be exact. There are Greek uprisings in Samos island. The Wise man, being included in the janissaries is stationed in Samos and is captured during the uprising. The captured slaves are sold to gain some income and most are killed. An old man pays a good price for our wise coffee seller puts him in his service. He takes him to a secluded area. The Captain remembers the coffee seller, but the wise man does not remember the captain.\r\n\r\nThe captain says: Oh wise man, the coffee you have offered me  has created a bond that has lasted for 40 years. Even if you have forgotten, I have not. You are a free man. However, if you wish I can take you to your homeland safely.', 'Nevsehir, Turkey'),
(39, 15, 0, '2016-01-06 18:53:31', '2016-01-06 18:53:31', 'A perfect day', 'Ece is my best friend and she lives in Denizli. If you meet her, the first thing that she does will be taking you to Pamukkale. It is like really a cotton castle, the water is warm and you are allowed to paddle. \r\n\r\nSay hi to my bestile :)', ''),
(40, 33, 0, '2016-01-07 02:50:12', '2016-01-07 02:50:12', 'Kulintang', 'When I was in Florence for my conference, I saw a guy while walking down the street. I stopped for a minute to see what he was doing. I am not gonna lie, at first I thought he was playing with drums. But once I got closer, I realized that the music sound was a lot lighter than drums could have ever produced. I couldn''t resist to watch the whole thing as the sound simply calmed all my nerves. After he is done, I talked to him about the instrument. He told me that the instrument which he had been playing was called "Kulintang" and it was a part of Philippine indigenous music. From now on, I intend to learn more about their music tastes. ', 'Florence, Italy'),
(41, 15, 0, '2016-01-07 07:01:30', '2016-01-07 07:01:30', 'Even his pets were works of art', 'In the Sixties Dali got a pet ocelot called Babou, which accompanied him on a leash and a studded collar nearly everywhere he went , including, famously, in a restaurant in Manhattan. When a fellow diner became alarmed, he calmly told her that Babou was a norma cat that he had "painted over in an op art design".', ''),
(42, 36, 0, '2016-01-07 07:42:47', '2016-01-07 07:45:39', 'Angkor', 'When I arrived in Cambodia, fellow travelers were eager to offer me advice and to share their temple experiences. The guidebooks I had read suggested walking through the temples in the morning to avoid afternoon heat. This would take a minimum of five days. Temple veterans were quick to dismiss this strategy and advised me to rent a bicycle in Seam Reap. I could see all the temples in a couple of days and the cost would be minimal. Another advisor believed a bike would be too strenuous in the heat of the Cambodian plains. A tuk-tuk could be hired for the entire day for $20. Another option was a bus tour, offered at most area hotels for a similar price.The easiest way, if you are with a two or three other visitors, is to hire a car and driver. In Phnom Penh I found a driver to take my two friends and me the five-hour ride to Siem Reap, and then all the next day drive us around the temples; all for $130. Split three ways, it was an unbeatable deal and a great convenience', 'Cambodia'),
(43, 15, 0, '2016-01-07 07:48:49', '2016-01-07 07:48:49', 'The Singing Fish', 'My favorite painting of him !', ''),
(44, 36, 0, '2016-01-07 07:50:00', '2016-01-07 07:50:00', 'Tuk-tuk', 'I used tuk-tuk s mostly while visiting city. Tuk-tuks can be different in different areas but the main function is same, cheap transportation.In Siem Reap and at the temple complex of Angkor, passenger tuk-tuks are generally the remorque type, comprising a motorcycle and articulated passenger trailer. The trailers are usually brakeless. Remorques are a popular form of transport for tourists, and can be hired, together with the driver, who may also offer his services as a guide, by the day.Remorques are also common in Sihanoukville. In Phnom Penh tuk-tuks are generally one piece, with a front end like or taken from a motorcycle and consisting of steering, fuel tank, and engine and gearbox. Power is transferred by chain to the rear axle which drives the two rear wheels. At the rear is an open cabin with an in-line seat on each side. Some can carry six people with ease, with additional cargo in the leg space. It is not unusual to see these vehicles greatly overloaded, especially around markets.', 'Cambodia'),
(45, 36, 0, '2016-01-07 07:54:43', '2016-01-07 07:54:43', 'Buldan Clothing', 'Buldan was famed for a thin handwoven cheesecloth-type fabric, with laced edges and used chiefly for bed covers and table cloths, called as "Buldan bezi" (Buldan clothes) under the name of locality. Already back in the 19th century, the townspeople wove 40,000 pieces of all-cotton colored striped cloth used called alaca used for attires and a similar number of cotton and mattress clothes. Buldan weavers also produced over one-half million handkerchiefs and a large number of cotton curtains. Another textile from Buldan that deserves mention is a vivid violet silk (pestemal) woven as a rectangular panel to be wrapped around the body. Yet another is kaplama, colorful head coverings typical of Turkey''s Aegean Region and worn by men and women alike with different colors associated with each gender and various regions. Thanks to sizable production effort, the number of looms in Buldan had risen to 1,500 by the end of the 19th century.', 'Turkey'),
(46, 29, 0, '2016-01-07 09:36:44', '2016-01-07 09:00:42', 'Debreli Hasan', 'I know a legend and a song about this place. The song is based on the legend of Debreli Hasan (Hasan from Debre), who lived at the end of the 19th century and the beginning of the 20th century. He was a soldier who killed his superior after a quarrel and was jailed in Drama. He escaped from the prison to hide in the mountains where he lived as a bandit. He was more or less like Robin Hood of English folklore. He robbed the rich and supported the poor. Before becoming a bandit he had been engaged to be married. Judging that life in the mountains would be too difficult for his fiance, he broke his engagement, but continued to send her presents even when she married another man. According to the legend, he was later pardoned by the sultan.\r\n\r\nHere is the translation of the song''s lyrics:\r\n\r\nDrama bridge is narrow O Hasan, it can''t be crossed,\r\nIts water is so cold Hasan that it can''t be drunk.\r\nShoot your "Martini" Debreli Hasan, let the mountains groan,\r\nLet the friends in Drama prison listen (to the sound).', ''),
(47, 39, 0, '2016-01-07 09:12:02', '2016-01-07 09:12:02', 'Stergios pismanipsi', 'Hey, I also know a thing or two about this place. In fact, I live in Drama! We also have song about this place. Its name is Stergios Pismanipsi. I am adding the song so you can listen to its Greek version too!', ''),
(48, 39, 0, '2016-01-07 09:30:19', '2016-01-07 09:30:19', 'View from Samuel Fortress', 'I went Samuil''s Fortress this fall with my family and it just has a stunning view from the top. It was rainy and when I looked at the city, I saw this awesome view of Lake Ohrid. Here is a photo I took!', 'Ohrid, Makedonya (FYROM)'),
(49, 35, 0, '2016-01-07 10:02:32', '2016-01-07 11:36:45', 'Ali Ekber Cicek', 'Ali Ekber Cicek was a Turkish musician who lived between 1935 and 2006. He was born in Erzincan, Turkey. His father died in an earthquake and his mother suffered financial problems which made it impossible for her to support his education. Nevertheless, he attenden Alevi gatherings called Cems, where he not only became familiar with playing baglama, but also with the philosophy of Alevis.\r\n\r\nHe performed on state-operated TRTradio broadcast services in Ankara, where he remained until retirement. TRT produced a documentary in 2003 about his life and his music. The documentary presents his work as part of the Anatolian folk tradition and was intended to broaden his influence.\r\n\r\nOne of his most noted songs is Haydar Haydar, which took him approximately seven years to master. It is said that Ali Ekber asked himself, with regard to this song, WHAT DID I DO! The song is often considered the pinncale of symphonic Turkish folk music.', ''),
(50, 21, 0, '2016-01-07 12:16:40', '2016-01-07 12:16:40', 'key chain', 'it''s a lovely story. one of my friend gave me manneken pis keychain as a gift. this was the first time i heard about the story. it think it''s a common gift :) and, i still use the keychain. ', 'belgium'),
(51, 21, 0, '2016-01-07 12:21:45', '2016-01-07 12:21:45', 'drama koprusu', 'this song is very popular in turkey. there are lots of version for this song. one of my fav is sang by can gox. here is the live record. https://www.youtube.com/watch?v=XNZKzbSA1t4', 'turkey');

-- --------------------------------------------------------

--
-- Table structure for table `POST_VOTE`
--

CREATE TABLE IF NOT EXISTS `POST_VOTE` (
  `MEMBER_ID` bigint(20) NOT NULL,
  `POST_ID` bigint(20) NOT NULL,
  `VOTE_TYPE` bit(1) NOT NULL,
  PRIMARY KEY (`MEMBER_ID`,`POST_ID`),
  KEY `POST_ID` (`POST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `POST_VOTE`
--

INSERT INTO `POST_VOTE` (`MEMBER_ID`, `POST_ID`, `VOTE_TYPE`) VALUES
(7, 6, ''),
(15, 8, ''),
(15, 29, ''),
(15, 34, ''),
(16, 6, ''),
(16, 7, ''),
(16, 8, ''),
(16, 9, ''),
(16, 33, '\0'),
(17, 8, '\0'),
(19, 6, '\0'),
(19, 7, '\0'),
(19, 8, ''),
(19, 9, '\0'),
(19, 25, '\0'),
(19, 26, ''),
(19, 27, '\0'),
(19, 28, ''),
(21, 9, ''),
(21, 31, ''),
(22, 35, ''),
(22, 36, ''),
(23, 27, ''),
(25, 8, ''),
(26, 32, ''),
(28, 32, ''),
(29, 27, ''),
(29, 28, ''),
(29, 46, ''),
(31, 35, ''),
(33, 46, ''),
(33, 47, ''),
(33, 51, ''),
(39, 46, '');

-- --------------------------------------------------------

--
-- Table structure for table `RESET_PASSWORD`
--

CREATE TABLE IF NOT EXISTS `RESET_PASSWORD` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `MEMBER_ID` bigint(20) NOT NULL,
  `TOKEN` text NOT NULL,
  `REQUESTED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  KEY `MEMBER_ID` (`MEMBER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `RESET_PASSWORD`
--


-- --------------------------------------------------------

--
-- Table structure for table `TAG`
--

CREATE TABLE IF NOT EXISTS `TAG` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `TAG_TEXT` varchar(1024) NOT NULL,
  `TAG_CONTEXT` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=46 ;

--
-- Dumping data for table `TAG`
--

INSERT INTO `TAG` (`ID`, `TAG_TEXT`, `TAG_CONTEXT`) VALUES
(1, 'christmas', 'holiday'),
(2, 'christmas', 'jesus'),
(3, 'henna night', 'wedding'),
(4, 'henna wedding', 'muslim'),
(5, 'santaclaus', 'santa'),
(6, 'santa', 'christmas'),
(7, 'halloween', 'holiday'),
(8, 'henna', 'wedding'),
(9, 'Zwarte piet', 'christmas'),
(10, 'turkey', 'wedding'),
(11, 'football', 'sport'),
(12, 'henna', 'turkey'),
(13, 'vacation', 'turkey'),
(14, 'vacation', 'holiday'),
(15, 'vacation', NULL),
(16, 'Cambodia', NULL),
(17, 'Angkor', NULL),
(18, 'Thom', NULL),
(19, 'Jayavarman', NULL),
(20, 'Khmer', NULL),
(21, 'capital', NULL),
(22, 'Temple', NULL),
(23, 'bicycle', NULL),
(24, 'tuk-tuk', NULL),
(25, 'beethoven', NULL),
(26, 'piano', NULL),
(27, 'classical', NULL),
(28, 'music', NULL),
(29, 'statue', NULL),
(30, 'brussels', NULL),
(31, 'belgium', NULL),
(32, 'legend', NULL),
(33, 'missingboy', NULL),
(34, 'fire', NULL),
(35, 'folk ', 'ottoman'),
(36, 'architecture ', 'ottoman'),
(37, 'folk ', 'greece'),
(38, 'architecture ', 'macedonia'),
(39, 'baglama', 'instrument'),
(40, 'ashik', 'bard'),
(41, 'cirit', NULL),
(42, 'artists', NULL),
(43, 'painting', NULL),
(44, 'surrealist', NULL),
(45, 'fareast', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `TAG_HERITAGE`
--

CREATE TABLE IF NOT EXISTS `TAG_HERITAGE` (
  `TAG_ID` bigint(20) NOT NULL,
  `HERITAGE_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`TAG_ID`,`HERITAGE_ID`),
  KEY `HERITAGE_ID` (`HERITAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `TAG_HERITAGE`
--

INSERT INTO `TAG_HERITAGE` (`TAG_ID`, `HERITAGE_ID`) VALUES
(7, 10),
(14, 10),
(1, 17),
(2, 17),
(3, 18),
(4, 18),
(6, 19),
(9, 19),
(42, 31),
(43, 31),
(44, 31),
(16, 32),
(17, 32),
(18, 32),
(19, 32),
(20, 32),
(21, 32),
(25, 34),
(26, 34),
(27, 34),
(28, 34),
(29, 35),
(30, 35),
(31, 35),
(32, 35),
(33, 35),
(34, 35),
(35, 36),
(36, 36),
(38, 37),
(39, 38),
(40, 38);

-- --------------------------------------------------------

--
-- Table structure for table `TAG_POST`
--

CREATE TABLE IF NOT EXISTS `TAG_POST` (
  `TAG_ID` bigint(20) NOT NULL,
  `POST_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`TAG_ID`,`POST_ID`),
  KEY `POST_ID` (`POST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `TAG_POST`
--

INSERT INTO `TAG_POST` (`TAG_ID`, `POST_ID`) VALUES
(8, 26),
(10, 26),
(12, 26),
(1, 28),
(5, 28),
(6, 28),
(13, 32),
(14, 32),
(41, 35),
(17, 42),
(22, 42),
(23, 42),
(24, 42),
(35, 46),
(37, 47);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `COMMENT`
--
ALTER TABLE `COMMENT`
  ADD CONSTRAINT `COMMENT_ibfk_1` FOREIGN KEY (`OWNER_ID`) REFERENCES `MEMBER` (`ID`),
  ADD CONSTRAINT `COMMENT_ibfk_2` FOREIGN KEY (`POST_ID`) REFERENCES `POST` (`ID`);

--
-- Constraints for table `COMMENT_VOTE`
--
ALTER TABLE `COMMENT_VOTE`
  ADD CONSTRAINT `COMMENT_VOTE_ibfk_1` FOREIGN KEY (`MEMBER_ID`) REFERENCES `MEMBER` (`ID`),
  ADD CONSTRAINT `COMMENT_VOTE_ibfk_2` FOREIGN KEY (`COMMENT_ID`) REFERENCES `COMMENT` (`ID`);

--
-- Constraints for table `FOLLOW`
--
ALTER TABLE `FOLLOW`
  ADD CONSTRAINT `FOLLOW_ibfk_1` FOREIGN KEY (`FOLLOWER_ID`) REFERENCES `MEMBER` (`ID`),
  ADD CONSTRAINT `FOLLOW_ibfk_2` FOREIGN KEY (`FOLLOWEE_ID`) REFERENCES `MEMBER` (`ID`);

--
-- Constraints for table `FOLLOW_HERITAGE`
--
ALTER TABLE `FOLLOW_HERITAGE`
  ADD CONSTRAINT `FOLLOW_HERITAGE_ibfk_1` FOREIGN KEY (`FOLLOWER_ID`) REFERENCES `MEMBER` (`ID`),
  ADD CONSTRAINT `FOLLOW_HERITAGE_ibfk_2` FOREIGN KEY (`HERITAGE_ID`) REFERENCES `HERITAGE` (`ID`);

--
-- Constraints for table `FOLLOW_TAG`
--
ALTER TABLE `FOLLOW_TAG`
  ADD CONSTRAINT `FOLLOW_TAG_ibfk_1` FOREIGN KEY (`FOLLOWER_ID`) REFERENCES `MEMBER` (`ID`),
  ADD CONSTRAINT `FOLLOW_TAG_ibfk_2` FOREIGN KEY (`TAG_ID`) REFERENCES `TAG` (`ID`);

--
-- Constraints for table `GAMIFICATION`
--
ALTER TABLE `GAMIFICATION`
  ADD CONSTRAINT `GAMIFICATION_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `MEMBER` (`ID`);

--
-- Constraints for table `HERITAGE_POST`
--
ALTER TABLE `HERITAGE_POST`
  ADD CONSTRAINT `HERITAGE_POST_ibfk_1` FOREIGN KEY (`HERITAGE_ID`) REFERENCES `HERITAGE` (`ID`),
  ADD CONSTRAINT `HERITAGE_POST_ibfk_2` FOREIGN KEY (`POST_ID`) REFERENCES `POST` (`ID`);

--
-- Constraints for table `POST`
--
ALTER TABLE `POST`
  ADD CONSTRAINT `POST_ibfk_1` FOREIGN KEY (`OWNER`) REFERENCES `MEMBER` (`ID`);

--
-- Constraints for table `POST_VOTE`
--
ALTER TABLE `POST_VOTE`
  ADD CONSTRAINT `POST_VOTE_ibfk_1` FOREIGN KEY (`MEMBER_ID`) REFERENCES `MEMBER` (`ID`),
  ADD CONSTRAINT `POST_VOTE_ibfk_2` FOREIGN KEY (`POST_ID`) REFERENCES `POST` (`ID`);

--
-- Constraints for table `RESET_PASSWORD`
--
ALTER TABLE `RESET_PASSWORD`
  ADD CONSTRAINT `RESET_PASSWORD_ibfk_1` FOREIGN KEY (`MEMBER_ID`) REFERENCES `MEMBER` (`ID`);

--
-- Constraints for table `TAG_HERITAGE`
--
ALTER TABLE `TAG_HERITAGE`
  ADD CONSTRAINT `TAG_HERITAGE_ibfk_1` FOREIGN KEY (`TAG_ID`) REFERENCES `TAG` (`ID`),
  ADD CONSTRAINT `TAG_HERITAGE_ibfk_2` FOREIGN KEY (`HERITAGE_ID`) REFERENCES `HERITAGE` (`ID`);

--
-- Constraints for table `TAG_POST`
--
ALTER TABLE `TAG_POST`
  ADD CONSTRAINT `TAG_POST_ibfk_1` FOREIGN KEY (`TAG_ID`) REFERENCES `TAG` (`ID`),
  ADD CONSTRAINT `TAG_POST_ibfk_2` FOREIGN KEY (`POST_ID`) REFERENCES `POST` (`ID`);
