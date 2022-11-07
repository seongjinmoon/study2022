package egovframework.let.banner.web;

import java.util.List;

import egovframework.let.banner.service.BannerService;
import egovframework.let.banner.service.BannerVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BannerController {
	
	@Resource(name = "bannerService")
    private BannerService bannerService;
	
	@Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;
	
	//배너 목록 가져오기
	@RequestMapping("/banner/bannerService.do")
	public String selectList(@ModelAttribute("searchVO") BannerVO bannerVO, HttpServletRequest request, ModelMap model) throws Exception{
		bannerVO.setRecordCountPerPage(Integer.MAX_VALUE);
		bannerVO.setFirstIndex(0);
		
		List<EgovMap> resultList = bannerService.selectBannerServiceList(bannerVO);
		model.addAttribute("resultList", resultList);
		
		String bannerWebPath = propertyService.getString("banner.fileStoreWebPath");
		model.addAttribute("bannerWebPath", bannerWebPath);
		
		return "banner/BannerService";
	}
	
}