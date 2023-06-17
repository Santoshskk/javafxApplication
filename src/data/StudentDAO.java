package data;

import models.Docent;
import models.Student;

import java.util.ArrayList;
import java.util.List;

public abstract class StudentDAO implements DAO<Student>{

    protected ArrayList<Student> studenten ;

    public StudentDAO(){
        this.studenten=new ArrayList<>();
    }

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
