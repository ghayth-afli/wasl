-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: wasl
-- ------------------------------------------------------
-- Server version	8.1.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Admin','admin@mail.com','$2a$10$yPzwqf7rnnIwp/7DU8lfluET4f1xO7SgMwi.BUkzEeowCpectp3zK',NULL,'ADMIN'),(2,'Admin','admin@mail.com','$2a$10$REHd7VaJBPU6eUGBGH3YwO7Tw03nRi5Y0RQY/36UJCD1eNPwkeu62',NULL,'ADMIN'),(3,'Admin','admin@mail.com','$2a$10$HArpENLwl.MzBXf7WCNtuOQ2Hyflmkxb1V1qfP81shQurY.z2T1He',NULL,'ADMIN'),(4,'Admin','admin@mail.com','$2a$10$4CQ7SjfPmMKbHngkdQKLou8NOb12tu1.0Cogda9QVE8lwoAUy1agy',NULL,'ADMIN'),(5,'Admin','admin@mail.com','$2a$10$Aos8ZgFwzqThQ1kf8okOe.PAgtWnVkI5jKfDoOuUaGhSkuGYV8sk.',NULL,'ADMIN'),(6,'Admin','admin@mail.com','$2a$10$4ta4Uk3dDkQo5OMoVt77tOmqYQ9KvtOtDzcnymOi66W53zAFH5Be2',NULL,'ADMIN'),(7,'Regular','regular@mail.com','$2a$10$EEhfh2u43rVSu1YwzAOaWu7Ocju6JmLJ7sLFu5S0y18A.GhcLeWg.',NULL,'REGULAR'),(8,NULL,'admin@mail.com','$2a$10$HUxtQBXRr5pJUYgPcM7C5ekeNj70.0UWSPe/qL4/bi8VLtAVpK6w.',NULL,NULL),(9,'Admin','admin@mail.com','$2a$10$Ud8JRIwm/yXDeQ9u/yl7VO6gXi8ahCAn7V7uWccbMvvzVd0EByr0e',NULL,'ADMIN'),(10,'Regular','regular@mail.com','$2a$10$M1EwThvWpnR9x0X39/835.iDOhHrxzOAevVCzATCWR4a/wmkhHSWS',NULL,'REGULAR'),(11,'Admin','admin@mail.com','$2a$10$TdIdhED0sPaf8ncJ.7zKguwd9G.IaI8NR9DTeqkx6NnfERBY/9d7.',NULL,'ADMIN'),(12,'Regular','regular@mail.com','$2a$10$2e0oAfKIKGGberjykEU1BOt0vAlnn/x7eQMQa7FFsOx5EYAn/9se.',NULL,'REGULAR'),(13,'Admin','admin@mail.com','$2a$10$/9kgIWijgrjpFZgmsUXh6.MqE5k/EtxxPvq/RmOLfXKrGCp.1ABEu',NULL,'ADMIN'),(14,'Regular','regular@mail.com','$2a$10$FUWtwal9vS1kbFpCK.//WO5.amZc9BLDiSUjhFSnlzoLFNe1UKY7K',NULL,'REGULAR');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-10  5:51:11
