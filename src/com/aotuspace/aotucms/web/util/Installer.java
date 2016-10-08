package com.aotuspace.aotucms.web.util;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.aotuspace.aotucms.web.spsysmcenter.hbm.SpEmployeePrivilege;
import com.aotuspace.aotucms.web.spsysmcenter.service.ISysMMService;

@Component
public class Installer {

	@Resource
	SessionFactory sessionFactory;
	@Resource
	ISysMMService iSysMMService;
	
	/**
	 * 执行安装
	 * @throws Exception 
	 */
	
	@Transactional
	public void install() throws Exception{
		System.out.println("执行安装程序...");
		Session session  = sessionFactory.getCurrentSession();
		
		/*//保存超级管理员用户
		SpEmployeeBinfo defaultEmpl = new SpEmployeeBinfo();
		defaultEmpl.setSpEpaccount("admin");//账号
		defaultEmpl.setSpEppassword("admin");//密码
		//员工id
		Integer maxEpId = iSysMMService.findByMaxEpId();
		SpEmployeeBinfoKey newKey=new SpEmployeeBinfoKey();
		if (maxEpId == null) {
			newKey.setSpEpid(100000);
			defaultEmpl.setSpEmployeeBinfoKey(newKey);
		}
		newKey.setSpEpid(maxEpId + 1);
		defaultEmpl.setSpEmployeeBinfoKey(newKey);
		//部门
		
		session.save(defaultEmpl); //保存
*/
		//保存权限数据
		SpEmployeePrivilege menu, menu1,menu2,menu3,menu4,menu5,menu6;
		
		//系统管理中心模块(权限名称，地址，图标，展开状态，父节点)
		menu =new SpEmployeePrivilege("系统管理中心",null,null,"open",null);
		menu1 =new SpEmployeePrivilege("系统员工管理","sysmm_list",null,"open",menu);
		menu2 =new SpEmployeePrivilege("系统组织机构管理","sysdm_list",null,"open",menu);
		menu3 =new SpEmployeePrivilege("系统角色管理","sysrm_list",null,"open",menu);
		menu4 =new SpEmployeePrivilege("系统操作权限管理","syspm_list",null,"open",menu);
		menu5 =new SpEmployeePrivilege("系统角色权限管理","sysrpm_list",null,"open",menu);
		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);
		session.save(menu4);
		session.save(menu5);
		
		session.save(new SpEmployeePrivilege("组织机构列表","sysmm_departTree",null,null,menu1));
		session.save(new SpEmployeePrivilege("系统员工列表","sysmm_listData",null,null,menu1));
		session.save(new SpEmployeePrivilege("系统员工添加","sysmm_add",null,null,menu1));
		session.save(new SpEmployeePrivilege("系统员工删除","sysmm_delete",null,null,menu1));
		session.save(new SpEmployeePrivilege("系统员工修改","sysmm_edit",null,null,menu1));
		
		session.save(new SpEmployeePrivilege("系统组织机构列表","sysmd_listData",null,null,menu2));
		session.save(new SpEmployeePrivilege("系统组织机构添加","sysmd_add",null,null,menu2));
		session.save(new SpEmployeePrivilege("系统组织机构删除","sysmd_delete",null,null,menu2));
		session.save(new SpEmployeePrivilege("系统组织机构修改","sysmd_edit",null,null,menu2));
	
		session.save(new SpEmployeePrivilege("系统角色列表","sysrm_listData",null,null,menu3));
		session.save(new SpEmployeePrivilege("系统角色添加","sysrm_add",null,null,menu3));
		session.save(new SpEmployeePrivilege("系统角色删除","sysrm_delete",null,null,menu3));
		session.save(new SpEmployeePrivilege("系统角色修改","sysrm_edit",null,null,menu3));	
		
		session.save(new SpEmployeePrivilege("系统操作权限列表","syspm_listData",null,null,menu4));
		session.save(new SpEmployeePrivilege("系统操作权限添加","syspm_add",null,null,menu4));
		session.save(new SpEmployeePrivilege("系统操作权限删除","syspm_delete",null,null,menu4));
		session.save(new SpEmployeePrivilege("系统操作权限修改","syspm_edit",null,null,menu4));	
		
		session.save(new SpEmployeePrivilege("系统角色列表","sysrm_listData",null,null,menu5));
		session.save(new SpEmployeePrivilege("系统所属角色权限列表","sysrpm_privTree",null,null,menu5));
		session.save(new SpEmployeePrivilege("系统角色权限设置","sysrpm_edit",null,null,menu5));	
		
		//凹凸空间管理中心模块
		//凹凸空间管理中心(权限名称，地址，图标，展开状态，父节点)
		menu =new SpEmployeePrivilege("凹凸空间管理中心",null,null,"open",null);
		menu1 =new SpEmployeePrivilege("凹凸空间用户管理","aotuum_list",null,null,menu);
		menu2 =new SpEmployeePrivilege("凹凸空间用户身份管理","aotuuim_list",null,null,menu);
		menu3 =new SpEmployeePrivilege("凹凸空间角色管理","aoturm_list",null,null,menu);
		menu4 =new SpEmployeePrivilege("凹凸空间权限管理","aotupm_list",null,null,menu);
		menu5 =new SpEmployeePrivilege("凹凸空间代言主播管理","aotuam_index",null,null,menu);
		
		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);
		session.save(menu4);
		session.save(menu5);
		
		session.save(new SpEmployeePrivilege("代言主播申请","aotuam_application",null,null,menu5));
		session.save(new SpEmployeePrivilege("代言主播解约申请","",null,null,menu5));
		
		//凹凸空间进销存管理中心模块
		menu =new SpEmployeePrivilege("凹凸空间进销存管理中心",null,null,"open",null);
		menu1 =new SpEmployeePrivilege("商品管理",null,null,null,menu);
		menu2 =new SpEmployeePrivilege("采购管理",null,null,null,menu);
		menu3 =new SpEmployeePrivilege("仓库管理",null,null,null,menu);
		menu4 =new SpEmployeePrivilege("销售管理",null,null,null,menu);
		menu5 =new SpEmployeePrivilege("宝贝管理",null,null,null,menu);
		
		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);
		session.save(menu4);
		session.save(menu5);
		
		//商品管理
		session.save(new SpEmployeePrivilege("商品信息","commodity_list",null,null,menu1));
		//采购管理
		session.save(new SpEmployeePrivilege("进货订单","purchase_purchaseOrdersAdd",null,null,menu2));
		session.save(new SpEmployeePrivilege("进货入库","purchase_purchaseStorageOrdersAdd",null,null,menu2));
		session.save(new SpEmployeePrivilege("进货查询","purchase_purchaseStorageInquire",null,null,menu2));
		//仓库管理
		session.save(new SpEmployeePrivilege("仓库信息","warehouse_list",null,null,menu3));
		session.save(new SpEmployeePrivilege("仓库商品信息","warehouse_pdList",null,null,menu3));
		//销售管理
		//宝贝管理
		session.save(new SpEmployeePrivilege("宝贝上架订单","treasure_treasureShelvesOrder",null,null,menu5));
		session.save(new SpEmployeePrivilege("上架订单查询","treasure_treasureShelvesOrderList",null,null,menu5));
		menu6=new SpEmployeePrivilege("任务管理","treasuretask_index",null,null,menu5);
		session.save(menu6);
		
		session.save(new SpEmployeePrivilege("发布任务","treasuretask_release",null,null,menu6));
		
		
		System.out.println("安装结束...");
	}
	
	public static void main(String[] args) throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
		Installer installer = (Installer) ac.getBean("installer");
		installer.install();

	}
}
