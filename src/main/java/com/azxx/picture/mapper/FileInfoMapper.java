package com.azxx.picture.mapper;

import com.azxx.picture.entity.AppInfo;
import com.azxx.picture.vo.AppInfoVo;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

@Mapper
public interface FileInfoMapper extends BaseMapper<AppInfo> {

//    List<FileInfo> getFiles(FileReqVo reqVo);

//    FileInfo getFileByName(String name);

	List<AppInfo> selectAppInfo(AppInfoVo appInfoVo);

//	void insertAppInfo(AppInfo appInfo);
//
//	void update(AppInfo appInfo);

	AppInfo getAppByAppId(String appId);

	List<AppInfo> selectState();
}