package everyos.browser.webicity.renderer.html.dom.html;

import everyos.browser.webicity.renderer.html.dom.NodeList;

public interface HTMLInputElement extends HTMLElement {
	//[HTMLConstructor] constructor();

	String getAccept();
	void setAccept(String accept);
	String getAlt();
	void setAlt(String alt);
	String getAutocomplete();
	void setAutocomplete(String autocomplete);
	boolean getDefaultChecked();
	void setDefaultChecked(boolean defaultChecked);
	boolean getChecked();
	void setChecked(boolean checked);
	String getDirName();
	void setDirName(String name);
	boolean getDisabled();
	void setDisabled(boolean disabled);
	HTMLFormElement getForm();
	FileList getFiles();
	void setFiles(FileList files);
	String getFormAction();
	void setFormAction(String formAction);
	String getFormEnctype();
	void setFormEnctype(String incType);
	String getFormMethod();
	void setFormMethod(String formMethod);
	boolean getFormNoValidate();
	void setFormNoValidate(boolean formNoValidate);
	String getFormTarget();
	void setFormTarget(String formTarget);
	long getHeight();
	void setHeight(long height);
	boolean getIndeterminate();
	void setIndererminate(boolean indeterminate);
	HTMLElement getList();
	String getMax();
	void setMax(String max);
	long getMaxLength();
	void setMaxLength(long maxLength);
	String getMin();
	void setMin(String min);
	long getMinLength();
	void setMinLength();
	boolean getMultiple();
	void setMultiple(boolean multiple);
	String getName();
	void setName(String name);
	String getPattern();
	void setPattern(String pattern);
	String getPlaceholder();
	void setPlaceholder(String placeholder);
	boolean getReadOnly();
	void setReadOnly(boolean readOnly);
	boolean getRequired();
	void setRequired(boolean required);
	long getSize();
	void setSize(long size);
	String getSrc();
	void setSrc(String src);
	String getStep();
	void setStep(String step);
	String getType();
	void setType(String type);
	String getDefaultValue();
	void setDefaultValue();
	String getValue();
	void setValue(String value);
	Object getValueAsDate();
	void setValueAsDate(String valueAsDate);
	double getValueAsNumber();
	void setValueAsNumber(double valueAsNumber);
	long getWidth();
	void setWidth(long width);

	void stepUp(long n);
	void stepDown(long n);

	boolean getWillValidate();
	ValidityState getValidity();
	String getValidationMessage();
	boolean checkValidity();
	boolean reportValidity();
	void setCustomValidity(String error);

	NodeList getLabels();

	void select();
	long getSelectionStart();
	long getSelectionEnd();
	String getSelectionDirection();
	void setSelectionDirection();
	void setRangeText(String replacement);
	void setRangeText(String replacement, long start, long end, SelectionMode selectionMode);
	void setSelectionRange(long start, long end, String direction);
}
