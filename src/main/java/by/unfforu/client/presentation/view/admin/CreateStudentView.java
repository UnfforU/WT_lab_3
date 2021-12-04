package by.unfforu.client.presentation.view.admin;

import by.unfforu.client.entity.student.Student;
import by.unfforu.client.entity.user.User;
import by.unfforu.client.presentation.view.PresentationView;
import by.unfforu.client.presentation.view.setters.SetInputStudent;
import by.unfforu.client.service.StudentClientService;
import org.javatuples.Pair;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Get create student form
 */
public class CreateStudentView extends PresentationView
{
    /**
     * Input fields
     */
    private final List<Pair<String, SetInputStudent>> inputs = Arrays.asList(
            new Pair<>("Name:", (student, input) -> {
                student.setName(input);
                return true;
            }),
            new Pair<>("Birthday (dd/mm/yyyy):", (student, input) -> {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                try
                {
                    student.setBirthday(LocalDate.parse(input, dateTimeFormatter));

                    return true;
                }
                catch (DateTimeParseException ex)
                {
                    System.out.println("Invalid format!");
                }

                return false;
            }),
            new Pair<>("Characteristic:", (student, input) -> {
                student.setCharacteristic(input);
                return true;
            })
    );

    /**
     * Constructor
     *
     * @param studentService student service
     * @param user user instance
     */
    public CreateStudentView(StudentClientService studentService, User user)
    {
        super(studentService, user);
    }

    /**
     * Show create student form
     */
    @Override
    public void show()
    {
        System.out.println("Enter 'exit' to go back");

        Student student = new Student();
        Scanner scanner = new Scanner(System.in);

        int i = 0;
        String input;

        // Get information about a new user
        while (i < inputs.size())
        {
            System.out.println(inputs.get(i).getValue0());
            input = scanner.nextLine();
            if (input.equals("exit"))
            {
                return;
            }

            if (inputs.get(i).getValue1().setInput(student, input))
            {
                i++;
            }
            else
            {
                System.out.println("Invalid input!");
            }
        }

        // Add last modification field and try to save a new user
        student.setLastModification(LocalDateTime.now());
        if (!this.studentService.create(student))
        {
            System.out.println("Error creating!");
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
        return new AdminView(this.studentService, this.currentUser);
    }
}
