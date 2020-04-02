
package in.nic.pes.lgd.bean;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;


@Entity
@NamedNativeQuery(query = " SELECT * FROM pescommon.get_faqList_by_appID(:appId)", name ="getFAQDetails",resultClass=GetFaq.class)
public class GetFaq {
	 @Id
	  @Basic(optional = false)
	  @Column(name = "faq_language_id", nullable = false)
	  private Integer faqLanguageId;
	  
	  @Basic(optional = false)
	  @Column(name = "faq_question_text", nullable = false, length = 500)
	  private String faqQuestionText;
	 
	  @Basic(optional = false)
	  @Column(name = "faq_answer_text", nullable = false, length = 500)
	   private String faqAnswerText;
	  
		public String getFaqQuestionText() {
			return faqQuestionText;
		}
		public void setFaqQuestionText(String faqQuestionText) {
			this.faqQuestionText = faqQuestionText;
		}
		public String getFaqAnswerText() {
			return faqAnswerText;
		}
		public void setFaqAnswerText(String faqAnswerText) {
			this.faqAnswerText = faqAnswerText;
		}
		public Integer getFaqLanguageId() {
			return faqLanguageId;
		}
		public void setFaqLanguageId(Integer faqLanguageId) {
			this.faqLanguageId = faqLanguageId;
		}
	
	
	
	

}



