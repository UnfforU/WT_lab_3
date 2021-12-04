package by.unfforu.client.presentation.view.setters;

import by.unfforu.client.entity.student.Student;

/**
 * Student information input api
 */
public interface SetInputStudent
{
    /**
     * Set student information
     *
     * @param student student information
     * @param input input command
     *
     * @return boolean
     */
    boolean setInput(Student student, String input);
}
