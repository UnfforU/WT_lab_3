package by.unfforu.client.presentation.view.admin;

import by.unfforu.client.entity.user.User;
import by.unfforu.client.presentation.view.user.GetAllStudentView;
import by.unfforu.client.presentation.view.PresentationView;
import by.unfforu.client.service.StudentClientService;

/**
 * Admin default form
 */
public class AdminView extends PresentationView
{
    /**
     * Constructor
     *
     * @param studentService student service
     * @param user user instance
     */
    public AdminView(StudentClientService studentService, User user)
    {
        super(studentService, user);
    }

    /**
     * Show default form
     */
    @Override
    public void show()
    {
        System.out.println("1: Get students list\n2: Edit student\n3: Create student\n4: Exit");
    }

    /**
     * Get user input command
     *
     * @param input user input command
     *
     * @return PresentationView
     */
    @Override
    public PresentationView getInput(String input) {
        return switch (input) {
            case "1" -> new GetAllStudentView(this.studentService, this.currentUser);
            case "2" -> new EditAllStudentView(this.studentService, this.currentUser);
            case "3" -> new CreateStudentView(this.studentService, this.currentUser);
            case "4" -> null;
            default -> throw new IllegalArgumentException();
        };
    }
}
