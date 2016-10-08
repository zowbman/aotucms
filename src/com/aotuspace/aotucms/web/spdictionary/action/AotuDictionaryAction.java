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
 * Description:Title:AotucmsAction字典action
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-9-21 下午4:34:29
 *
 */

@Controller
@Scope("prototype")
@SuppressWarnings({ "unchecked", "serial", "unused" })
public class AotuDictionaryAction extends ActionSupport implements ServletResponseAware {

	// ========获取jackson对象==========
	private ObjectMapper objectMapper = new ObjectMapper();

	// ========获取HttpServletResponse对象==========
	private HttpServletResponse resp;

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		arg0.setContentType("application/json; charset=utf-8");
		resp = arg0;

	}

	// =======银行=============================
	/**
	 * 银行列表
	 * @return
	 * @throws Exception
	 */
	public String bankList() throws Exception {
		List<SpBanks> spBanks = iBankService.findAll();
		objectMapper.registerModule(new Hibernate4Module());
		objectMapper.setSerializationInclusion(Include.NON_NULL);//空值不序列化
		resp.getWriter().write(objectMapper.writeValueAsString(spBanks));
		return NONE;
	}

	/**
	 * 银行地域
	 * @return
	 * @throws Exception
	 */
	public String bankRegionList() throws Exception {
		//查询省份
		List<SpBankRegion> spBankRegion = iBankRegionService.findSpBankRegionByLevelId(1);
		resp.getWriter().write(objectMapper.writeValueAsString(spBankRegion));
		return NONE;
	}

	//银行id
	private Integer bankId;
	//省id
	private Integer proId;
	//市id
	private Integer cityId;

	/**
	 * 支行列表
	 * @return
	 * @throws Exception 
	 */
	public String bankbanchList() throws Exception {
		List<SpBankBranch> spBankBranchs = iBankService.findBankByIdAndProIdAndCityId(bankId, proId, cityId);
		resp.getWriter().write(objectMapper.writeValueAsString(spBankBranchs));
		return NONE;
	}
	
	
	// =======直播平台=============================
	public String liveStationList()throws Exception{
		List<SpLivestationInfo> spLivestationInfos=iLiveStationService.findAll();
		resp.getWriter().write(objectMapper.writeValueAsString(spLivestationInfos));
		return NONE;
	}

	// ===========service接口========================
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
