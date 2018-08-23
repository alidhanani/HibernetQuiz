package edu.awt.hibernet.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.awt.hibernet.model.Option;
import edu.awt.hibernet.model.Question;
import edu.awt.hibernet.model.Selectoption;


@Service
@Transactional
public class DataSheetService extends BaseService {

	public List<?> getAllQuestions() {
		return getSession().createCriteria(Question.class).list();
	}
	
	public Question getQuestionByNo(int qno){
//		Student student = (Student) getSession().get(Student.class, regno);
//		return student;
		
		Question que = (Question) getSession().get(Question.class, qno);
		return que;
		
		/*return (Question) getSession().createQuery("FROM question WHERE qno = :qno")
				.setParameter("qno", qno)
				.uniqueResult();*/
	}
	
	public List<?> getOptionByQNo(){
//		Student student = (Student) getSession().get(Student.class, regno);
//		return student;
		//String hql = "FROM option WHERE regno = :regno";
		//return getSession().createQuery(hql).setParameter("regno", qno).list();
		return getSession().createCriteria(Option.class).list();
		
		/*Option opt = (Option) getSession().get(Option.class, qno);
		return opt;*/
	}
	
	public void addUserOption(int regno, int qno, int sno, int correct){
		Option option = (Option) getSession().load(Option.class, sno);
		Question question = (Question) getSession().load(Question.class, qno);
		
		
		Selectoption opt = new Selectoption();
		opt.setQuestion(question);
		opt.setOption(option);
		opt.setRegno(regno);
		opt.setCorrect(correct);

		/*getSession().createQuery("INSERT INTO selectoption(Regno, qno, sno, Correct) VALUES (:regno, :qno, :sno, :correct)").setParameter("regno", regno)
		.setParameter("qno", qno).setParameter("sno", sno).setParameter("correct", correct).uniqueResult();
		*/
		getSession().save(opt);
		//getSession().createSQLQuery("INSERT INTO (Regno, qno, sno, Correct) VALUES ("+regno+","+qno+", "+sno+", "+correct+");").uniqueResult();
	}
	
	public Selectoption searchRegno(int Regno) {
		Selectoption opt = (Selectoption) getSession().get(Selectoption.class, Regno);
		return opt;
	}
	
	public List<?> getSelectOption(int qno){
		String hql = "FROM selectoption WHERE qno = :qno";
		return getSession().createQuery(hql).setParameter("qno", qno).list();
	}
	
}
