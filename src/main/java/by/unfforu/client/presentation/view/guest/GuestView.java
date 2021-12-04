package by.unfforu.client.presentation.view.guest;

import by.unfforu.client.entity.user.User;
import by.unfforu.client.presentation.view.PresentationView;
import by.unfforu.client.service.StudentClientService;

/**
 * Default guest form
 */
public class GuestView extends PresentationView
{
    /**
     * Constructor
     *
     * @param studentService student service
     * @param user user instance
     */
    public GuestView(StudentClientService studentService, User user)
    {
        super(studentService, user);
    }

    /**
     * Show default guest form
     */
    @Override
    public void show()
    {
        System.out.println("1: Register\n2: Login\n3: Exit");
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
            case "1" -> new RegisterView(this.studentService, this.currentUser);
            case "2" -> new LoginView(this.studentService, this.currentUser);
            case "3" -> null;
            default -> throw new IllegalArgumentException();
        };
    }
}
