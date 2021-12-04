package by.unfforu.server.service;

import by.unfforu.server.dao.StudentServerDAO;
import by.unfforu.server.service.impl.StudentServerServiceImpl;

/**
 * Service factory class
 */
public class ServiceServerFactory
{
    /**
     * Service factory instance
     */
    private static final ServiceServerFactory instance = new ServiceServerFactory();

    /**
     * Constructor
     */
    private ServiceServerFactory()
    {

    }

    /**
     * Create student service instance
     *
     * @param studentDAO student data object
     *
     * @return StudentServerService
     */
    public StudentServerService getStudentService(StudentServerDAO studentDAO)
    {
        return new StudentServerServiceImpl(studentDAO);
    }

    /**
     * Get service factory instance
     *
     * @return ServiceServerFactory
     */
    public static ServiceServerFactory getInstance()
    {
        return instance;
    }
}
