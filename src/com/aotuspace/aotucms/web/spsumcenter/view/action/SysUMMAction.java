package com.aotuspace.aotucms.web.spsumcenter.view.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aotuspace.aotucms.web.base.BaseAction;
import com.aotuspace.aotucms.web.model.PageBean;
import com.aotuspace.aotucms.web.spsumcenter.hbm.SpSupplierBinfo;
import com.aotuspace.aotucms.web.spsumcenter.hbm.SpSupplierBinfoKey;
import com.aotuspace.aotucms.web.spsumcenter.hbm.SpSupplierDinfo;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * Title:SysSMMAction
 * Description:系统供应商管理
 * Company:aotuspace
 * @author    sida
 * @date      2015-10-12 下午5:29:45
 *
 */
@Controller
@Scope("prototype")
public class SysUMMAction extends BaseAction<SpSupplierBinfo> {

	//供应商ids
	public List<SpSupplierBinfoKey> supIds;
	
	//供应商id
	private SpSupplierBinfoKey spSupplierBinfoKey;
	
	//供应商信息列表页面
	public String list() throws Exception{
		return "list";
	}
	
	//供应商信息data
	public String listData() throws Exception{
		PageBean<SpSupplierBinfo> spSupplierBinfoList =iSysUMMService.findSpSupplierBinfoList(rows,page);
		//封装json
		Map<String, Object> pageListMap = new HashMap<String, Object>();
		List<Map<String, Object>> listMaps = new ArrayList<Map<String,Object>>();
		for (SpSupplierBinfo spSupplierBinfo : spSupplierBinfoList.getRecordList()) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("sp_id", spSupplierBinfo.getSpSupplierBinfoKey().getSpId());//序号id
			rowMap.put("sp_SuId", spSupplierBinfo.getSpSupplierBinfoKey().getSpSuid());//供应商id
			rowMap.put("sp_SuAccount", spSupplierBinfo.getSpSuaccount());//供应商帐号
			rowMap.put("sp_SuPassword", spSupplierBinfo.getSpSupassword());//供应商密码
			rowMap.put("sp_SuSup", spSupplierBinfo.getSpSupplierDinfo().getSpSusup());//商家名称
			rowMap.put("sp_SuCont", spSupplierBinfo.getSpSupplierDinfo().getSpSucont());//商家联系人姓名
			rowMap.put("sp_SuMobie", spSupplierBinfo.getSpSupplierDinfo().getSpSumobie());//商家手机号码
			rowMap.put("sp_SuTel", spSupplierBinfo.getSpSupplierDinfo().getSpSutel());//商家联系电话
			rowMap.put("sp_SuDistrict", spSupplierBinfo.getSpSupplierDinfo().getSpSudistrict());//商家所在地
			listMaps.add(rowMap);
		}
		//easyui total 总数rows列表
		pageListMap.put("total", spSupplierBinfoList.getRecordList());//总条数
		pageListMap.put("rows", listMaps);//列表
		resp.getWriter().write(objectMapper.writeValueAsString(pageListMap));
		return NONE;
	}
	
	//供应商信息详细
	public String detail() throws Exception{
		//查询供应商信息详细
		SpSupplierBinfo spSupplierBinfoDetail = iSysUMMService.getByObject(model.getSpSupplierBinfoKey());
		ActionContext.getContext().put("spSupplierBinfoDetail", spSupplierBinfoDetail);
		return "detail";
	}
	
	//检查供应商是否重复
	public String checkSuAccount() throws Exception{
		//服务器端查询供应商是否存在
		SpSupplierBinfo spSupplierBinfo = iSysUMMService.findBySpSuAccount(model.getSpSuaccount());
		if(spSupplierBinfo==null){
			jsonResult.setCode(0);
			jsonResult.setMsg("请求成功");
		}else{
			jsonResult.setCode(100001);
			jsonResult.setMsg("请求失败");
		}
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	//添加供应商
	public String add() throws Exception{
		//如果用户名没有重复，则保存到数据库
		SpSupplierBinfo spSupplierBinfo = iSysUMMService.findBySpSuAccount(model.getSpSuaccount());
		if(spSupplierBinfo == null){
			//1:把最大的Suid查出(service接口实现类，根据供应商信息的spid进行降序，并且获取一条select top 1 from 表 order by desc <-- 是一个对象，而不是一个list)
			Integer maxSuId = iSysUMMService.findByMaxSuId();
			SpSupplierBinfoKey newKey = new SpSupplierBinfoKey();
			if(maxSuId == null){
				newKey.setSpSuid(100000);
			}else{
				newKey.setSpSuid(maxSuId+1);
			}
			model.setSpSupplierBinfoKey(newKey);
		//详细信息---------
			SpSupplierDinfo spSupplierDinfo = model.getSpSupplierDinfo();
			spSupplierDinfo.setSpSupplierBinfoKey(newKey);
			spSupplierDinfo.setSpSutraid(0);
			model.setSpSupplierDinfo(spSupplierDinfo);
		//2:增加到数据库
		iSysUMMService.save(model);
		//成功
		jsonResult.setCode(0);
		jsonResult.setMsg("请求成功");
		}else{//如果供应商重复，则提示重新输入
			//失败
			jsonResult.setCode(100001);
			jsonResult.setMsg("请求失败");
		}
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	//删除供应商
	public String delete() throws Exception{
		//获取供应商的源对象
		iSysUMMService.deleteByList(supIds);
		jsonResult.setCode(0);
		jsonResult.setMsg("请求成功");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	//更改供应商页面
	public String edit() throws Exception{
		//获取源对象回显
		SpSupplierBinfo spSupplierBinfo = iSysUMMService.getByObject(model.getSpSupplierBinfoKey());
		if(spSupplierBinfo!=null){
			objectMapper.registerModule(new Hibernate4Module());
			jsonResult.setCode(0);
			jsonResult.setMsg("请求成功");
			jsonResult.setData("["+objectMapper.writeValueAsString(spSupplierBinfo)+"]");
		}else{
			jsonResult.setCode(100001);
			jsonResult.setMsg("请求失败");
		}
		resp.getWriter().write("["+objectMapper.writeValueAsString(jsonResult)+"]");
		return NONE;
	}
	
	//更改供应商
	public String editSubmit() throws Exception{
		//从数据库获取源对象
		SpSupplierBinfo spSupplierBinfo = iSysUMMService.getByObject(model.getSpSupplierBinfoKey());
		if(spSupplierBinfo!=null){
			//设置要修改的属性
			spSupplierBinfo.setSpSupassword(model.getSpSupassword());
			//详细信息
			spSupplierBinfo.getSpSupplierDinfo().setSpSusup(model.getSpSupplierDinfo().getSpSusup());//商家名称
			spSupplierBinfo.getSpSupplierDinfo().setSpSucont(model.getSpSupplierDinfo().getSpSucont());//商家联系人姓名
			spSupplierBinfo.getSpSupplierDinfo().setSpSutel(model.getSpSupplierDinfo().getSpSutel());//商家联系电话
			spSupplierBinfo.getSpSupplierDinfo().setSpSumobie(model.getSpSupplierDinfo().getSpSumobie());//商家手机号码
			spSupplierBinfo.getSpSupplierDinfo().setSpSudistrict(model.getSpSupplierDinfo().getSpSudistrict());//商家所在地
			spSupplierBinfo.getSpSupplierDinfo().setSpSuaddress(model.getSpSupplierDinfo().getSpSuaddress());//商家详细地址
			spSupplierBinfo.getSpSupplierDinfo().setSpSutraid(model.getSpSupplierDinfo().getSpSutraid());//商家所属行业ID
			spSupplierBinfo.getSpSupplierDinfo().setSpSuresume(model.getSpSupplierDinfo().getSpSuresume());//商家简介
			spSupplierBinfo.getSpSupplierDinfo().setSpSulogo(model.getSpSupplierDinfo().getSpSulogo());//商家logo
			//更新到数据库
			iSysUMMService.update(spSupplierBinfo);
			jsonResult.setCode(0);
			jsonResult.setMsg("请求成功");
		}else{
			jsonResult.setCode(0);
			jsonResult.setMsg("请求失败");
		}
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	//-------------------------------------------
	public List<SpSupplierBinfoKey> getSupIds() {
		return supIds;
	}

	@JSON(serialize = true,deserialize = true)
	public void setSupIds(List<SpSupplierBinfoKey> supIds) {
		this.supIds = supIds;
	}

	public SpSupplierBinfoKey getSpSupplierBinfoKey() {
		return spSupplierBinfoKey;
	}

	@JSON(serialize = true, deserialize = true)
	public void setSpSupplierBinfoKey(SpSupplierBinfoKey spSupplierBinfoKey) {
		this.spSupplierBinfoKey = spSupplierBinfoKey;
	}
	
	
}
