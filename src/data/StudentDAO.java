package data;

import models.Docent;
import models.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Student dao.
 */
public abstract class StudentDAO implements DAO<Student>{

    /**
     * The Studenten.
     */
    protected ArrayList<Student> studenten ;

    /**
     * Instantiates a new Student dao.
     */
    public StudentDAO(){
        this.studenten=new ArrayList<>();
    }

    /**
     * Get all for list.
     *
     * @param object the object
     * @return the list
     */
    public List<Student> getAllFor(Docent object){
        List<Student> spelerList= new ArrayList<>();
        for (Student student : studenten) {
            if( student.getHoortBij()==object){
                spelerList.add(student);
            }
        }
        return spelerList;
    }
    @Override
    public List<Student> getAll() {
        return studenten;
    }

    @Override
    public void addOrUpdate(Student model) {
        if(studenten.contains(model)){
            return;
        }
        studenten.add(model);

    }

    @Override
    public void delete(Student model) {
        if (model != null){
            studenten.remove(model);
        }

    }

    @Override
    public abstract boolean save();

    @Override
    public abstract boolean load();
}
