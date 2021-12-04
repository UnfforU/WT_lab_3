package by.unfforu.client.presentation.viewModel.user;

import by.unfforu.client.entity.student.Student;
import by.unfforu.client.presentation.viewModel.PresentationModel;
import by.unfforu.client.service.StudentClientService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Get student information
 */
public class GetStudentModelView extends PresentationModel
{
    /**
     * Student id
     */
    private final int id;

    /**
     * Constructor
     *
     * @param studentService student service
     * @param id student id
     */
    public GetStudentModelView(StudentClientService studentService, int id)
    {
        super(studentService);

        this.id = id;
    }

    /**
     * Get student by id
     *
     * @return List<Student>
     */
    @Override
    public List<Student> getItems()
    {
        Student result = this.studentService.get(this.id);

        return result == null ? new ArrayList<>() : Collections.singletonList(result);
    }
}
