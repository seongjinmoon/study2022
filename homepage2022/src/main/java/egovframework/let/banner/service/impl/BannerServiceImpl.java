package egovframework.let.banner.service.impl;

import egovframework.let.banner.service.BannerService;
import egovframework.let.banner.service.BannerVO;
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


@Service("bannerService")
public class BannerServiceImpl extends EgovAbstractServiceImpl implements BannerService {

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;
    
    @Resource(name="bannerMapper")
	private BannerMapper bannerMapper;
    
    @Resource(name = "bannerIdGnrService")
    private EgovIdGnrService idgenService;
    
    //배너 리스트Hash
    private HashMap<String, List<EgovMap>> bannerHash = new HashMap<String, List<EgovMap>>();
    
    //변수관련 Hash
    private HashMap<String, String> cacheMap = new HashMap<String, String>();
    
    //배너 목록 가져오기
  	public List<EgovMap> selectBannerList(BannerVO vo) throws Exception{
  		return bannerMapper.selectBannerList(vo);
  	}
  	
  	//배너 목록 수
  	public int selectBannerListCnt(BannerVO vo) throws Exception{
  		return bannerMapper.selectBannerListCnt(vo);
  	}
  	
  	//배너 등록하기
  	public String insertBanner(BannerVO vo) throws Exception{
  		String id = idgenService.getNextStringId();
		vo.setBannerId(id);
		bannerMapper.insertBanner(vo);
		
		//팝업목록 초기화
		this.bannerHash.remove("bannerList");
		
		return id;
  	}
  	
	//배너상세
	public EgovMap selectBanner(BannerVO vo) throws Exception{
		return bannerMapper.selectBanner(vo);
	}
	
	//배너 수정하기
	public void updateBanner(BannerVO vo) throws Exception{
		bannerMapper.updateBanner(vo);
		
		//배너목록 초기화
		this.bannerHash.remove("bannerList");
	}
	
	//배너 삭제하기
	public void deleteBanner(BannerVO vo) throws Exception{
		bannerMapper.deleteBanner(vo);
		
		//배너목록 초기화
		this.bannerHash.remove("bannerList");
	}
	
	//서비스 배너 목록 가져오기
	public List<EgovMap> selectBannerServiceList(BannerVO vo) throws Exception{
		List<EgovMap> bannerList = new ArrayList<EgovMap>();
		String cacheDay = this.cacheMap.get("today");
		String today = EgovDateUtil.getToday("yyyyMMdd");
		
		//캐시 메모리에 배너목록이 있는지 체크
		if(!this.bannerHash.containsKey("popupList") || !today.equals(cacheDay)) {
			List<EgovMap> resultList = bannerMapper.selectBannerList(vo);
			if(resultList != null && resultList.size() > 0){
				for(int i = 0; i < resultList.size(); i++) {
					long sl = Long.parseLong(resultList.get(i).get("ntceBgnde").toString().replaceAll("-", ""));
		    		long el = Long.parseLong(resultList.get(i).get("ntceEndde").toString().replaceAll("-", ""));
					long cl = Long.parseLong(EgovDateUtil.getToday("yyyyMMdd"));
					if(cl <= el && cl >= sl) {
						bannerList.add(resultList.get(i));
					}
				}
			}
			//배너 저장
			this.bannerHash.remove("bannerList");
			this.bannerHash.put("bannerList", bannerList);
			
			//날짜 저장
			this.cacheMap.remove("today");
			this.cacheMap.put("today", EgovDateUtil.getToday("yyyyMMdd"));
		}else {
			bannerList = this.bannerHash.get("bannerList");
		}
		
  		return bannerList;
  	}
	
}
