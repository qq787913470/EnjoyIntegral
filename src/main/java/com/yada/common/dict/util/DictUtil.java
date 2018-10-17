package com.yada.common.dict.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.yada.security.dao.impl.DictItemDaoImpl;
import com.yada.security.dao.impl.DictListDaoImpl;
import com.yada.security.model.DictItem;
import com.yada.security.model.DictList;

public class DictUtil {

	private static DictListDaoImpl dictListDaoImpl;
	private static DictItemDaoImpl dictItemDaoImpl;
	private static Map<String, List<DictItem>> dictMap = new HashMap<String, List<DictItem>>();
	private static JdbcTemplate jdbcTemplate;
	private static List<String> dynamicDict = new ArrayList<String>();

	@Autowired
	public void setDictListDaoImpl(DictListDaoImpl dictListDaoImpl) {
		DictUtil.dictListDaoImpl = dictListDaoImpl;
	}

	@Autowired
	public void setDictItemDaoImpl(DictItemDaoImpl dictItemDaoImpl) {
		DictUtil.dictItemDaoImpl = dictItemDaoImpl;
	}

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		DictUtil.jdbcTemplate = jdbcTemplate;
	}

	public static Map<String, List<DictItem>> getDictMap() {
		return dictMap;
	}
	
	public static List<DictItem> getDictByType(String dictType){
		//模拟初始化
		List<DictItem> list = dictMap.get(dictType);
		if(list == null || list.size()==0){
			initDictMap(dictType);
		}
		return dictMap.get(dictType);
	}
	
	public static void initDictMap(String dictType) {
		
		DictList temp = null;
		
		if(dictType != null){
			temp = new DictList(dictType);
		}
		
		List<DictList> list = dictListDaoImpl.findListByWhere(temp);
		if(list == null && list.size()==0){
			return ;
		}
		for(DictList dictList : list){
			List<DictItem> dictItems = null;
			if(dictList.getDictflag().equals("2")){
				dictItems = jdbcTemplate.query(dictList.getDicttableSql(), new DictMapper());
				dynamicDict.add(dictList.getDicttype());
			}else{
				DictItem dictItem = new DictItem();
				dictItem.setDicttype(dictList.getDicttype());
				dictItems = dictItemDaoImpl.findListByWhere(dictItem);
			}
			dictMap.put(dictList.getDicttype(), dictItems);
		}
		
	}

	public static void initDictMap(){
		initDictMap(null);
	}
	
	public static void remove (String key){
		dictMap.remove(key);
	}
	
	protected static class DictMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			DictItem dictItem = new DictItem();
			dictItem.setDictcode(rs.getString(1));
			dictItem.setDictcodename(rs.getString(2));
			return dictItem;
		}
	}

	public static List<String> getDynamicDict() {
		return dynamicDict;
	}
	
	
}
