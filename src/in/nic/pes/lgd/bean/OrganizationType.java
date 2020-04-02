/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.nic.pes.lgd.bean;

//import java.io.Serializable;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * 
 * @author
 */
@Entity
@Table(name = "organization_type")
public class OrganizationType implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer orgTypeCode;

	private String orgType;

	private Set<Organization> organization = new HashSet<Organization>(0);

	public OrganizationType() {
	}

	public OrganizationType(Integer orgTypeCode) {
		this.orgTypeCode = orgTypeCode;
	}

// TODO Remove unused code found by UCDetector
// 	public OrganizationType(Integer orgTypeCode, String orgType,
// 			Set<Organization> organization) {
// 		super();
// 		this.orgTypeCode = orgTypeCode;
// 		this.orgType = orgType;
// 		this.organization = organization;
// 	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationType")
	public Set<Organization> getOrganization() {
		return organization;
	}

	public void setOrganization(Set<Organization> organization) {
		this.organization = organization;
	}

	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "seqorgtype") })
	@Id
	@GeneratedValue(generator = "sequence")
	@Column(name = "org_type_code", unique = true, nullable = false)
	public Integer getOrgTypeCode() {
		return orgTypeCode;
	}

	public void setOrgTypeCode(Integer orgTypeCode) {
		this.orgTypeCode = orgTypeCode;
	}

	@Column(name = "org_type", length = 50)
	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (orgTypeCode != null ? orgTypeCode.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof OrganizationType)) {
			return false;
		}
		OrganizationType other = (OrganizationType) object;
		if ((this.orgTypeCode == null && other.orgTypeCode != null)
				|| (this.orgTypeCode != null && !this.orgTypeCode
						.equals(other.orgTypeCode))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "in.nic.pes.lgd.bean.OrganizationType[ orgTypeCode="
				+ orgTypeCode + " ]";
	}

}
