package in.nic.pes.lgd.dao;

import in.nic.pes.lgd.bean.ReportStatePanchayatPojo;

import java.util.List;

import org.hibernate.Session;

public interface StatePanchayatReportDao {

	public List<ReportStatePanchayatPojo> getStatePanchayatReport(char panchayat,Session session) throws Exception;
}
