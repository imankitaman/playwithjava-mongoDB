package controllers;

import static play.libs.Json.toJson;

import java.net.UnknownHostException;
import java.util.List;

import model.MyEntity;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import de.caluga.morphium.MorphiumConfig;
import de.caluga.morphium.MorphiumSingleton;
import de.caluga.morphium.query.Query;

public class Application extends Controller {

	public Result index() {
		return ok(index.render("Your new application is ready."));
	}

	public Result addStudents() {

		MorphiumConfig cfg = new MorphiumConfig();
		cfg.setDatabase("test");
		try {
			cfg.addHost("localhost", 27017);
		} catch (UnknownHostException e) {

			e.printStackTrace();
		}
		if (!MorphiumSingleton.isConfigured()) {
			MorphiumSingleton.setConfig(cfg);
		}
		
		MyEntity me= Form.form(MyEntity.class).bindFromRequest().get();

		
		// Saving object
		MorphiumSingleton.get().store(me);

		System.out.println(me);
		return redirect(routes.Application.index());

//		return ok(Json.toJson(me));
		}
	
	

	public Result getStudents(){
		
		MorphiumConfig cfg = new MorphiumConfig();
		cfg.setDatabase("test");
		try {
			cfg.addHost("localhost", 27017);
		} catch (UnknownHostException e) {

			e.printStackTrace();
		}
		if (!MorphiumSingleton.isConfigured()) {
			MorphiumSingleton.setConfig(cfg);
		}
		
		  Query q = MorphiumSingleton.get().createQueryFor(MyEntity.class);
		  
		  //use comment line for query
//           q =q.f(MyEntity.Fields.id).eq(q.getById(q));
//           q=q.f(test);
         
           List<MyEntity> list = q.asList();
		 return ok(Json.toJson(list));

	}
	
}
