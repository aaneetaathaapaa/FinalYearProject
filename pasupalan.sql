-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 26, 2015 at 08:56 AM
-- Server version: 5.5.8
-- PHP Version: 5.3.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `ahis`
--

-- --------------------------------------------------------

--
-- Table structure for table `buffalo_general`
--

CREATE TABLE IF NOT EXISTS `buffalo_general` (
  `tag_Id` int(11) NOT NULL,
  `herd_Id` int(11) NOT NULL,
  `farmer_Id` int(11) NOT NULL,
  `Weight` varchar(255) NOT NULL,
  `Age` varchar(11) NOT NULL,
  `Birth_info` varchar(255) NOT NULL,
  PRIMARY KEY (`tag_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

--
-- Dumping data for table `buffalo_general`
--

INSERT INTO `buffalo_general` (`tag_Id`, `herd_Id`, `farmer_Id`, `Weight`, `Age`, `Birth_info`) VALUES
(77, 77, 77, '77', '77', '');

-- --------------------------------------------------------

--
-- Table structure for table `cow_animal_purchase`
--

CREATE TABLE IF NOT EXISTS `cow_animal_purchase` (
  `tag_id` int(11) NOT NULL,
  `seller_farmerid` int(11) NOT NULL,
  `buying_price` int(11) NOT NULL,
  `purchase_date` date NOT NULL,
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

--
-- Dumping data for table `cow_animal_purchase`
--

INSERT INTO `cow_animal_purchase` (`tag_id`, `seller_farmerid`, `buying_price`, `purchase_date`) VALUES
(0, 0, 0, '0000-00-00'),
(333, 333, 1210, '0000-00-00');

-- --------------------------------------------------------

--
-- Table structure for table `cow_animal_sales`
--

CREATE TABLE IF NOT EXISTS `cow_animal_sales` (
  `tag_id` int(11) NOT NULL,
  `buyer_farmerid` int(11) NOT NULL,
  `solddate` date NOT NULL,
  `selling_price` varchar(255) NOT NULL,
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

--
-- Dumping data for table `cow_animal_sales`
--

INSERT INTO `cow_animal_sales` (`tag_id`, `buyer_farmerid`, `solddate`, `selling_price`) VALUES
(0, 0, '0000-00-00', ''),
(22, 22, '2010-12-20', '22'),
(33, 45, '0000-00-00', '88'),
(44, 34, '2012-10-20', '4400');

-- --------------------------------------------------------

--
-- Table structure for table `cow_general`
--

CREATE TABLE IF NOT EXISTS `cow_general` (
  `tag_id` int(11) NOT NULL AUTO_INCREMENT,
  `herd_id` int(11) NOT NULL,
  `farmer_id` int(11) NOT NULL,
  `weight` varchar(255) NOT NULL,
  `age` varchar(255) NOT NULL,
  `birth_info` varchar(255) NOT NULL,
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf16 AUTO_INCREMENT=1235 ;

--
-- Dumping data for table `cow_general`
--

INSERT INTO `cow_general` (`tag_id`, `herd_id`, `farmer_id`, `weight`, `age`, `birth_info`) VALUES
(1, 1, 1, '1', '3', 'raised'),
(2, 1, 1, '1', '3', 'raised'),
(3, 1, 1, '1', '3', 'raised'),
(4, 400, 400, '355', '3', 'purchased'),
(12, 12, 12, '12', '12', 'Purchased'),
(13, 131, 3, '13', '13', 'Purchased'),
(45, 445, 45, '4', '45', 'Purchased'),
(55, 55, 55, '55', '55', 'Purchased'),
(56, 56, 56, '56', '56', 'Raised'),
(123, 123, 123, '13', '123', 'Raised'),
(1111, 11, 11, '111', '11', 'Purchased'),
(1234, 12, 1, '110', '2', 'Purchased');

-- --------------------------------------------------------

--
-- Table structure for table `cow_sales_product`
--

CREATE TABLE IF NOT EXISTS `cow_sales_product` (
  `tag_id` int(11) NOT NULL,
  `milkproduced` int(11) NOT NULL,
  `homeuse` int(11) NOT NULL,
  `soldamount` int(11) NOT NULL,
  `dailysale` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

--
-- Dumping data for table `cow_sales_product`
--

INSERT INTO `cow_sales_product` (`tag_id`, `milkproduced`, `homeuse`, `soldamount`, `dailysale`) VALUES
(34, 0, 0, 0, 0),
(322, 2333, 23, 34, 7800),
(0, 0, 0, 0, 0),
(111, 3500, 35, 455, 450);

-- --------------------------------------------------------

--
-- Table structure for table `cow_vaccination_record`
--

CREATE TABLE IF NOT EXISTS `cow_vaccination_record` (
  `tag_id` int(11) NOT NULL,
  `vac_date` date NOT NULL,
  `vac_amount` double NOT NULL,
  `vac_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

--
-- Dumping data for table `cow_vaccination_record`
--

INSERT INTO `cow_vaccination_record` (`tag_id`, `vac_date`, `vac_amount`, `vac_name`) VALUES
(11, '2012-10-20', 0, '4500'),
(22, '0000-00-00', 22, '22'),
(4, '2015-10-20', 3500, 'penicillin'),
(1001, '2014-02-02', 34555, 'penicillin');
