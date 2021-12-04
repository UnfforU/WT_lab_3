package by.unfforu.client.presentation.view.setters;

import by.unfforu.client.entity.user.User;

/**
 * User information input api
 */
public interface SetInputUser
{
    /**
     * Set user information
     *
     * @param user user information
     * @param input input command
     *
     * @return boolean
     */
    boolean setInput(User user, String input);
}
