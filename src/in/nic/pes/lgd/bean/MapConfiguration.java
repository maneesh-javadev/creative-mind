package in.nic.pes.lgd.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * MapConfiguration.java.
 * @category Database Entity 
 * @author Vinay Yadav
 */

@SequenceGenerator(name="map_conf_seq", sequenceName="map_config_seq")
@Entity
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, include="hibernate.cache.use_second_level_cache")
@Table(name = "map_configuration",  schema = "public")
public class MapConfiguration implements java.io.Serializable {
	private static final long serialVersionUID = 689858276093988108L;

	
	
	
	@Id
	@GeneratedValue(generator="map_conf_seq", strategy=GenerationType.SEQUENCE)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "level")
	private Integer level;
	
	@Column(name = "name", length=255)
	private String name;
	
	@Column(name = "base_url")
	private String baseUrl;
	
	@Column(name = "parent_base_url")
	private String parentBaseUrl;
	
	@Column(name = "param_name")
	private String paramName;
	
	@Column(name = "attribute_name")
	private String attributeName;
	
	@Column(name = "token")
	private String token;
	
	@Column(name = "attribute_code_name")
	private String attributeCodeName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getParentBaseUrl() {
		return parentBaseUrl;
	}

	public void setParentBaseUrl(String parentBaseUrl) {
		this.parentBaseUrl = parentBaseUrl;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAttributeCodeName() {
		return attributeCodeName;
	}

	public void setAttributeCodeName(String attributeCodeName) {
		this.attributeCodeName = attributeCodeName;
	}

	
	
		
}