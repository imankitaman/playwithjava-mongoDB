package model;

import de.caluga.morphium.annotations.Entity;
import de.caluga.morphium.annotations.Id;
import de.caluga.morphium.annotations.caching.Cache;

@Entity(translateCamelCase = true, collectionName="my_entities")
@Cache
public class MyEntity{
  
	@Id
	public String id;
	public String name;
	public String address;
	public String age;
	
	
	@Override
	public String toString() {
		return "MyEntity [id=" + id + ", name=" + name + ", address=" + address + ", age=" + age + "]";
	}
	
	
	
	

    
    
}
