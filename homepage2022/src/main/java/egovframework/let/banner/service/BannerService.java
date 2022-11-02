package egovframework.let.banner.service;

import java.util.List;
import egovframework.rte.psl.dataaccess.util.EgovMap;


public interface BannerService {
	
	//배너 목록 가져오기
	public List<EgovMap> selectBannerList(BannerVO vo) throws Exception;
	
	//배너 목록 수
	public int selectBannerListCnt(BannerVO vo) throws Exception;
	
	//배너 등록하기
	public String insertBanner(BannerVO vo) throws Exception;
		
	//배너상세
	public EgovMap selectBanner(BannerVO vo) throws Exception;
	
	//배너 수정하기
	public void updateBanner(BannerVO vo) throws Exception;
	
	//배너 삭제하기
	public void deleteBanner(BannerVO vo) throws Exception;
	
	//서비스 배너 목록 가져오기
	public List<EgovMap> selectBannerServiceList(BannerVO vo) throws Exception;
	
	
	
}