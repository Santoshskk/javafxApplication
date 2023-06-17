package data;

import models.Docent;

import java.util.ArrayList;
import java.util.List;

/**
 * de type Docent dao.
 */
public abstract class DocentDAO implements DAO<Docent> {

    /**
     * The Docenten.
     */
    protected ArrayList<Docent> docenten;

    /**
     * Instantiates a new Docent dao.
     */
    public DocentDAO (){
        this.docenten=new ArrayList<>();
    }

    /**
     * Get by id docent.
     *
     * @param id the id
     * @return the docent
     */
    public Docent getById(int id){
        if (id >= 0 && id <= docenten.size()) {
            return docenten.get(id);
        } else {
            return null;
        }}

    /**
     * Gets id for.
     *
     * @param object the object
     * @return the id for
     */
    public int getIdFor(Docent object) {
        int index = docenten.indexOf(object);
        if (index != -1) {
            return index;
        } else {
            return -1;
        }
    }
    @Override
    public List<Docent> getAll() {
        return docenten;
    }

    @Override
    public void addOrUpdate(Docent model) {
        if(docenten.contains(model)){
            return;
        }
        docenten.add(model);

    }

    @Override
    public void delete(Docent model) {
        if (model != null){
            docenten.remove(model);
        }
    }

    @Override
    public abstract boolean save();


    @Override
    public abstract boolean load();
}
