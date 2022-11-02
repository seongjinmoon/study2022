package egovframework.let.banner.service.impl;

import java.util.List;

import egovframework.let.banner.service.BannerVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Mapper("bannerMapper")
public interface BannerMapper {
	
	//배너 목록 가져오기
  	List<EgovMap> selectBannerList(BannerVO vo) throws Exception;
  	
  	//배너 목록 수
  	int selectBannerListCnt(BannerVO vo) throws Exception;
  	
  	//배너 등록하기
  	void insertBanner(BannerVO vo) throws Exception;
  	
	//배너상세
  	EgovMap selectBanner(BannerVO vo) throws Exception;
	
	//배너 수정하기
	void updateBanner(BannerVO vo) throws Exception;
	
	//배너 삭제하기
	void deleteBanner(BannerVO vo) throws Exception;
	
}
