package by.unfforu.client.presentation;

import by.unfforu.client.entity.role.UserRole;
import by.unfforu.client.entity.user.User;
import by.unfforu.client.presentation.view.guest.GuestView;
import by.unfforu.client.presentation.view.PresentationView;
import by.unfforu.client.service.StudentClientService;
import java.util.Scanner;

/**
 * Form view
 */
public class Presentation
{
    /**
     * View via user role
     */
    private PresentationView view;

    /**
     * Current user
     */
    private User currentUser;

    /**
     * Constructor
     *
     * @param studentService student service
     */
    public Presentation(StudentClientService studentService)
    {
        this.currentUser = new User();
        this.currentUser.setRole(UserRole.GUEST);

        this.view = new GuestView(studentService, this.currentUser);
    }

    /**
     * Get user input command
     *
     * @param input input command
     *
     * @return boolean
     */
    private boolean getInput(String input)
    {
        try
        {
            // Get a new form view
            this.view = this.view.getInput(input);

            return true;
        }
        catch (IllegalArgumentException ex)
        {
            return false;
        }
    }

    /**
     * Show a new form via user role
     */
    public void show()
    {
        Scanner scanner = new Scanner(System.in);
        while (this.view != null)
        {
            this.view.show();

            while (!getInput(scanner.nextLine()))
            {
                System.out.println("Input fail!");
            }
        }
    }
}
