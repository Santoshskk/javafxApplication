package data;

import java.util.List;

/**
 * de interface Dao.
 *
 * @param <T> the type parameter
 */
public interface DAO <T> {

    /**
     * Gets all.
     *
     * @return the all
     */
    List<T> getAll();

    /**
     * Add or update.
     *
     * @param model the model
     */
    void addOrUpdate(T model);

    /**
     * Delete.
     *
     * @param model the model
     */
    void delete(T model);

    /**
     * Save boolean.
     *
     * @return the boolean
     */
    boolean save();

    /**
     * Load boolean.
     *
     * @return the boolean
     */
    boolean load();
}
