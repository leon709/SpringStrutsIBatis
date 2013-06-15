package leon.ssi.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import leon.ssi.dao.StudentDAO;
import leon.ssi.model.Student;
import leon.ssi.model.StudentCourseDetailBean;
import leon.ssi.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDAO studentDAO;
	
	@Override
	public void delStudent(String[] stdNo) throws Exception {
		for (String s : stdNo) {
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("stdNo", s);
			studentDAO.delStudent(paraMap);
			throw new Exception("force system to throw a exception");
		}
	}

	@Override
	public List<StudentCourseDetailBean> getStudentCourseDetail(
			Map<String, String> param) throws Exception {
		return studentDAO.getStudentCourseDetail(param);
	}

	@Override
	public List<Student> getAllStudent(Map<String, String> param)
			throws Exception {
		String s = param.get("enableFuzzy");
		String studentName = param.get("studentName");
		if("true".equals(s) && studentName !=null)
			param.put("studentNameLike", "%"+studentName+"%");
		else
			param.put("enableFuzzy", "false");
		return studentDAO.getAllStudent(param);
	}

}
