package by.unfforu.client.main;

import by.unfforu.client.dao.ClientDAOFactory;
import by.unfforu.client.presentation.Presentation;
import by.unfforu.client.service.ServiceClientFactory;

/**
 * Main client class
 */
public class Main
{
    /**
     * Entry point
     *
     * @param args - arguments
     */
    public static void main(String[] args)
    {
        // Initialize layers instances
        ServiceClientFactory factory = ServiceClientFactory.getInstance();
        ClientDAOFactory daoFactory = ClientDAOFactory.getInstance();

        // Show information via user role
        Presentation presentation = new Presentation(factory.getStudentService(daoFactory.getStudentDAO()));
        presentation.show();
    }
}
