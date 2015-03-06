import java.io.IOException;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;


public class DaoNGBookGenerator {
public static void main(String[] args) throws IOException, Exception {
	Schema schema=new Schema(1, "com.sswork.ngbook");
	addRecord(schema);
	new DaoGenerator().generateAll(schema, "../NGBook/src-gen");
}
private static void addRecord(Schema schema){
	Entity record=schema.addEntity("Record");
	record.addIdProperty();
	record.addStringProperty("bookName").notNull();
	record.addLongProperty("oldTime").notNull();
	record.addStringProperty("path").notNull();
	record.addStringProperty("record").notNull();
}
}
