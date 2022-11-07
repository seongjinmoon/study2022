package egovframework.let.popup.service.impl;

import egovframework.let.popup.service.PopupService;
import egovframework.let.popup.service.PopupVO;
import egovframework.let.utl.fcc.service.EgovDateUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

import java.util.ArrayList;
import java.util.HashMap;
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
    
    //팝업 리스트Hash
    private HashMap<String, List<EgovMap>> popupHash = new HashMap<String, List<EgovMap>>();
    
    //변수관련 Hash
    private HashMap<String, String> cacheMap = new HashMap<String, String>();
    
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
		
		//팝업목록 초기화
		this.popupHash.remove("popupList");
		
		return id;
  	}
  	
	//팝업상세
	public EgovMap selectPopup(PopupVO vo) throws Exception{
		return popupMapper.selectPopup(vo);
	}
	
	//팝업 수정하기
	public void updatePopup(PopupVO vo) throws Exception{
		popupMapper.updatePopup(vo);
		
		//팝업목록 초기화
		this.popupHash.remove("popupList");
	}
	
	//팝업 삭제하기
	public void deletePopup(PopupVO vo) throws Exception{
		popupMapper.deletePopup(vo);
		
		//팝업목록 초기화
		this.popupHash.remove("popupList");
	}
	
	//서비스 팝업 목록 가져오기
	public List<EgovMap> selectPopupServiceList(PopupVO vo) throws Exception{
		List<EgovMap> popupList = new ArrayList<EgovMap>();
		String cacheDay = this.cacheMap.get("today");
		String today = EgovDateUtil.getToday("yyyyMMdd");
		
		//캐시 메모리에 팝업목록이 있는지 체크
		if(!this.popupHash.containsKey("popupList") || !today.equals(cacheDay)) {
			List<EgovMap> resultList = popupMapper.selectPopupList(vo);
			if(resultList != null && resultList.size() > 0){
				for(int i = 0; i < resultList.size(); i++) {
					long sl = Long.parseLong(resultList.get(i).get("ntceBgnde").toString().replaceAll("-", ""));
		    		long el = Long.parseLong(resultList.get(i).get("ntceEndde").toString().replaceAll("-", ""));
					long cl = Long.parseLong(EgovDateUtil.getToday("yyyyMMdd"));
					if(cl <= el && cl >= sl) {
						popupList.add(resultList.get(i));
					}
				}
			}
			//팝업 저장
			this.popupHash.remove("popupList");
			this.popupHash.put("popupList", popupList);
			
			//날짜 저장
			this.cacheMap.remove("today");
			this.cacheMap.put("today", EgovDateUtil.getToday("yyyyMMdd"));
		}else {
			popupList = this.popupHash.get("popupList");
		}
		
  		return popupList;
  	}
	
}
