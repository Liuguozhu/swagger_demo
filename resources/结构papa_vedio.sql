/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : papa_vedio

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2016-06-16 16:54:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ad_info_list
-- ----------------------------
DROP TABLE IF EXISTS `ad_info_list`;
CREATE TABLE `ad_info_list` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '广告Id',
  `AD_TITLE` varchar(200) DEFAULT NULL COMMENT '广告标题',
  `AD_TYPE_ID` int(11) DEFAULT NULL COMMENT '广告类型',
  `AD_POSITION` int(11) DEFAULT NULL COMMENT '广告栏位',
  `AD_ORDER` int(11) DEFAULT NULL COMMENT '广告排序',
  `AD_URL` varchar(2000) DEFAULT NULL COMMENT '广告链接',
  `IMG_URL` varchar(2000) DEFAULT NULL COMMENT '广告图片URL',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ad_position_list
-- ----------------------------
DROP TABLE IF EXISTS `ad_position_list`;
CREATE TABLE `ad_position_list` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `POSITION_NAME` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ad_type_list
-- ----------------------------
DROP TABLE IF EXISTS `ad_type_list`;
CREATE TABLE `ad_type_list` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TYPE_NAME` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for channel_info
-- ----------------------------
DROP TABLE IF EXISTS `channel_info`;
CREATE TABLE `channel_info` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `CHANNEL_NAME` varchar(200) DEFAULT NULL COMMENT '频道名称',
  `CHANNEL_ORDER` int(11) DEFAULT NULL COMMENT '频道排序',
  `CHANNEL_DESC` varchar(500) DEFAULT NULL,
  `ICON_URL` varchar(1000) DEFAULT NULL COMMENT '图标icon的URL',
  `CHANNEL_STATUS` bit(1) NOT NULL DEFAULT b'0' COMMENT '频道状态，是否启用。0禁用；1正常',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for channel_to_video_area_set
-- ----------------------------
DROP TABLE IF EXISTS `channel_to_video_area_set`;
CREATE TABLE `channel_to_video_area_set` (
  `CHANNEL_ID` int(11) NOT NULL COMMENT '频道ID',
  `VIDEO_AREA_ID` int(11) NOT NULL COMMENT '视频区域ID',
  `SORT` int(11) DEFAULT NULL COMMENT '排序',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`CHANNEL_ID`,`VIDEO_AREA_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for channel_to_video_classify_set
-- ----------------------------
DROP TABLE IF EXISTS `channel_to_video_classify_set`;
CREATE TABLE `channel_to_video_classify_set` (
  `CHANNEL_ID` int(11) NOT NULL COMMENT '频道ID',
  `VIDEO_CLASSIFY_ID` int(11) NOT NULL COMMENT '视频分类ID',
  `SORT` int(11) DEFAULT NULL COMMENT '排序',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`CHANNEL_ID`,`VIDEO_CLASSIFY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for channel_to_video_type_set
-- ----------------------------
DROP TABLE IF EXISTS `channel_to_video_type_set`;
CREATE TABLE `channel_to_video_type_set` (
  `CHANNEL_ID` int(11) NOT NULL COMMENT '频道ID',
  `VIDEO_TYPE_ID` int(11) NOT NULL COMMENT '视频类型ID',
  `SORT` int(11) DEFAULT NULL COMMENT '排序',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`CHANNEL_ID`,`VIDEO_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for channel_to_video_year_set
-- ----------------------------
DROP TABLE IF EXISTS `channel_to_video_year_set`;
CREATE TABLE `channel_to_video_year_set` (
  `CHANNEL_ID` int(11) NOT NULL COMMENT '频道ID',
  `VIDEO_RELEASE_DATE` varchar(20) NOT NULL COMMENT '视频上映年份',
  `SORT` int(11) DEFAULT NULL COMMENT '排序',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`CHANNEL_ID`,`VIDEO_RELEASE_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for featured_first
-- ----------------------------
DROP TABLE IF EXISTS `featured_first`;
CREATE TABLE `featured_first` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '推荐位ID',
  `NAME` varchar(255) DEFAULT NULL COMMENT '推荐位显示名称',
  `SORT` int(11) DEFAULT NULL COMMENT '排序(给客户端用）',
  `MORE_TYPE` int(11) DEFAULT NULL COMMENT '点击更多跳转到什么类型（专题、渠道等）',
  `MORE_ID` int(11) DEFAULT NULL COMMENT '点击更多所对应的类型的id（某个专题的ID，或频道的ID等）',
  `FEATURED_STATUS` bit(1) NOT NULL DEFAULT b'0' COMMENT '状态，是否启用0禁用，1启用',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for featured_video_set
-- ----------------------------
DROP TABLE IF EXISTS `featured_video_set`;
CREATE TABLE `featured_video_set` (
  `FEATURED_FIRST_ID` int(11) NOT NULL COMMENT '推荐位id',
  `VIDEO_ID` int(11) NOT NULL COMMENT '视频id',
  `SORT` int(11) DEFAULT NULL COMMENT '排序',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`FEATURED_FIRST_ID`,`VIDEO_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for member_level_list
-- ----------------------------
DROP TABLE IF EXISTS `member_level_list`;
CREATE TABLE `member_level_list` (
  `LEVEL_ID` int(11) NOT NULL,
  `LEVEL_NAME` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`LEVEL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for player
-- ----------------------------
DROP TABLE IF EXISTS `player`;
CREATE TABLE `player` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户/玩家ID',
  `ORIGIN` int(11) NOT NULL DEFAULT '1' COMMENT '来源，1啪啪视频，2啪啪音乐，3啪啪阅读等',
  `NICK_NAME` varchar(255) DEFAULT NULL COMMENT '昵称',
  `USER_NAME` varchar(255) DEFAULT NULL COMMENT '账号',
  `PASSWORD` varchar(255) DEFAULT NULL COMMENT '密码',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `VIP_LEVEL` int(11) NOT NULL DEFAULT '0' COMMENT '会员等级，0普通，1包月，2包年，3终身等等',
  `VIP_DATE_DUE` timestamp NULL DEFAULT NULL COMMENT '会员到期时间',
  `PLAYER_STATUS` bit(1) NOT NULL DEFAULT b'1' COMMENT '状态，0禁用，1正常',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for player_historic_records
-- ----------------------------
DROP TABLE IF EXISTS `player_historic_records`;
CREATE TABLE `player_historic_records` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '观看历史记录ID',
  `PLAYER_ID` int(11) DEFAULT NULL COMMENT '用户id',
  `VIDEO_ID` int(11) DEFAULT NULL COMMENT '视频id',
  `VIDEO_DETAIL_ID` int(11) DEFAULT NULL COMMENT '剧集id',
  `PROGRESS` varchar(255) DEFAULT NULL COMMENT '观看进度，观看到第几分钟',
  `CREATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '观看时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for player_origin_list
-- ----------------------------
DROP TABLE IF EXISTS `player_origin_list`;
CREATE TABLE `player_origin_list` (
  `ORIGIN_ID` int(11) NOT NULL,
  `ORIGIN_NAME` varchar(200) NOT NULL,
  PRIMARY KEY (`ORIGIN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for player_subscription
-- ----------------------------
DROP TABLE IF EXISTS `player_subscription`;
CREATE TABLE `player_subscription` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '收藏记录ID',
  `PLAYER_ID` int(11) DEFAULT NULL COMMENT '用户id',
  `VIDEO_ID` int(11) DEFAULT NULL COMMENT '视频id',
  `CREATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '收藏时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for rank_list
-- ----------------------------
DROP TABLE IF EXISTS `rank_list`;
CREATE TABLE `rank_list` (
  `RANK_LIST_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '排行榜id',
  `CLASSIFY_ID` int(11) DEFAULT NULL COMMENT '视频类型',
  `VIDEO_ID` int(11) DEFAULT NULL COMMENT '视频id',
  `SORT` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`RANK_LIST_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for subject_info
-- ----------------------------
DROP TABLE IF EXISTS `subject_info`;
CREATE TABLE `subject_info` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '专题ID',
  `SUBJECT_NAME` varchar(200) DEFAULT NULL COMMENT '专题名称',
  `SUBJECT_DESC` varchar(2000) DEFAULT NULL COMMENT '专题描述',
  `SUBJECT_IMG` varchar(2000) DEFAULT NULL COMMENT '专题图片',
  `SORT` int(11) NOT NULL DEFAULT '0',
  `SUBJECT_SATUS` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否启用',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for subject_video_set
-- ----------------------------
DROP TABLE IF EXISTS `subject_video_set`;
CREATE TABLE `subject_video_set` (
  `SUBJECT_ID` int(11) NOT NULL,
  `VIDEO_ID` int(11) NOT NULL,
  `SORT` int(11) DEFAULT NULL COMMENT '排序',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`SUBJECT_ID`,`VIDEO_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `ACCOUNT` varchar(50) DEFAULT NULL COMMENT '账号',
  `PASSWORD` varchar(50) DEFAULT NULL COMMENT '密码',
  `USER_NAME` varchar(50) DEFAULT NULL COMMENT '姓名',
  `GAME_NAME` varchar(100) DEFAULT NULL COMMENT '游戏内昵称',
  `CHANNELID` int(11) DEFAULT NULL COMMENT '所属渠道',
  `PERMISSION` varchar(2000) DEFAULT NULL COMMENT '权限',
  `ROLEID` int(11) DEFAULT NULL COMMENT '角色',
  `THIS_TIME` datetime DEFAULT NULL,
  `LATER_TIME` datetime DEFAULT NULL COMMENT '上次登录时间',
  `REMARK` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='平台用户';

-- ----------------------------
-- Table structure for video_area_info
-- ----------------------------
DROP TABLE IF EXISTS `video_area_info`;
CREATE TABLE `video_area_info` (
  `AREA_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '区域ID',
  `AREA_NAME` varchar(255) DEFAULT NULL COMMENT '区域名称',
  `AREA_DESC` varchar(255) DEFAULT NULL COMMENT '描述',
  `AREA_STATUS` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否启用，0禁用；1正常',
  PRIMARY KEY (`AREA_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for video_classify_info
-- ----------------------------
DROP TABLE IF EXISTS `video_classify_info`;
CREATE TABLE `video_classify_info` (
  `CLASSIFY_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `CLASSIFY_NAME` varchar(255) DEFAULT NULL COMMENT '分类名称',
  `CLASSIFY_DESC` varchar(255) DEFAULT NULL COMMENT '分类描述',
  `CLASSIFY_STATUS` bit(1) NOT NULL DEFAULT b'0' COMMENT '分类的状态，是否启用，0禁用；1正常',
  PRIMARY KEY (`CLASSIFY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for video_details
-- ----------------------------
DROP TABLE IF EXISTS `video_details`;
CREATE TABLE `video_details` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `VIDEO_ID` int(11) DEFAULT NULL COMMENT '视频ID',
  `SUB_NAME` varchar(2000) DEFAULT NULL COMMENT '分集标题（可为空）',
  `SORT` int(11) DEFAULT NULL COMMENT '合集排序（第几集）',
  `IMG_URL` text COMMENT '封面地址',
  `VIDEO_URL` text COMMENT '视频地址',
  `DESCRIPTION` varchar(2000) DEFAULT NULL COMMENT '分集简介（可为空）',
  `PAGE_URL` varchar(2000) DEFAULT NULL COMMENT '数据来源',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=45136 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for video_fee_type_info
-- ----------------------------
DROP TABLE IF EXISTS `video_fee_type_info`;
CREATE TABLE `video_fee_type_info` (
  `ID` int(11) DEFAULT NULL,
  `FEE_TYPE_NAME` varchar(255) DEFAULT NULL COMMENT '收费类型名称（免费，会员免费，点播收费，会员用券等）'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for video_info
-- ----------------------------
DROP TABLE IF EXISTS `video_info`;
CREATE TABLE `video_info` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '视频ID',
  `IS_COLLECTION` bit(1) DEFAULT b'0' COMMENT '是否是合集，0不是；1是；默认为0',
  `IMG_URL` text COMMENT '封面地址',
  `VIDEO_NAME` varchar(2000) DEFAULT NULL COMMENT '标题',
  `TITLE` varchar(100) DEFAULT NULL COMMENT '几个字的小标题',
  `DESCRIPTION` varchar(2000) DEFAULT NULL COMMENT '简介',
  `LEADING_ROLE` varchar(2000) DEFAULT NULL COMMENT '主要角色',
  `DERCETOR` varchar(2000) DEFAULT NULL COMMENT '导演',
  `RELEASE_DATE` varchar(20) DEFAULT NULL COMMENT '上映年份',
  `FEE_TYPE` int(11) DEFAULT NULL COMMENT '收费类型',
  `IS_NEW` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否最新',
  `IS_HOT` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否最热',
  `SCORE` int(11) DEFAULT NULL COMMENT '评分',
  `IS_USABLE` int(11) NOT NULL DEFAULT '0' COMMENT '是否可用',
  `VIDEO_CLASSIFY` varchar(100) DEFAULT NULL COMMENT '视频分类',
  `RENEWAL_STATUS` varchar(100) DEFAULT NULL COMMENT '更新到第几集',
  `RENEWAL_DATE` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `PAGE_URL` varchar(2000) DEFAULT NULL COMMENT '数据来源url',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1601 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for video_to_area_set
-- ----------------------------
DROP TABLE IF EXISTS `video_to_area_set`;
CREATE TABLE `video_to_area_set` (
  `VIDEO_ID` int(11) NOT NULL,
  `VIDEO_AREA_ID` int(11) NOT NULL COMMENT '视频区域ID',
  `SORT` int(11) DEFAULT NULL COMMENT '排序',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`VIDEO_ID`,`VIDEO_AREA_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for video_to_classify_set
-- ----------------------------
DROP TABLE IF EXISTS `video_to_classify_set`;
CREATE TABLE `video_to_classify_set` (
  `VIDEO_ID` int(11) NOT NULL COMMENT '视频ID',
  `VIDEO_CLASSIFY_ID` int(11) NOT NULL COMMENT '视频分类ID',
  `SORT` int(11) DEFAULT NULL COMMENT '排序',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`VIDEO_ID`,`VIDEO_CLASSIFY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for video_to_type_set
-- ----------------------------
DROP TABLE IF EXISTS `video_to_type_set`;
CREATE TABLE `video_to_type_set` (
  `VIDEO_ID` int(11) NOT NULL,
  `VIDEO_TYPE_ID` int(11) NOT NULL COMMENT '视频类型ID',
  `SORT` int(11) DEFAULT NULL COMMENT '排序',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`VIDEO_ID`,`VIDEO_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for video_type_info
-- ----------------------------
DROP TABLE IF EXISTS `video_type_info`;
CREATE TABLE `video_type_info` (
  `TYPE_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '类型ID',
  `TYPE_NAME` varchar(255) DEFAULT NULL COMMENT '类型名称',
  `TYPE_DESC` varchar(255) DEFAULT NULL COMMENT '类型描述',
  `TYPE_STATUS` bit(1) NOT NULL DEFAULT b'0' COMMENT '类型的状态，是否启用，0禁用；1正常',
  PRIMARY KEY (`TYPE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;
