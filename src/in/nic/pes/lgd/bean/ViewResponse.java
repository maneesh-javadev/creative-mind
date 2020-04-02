package in.nic.pes.lgd.bean;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;




@Entity
@NamedNativeQuery(query = "select * from pescommon.get_viewResponseList_by_appID(1)", name ="ResponseFAQ",resultClass=GetFaq.class)
public class ViewResponse {
	 @Id
	  @Basic(optional = false)
	  @Column(name = "faq_language_id", nullable = false)
	  private Integer faqLanguageId;
	  
	  
	  @Basic(optional = false)
	  @Column(name = "query_question_text", nullable = false, length = 500)
	  private String queryQuestionText;
	 
	  @Basic(optional = false)
	  @Column(name = "query_answer_text", nullable = false, length = 500)
	   private String faqAnswerText;

	public Integer getFaqLanguageId() {
		return faqLanguageId;
	}

	public void setFaqLanguageId(Integer faqLanguageId) {
		this.faqLanguageId = faqLanguageId;
	}

	public String getQueryQuestionText() {
		return queryQuestionText;
	}

	public void setQueryQuestionText(String queryQuestionText) {
		this.queryQuestionText = queryQuestionText;
	}

	public String getFaqAnswerText() {
		return faqAnswerText;
	}

	public void setFaqAnswerText(String faqAnswerText) {
		this.faqAnswerText = faqAnswerText;
	}
	  
	
	
	

}



