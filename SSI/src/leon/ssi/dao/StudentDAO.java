package leon.ssi.dao;

import java.util.List;
import java.util.Map;

import leon.ssi.model.Student;
import leon.ssi.model.StudentCourseDetailBean;

public interface StudentDAO {

	public List<Student> getAllStudent(Map<String, String> param) throws Exception;

	public void addStudent(Map<String, Object> paraMap) throws Exception;

	public void delStudent(Map<String, Object> paraMap) throws Exception;

	public List<StudentCourseDetailBean> getStudentCourseDetail(
			Map<String, String> param) throws Exception;
}
