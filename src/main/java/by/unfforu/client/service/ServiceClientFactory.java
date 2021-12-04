package by.unfforu.client.service;

import by.unfforu.client.dao.StudentClientDAO;
import by.unfforu.client.service.impl.StudentClientServiceImpl;

/**
 * Service factory class
 */
public class ServiceClientFactory
{
    /**
     * Service factory instance
     */
    private static final ServiceClientFactory instance = new ServiceClientFactory();

    /**
     * Constructor
     */
    private ServiceClientFactory()
    {

    }

    /**
     * Create student service instance
     *
     * @param studentDAO student data object
     *
     * @return StudentClientService
     */
    public StudentClientService getStudentService(StudentClientDAO studentDAO)
    {
        return new StudentClientServiceImpl(studentDAO);
    }

    /**
     * Get service factory instance
     *
     * @return ServiceClientFactory
     */
    public static ServiceClientFactory getInstance()
    {
        return instance;
    }
}
