package com.cognizant.beans;

import java.util.List;
import java.util.Map;

public class Layers {

	private List<String> bottom;
	private float click_x;
	private float click_y;
	private List<String> contains;
	private Map<String,String> cssStyles;
	private String elementName;
	private String height;
	private List<String> left;
	private List<String> parent;
	
	private List<String> right;
	private String styleGuideX;
	private String styleGuideY;
	private List<String> top;
	private String width;
	private int xMouseClickPoint;
	private int yMouseClickPoint;
	private String text;
	private String actElementName;
	private String elementType;
	
	
	public Layers(List<String> bottom, float click_x, float click_y, List<String> contains,
			Map<String, String> cssStyles, String elementName, String height, List<String> left, List<String> parent,
			List<String> right, String styleGuideX, String styleGuideY, List<String> top, String width,
			int xMouseClickPoint, int yMouseClickPoint, String text, String actElementName, String elementType) {
		super();
		this.bottom = bottom;
		this.click_x = click_x;
		this.click_y = click_y;
		this.contains = contains;
		this.cssStyles = cssStyles;
		this.elementName = elementName;
		this.height = height;
		this.left = left;
		this.parent = parent;
		this.right = right;
		this.styleGuideX = styleGuideX;
		this.styleGuideY = styleGuideY;
		this.top = top;
		this.width = width;
		this.xMouseClickPoint = xMouseClickPoint;
		this.yMouseClickPoint = yMouseClickPoint;
		this.text = text;
		this.actElementName = actElementName;
		this.elementType = elementType;
	}


	public List<String> getBottom() {
		return bottom;
	}


	public void setBottom(List<String> bottom) {
		this.bottom = bottom;
	}


	public float getClick_x() {
		return click_x;
	}


	public void setClick_x(float click_x) {
		this.click_x = click_x;
	}


	public float getClick_y() {
		return click_y;
	}


	public void setClick_y(float click_y) {
		this.click_y = click_y;
	}


	public List<String> getContains() {
		return contains;
	}


	public void setContains(List<String> contains) {
		this.contains = contains;
	}


	public Map<String, String> getCssStyles() {
		return cssStyles;
	}


	public void setCssStyles(Map<String, String> cssStyles) {
		this.cssStyles = cssStyles;
	}


	public String getElementName() {
		return elementName;
	}


	public void setElementName(String elementName) {
		this.elementName = elementName;
	}


	public String getHeight() {
		return height;
	}


	public void setHeight(String height) {
		this.height = height;
	}


	public List<String> getLeft() {
		return left;
	}


	public void setLeft(List<String> left) {
		this.left = left;
	}


	public List<String> getParent() {
		return parent;
	}


	public void setParent(List<String> parent) {
		this.parent = parent;
	}


	public List<String> getRight() {
		return right;
	}


	public void setRight(List<String> right) {
		this.right = right;
	}


	public String getStyleGuideX() {
		return styleGuideX;
	}


	public void setStyleGuideX(String styleGuideX) {
		this.styleGuideX = styleGuideX;
	}


	public String getStyleGuideY() {
		return styleGuideY;
	}


	public void setStyleGuideY(String styleGuideY) {
		this.styleGuideY = styleGuideY;
	}


	public List<String> getTop() {
		return top;
	}


	public void setTop(List<String> top) {
		this.top = top;
	}


	public String getWidth() {
		return width;
	}


	public void setWidth(String width) {
		this.width = width;
	}


	public int getxMouseClickPoint() {
		return xMouseClickPoint;
	}


	public void setxMouseClickPoint(int xMouseClickPoint) {
		this.xMouseClickPoint = xMouseClickPoint;
	}


	public int getyMouseClickPoint() {
		return yMouseClickPoint;
	}


	public void setyMouseClickPoint(int yMouseClickPoint) {
		this.yMouseClickPoint = yMouseClickPoint;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public String getActElementName() {
		return actElementName;
	}


	public void setActElementName(String actElementName) {
		this.actElementName = actElementName;
	}


	public String getElementType() {
		return elementType;
	}


	public void setElementType(String elementType) {
		this.elementType = elementType;
	}


	@Override
	public String toString() {
		
		
		return "Layers [bottom=" + bottom + ", click_x=" + click_x
				+ ", click_y=" + click_y + ", contains=" + contains
				+ ", cssStyles=" + cssStyles + ", elementName=" + elementName
				+ ", height=" + height + ", left=" + left + ", parent="
				+ parent + ", right=" + right + ", styleGuideX=" + styleGuideX
				+ ", styleGuideY=" + styleGuideY + ", top=" + top + ", width="
				+ width + ", xMouseClickPoint=" + xMouseClickPoint
				+ ", yMouseClickPoint=" + yMouseClickPoint + ", text=" + text
				+", actElementName=" + actElementName + ", elementType=" + elementType + ",]\n";
	}

}
