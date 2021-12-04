package by.unfforu.client.presentation.view.user;

import by.unfforu.client.entity.user.User;
import by.unfforu.client.presentation.view.PresentationView;
import by.unfforu.client.service.StudentClientService;

/**
 * User default form
 */
public class UserView extends PresentationView
{
    /**
     * Constructor
     *
     * @param studentService student service
     * @param user user instance
     */
    public UserView(StudentClientService studentService, User user)
    {
        super(studentService, user);
    }

    /**
     * Show default form
     */
    @Override
    public void show()
    {
        System.out.println("1: Get students list\n2: Exit");
    }

    /**
     * Get user input command
     *
     * @param input user input command
     *
     * @return PresentationView
     */
    @Override
    public PresentationView getInput(String input)
    {
        return switch (input)
        {
            case "1" -> new GetAllStudentView(this.studentService, this.currentUser);
            case "2" -> null;
            default -> throw new IllegalArgumentException();
        };
    }
}