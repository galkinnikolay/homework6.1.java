import java.util.Collections;
import java.util.List;
import java.util.Arrays;

public class Notebook {
    
    public static enum OS {
        MacOS, 
        Windows,
        Linux; 
        private static final List<OS> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
        public static int SIZE = VALUES.size();           
        public static OS get(int number){ return VALUES.get(number); }
        public static String items(){ 
            String[] array = new String[SIZE]; 
            for(int i=0; i<SIZE; i++)
                array[i] = VALUES.get(i).toString();
            return String.join(", ", array);
        }
    }
    public static enum Color {
        white, 
        black, 
        gray, 
        yellow, 
        red, 
        blue, 
        purple, 
        green;
        private static final List<Color> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
        public static int SIZE = VALUES.size();           
        public static Color get(int number){ return VALUES.get(number); }
        public static String items(){ 
            String[] array = new String[SIZE]; 
            for(int i=0; i<SIZE; i++)
                array[i] = VALUES.get(i).toString();
            return String.join(", ", array);
        }
    }
    
    public static final int[] OptionOZU = {2, 4, 6, 8, 12, 16, 24};    
    
    public static final int[] OptionHDD = {50, 100, 150, 200, 250};   

    
    private int id;
    private OS type_OS;
    private int capacityOZU, capacityHDD;
    private Color color;

    
    public int getId(){ return id; }
    public OS getTypeOS(){ return type_OS; }
    public int getCapacityOZU(){ return capacityOZU; }
    public int getCapacityHDD(){ return capacityHDD; }
    public Color getColor(){ return color; }

    
    public Notebook(int id,OS type_OS,int capacityOZU,int capacityHDD,Color color){
        this.id = id;
        this.type_OS = type_OS;
        this.capacityOZU = capacityOZU;
        this.capacityHDD = capacityHDD;
        this.color = color;
    } 
    
    public String toString() {
        return String.format("id: %d, тип ОС: %s, размер ОЗУ: %d, объем HDD: %d, color: %s", 
            id, type_OS, capacityOZU, capacityHDD, color);
    }
}