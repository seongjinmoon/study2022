package egovframework.let.utl.fcc.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import egovframework.com.cmm.service.FileVO;
import egovframework.let.utl.fcc.service.EgovStringUtil;

import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Component("fileMngUtil")
public class FileMngUtil {

    public static final int BUFF_SIZE = 2048;

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;

    @Resource(name = "egovFileIdGnrService")
    private EgovIdGnrService idgenService;

    //첨부파일에 대한 목록 정보를 취득한다.
    public List<FileVO> parseFileInf(Map<String, MultipartFile> files, String KeyStr, int fileKeyParam, String atchFileId, String storePath) throws Exception {
		int fileKey = fileKeyParam;
		
		//파일저장경로
		String storePathString = "";
		//첨부파일ID
		String atchFileIdString = "";
	
		//파일 저장경로 여부
		if ("".equals(storePath) || storePath == null) {
		    storePathString = propertyService.getString("Globals.fileStorePath");
		} else {
		    storePathString = propertyService.getString(storePath);
		}
		
		//첨부파일ID 생성 및 업데이트 여부
		if ("".equals(atchFileId) || atchFileId == null) {
		    atchFileIdString = idgenService.getNextStringId();
		} else {
		    atchFileIdString = atchFileId;
		}
		
		//폴더경로 설정
		File saveFolder = new File(storePathString);
		if (!saveFolder.exists() || saveFolder.isFile()) {
		    saveFolder.mkdirs();
		}
		
		//파일변수
		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
		MultipartFile file;
		String filePath = "";
		List<FileVO> result  = new ArrayList<FileVO>();
		FileVO fvo;
	
		while (itr.hasNext()) {
		    Entry<String, MultipartFile> entry = itr.next();
	
		    file = entry.getValue();
		    String orginFileName = file.getOriginalFilename();
	
		    //--------------------------------------
		    // 원 파일명이 없는 경우 처리
		    // (첨부가 되지 않은 input file type)
		    //--------------------------------------
		    if("".equals(orginFileName)) {
		    	continue;
		    }
		    ////------------------------------------
		    
		    //파일확장자 체크
		    int index = orginFileName.lastIndexOf(".");
		    String fileExt = orginFileName.substring(index + 1);
		    
		    //저장파일명
		    String newName = KeyStr + EgovStringUtil.getTimeStamp() + fileKey;
		    
		    //파일사이즈
		    long size = file.getSize();
		    
		    //파일저장
		    if (!"".equals(orginFileName)) {
				filePath = storePathString + File.separator + newName;
				file.transferTo(new File(filePath));
		    }
		    fvo = new FileVO();
		    fvo.setFileExtsn(fileExt);
		    fvo.setFileStreCours(storePathString);
		    fvo.setFileMg(Long.toString(size));
		    fvo.setOrignlFileNm(orginFileName);
		    fvo.setStreFileNm(newName);
		    fvo.setAtchFileId(atchFileIdString);
		    fvo.setFileSn(String.valueOf(fileKey));
	
		    result.add(fvo);
	
		    fileKey++;
		}
	
		return result;
    }
    
    //첨부파일 바로저장(확장자 포함)
    public List<FileVO> directParseFileInf(Map<String, MultipartFile> files, String KeyStr, int fileKeyParam, String atchFileId, String storePath) throws Exception {
		int fileKey = fileKeyParam;
		
		//파일저장경로
		String storePathString = "";
	
		//파일 저장경로 여부
		if ("".equals(storePath) || storePath == null) {
		    storePathString = propertyService.getString("Globals.fileStorePath");
		} else {
		    storePathString = propertyService.getString(storePath);
		}
		
		//폴더경로 설정
		File saveFolder = new File(storePathString);
		if (!saveFolder.exists() || saveFolder.isFile()) {
		    saveFolder.mkdirs();
		}
		
		//파일변수
		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
		MultipartFile file;
		String filePath = "";
		List<FileVO> result  = new ArrayList<FileVO>();
		FileVO fvo;
	
		while (itr.hasNext()) {
		    Entry<String, MultipartFile> entry = itr.next();
	
		    file = entry.getValue();
		    String orginFileName = file.getOriginalFilename();
	
		    //--------------------------------------
		    // 원 파일명이 없는 경우 처리
		    // (첨부가 되지 않은 input file type)
		    //--------------------------------------
		    if("".equals(orginFileName)) {
		    	continue;
		    }
		    ////------------------------------------
		    
		    //파일확장자 체크
		    int index = orginFileName.lastIndexOf(".");
		    String fileExt = orginFileName.substring(index + 1);
		    
		    //저장파일명
		    String newName = KeyStr + EgovStringUtil.getTimeStamp() + fileKey + "." + fileExt;
		    
		    //파일사이즈
		    long size = file.getSize();
		    
		    //파일저장
		    if (!"".equals(orginFileName)) {
				filePath = storePathString + File.separator + newName;
				file.transferTo(new File(filePath));
		    }
		    fvo = new FileVO();
		    fvo.setFileExtsn(fileExt);
		    fvo.setFileStreCours(storePathString);
		    fvo.setFileMg(Long.toString(size));
		    fvo.setOrignlFileNm(orginFileName);
		    fvo.setStreFileNm(newName);
		    fvo.setFileSn(String.valueOf(fileKey));
	
		    result.add(fvo);
	
		    fileKey++;
		}
	
		return result;
    }

}
