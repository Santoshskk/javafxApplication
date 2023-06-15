package data;

import models.Docent;

import java.util.ArrayList;
import java.util.List;

public abstract class DocentDAO implements DAO<Docent> {

    protected ArrayList<Docent> docenten;

    public DocentDAO (){
        this.docenten=new ArrayList<>();
    }

    public Docent getById(int id){
        if (id >= 0 && id < docenten.size()) {
            return docenten.get(id);
        } else {
            return null;
        }}
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
        docenten.remove(model);

    }

    @Override
    public abstract boolean save();


    @Override
    public abstract boolean load();
}
