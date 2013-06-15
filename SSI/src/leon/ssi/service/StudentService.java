package leon.ssi.service;

import java.util.List;
import java.util.Map;

import leon.ssi.model.Student;
import leon.ssi.model.StudentCourseDetailBean;

public interface StudentService {
	public void delStudent(String[] stdNo) throws Exception;

	public List<StudentCourseDetailBean> getStudentCourseDetail(
			Map<String, String> param) throws Exception;

	public List<Student> getAllStudent(Map<String, String> param) throws Exception;
}
