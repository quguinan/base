package com.cms.util;

import my.dao.BaseDao;
import my.dao.DaoFactory;

public class DaoUtil {
	public static BaseDao getDao(){
		return DaoFactory.get();
	}
}
