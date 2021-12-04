package by.unfforu.client.presentation.viewModel;

import by.unfforu.client.entity.student.Student;
import by.unfforu.client.service.StudentClientService;
import java.util.List;

/**
 * Presentation model class
 */
public abstract class PresentationModel
{
    /**
     * Student service
     */
    protected StudentClientService studentService;

    /**
     * Constructor
     *
     * @param studentService student service
     */
    public PresentationModel(StudentClientService studentService)
    {
        this.studentService = studentService;
    }

    /**
     * Get student by id
     *
     * @return List<Student>
     */
    public abstract List<Student> getItems();
}
