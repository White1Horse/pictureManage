package com.azxx.picture.service;

import com.azxx.picture.entity.AppInfo;
import com.azxx.picture.mapper.FileInfoMapper;
import com.azxx.picture.utils.IDGenerator;
import com.azxx.picture.vo.AppInfoVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @version V1.0
 * @class: FileService
 * @description: file service
 * @author: xuzheng
 * @create: 2019-02-02 10:14
 **/
@Service
public class FileService {

    @Autowired
    private FileInfoMapper fileInfoMapper;

    public PageInfo<AppInfo> getFiles(AppInfoVo appInfoVo) {
        return PageHelper.startPage(appInfoVo.getPage(), appInfoVo.getRows()).doSelectPageInfo(() -> fileInfoMapper.selectAppInfo(appInfoVo));
    }

    public Boolean insertOrUpdateFile(AppInfoVo appInfoVo) {
        int effectRows = 0;
        if (appInfoVo == null) {
            return false;
        }
        if(StringUtils.isBlank(appInfoVo.getAppId())){
            return false;
        }
        AppInfo appInfo = fileInfoMapper.getAppByAppId(appInfoVo.getAppId());
        if(appInfo == null ){
            appInfo = new AppInfo();
        }
        String id = appInfo.getId();
        BeanUtils.copyProperties(appInfoVo, appInfo);
        appInfo.setId(id);
        if (appInfo.getId() == null) {
            appInfo.setId(IDGenerator.getUUID());
//            appInfo.setCreateUser();
            appInfo.setReleaseDate(new Date());
            appInfo.setAppType("reactnative");
            appInfo.setState("7");
            effectRows = fileInfoMapper.insert(appInfo);
        } else {
//            appInfo.setUpdateUser();
            appInfo.setUpdateDate(new Date());
            effectRows = fileInfoMapper.updateByPrimaryKeySelective(appInfo);
        }
        return effectRows > 0 ? true : false;
    }

//    public boolean deleteFile(String ids) {
//        int effectRows = 0;
//        if (StringUtils.isBlank(ids)) {
//            return false;
//        }
//        String[] idArray = ids.split(",");
//        for (int i = 0; i < idArray.length; i++) {
//            effectRows = effectRows+fileInfoMapper.deleteByPrimaryKey(Integer.parseInt(idArray[i]));
//        }
//        return effectRows > 0 ? true : false;
//    }

}
