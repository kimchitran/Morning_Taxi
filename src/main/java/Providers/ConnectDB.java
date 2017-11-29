package Providers;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.*;

public class ConnectDB {
	
	MongoCollection<Document> collection;
	
	public ConnectDB() {
		 MongoClientURI connectionString = new MongoClientURI("mongodb://morning:morning@morning.taxionline.vn:2411/taxi_dev?3t.uriVersion=2&3t.connection.name=taxidev&3t.connectionMode=direct&readPreference=primary");
		 MongoClient mongoClient = new MongoClient(connectionString);
		 MongoDatabase database = mongoClient.getDatabase("taxi_dev");
		 MongoCollection<Document> collection = database.getCollection("requests");
	} 
	
	public String GetPhoneNumber (){
		 MongoClientURI connectionString = new MongoClientURI("mongodb://morning:morning@morning.taxionline.vn:2411/taxi_dev?3t.uriVersion=2&3t.connection.name=taxidev&3t.connectionMode=direct&readPreference=primary");
		 MongoClient mongoClient = new MongoClient(connectionString);
		 MongoDatabase database = mongoClient.getDatabase("taxi_dev");
		 MongoCollection<Document> collection = database.getCollection("requests");
	    Document fields = new Document();
	    fields.put("phone", 1);
	    fields.put("_id", 0);	
	    Document cursor =  collection.find().projection(fields).sort(new Document("_id", -1)).first();
	    String phone = cursor.values() + "";
	    return (phone.substring(1, phone.length()-1));
	}
	public void DeleteData (){
		 MongoClientURI connectionString = new MongoClientURI("mongodb://morning:morning@morning.taxionline.vn:2411/taxi_dev?3t.uriVersion=2&3t.connection.name=taxidev&3t.connectionMode=direct&readPreference=primary");
		 MongoClient mongoClient = new MongoClient(connectionString);
		 MongoDatabase database = mongoClient.getDatabase("taxi_dev");
		 MongoCollection<Document> collection = database.getCollection("requests");
		 collection.deleteMany( eq("phone", "841626116635"));
	}
	
	public void InsertData (){
		 MongoClientURI connectionString = new MongoClientURI("mongodb://morning:morning@morning.taxionline.vn:2411/taxi_dev?3t.uriVersion=2&3t.connection.name=taxidev&3t.connectionMode=direct&readPreference=primary");
		 MongoClient mongoClient = new MongoClient(connectionString);
		 MongoDatabase database = mongoClient.getDatabase("taxi_dev");
		 MongoCollection<Document> collection = database.getCollection("requests");
		
	}
	
	
   
   public static void main( String args[] ) {  
      
	   MongoClientURI connectionString = new MongoClientURI("mongodb://morning:morning@morning.taxionline.vn:2411/taxi_dev?3t.uriVersion=2&3t.connection.name=taxidev&3t.connectionMode=direct&readPreference=primary");
       MongoClient mongoClient = new MongoClient(connectionString);
       MongoDatabase database = mongoClient.getDatabase("taxi_dev");
       MongoCollection<Document> collection = database.getCollection("requests");
       
//       FindIterable<Document> cursor =  collection.find().sort(new Document("_id", -1)).projection(new Document("phone", 1)
//               .append("bAddress", 1)
//               .append("_id", 0));
//       while(((Object) cursor).hasNext()){
//           System.out.println(((Object) cursor).next());
//       }

//       Document myDoc = collection.find().sort(new Document("_id", -1)).first();
//       System.out.println(myDoc.toJson());
       
       Document doc1 = new Document("phone", "01649558528")
    		   			.append("carType" , 2)
    		   			.append("note" , "abc xyz")
    		   			.append("user", new ObjectId("59b28a3a43a071083c1170db"))
    		   			.append("bAddress", "12 Duy Tân, Cầu Giấy, Hanoi, Vietnam");
       collection.insertOne(doc1);
       System.out.println(doc1.toJson());
       
//       Document query = new Document();
//       query.put("phone", "01626116635");

//       Document fields = new Document();
//       fields.put("phone", 1);
//       fields.put("_id", 0);
////       Document myDoc = collection.find(fields).sort(new Document("_id", -1)).first();
////       System.out.println(myDoc.toJson());
//       Document cursor =  collection.find().projection(fields).sort(new Document("_id", -1)).first();
//       String phone = cursor.values() + "";
//      int i = phone.length();
//       System.out.println(phone.substring(1, i-1));

//       for (Document document : cursor) {
//    	    System.out.println(document);
//    	}
//        while(cursor.hasNext()){
//               System.out.println(((Iterator<DBObject>) cursor).next());
//           }
//	   collection.deleteMany( eq("phone", "841626116635"));
//////       
//	   ConnectDB con = new ConnectDB();
//	   con.DeleteData();
   } 
}