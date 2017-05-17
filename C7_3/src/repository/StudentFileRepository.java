package repository;

import domain.Student;
import domain.validators.Validator;
import domain.validators.ValidatorException;

import java.io.*;

public class StudentFileRepository extends InMemoryRepository<Student,String> {
	private String fName;
	public StudentFileRepository(Validator<Student> v, String fName) {
		super(v);
		this.fName=fName;
		loadData();
	}

	private void loadData(){
		try(BufferedReader br=new BufferedReader(new FileReader(fName))){
			String line;
			while((line=br.readLine())!=null){
				String[] atributes=line.split(";");
				if(atributes.length!=4)
					throw new Exception("Linia nu este valida!");
                Student t=new Student(atributes[0],atributes[1],atributes[2],atributes[3]);
				super.save(t);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void writeToFile() {
		try (BufferedWriter br = new BufferedWriter(new FileWriter(fName))){
			super.entities.forEach(x -> {
				try {
					br.write(x.studentFileLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Student save(Student t) throws ValidatorException {
        Student a=super.save(t);
		writeToFile();
		return a;
	}


}