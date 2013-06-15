package leon.ssi.util;

import static org.springframework.beans.factory.config.PropertyPlaceholderConfigurer.DEFAULT_PLACEHOLDER_PREFIX;
import static org.springframework.beans.factory.config.PropertyPlaceholderConfigurer.DEFAULT_PLACEHOLDER_SUFFIX;
import static org.springframework.beans.factory.config.PropertyPlaceholderConfigurer.SYSTEM_PROPERTIES_MODE_FALLBACK;
import static org.springframework.beans.factory.config.PropertyPlaceholderConfigurer.SYSTEM_PROPERTIES_MODE_OVERRIDE;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationConverter;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.properties.PropertyValueEncryptionUtils;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.Constants;
import org.springframework.util.StringUtils;

/**
 * Creates a commons configuration factory bean, by using the best of both
 * worlds Jakarta Commons Configuration and SpringSource PropertyPlaceHolder
 * 
 * @author lifetragedy
 * @since Apr 28, 2009
 * 
 */
public class CommonsConfigurationFactoryBean extends
		org.springmodules.commons.configuration.CommonsConfigurationFactoryBean {

	protected final Log logger = LogFactory.getLog(getClass());

	private CompositeConfiguration configuration;

	private static final Constants constants = new Constants(
			PropertyPlaceholderConfigurer.class);

	private String placeholderPrefix = DEFAULT_PLACEHOLDER_PREFIX;

	private String placeholderSuffix = DEFAULT_PLACEHOLDER_SUFFIX;

	private int systemPropertiesMode = SYSTEM_PROPERTIES_MODE_FALLBACK;

	private boolean searchSystemEnvironment = true;

	private boolean ignoreUnresolvablePlaceholders = false;

	private StringEncryptor encryptor;

	@SuppressWarnings("unused")
	private String nullValue = null;

	public CommonsConfigurationFactoryBean() {
		super();
	}

	public CommonsConfigurationFactoryBean(Configuration configuration) {
		super(configuration);
	}

	@Override
	public Object getObject() throws Exception {
		return (configuration != null) ? ConfigurationConverter
				.getProperties(configuration) : null;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		super.afterPropertiesSet();
		processConfiguration((Properties) super.getObject());
	}

	@Override
	public CompositeConfiguration getConfiguration() {
		return configuration;
	}

	protected void processConfiguration(final Properties properties) {
		Configuration propertiesConfiguration = new PropertiesConfiguration();
		if (properties != null) {
			for (Iterator iter = properties.entrySet().iterator(); iter
					.hasNext();) {
				Map.Entry entry = (Map.Entry) iter.next();
				String key = (String) entry.getKey();
				String value = parseStringValue((String) entry.getValue(),
						properties, new HashSet());
				if (value != null && value.trim().length() > 0)
					// logger.info("the key======"+key+"   value======"+value);
					propertiesConfiguration.setProperty(key, value);
			}
		}
		configuration = new CompositeConfiguration(propertiesConfiguration);
	}

	// Source taken SpringSource class PropertyPlaceholderConfigurer for the
	// placeholder logic

	/**
	 * Set the prefix that a placeholder string starts with. The default is
	 * "${".
	 * 
	 * @see #DEFAULT_PLACEHOLDER_PREFIX
	 */
	public void setPlaceholderPrefix(String placeholderPrefix) {
		this.placeholderPrefix = placeholderPrefix;
	}

	/**
	 * Set the suffix that a placeholder string ends with. The default is "}".
	 * 
	 * @see #DEFAULT_PLACEHOLDER_SUFFIX
	 */
	public void setPlaceholderSuffix(String placeholderSuffix) {
		this.placeholderSuffix = placeholderSuffix;
	}

	/**
	 * Set the system property mode by the name of the corresponding constant,
	 * e.g. "SYSTEM_PROPERTIES_MODE_OVERRIDE".
	 * 
	 * @param constantName
	 *            name of the constant
	 * @throws java.lang.IllegalArgumentException
	 *             if an invalid constant was specified
	 * @see #setSystemPropertiesMode
	 */
	public void setSystemPropertiesModeName(String constantName)
			throws IllegalArgumentException {
		this.systemPropertiesMode = constants.asNumber(constantName).intValue();
	}

	/**
	 * Set how to check system properties: as fallback, as override, or never.
	 * For example, will resolve ${user.dir} to the "user.dir" system property.
	 * <p>
	 * The default is "fallback": If not being able to resolve a placeholder
	 * with the specified properties, a system property will be tried.
	 * "override" will check for a system property first, before trying the
	 * specified properties. "never" will not check system properties at all.
	 * 
	 * @see #SYSTEM_PROPERTIES_MODE_NEVER
	 * @see #SYSTEM_PROPERTIES_MODE_FALLBACK
	 * @see #SYSTEM_PROPERTIES_MODE_OVERRIDE
	 * @see #setSystemPropertiesModeName
	 */
	public void setSystemPropertiesMode(int systemPropertiesMode) {
		this.systemPropertiesMode = systemPropertiesMode;
	}

	/**
	 * Set whether to search for a matching system environment variable if no
	 * matching system property has been found. Only applied when
	 * "systemPropertyMode" is active (i.e. "fallback" or "override"), right
	 * after checking JVM system properties.
	 * <p>
	 * Default is "true". Switch this setting off to never resolve placeholders
	 * against system environment variables. Note that it is generally
	 * recommended to pass external values in as JVM system properties: This can
	 * easily be achieved in a startup script, even for existing environment
	 * variables.
	 * <p>
	 * <b>NOTE:</b> Access to environment variables does not work on the Sun VM
	 * 1.4, where the corresponding {@link System#getenv} support was disabled -
	 * before it eventually got re-enabled for the Sun VM 1.5. Please upgrade to
	 * 1.5 (or higher) if you intend to rely on the environment variable
	 * support.
	 * 
	 * @see #setSystemPropertiesMode
	 * @see java.lang.System#getProperty(String)
	 * @see java.lang.System#getenv(String)
	 */
	public void setSearchSystemEnvironment(boolean searchSystemEnvironment) {
		this.searchSystemEnvironment = searchSystemEnvironment;
	}

	/**
	 * Set whether to ignore unresolvable placeholders. Default is "false": An
	 * exception will be thrown if a placeholder cannot be resolved.
	 */
	public void setIgnoreUnresolvablePlaceholders(
			boolean ignoreUnresolvablePlaceholders) {
		this.ignoreUnresolvablePlaceholders = ignoreUnresolvablePlaceholders;
	}

	/**
	 * Set a value that should be treated as <code>null</code> when resolved as
	 * a placeholder value: e.g. "" (empty String) or "null".
	 * <p>
	 * Note that this will only apply to full property values, not to parts of
	 * concatenated values.
	 * <p>
	 * By default, no such null value is defined. This means that there is no
	 * way to express <code>null</code> as a property value unless you explictly
	 * map a corresponding value here.
	 */
	public void setNullValue(String nullValue) {
		this.nullValue = nullValue;
	}

	/**
	 * Set the Text based Encryptor which will be used to decrypt the passwords
	 * as per JASYPT
	 * 
	 * @param encryptor
	 */
	public void setEncryptor(StringEncryptor encryptor) {
		this.encryptor = encryptor;
	}

	/**
	 * Parse the given String value recursively, to be able to resolve nested
	 * placeholders (when resolved property values in turn contain placeholders
	 * again).
	 * 
	 * @param strVal
	 *            the String value to parse
	 * @param props
	 *            the Properties to resolve placeholders against
	 * @param visitedPlaceholders
	 *            the placeholders that have already been visited during the
	 *            current resolution attempt (used to detect circular references
	 *            between placeholders). Only non-null if we're parsing a nested
	 *            placeholder.
	 * @throws BeanDefinitionStoreException
	 *             if invalid values are encountered
	 * @see #resolvePlaceholder(String, java.util.Properties, int)
	 */
	@SuppressWarnings("unchecked")
	protected String parseStringValue(String strVal, Properties props,
			Set visitedPlaceholders) throws BeanDefinitionStoreException {

		StringBuffer buf = new StringBuffer(strVal);

		int startIndex = strVal.indexOf(this.placeholderPrefix);
		while (startIndex != -1) {
			int endIndex = findPlaceholderEndIndex(buf, startIndex);
			if (endIndex != -1) {
				String placeholder = buf.substring(startIndex
						+ this.placeholderPrefix.length(), endIndex);
				if (!visitedPlaceholders.add(placeholder)) {
					throw new BeanDefinitionStoreException(
							"Circular placeholder reference '" + placeholder
									+ "' in property definitions");
				}
				// Recursive invocation, parsing placeholders contained in the
				// placeholder key.
				placeholder = parseStringValue(placeholder, props,
						visitedPlaceholders);
				// Now obtain the value for the fully resolved key...
				String propVal = resolvePlaceholder(placeholder, props,
						this.systemPropertiesMode);
				if (propVal != null) {
					// Recursive invocation, parsing placeholders contained in
					// the
					// previously resolved placeholder value.
					propVal = parseStringValue(propVal, props,
							visitedPlaceholders);
					buf.replace(startIndex,
							endIndex + this.placeholderSuffix.length(), propVal);
					if (logger.isTraceEnabled()) {
						logger.trace("Resolved placeholder '" + placeholder
								+ "'");
					}
					startIndex = buf.indexOf(this.placeholderPrefix, startIndex
							+ propVal.length());
				} else if (this.ignoreUnresolvablePlaceholders) {
					// Proceed with unprocessed value.
					startIndex = buf.indexOf(this.placeholderPrefix, endIndex
							+ this.placeholderSuffix.length());
				} else {
					throw new BeanDefinitionStoreException(
							"Could not resolve placeholder '" + placeholder
									+ "'");
				}
				visitedPlaceholders.remove(placeholder);
			} else {
				startIndex = -1;
			}
		}
		return convertPropertyValue(buf.toString());
	}

	private int findPlaceholderEndIndex(CharSequence buf, int startIndex) {
		int index = startIndex + this.placeholderPrefix.length();
		int withinNestedPlaceholder = 0;
		while (index < buf.length()) {
			if (StringUtils.substringMatch(buf, index, this.placeholderSuffix)) {
				if (withinNestedPlaceholder > 0) {
					withinNestedPlaceholder--;
					index = index + this.placeholderSuffix.length();
				} else {
					return index;
				}
			} else if (StringUtils.substringMatch(buf, index,
					this.placeholderPrefix)) {
				withinNestedPlaceholder++;
				index = index + this.placeholderPrefix.length();
			} else {
				index++;
			}
		}
		return -1;
	}

	/**
	 * Resolve the given placeholder using the given properties, performing a
	 * system properties check according to the given mode.
	 * <p>
	 * Default implementation delegates to <code>resolvePlaceholder
	 * (placeholder, props)</code> before/after the system properties check.
	 * <p>
	 * Subclasses can override this for custom resolution strategies, including
	 * customized points for the system properties check.
	 * 
	 * @param placeholder
	 *            the placeholder to resolve
	 * @param props
	 *            the merged properties of this configurer
	 * @param systemPropertiesMode
	 *            the system properties mode, according to the constants in this
	 *            class
	 * @return the resolved value, of null if none
	 * @see #setSystemPropertiesMode
	 * @see System#getProperty
	 * @see #resolvePlaceholder(String, java.util.Properties)
	 */
	protected String resolvePlaceholder(String placeholder, Properties props,
			int systemPropertiesMode) {
		String propVal = null;
		if (systemPropertiesMode == SYSTEM_PROPERTIES_MODE_OVERRIDE) {
			propVal = resolveSystemProperty(placeholder);
		}
		if (propVal == null) {
			propVal = resolvePlaceholder(placeholder, props);
		}
		if (propVal == null
				&& systemPropertiesMode == SYSTEM_PROPERTIES_MODE_FALLBACK) {
			propVal = resolveSystemProperty(placeholder);
		}
		return propVal;
	}

	/**
	 * Resolve the given placeholder using the given properties. The default
	 * implementation simply checks for a corresponding property key.
	 * <p>
	 * Subclasses can override this for customized placeholder-to-key mappings
	 * or custom resolution strategies, possibly just using the given properties
	 * as fallback.
	 * <p>
	 * Note that system properties will still be checked before respectively
	 * after this method is invoked, according to the system properties mode.
	 * 
	 * @param placeholder
	 *            the placeholder to resolve
	 * @param props
	 *            the merged properties of this configurer
	 * @return the resolved value, of <code>null</code> if none
	 * @see #setSystemPropertiesMode
	 */
	protected String resolvePlaceholder(String placeholder, Properties props) {
		return convertPropertyValue(props.getProperty(placeholder));
	}

	/**
	 * Resolve the given key as JVM system property, and optionally also as
	 * system environment variable if no matching system property has been
	 * found.
	 * 
	 * @param key
	 *            the placeholder to resolve as system property key
	 * @return the system property value, or <code>null</code> if not found
	 * @see #setSearchSystemEnvironment
	 * @see java.lang.System#getProperty(String)
	 * @see java.lang.System#getenv(String)
	 */
	protected String resolveSystemProperty(String key) {
		try {
			String value = System.getProperty(key);
			if (value == null && this.searchSystemEnvironment) {
				value = System.getenv(key);
			}
			return value;
		} catch (Throwable ex) {
			if (logger.isDebugEnabled()) {
				logger.debug("Could not access system property '" + key + "': "
						+ ex);
			}
			return null;
		}
	}

	protected String convertPropertyValue(String originalValue) {
		if (!PropertyValueEncryptionUtils.isEncryptedValue(originalValue)) {
			return originalValue;
		}
		if (this.encryptor != null) {
			return PropertyValueEncryptionUtils.decrypt(originalValue,
					this.encryptor);

		}
		return PropertyValueEncryptionUtils.decrypt(originalValue,
				this.encryptor);
	}
}
