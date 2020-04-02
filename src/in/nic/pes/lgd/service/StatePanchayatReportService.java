package in.nic.pes.lgd.service;

import in.nic.pes.lgd.bean.ReportStatePanchayatPojo;

import java.util.List;

public interface StatePanchayatReportService 
{
	public List<ReportStatePanchayatPojo> getStatePanchayatReport(char panchayat) throws Exception;
}
