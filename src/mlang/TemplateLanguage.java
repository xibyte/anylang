package mlang;

import java.util.Arrays;

import mlang.ui.OutlineInfo;

public class TemplateLanguage extends MultiLanguage {

	public TemplateLanguage(Language mainLanguage, Language dataLanguage) {
		super(Arrays.asList(mainLanguage, dataLanguage));
	}
	
	public Language getMainLanguage() {
		return languages.get(0);
	}

	public Language getDataLanguage() {
		return languages.get(1);
	}
	
	@Override
	public Parser getParser() {
		return getMainLanguage().getParser();
	}
	
	@Override
	public OutlineInfo getOutlineInfo() {
		return getMainLanguage().getOutlineInfo();
	}
}
