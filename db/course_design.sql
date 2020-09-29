/*
 Navicat Premium Data Transfer

 Source Server         : Bata's Link
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : course_design

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 29/09/2020 17:30:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `eid` int(10) NOT NULL AUTO_INCREMENT,
  `account_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `account_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `level` int(1) NOT NULL,
  PRIMARY KEY (`eid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (1, 'admin', '000', 0);
INSERT INTO `account` VALUES (2, '11823020415', '11823020415', 1);

-- ----------------------------
-- Table structure for city
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city`  (
  `eid` int(10) NOT NULL AUTO_INCREMENT,
  `city_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`eid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of city
-- ----------------------------
INSERT INTO `city` VALUES (1, '西宁');
INSERT INTO `city` VALUES (2, '兰州');
INSERT INTO `city` VALUES (3, '贵阳');
INSERT INTO `city` VALUES (4, '重庆');
INSERT INTO `city` VALUES (5, '北京');
INSERT INTO `city` VALUES (6, '郑州');
INSERT INTO `city` VALUES (7, '株洲');
INSERT INTO `city` VALUES (8, '南昌');
INSERT INTO `city` VALUES (9, '广州');
INSERT INTO `city` VALUES (10, '上海');
INSERT INTO `city` VALUES (11, '天津');

-- ----------------------------
-- Table structure for contacts
-- ----------------------------
DROP TABLE IF EXISTS `contacts`;
CREATE TABLE `contacts`  (
  `eid` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`eid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of contacts
-- ----------------------------
INSERT INTO `contacts` VALUES (3, '袁锋', '13364036195', '2273504357@qq.com');
INSERT INTO `contacts` VALUES (4, '伍文浩', '13996056795', '2210124897@qq.com');
INSERT INTO `contacts` VALUES (5, '易志钢', '13528476905', '2287039770@qq.com');
INSERT INTO `contacts` VALUES (6, '杨宗圆', '15736061685', '2263165311@qq.com');
INSERT INTO `contacts` VALUES (7, '杨能', '15826173195', '2280012389@qq.com');
INSERT INTO `contacts` VALUES (8, '曾新', '18290418915', '2232878351@qq.com');
INSERT INTO `contacts` VALUES (9, '李友', '13290010655', '2216218030@qq.com');
INSERT INTO `contacts` VALUES (10, '刘胡鑫', '17830819255', '2221030286@qq.com');
INSERT INTO `contacts` VALUES (11, '方家万', '17772301355', '2231867562@qq.com');
INSERT INTO `contacts` VALUES (12, '周明华', '15123462155', '2231561576@qq.com');
INSERT INTO `contacts` VALUES (13, '邱天', '15310668855', '2262245964@qq.com');
INSERT INTO `contacts` VALUES (14, '周江', '15023560855', '2271811238@qq.com');
INSERT INTO `contacts` VALUES (15, '舒波', '17784393355', '2267861200@qq.com');
INSERT INTO `contacts` VALUES (16, '王俊杰', '15025736155', '2202153331@qq.com');
INSERT INTO `contacts` VALUES (17, '张波浪', '15320650155', '2295852313@qq.com');
INSERT INTO `contacts` VALUES (18, '谭皓文', '18323703955', '2276327831@qq.com');
INSERT INTO `contacts` VALUES (19, '王松松', '15730111455', '2297889153@qq.com');
INSERT INTO `contacts` VALUES (20, '龚麒麟', '15823606555', '2222730138@qq.com');
INSERT INTO `contacts` VALUES (21, '褚浩然', '13833167855', '2254846208@qq.com');
INSERT INTO `contacts` VALUES (22, '王政', '18035297155', '2227439046@qq.com');
INSERT INTO `contacts` VALUES (23, '朱昊宇', '15730156155', '221301552@qq.com');
INSERT INTO `contacts` VALUES (24, '郑佳力', '15730113255', '22258031909@163.com');
INSERT INTO `contacts` VALUES (25, '陈昊天', '13101786855', '2256631453@qq.com');
INSERT INTO `contacts` VALUES (27, '禹瑾', '15213655855', '2223961719@qq.com');
INSERT INTO `contacts` VALUES (28, '隆婉洁', '18896123955', '2226436453@qq.com');
INSERT INTO `contacts` VALUES (29, '周春宇', '14780688855', '6553674523@qq.com');
INSERT INTO `contacts` VALUES (30, '杨倩', '18896123955', '122416436453@qq.com');
INSERT INTO `contacts` VALUES (31, '寸泽婉', '15183666455', '41513732372@qq.com');
INSERT INTO `contacts` VALUES (32, '刘美君', '15023816255', '2223107296@qq.com');
INSERT INTO `contacts` VALUES (33, '马军', '15500580655', '21241243965@qq.com');
INSERT INTO `contacts` VALUES (34, '秦广达', '15730105155', '6031541126@qq.con');
INSERT INTO `contacts` VALUES (35, '涂箫悦', '15244852955', '5151891644@qq.com');
INSERT INTO `contacts` VALUES (36, '甘春晏', '15730166355', '15455140184@qq.com');

-- ----------------------------
-- Table structure for route
-- ----------------------------
DROP TABLE IF EXISTS `route`;
CREATE TABLE `route`  (
  `eid` int(10) NOT NULL AUTO_INCREMENT,
  `start_city` int(10) NOT NULL,
  `end_city` int(10) NOT NULL,
  PRIMARY KEY (`eid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of route
-- ----------------------------
INSERT INTO `route` VALUES (1, 1, 2);
INSERT INTO `route` VALUES (2, 2, 3);
INSERT INTO `route` VALUES (3, 2, 5);
INSERT INTO `route` VALUES (4, 3, 4);
INSERT INTO `route` VALUES (5, 3, 5);
INSERT INTO `route` VALUES (6, 3, 7);
INSERT INTO `route` VALUES (7, 3, 9);
INSERT INTO `route` VALUES (8, 4, 3);
INSERT INTO `route` VALUES (9, 4, 5);
INSERT INTO `route` VALUES (10, 4, 9);
INSERT INTO `route` VALUES (11, 5, 2);
INSERT INTO `route` VALUES (12, 5, 3);
INSERT INTO `route` VALUES (13, 5, 4);
INSERT INTO `route` VALUES (14, 5, 6);
INSERT INTO `route` VALUES (15, 5, 11);
INSERT INTO `route` VALUES (16, 6, 5);
INSERT INTO `route` VALUES (17, 6, 7);
INSERT INTO `route` VALUES (18, 6, 8);
INSERT INTO `route` VALUES (19, 7, 3);
INSERT INTO `route` VALUES (20, 7, 6);
INSERT INTO `route` VALUES (21, 7, 8);
INSERT INTO `route` VALUES (22, 8, 6);
INSERT INTO `route` VALUES (23, 8, 7);
INSERT INTO `route` VALUES (24, 8, 10);
INSERT INTO `route` VALUES (25, 9, 3);
INSERT INTO `route` VALUES (26, 9, 4);
INSERT INTO `route` VALUES (27, 10, 8);
INSERT INTO `route` VALUES (28, 10, 11);
INSERT INTO `route` VALUES (29, 11, 5);
INSERT INTO `route` VALUES (30, 11, 10);
INSERT INTO `route` VALUES (31, 2, 1);
INSERT INTO `route` VALUES (32, 3, 2);

-- ----------------------------
-- Table structure for traintime
-- ----------------------------
DROP TABLE IF EXISTS `traintime`;
CREATE TABLE `traintime`  (
  `eid` int(6) NOT NULL AUTO_INCREMENT,
  `train_number` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '列车编号',
  `route_id` int(6) NOT NULL COMMENT '线路id',
  `departure_time` datetime(0) NOT NULL COMMENT '起始时间',
  `achieve_time` datetime(0) NOT NULL COMMENT '到达时间',
  `money` int(6) NOT NULL COMMENT '花费金钱',
  PRIMARY KEY (`eid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of traintime
-- ----------------------------
INSERT INTO `traintime` VALUES (1, 'C640', 1, '2019-12-31 00:10:00', '2019-12-31 00:20:00', 100);
INSERT INTO `traintime` VALUES (2, 'C641', 2, '2019-12-31 00:10:00', '2019-12-31 00:50:00', 120);
INSERT INTO `traintime` VALUES (3, 'C642', 3, '2019-12-31 00:10:00', '2019-12-31 00:30:00', 90);
INSERT INTO `traintime` VALUES (4, 'C643', 4, '2019-12-31 00:10:00', '2019-12-31 00:40:00', 110);
INSERT INTO `traintime` VALUES (5, 'C644', 5, '2019-12-31 00:10:00', '2019-12-31 00:50:00', 80);
INSERT INTO `traintime` VALUES (6, 'C645', 6, '2019-12-31 00:10:00', '2019-12-31 01:00:00', 100);
INSERT INTO `traintime` VALUES (7, 'C646', 7, '2019-12-31 00:10:00', '2019-12-31 00:50:00', 120);
INSERT INTO `traintime` VALUES (8, 'C647', 8, '2019-12-31 00:10:00', '2019-12-31 00:40:00', 110);
INSERT INTO `traintime` VALUES (9, 'C648', 9, '2019-12-31 00:10:00', '2019-12-31 00:20:00', 100);
INSERT INTO `traintime` VALUES (10, 'C649', 10, '2019-12-31 00:10:00', '2019-12-31 00:50:00', 130);
INSERT INTO `traintime` VALUES (11, 'C650', 11, '2019-12-31 00:10:00', '2019-12-31 00:45:00', 90);
INSERT INTO `traintime` VALUES (12, 'C651', 12, '2019-12-31 00:10:00', '2019-12-31 00:40:00', 80);
INSERT INTO `traintime` VALUES (13, 'C652', 13, '2019-12-31 00:10:00', '2019-12-31 00:30:00', 100);
INSERT INTO `traintime` VALUES (14, 'C653', 14, '2019-12-31 00:10:00', '2019-12-31 01:00:00', 110);
INSERT INTO `traintime` VALUES (15, 'C654', 15, '2019-12-31 00:10:00', '2019-12-31 00:50:00', 150);
INSERT INTO `traintime` VALUES (16, 'C655', 16, '2019-12-31 00:10:00', '2019-12-31 00:40:00', 110);
INSERT INTO `traintime` VALUES (17, 'C656', 17, '2019-12-31 00:10:00', '2019-12-31 00:30:00', 80);
INSERT INTO `traintime` VALUES (18, 'C657', 18, '2019-12-31 00:10:00', '2019-12-31 00:45:00', 120);
INSERT INTO `traintime` VALUES (19, 'C658', 19, '2019-12-31 00:10:00', '2019-12-31 00:40:00', 100);
INSERT INTO `traintime` VALUES (20, 'C659', 20, '2019-12-31 00:10:00', '2019-12-31 00:30:00', 80);
INSERT INTO `traintime` VALUES (21, 'C660', 21, '2019-12-31 00:10:00', '2019-12-31 01:10:00', 90);
INSERT INTO `traintime` VALUES (22, 'C661', 22, '2019-12-31 00:10:00', '2019-12-31 00:58:00', 120);
INSERT INTO `traintime` VALUES (23, 'C662', 23, '2019-12-31 00:10:00', '2019-12-31 00:47:00', 90);
INSERT INTO `traintime` VALUES (24, 'C663', 24, '2019-12-31 00:10:00', '2019-12-31 00:40:00', 110);
INSERT INTO `traintime` VALUES (25, 'C664', 25, '2019-12-31 00:10:00', '2019-12-31 00:37:00', 120);
INSERT INTO `traintime` VALUES (26, 'C665', 26, '2019-12-31 00:10:00', '2019-12-31 00:55:00', 130);
INSERT INTO `traintime` VALUES (27, 'C667', 27, '2019-12-31 00:10:00', '2019-12-31 00:46:00', 110);
INSERT INTO `traintime` VALUES (28, 'C668', 28, '2019-12-31 00:10:00', '2019-12-31 00:40:00', 120);
INSERT INTO `traintime` VALUES (29, 'C669', 29, '2019-12-31 00:10:00', '2019-12-31 00:56:00', 150);
INSERT INTO `traintime` VALUES (30, 'C670', 30, '2019-12-31 00:10:00', '2019-12-31 00:40:00', 120);
INSERT INTO `traintime` VALUES (31, 'C671', 31, '2019-12-31 00:10:00', '2019-12-31 00:35:00', 100);
INSERT INTO `traintime` VALUES (32, 'C672', 32, '2019-12-31 00:10:00', '2019-12-31 00:40:00', 120);

SET FOREIGN_KEY_CHECKS = 1;
