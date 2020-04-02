package in.nic.pes.lgd.bean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(query = "SELECT get_lb_hierarchy_as_text_rpt FROM get_lb_hierarchy_as_text_rpt(:parentLBCode)", name = "getLBHierarchy", resultClass = GetLBHierarchy.class)

public class GetLBHierarchy 
{
	private String get_lb_hierarchy_as_text_rpt;

	@Id
	@Column(name = "get_lb_hierarchy_as_text_rpt")
	public String getGet_lb_hierarchy_as_text_rpt() {
		return get_lb_hierarchy_as_text_rpt;
	}

	public void setGet_lb_hierarchy_as_text_rpt(String get_lb_hierarchy_as_text_rpt) {
		this.get_lb_hierarchy_as_text_rpt = get_lb_hierarchy_as_text_rpt;
	}
	
	
}
