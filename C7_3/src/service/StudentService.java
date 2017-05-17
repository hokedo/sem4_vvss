package service;

import java.util.*;

import domain.Student;
import domain.validators.ValidatorException;
import repository.Repository;
import utils.*;
import utils.Observer;

public class StudentService implements utils.Observable<Student> {
	private Repository<Student,String> studentRepo;
	protected List <Observer<Student>> observers = new ArrayList<Observer<Student>>();
	
	public StudentService(Repository<Student,String> repo)
	{
		studentRepo=repo;
	}

	public Student save(Student t) throws ValidatorException
	{
		Student s= null;
		try {
			s = studentRepo.save(t);
			if (s==null)
			{
				notifyObservers();
			}
		} catch (ValidatorException e) {
			throw e;
		}
		return s;
	}
	public List<Student>  getAllStudents()
	{
		List<Student> s=new ArrayList<>();
		Iterable<Student> list=studentRepo.findAll();
		for (Student t:list)
			s.add(t);
		return s;
	}

	@Override
	public void addObserver(Observer<Student> o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer<Student> o) {
		observers.remove(o);
	}

	@Override
	public void notifyObservers(){
		for(Observer<Student> o : observers){
			o.update(this);
		}
	}
}
