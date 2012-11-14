package persistence;
import java.util.List;

import model.Model;

public interface ModelDao {

	public void save(Model model);

	public void update(Model model);

	public void delete(Model model);
	
	public List<Model> find(Boolean deleted);
	
	public List<Model> find(String name);
	
	public List<Model> find(String name,Boolean deleted);

}