package kata_6_json;
import com.google.gson.annotations.SerializedName;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Fruit {
    @XmlElement(name = "Genus")
    public String genus;
    @XmlElement(name = "Name")
    public String name;
    @XmlElement(name = "Id")
    public int id;
    @XmlElement(name = "Family")
    public String family;
    @XmlElement(name = "Order")
    public String order;
    @XmlElement(name = "Nutritions")
    public Nutritions nutritions;

    @Override
    public String toString() {
        return "Id: " + id + " // Fruit: " + name + " // Genus: " + genus 
                + " // Family: " + family + " // Order: " + order 
                + " // Nutritions -> " + nutritions.toString();
    }
    
    public static class Nutritions{
        @XmlElement(name = "Carbohydrates")
        public int carbohydrates;
        @XmlElement(name = "Protein")
        public int protein;
        @XmlElement(name = "Fat")
        public int fat;
        @XmlElement(name = "Calories")
        public int calories;
        @XmlElement(name = "Sugar")
        public int sugar;

        @Override
        public String toString() {
            return "[ Carbohydrates = " + carbohydrates + " Protein = " + protein + " Fat = " + fat 
                    + " Calories = " + calories + " Sugar = " + sugar + " ]";
        }
        
        

    }
}
