package egovframework.let.banner.service;

import java.io.Serializable;

import egovframework.com.cmm.ComDefaultVO;


public class BannerVO extends ComDefaultVO implements Serializable {

	//배너ID
	private String bannerId;
	
	//배너명
	private String bannerNm;
	
	//팝업창위치_세로
	private String bannerImage;
	
	//팝업창위치_가로
	private String bannerImageFile;
	
	//팝업내용
	private String bannerDc;
	
	//게시시작일
	private String ntceBgnde;
	
	//게시종료일
	private String ntceEndde;
	
	//링크URL
	private String liknUrl;
	
	//새창여부
	private String popupTrgetAt;
	
	//사용여부
	private String useAt;
	
	//최초등록시점
	private java.util.Date frstRegistPnttm;
	
	//최초등록자ID
	private String frstRegisterId;
	
	//최종수정시점
	private java.util.Date lastUpdtPnttm;
	
	//최종수정자ID
	private String lastUpdusrId;

	//사용자ID
	private String userId;

	public String getBannerId() {
		return bannerId;
	}

	public String getBannerNm() {
		return bannerNm;
	}

	public String getBannerImage() {
		return bannerImage;
	}

	public String getBannerImageFile() {
		return bannerImageFile;
	}

	public String getBannerDc() {
		return bannerDc;
	}

	public String getNtceBgnde() {
		return ntceBgnde;
	}

	public String getNtceEndde() {
		return ntceEndde;
	}

	public String getLiknUrl() {
		return liknUrl;
	}

	public String getPopupTrgetAt() {
		return popupTrgetAt;
	}

	public String getUseAt() {
		return useAt;
	}

	public java.util.Date getFrstRegistPnttm() {
		return frstRegistPnttm;
	}

	public String getFrstRegisterId() {
		return frstRegisterId;
	}

	public java.util.Date getLastUpdtPnttm() {
		return lastUpdtPnttm;
	}

	public String getLastUpdusrId() {
		return lastUpdusrId;
	}

	public String getUserId() {
		return userId;
	}

	public void setBannerId(String bannerId) {
		this.bannerId = bannerId;
	}

	public void setBannerNm(String bannerNm) {
		this.bannerNm = bannerNm;
	}

	public void setBannerImage(String bannerImage) {
		this.bannerImage = bannerImage;
	}

	public void setBannerImageFile(String bannerImageFile) {
		this.bannerImageFile = bannerImageFile;
	}

	public void setBannerDc(String bannerDc) {
		this.bannerDc = bannerDc;
	}

	public void setNtceBgnde(String ntceBgnde) {
		this.ntceBgnde = ntceBgnde;
	}

	public void setNtceEndde(String ntceEndde) {
		this.ntceEndde = ntceEndde;
	}

	public void setLiknUrl(String liknUrl) {
		this.liknUrl = liknUrl;
	}

	public void setPopupTrgetAt(String popupTrgetAt) {
		this.popupTrgetAt = popupTrgetAt;
	}

	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}

	public void setFrstRegistPnttm(java.util.Date frstRegistPnttm) {
		this.frstRegistPnttm = frstRegistPnttm;
	}

	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	public void setLastUpdtPnttm(java.util.Date lastUpdtPnttm) {
		this.lastUpdtPnttm = lastUpdtPnttm;
	}

	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	
}