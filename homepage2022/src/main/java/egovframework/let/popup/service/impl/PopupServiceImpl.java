package egovframework.let.popup.service.impl;

import egovframework.let.popup.service.PopupService;
import egovframework.let.popup.service.PopupVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


@Service("popupService")
public class PopupServiceImpl extends EgovAbstractServiceImpl implements PopupService {

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;
    
    @Resource(name="popupMapper")
	private PopupMapper popupMapper;
    
    @Resource(name = "popupIdGnrService")
    private EgovIdGnrService idgenService;
    
    //팝업 목록 가져오기
  	public List<EgovMap> selectPopupList(PopupVO vo) throws Exception{
  		return popupMapper.selectPopupList(vo);
  	}
  	
  	//팝업 목록 수
  	public int selectPopupListCnt(PopupVO vo) throws Exception{
  		return popupMapper.selectPopupListCnt(vo);
  	}
  	
  	//팝업 등록하기
  	public String insertPopup(PopupVO vo) throws Exception{
  		String id = idgenService.getNextStringId();
		vo.setPopupId(id);
		popupMapper.insertPopup(vo);
		
		return id;
  	}
  	
	//팝업상세
	public EgovMap selectPopup(PopupVO vo) throws Exception{
		return popupMapper.selectPopup(vo);
	}
	
	//팝업 수정하기
	public void updatePopup(PopupVO vo) throws Exception{
		popupMapper.updatePopup(vo);
	}
	
	//팝업 삭제하기
	public void deletePopup(PopupVO vo) throws Exception{
		popupMapper.deletePopup(vo);
	}
	
	//서비스 팝업 목록 가져오기
	public List<EgovMap> selectPopupServiceList(PopupVO vo) throws Exception{
		//캐시 메로지 작업
		
		
  		return popupMapper.selectPopupList(vo);
  	}
	
}
