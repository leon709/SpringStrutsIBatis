package leon.ssi.dao.impl;

import java.util.List;
import java.util.Map;

import leon.ssi.dao.StudentDAO;
import leon.ssi.ibatis.IBatisDAOSupport;
import leon.ssi.model.Student;
import leon.ssi.model.StudentCourseDetailBean;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDAOImpl extends IBatisDAOSupport<StudentDAO> implements
		StudentDAO {

	@Override
	public List<Student> getAllStudent(Map<String, String> param) throws Exception {
		SqlSession session = this.getSqlSession();
		try {
			return this.getMapper(StudentDAO.class, session).getAllStudent(param);
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			this.closeSqlSession(session);
		}
	}

	@Override
	public void addStudent(Map<String, Object> paraMap) throws Exception {
		SqlSession session = this.getSqlSession();
		try {
			this.getMapper(StudentDAO.class, session).addStudent(paraMap);
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			this.closeSqlSession(session);
		}
	}

	@Override
	public void delStudent(Map<String, Object> paraMap) throws Exception {
		SqlSession session = this.getSqlSession();
		try {
			this.getMapper(StudentDAO.class, session).delStudent(paraMap);
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			this.closeSqlSession(session);
		}
	}

	@Override
	public List<StudentCourseDetailBean> getStudentCourseDetail(
			Map<String, String> param) throws Exception {
		SqlSession session = this.getSqlSession();
		try {
			return this.getMapper(StudentDAO.class, session).getStudentCourseDetail(param);
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			this.closeSqlSession(session);
		}
	}

}
