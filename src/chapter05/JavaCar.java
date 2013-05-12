package chapter05;

/**
 * Date: 12/05/13
 * Time: 21:18
 *
 * class Car(val manufacturer: String, val modelName: String, val modelYear: Int, var licensePlate: String) {
 *   def this(manufacturer: String, modelName: String){ this(manufacturer, modelName, -1, "") }
 *   def this(manufacturer: String, modelName: String, modelYear: Int){ this(manufacturer, modelName, modelYear, "") }
 *   def this(manufacturer: String, modelName: String, licensePlate: String){ this(manufacturer, modelName, -1, licensePlate) }
 *
 *   override def toString: String = "[manufacturer = %s, model = %s, year = %d, plate = %s]".format(manufacturer, modelName, modelYear, licensePlate)
 * }
 *
 */
public class JavaCar {

    private final String manufacturer;
    private final String modelName;
    private final int modelYear;

    private final String licensePlate;

    public JavaCar(String manufacturer, String modelName) {
        this(manufacturer, modelName, -1, "");
    }

    public JavaCar(String manufacturer, String modelName, int modelYear) {
        this(manufacturer, modelName, modelYear, "");
    }

    public JavaCar(String manufacturer, String modelName, String licensePlate) {
        this(manufacturer, modelName, -1, licensePlate);
    }

    public JavaCar(String manufacturer, String modelName, int modelYear, String licensePlate) {
        this.manufacturer = manufacturer;
        this.modelName = modelName;
        this.modelYear = modelYear;
        this.licensePlate = licensePlate;
    }

    @Override
    public String toString() {
        return "JavaCar{" +
                "manufacturer='" + manufacturer + '\'' +
                ", modelName='" + modelName + '\'' +
                ", modelYear=" + modelYear +
                ", licensePlate='" + licensePlate + '\'' +
                '}';
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public int getModelYear() {
        return modelYear;
    }

    public String getModelName() {
        return modelName;
    }

    public String getManufacturer() {
        return manufacturer;
    }
}
