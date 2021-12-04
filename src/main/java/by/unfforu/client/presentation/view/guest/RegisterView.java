package by.unfforu.client.presentation.view.guest;

import by.unfforu.client.entity.role.UserRole;
import by.unfforu.client.entity.user.User;
import by.unfforu.client.presentation.view.user.UserView;
import by.unfforu.client.presentation.view.PresentationView;
import by.unfforu.client.presentation.view.admin.AdminView;
import by.unfforu.client.presentation.view.setters.SetInputUser;
import by.unfforu.client.service.StudentClientService;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.javatuples.Pair;

/**
 * Guest register form
 */
public class RegisterView extends PresentationView
{
    /**
     * Input fields
     */
    private final List<Pair<String, SetInputUser>> inputs = Arrays.asList(
            new Pair<>("Login:", (user, input) -> {
                user.setLogin(input);
                return true;
            }),
            new Pair<>("Password:", (user, input) -> {
                user.setPassword(input);
                return true;
            }),
            new Pair<>("Choose role (1: User, 2: Admin)", (user, input) -> {
                if (input.equals("1")) {
                    user.setRole(UserRole.USER);
                    return true;
                }
                else if (input.equals("2"))
                {
                    user.setRole(UserRole.ADMIN);
                    return true;
                }

                return false;
            })
    );

    /**
     * Constructor
     *
     * @param studentService student service
     * @param user user instance
     */
    public RegisterView(StudentClientService studentService, User user)
    {
        super(studentService, user);
    }

    /**
     * Show guest login form
     */
    @Override
    public void show()
    {
        System.out.println("Enter 'exit' to go back");

        Scanner scanner = new Scanner(System.in);
        User user = new User();

        int i = 0;
        String input;

        // Get login, password and user role
        while (i < inputs.size())
        {
            System.out.println(inputs.get(i).getValue0());
            input = scanner.nextLine();
            if (input.equals("exit"))
            {
                return;
            }

            if (inputs.get(i).getValue1().setInput(user, input))
            {
                i++;
            }
            else
            {
                System.out.println("Invalid input!");
            }
        }

        // Try to register a new user
        User auth = this.studentService.register(user);
        if (auth == null)
        {
            System.out.println("User exists!");
        }
        else
        {
            this.currentUser = auth;
        }
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
        return switch (this.currentUser.getRole())
        {
            case GUEST -> new GuestView(this.studentService, this.currentUser);
            case USER -> new UserView(this.studentService, this.currentUser);
            case ADMIN -> new AdminView(this.studentService, this.currentUser);
        };
    }
}
