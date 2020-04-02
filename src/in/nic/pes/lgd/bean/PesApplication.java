/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 
package in.nic.pes.lgd.bean;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;


@Entity
@Table(catalog = "pes", schema = "pescommon")
@NamedNativeQueries({@NamedNativeQuery(query="  select * from pescommon.get_all_application(:userId) where pes_application_id not in(0,14,13,9,15) order by  app_disp_order ",
										name="getAllApplication",resultClass=PesApplication.class)})

public class PesApplication implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Basic(optional = false)
	@Column(name = "pes_application_id")
	private Integer pesApplicationId;
	@Column(name = "pes_application_name")
	private String pesApplicationName;
	
	@Column(name = "pes_application_url")
	private String pesApplicationUrl;
	
	@Column(name = "app_disp_order")
	private Integer appDispOrder;
	
	@Column(name="resource_id")
	private String resourceId;
	
	@Column(name="privilage_app_id")
	private Integer privilageAppId;

	public Integer getPesApplicationId() {
		return pesApplicationId;
	}

	public void setPesApplicationId(Integer pesApplicationId) {
		this.pesApplicationId = pesApplicationId;
	}

	public String getPesApplicationName() {
		return pesApplicationName;
	}

	public void setPesApplicationName(String pesApplicationName) {
		this.pesApplicationName = pesApplicationName;
	}

	public String getPesApplicationUrl() {
		return pesApplicationUrl;
	}

	public void setPesApplicationUrl(String pesApplicationUrl) {
		this.pesApplicationUrl = pesApplicationUrl;
	}

	public Integer getAppDispOrder() {
		return appDispOrder;
	}

	public void setAppDispOrder(Integer appDispOrder) {
		this.appDispOrder = appDispOrder;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public Integer getPrivilageAppId() {
		return privilageAppId;
	}

	public void setPrivilageAppId(Integer privilageAppId) {
		this.privilageAppId = privilageAppId;
	}
	
}
