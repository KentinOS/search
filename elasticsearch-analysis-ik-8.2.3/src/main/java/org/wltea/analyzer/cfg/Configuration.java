/**
 * 
 */
package org.wltea.analyzer.cfg;

import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.core.PathUtils;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.plugin.analysis.ik.AnalysisIkPlugin;
import org.wltea.analyzer.dic.Dictionary;

import java.io.File;
import java.nio.file.Path;

public class Configuration {

	private Environment environment;
	private Settings settings;

	//是否启用智能分词
	private  boolean useSmart;

	//是否启用远程词典加载
	private boolean enableRemoteDict=false;

	//是否启用小写处理
	private boolean enableLowercase=true;

	//是否启用英文前缀
	private boolean enableEnglishPrefix=false;

	//是否启用英文后缀
	private boolean enableEnglishSuffix=false;

	//是否启用数字前缀
	private boolean enableArabicPrefix=false;

	//是否启用数字后缀
	private boolean enableArabicSuffix=false;


	@Inject
	public Configuration(Environment env,Settings settings) {
		this.environment = env;
		this.settings=settings;

		this.useSmart = settings.get("use_smart", "false").equals("true");
		this.enableLowercase = settings.get("enable_lowercase", "true").equals("true");
		this.enableRemoteDict = settings.get("enable_remote_dict", "true").equals("true");
		this.enableArabicSuffix = settings.get("enable_arabic_prefix", "false").equals("true");
		this.enableArabicSuffix = settings.get("enable_arabic_suffix", "false").equals("true");
		this.enableEnglishPrefix = settings.get("enable_english_prefix", "false").equals("true");
		this.enableEnglishSuffix = settings.get("enable_english_suffix", "false").equals("true");

		Dictionary.initial(this);

	}

	public Path getConfigInPluginDir() {
		return PathUtils
				.get(new File(AnalysisIkPlugin.class.getProtectionDomain().getCodeSource().getLocation().getPath())
						.getParent(), "config")
				.toAbsolutePath();
	}

	public boolean isUseSmart() {
		return useSmart;
	}

	public Configuration setUseSmart(boolean useSmart) {
		this.useSmart = useSmart;
		return this;
	}

	public Environment getEnvironment() {
		return environment;
	}

	public Settings getSettings() {
		return settings;
	}

	public boolean isEnableRemoteDict() {
		return enableRemoteDict;
	}

	public boolean isEnableLowercase() {
		return enableLowercase;
	}

	public boolean isEnableEnglishPrefix() {
		return enableEnglishPrefix;
	}

	public boolean isEnableEnglishSuffix() {
		return enableEnglishSuffix;
	}

	public boolean isEnableArabicPrefix() {
		return enableArabicPrefix;
	}

	public boolean isEnableArabicSuffix() {
		return enableArabicSuffix;
	}

}
