package in.ac.dei.edrp.admissionsystem.tlds;

import in.ac.dei.edrp.admissionsystem.common.CommonBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class SelectTagHandler extends SimpleTagSupport implements DynamicAttributes
{
	private List<CommonBean> optionList = new ArrayList<CommonBean>();
	private String name;
	private String defaultText;
	private String selectedValue;
	
	
	private Map<String, Object>  tagAttrs = new HashMap<String, Object>();
	
	private static final String ATTR_TEMPLATE = "%s='%s'";
	private static final String OPTION_TEMPLATE = "<option value='%1$s'>%2$s </option>";
	private static final String SELECTED_OPTION_TEMPLATE = "<option value='%1$s' selected='selected'>%2$s </option>";
	
	public void doTag() throws JspException, IOException
	{
		PageContext pageContext = (PageContext) getJspContext();
		JspWriter out = pageContext.getOut();
		out.println("<select ");
		out.println(String.format(ATTR_TEMPLATE, "name", this.name));
		for(String attrName : tagAttrs.keySet())
		{
			String attrDefinition = String.format(ATTR_TEMPLATE, attrName, tagAttrs.get(attrName));
			out.println(attrDefinition);
		}
		out.println(">");
		out.println("<option value=''>"+defaultText);
		
		for(CommonBean option : this.optionList)
		{
			String optionTag = null;
			if(option.getCode().trim().equalsIgnoreCase(selectedValue))
			{
				optionTag = String.format(SELECTED_OPTION_TEMPLATE, option.getCode(),option.getDescription());
			}
			else
			{
				optionTag = String.format(OPTION_TEMPLATE, option.getCode(),option.getDescription());
			}
			 
			out.println(optionTag);
		}
		
		out.println(" </select>");
	}
	
	public void setOptionList(List<CommonBean> optionList)
	{
		this.optionList = optionList;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	
	public void setDefaultText(String defaultText)
	{
		this.defaultText = defaultText;
	}
	public void setSelectedValue(String selectedValue)
	{
		this.selectedValue = selectedValue;
	}
	
	
	public void setDynamicAttribute(String uri, String name, Object value)
			throws JspException {
		tagAttrs.put(name, value);
		
	}
	
}
