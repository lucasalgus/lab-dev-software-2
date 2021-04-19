-- phpMyAdmin SQL Dump
-- version 4.9.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Apr 19, 2021 at 02:25 AM
-- Server version: 5.7.32
-- PHP Version: 7.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `aluguel_automoveis`
--

-- --------------------------------------------------------

--
-- Table structure for table `administrador`
--

CREATE TABLE `administrador` (
  `id` int(10) UNSIGNED NOT NULL,
  `usuario_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `agente`
--

CREATE TABLE `agente` (
  `id` int(10) UNSIGNED NOT NULL,
  `cnpj` varchar(80) NOT NULL,
  `usuario_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `aluguel`
--

CREATE TABLE `aluguel` (
  `id` int(10) UNSIGNED NOT NULL,
  `contrato` varchar(80) NOT NULL,
  `ativo` tinyint(4) NOT NULL,
  `data_inicio` datetime NOT NULL,
  `data_fim` datetime NOT NULL,
  `automovel_id` int(10) UNSIGNED NOT NULL,
  `cliente_id` int(10) UNSIGNED NOT NULL,
  `agente_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `automovel`
--

CREATE TABLE `automovel` (
  `id` int(10) UNSIGNED NOT NULL,
  `ano` int(10) UNSIGNED NOT NULL,
  `marca` varchar(80) NOT NULL,
  `modelo` varchar(80) NOT NULL,
  `placa` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `cliente`
--

CREATE TABLE `cliente` (
  `id` int(10) UNSIGNED NOT NULL,
  `rg` varchar(80) NOT NULL,
  `cpf` varchar(80) NOT NULL,
  `profissao` varchar(80) NOT NULL,
  `usuario_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `empregador`
--

CREATE TABLE `empregador` (
  `id` int(10) UNSIGNED NOT NULL,
  `nome` varchar(80) NOT NULL,
  `cliente_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `rendimento`
--

CREATE TABLE `rendimento` (
  `id` int(10) UNSIGNED NOT NULL,
  `valor` double UNSIGNED NOT NULL,
  `cliente_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

CREATE TABLE `usuario` (
  `id` int(10) UNSIGNED NOT NULL,
  `endereco` varchar(80) NOT NULL,
  `nome` varchar(80) NOT NULL,
  `email` varchar(80) NOT NULL,
  `senha` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `administrador`
--
ALTER TABLE `administrador`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usuario_id` (`usuario_id`);

--
-- Indexes for table `agente`
--
ALTER TABLE `agente`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usuario_id` (`usuario_id`);

--
-- Indexes for table `aluguel`
--
ALTER TABLE `aluguel`
  ADD PRIMARY KEY (`id`),
  ADD KEY `automovel_id` (`automovel_id`),
  ADD KEY `cliente_id` (`cliente_id`),
  ADD KEY `agente_id` (`agente_id`);

--
-- Indexes for table `automovel`
--
ALTER TABLE `automovel`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usuario_id` (`usuario_id`);

--
-- Indexes for table `empregador`
--
ALTER TABLE `empregador`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cliente_id` (`cliente_id`);

--
-- Indexes for table `rendimento`
--
ALTER TABLE `rendimento`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cliente_id` (`cliente_id`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `administrador`
--
ALTER TABLE `administrador`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `agente`
--
ALTER TABLE `agente`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `aluguel`
--
ALTER TABLE `aluguel`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `automovel`
--
ALTER TABLE `automovel`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `empregador`
--
ALTER TABLE `empregador`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `rendimento`
--
ALTER TABLE `rendimento`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `administrador`
--
ALTER TABLE `administrador`
  ADD CONSTRAINT `administrador_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`);

--
-- Constraints for table `agente`
--
ALTER TABLE `agente`
  ADD CONSTRAINT `agente_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`);

--
-- Constraints for table `aluguel`
--
ALTER TABLE `aluguel`
  ADD CONSTRAINT `aluguel_ibfk_1` FOREIGN KEY (`automovel_id`) REFERENCES `automovel` (`id`),
  ADD CONSTRAINT `aluguel_ibfk_2` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`),
  ADD CONSTRAINT `aluguel_ibfk_3` FOREIGN KEY (`agente_id`) REFERENCES `agente` (`id`);

--
-- Constraints for table `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`);

--
-- Constraints for table `empregador`
--
ALTER TABLE `empregador`
  ADD CONSTRAINT `empregador_ibfk_1` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`);

--
-- Constraints for table `rendimento`
--
ALTER TABLE `rendimento`
  ADD CONSTRAINT `rendimento_ibfk_1` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`);
