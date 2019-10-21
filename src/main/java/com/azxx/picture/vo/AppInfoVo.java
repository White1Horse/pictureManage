package com.azxx.picture.vo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Objects;

public class AppInfoVo {

    private static final long serialVersionUID = 1L;

    private String Id;

    /**应用标识*/
    private String appId;

    /**应用名称*/
    private String appName;

    /**当前版本号*/
    private String version;

    /**版本下载url*/
    private String url;

    /**应用图标*/
    private String icon;

    /**应用截图*/
    private String preview;

    /**应用类型*/
    private String appType;

    /**老版本应用类型应用类型*/
    private String oldType;

    /**新版本应用类型应用类型*/
    private String newType;

    /**当前状态*/
    private String state;

    /**发布日期*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date releaseDate;

    /**bundleId*/
    private String bundleId;

    /**包名*/
    private String packageName;

    /**包名*/
    private String activityName;

    /**参数*/
    private String params;

    /**plist文件地址*/
    private String pList;

    /**灰度plist文件地址*/
    private String graypList;

    /**应用发布策略*/
    private String releasePolicy;

    /**开发者id*/
    private String devId;

    /**是否是云门户应用*/
    private Integer isCurrent;

    /**应用描述*/
    private String description;

    /**iOS urlscheme*/
    private String urlScheme;

    /**HTML5 entryURL*/
    private String entryUrl;

    /**应用更新信息*/
    private String updateInfo;

    /**是否跳过灰度发布*/
    private Integer isSkipGray;

    /**应用概述*/
    private String summary;

    /**应用评论的参数（平均分，总人数，不同分数的人数）*/
    private String commentParams;

    /**应用下载次数*/
    private Integer downAmount;

    /**应用分类*/
    private String appClass;

    /**应用所属区域*/
    private String region;

    /**图片更新日期*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    /**图片创建日期*/
    private String createUser;

    /**更新用户*/
    private String updateUser;

    /**图片地址*/
    private String fileUrl;

    /**页数*/
    private Integer page;

    /**条数*/
    private Integer rows;

    /**开始时间*/
    private String startTime;

    /**结束时间*/
    private String endTime;

    /**状态名字*/
    private String stateName;

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getOldType() {
        return oldType;
    }

    public void setOldType(String oldType) {
        this.oldType = oldType;
    }

    public String getNewType() {
        return newType;
    }

    public void setNewType(String newType) {
        this.newType = newType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getBundleId() {
        return bundleId;
    }

    public void setBundleId(String bundleId) {
        this.bundleId = bundleId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getpList() {
        return pList;
    }

    public void setpList(String pList) {
        this.pList = pList;
    }

    public String getGraypList() {
        return graypList;
    }

    public void setGraypList(String graypList) {
        this.graypList = graypList;
    }

    public String getReleasePolicy() {
        return releasePolicy;
    }

    public void setReleasePolicy(String releasePolicy) {
        this.releasePolicy = releasePolicy;
    }

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }

    public Integer getIsCurrent() {
        return isCurrent;
    }

    public void setIsCurrent(Integer isCurrent) {
        this.isCurrent = isCurrent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlScheme() {
        return urlScheme;
    }

    public void setUrlScheme(String urlScheme) {
        this.urlScheme = urlScheme;
    }

    public String getEntryUrl() {
        return entryUrl;
    }

    public void setEntryUrl(String entryUrl) {
        this.entryUrl = entryUrl;
    }

    public String getUpdateInfo() {
        return updateInfo;
    }

    public void setUpdateInfo(String updateInfo) {
        this.updateInfo = updateInfo;
    }

    public Integer getIsSkipGray() {
        return isSkipGray;
    }

    public void setIsSkipGray(Integer isSkipGray) {
        this.isSkipGray = isSkipGray;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCommentParams() {
        return commentParams;
    }

    public void setCommentParams(String commentParams) {
        this.commentParams = commentParams;
    }

    public Integer getDownAmount() {
        return downAmount;
    }

    public void setDownAmount(Integer downAmount) {
        this.downAmount = downAmount;
    }

    public String getAppClass() {
        return appClass;
    }

    public void setAppClass(String appClass) {
        this.appClass = appClass;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "AppInfoVo{" +
                "Id='" + Id + '\'' +
                ", appId='" + appId + '\'' +
                ", appName='" + appName + '\'' +
                ", version='" + version + '\'' +
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                ", preview='" + preview + '\'' +
                ", appType='" + appType + '\'' +
                ", oldType='" + oldType + '\'' +
                ", newType='" + newType + '\'' +
                ", state='" + state + '\'' +
                ", releaseDate=" + releaseDate +
                ", bundleId='" + bundleId + '\'' +
                ", packageName='" + packageName + '\'' +
                ", activityName='" + activityName + '\'' +
                ", params='" + params + '\'' +
                ", pList='" + pList + '\'' +
                ", graypList='" + graypList + '\'' +
                ", releasePolicy='" + releasePolicy + '\'' +
                ", devId='" + devId + '\'' +
                ", isCurrent=" + isCurrent +
                ", description='" + description + '\'' +
                ", urlScheme='" + urlScheme + '\'' +
                ", entryUrl='" + entryUrl + '\'' +
                ", updateInfo='" + updateInfo + '\'' +
                ", isSkipGray=" + isSkipGray +
                ", summary='" + summary + '\'' +
                ", commentParams='" + commentParams + '\'' +
                ", downAmount=" + downAmount +
                ", appClass='" + appClass + '\'' +
                ", region='" + region + '\'' +
                ", updateDate=" + updateDate +
                ", createUser='" + createUser + '\'' +
                ", updateUser='" + updateUser + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", page=" + page +
                ", rows=" + rows +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", stateName='" + stateName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AppInfoVo)) return false;
        AppInfoVo appInfoVo = (AppInfoVo) o;
        return Objects.equals(Id, appInfoVo.Id) &&
                Objects.equals(appId, appInfoVo.appId) &&
                Objects.equals(appName, appInfoVo.appName) &&
                Objects.equals(version, appInfoVo.version) &&
                Objects.equals(url, appInfoVo.url) &&
                Objects.equals(icon, appInfoVo.icon) &&
                Objects.equals(preview, appInfoVo.preview) &&
                Objects.equals(appType, appInfoVo.appType) &&
                Objects.equals(oldType, appInfoVo.oldType) &&
                Objects.equals(newType, appInfoVo.newType) &&
                Objects.equals(state, appInfoVo.state) &&
                Objects.equals(releaseDate, appInfoVo.releaseDate) &&
                Objects.equals(bundleId, appInfoVo.bundleId) &&
                Objects.equals(packageName, appInfoVo.packageName) &&
                Objects.equals(activityName, appInfoVo.activityName) &&
                Objects.equals(params, appInfoVo.params) &&
                Objects.equals(pList, appInfoVo.pList) &&
                Objects.equals(graypList, appInfoVo.graypList) &&
                Objects.equals(releasePolicy, appInfoVo.releasePolicy) &&
                Objects.equals(devId, appInfoVo.devId) &&
                Objects.equals(isCurrent, appInfoVo.isCurrent) &&
                Objects.equals(description, appInfoVo.description) &&
                Objects.equals(urlScheme, appInfoVo.urlScheme) &&
                Objects.equals(entryUrl, appInfoVo.entryUrl) &&
                Objects.equals(updateInfo, appInfoVo.updateInfo) &&
                Objects.equals(isSkipGray, appInfoVo.isSkipGray) &&
                Objects.equals(summary, appInfoVo.summary) &&
                Objects.equals(commentParams, appInfoVo.commentParams) &&
                Objects.equals(downAmount, appInfoVo.downAmount) &&
                Objects.equals(appClass, appInfoVo.appClass) &&
                Objects.equals(region, appInfoVo.region) &&
                Objects.equals(updateDate, appInfoVo.updateDate) &&
                Objects.equals(createUser, appInfoVo.createUser) &&
                Objects.equals(updateUser, appInfoVo.updateUser) &&
                Objects.equals(fileUrl, appInfoVo.fileUrl) &&
                Objects.equals(page, appInfoVo.page) &&
                Objects.equals(rows, appInfoVo.rows) &&
                Objects.equals(startTime, appInfoVo.startTime) &&
                Objects.equals(endTime, appInfoVo.endTime) &&
                Objects.equals(stateName, appInfoVo.stateName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, appId, appName, version, url, icon, preview, appType, oldType, newType, state, releaseDate, bundleId, packageName, activityName, params, pList, graypList, releasePolicy, devId, isCurrent, description, urlScheme, entryUrl, updateInfo, isSkipGray, summary, commentParams, downAmount, appClass, region, updateDate, createUser, updateUser, fileUrl, page, rows, startTime, endTime, stateName);
    }

}
