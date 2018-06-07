package test;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import my.dao.utils.MysqlPOGen;
import my.dao.utils.OraclePOGen;

public class TestDataGen {
	public static void main(String[] args) {
		try {

//			OraclePOGen.gen("Kpi_value_cross_colname_v", "Kpi_value_cross_colname_v");
//			OraclePOGen.gen("Kpi_value_cross_rowname_v", "Kpi_value_cross_rowname_v");
//			OraclePOGen.gen("sys_role_menu", "sys_role_menu_v");
//			OraclePOGen.gen("sys_user_erp", "sys_user_erp_v");
//			OraclePOGen.gen("ZX_IMPORT_FROMEXCEL", "ZX_IMPORT_FROMEXCEL_v"); 
			OraclePOGen.gen("SYS_USER_MENU_ROLE_V", "SYS_USER_MENU_ROLE_V"); 
			
//			OraclePOGen.gen("zx_pub_relation_query_dtl_v", "zx_pub_relation_query_dtl_v"); 
//			OraclePOGen.gen("SYS_USER_MENU_ROLE_V", "SYS_USER_MENU_ROLE_V");
//			OraclePOGen.gen("SYS_MENU", "SYS_MENU_V");
//			OraclePOGen.gen("WX_KPI_CHARTS", "WX_KPI_CHARTS");
//			OraclePOGen.gen("KPI_VALUE", "KPI_VALUE_V");
//			OraclePOGen.gen("ZM_IND_DEPT_SCHDTYL", "ZM_IND_DEPT_SCHDTYL");
//			OraclePOGen.gen("ZM_IND_SETTLEMENT", "ZM_IND_SETTLEMENT");
//			OraclePOGen.gen("ZM_IND_DEPT_SETTLEMENT", "ZM_IND_DEPT_SETTLEMENT");
//			OraclePOGen.gen("ZM_IND_DEPT_XTCZBT", "ZM_IND_DEPT_XTCZBT");
//			OraclePOGen.gen("wx_branch", "wx_branch");
//			OraclePOGen.gen("wx_dict", "wx_dict");
//			OraclePOGen.gen("wx_doctor", "wx_doctor_v");
//			OraclePOGen.gen("wx_hospital", "wx_hospitald");
//			OraclePOGen.gen("wx_patient", "wx_patient");
//			OraclePOGen.gen("wx_site", "wx_site");
//			OraclePOGen.gen("wx_sysparm", "wx_sysparm");
//			
			
//			OraclePOGen.gen("IM_GROUP_USER", "IM_GROUP_USER");
//			OraclePOGen.gen("IM_MSG", "IM_MSG");
//			OraclePOGen.gen("IM_MSG_STATUS", "IM_MSG_STATUS");
//			OraclePOGen.gen("IM_USER", "IM_USER");
//			OraclePOGen.gen("im_friendlist", "im_friendlist");
//			OraclePOGen.gen("im_friend_request", "im_friend_request");
//			OraclePOGen.gen("im_user_group_define", "im_user_group_define");
//			OraclePOGen.gen("im_user_group_def_user", "im_user_group_def_user");
//			
			
			
			//MysqlPOGen.gen("log_shipping_evaluate", "log_shipping_evaluate", "log");
			
			
//			MysqlPOGen.gen("sys_user_role", "sys_user_role", "sys");
//			MysqlPOGen.gen("sys_tenant_paymentrecord",
//					"sys_tenant_paymentrecord", "sys");
//			MysqlPOGen.gen("SYS_TENANT_AP", "SYS_TENANT_AP", "sys");
//			MysqlPOGen.gen("SYS_TENANT", "SYS_TENANT", "sys");
//			
//			MysqlPOGen.gen("sys_role_ap", "sys_role_ap", "sys");
//			MysqlPOGen.gen("sys_role", "sys_role", "sys");
//			MysqlPOGen.gen("sys_menu_action", "sys_menu_action", "sys");
//			MysqlPOGen.gen("sys_menu", "sys_menu", "sys");
//			MysqlPOGen.gen("sys_DICT", "sys_DICT", "sys");
//			MysqlPOGen.gen("sys_actionlog", "sys_actionlog", "sys");
			
//			MysqlPOGen.gen("sys_province", "sys_province", "sys");
//			MysqlPOGen.gen("sys_city", "sys_city", "sys");
//			MysqlPOGen.gen("sys_district", "sys_district", "sys");
			
//			MysqlPOGen.gen("jo_user", "jo_user", "");
//			MysqlPOGen.gen("jb_user", "jb_user", "");
//			MysqlPOGen.gen("jb_user_ext", "jb_user_ext", "");
			
// 			MysqlPOGen.gen("sys_auth", "sys_auth", "api");
// 			MysqlPOGen.gen("sys_upload_log", "sys_upload_log", "api");
// 			MysqlPOGen.gen("LOG_DELIVERY_DOC", "LOG_DELIVERY_DOC", "api");
// 			MysqlPOGen.gen("LOG_DELIVERY_DTL", "LOG_DELIVERY_DTL", "api");
// 			MysqlPOGen.gen("LOG_SHIPPING_DOC", "LOG_SHIPPING_DOC", "api");
// 			MysqlPOGen.gen("LOG_SHIPPING_DTL", "LOG_SHIPPING_DTL", "api");
// 			MysqlPOGen.gen("LOG_SHIPPING_VEHICLE", "LOG_SHIPPING_VEHICLE", "api");

			//MysqlPOGen.gen("sys_files", "sys_files", "file");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
