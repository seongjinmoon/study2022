package egovframework.let.admin.banner.web;

import java.util.List;
import java.util.Map;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.let.banner.service.BannerService;
import egovframework.let.banner.service.BannerVO;
import egovframework.let.utl.fcc.service.EgovStringUtil;
import egovframework.let.utl.fcc.service.FileMngUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class BannerAdminController {
	
	@Resource(name = "bannerService")
    private BannerService bannerService;
	
	@Resource(name = "fileMngUtil")
    private FileMngUtil fileUtil;
	
	@Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;
	
	//배너 목록 가져오기
	@RequestMapping("/admin/banner/selectList.do")
	public String selectList(@ModelAttribute("searchVO") BannerVO bannerVO, HttpServletRequest request, ModelMap model) throws Exception{
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(user == null || EgovStringUtil.isEmpty(user.getId())){
	    	model.addAttribute("message", "로그인 후 사용가능합니다.");
	    	return "forward:/index.do"; //메인페이지로 이동
		}else {
			model.addAttribute("USER_INFO", user);
		}
		
		PaginationInfo paginationInfo = new PaginationInfo();

		paginationInfo.setCurrentPageNo(bannerVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(bannerVO.getPageUnit());
		paginationInfo.setPageSize(bannerVO.getPageSize());

		bannerVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		bannerVO.setLastIndex(paginationInfo.getLastRecordIndex());
		bannerVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<EgovMap> resultList = bannerService.selectBannerList(bannerVO);
		model.addAttribute("resultList", resultList);
		
		int totCnt = bannerService.selectBannerListCnt(bannerVO);
		
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		//배너경로
		String bannerWebPath = propertyService.getString("banner.fileStoreWebPath");
		model.addAttribute("bannerWebPath", bannerWebPath);
		
		return "admin/banner/BannerList";
	}
	
	//배너 등록/수정
	@RequestMapping("/admin/banner/regist.do")
	public String regist(@ModelAttribute("searchVO") BannerVO bannerVO, HttpServletRequest request, ModelMap model) throws Exception{
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(user == null || EgovStringUtil.isEmpty(user.getId())){
	    	model.addAttribute("message", "로그인 후 사용가능합니다.");
	    	return "forward:/index.do"; //메인페이지로 이동
		}else {
			model.addAttribute("USER_INFO", user);
		}
		
		EgovMap result = new EgovMap();
		if(!EgovStringUtil.isEmpty(bannerVO.getBannerId())) {
			result = bannerService.selectBanner(bannerVO);
		}
		model.addAttribute("result", result);
		
		request.getSession().removeAttribute("sessionBanner");
		
		return "admin/banner/BannerRegist";
	}
		
	///배너 등록하기
	@RequestMapping("/admin/banner/insert.do")
	public String insert(final MultipartHttpServletRequest multiRequest, @ModelAttribute("searchVO") BannerVO bannerVO, HttpServletRequest request, ModelMap model) throws Exception{
		//이중 서브밋 방지 체크
		if(request.getSession().getAttribute("sessionBanner") != null){
			return "forward:/admin/banner/selectList.do";
		}
				
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(user == null || EgovStringUtil.isEmpty(user.getId())){
	    	model.addAttribute("message", "로그인 후 사용가능합니다.");
	    	return "forward:/index.do"; //메인페이지로 이동
		}else {
			model.addAttribute("USER_INFO", user);
			bannerVO.setUserId(user.getId());
		}
		
		List<FileVO> result = null;
		String bannerImage = "";
		String bannerImageFile = "";

		final Map<String, MultipartFile> files = multiRequest.getFileMap();

		if(!files.isEmpty()) {
			result = fileUtil.directParseFileInf(files, "BNR_", 0, "", "banner.fileStorePath");

			if(result != null && result.size() > 0) {
				FileVO vo = (FileVO)result.get(0);
				bannerImage = vo.getOrignlFileNm();
				bannerImageFile = vo.getStreFileNm();
			}
		}
		
		bannerVO.setBannerImage(bannerImage);
		bannerVO.setBannerImageFile(bannerImageFile);
		
		bannerService.insertBanner(bannerVO);
		
		//이중 서브밋 방지
		request.getSession().setAttribute("sessionBanner", bannerVO);
		
		return "redirect:/admin/banner/selectList.do";
	}
	
	//배너 수정하기
	@RequestMapping("/admin/banner/update.do")
	public String update(final MultipartHttpServletRequest multiRequest, @ModelAttribute("searchVO") BannerVO bannerVO, HttpServletRequest request, ModelMap model) throws Exception{
		//이중 서브밋 방지 체크
		if(request.getSession().getAttribute("sessionBanner") != null){
			return "forward:/admin/banner/selectList.do";
		}
				
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(user == null || EgovStringUtil.isEmpty(user.getId())){
	    	model.addAttribute("message", "로그인 후 사용가능합니다.");
	    	return "forward:/index.do"; //메인페이지로 이동
		}else {
			model.addAttribute("USER_INFO", user);
			bannerVO.setUserId(user.getId());
		}
		
		List<FileVO> result = null;
		String bannerImage = "";
		String bannerImageFile = "";

		final Map<String, MultipartFile> files = multiRequest.getFileMap();

		if(!files.isEmpty()) {
			result = fileUtil.directParseFileInf(files, "BNR_", 0, "", "banner.fileStorePath");

			if(result != null && result.size() > 0) {
				FileVO vo = (FileVO)result.get(0);
				bannerImage = vo.getOrignlFileNm();
				bannerImageFile = vo.getStreFileNm();
			}
		}
		
		bannerVO.setBannerImage(bannerImage);
		bannerVO.setBannerImageFile(bannerImageFile);
		
		bannerService.updateBanner(bannerVO);
		
		//이중 서브밋 방지
		request.getSession().setAttribute("sessionBanner", bannerVO);
				
		return "forward:/admin/banner/selectList.do";
	}
	
	//배너 삭제하기
	@RequestMapping("/admin/banner/delete.do")
	public String delete(@ModelAttribute("searchVO") BannerVO bannerVO, HttpServletRequest request, ModelMap model) throws Exception{
		//이중 서브밋 방지 체크
		if(request.getSession().getAttribute("sessionBanner") != null){
			return "forward:/admin/banner/selectList.do";
		}
				
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(user == null || EgovStringUtil.isEmpty(user.getId())){
	    	model.addAttribute("message", "로그인 후 사용가능합니다.");
	    	return "forward:/index.do"; //메인페이지로 이동
		}else {
			model.addAttribute("USER_INFO", user);
			bannerVO.setUserId(user.getId());
		}
		
		bannerService.deleteBanner(bannerVO);
		
		//이중 서브밋 방지
		request.getSession().setAttribute("sessionBanner", bannerVO);
				
		return "forward:/admin/banner/selectList.do";
	}
	
}