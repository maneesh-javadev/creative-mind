package ws.in.nic.pes.lgdws.webservice;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import in.nic.pes.lgd.bean.Block;
import in.nic.pes.lgd.bean.CoveredVillagesOfBlockEntityXML;
import in.nic.pes.lgd.bean.DesignationLevelSortorder;
import in.nic.pes.lgd.bean.LandRegion;
import in.nic.pes.lgd.bean.LgdDesignation;
import in.nic.pes.lgd.bean.LgdDesignationPK;
import in.nic.pes.lgd.bean.LocalbodyListbyStateold;
import in.nic.pes.lgd.bean.OrgLocatedAtLevels;
import in.nic.pes.lgd.bean.ParentWiseLBList;
import in.nic.pes.lgd.constant.DesignationConstant;
import in.nic.pes.lgd.service.LgdDesignationService;
import ws.in.nic.pes.lgdws.entity.BlockLGDDetailRDDEPT;
import ws.in.nic.pes.lgdws.entity.CoveredVillagesOfBlock;
import ws.in.nic.pes.lgdws.entity.CoveredVillagesOfBlockXML;
import ws.in.nic.pes.lgdws.entity.DistrictEntity;
import ws.in.nic.pes.lgdws.entity.DistrictListXML;
import ws.in.nic.pes.lgdws.entity.DistrictXML;
import ws.in.nic.pes.lgdws.entity.GPLGDDetailRDDEPT;
import ws.in.nic.pes.lgdws.entity.GetHierarchyOfEntity;
import ws.in.nic.pes.lgdws.entity.GetHierarchyOfEntityXML;
import ws.in.nic.pes.lgdws.entity.GetHierarchyOfEntityXmlList;
import ws.in.nic.pes.lgdws.entity.LevelwiseofRuralLbCount;
import ws.in.nic.pes.lgdws.entity.LevelwiseofRuralLbCountListXML;
import ws.in.nic.pes.lgdws.entity.LocalbodyCoverageListXML;
import ws.in.nic.pes.lgdws.entity.LocalbodyCoverageXML;
import ws.in.nic.pes.lgdws.entity.LocalbodyListNewXML;
import ws.in.nic.pes.lgdws.entity.LocalbodyListXML;
import ws.in.nic.pes.lgdws.entity.LocalbodyNewXML;
import ws.in.nic.pes.lgdws.entity.LocalbodyXML;
import ws.in.nic.pes.lgdws.entity.OrganizationListXML;
import ws.in.nic.pes.lgdws.entity.OrganiztionXML;
import ws.in.nic.pes.lgdws.entity.StateEntity;
import ws.in.nic.pes.lgdws.entity.StateListXML;
import ws.in.nic.pes.lgdws.entity.StateXML;
import ws.in.nic.pes.lgdws.entity.SubdistrictEntity;
import ws.in.nic.pes.lgdws.entity.SubdistrictListXML;
import ws.in.nic.pes.lgdws.entity.SubdistrictXML;
import ws.in.nic.pes.lgdws.entity.TotalNoofPriLbnVillageCount;
import ws.in.nic.pes.lgdws.entity.VillageListWithHierarchy;
import ws.in.nic.pes.lgdws.entity.VillageListWithHierarchyListXML;
import ws.in.nic.pes.lgdws.entity.VillageListWithHierarchyXML;
import ws.in.nic.pes.lgdws.entity.VillageListXML;
import ws.in.nic.pes.lgdws.entity.VillageXML;
import ws.in.nic.pes.lgdws.entity.VillagewiseLGDDetails;
import ws.in.nic.pes.lgdws.entity.WSBlock;
import ws.in.nic.pes.lgdws.entity.WSBlockXMLList;
import ws.in.nic.pes.lgdws.entity.WSDistrict;
import ws.in.nic.pes.lgdws.entity.WSLocalbody;
import ws.in.nic.pes.lgdws.entity.WSOrganization;
import ws.in.nic.pes.lgdws.entity.WSState;
import ws.in.nic.pes.lgdws.entity.WSSubdistrict;
import ws.in.nic.pes.lgdws.entity.WSVillage;
import ws.in.nic.pes.lgdws.entity.WardDetailList;
import ws.in.nic.pes.lgdws.entity.WardDetailListXML;
import ws.in.nic.pes.lgdws.services.WSService;

@Path("/lgdws")
public class LGDWebService {
	private static final Logger log = Logger.getLogger(LGDWebService.class);

	@Autowired
	private WSService wsServiceImpl;

	@Autowired
	private LgdDesignationService designationService;

	@Autowired
	private StatewiseGPVillageMappedService statewiseGPVillageMappedServiceImpl;

	@Autowired
	private LGDWebServiceValidator lgdWebServiceValidator;

	private LGDWebServiceForm lgdWebServiceForm;

	private LGDWebServiceOrgForm lgdWebServiceOrgForm;
	
	private static char QUERY_FLAG_CENSUS_VILLAGE='C';
	private static char QUERY_FLAG_MAPPED_VILLAGE='M';
	private static char QUERY_FLAG_GRAM_PANCHAYAT_USING_GIS='G';

	public void init(ServletContext servletContext) throws Exception {
		ApplicationContext ApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		wsServiceImpl = ApplicationContext.getBean(WSService.class);
		designationService = ApplicationContext.getBean(LgdDesignationService.class);
		lgdWebServiceValidator = ApplicationContext.getBean(LGDWebServiceValidator.class);
		lgdWebServiceForm = new LGDWebServiceForm();
		lgdWebServiceOrgForm = new LGDWebServiceOrgForm();
	}

	@GET
	@Path("/errorMessage")
	@Produces({ MediaType.APPLICATION_XML })
	public Response geterrorMessage() throws Exception {
		Response response = null;
		// return
		// Response.serverError().type(MediaType.APPLICATION_JSON).entity("Permission
		// Denied - You don't have permission").build();
		// return
		// Response.ok().type(MediaType.APPLICATION_JSON).entity("Permission
		// Denied").build();
		System.out.println("inside errormessag");
		response = Response.serverError().entity("Permission Denied - You don't have permission").build();
		System.out.println(response);
		return response;
	}

	@POST
	@Path("/stateList")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response getStateList(@Context ServletContext servletContext, @FormParam("outputFormat") String outputFormat,
			@DefaultValue("0") @FormParam("authKey") String authKey) {
		Response response = null;
		try {
			init(servletContext);
			lgdWebServiceForm.setOutputFormat(outputFormat);
			lgdWebServiceForm.setAuthKey(authKey);
			lgdWebServiceForm.setEntityType(WebServiceConstant.ENTITY_TYPE_STATE.toString());
			String errorMessage = lgdWebServiceValidator.validateEntity(lgdWebServiceForm);
			if (errorMessage != null) {
				response = Response.serverError().entity(errorMessage).build();
			} else {
				List<WSState> wsStateList = wsServiceImpl.getStateList();
				if (wsStateList != null && !wsStateList.isEmpty()) {
					if (outputFormat != null) {
						if (("json").equals(outputFormat)) {
							response = Response.ok().type(MediaType.APPLICATION_JSON).entity(wsStateList).build();
						} else if (("xml").equals(outputFormat)) {
							StateXML stateXML = null;
							StateListXML stateListXML = new StateListXML();
							stateListXML.setStateXMLList(new ArrayList<StateXML>());
							for (WSState obj : wsStateList) {
								stateXML = new StateXML();
								stateXML.setStateCode(obj.getStateCode());
								stateXML.setStateNameEnglish(obj.getStateNameEnglish());
								stateXML.setStateNameLocal(obj.getStateNameLocal());
								stateXML.setCensus2011Code(obj.getCensus2011Code());
								stateXML.setCensus2001Code(obj.getCensus2001Code());
								stateListXML.getStateXMLList().add(stateXML);
							}
							JAXBContext jaxbContext = JAXBContext.newInstance(StateListXML.class);
							Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
							jaxbMarshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8");
							jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
							java.io.StringWriter sw = new StringWriter();
							jaxbMarshaller.marshal(stateListXML, sw);
							response = Response.ok().type(MediaType.APPLICATION_XML)
									.entity(sw.toString().getBytes("UTF-8")).build();

						}

					} else {
						StringBuilder output = new StringBuilder(
								"<sdp><response  name='Title' value='State Services' />");
						for (WSState wsState : wsStateList) {
							output.append("<response name='");
							output.append(wsState.getStateCode());
							output.append("' value='");
							output.append(wsState.getStateNameEnglish());
							output.append("' />");
						}
						output.append("</sdp>");
						response = Response.ok().type(MediaType.APPLICATION_XML)
								.entity(output.toString().getBytes("UTF-8")).build();

					}

				} else {
					response = Response.serverError().entity("State are not available").build();
				}
			}

		} catch (Exception e) {
			response = Response.serverError().entity(e).build();
			e.printStackTrace();
			log.error("Exception-->" + e);
		}
		return response;
	}

	@POST
	@Path("/districtList")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response getDistrictList(@Context ServletContext servletContext,
			@FormParam("outputFormat") String outputFormat, @DefaultValue("0") @FormParam("authKey") String authKey,
			@DefaultValue("0") @FormParam(value = "stateCode") String stateCode) {
		Response response = null;
		try {
			init(servletContext);
			lgdWebServiceForm.setOutputFormat(outputFormat);
			lgdWebServiceForm.setAuthKey(authKey);
			lgdWebServiceForm.setEntityCode(stateCode);
			lgdWebServiceForm.setEntityType(WebServiceConstant.ENTITY_TYPE_STATE.toString());
			lgdWebServiceForm.setOutputType(WebServiceConstant.ENTITY_TYPE_DISTRICT.toString());
			String errorMessage = lgdWebServiceValidator.validateEntity(lgdWebServiceForm);
			if (errorMessage != null) {
				response = Response.serverError().entity(errorMessage).build();
			} else {
				List<WSDistrict> wsDistrictList = wsServiceImpl.getDistrictList(stateCode);
				if (wsDistrictList != null && !wsDistrictList.isEmpty()) {
					if (outputFormat != null) {
						if (("json").equals(outputFormat)) {
							response = Response.ok().type(MediaType.APPLICATION_JSON).entity(wsDistrictList).build();
						} else if (("xml").equals(outputFormat)) {
							DistrictXML districtXML = null;
							DistrictListXML districtListXML = new DistrictListXML();
							districtListXML.setDistrictXMLList(new ArrayList<DistrictXML>());
							for (WSDistrict obj : wsDistrictList) {
								districtXML = new DistrictXML();
								districtXML.setDistrictCode(obj.getDistrictCode());
								districtXML.setDistrictNameEnglish(obj.getDistrictNameEnglish());
								districtXML.setDistrictNameLocal(obj.getDistrictNameLocal());
								districtXML.setCensus2001Code(obj.getCensus2001Code());
								districtXML.setCensus2011Code(obj.getCensus2011Code());
								districtXML.setSsCode(obj.getSsCode());
								districtListXML.getDistrictXMLList().add(districtXML);

							}
							JAXBContext jaxbContext = JAXBContext.newInstance(DistrictListXML.class);
							Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
							jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
							java.io.StringWriter sw = new StringWriter();
							jaxbMarshaller.marshal(districtListXML, sw);
							response = Response.ok().type(MediaType.APPLICATION_XML)
									.entity(sw.toString().getBytes("UTF-8")).build();
						}

					} else {
						StringBuilder output = new StringBuilder(
								"<sdp><response  name='Title' value='District Services' />");
						for (WSDistrict wsDistrict : wsDistrictList) {
							output.append("<response name='");
							output.append(wsDistrict.getDistrictCode());
							output.append("' value='");
							output.append(wsDistrict.getDistrictNameEnglish() == null ? null
									: wsDistrict.getDistrictNameEnglish().trim());
							output.append("' />");
						}
						output.append("</sdp>");
						response = Response.ok().type(MediaType.APPLICATION_XML)
								.entity(output.toString().getBytes("UTF-8")).build();

					}

				} else {
					response = Response.serverError()
							.entity(lgdWebServiceValidator.showOutputEmptyError(lgdWebServiceForm)).build();
				}
			}
		} catch (Exception e) {
			response = Response.serverError().entity(e).build();
			e.printStackTrace();
			log.error("Exception-->" + e);
		}
		return response;
	}

	@POST
	@Path("/district")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response getDistrictDetails(@Context ServletContext servletContext,
			@FormParam("outputFormat") String outputFormat, @DefaultValue("0") @FormParam("authKey") String authKey,
			@DefaultValue("0") @FormParam(value = "districtCode") String districtCode) {
		Response response = null;
		try {
			init(servletContext);
			lgdWebServiceForm.setOutputFormat(outputFormat);
			lgdWebServiceForm.setAuthKey(authKey);
			lgdWebServiceForm.setEntityCode(districtCode);
			lgdWebServiceForm.setEntityType(WebServiceConstant.ENTITY_TYPE_DISTRICT.toString());
			lgdWebServiceForm.setOutputType(WebServiceConstant.ENTITY_TYPE_DISTRICT.toString());
			String errorMessage = lgdWebServiceValidator.validateEntity(lgdWebServiceForm);
			if (errorMessage != null) {
				response = Response.serverError().entity(errorMessage).build();
			} else {
				List<WSDistrict> wsDistrictList = wsServiceImpl.getDistrict(districtCode);
				if (wsDistrictList != null && !wsDistrictList.isEmpty()) {
					if (outputFormat != null) {
						if (("json").equals(outputFormat)) {
							response = Response.ok().type(MediaType.APPLICATION_JSON).entity(wsDistrictList).build();
						} else if (("xml").equals(outputFormat)) {
							DistrictXML districtXML = null;
							DistrictListXML districtListXML = new DistrictListXML();
							districtListXML.setDistrictXMLList(new ArrayList<DistrictXML>());
							for (WSDistrict obj : wsDistrictList) {
								districtXML = new DistrictXML();
								districtXML.setDistrictCode(obj.getDistrictCode());
								districtXML.setDistrictNameEnglish(obj.getDistrictNameEnglish());
								districtXML.setDistrictNameLocal(obj.getDistrictNameLocal());
								districtXML.setCensus2001Code(obj.getCensus2001Code());
								districtXML.setCensus2011Code(obj.getCensus2011Code());
								districtXML.setSsCode(obj.getSsCode());
								districtListXML.getDistrictXMLList().add(districtXML);
							}
							JAXBContext jaxbContext = JAXBContext.newInstance(DistrictListXML.class);
							Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
							jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
							java.io.StringWriter sw = new StringWriter();
							jaxbMarshaller.marshal(districtListXML, sw);
							response = Response.ok().type(MediaType.APPLICATION_XML)
									.entity(sw.toString().getBytes("UTF-8")).build();
						}

					} else {
						StringBuilder output = new StringBuilder("<sdp>");
						for (WSDistrict wsDistrict : wsDistrictList) {

							output.append("<group name='District' value=''>");
							output.append(
									"<response name='districtCode' value='" + wsDistrict.getDistrictCode() + "' />");
							output.append("<response name='districtNameEnglish' value='"
									+ (wsDistrict.getDistrictNameEnglish() != null
											? wsDistrict.getDistrictNameEnglish().trim() : null)
									+ "' />");
							output.append("<response name='districtNameLocal' value='"
									+ (wsDistrict.getDistrictNameLocal() != null
											? wsDistrict.getDistrictNameLocal().trim() : null)
									+ "' />");
							output.append(
									"<response name='census2001Code' value='" + (wsDistrict.getCensus2001Code() != null
											? wsDistrict.getCensus2001Code().trim() : null) + "' />");
							output.append("<response name='census2011Code' value='" + wsDistrict.getCensus2011Code()
									+ "' />");
							output.append("<response name='ssCode' value='"
									+ (wsDistrict.getSsCode() != null ? wsDistrict.getSsCode().trim() : null) + "' />");
							output.append("</group>");
						}
						output.append("</sdp>");
						response = Response.ok().type(MediaType.APPLICATION_XML)
								.entity(output.toString().getBytes("UTF-8")).build();

					}

				} else {
					response = Response.serverError()
							.entity(lgdWebServiceValidator.showOutputEmptyError(lgdWebServiceForm)).build();
				}
			}

		} catch (Exception e) {
			response = Response.serverError().entity(e).build();
			e.printStackTrace();
			log.error("Exception-->" + e);
		}
		return response;
	}

	@POST
	@Path("/subdistrictList")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response getSubDistrictList(@Context ServletContext servletContext,
			@FormParam("outputFormat") String outputFormat, @DefaultValue("0") @FormParam("authKey") String authKey,
			@DefaultValue("0") @FormParam(value = "districtCode") String districtCode) {
		Response response = null;
		try {
			init(servletContext);
			lgdWebServiceForm.setOutputFormat(outputFormat);
			lgdWebServiceForm.setAuthKey(authKey);
			lgdWebServiceForm.setEntityCode(districtCode);
			lgdWebServiceForm.setEntityType(WebServiceConstant.ENTITY_TYPE_DISTRICT.toString());
			lgdWebServiceForm.setOutputType(WebServiceConstant.ENTITY_TYPE_SUBDISTRICT.toString());
			String errorMessage = lgdWebServiceValidator.validateEntity(lgdWebServiceForm);
			if (errorMessage != null) {
				response = Response.serverError().entity(errorMessage).build();
			} else {
				List<WSSubdistrict> wsSubdistrictList = wsServiceImpl.getSubdistrictList(districtCode);
				if (wsSubdistrictList != null && !wsSubdistrictList.isEmpty()) {
					if (outputFormat != null) {
						if (("json").equals(outputFormat)) {
							response = Response.ok().type(MediaType.APPLICATION_JSON).entity(wsSubdistrictList).build();
						} else if (("xml").equals(outputFormat)) {
							SubdistrictXML subdistrictXML = null;
							SubdistrictListXML subdistrictListXML = new SubdistrictListXML();
							subdistrictListXML.setSubdistrictXMLList(new ArrayList<SubdistrictXML>());
							for (WSSubdistrict obj : wsSubdistrictList) {
								subdistrictXML = new SubdistrictXML();
								subdistrictXML.setSubdistrictCode(obj.getSubdistrictCode());
								subdistrictXML.setSubdistrictNameEnglish(obj.getSubdistrictNameEnglish());
								subdistrictXML.setSubdistrictNameLocal(obj.getSubdistrictNameLocal());
								subdistrictXML.setCensus2001Code(obj.getCensus2001Code());
								subdistrictXML.setCensus2011Code(obj.getCensus2011Code());
								subdistrictXML.setSsCode(obj.getSsCode());
								subdistrictListXML.getSubdistrictXMLList().add(subdistrictXML);
							}
							JAXBContext jaxbContext = JAXBContext.newInstance(SubdistrictListXML.class);
							Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
							jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
							java.io.StringWriter sw = new StringWriter();
							jaxbMarshaller.marshal(subdistrictListXML, sw);
							response = Response.ok().type(MediaType.APPLICATION_XML)
									.entity(sw.toString().getBytes("UTF-8")).build();
						}

					} else {
						StringBuilder output = new StringBuilder(
								"<sdp><response  name='Title' value='Subdistrict Services' />");
						for (WSSubdistrict wsSubdistrict : wsSubdistrictList) {
							output.append("<response name='");
							output.append(wsSubdistrict.getSubdistrictCode());
							output.append("' value='");
							output.append(wsSubdistrict.getSubdistrictNameEnglish() == null ? null
									: wsSubdistrict.getSubdistrictNameEnglish().trim());
							output.append("' />");
						}
						output.append("</sdp>");
						response = Response.ok().type(MediaType.APPLICATION_XML)
								.entity(output.toString().getBytes("UTF-8")).build();

					}

				} else {
					response = Response.serverError()
							.entity(lgdWebServiceValidator.showOutputEmptyError(lgdWebServiceForm)).build();
				}
			}
		} catch (Exception e) {
			response = Response.serverError().entity(e).build();
			e.printStackTrace();
			log.error("Exception-->" + e);
		}
		return response;
	}

	@POST
	@Path("/subdistrict")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response getSubdistrictDetails(@Context ServletContext servletContext,
			@FormParam("outputFormat") String outputFormat, @DefaultValue("0") @FormParam("authKey") String authKey,
			@DefaultValue("0") @FormParam(value = "subDistrictCode") String subDistrictCode) {
		Response response = null;
		try {
			init(servletContext);
			lgdWebServiceForm.setOutputFormat(outputFormat);
			lgdWebServiceForm.setAuthKey(authKey);
			lgdWebServiceForm.setEntityCode(subDistrictCode);
			lgdWebServiceForm.setEntityType(WebServiceConstant.ENTITY_TYPE_SUBDISTRICT.toString());
			lgdWebServiceForm.setOutputType(WebServiceConstant.ENTITY_TYPE_SUBDISTRICT.toString());
			String errorMessage = lgdWebServiceValidator.validateEntity(lgdWebServiceForm);
			if (errorMessage != null) {
				response = Response.serverError().entity(errorMessage).build();
			} else {
				List<WSSubdistrict> wsSubdistrictList = wsServiceImpl.getSubdistrict(subDistrictCode);
				if (wsSubdistrictList != null && !wsSubdistrictList.isEmpty()) {
					if (outputFormat != null) {
						if (("json").equals(outputFormat)) {
							response = Response.ok().type(MediaType.APPLICATION_JSON).entity(wsSubdistrictList).build();
						} else if (("xml").equals(outputFormat)) {
							SubdistrictXML subdistrictXML = null;
							SubdistrictListXML subdistrictListXML = new SubdistrictListXML();
							subdistrictListXML.setSubdistrictXMLList(new ArrayList<SubdistrictXML>());
							for (WSSubdistrict obj : wsSubdistrictList) {
								subdistrictXML = new SubdistrictXML();
								subdistrictXML.setSubdistrictCode(obj.getSubdistrictCode());
								subdistrictXML.setSubdistrictNameEnglish(obj.getSubdistrictNameEnglish());
								subdistrictXML.setSubdistrictNameLocal(obj.getSubdistrictNameLocal());
								subdistrictXML.setCensus2001Code(obj.getCensus2001Code());
								subdistrictXML.setCensus2011Code(obj.getCensus2011Code());
								subdistrictXML.setSsCode(obj.getSsCode());
								subdistrictListXML.getSubdistrictXMLList().add(subdistrictXML);
							}
							JAXBContext jaxbContext = JAXBContext.newInstance(SubdistrictListXML.class);
							Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
							jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
							java.io.StringWriter sw = new StringWriter();
							jaxbMarshaller.marshal(subdistrictListXML, sw);
							response = Response.ok().type(MediaType.APPLICATION_XML)
									.entity(sw.toString().getBytes("UTF-8")).build();
						}

					} else {
						StringBuilder output = new StringBuilder("<sdp>");
						for (WSSubdistrict wsSubdistrict : wsSubdistrictList) {
							output.append("<group name='Subdistrict' value=''>");
							output.append("<response name='subdistrictCode' value='"
									+ wsSubdistrict.getSubdistrictCode() + "' />");
							output.append(
									"<response name='subdistrictNameEnglish' value='"
											+ (wsSubdistrict.getSubdistrictNameEnglish() != null
													? wsSubdistrict.getSubdistrictNameEnglish().trim() : null)
											+ "' />");
							output.append("<response name='subdistrictNameLocal' value='"
									+ (wsSubdistrict.getSubdistrictNameLocal() != null
											? wsSubdistrict.getSubdistrictNameLocal() : null)
									+ "' />");
							output.append("<response name='census2001Code' value='"
									+ (wsSubdistrict.getCensus2001Code() != null
											? wsSubdistrict.getCensus2001Code().trim() : null)
									+ "' />");
							output.append("<response name='census2011Code' value='" + wsSubdistrict.getCensus2011Code()
									+ "' />");
							output.append("<response name='ssCode' value='"
									+ (wsSubdistrict.getSsCode() != null ? wsSubdistrict.getSsCode().trim() : null)
									+ "' />");
							output.append("</group>");

						}
						output.append("</sdp>");
						response = Response.ok().type(MediaType.APPLICATION_XML)
								.entity(output.toString().getBytes("UTF-8")).build();
					}

				} else {
					response = Response.serverError()
							.entity(lgdWebServiceValidator.showOutputEmptyError(lgdWebServiceForm)).build();
				}

			}
		} catch (Exception e) {
			response = Response.serverError().entity(e).build();
			e.printStackTrace();
			log.error("Exception-->" + e);
		}
		return response;
	}

	@POST
	@Path("/villageList")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response getVillageList(@Context ServletContext servletContext,
			@FormParam("outputFormat") String outputFormat, @DefaultValue("0") @FormParam("authKey") String authKey,
			@DefaultValue("0") @FormParam(value = "subDistrictCode") String subDistrictCode) {
		Response response = null;
		try {
			init(servletContext);
			lgdWebServiceForm.setOutputFormat(outputFormat);
			lgdWebServiceForm.setAuthKey(authKey);
			lgdWebServiceForm.setEntityCode(subDistrictCode);
			lgdWebServiceForm.setOutputType(WebServiceConstant.ENTITY_TYPE_VILLAGE.toString());
			lgdWebServiceForm.setEntityType(WebServiceConstant.ENTITY_TYPE_SUBDISTRICT.toString());

			String errorMessage = lgdWebServiceValidator.validateEntity(lgdWebServiceForm);
			if (errorMessage != null) {
				response = Response.serverError().entity(errorMessage).build();
			} else {
				List<WSVillage> wsVillageList = wsServiceImpl.getVillageList(subDistrictCode);
				if (wsVillageList != null && !wsVillageList.isEmpty()) {
					if (outputFormat != null) {
						if (("json").equals(outputFormat)) {
							response = Response.ok().type(MediaType.APPLICATION_JSON).entity(wsVillageList).build();
						} else if (("xml").equals(outputFormat)) {
							VillageXML villageXML = null;
							VillageListXML villageListXML = new VillageListXML();
							villageListXML.setVillageXMLList(new ArrayList<VillageXML>());
							for (WSVillage obj : wsVillageList) {
								villageXML = new VillageXML();
								villageXML.setVillageCode(obj.getVillageCode());
								villageXML.setVillageNameEnglish(obj.getVillageNameEnglish());
								villageXML.setVillageNameLocal(obj.getVillageNameLocal());
								villageXML.setCensus2001Code(obj.getCensus2001Code());
								villageXML.setCensus2011Code(obj.getCensus2011Code());
								villageXML.setSsCode(obj.getSsCode());
								villageListXML.getVillageXMLList().add(villageXML);
							}
							JAXBContext jaxbContext = JAXBContext.newInstance(VillageListXML.class);
							Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
							jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
							java.io.StringWriter sw = new StringWriter();
							jaxbMarshaller.marshal(villageListXML, sw);
							response = Response.ok().type(MediaType.APPLICATION_XML)
									.entity(sw.toString().getBytes("UTF-8")).build();
						}

					} else {
						StringBuilder output = new StringBuilder(
								"<sdp><response  name='Title' value='Village Services' />");
						for (WSVillage wsVillage : wsVillageList) {
							output.append("<response name='");
							output.append(wsVillage.getVillageCode());
							output.append("' value='");
							output.append(wsVillage.getVillageNameEnglish() == null ? null
									: wsVillage.getVillageNameEnglish().trim());
							output.append("' />");
						}
						output.append("</sdp>");
						response = Response.ok().type(MediaType.APPLICATION_XML)
								.entity(output.toString().getBytes("UTF-8")).build();

					}

				} else {
					response = Response.serverError()
							.entity(lgdWebServiceValidator.showOutputEmptyError(lgdWebServiceForm)).build();
				}
			}
		} catch (Exception e) {
			response = Response.serverError().entity(e).build();
			e.printStackTrace();
			log.error("Exception-->" + e);
		}
		return response;
	}

	@POST
	@Path("/village")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response getVillageDetails(@Context ServletContext servletContext,
			@FormParam("outputFormat") String outputFormat, @DefaultValue("0") @FormParam("authKey") String authKey,
			@DefaultValue("0") @FormParam(value = "villageCode") String villageCode) {
		Response response = null;
		try {
			init(servletContext);
			lgdWebServiceForm.setOutputFormat(outputFormat);
			lgdWebServiceForm.setAuthKey(authKey);
			lgdWebServiceForm.setEntityCode(villageCode);
			lgdWebServiceForm.setEntityType(WebServiceConstant.ENTITY_TYPE_VILLAGE.toString());
			lgdWebServiceForm.setOutputType(WebServiceConstant.ENTITY_TYPE_VILLAGE.toString());
			String errorMessage = lgdWebServiceValidator.validateEntity(lgdWebServiceForm);
			if (errorMessage != null) {
				response = Response.serverError().entity(errorMessage).build();
			} else {
				List<WSVillage> wsVillageList = wsServiceImpl.getVillage(villageCode);
				if (wsVillageList != null && !wsVillageList.isEmpty()) {
					if (outputFormat != null) {
						if (("json").equals(outputFormat)) {
							response = Response.ok().type(MediaType.APPLICATION_JSON).entity(wsVillageList).build();
						} else if (("xml").equals(outputFormat)) {
							VillageXML villageXML = null;
							VillageListXML villageListXML = new VillageListXML();
							villageListXML.setVillageXMLList(new ArrayList<VillageXML>());
							for (WSVillage obj : wsVillageList) {
								villageXML = new VillageXML();
								villageXML.setVillageCode(obj.getVillageCode());
								villageXML.setVillageNameEnglish(obj.getVillageNameEnglish());
								villageXML.setVillageNameLocal(obj.getVillageNameLocal());
								villageXML.setCensus2001Code(obj.getCensus2001Code());
								villageXML.setCensus2011Code(obj.getCensus2011Code());
								villageXML.setSsCode(obj.getSsCode());
								villageListXML.getVillageXMLList().add(villageXML);
							}
							JAXBContext jaxbContext = JAXBContext.newInstance(VillageListXML.class);
							Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
							jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
							java.io.StringWriter sw = new StringWriter();
							jaxbMarshaller.marshal(villageListXML, sw);
							response = Response.ok().type(MediaType.APPLICATION_XML)
									.entity(sw.toString().getBytes("UTF-8")).build();
						}

					} else {
						StringBuilder output = new StringBuilder("<sdp>");
						for (WSVillage wsVillage : wsVillageList) {
							output.append("<group name='Village' value=''>");
							output.append("<response name='villageCode' value='" + wsVillage.getVillageCode() + "' />");
							output.append("<response name='villageNameEnglish' value='"
									+ (wsVillage.getVillageNameEnglish() != null
											? wsVillage.getVillageNameEnglish().trim() : null)
									+ "' />");
							output.append("<response name='villageNameLocal' value='"
									+ (wsVillage.getVillageNameLocal() != null ? wsVillage.getVillageNameLocal() : null)
									+ "' />");
							output.append(
									"<response name='census2001Code' value='" + (wsVillage.getCensus2001Code() != null
											? wsVillage.getCensus2001Code().trim() : null) + "' />");
							output.append(
									"<response name='census2011Code' value='" + wsVillage.getCensus2011Code() + "' />");
							output.append("<response name='ssCode' value='"
									+ (wsVillage.getSsCode() != null ? wsVillage.getSsCode().trim() : null) + "' />");
							output.append("</group>");
						}
						output.append("</sdp>");
						response = Response.ok().type(MediaType.APPLICATION_XML)
								.entity(output.toString().getBytes("UTF-8")).build();

					}

				} else {
					response = Response.serverError()
							.entity(lgdWebServiceValidator.showOutputEmptyError(lgdWebServiceForm)).build();
				}
			}
		} catch (Exception e) {
			response = Response.serverError().entity(e).build();
			e.printStackTrace();
			log.error("Exception-->" + e);
		}
		return response;
	}

	@POST
	@Path("/lbListbySubdistrict")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response getLBListbySubdistrictCode(@Context ServletContext servletContext,
			@FormParam("outputFormat") String outputFormat, @DefaultValue("0") @FormParam("authKey") String authKey,
			@DefaultValue("0") @FormParam(value = "subDistrictCode") String subDistrictCode) {
		Response response = null;
		try {
			init(servletContext);
			lgdWebServiceForm.setOutputFormat(outputFormat);
			lgdWebServiceForm.setAuthKey(authKey);
			lgdWebServiceForm.setEntityCode(subDistrictCode);
			lgdWebServiceForm.setEntityType(WebServiceConstant.ENTITY_TYPE_SUBDISTRICT.toString());
			lgdWebServiceForm.setOutputType(WebServiceConstant.ENTITY_TYPE_LOCALBODY.toString());
			String errorMessage = lgdWebServiceValidator.validateEntity(lgdWebServiceForm);
			if (errorMessage != null) {
				response = Response.serverError().entity(errorMessage).build();
			} else {
				List<WSLocalbody> wsLocalbodyList = wsServiceImpl.getLBListbySubdistrict(
						WebServiceConstant.ENTITY_TYPE_SUBDISTRICT.toString(), Integer.parseInt(subDistrictCode),
						Integer.parseInt(WebServiceConstant.LOCALBODY_TYPE_CODE_VILLAGE_PANCHAYAT.toString()));
				if (wsLocalbodyList != null && !wsLocalbodyList.isEmpty()) {
					if (outputFormat != null) {
						if (("json").equals(outputFormat)) {
							response = Response.ok().type(MediaType.APPLICATION_JSON).entity(wsLocalbodyList).build();
						} else if (("xml").equals(outputFormat)) {
							LocalbodyXML localbodyXML = null;
							LocalbodyListXML localbodyListXML = new LocalbodyListXML();
							localbodyListXML.setLocalbodyXMLList(new ArrayList<LocalbodyXML>());
							for (WSLocalbody obj : wsLocalbodyList) {
								localbodyXML = new LocalbodyXML();
								localbodyXML.setLocalbodyCode(obj.getLocalbodyCode());
								localbodyXML.setLocalbodyNameEnglish(obj.getLocalbodyNameEnglish());
								localbodyXML.setLocalbodyNameLocal(obj.getLocalbodyNameLocal());

								localbodyListXML.getLocalbodyXMLList().add(localbodyXML);
							}
							JAXBContext jaxbContext = JAXBContext.newInstance(LocalbodyListXML.class);
							Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
							jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
							java.io.StringWriter sw = new StringWriter();
							jaxbMarshaller.marshal(localbodyListXML, sw);
							response = Response.ok().type(MediaType.APPLICATION_XML)
									.entity(sw.toString().getBytes("UTF-8")).build();
						}

					} else {
						StringBuilder output = new StringBuilder(
								"<sdp><response  name='Title' value='Localbody Services' />");
						for (WSLocalbody wsLocalbody : wsLocalbodyList) {
							output.append("<response name='");
							output.append(wsLocalbody.getLocalbodyCode());
							output.append("' value='");
							output.append(wsLocalbody.getLocalbodyNameEnglish() == null ? null
									: wsLocalbody.getLocalbodyNameEnglish().trim());
							output.append("' />");
						}
						output.append("</sdp>");
						response = Response.ok().type(MediaType.APPLICATION_XML)
								.entity(output.toString().getBytes("UTF-8")).build();

					}

				} else {
					response = Response.serverError()
							.entity(lgdWebServiceValidator.showOutputEmptyError(lgdWebServiceForm)).build();
				}
			}
		} catch (Exception e) {
			response = Response.serverError().entity(e).build();
			e.printStackTrace();
			log.error("Exception-->" + e);
		}
		return response;
	}

	@POST
	@Path("/lbListbyDistrict")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response getLbListbyDistrict(@Context ServletContext servletContext,
			@FormParam("outputFormat") String outputFormat, @DefaultValue("0") @FormParam("authKey") String authKey,
			@DefaultValue("0") @FormParam(value = "districtCode") String districtCode,
			@DefaultValue("0") @FormParam(value = "lbTypeCode") String lbTypeCode) {
		Response response = null;
		try {
			init(servletContext);
			lgdWebServiceForm.setOutputFormat(outputFormat);
			lgdWebServiceForm.setAuthKey(authKey);
			lgdWebServiceForm.setEntityCode(districtCode);
			lgdWebServiceForm.setEntityType(WebServiceConstant.ENTITY_TYPE_DISTRICT.toString());
			lgdWebServiceForm.setLbTypeCode(lbTypeCode);
			lgdWebServiceForm.setOutputType(WebServiceConstant.ENTITY_TYPE_LOCALBODY.toString());
			String errorMessage = lgdWebServiceValidator.validateLbTypeCode(lgdWebServiceForm);
			if (errorMessage != null) {
				response = Response.serverError().entity(errorMessage).build();
			} else {
				List<WSLocalbody> wsLocalbodyList = wsServiceImpl.getLocalbodyListbyDistrictnLbTypeCode(
						Integer.parseInt(districtCode), Integer.parseInt(lbTypeCode));
				if (wsLocalbodyList != null && !wsLocalbodyList.isEmpty()) {
					if (outputFormat != null) {
						if (("json").equals(outputFormat)) {
							response = Response.ok().type(MediaType.APPLICATION_JSON).entity(wsLocalbodyList).build();
						} else if (("xml").equals(outputFormat)) {
							LocalbodyXML localbodyXML = null;
							LocalbodyListXML localbodyListXML = new LocalbodyListXML();
							localbodyListXML.setLocalbodyXMLList(new ArrayList<LocalbodyXML>());
							for (WSLocalbody obj : wsLocalbodyList) {
								localbodyXML = new LocalbodyXML();
								localbodyXML.setLocalbodyCode(obj.getLocalbodyCode());
								localbodyXML.setLocalbodyNameEnglish(obj.getLocalbodyNameEnglish());
								localbodyXML.setLocalbodyNameLocal(obj.getLocalbodyNameLocal());

								localbodyListXML.getLocalbodyXMLList().add(localbodyXML);
							}
							JAXBContext jaxbContext = JAXBContext.newInstance(LocalbodyListXML.class);
							Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
							jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
							java.io.StringWriter sw = new StringWriter();
							jaxbMarshaller.marshal(localbodyListXML, sw);
							response = Response.ok().type(MediaType.APPLICATION_XML)
									.entity(sw.toString().getBytes("UTF-8")).build();
						}

					} else {
						StringBuilder output = new StringBuilder(
								"<sdp><response  name='Title' value='Localbody Services' />");
						for (WSLocalbody wsLocalbody : wsLocalbodyList) {
							output.append("<response name='");
							output.append(wsLocalbody.getLocalbodyCode());
							output.append("' value='");
							output.append(wsLocalbody.getLocalbodyNameEnglish() == null ? null
									: wsLocalbody.getLocalbodyNameEnglish().trim());
							output.append("' />");
						}
						output.append("</sdp>");
						response = Response.ok().type(MediaType.APPLICATION_XML)
								.entity(output.toString().getBytes("UTF-8")).build();

					}

				} else {
					response = Response.serverError()
							.entity(lgdWebServiceValidator.showOutputEmptyError(lgdWebServiceForm)).build();
				}
			}
		} catch (Exception e) {
			response = Response.serverError().entity(e).build();
			e.printStackTrace();
			log.error("Exception-->" + e);
		}
		return response;
	}

	@GET
	@Path("/villageMappedPercent")
	@Produces({ MediaType.APPLICATION_XML })
	public StateWiseGPVillageMappedEntity getSTWiseGPVillageMapListXml(@Context ServletContext servletContext,
			@DefaultValue("A") @QueryParam(value = "LBGroup") String LBGroup,
			@QueryParam(value = "stateCode") Integer stateCode) throws Exception {
		StateWiseGPVillageMappedEntity stateWiseGPVillageMapped = new StateWiseGPVillageMappedEntity();
		try {
			ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
			statewiseGPVillageMappedServiceImpl = ctx.getBean(StatewiseGPVillageMappedService.class);
			if (stateCode != null && stateCode > 0)
				stateWiseGPVillageMapped = statewiseGPVillageMappedServiceImpl.getStateWiseGPtoVillageMapping(stateCode,
						LBGroup);
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
		return stateWiseGPVillageMapped;
	}

	@POST
	@Path("/organizationList")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response organizationList(@Context ServletContext servletContext,
			@FormParam("outputFormat") String outputFormat, @DefaultValue("0") @FormParam("authKey") String authKey,
			@DefaultValue("null") @FormParam(value = "orgTypeCode") String orgTypeCode,
			@DefaultValue("false") @FormParam(value = "isCenter") String isCenter,
			@DefaultValue("false") @FormParam(value = "stateCode") String stateCode) {
		Response response = null;
		try {
			init(servletContext);
			lgdWebServiceOrgForm.setOutputFormat(outputFormat);
			lgdWebServiceOrgForm.setOrgTypeCode(orgTypeCode);
			lgdWebServiceOrgForm.setIsCenter(isCenter);
			lgdWebServiceOrgForm.setStateCode(stateCode);
			String errorMessage = lgdWebServiceValidator.validateOganization(lgdWebServiceOrgForm);
			if (errorMessage != null) {
				response = Response.serverError().entity(errorMessage).build();
			} else {
				Integer parameterOrgTypeCode = WebServiceConstant.NULL_STRING.toString().equals(orgTypeCode) ? null
						: Integer.parseInt(orgTypeCode);
				boolean parameterIsCenter = Boolean.parseBoolean(isCenter);

				List<WSOrganization> wsOrganizationlist = wsServiceImpl.getOrganizationList(parameterOrgTypeCode,
						parameterIsCenter, Integer.parseInt(stateCode));
				if (wsOrganizationlist != null && !wsOrganizationlist.isEmpty()) {
					if (outputFormat != null) {
						if (("json").equals(outputFormat)) {
							response = Response.ok().type(MediaType.APPLICATION_JSON).entity(wsOrganizationlist)
									.build();
						} else if (("xml").equals(outputFormat)) {
							OrganiztionXML organiztionXML = null;
							OrganizationListXML organizationListXML = new OrganizationListXML();
							organizationListXML.setOrganizationXMLList(new ArrayList<OrganiztionXML>());
							for (WSOrganization obj : wsOrganizationlist) {
								organiztionXML = new OrganiztionXML();
								organiztionXML.setOrgCode(obj.getOrgCode());
								organiztionXML.setOrgName(obj.getOrgName());

								organizationListXML.getOrganizationXMLList().add(organiztionXML);
							}
							JAXBContext jaxbContext = JAXBContext.newInstance(OrganizationListXML.class);
							Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
							jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
							java.io.StringWriter sw = new StringWriter();
							jaxbMarshaller.marshal(organizationListXML, sw);
							response = Response.ok().type(MediaType.APPLICATION_XML)
									.entity(sw.toString().getBytes("UTF-8")).build();
						}

					} else {
						StringBuilder output = new StringBuilder(
								"<sdp><response  name='Title' value='Orgnaization Services' />");
						for (WSOrganization wsOrganization : wsOrganizationlist) {
							output.append("<response name='");
							output.append(wsOrganization.getOrgCode());
							output.append("' value='");
							output.append(
									wsOrganization.getOrgName() == null ? null : wsOrganization.getOrgName().trim());
							output.append("' />");
						}
						output.append("</sdp>");
						response = Response.ok().type(MediaType.APPLICATION_XML)
								.entity(output.toString().getBytes("UTF-8")).build();

					}

				} else {
					response = Response.serverError()
							.entity(lgdWebServiceValidator.showOutputEmptyErrorforOrganization()).build();
				}
			}
		} catch (Exception e) {
			response = Response.serverError().entity(e).build();
			e.printStackTrace();
			log.error("Exception-->" + e);
		}
		return response;
	}

	@POST
	@Path("/levelwiseofRuralLbCount")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response getLevelwiseofRuralLbCount(@Context ServletContext servletContext,
			@FormParam("outputFormat") String outputFormat, @DefaultValue("0") @FormParam("authKey") String authKey,
			@FormParam("stateCode") String stateCode) {
		Response response = null;
		try {
			init(servletContext);
			lgdWebServiceForm.setOutputFormat(outputFormat);
			lgdWebServiceForm.setAuthKey(authKey);
			lgdWebServiceForm.setEntityCode(stateCode);
			lgdWebServiceForm.setEntityType(WebServiceConstant.ENTITY_TYPE_STATE.toString());
			String errorMessage = lgdWebServiceValidator.validateEntity(lgdWebServiceForm);
			if (errorMessage != null) {
				response = Response.serverError().entity(errorMessage).build();
			} else {
				if (stateCode != null) {
					List<LevelwiseofRuralLbCount> levelwiseofRuralLbCountList = wsServiceImpl
							.getLevelwiseRuralLBCount(Integer.parseInt(stateCode));
					if (levelwiseofRuralLbCountList != null && !levelwiseofRuralLbCountList.isEmpty()) {
						if (outputFormat != null) {
							if (("json").equals(outputFormat)) {
								response = Response.ok().type(MediaType.APPLICATION_JSON)
										.entity(levelwiseofRuralLbCountList).build();
							} else if (("xml").equals(outputFormat)) {

								LevelwiseofRuralLbCountListXML levelwiseofRuralLbCountListXML = new LevelwiseofRuralLbCountListXML();
								levelwiseofRuralLbCountListXML
										.setLevelwiseofRuralLbCountListXML(levelwiseofRuralLbCountList);
								JAXBContext jaxbContext = JAXBContext.newInstance(LevelwiseofRuralLbCountListXML.class);
								Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
								jaxbMarshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8");
								jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
								java.io.StringWriter sw = new StringWriter();
								jaxbMarshaller.marshal(levelwiseofRuralLbCountListXML, sw);
								response = Response.ok().type(MediaType.APPLICATION_XML)
										.entity(sw.toString().getBytes("UTF-8")).build();

							}

						} else {
							response = Response.serverError().entity("outputFormat is required").build();
						}
					} else {
						response = Response.serverError().entity("localbody data not present for this state").build();
					}
				} else {
					response = Response.serverError().entity("stateCode is required").build();
				}

			}

		} catch (Exception e) {
			response = Response.serverError().entity(e).build();
			e.printStackTrace();
			log.error("Exception-->" + e);
		}
		return response;
	}

	@GET
	@Path("/totalNoofPriLocalbodyandvillage")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response getTotalNoofPriLocalbodyandvillage(@Context ServletContext servletContext) throws Exception {
		Response response = null;
		try {
			init(servletContext);
			TotalNoofPriLbnVillageCount totalNoofPriLbnVillageCount = wsServiceImpl
					.getTotalNoofPriLbnVillageCount(WebServiceConstant.LB_AND_VILLAGE_COUNT.toString());
			/*
			 * JAXBContext jaxbContext =
			 * JAXBContext.newInstance(TotalNoofPriLbnVillageCount.class);
			 * Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			 * jaxbMarshaller.setProperty(javax.xml.bind.Marshaller.
			 * JAXB_ENCODING, "UTF-8");
			 * jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
			 * true); java.io.StringWriter sw = new StringWriter();
			 * jaxbMarshaller.marshal(totalNoofPriLbnVillageCount, sw);
			 */

			StringBuilder sw = new StringBuilder("<xml>\n<status>\n");
			sw.append(totalNoofPriLbnVillageCount.getStatus());
			sw.append("\n</status>\n</xml>");
			response = Response.ok().type(MediaType.APPLICATION_XML).entity(sw.toString().getBytes("UTF-8")).build();

		} catch (Exception e) {
			StringBuilder sw = new StringBuilder("<xml>\n<error>\n");
			sw.append(e);
			sw.append("\n</error>\n</xml>");
			response = Response.ok().type(MediaType.APPLICATION_XML).entity(sw.toString().getBytes("UTF-8")).build();
			e.printStackTrace();
			log.error("Exception-->" + e);
		}
		return response;
	}

	@GET
	@Path("/totalNoofPesaStatus")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response totalNoofPesaStatus(@Context ServletContext servletContext) throws Exception {
		Response response = null;
		try {
			init(servletContext);
			TotalNoofPriLbnVillageCount totalNoofPriLbnVillageCount = wsServiceImpl
					.getTotalNoofPriLbnVillageCount(WebServiceConstant.PESA_STATUS_COUNT.toString());
			/*
			 * JAXBContext jaxbContext =
			 * JAXBContext.newInstance(TotalNoofPriLbnVillageCount.class);
			 * Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			 * jaxbMarshaller.setProperty(javax.xml.bind.Marshaller.
			 * JAXB_ENCODING, "UTF-8");
			 * jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
			 * true); java.io.StringWriter sw = new StringWriter();
			 * jaxbMarshaller.marshal(totalNoofPriLbnVillageCount, sw);
			 */

			StringBuilder sw = new StringBuilder("<xml>\n<status>\n");
			sw.append(totalNoofPriLbnVillageCount.getStatus());
			sw.append("\n</status>\n</xml>");
			response = Response.ok().type(MediaType.APPLICATION_XML).entity(sw.toString().getBytes("UTF-8")).build();

		} catch (Exception e) {
			StringBuilder sw = new StringBuilder("<xml>\n<error>\n");
			sw.append(e);
			sw.append("\n</error>\n</xml>");
			response = Response.ok().type(MediaType.APPLICATION_XML).entity(sw.toString().getBytes("UTF-8")).build();
			e.printStackTrace();
			log.error("Exception-->" + e);
		}
		return response;
	}

	@POST
	@Path("/villageHierarachyDetails")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response villageHierarachyDetails(@Context ServletContext servletContext,
			@DefaultValue("0") @FormParam("authKey") String authKey, @FormParam("villageCode") String villageCode) {
		Response response = null;
		try {
			init(servletContext);
			lgdWebServiceForm.setAuthKey(authKey);
			lgdWebServiceForm.setEntityCode(villageCode);
			lgdWebServiceForm.setEntityType(WebServiceConstant.ENTITY_TYPE_VILLAGE.toString());
			String errorMessage = lgdWebServiceValidator.validateEntityWithoutOutputFormat(lgdWebServiceForm);
			if (errorMessage != null) {
				response = Response.serverError().entity(errorMessage).build();
			} else {
				if (villageCode != null) {
					Object[] ObjectArr = wsServiceImpl.villageWiseLGDDetails(Integer.parseInt(villageCode));
					if (ObjectArr != null && ObjectArr[0] != null) {
						List<StateEntity> stateEntityList = (List<StateEntity>) ObjectArr[0];
						List<DistrictEntity> districtEntityList = (List<DistrictEntity>) ObjectArr[1];
						List<SubdistrictEntity> subdistrictEntityList = (List<SubdistrictEntity>) ObjectArr[2];
						List<BlockLGDDetailRDDEPT> blockLGDDetailRDDEPTList = (List<BlockLGDDetailRDDEPT>) ObjectArr[3];
						List<GPLGDDetailRDDEPT> gpLGDDetailRDDEPTList = (List<GPLGDDetailRDDEPT>) ObjectArr[4];

						VillagewiseLGDDetails villagewiseLGDDetailsXML = new VillagewiseLGDDetails();

						villagewiseLGDDetailsXML.setStateEntityList(stateEntityList);
						villagewiseLGDDetailsXML.setDistrictEntityList(districtEntityList);
						villagewiseLGDDetailsXML.setSubdistrictEntityList(subdistrictEntityList);
						villagewiseLGDDetailsXML.setBlockLGDDetailRDDEPTList(blockLGDDetailRDDEPTList);
						villagewiseLGDDetailsXML.setGpLGDDetailRDDEPTList(gpLGDDetailRDDEPTList);

						JAXBContext jaxbContext = JAXBContext.newInstance(VillagewiseLGDDetails.class);
						Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
						jaxbMarshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8");
						jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
						java.io.StringWriter sw = new StringWriter();
						jaxbMarshaller.marshal(villagewiseLGDDetailsXML, sw);
						response = Response.ok().type(MediaType.APPLICATION_XML).entity(sw.toString().getBytes("UTF-8"))
								.build();

					} else {
						response = Response.serverError().entity(" data not present ").build();
					}
				} else {
					response = Response.serverError().entity("village code is required").build();
				}

			}

		} catch (Exception e) {
			response = Response.serverError().entity(e).build();
			e.printStackTrace();
			log.error("Exception-->" + e);
		}
		return response;
	}

	@POST
	@Path("/lbwiseWardDetails")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response lbwiseWardDetails(@Context ServletContext servletContext,
			@DefaultValue("0") @FormParam("authKey") String authKey, @FormParam("outputFormat") String outputFormat,
			@FormParam("localbodyCode") String localbodyCode) {
		Response response = null;
		try {
			init(servletContext);
			lgdWebServiceForm.setAuthKey(authKey);
			lgdWebServiceForm.setEntityCode(localbodyCode);
			lgdWebServiceForm.setEntityType(WebServiceConstant.ENTITY_TYPE_LOCALBODY.toString());
			String errorMessage = lgdWebServiceValidator.validateEntityWithoutOutputFormat(lgdWebServiceForm);
			if (errorMessage != null) {
				response = Response.serverError().entity(errorMessage).build();
			} else {
				if (localbodyCode != null) {
					List<WardDetailList> wardDetailList = wsServiceImpl
							.getWardDetailsbyLocalbodyCode(Integer.parseInt(localbodyCode));

					if (wardDetailList != null && !wardDetailList.isEmpty()) {
						if (outputFormat != null) {
							if (("json").equals(outputFormat)) {
								response = Response.ok().type(MediaType.APPLICATION_JSON).entity(wardDetailList)
										.build();
							} else if (("xml").equals(outputFormat)) {

								WardDetailListXML wardDetailListXML = new WardDetailListXML();
								wardDetailListXML.setWardDetailList(wardDetailList);

								JAXBContext jaxbContext = JAXBContext.newInstance(WardDetailListXML.class);
								Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
								jaxbMarshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8");
								jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
								java.io.StringWriter sw = new StringWriter();
								jaxbMarshaller.marshal(wardDetailListXML, sw);
								response = Response.ok().type(MediaType.APPLICATION_XML)
										.entity(sw.toString().getBytes("UTF-8")).build();

							}

						} else {
							response = Response.serverError().entity("outputFormat is required").build();
						}
					} else {
						response = Response.serverError().entity("There is no ward exists in this localbody").build();
					}
				} else {
					response = Response.serverError().entity("Localbody Code is required").build();
				}

			}
		} catch (Exception e) {
			response = Response.serverError().entity(e).build();
			e.printStackTrace();
			log.error("Exception-->" + e);
		}
		return response;
	}

	// By Pranav
	@POST
	@Path("/localBodyList")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response getLocalBodyList(@Context ServletContext servletContext,
			@DefaultValue("0") @FormParam("authKey") String authKey, @FormParam("outputFormat") String outputFormat,
			@FormParam("localbodyTypeCode") String localbodyTypeCode, @FormParam("stateCode") String stateCode) {

		Response response = null;
		try {
			init(servletContext);
			lgdWebServiceForm.setAuthKey(authKey);
			lgdWebServiceForm.setOutputFormat(outputFormat);
			lgdWebServiceForm.setEntityCode(localbodyTypeCode);
			lgdWebServiceForm.setEntityCode(stateCode);
			lgdWebServiceForm.setEntityType(WebServiceConstant.ENTITY_TYPE_STATE.toString());
			lgdWebServiceForm.setEntityType(WebServiceConstant.ENTITY_TYPE_LOCALBODY.toString());
			lgdWebServiceForm.setOutputType(WebServiceConstant.ENTITY_TYPE_LOCALBODY.toString());

			String errorMessage = lgdWebServiceValidator.validateEntity(lgdWebServiceForm);
			if (errorMessage != null) {
				response = Response.serverError().entity(errorMessage).build();
			} else {
				List<LocalbodyListbyStateold> localbodyList = wsServiceImpl
						.getExistingPanchayatList(Integer.parseInt(localbodyTypeCode), Integer.parseInt(stateCode));
				if (localbodyList != null && !localbodyList.isEmpty()) {
					if (outputFormat != null) {
						if (("json").equals(outputFormat)) {
							response = Response.ok().type(MediaType.APPLICATION_JSON).entity(localbodyList).build();
						} else if (("xml").equals(outputFormat)) {
							LocalbodyNewXML localbodyNewXML = null;
							LocalbodyListNewXML localbodyListNewXML = new LocalbodyListNewXML();
							localbodyListNewXML.setLocalbodyNewXML(new ArrayList<LocalbodyNewXML>());
							for (LocalbodyListbyStateold obj : localbodyList) {
								localbodyNewXML = new LocalbodyNewXML();
								localbodyNewXML.setLocalBodyCode(obj.getLocalBodyCode());
								localbodyNewXML.setLocalBodyTypeCode(obj.getLocalBodyTypeCode());
								localbodyNewXML.setLocalBodyTypeName(obj.getLocalBodyTypeName());
								localbodyNewXML.setLocalBodyNameEnglish(obj.getLocalBodyNameEnglish());
								localbodyNewXML.setLocalBodyNameLocal(obj.getLocalBodyNameLocal());
								localbodyListNewXML.getLocalbodyNewXML().add(localbodyNewXML);
							}

							JAXBContext jaxbContext = JAXBContext.newInstance(LocalbodyListNewXML.class);
							Marshaller JaxbMarsheller = jaxbContext.createMarshaller();
							JaxbMarsheller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
							java.io.StringWriter sw = new StringWriter();
							JaxbMarsheller.marshal(localbodyListNewXML, sw);
							response = Response.ok().type(MediaType.APPLICATION_XML)
									.entity(sw.toString().getBytes("UTF-8")).build();

						}

					} else {
						StringBuilder output = new StringBuilder(
								"<sdp><response  name='Title' value='LocalBody Services' />");
						for (LocalbodyListbyStateold localbodyListbyStateold : localbodyList) {
							output.append("<response name='");
							output.append(localbodyListbyStateold.getLocalBodyTypeCode());
							output.append("' value='");
							output.append(localbodyListbyStateold.getLocalBodyNameEnglish() == null ? null
									: localbodyListbyStateold.getLocalBodyNameEnglish().trim());
							output.append("' />");
						}
						output.append("</sdp>");
						response = Response.ok().type(MediaType.APPLICATION_XML)
								.entity(output.toString().getBytes("UTF-8")).build();
					}

				} else {
					response = Response.serverError().entity("There is no Local Body Details found for provided inputs")
							.build();
				}
			}

		} catch (Exception e) {
			response = Response.serverError().entity(e).build();
			e.printStackTrace();
			log.error("Exception -->" + e);
		}

		return response;
	}

	@POST
	@Path("/childLocalBodyList")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response getChildLocalBodyList(@Context ServletContext servletContext,
			@DefaultValue("0") @FormParam("authKey") String authKey, @FormParam("outputFormat") String outputFormat,
			@FormParam("localbodyCode") String localbodyCode) {

		Response response = null;
		try {
			init(servletContext);
			lgdWebServiceForm.setAuthKey(authKey);
			lgdWebServiceForm.setOutputFormat(outputFormat);
			lgdWebServiceForm.setEntityCode(localbodyCode);
			lgdWebServiceForm.setEntityType(WebServiceConstant.ENTITY_TYPE_LOCALBODY.toString());
			lgdWebServiceForm.setOutputType(WebServiceConstant.ENTITY_TYPE_LOCALBODY.toString());

			String errorMessage = lgdWebServiceValidator.validateEntity(lgdWebServiceForm);
			if (errorMessage != null) {
				response = Response.serverError().entity(errorMessage).build();
			} else {
				// List<LocalbodyListbyStateold> localbodyList =
				// wsServiceImpl.getExistingPanchayatList(Integer.parseInt(localbodyTypeCode),
				// Integer.parseInt(stateCode));
				List<ParentWiseLBList> childLocalbodyLis = wsServiceImpl
						.getParentWiseLBList(Integer.parseInt(localbodyCode));
				if (childLocalbodyLis != null && !childLocalbodyLis.isEmpty()) {
					if (outputFormat != null) {
						if (("json").equals(outputFormat)) {
							response = Response.ok().type(MediaType.APPLICATION_JSON).entity(childLocalbodyLis).build();
						} else if (("xml").equals(outputFormat)) {
							LocalbodyNewXML localbodyNewXML = null;
							LocalbodyListNewXML localbodyListNewXML = new LocalbodyListNewXML();
							localbodyListNewXML.setLocalbodyNewXML(new ArrayList<LocalbodyNewXML>());
							for (ParentWiseLBList obj : childLocalbodyLis) {
								localbodyNewXML = new LocalbodyNewXML();
								localbodyNewXML.setLocalBodyCode(Integer.parseInt(obj.getLocalBodyCode()));
								localbodyNewXML.setLocalBodyNameEnglish(obj.getLocalBodyNameEng());
								localbodyNewXML.setLocalBodyNameLocal(obj.getLocalBodyNameLocal());
								localbodyListNewXML.getLocalbodyNewXML().add(localbodyNewXML);
							}

							JAXBContext jaxbContext = JAXBContext.newInstance(LocalbodyListNewXML.class);
							Marshaller JaxbMarsheller = jaxbContext.createMarshaller();
							JaxbMarsheller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
							java.io.StringWriter sw = new StringWriter();
							JaxbMarsheller.marshal(localbodyListNewXML, sw);
							response = Response.ok().type(MediaType.APPLICATION_XML)
									.entity(sw.toString().getBytes("UTF-8")).build();

						}

					} else {
						StringBuilder output = new StringBuilder(
								"<sdp><response  name='Title' value='LocalBody Services' />");
						for (ParentWiseLBList obj : childLocalbodyLis) {
							output.append("<response name='");
							output.append(obj.getLocalBodyCode());
							output.append("' value='");
							output.append(obj.getLocalBodyNameEng() == null ? null : obj.getLocalBodyNameEng().trim());
							output.append("' />");
						}
						output.append("</sdp>");
						response = Response.ok().type(MediaType.APPLICATION_XML)
								.entity(output.toString().getBytes("UTF-8")).build();
					}

				} else {
					response = Response.serverError()
							.entity(lgdWebServiceValidator.showOutputEmptyError(lgdWebServiceForm)).build();
				}
			}

		} catch (Exception e) {
			response = Response.serverError().entity(e).build();
			e.printStackTrace();
			log.error("Exception -->" + e);
		}

		return response;
	}

	@POST
	@Path("/localbodyCoverage")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response getLBCoverage(@Context ServletContext servletContext,
			@DefaultValue("0") @FormParam("authKey") String authKey, @FormParam("outputFormat") String outputFormat,
			@FormParam("localbodyCode") String localbodyCode) {

		Response response = null;
		try {
			init(servletContext);
			lgdWebServiceForm.setAuthKey(authKey);
			lgdWebServiceForm.setOutputFormat(outputFormat);
			lgdWebServiceForm.setEntityCode(localbodyCode);
			lgdWebServiceForm.setEntityType(WebServiceConstant.ENTITY_TYPE_LOCALBODY.toString());
			lgdWebServiceForm.setOutputType(WebServiceConstant.ENTITY_TYPE_LOCALBODY.toString());

			String errorMessage = lgdWebServiceValidator.validateEntity(lgdWebServiceForm);
			if (errorMessage != null) {
				response = Response.serverError().entity(errorMessage).build();
			} else {
				// List<LocalbodyListbyStateold> localbodyList =
				// wsServiceImpl.getExistingPanchayatList(Integer.parseInt(localbodyTypeCode),
				// Integer.parseInt(stateCode));
				if (lgdWebServiceForm.getEntityCode() == null) {
					response = Response.serverError().entity("localbody code must reuired").build();
				} else {
					List<LandRegion> lbCoverageList = wsServiceImpl
							.getcoveredLandregionByLocalbodyCode(Integer.parseInt(localbodyCode));
					if (lbCoverageList != null && !lbCoverageList.isEmpty()) {
						if (outputFormat != null) {
							if (("json").equals(outputFormat)) {
								List<LandRegion> lbCoverageListFinal = new ArrayList<LandRegion>();
								LandRegion landRegionFinal = null;
								for (LandRegion obj : lbCoverageList) {
									landRegionFinal = new LandRegion();
									landRegionFinal.setLandRegionCode(obj.getLandRegionCode());
									landRegionFinal.setLandRegionNameEnglish(obj.getLandRegionNameEnglish() != null
											? obj.getLandRegionNameEnglish().trim() : "");
									landRegionFinal.setLandRegionNameLocal(obj.getLandRegionNameLocal() != null
											? obj.getLandRegionNameLocal().trim() : "");
									landRegionFinal.setLandRegionType(obj.getLandRegionType());
									lbCoverageListFinal.add(landRegionFinal);
								}
								response = Response.ok().type(MediaType.APPLICATION_JSON).entity(lbCoverageListFinal)
										.build();
							} else if (("xml").equals(outputFormat)) {
								LocalbodyCoverageXML localbodyCoverageXML = null;
								LocalbodyCoverageListXML localbodyCoverageListXML = new LocalbodyCoverageListXML();
								localbodyCoverageListXML
										.setLocalbodyCoverageXMLList(new ArrayList<LocalbodyCoverageXML>());

								for (LandRegion obj : lbCoverageList) {
									localbodyCoverageXML = new LocalbodyCoverageXML();
									localbodyCoverageXML.setLandRegionCode(obj.getLandRegionCode());
									localbodyCoverageXML.setLandRegionNameEnglish(obj.getLandRegionNameEnglish() != null
											? obj.getLandRegionNameEnglish().trim() : "");
									localbodyCoverageXML.setLandRegionNameLocal(obj.getLandRegionNameLocal() != null
											? obj.getLandRegionNameLocal().trim() : "");
									localbodyCoverageXML.setLandRegionType(String.valueOf(obj.getLandRegionType()));
									localbodyCoverageListXML.getLocalbodyCoverageXMLList().add(localbodyCoverageXML);
								}

								JAXBContext jaxbContext = JAXBContext.newInstance(LocalbodyCoverageListXML.class);
								Marshaller JaxbMarsheller = jaxbContext.createMarshaller();
								JaxbMarsheller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
								java.io.StringWriter sw = new StringWriter();
								JaxbMarsheller.marshal(localbodyCoverageListXML, sw);
								response = Response.ok().type(MediaType.APPLICATION_XML)
										.entity(sw.toString().getBytes("UTF-8")).build();

							}

						} else {
							StringBuilder output = new StringBuilder(
									"<sdp><response  name='Title' value='LocalBody Coverage Services' />");
							for (LandRegion obj : lbCoverageList) {
								output.append("<response name='");
								output.append(obj.getLandRegionCode());
								output.append("' value='");
								output.append(obj.getLandRegionNameEnglish() == null ? null
										: obj.getLandRegionNameEnglish().trim());
								output.append("' />");
							}
							output.append("</sdp>");
							response = Response.ok().type(MediaType.APPLICATION_XML)
									.entity(output.toString().getBytes("UTF-8")).build();
						}

					} else {
						response = Response.serverError()
								.entity(lgdWebServiceValidator.showOutputEmptyError(lgdWebServiceForm)).build();
					}
				}

			}

		} catch (Exception e) {
			response = Response.serverError().entity(e).build();
			e.printStackTrace();
			log.error("Exception -->" + e);
		}

		return response;
	}

	@POST
	@Path("/blockList")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response getBlockList(@Context ServletContext servletContext, @FormParam("outputFormat") String outputFormat,
			@DefaultValue("0") @FormParam("authKey") String authKey,
			@DefaultValue("0") @FormParam(value = "districtCode") String districtCode) {
		Response response = null;
		try {
			init(servletContext);
			lgdWebServiceForm.setOutputFormat(outputFormat);
			lgdWebServiceForm.setAuthKey(authKey);
			lgdWebServiceForm.setEntityCode(districtCode);
			lgdWebServiceForm.setEntityType(WebServiceConstant.ENTITY_NAME_DISTRICT.toString());
			lgdWebServiceForm.setOutputType(WebServiceConstant.ENTITY_NAME_BLOCK.toString());
			String errorMessage = lgdWebServiceValidator.validateEntity(lgdWebServiceForm);
			if (errorMessage != null) {
				response = Response.serverError().entity(errorMessage).build();
			} else {
				List<Block> blockList = wsServiceImpl.getBlockListbyDistrictCode(Integer.parseInt(districtCode),
						Boolean.TRUE);
				if (blockList != null && !blockList.isEmpty()) {
					WSBlock wsBlock = null;
					WSBlockXMLList wsBlockXMLList = new WSBlockXMLList();
					wsBlockXMLList.setWsBlockList(new ArrayList<WSBlock>());

					for (Block obj : blockList) {
						wsBlock = new WSBlock();
						wsBlock.setBlockCode(obj.getBlockCode());
						wsBlock.setBlockNameEnglish(obj.getBlockNameEnglish() != null ? obj.getBlockNameEnglish().trim()
								: (("xml").equalsIgnoreCase(outputFormat) ? null : ""));
						wsBlock.setBlockNameLocal(obj.getBlockNameLocal() != null ? obj.getBlockNameLocal().trim()
								: (("xml").equalsIgnoreCase(outputFormat) ? null : ""));
						wsBlockXMLList.getWsBlockList().add(wsBlock);

					}
					if (outputFormat != null) {
						if (("json").equals(outputFormat)) {
							response = Response.ok().type(MediaType.APPLICATION_JSON).entity(wsBlockXMLList).build();
						} else if (("xml").equals(outputFormat)) {

							JAXBContext jaxbContext = JAXBContext.newInstance(WSBlockXMLList.class);
							Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
							jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
							java.io.StringWriter sw = new StringWriter();
							jaxbMarshaller.marshal(wsBlockXMLList, sw);
							response = Response.ok().type(MediaType.APPLICATION_XML)
									.entity(sw.toString().getBytes("UTF-8")).build();
						}

					} else {
						StringBuilder output = new StringBuilder(
								"<sdp><response  name='Title' value='Block Services' />");
						for (Block obj : blockList) {
							output.append("<response name='");
							output.append(obj.getBlockCode());
							output.append("' value='");
							output.append(obj.getBlockNameEnglish() == null ? null : obj.getBlockNameEnglish().trim());
							output.append("' />");
						}
						output.append("</sdp>");
						response = Response.ok().type(MediaType.APPLICATION_XML)
								.entity(output.toString().getBytes("UTF-8")).build();

					}

				} else {
					response = Response.serverError()
							.entity(lgdWebServiceValidator.showOutputEmptyError(lgdWebServiceForm)).build();
				}
			}
		} catch (Exception e) {
			response = Response.serverError().entity(e).build();
			e.printStackTrace();
			log.error("Exception-->" + e);
		}
		return response;
	}

	@POST
	@Path("/getBlockwiseMappedGP")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response getBlockNGaramPanchyatList(@Context ServletContext servletContext,
			@FormParam("outputFormat") String outputFormat, @DefaultValue("0") @FormParam("authKey") String authKey,
			@DefaultValue("0") @FormParam(value = "blockCode") String blockCode) {
		Response response = null;
		try {
			init(servletContext);
			lgdWebServiceForm.setOutputFormat(outputFormat);
			lgdWebServiceForm.setAuthKey(authKey);
			lgdWebServiceForm.setEntityCode(blockCode);
			lgdWebServiceForm.setEntityType(WebServiceConstant.ENTITY_NAME_BLOCK.toString());
			lgdWebServiceForm.setOutputType(WebServiceConstant.ENTITY_NAME_BLOCK.toString());
			String errorMessage = lgdWebServiceValidator.validateEntity(lgdWebServiceForm);
			if (errorMessage != null) {
				response = Response.serverError().entity(errorMessage).build();
			} else {
				List<WSLocalbody> wsLocalbodyList = wsServiceImpl
						.getDistrictwiseBlockandGramPanchyat(Integer.parseInt(blockCode));
				if (wsLocalbodyList != null && !wsLocalbodyList.isEmpty()) {
					if (outputFormat != null) {

						LocalbodyXML localbodyXML = null;
						LocalbodyListXML localbodyListXML = new LocalbodyListXML();
						localbodyListXML.setLocalbodyXMLList(new ArrayList<LocalbodyXML>());
						for (WSLocalbody obj : wsLocalbodyList) {
							localbodyXML = new LocalbodyXML();
							localbodyXML.setLocalbodyCode(obj.getLocalbodyCode());
							localbodyXML.setLocalbodyNameEnglish(
									obj.getLocalbodyNameEnglish() != null ? obj.getLocalbodyNameEnglish().trim()
											: (("xml").equalsIgnoreCase(outputFormat) ? null : ""));
							localbodyXML.setLocalbodyNameLocal(
									obj.getLocalbodyNameLocal() != null ? obj.getLocalbodyNameLocal().trim()
											: (("xml").equalsIgnoreCase(outputFormat) ? null : ""));
							localbodyListXML.getLocalbodyXMLList().add(localbodyXML);
						}

						if (("json").equals(outputFormat)) {
							response = Response.ok().type(MediaType.APPLICATION_JSON).entity(localbodyListXML).build();
						} else if (("xml").equals(outputFormat)) {

							JAXBContext jaxbContext = JAXBContext.newInstance(LocalbodyListXML.class);
							Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
							jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
							java.io.StringWriter sw = new StringWriter();
							jaxbMarshaller.marshal(localbodyListXML, sw);
							response = Response.ok().type(MediaType.APPLICATION_XML)
									.entity(sw.toString().getBytes("UTF-8")).build();
						}

					}
				} else {
					response = Response.serverError()
							.entity(lgdWebServiceValidator.showOutputEmptyError(lgdWebServiceForm)).build();
				}
			}
		} catch (Exception e) {
			response = Response.serverError().entity(e).build();
			e.printStackTrace();
			log.error("Exception-->" + e);
		}
		return response;
	}

	@POST
	@Path("/saveLGDDepartmentDegination")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response saveLGDDepartmentDegination(@Context ServletContext servletContext,
			@DefaultValue("0") @FormParam("authKey") String authKey, @FormParam("olc") Integer olc,
			@FormParam("userId") Integer userId, @FormParam("stateCode") Integer stateCode,
			@FormParam("paramString") String paramString) {
		Response response = null;
		try {
			/*
			 * paramString Patteren -
			 * degCode##degNameEng##degNameLocal##orgLotatLvl1,orgLotatLvl2##
			 * mulOffice##isRegPost@@
			 */
			System.out.println("olc-->" + olc);
			System.out.println("userId-->" + userId);
			System.out.println("stateCode-->" + stateCode);
			System.out.println("paramString" + paramString);

			init(servletContext);
			List<String> outputString = new ArrayList<>();

			String errorMessage = lgdWebServiceValidator.validateDepartmentDesignation(paramString, olc);
			if (errorMessage != null) {
				response = Response.serverError().entity(errorMessage).build();
			} else {
				boolean NameChange = false;
				boolean LevelChange = false;
				boolean AddNew = false;
				LgdDesignation deleteLgdDesignation = null;
				List<LgdDesignation> deleteNodeList = new ArrayList<LgdDesignation>();
				boolean isNextVesrion = false;
				// boolean addDesignationFlag=false;

				String designationType = "D";
				Integer locatedAtLevel = 0;
				LgdDesignation lgdDesign = null;
				List<LgdDesignation> existDesignationsList = designationService
						.getExistingDesignationsForDepartment(olc);
				if (existDesignationsList != null && !existDesignationsList.isEmpty()) {
					/*
					 * if(designations.split("@@").length>existDesignationsList.
					 * size()){ addDesignationFlag=true;
					 * 
					 * }
					 */

					for (int i = 0; i < existDesignationsList.size(); i++) {
						if (checkDeleteNode(existDesignationsList.get(i).getDesignationCode(), paramString)) {
							deleteLgdDesignation = existDesignationsList.get(i);
							deleteLgdDesignation.setIsActive(false);
							deleteLgdDesignation.setNextVersion(true);
							deleteLgdDesignation.setOpeationCode(null);
							deleteNodeList.add(deleteLgdDesignation);
						}
					}
				}

				Character opearionType = 'U';

				Scanner scanner = new Scanner(paramString);
				scanner.useDelimiter("@@");

				while (scanner.hasNext()) {
					isNextVesrion = false;
					String rowvalues = scanner.next();
					String[] designationColumns = rowvalues.split("\\##");
					Integer desCode = null;
					String inputDesCode = designationColumns[0];// Designation
																// Code
					if (inputDesCode != null && !"".equals(inputDesCode)) {
						desCode = Integer.parseInt(inputDesCode);
					}

					AddNew = false;
					List<DesignationLevelSortorder> existLevelList = null;

					AddNew = desCode == null ? true : false;
					if (desCode != null) {
						lgdDesign = designationService.getDesignationById(desCode);
						existLevelList = designationService.getExistingDesignationLevel(lgdDesign);
						NameChange = !lgdDesign.getDesignationName().equals(designationColumns[1]);
						LevelChange = this.checkLevelChange(existLevelList, designationColumns[3]);
						// checkNewNode(desCode,existDesignationsList);
						if ((NameChange) || LevelChange || AddNew) {
							lgdDesign.setLastupdated(new Date());
							lgdDesign.setLastupdatedby(userId);
							Integer currentVersion = lgdDesign.getLgdDesignationPK().getDesignationVersion();
							lgdDesign.getLgdDesignationPK().setDesignationVersion(currentVersion + 1);
							isNextVesrion = true;
							lgdDesign.setCreatedby(userId);
							lgdDesign.setCreatedon(new Date());
							lgdDesign.setLastupdated(new Date());
							lgdDesign.setLastupdatedby(userId);

						}

					} else {

						lgdDesign = new LgdDesignation();

						lgdDesign.setIsTopDesignation(false);
						LgdDesignationPK lgdDesignationPK = new LgdDesignationPK();
						lgdDesignationPK.setDesignationVersion(1);
						lgdDesignationPK
								.setDesignationCode(designationService.getNextDesignationCodeFromLgdDesignation());
						lgdDesign.setLgdDesignationPK(lgdDesignationPK);
						lgdDesign.setCreatedby(userId);
						lgdDesign.setCreatedon(new Date());
						lgdDesign.setLastupdated(new Date());
						lgdDesign.setLastupdatedby(userId);

					}

					if (NameChange && LevelChange) {
						lgdDesign.setOpeationCode(
								Integer.parseInt(DesignationConstant.MANAGE_DESIGNATION_OPERATION_CODE.toString()));
						lgdDesign.setDescription(
								"English Name of designation and Level details of '{DESIGNATION_NAME}' having designation code '{DESIGNATION_CODE}' of '{ORGANIZATION_OR_LOCAL_BODY_TYPE}' organization/local body type has been updated.");
					} else if (NameChange) {
						lgdDesign.setOpeationCode(Integer
								.parseInt(DesignationConstant.RENAME_DESIGNATION_NAME_OPERATION_CODE.toString()));
						lgdDesign.setDescription(
								"English Name of designation '{DESIGNATION_NAME}' having designation code '{DESIGNATION_CODE}' of '{ORGANIZATION_OR_LOCAL_BODY_TYPE}' organization/local body type has been updated.");
					} else if (LevelChange) {
						lgdDesign.setOpeationCode(Integer
								.parseInt(DesignationConstant.ADD_REMOVE_DESIGNATION_LEVEL_OPERATION_CODE.toString()));
						lgdDesign.setDescription(
								"Designation '{DESIGNATION_NAME}' having designation code '{DESIGNATION_CODE}' of '{ORGANIZATION_OR_LOCAL_BODY_TYPE}' organization/local body type is added for a new level/remove from a level.");
					} else if (AddNew) {
						lgdDesign.setOpeationCode(
								Integer.parseInt(DesignationConstant.CREATE_NEW_DESIGNATION_OPERATION_CODE.toString()));
						lgdDesign.setDescription(
								"New Designation '{DESIGNATION_NAME}' having designation code '{DESIGNATION_CODE}' is created for '{ORGANIZATION_OR_LOCAL_BODY_TYPE}' organization/local body type.");
						opearionType = 'N';

					}
					lgdDesign.setStateCode(stateCode);
					lgdDesign.setNextVersion(isNextVesrion);
					lgdDesign.setEffectiveDate(new Date());
					lgdDesign.setOlc(olc);
					lgdDesign.setDesignationName(designationColumns[1]); // Designation
																			// Name
					lgdDesign.setDesignationNameLocal(designationColumns[2]);// Designation
																				// Name
																				// in
																				// Local
					Boolean isMultiple = null;
					try {
						isMultiple = Boolean.parseBoolean(designationColumns[4]); // Is
																					// Multiple
																					// Designation
					} catch (Exception e) {
						isMultiple = null;
					}
					lgdDesign.setIsMultiple(isMultiple);
					lgdDesign.setDesignationType(designationType);
					lgdDesign.setIsActive(true);
					Boolean isContractPerma = null;
					try {
						isContractPerma = Boolean.parseBoolean(designationColumns[5]);// Is
																						// Contract
																						// Permanent
					} catch (Exception e) {
						isContractPerma = null;
					}
					lgdDesign.setIsContractPermanent(isContractPerma);
					List<DesignationLevelSortorder> addedLevelList = new ArrayList<DesignationLevelSortorder>();

					String levels = designationColumns[3];

					if (levels != null && !"".equals(levels)) {

						String[] selectedLevels = levels.split(",");

						DesignationLevelSortorder designationLevelSortorder = null;
						Integer srNo = null;
						if (selectedLevels != null && selectedLevels.length > 0) {
							for (String strLevelId : selectedLevels) {
								if (isNextVesrion == false && existLevelList != null && !existLevelList.isEmpty()) {
									for (DesignationLevelSortorder obj : existLevelList) {
										if (obj.getOrgLocatedLevelCode().equals(Integer.parseInt(strLevelId))) {
											srNo = obj.getSrNo();
											break;
										}
									}

								}
								designationLevelSortorder = new DesignationLevelSortorder();
								designationLevelSortorder.setSrNo(srNo);
								designationLevelSortorder.setLgdDesignationCode(lgdDesign);
								designationLevelSortorder.setOrgLocatedLevelCode(Integer.parseInt(strLevelId));
								locatedAtLevel = designationService.getLocatedAtLevel(Integer.parseInt(strLevelId));
								designationLevelSortorder.setLocatedAtLevel(locatedAtLevel);
								designationLevelSortorder.setIsActive(true);
								addedLevelList.add(designationLevelSortorder);

							}
						}
					} else {
						List<OrgLocatedAtLevels> orgLocatedAtLevelsList = designationService
								.getOrgLocatedLevelList(olc);
						int tempLevel = 999;
						Integer srNo = null;
						OrgLocatedAtLevels insertOrgLocatedAtLevels = null;
						for (OrgLocatedAtLevels obj : orgLocatedAtLevelsList) {
							if (tempLevel > obj.getLocatedAtLevel()) {
								tempLevel = obj.getLocatedAtLevel();
								insertOrgLocatedAtLevels = obj;
							}
						}

						if (isNextVesrion == false && existLevelList != null && !existLevelList.isEmpty()) {
							for (DesignationLevelSortorder obj : existLevelList) {
								if (obj.getOrgLocatedLevelCode()
										.equals(insertOrgLocatedAtLevels.getOrgLocatedLevelCode())) {
									srNo = obj.getSrNo();
									break;
								}
							}
						}
						DesignationLevelSortorder designationLevelSortorder = null;
						designationLevelSortorder = new DesignationLevelSortorder();
						designationLevelSortorder.setSrNo(srNo);
						designationLevelSortorder.setLgdDesignationCode(lgdDesign);
						designationLevelSortorder
								.setOrgLocatedLevelCode(insertOrgLocatedAtLevels.getOrgLocatedLevelCode());

						designationLevelSortorder.setLocatedAtLevel(insertOrgLocatedAtLevels.getLocatedAtLevel());
						designationLevelSortorder.setIsActive(true);
						addedLevelList.add(designationLevelSortorder);

					}
					// }

					lgdDesign.setDesignationLevelList(addedLevelList);
					lgdDesign.setDesignationAddedBy("E");
					Integer saveDesCode = designationService.saveOrUpdateWebserviceDesignation(lgdDesign);
					if (saveDesCode != null) {

						String temp = saveDesCode + "##" + lgdDesign.getDesignationName() + "##"
								+ lgdDesign.getDesignationNameLocal() + "##"
								+ getDesiginationLevel(lgdDesign.getDesignationLevelList()) + "##"
								+ lgdDesign.getIsMultiple() + "##" + lgdDesign.getIsContractPermanent() + "##"
								+ opearionType + "@";
						outputString.add(temp);
					} else {
						response = Response.serverError().entity("FAILURE").build();
						return response;
					}
				}
				for (LgdDesignation deletelgdDesignation : deleteNodeList) {
					designationService.saveOrUpdate(deletelgdDesignation);
					String temp = deletelgdDesignation.getDesignationCode() + "##"
							+ deletelgdDesignation.getDesignationName() + "##"
							+ deletelgdDesignation.getDesignationNameLocal() + "##"
							+ getDesiginationLevel(deletelgdDesignation.getDesignationLevelList()) + "##"
							+ deletelgdDesignation.getIsMultiple() + "##"
							+ deletelgdDesignation.getIsContractPermanent() + "##D@";
					outputString.add(temp);

				}
				scanner.close();

				response = Response.ok().type(MediaType.APPLICATION_FORM_URLENCODED)
						.entity("SUCCESS$" + outputString.toString()).build();
			}

		} catch (Exception e) {
			response = Response.serverError().entity("FAILURE").build();
			e.printStackTrace();
			log.error("LGDWebService-->saveLGDDepartmentDegination-->" + e);
		}
		return response;

	}

	@POST
	@Path("/saveLGDDepartmentDeginationReorder")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response saveLGDDepartmentDeginationReorder(@Context ServletContext servletContext,
			@DefaultValue("0") @FormParam("authKey") String authKey, @FormParam("olc") Integer olc,
			@FormParam("orgLocatedLevelCode") Integer orgLocatedLevelCode,
			@FormParam("paramString") String paramString) {
		Response response = null;
		try {
			/*
			 * paramString Patteren -
			 * degCode##degNameEng##degNameLocal##orgLotatLvl1,orgLotatLvl2##
			 * mulOffice##isRegPost@@
			 */
			init(servletContext);
			String errorMessage = lgdWebServiceValidator.validateDepartmentDesignationReorder(paramString, olc,
					orgLocatedLevelCode);
			if (errorMessage != null) {
				response = Response.serverError().entity(errorMessage).build();
			} else {

				List<DesignationLevelSortorder> desigLevelList = null;
				List<DesignationLevelSortorder> newDesigLevelList = null;
				LgdDesignation lgdDesign = null;
				DesignationLevelSortorder desigLevelSortOrder = null;

				int sortOrder = 0;
				Scanner scanner = new Scanner(paramString);
				scanner.useDelimiter("##");
				while (scanner.hasNext()) {
					sortOrder++;
					newDesigLevelList = new ArrayList<DesignationLevelSortorder>();
					Integer desCode = Integer.parseInt(scanner.next());
					lgdDesign = designationService.getDesignationById(desCode);
					Integer currentVersion = lgdDesign.getDesignationVersion();
					lgdDesign.getLgdDesignationPK().setDesignationVersion(currentVersion + 1);
					lgdDesign.setNextVersion(Boolean.TRUE);
					lgdDesign.setOpeationCode(Integer
							.parseInt(DesignationConstant.CHANGE_DESIGNATION_SORT_ORDER_OPERATION_CODE.toString()));
					lgdDesign.setDescription(
							"Sort order of designation {DESIGNATION_NAME} having designation code '{DESIGNATION_CODE}' of '{ORGANIZATION_OR_LOCAL_BODY_TYPE}' organization/local body type has been updated.");
					desigLevelList = designationService.getdesignationLevelsbyDesigCode(desCode);

					for (int i = 0; i < desigLevelList.size(); i++) {
						if (desigLevelList.get(i).getOrgLocatedLevelCode().equals(orgLocatedLevelCode)) {
							desigLevelList.get(i).setSortOrder(sortOrder);
							desigLevelSortOrder = desigLevelList.get(i);
							desigLevelSortOrder.setLgdDesignationCode(lgdDesign);
							desigLevelSortOrder.setSrNo(null);
							newDesigLevelList.add(desigLevelSortOrder);
						} else {
							desigLevelSortOrder = desigLevelList.get(i);
							desigLevelSortOrder.setLgdDesignationCode(lgdDesign);
							desigLevelSortOrder.setSrNo(null);
							newDesigLevelList.add(desigLevelSortOrder);
						}
					}
					if (sortOrder == 1) {
						lgdDesign.setIsTopDesignation(true);
					}
					lgdDesign.setDesignationLevelList(newDesigLevelList);
					designationService.saveOrUpdate(lgdDesign);
					// designationService.saveOrUpdateReorderLevel(desigLevelSortOrder);
				}
				scanner.close();

				response = Response.ok().type(MediaType.APPLICATION_FORM_URLENCODED).entity("SUCCESS").build();
			}
		} catch (Exception e) {
			response = Response.serverError().entity("FAILURE").build();
			e.printStackTrace();
			log.error("LGDWebService-->saveLGDDepartmentDeginationReorder-->" + e);
		}
		return response;

	}

	private String getDesiginationLevel(List<DesignationLevelSortorder> designationLevelList) throws Exception {

		List<String> desigLelListStr = new ArrayList<>();
		for (DesignationLevelSortorder obj : designationLevelList) {
			desigLelListStr.add(obj.getOrgLocatedLevelCode().toString());
		}
		return desigLelListStr.toString();
	}

	public boolean checkDeleteNode(Integer existDesigCode, String currentDesig) throws Exception {
		boolean isDeleteNode = true;
		Integer curDesigCode = null;
		Scanner scanner = new Scanner(currentDesig);

		scanner.useDelimiter("@@");
		while (scanner.hasNext()) {
			String rowvalues = scanner.next();
			String[] designationColumns = rowvalues.split("\\##");
			if (designationColumns != null && designationColumns[0] != null
					&& designationColumns[0].trim().length() > 0) {
				curDesigCode = Integer.parseInt(designationColumns[0]);
				if (curDesigCode.equals(existDesigCode)) {
					isDeleteNode = false;
					break;
				}
			}
		}
		return isDeleteNode;
	}

	public boolean checkNewNode(Integer currentDesigCode, List<LgdDesignation> existDesignationsList) throws Exception {
		boolean isNewNode = true;
		for (LgdDesignation lgdDesignation : existDesignationsList) {
			if (currentDesigCode.equals(lgdDesignation.getDesignationCode())) {
				isNewNode = false;
				break;
			}
		}
		return isNewNode;
	}

	public boolean checkLevelChange(List<DesignationLevelSortorder> existLevelList, String levels) throws Exception {
		boolean isLevelChange = false;
		boolean isFind = false;
		if (existLevelList != null && !existLevelList.isEmpty() && levels.length() > 0) {
			String levelsArr[] = levels.split(",");
			if (levelsArr.length == existLevelList.size()) {

				for (DesignationLevelSortorder designationLevelSortorder : existLevelList) {
					isFind = false;
					Scanner scanner = new Scanner(levels);
					scanner.useDelimiter(",");
					while (scanner.hasNext()) {
						Integer selOrgLocatedLevelCode = Integer.parseInt(scanner.next());
						if (selOrgLocatedLevelCode.equals(designationLevelSortorder.getOrgLocatedLevelCode())) {
							isFind = true;
							break;
						}
					}
					if (!isFind) {
						isLevelChange = true;
						break;
					}
				}
			} else {
				isLevelChange = true;
			}

		} else {
			isLevelChange = false;
		}

		return isLevelChange;
	}

	/**
	 * This method take district code and returns the urban localbody for that
	 * district.
	 * 
	 * @author sourabhrai
	 * @param servletContext
	 * @param outputFormat
	 * @param authKey
	 * @param districtCode
	 * @param lbTypeCode
	 * @return
	 */
	@POST
	@Path("/fetchUrbanLocalBodyForGivenDistrict")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response fetchUrbanLocalBodyForGivenDistrict(@Context ServletContext servletContext,
			@FormParam("outputFormat") String outputFormat, @DefaultValue("0") @FormParam("authKey") String authKey,
			@FormParam("landRegionType") String landRegiontype, @FormParam("landRegionCode") String landRegionCode) {
		Response response = null;
		try {
			init(servletContext);
			landRegiontype = landRegiontype.toUpperCase();
			lgdWebServiceForm.setOutputFormat(outputFormat);
			lgdWebServiceForm.setAuthKey(authKey);
			lgdWebServiceForm.setEntityCode(landRegionCode);
			lgdWebServiceForm.setEntityType(landRegiontype);
			// lgdWebServiceForm.setLbTypeCode(lbTypeCode);
			lgdWebServiceForm.setOutputType(WebServiceConstant.ENTITY_TYPE_LOCALBODY.toString());
			String errorMessage = lgdWebServiceValidator.validateLbTypeCode(lgdWebServiceForm);
			if (errorMessage != null) {
				response = Response.serverError().entity(errorMessage).build();
			} else {
				List<WSLocalbody> wsLocalbodyList = wsServiceImpl.getUrbanLocalbodyBasedOnLandRegiontypeAndCode(
						landRegiontype, Integer.parseInt(landRegionCode));
				if (wsLocalbodyList != null && !wsLocalbodyList.isEmpty()) {
					if (outputFormat != null) {
						if (("json").equals(outputFormat)) {
							response = Response.ok().type(MediaType.APPLICATION_JSON).entity(wsLocalbodyList).build();
						} else if (("xml").equals(outputFormat)) {
							LocalbodyXML localbodyXML = null;
							LocalbodyListXML localbodyListXML = new LocalbodyListXML();
							localbodyListXML.setLocalbodyXMLList(new ArrayList<LocalbodyXML>());
							for (WSLocalbody obj : wsLocalbodyList) {
								localbodyXML = new LocalbodyXML();
								localbodyXML.setLocalbodyCode(obj.getLocalbodyCode());
								localbodyXML.setLocalbodyNameEnglish(obj.getLocalbodyNameEnglish());
								localbodyXML.setLocalbodyNameLocal(obj.getLocalbodyNameLocal());

								localbodyListXML.getLocalbodyXMLList().add(localbodyXML);
							}
							JAXBContext jaxbContext = JAXBContext.newInstance(LocalbodyListXML.class);
							Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
							jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
							java.io.StringWriter sw = new StringWriter();
							jaxbMarshaller.marshal(localbodyListXML, sw);
							response = Response.ok().type(MediaType.APPLICATION_XML)
									.entity(sw.toString().getBytes("UTF-8")).build();
						}

					} else {
						StringBuilder output = new StringBuilder(
								"<sdp><response  name='Title' value='Localbody Services' />");
						for (WSLocalbody wsLocalbody : wsLocalbodyList) {
							output.append("<response name='");
							output.append(wsLocalbody.getLocalbodyCode());
							output.append("' value='");
							output.append(wsLocalbody.getLocalbodyNameEnglish() == null ? null
									: wsLocalbody.getLocalbodyNameEnglish().trim());
							output.append("' />");
						}
						output.append("</sdp>");
						response = Response.ok().type(MediaType.APPLICATION_XML)
								.entity(output.toString().getBytes("UTF-8")).build();

					}

				} else {
					response = Response.serverError()
							.entity(lgdWebServiceValidator.showOutputEmptyError(lgdWebServiceForm)).build();
				}
			}
		} catch (Exception e) {
			response = Response.serverError().entity(e).build();
			e.printStackTrace();
			log.error("Exception-->" + e);
		}
		return response;
	}

	@POST
	@Path("/villageListWithHierarchy")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response getVillageListWithHierarchy(@Context ServletContext servletContext,
			@FormParam("outputFormat") String outputFormat, @DefaultValue("0") @FormParam("authKey") String authKey,
			@DefaultValue("0") @FormParam(value = "subDistrictCode") Integer subDistrictCode) {
		Response response = null;
		try {
			init(servletContext);
			lgdWebServiceForm.setOutputFormat(outputFormat);
			lgdWebServiceForm.setAuthKey(authKey);
			lgdWebServiceForm.setEntityCode(String.valueOf(subDistrictCode));
			lgdWebServiceForm.setOutputType(WebServiceConstant.ENTITY_TYPE_VILLAGE.toString());
			lgdWebServiceForm.setEntityType(WebServiceConstant.ENTITY_TYPE_SUBDISTRICT.toString());

			String errorMessage = lgdWebServiceValidator.validateEntity(lgdWebServiceForm);
			if (errorMessage != null) {
				response = Response.serverError().entity(errorMessage).build();
			} else {
				List<VillageListWithHierarchy> villageListWithHierarchy = wsServiceImpl
						.fetchVillageListWithHierarchy(subDistrictCode);
				if (villageListWithHierarchy != null && !villageListWithHierarchy.isEmpty()) {
					if (outputFormat != null) {
						if (("json").equals(outputFormat)) {
							VillageListXML villageListXML = new VillageListXML();
							villageListXML.setVillageXMLList(new ArrayList<VillageXML>());

							response = Response.ok().type(MediaType.APPLICATION_JSON).entity(villageListWithHierarchy)
									.build();

						} else if (("xml").equals(outputFormat)) {
							VillageListWithHierarchyXML villageListWithHierarchyXML = null;
							VillageListWithHierarchyListXML villageListWithHierarchyListXML = new VillageListWithHierarchyListXML();
							villageListWithHierarchyListXML
									.setVillageListWithHierarchyXMLList(new ArrayList<VillageListWithHierarchyXML>());
							for (VillageListWithHierarchy obj : villageListWithHierarchy) {
								villageListWithHierarchyXML = new VillageListWithHierarchyXML();
								villageListWithHierarchyXML.setStateCode(obj.getStateCode());
								villageListWithHierarchyXML.setStateNameEnglish(obj.getStateNameEnglish());
								villageListWithHierarchyXML.setDistrictCode(obj.getDistrictCode());
								villageListWithHierarchyXML.setDistrictNameEnglish(obj.getDistrictNameEnglish());
								villageListWithHierarchyXML.setSubDistrictCode(obj.getSubDistrictCode());
								villageListWithHierarchyXML.setSubDistrictNameEnglish(obj.getSubDistrictNameEnglish());
								villageListWithHierarchyXML.setBlockCode(obj.getBlockCode());
								villageListWithHierarchyXML.setBlockNameEnglish(obj.getBlockNameEnglish());
								villageListWithHierarchyXML.setLocalBodyCode(obj.getLocalBodyCode());
								villageListWithHierarchyXML.setVillageCode(obj.getVillageCode());
								villageListWithHierarchyXML.setVillageNameEnglish(obj.getVillageNameEnglish());
								villageListWithHierarchyXML.setLocalBodyNameEnglish(obj.getLocalBodyNameEnglish());
								villageListWithHierarchyXML.setVillageStatus(obj.getVillageStatus());
								villageListWithHierarchyXML.setLocalBodyTypeCode(obj.getLocalBodyTypeCode());
								villageListWithHierarchyListXML.getVillageListWithHierarchyXMLList()
										.add(villageListWithHierarchyXML);

							}
							JAXBContext jaxbContext = JAXBContext.newInstance(VillageListWithHierarchyListXML.class);
							Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
							jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
							java.io.StringWriter sw = new StringWriter();
							jaxbMarshaller.marshal(villageListWithHierarchyListXML, sw);
							response = Response.ok().type(MediaType.APPLICATION_XML)
									.entity(sw.toString().getBytes("UTF-8")).build();
						}

					} else {
						response = Response.serverError()
								.entity(lgdWebServiceValidator.showOutputEmptyError(lgdWebServiceForm)).build();
					}
				}
			}
		} catch (Exception e) {
			response = Response.serverError().entity(e).build();
			e.printStackTrace();
			log.error("Exception-->" + e);
		}
		return response;
	}

	@GET
	@Path("/percentStateBlockMapped")
	@Produces({ MediaType.APPLICATION_XML })
	public StateWiseGPVillageMappedEntity getStateBlocWiseMapListXml(@Context ServletContext servletContext,
			@DefaultValue("A") @QueryParam(value = "finYear") String finYear,
			@QueryParam(value = "stateCode") Integer stateCode) throws Exception {
		StateWiseGPVillageMappedEntity stateBlocWiseMapped = new StateWiseGPVillageMappedEntity();
		try {
			ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
			wsServiceImpl = ctx.getBean(WSService.class);
			if (stateCode != null && stateCode > 0)
				stateBlocWiseMapped = wsServiceImpl.getStateBlocWiseMapped(stateCode, finYear, QUERY_FLAG_MAPPED_VILLAGE);
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
		return stateBlocWiseMapped;
	}

	/*
	 * @GET
	 * 
	 * @Path("/coveredVillagesOfBlock")
	 * 
	 * @Produces ({MediaType.APPLICATION_XML })
	 */

	@POST
	@Path("/CoveredVillagesOfBlock")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response getCoveredVillagesOfBlock(@Context ServletContext servletContext,
			@FormParam("outputFormat") String outputFormat, @DefaultValue("0") @FormParam("authKey") String authKey,
			@DefaultValue("0") @FormParam(value = "blockCode") Integer blockCode) {
		Response response = null;
		try {
			init(servletContext);
			lgdWebServiceForm.setOutputFormat(outputFormat);
			lgdWebServiceForm.setAuthKey(authKey);
			lgdWebServiceForm.setEntityCode(String.valueOf(blockCode));
			lgdWebServiceForm.setEntityType(WebServiceConstant.ENTITY_TYPE_BLOCK.toString());
			
			String errorMessage = lgdWebServiceValidator.validateEntity(lgdWebServiceForm);
			if (errorMessage != null) {
				response = Response.serverError().entity(errorMessage).build();
			} else { 
				List<CoveredVillagesOfBlock> coveredvillagesofblock = wsServiceImpl.fetchCoveredVillagesOfBlock (blockCode);
				if (coveredvillagesofblock != null && !coveredvillagesofblock.isEmpty()) {
					if (outputFormat != null) {
						
						if (("json").equals(outputFormat)) {
							response = Response.ok().type(MediaType.APPLICATION_JSON).entity(coveredvillagesofblock).build();
						} 
						else if (("xml").equals(outputFormat)) {
							CoveredVillagesOfBlockXML coveredVillagesOfBlockXML = null;
							 CoveredVillagesOfBlockEntityXML coveredVillagesOfBlockEntityXML = new CoveredVillagesOfBlockEntityXML();
							 coveredVillagesOfBlockEntityXML.setCoveredVillagesOfBlockEntityXML(new ArrayList<CoveredVillagesOfBlockXML>());
							for (CoveredVillagesOfBlock obj : coveredvillagesofblock) {
								coveredVillagesOfBlockXML = new CoveredVillagesOfBlockXML();
								coveredVillagesOfBlockXML.setVillageCode(obj.getVillageCode());
								coveredVillagesOfBlockXML.setVillageNameEnglish(obj.getVillageNameEnglish());
								coveredVillagesOfBlockXML.setVillageNameLocal(obj.getVillageNameLocal());
								coveredVillagesOfBlockXML.setCensus2011Code(obj.getCensus2011Code());
								coveredVillagesOfBlockEntityXML.getCoveredVillagesOfBlockEntityXML().add(coveredVillagesOfBlockXML);
							}
							JAXBContext jaxbContext = JAXBContext.newInstance(CoveredVillagesOfBlockEntityXML.class);
							Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
							jaxbMarshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8");
							jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
							java.io.StringWriter sw = new StringWriter();
							jaxbMarshaller.marshal(coveredVillagesOfBlockEntityXML, sw);
							response = Response.ok().type(MediaType.APPLICATION_XML)
									.entity(sw.toString().getBytes("UTF-8")).build();
						}
					else {
						response = Response.serverError()
								.entity(lgdWebServiceValidator.showOutputEmptyError(lgdWebServiceForm)).build();
						}

	             }
			}	
	
			}
		}
		  catch (Exception e) {
			response = Response.serverError().entity(e).build();
			e.printStackTrace();
			log.error("Exception-->" + e);
		}
		return response;
	}
	
	@POST
	@Path("/get_hierarchy_of_entity") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response getHierarchyOfEntity(@Context ServletContext servletContext,
			@FormParam("outputFormat") String outputFormat, @DefaultValue("0") @FormParam("authKey") String authKey,
			@FormParam("entityType") String entityType, @DefaultValue("0") @FormParam(value = "entityCode") Integer entityCode) {
		Response response = null;
		 Boolean errorMessage =false;
		 
		try {
			init(servletContext);
			lgdWebServiceForm.setOutputFormat(outputFormat);
			lgdWebServiceForm.setAuthKey(authKey);
			lgdWebServiceForm.setEntityCode(String.valueOf(entityCode));
			lgdWebServiceForm.setEntityType(entityType);
			

			 errorMessage = lgdWebServiceValidator.validateGetHierarchyOfEntity(lgdWebServiceForm);
			if (errorMessage == false) {
				response = Response.serverError().entity(errorMessage).build();
			} else {
				List<GetHierarchyOfEntity> getHierarchyOfEntityList = wsServiceImpl.getHierarchyOfEntity(entityType,entityCode);
				if (getHierarchyOfEntityList != null && !getHierarchyOfEntityList.isEmpty()) {
					if (outputFormat != null) {
						if (("json").equals(outputFormat)) {
								String getHierarchyOfEntityRecord =new String();
								
								for(GetHierarchyOfEntity getHierarchyOfEntity: getHierarchyOfEntityList) {
								 ObjectMapper mapper = new ObjectMapper();
								   mapper.enable(SerializationFeature.INDENT_OUTPUT);
								    getHierarchyOfEntityRecord =getHierarchyOfEntityRecord+ mapper.writeValueAsString(getHierarchyOfEntity);
							
								}
							response = Response.ok().type(MediaType.APPLICATION_JSON).entity(getHierarchyOfEntityRecord).build();
							} 
						   else if (("xml").equals(outputFormat)) {
							GetHierarchyOfEntityXML getHierarchyOfEntityXML = null;
							GetHierarchyOfEntityXmlList getHierarchyOfEntityXmlList = new GetHierarchyOfEntityXmlList();
							getHierarchyOfEntityXmlList.setGetHierarchyOfEntityXMLList(new ArrayList<GetHierarchyOfEntityXML>());
							for (GetHierarchyOfEntity obj : getHierarchyOfEntityList) {
								getHierarchyOfEntityXML = new GetHierarchyOfEntityXML();
								getHierarchyOfEntityXML.setStateCode(obj.getStateCode());
								getHierarchyOfEntityXML.setStateNameEnglish(obj.getStateNameEnglish());
								getHierarchyOfEntityXML.setDistrictCode(obj.getDistrictCode());
								getHierarchyOfEntityXML.setDistrictNameEnglish(obj.getDistrictNameEnglish());
								getHierarchyOfEntityXML.setSubDistrictCode(obj.getSubDistrictCode());
								getHierarchyOfEntityXML.setSubDistrictNameEnglish(obj.getSubDistrictNameEnglish());
								getHierarchyOfEntityXML.setBlockCode(obj.getBlockCode());
								getHierarchyOfEntityXML.setBlockNameEnglish(obj.getBlockNameEnglish());
								getHierarchyOfEntityXML.setLocalBodyCode(obj.getLocalBodyCode());
								getHierarchyOfEntityXML.setVillageCode(obj.getVillageCode());
								getHierarchyOfEntityXML.setVillageNameEnglish(obj.getVillageNameEnglish());
								getHierarchyOfEntityXML.setLocalBodyNameEnglish(obj.getLocalBodyNameEnglish());
								getHierarchyOfEntityXML.setLocalBodyTypeName(obj.getLocalBodyTypeName());
								getHierarchyOfEntityXML.setLocalBodyTypeCode(obj.getLocalBodyTypeCode());
								getHierarchyOfEntityXmlList.getGetHierarchyOfEntityXMLList()
										.add(getHierarchyOfEntityXML);

							}
							JAXBContext jaxbContext = JAXBContext.newInstance(GetHierarchyOfEntityXmlList.class);
							Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
							jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
							java.io.StringWriter sw = new StringWriter();
							jaxbMarshaller.marshal(getHierarchyOfEntityXmlList, sw);
							response = Response.ok().type(MediaType.APPLICATION_XML)
									.entity(sw.toString().getBytes("UTF-8")).build();
						}

					} else {
						response = Response.serverError()
								.entity(lgdWebServiceValidator.showOutputEmptyError(lgdWebServiceForm)).build();
					}
				}
			}
		} catch (Exception e) {
			response = Response.serverError().entity(e).build();
			e.printStackTrace();
			log.error("Exception-->" + e);
		}
		return response;
	}
	
	
	@GET
	@Path("/percentCensusVillage")
	@Produces({ MediaType.APPLICATION_XML })
	public StateWiseGPVillageMappedEntity percentCensusVillage(@Context ServletContext servletContext,
			@DefaultValue("A") @QueryParam(value = "finYear") String finYear,
			@QueryParam(value = "stateCode") Integer stateCode) throws Exception {
		StateWiseGPVillageMappedEntity stateBlocWiseMapped = new StateWiseGPVillageMappedEntity();
		try {
			ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
			wsServiceImpl = ctx.getBean(WSService.class);
			if (stateCode != null && stateCode > 0)
				stateBlocWiseMapped = wsServiceImpl.getStateBlocWiseMapped(stateCode, finYear, QUERY_FLAG_CENSUS_VILLAGE);
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
		return stateBlocWiseMapped;
	}

	@GET
	@Path("/percentGPIntegratedwithGIS")
	@Produces({ MediaType.APPLICATION_XML })
	public StateWiseGPVillageMappedEntity percentGPIntegratedwithGIS(@Context ServletContext servletContext,
				@QueryParam(value = "stateCode") Integer stateCode) throws Exception {
		StateWiseGPVillageMappedEntity stateBlocWiseMapped = new StateWiseGPVillageMappedEntity();
		try {
			ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
			wsServiceImpl = ctx.getBean(WSService.class);
			if (stateCode != null && stateCode > 0)
				stateBlocWiseMapped = wsServiceImpl.getStateBlocWiseMapped(stateCode, null, QUERY_FLAG_GRAM_PANCHAYAT_USING_GIS);
		} catch (Exception e) {
			log.error("Exception-->" + e);
		}
		return stateBlocWiseMapped;
	}
	
	@POST
	@Path("/villageListALL")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response getVillageList(@Context ServletContext servletContext,
			@FormParam("outputFormat") String outputFormat, @DefaultValue("0") @FormParam("authKey") String authKey,
			@DefaultValue("1") @FormParam(value = "page") Integer page) {
		Response response = null;
		try {
			init(servletContext);
			lgdWebServiceForm.setOutputFormat(outputFormat);
			lgdWebServiceForm.setAuthKey(authKey);
		    lgdWebServiceForm.setOutputType(WebServiceConstant.ENTITY_TYPE_VILLAGE.toString());
			

			String errorMessage = lgdWebServiceValidator.validateEntity(lgdWebServiceForm);
			if (errorMessage != null) {
				response = Response.serverError().entity(errorMessage).build();
			} else {
				List<WSVillage> wsVillageList = wsServiceImpl.getVillageListALL(page);
				if (wsVillageList != null && !wsVillageList.isEmpty()) {
					if (outputFormat != null) {
						if (("json").equals(outputFormat)) {
							response = Response.ok().type(MediaType.APPLICATION_JSON).entity(wsVillageList).build();
						} else if (("xml").equals(outputFormat)) {
							VillageXML villageXML = null;
							VillageListXML villageListXML = new VillageListXML();
							villageListXML.setVillageXMLList(new ArrayList<VillageXML>());
							for (WSVillage obj : wsVillageList) {
								villageXML = new VillageXML();
								villageXML.setVillageCode(obj.getVillageCode());
								villageXML.setVillageNameEnglish(obj.getVillageNameEnglish());
								villageXML.setVillageNameLocal(obj.getVillageNameLocal());
								villageXML.setCensus2001Code(obj.getCensus2001Code());
								villageXML.setCensus2011Code(obj.getCensus2011Code());
								villageXML.setSsCode(obj.getSsCode());
								villageListXML.getVillageXMLList().add(villageXML);
							}
							JAXBContext jaxbContext = JAXBContext.newInstance(VillageListXML.class);
							Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
							jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
							java.io.StringWriter sw = new StringWriter();
							jaxbMarshaller.marshal(villageListXML, sw);
							response = Response.ok().type(MediaType.APPLICATION_XML)
									.entity(sw.toString().getBytes("UTF-8")).build();
						}

					} else {
						StringBuilder output = new StringBuilder(
								"<sdp><response  name='Title' value='Village Services' />");
						for (WSVillage wsVillage : wsVillageList) {
							output.append("<response name='");
							output.append(wsVillage.getVillageCode());
							output.append("' value='");
							output.append(wsVillage.getVillageNameEnglish() == null ? null
									: wsVillage.getVillageNameEnglish().trim());
							output.append("' />");
						}
						output.append("</sdp>");
						response = Response.ok().type(MediaType.APPLICATION_XML)
								.entity(output.toString().getBytes("UTF-8")).build();

					}

				} else {
					response = Response.serverError()
							.entity(lgdWebServiceValidator.showOutputEmptyError(lgdWebServiceForm)).build();
				}
			}
		} catch (Exception e) {
			response = Response.serverError().entity(e).build();
			e.printStackTrace();
			log.error("Exception-->" + e);
		}
		return response;
	}

	
}
			

			
	

