package in.nic.pes.lgd.validator;

import in.nic.pes.lgd.common.CustomRegexValidator;
import in.nic.pes.lgd.forms.DesignationForm;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("GovtsetupValidator")
public class GovtsetupValidator implements Validator {


	public void validate(Object model, Errors errors) 
	{
		CustomRegexValidator customValidator = CustomRegexValidator.getInstance();
		DesignationForm designationForm = (DesignationForm) model;
		String designName = designationForm.getDesgName();
		String desigNameLocal= designationForm.getDesgNameLocal();
		boolean flag1=false,flag2=false;
		
		/*if (designationForm.getDesgName().trim()==null  && designationForm.getDesgName().trim().equals("")) 	*/
		
		if(designationForm.getLgTypeCode()==0)
		{
			errors.rejectValue("lgTypeCode","Local Government Type is required");
			flag1=true;
			flag2=true;
		}
		
		if((designName==null || designName.equals("")) && flag1==false)
		{
			errors.rejectValue("desgName","Designation name is required");
			flag1=true;
		}
		
		else if(designationForm.getDesgName().contains(","))
		{
			String[] desName = designationForm.getDesgName().split(",");
			for(int i=0; i<desName.length;i++)
			{
				
				if(desName[i].equals("") || desName[i]==null)
				{
					errors.rejectValue("desgName","Designation name is required");
					flag1=true;
					break;
				}
				else
				{
					if (!customValidator.checkforAlphaNumericandSpace(desName[i]))
					{
						errors.rejectValue("desgName","Designation name is allow only (a-z)(A-Z)(0-9) and Space");
						flag1=true;
						break;
						

					}
					
					else if(desName[i].length()>50)
					{
						errors.rejectValue("desgName","Designation name Length must be less then and equal to 50");
						flag1=true;
						break;
						
					}
				}
			}
			if((designationForm.getDesgName().endsWith(",")) && flag1==false)
			{
				errors.rejectValue("desgName","Designation name is required");
				flag1=true;
			
				
			}
			
			
			if(flag1==false)
			{
				
				boolean flag=true;
				String degNames[]=designName.split(",");
				String tempname="";
				for(int i=0;i<degNames.length;i++)
				{
					tempname=degNames[i];
					for(int j=0;j<degNames.length;j++)
					{
						if(tempname.trim().equals(degNames[j].trim()) && i!=j)
						{
							flag=false;
							break;
						}
					}
					
					if(flag==false)
					{
						errors.rejectValue("desgName","*Error-Top Designation Name and Other's Designation Name must be unique");
						break;
					}
				}
			}
					
		}
		
		if((designName==null || designName.equals("")) && flag1==false)
		{
			errors.rejectValue("desgName","Designation name is required");
			flag2=true;
		}
		
		
			
			else if(desigNameLocal !=null && !desigNameLocal.equals(""))
		{
			 if(designationForm.getDesgNameLocal().contains(","))
				{
				 
				 String[] desNameLocal = designationForm.getDesgNameLocal().split(",");
					for(int i=0; i<desNameLocal.length;i++)
					{
						if(desNameLocal[i].equals("") || desNameLocal[i]==null)
						{
							errors.rejectValue("desgName","Designation name Local is required");
							flag2=true;
							break;
							
						}
						else
						{
							if (!customValidator.validateSpecialCharacters(desNameLocal[i]))
							{
								errors.rejectValue("desgName","Designation name Local contain invalided character");
								flag2=true;
								break;
								
								

							}
							if((desNameLocal[i]).length()>60)
							{
								errors.rejectValue("desgName","Designation name Local Length must be less then and equal to 60");
								flag2=true;
								break;
								
							}
						}
					}
					if((designationForm.getDesgNameLocal().endsWith(",")) && flag1==false )
					{
						errors.rejectValue("desgName","Designation name Local is required");
						flag2=true;
					
						
					}
				
					if((desNameLocal==null || desNameLocal.equals(""))&& flag1==false )
					{
						errors.rejectValue("desgName","Designation name Local is required");
						flag2=true;
						
					}
			
				
					if(flag2==false)
					{
						 boolean flag=true;
							String degNamesLocal[]=desigNameLocal.split(",");
							String tempname="";
							for(int i=0;i<degNamesLocal.length;i++)
							{
								tempname=degNamesLocal[i];
								for(int j=0;j<degNamesLocal.length;j++)
								{
									if(tempname.trim().equals(degNamesLocal[j].trim()) && i!=j)
									{
										flag=false;
										break;
									}
								}
								
								if(flag==false)
								{
									errors.rejectValue("desgName","*Error-Top Designation Name Local and Other's Designation Name Local must be unique");
									break;
								}
							}
					}	
					
					
				}
		}
		
		
		
	}

	
	

	
	
	
	
	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
