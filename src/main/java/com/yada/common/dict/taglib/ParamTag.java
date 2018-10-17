package com.yada.common.dict.taglib;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.tags.form.AbstractHtmlElementTag;
import org.springframework.web.servlet.tags.form.TagWriter;

@SuppressWarnings("serial")
public class ParamTag extends AbstractHtmlElementTag {

	/** 输出为input tag */
	public static final String TYPE_INPUT_TAG = "inputTag";
	/** 输出为query string */
	public static final String TYPE_QUERY_STRING = "queryString";
	/** 输出为query string */
	public static final String TYPE_QUERY_STRING_UTF = "queryStringUtf";

	/**
	 * 指定包含的parameters，如果没有指定，则包含全部。可以使用*、?作为通配符。
	 */
	private String includes;
	/**
	 * @see #TYPE_INPUT_TAG
	 * @see #TYPE_QUERY_STRING
	 * @see #TYPE_QUERY_STRING_UTF
	 */
	private String type;

	@Override
	public void release() {
		includes = null;
		type = null;
		super.release();
	}

	@Override
	protected int writeTagContent(TagWriter tagWriter) throws JspException {
		try {
			pageContext.getRequest().setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException uee) {
			throw new JspTagException(uee.getMessage());
		}

		Enumeration<String> keys = pageContext.getRequest().getParameterNames();
		String str = "";
		if (StringUtils.isBlank(type) || StringUtils.equals(TYPE_QUERY_STRING_UTF, type)) {
			str = buildQueryStringUtf(keys);
		} else if (StringUtils.isNotBlank(type) && StringUtils.equals(TYPE_INPUT_TAG, type)) {
			str = buildInputTags(keys);
		} else if (StringUtils.isNotBlank(type) && StringUtils.equals(TYPE_QUERY_STRING, type)) {
			str = buildQueryString(keys);
		}
		try {
			pageContext.getOut().write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return BodyTagSupport.EVAL_PAGE;
	}

	/**
	 * 根据参数构造queryString
	 * 
	 * @param keys
	 *            参数名称
	 */
	private String buildQueryString(Enumeration<String> keys) {
		StringBuffer buf = new StringBuffer();

		while (keys.hasMoreElements()) {
			String key = keys.nextElement();

			if (!isInclude(key)) {
				continue;
			}
			String value = pageContext.getRequest().getParameter(key);

			if (StringUtils.isBlank(value)) {
				continue;
			}

			buf.append(key).append("=").append(value);

			if (keys.hasMoreElements()) {
				buf.append("&");
			}
		}
		return buf.toString();
	}

	/**
	 * 根据参数构造queryString
	 * 
	 * @param keys
	 *            参数名称
	 */
	private String buildQueryStringUtf(Enumeration<String> keys) {
		StringBuffer buf = new StringBuffer();

		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			if (!isInclude(key)) {
				continue;
			}
			String value = pageContext.getRequest().getParameter(key);

			try {
				value = java.net.URLEncoder.encode(value, "utf-8");
			} catch (Exception e) {

			}

			if (StringUtils.isBlank(value)) {
				continue;
			}

			buf.append(key).append("=").append(value);

			if (keys.hasMoreElements()) {
				buf.append("&");
			}
		}
		return buf.toString();
	}

	/**
	 * 根据参数构造Input
	 * 
	 * @param keys
	 *            参数名称
	 */
	private String buildInputTags(Enumeration<String> keys) {
		StringBuffer buf = new StringBuffer();

		while (keys.hasMoreElements()) {
			String key = keys.nextElement();

			if (!isInclude(key)) {
				continue;
			}

			String value = pageContext.getRequest().getParameter(key);

			buf.append("<input type='hidden' name='").append(key).append("' value='").append((value == null) ? StringUtils.EMPTY : value).append("'/>");
		}

		return buf.toString();
	}

	/**
	 * 判断某个参数是否可以包含。
	 */
	private boolean isInclude(String target) {
		if (StringUtils.isBlank(includes)) {
			return true;
		}

		PathMatcher matcher = new AntPathMatcher();
		// Set<String> incs = StringUtil.parseCommaSplitedString(includes);

		String[] incsArr = includes.split(",");
		Set<String> incs = new HashSet<String>();
		for (int j = 0; j < incsArr.length; j++) {
			incs.add(incsArr[j]);
		}
		for (String inc : incs) {
			if (matcher.match(inc, target)) {
				return true;
			}
		}

		return false;
	}

	public String getIncludes() {
		return includes;
	}

	public void setIncludes(String includes) {
		this.includes = includes;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
