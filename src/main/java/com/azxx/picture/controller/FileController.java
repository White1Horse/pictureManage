package com.azxx.picture.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.azxx.picture.vo.AppInfoVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.azxx.picture.entity.AppInfo;
import com.azxx.picture.entity.FileInfo;
import com.azxx.picture.service.FileService;
import com.azxx.picture.vo.OperateTypeEm;
import com.azxx.picture.vo.fileInfo.FileReqVo;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @version V1.0
 * @class: PictureController
 * @description: 图片操作、查询类
 * @author: xuzheng
 * @create: 2019-02-02 10:08
 **/

@RestController
@RequestMapping(path = "/apps")
@Api(tags = "apps", description = "图片管理")
public class FileController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileService fileService;


    private final ResourceLoader resourceLoader;

    @Value("${img.dir}")
    private String filePath;

    @Value("${img.url}")
    private String imgUrl;

    @Value("${img.imgName}")
    private String imgName;

    @Autowired
    public FileController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @RequestMapping(path = "/uploadFile", method = RequestMethod.PUT)
    @ApiOperation(value = "文件上传", notes = "文件上传")
    @ApiResponses({@ApiResponse(code = 200, message = "处理成功")})
    public String uploadFile(@RequestParam("fileToUpload") MultipartFile file, AppInfo appInfo) {

//        String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        try {
            if (null == appInfo.getAppId()) {
                return paramsError();
            }
            String pathName = filePath + appInfo.getAppId() + "/" + imgName;
            String imgFilePath = filePath + appInfo.getAppId();
            File imgFilePath1 = new File(imgFilePath);
            if (!imgFilePath1.exists()) {
                imgFilePath1.mkdirs();
            }
            File tmpFile = new File(pathName);
            if (!tmpFile.exists()) {
                tmpFile.createNewFile();
            }
            InputStream in = file.getInputStream();
            FileOutputStream fos = new FileOutputStream(tmpFile);
            byte[] b = new byte[1024];
            int length;
            while ((length = in.read(b)) != -1) {
                fos.write(b, 0, length);
            }
            in.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return queryOk(null);
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取微应用信息", notes = "分页获取微应用信息")
    @ApiResponses({@ApiResponse(code = 200, message = "处理成功", response = AppInfo.class)})
    public String getFiles(AppInfoVo appInfoVo) {

        if (appInfoVo == null) {
            return paramsError();
        }
        try {
            PageInfo<AppInfo> result = fileService.getFiles(appInfoVo);
            return queryOk(result);
        } catch (Exception e) {
            e.printStackTrace();
            return error(null, OperateTypeEm.QUERY.toString());
        }

    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    @ApiOperation(value = "增加或更新微应用管理信息", notes = "增加或更新微应用管理信息")
    @ApiResponses({@ApiResponse(code = 200, message = "处理成功", response = FileInfo.class)})
    public String addOrUpdateFile(AppInfoVo appInfoVo) {
        if (appInfoVo == null || StringUtils.isBlank(appInfoVo.getAppId())) {
            return paramsError();
        }

        logger.info("增加或更新图片信息，参数：{}", JSON.toJSON(appInfoVo));
        try {
            appInfoVo.setState(appInfoVo.getStateName());
            System.err.println(appInfoVo.getState());
            boolean result = fileService.insertOrUpdateFile(appInfoVo);
            if (result) {
                return updateOk(null);
            } else {
                return paramsError();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return error(null, OperateTypeEm.UPDATE.toString());
        }
    }

    @RequestMapping(path = "/selectState", method = RequestMethod.GET)
    @ApiOperation(value = "查询微应用状态", notes = "查询微应用状态")
    @ApiResponses({@ApiResponse(code = 200, message = "处理成功", response = FileInfo.class)})
    public String selectState() {

        List<AppInfo> list = fileService.selectState();
        return queryOk(list);
    }

    /**
     * 显示单张图片
     *
     * @return
     */
    @RequestMapping(path = "/show", method = RequestMethod.GET)
    public ResponseEntity showPhotos(String fileName) {

        try {
            // 由于是读取本机的文件，file是一定要加上的， path是在application配置文件中的路径
            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get("D:\\newcoding\\pricterManage\\src\\main\\resources\\static\\customer\\img", "bbbbbbb.jpg").toString()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

//    @RequestMapping(path = "/pages", method = RequestMethod.POST)
//    @ApiOperation(value = "获取图片列表", notes = "获取图片列表")
//    @ApiResponses({@ApiResponse(code = 200, message = "处理成功", response = FileInfo.class)})
//    public String pages(FileReqVo reqVo) {
//
//        if (reqVo == null) {
//            return paramsError();
//        }
//
//        logger.info("获取文件列表开始，参数：{}", JSON.toJSON(reqVo));
//        try {
//            PageInfo<FileInfo> result = fileService.pages(reqVo);
//            return queryOk(result);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return error(null, OperateTypeEm.QUERY.toString());
//        }
//
//    }

//    @RequestMapping(path = "/uploadFile", method = RequestMethod.POST)
//    @ApiOperation(value = "文件上传", notes = "文件上传")
//    @ApiResponses({@ApiResponse(code = 200, message = "处理成功")})
//    public String uploadFile(@RequestParam("fileToUpload") MultipartFile file, FileReqVo reqVo, HttpServletRequest request) {
//        if (reqVo == null) {
//            return paramsError();
//        }
//        String fileName = file.getOriginalFilename();
//        reqVo.setSize((int) (file.getSize() / 1000));
//        if (StringUtils.isBlank(reqVo.getName())) {
//            reqVo.setName(fileName);
//        }
//        if (StringUtils.isBlank(reqVo.getTitle())) {
//            reqVo.setName(fileName);
//        }
//        if (fileName.indexOf(".") > -1) {
//            reqVo.setType(fileName.substring(fileName.lastIndexOf(".")));
//        }
//        reqVo.setUrl(imgUrl+reqVo.getName());
//        try {
//            String pathname = filePath + reqVo.getName();
//            if(reqVo.getName().indexOf(reqVo.getType())<0){
//                reqVo.setUrl(imgUrl+reqVo.getName()+reqVo.getType());
//                pathname = filePath + reqVo.getName()+reqVo.getType();
//            }
//            fileService.addOrUpdateFile(reqVo);
//            String url = request.getContextPath();
//            logger.info("绝对路径：{}，工程路径：{}", filePath, url);
//            File tmpFile = new File(pathname);
//            if (!tmpFile.exists()) {
//                tmpFile.createNewFile();
//            }
//            InputStream in = file.getInputStream();
//            FileOutputStream fos = new FileOutputStream(tmpFile);
//            byte[] b = new byte[1024];
//            int length;
//            while ((length = in.read(b)) != -1) {
//                fos.write(b, 0, length);
//            }
//            in.close();
//            fos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return queryOk(null);
//    }
//

//
//    @RequestMapping(path = "/deleteFile", method = RequestMethod.POST)
//    @ApiOperation(value = "删除图片信息", notes = "删除图片信息")
//    @ApiResponses({@ApiResponse(code = 200, message = "处理成功", response = FileInfo.class)})
//    public String deleteFile(String id) {
//        if (StringUtils.isBlank(id)) {
//            return paramsError();
//        }
//        logger.info("删除图片信息，参数：{}", id);
//        try {
//            fileService.deleteFile(id);
//            return updateOk(null);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return error(null, OperateTypeEm.UPDATE.toString());
//        }
//    }

}
