/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : byte_easy

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-04-11 22:12:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `series` varchar(255) NOT NULL,
  `last_used` datetime DEFAULT NULL,
  `token` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`series`),
  UNIQUE KEY `UK_bqa9l0go97v49wwyx4c0u3kpd` (`token`),
  UNIQUE KEY `UK_f8t9fsfwc17s6qcbx0ath6l3h` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------

-- ----------------------------
-- Table structure for sys_organization
-- ----------------------------
DROP TABLE IF EXISTS `sys_organization`;
CREATE TABLE `sys_organization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `available` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmeds2u6ae5usj0ko0bqj3k0eo` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_organization
-- ----------------------------

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `available` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `order_num` int(11) NOT NULL,
  `permission` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3fekum3ead5klp7y4lckn5ohi` (`parent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=153 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('1', '\0', '顶级栏目', '100', null, '0', null, '0');
INSERT INTO `sys_resource` VALUES ('2', '\0', '权限配置', '8', '', '0', '', '1');
INSERT INTO `sys_resource` VALUES ('3', '\0', '角色管理', '102', '/role', '0', '/role', '2');
INSERT INTO `sys_resource` VALUES ('4', '\0', '权限管理', '103', '/resource', '0', '/resource', '2');
INSERT INTO `sys_resource` VALUES ('5', '\0', '用户管理', '101', '/user', '0', '/user', '2');
INSERT INTO `sys_resource` VALUES ('6', '\0', '编辑', '100', '/role/editor-role', '1', '/role/editor-role', '3');
INSERT INTO `sys_resource` VALUES ('7', '\0', '添加权限节点', '100', '/resource/add-permission', '1', '/resource/add-permission', '4');
INSERT INTO `sys_resource` VALUES ('8', '\0', '添加管理员', '100', '/user/add-user', '1', '/user/add-user', '5');
INSERT INTO `sys_resource` VALUES ('9', '\0', '添加角色', '100', '/role/add-role', '1', '/role/add-role', '3');
INSERT INTO `sys_resource` VALUES ('10', '\0', '删除角色', '100', '/role/delete', '1', '/role/delete', '3');
INSERT INTO `sys_resource` VALUES ('11', '\0', '删除用户', '100', '/user/delete-user', '1', '/user/delete-user', '5');
INSERT INTO `sys_resource` VALUES ('12', '\0', '删除权限', '100', '/resource/delete', '1', '/resource/delete', '4');
INSERT INTO `sys_resource` VALUES ('13', '\0', '启用', '100', '/user/available-user', '1', '/user/available-user', '3');
INSERT INTO `sys_resource` VALUES ('14', '\0', '修改管理员密码', '100', '/user/modify-password', '1', '/user/modify-password', '5');
INSERT INTO `sys_resource` VALUES ('16', null, '权限编辑', '100', '/resource/editor-permission', '1', '/resource/editor-permission', '4');
INSERT INTO `sys_resource` VALUES ('150', '\0', '编辑管理员信息', '100', '/user/edit-user', '1', '/user/edit-user', '5');
INSERT INTO `sys_resource` VALUES ('151', null, '人信息管理', '1', '', '0', '', '1');
INSERT INTO `sys_resource` VALUES ('152', null, '人信息列表', '1', '/admin/person', '0', '/admin/person', '151');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `available` bit(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', null, '管理员', '管理员');

-- ----------------------------
-- Table structure for sys_role_resources
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resources`;
CREATE TABLE `sys_role_resources` (
  `sys_role_id` bigint(20) NOT NULL,
  `resources_id` bigint(20) NOT NULL,
  KEY `FKog6jj4v6yh9e1ilxk2mwuk75a` (`resources_id`),
  KEY `FKsqkqfd2hpr5cc2kbrtgoced2w` (`sys_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_resources
-- ----------------------------
INSERT INTO `sys_role_resources` VALUES ('1', '2');
INSERT INTO `sys_role_resources` VALUES ('1', '3');
INSERT INTO `sys_role_resources` VALUES ('1', '6');
INSERT INTO `sys_role_resources` VALUES ('1', '9');
INSERT INTO `sys_role_resources` VALUES ('1', '10');
INSERT INTO `sys_role_resources` VALUES ('1', '13');
INSERT INTO `sys_role_resources` VALUES ('1', '4');
INSERT INTO `sys_role_resources` VALUES ('1', '7');
INSERT INTO `sys_role_resources` VALUES ('1', '12');
INSERT INTO `sys_role_resources` VALUES ('1', '16');
INSERT INTO `sys_role_resources` VALUES ('1', '5');
INSERT INTO `sys_role_resources` VALUES ('1', '8');
INSERT INTO `sys_role_resources` VALUES ('1', '11');
INSERT INTO `sys_role_resources` VALUES ('1', '14');
INSERT INTO `sys_role_resources` VALUES ('1', '150');
INSERT INTO `sys_role_resources` VALUES ('1', '151');
INSERT INTO `sys_role_resources` VALUES ('1', '152');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createtime` datetime DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `available` bit(1) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `sex_type` int(11) DEFAULT NULL COMMENT '性别(0.男,1.女)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '2017-07-11 17:42:18', '$2a$10$SIU57gfkh/TsIVYALXBNAeDnQzkm652FT9cg4h8wtEfC306uliyYa', '2019-01-11 07:34:38', 'admin', '', '1191134106@qq.com', '15030103078', '1');

-- ----------------------------
-- Table structure for sys_user_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_roles`;
CREATE TABLE `sys_user_roles` (
  `sys_user_id` bigint(20) NOT NULL,
  `roles_id` bigint(20) NOT NULL,
  KEY `FKdpvc6d7xqpqr43dfuk1s27cqh` (`roles_id`),
  KEY `FKd0ut7sloes191bygyf7a3pk52` (`sys_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_roles
-- ----------------------------
INSERT INTO `sys_user_roles` VALUES ('1', '1');

-- ----------------------------
-- Table structure for t_person
-- ----------------------------
DROP TABLE IF EXISTS `t_person`;
CREATE TABLE `t_person` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `code` varchar(100) DEFAULT NULL COMMENT '编号',
  `content` text COMMENT '内容',
  `file_path` varchar(100) DEFAULT NULL COMMENT '图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_person
-- ----------------------------
INSERT INTO `t_person` VALUES ('5', '123', '123123', '<p><img src=\"http://localhost:8080/fileSuffix/20190403/20190403150850813_CBJCS6.png\" title=\"upfile\" alt=\"系统快捷键.png\"/></p>', '12312');
