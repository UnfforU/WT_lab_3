package by.unfforu.client.presentation.view;

import by.unfforu.client.entity.user.User;
import by.unfforu.client.presentation.viewModel.PresentationModel;
import by.unfforu.client.service.StudentClientService;

/**
 * Presentation view class
 */
public abstract class PresentationView
{
    /**
     * Presentation model
     */
    protected PresentationModel model;

    /**
     * Student service
     */
    protected StudentClientService studentService;

    /**
     * User instance
     */
    protected User currentUser;

    /**
     * Constructor
     *
     * @param studentService student service
     * @param user user instance
     */
    protected PresentationView(StudentClientService studentService, User user)
    {
        this.studentService = studentService;
        this.currentUser = user;
    }

    /**
     * Get user input command
     *
     * @param input user input command
     *
     * @return PresentationView
     */
    public abstract PresentationView getInput(String input);

    /**
     * Show a new form view
     */
    public abstract void show();
}
