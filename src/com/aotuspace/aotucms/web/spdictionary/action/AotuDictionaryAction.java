package com.aotuspace.aotucms.web.spdictionary.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aotuspace.aotucms.web.spdictionary.hbm.bank.SpBankBranch;
import com.aotuspace.aotucms.web.spdictionary.hbm.bank.SpBankRegion;
import com.aotuspace.aotucms.web.spdictionary.hbm.bank.SpBanks;
import com.aotuspace.aotucms.web.spdictionary.hbm.livestation.SpLivestationInfo;
import com.aotuspace.aotucms.web.spdictionary.service.bank.IBankRegionService;
import com.aotuspace.aotucms.web.spdictionary.service.bank.IBankService;
import com.aotuspace.aotucms.web.spdictionary.service.livestation.ILiveStationService;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * Title:AotucmsAction
 * Description:Title:AotucmsAction�ֵ�action
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-9-21 ����4:34:29
 *
 */

@Controller
@Scope("prototype")
@SuppressWarnings({ "unchecked", "serial", "unused" })
public class AotuDictionaryAction extends ActionSupport implements ServletResponseAware {

	// ========��ȡjackson����==========
	private ObjectMapper objectMapper = new ObjectMapper();

	// ========��ȡHttpServletResponse����==========
	private HttpServletResponse resp;

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		arg0.setContentType("application/json; charset=utf-8");
		resp = arg0;

	}

	// =======����=============================
	/**
	 * �����б�
	 * @return
	 * @throws Exception
	 */
	public String bankList() throws Exception {
		List<SpBanks> spBanks = iBankService.findAll();
		objectMapper.registerModule(new Hibernate4Module());
		objectMapper.setSerializationInclusion(Include.NON_NULL);//��ֵ�����л�
		resp.getWriter().write(objectMapper.writeValueAsString(spBanks));
		return NONE;
	}

	/**
	 * ���е���
	 * @return
	 * @throws Exception
	 */
	public String bankRegionList() throws Exception {
		//��ѯʡ��
		List<SpBankRegion> spBankRegion = iBankRegionService.findSpBankRegionByLevelId(1);
		resp.getWriter().write(objectMapper.writeValueAsString(spBankRegion));
		return NONE;
	}

	//����id
	private Integer bankId;
	//ʡid
	private Integer proId;
	//��id
	private Integer cityId;

	/**
	 * ֧���б�
	 * @return
	 * @throws Exception 
	 */
	public String bankbanchList() throws Exception {
		List<SpBankBranch> spBankBranchs = iBankService.findBankByIdAndProIdAndCityId(bankId, proId, cityId);
		resp.getWriter().write(objectMapper.writeValueAsString(spBankBranchs));
		return NONE;
	}
	
	
	// =======ֱ��ƽ̨=============================
	public String liveStationList()throws Exception{
		List<SpLivestationInfo> spLivestationInfos=iLiveStationService.findAll();
		resp.getWriter().write(objectMapper.writeValueAsString(spLivestationInfos));
		return NONE;
	}

	// ===========service�ӿ�========================
	@Resource
	IBankService iBankService;
	@Resource
	IBankRegionService iBankRegionService;
	@Resource
	ILiveStationService iLiveStationService;

	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	public Integer getProId() {
		return proId;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

}
