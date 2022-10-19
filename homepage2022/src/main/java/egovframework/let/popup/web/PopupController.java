package egovframework.let.popup.web;

import java.util.List;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.let.popup.service.PopupService;
import egovframework.let.popup.service.PopupVO;
import egovframework.let.utl.fcc.service.EgovStringUtil;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PopupController {
	
	@Resource(name = "popupService")
    private PopupService popupService;
	
	//팝업 목록 가져오기
	@RequestMapping("/popup/selectList.do")
	public String selectList(@ModelAttribute("searchVO") PopupVO popupVO, HttpServletRequest request, ModelMap model) throws Exception{
		popupVO.setRecordCountPerPage(Integer.MAX_VALUE);
		
		List<EgovMap> resultList = popupService.selectPopupServiceList(popupVO);
		model.addAttribute("resultList", resultList);
		
		return "popup/PopupList";
	}
	
	//팝업 상세
	@RequestMapping("/popup/select.do")
	public String select(@ModelAttribute("searchVO") PopupVO popupVO, HttpServletRequest request, ModelMap model) throws Exception{
		EgovMap result = popupService.selectPopup(popupVO);
		model.addAttribute("result", result);
		
		return "popup/Popup";
	}
		
	
}