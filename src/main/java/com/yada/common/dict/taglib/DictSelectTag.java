package com.yada.common.dict.taglib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.springframework.web.servlet.tags.form.AbstractHtmlElementTag;
import org.springframework.web.servlet.tags.form.TagWriter;

import com.yada.common.dict.util.DictUtil;
import com.yada.security.model.DictItem;

@SuppressWarnings("serial")
public class DictSelectTag extends AbstractHtmlElementTag {

	private String id;
	
	/** 下拉菜单缺省值 */
	protected String value;

	/** 不使用property时定义的下拉菜单名 */
	protected String name;

	/** 接受actionform 的属性 */
	protected String property;

	/** 页面传入的list数据 */
	protected String listName;

	/** 内存中字典数据名 */
	protected String dictName;

	/** <optin>中的value */
	protected String itemKey;
	
	/** <optin></optin>中的文本值 */
	protected String itemValue;
	
	protected String multiple;

	protected String size;
	
	protected String styleClass;
	
	protected String onchange;

	protected String[] match;

	/** 是否禁止首项为空 */
	protected String notEmpty;

	private String disabled;
	
	private HttpServletRequest request ;
	
	private TagWriter tagWriter;
	
	@Override
	protected int writeTagContent(TagWriter tagWriter) throws JspException {
		
		this.tagWriter = tagWriter;
		tagWriter.startTag("select");
		if(name != null){
			tagWriter.writeAttribute("name", name);
		}
		if(multiple != null){
			tagWriter.writeAttribute("multiple", multiple);
		}
		if (size != null) {
			tagWriter.writeAttribute("size", size);
		}
		if (styleClass != null) {
			tagWriter.writeAttribute("styleClass", styleClass);
		}
		if (onchange != null) {
			tagWriter.writeAttribute("onchange", onchange);
		}
		if (disabled != null) {
			tagWriter.writeAttribute("disabled", disabled);
		}
		if (id != null) {
			tagWriter.writeAttribute("id", id);
		}
		
		return EVAL_BODY_INCLUDE;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public int doEndTag() throws JspException {
		List<DictItem> list = null;
		if(dictName != null){
			 list = DictUtil.getDictByType(dictName);
			
		}else if(listName != null){//如果是使用的listName形式的非字典数据则进行如下处理。
			list = new ArrayList<DictItem>();
			//获取数据集合名称
			List<Object> items = (List) super.pageContext.getRequest().getAttribute(listName);
			//反射Object的itemKey、itemValue字段的值。其中itemKey、itemValue是标签中的属性。不可以为null或者''!
			for(Object obj : items){
				
				String itemKey_vaule = "";
				String itemValue_vaule = "";
				
				try {
					itemKey_vaule = obj.getClass().getMethod(creatMethodName(itemKey)).invoke(obj).toString();
					itemValue_vaule = obj.getClass().getMethod(creatMethodName(itemValue)).invoke(obj).toString();
				} catch (Exception e) {
					e.printStackTrace();
				} 
				//转换成DictItem对象
				DictItem dictItem = new DictItem();
				dictItem.setDictcode(itemKey_vaule);
				dictItem.setDictcodename(itemValue_vaule);
				
				list.add(dictItem);
			}
		}
		
		Map<String, String> attrs = null;
		
		if(list != null && list.size() != 0){
			if(notEmpty!=null&&notEmpty.equals("false"))
			creatOptin("", "--请选择--");
			
			for(DictItem dictItem :list){
				attrs = new HashMap<String, String>();
				if(dictItem.getDictcode().equals(value)){
					attrs.put("selected", "selected");
				}
				creatOptin(dictItem.getDictcode()+"", dictItem.getDictcodename(),attrs);
			}
		}else{
			creatOptin("", "没有数据");
		}
		
		tagWriter.endTag();
		return EVAL_PAGE;
	}
	
	//拼取方法名称
	private String creatMethodName(String fieldName){
		
		if(fieldName == null || fieldName.equals("")){
			throw new RuntimeException("fieldName mast be not null or ''");
		}
		//拼取getXxx()方法。
		StringBuffer buf = new StringBuffer();
		buf.append("get");
		buf.append(fieldName.substring(0, 1).toUpperCase());
		if(fieldName.length()>1){
			buf.append(fieldName.substring(1));
		}
		return buf.toString();
	}
	
	private void creatOptin(String value, String text,Map<String, String> attrs ) throws JspException{
		tagWriter.startTag("option");
		if(attrs != null && attrs.size()>0){
			Iterator<String> iterator =  attrs.keySet().iterator();
			while(iterator.hasNext()){
				String key = iterator.next();
				tagWriter.writeAttribute(key, attrs.get(key));
			}
		}
		tagWriter.writeAttribute("value", value);
		tagWriter.appendValue(text);
		tagWriter.endTag();
	}
	
	private void creatOptin(String value, String text) throws JspException{
		creatOptin(value, text, null);
	}
	
	

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getMultiple() {
		return multiple;
	}

	public void setMultiple(String multiple) {
		this.multiple = multiple;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public String getOnchange() {
		return onchange;
	}

	public void setOnchange(String onchange) {
		this.onchange = onchange;
	}

	public String[] getMatch() {
		return match;
	}

	public void setMatch(String[] match) {
		this.match = match;
	}

	public String getNotEmpty() {
		return notEmpty;
	}

	public void setNotEmpty(String notEmpty) {
		this.notEmpty = notEmpty;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public TagWriter getTagWriter() {
		return tagWriter;
	}

	public void setTagWriter(TagWriter tagWriter) {
		this.tagWriter = tagWriter;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public String getItemKey() {
		return itemKey;
	}

	public void setItemKey(String itemKey) {
		this.itemKey = itemKey;
	}

	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	} 
	
	
}
